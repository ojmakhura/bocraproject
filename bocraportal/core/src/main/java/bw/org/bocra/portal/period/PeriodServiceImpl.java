// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::period::PeriodService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.period;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.BocraportalSpecifications;
import bw.org.bocra.portal.period.config.PeriodConfigVO;
import bw.org.bocra.portal.period.config.RepeatPeriod;

/**
 * @see bw.org.bocra.portal.period.PeriodService
 */
@Service("periodService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class PeriodServiceImpl
    extends PeriodServiceBase
{

    public PeriodServiceImpl(PeriodDao periodDao, PeriodRepository periodRepository, MessageSource messageSource) {
        super(periodDao, periodRepository, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.period.PeriodService#findById(Long)
     */
    @Override
    protected  PeriodVO handleFindById(Long id)
        throws Exception
    {

        PeriodVO periodVO = null;

        if(id != null) {
            Period period = this.periodRepository.getById(id);
            periodVO = getPeriodDao().toPeriodVO(period);
        }

        return periodVO;
    }

    /**
     * @see bw.org.bocra.portal.period.PeriodService#save(PeriodVO)
     */
    @Override
    protected  PeriodVO handleSave(PeriodVO periodVO)
        throws Exception
    {
        Period period = periodDao.periodVOToEntity(periodVO);
        period = this.periodRepository.save(period);

        if(periodVO.getId() == null) {
            periodVO = getPeriodDao().toPeriodVO(period);
        }

        if(period.getPrevious() != null && period.getPrevious().getId() != null) {
            Period prev = period.getPrevious();
            if(prev.getNext() == null || (prev.getNext() != null && prev.getNext().getId() != period.getId())) {
                prev.setNext(period);
                getPeriodRepository().save(prev);
            }
        }

        if(period.getNext() != null && period.getNext().getId() != null) {
            Period next = period.getNext();
            if(next.getPrevious() == null || (next.getPrevious() != null && next.getPrevious().getId() != period.getId())) {
                next.setPrevious(period);
                getPeriodRepository().save(next);
            }
        }

        return periodVO;
    }

    /**
     * @see bw.org.bocra.portal.period.PeriodService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {

        if(id == null) {
            return false;
        }

        this.periodRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.period.PeriodService#getAll()
     */
    @Override
    protected  Collection<PeriodVO> handleGetAll()
        throws Exception
    {
        return (Collection<PeriodVO>) periodDao.loadAll(PeriodDao.TRANSFORM_PERIODVO);

    }

    /**
     * @see bw.org.bocra.portal.period.PeriodService#search(PeriodCriteria)
     */
    @Override
    protected  Collection<PeriodVO> handleSearch(PeriodCriteria criteria)
        throws Exception
    {
        Collection<Period> periods = getPeriodDao().findByCriteria(criteria);
        Collection<PeriodVO> vos = new ArrayList<>();

        for(Period period : periods) {
            vos.add(getPeriodDao().toPeriodVO(period));
        }

        return vos;
    }

    @Override
    protected Collection<PeriodVO> handleGetAll(Integer pageNumber, Integer pageSize) throws Exception {

        Collection<Period> periods = null;

        if(pageNumber < 0 || pageSize < 1) {
            periods = periodRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("periodName").descending());
            periods = periodRepository.findAll(pageable).getContent();
        }

        return periods == null ? null : getPeriodDao().toPeriodVOCollection(periods);
    }

    @Override
    protected Collection<PeriodVO> handleLoadCurrentPeriods() throws Exception {

        Collection<Period> periods = periodDao.getActivePeriods();
        
        return periods == null ? null : getPeriodDao().toPeriodVOCollection(periods);
    }

    private LocalDate calculateEndDate(LocalDate start, PeriodConfigVO config) {
        LocalDate next = LocalDate.parse(start.format(DateTimeFormatter.ISO_DATE)); //new Date(start.toString());
    
        if (config.getRepeatPeriod() == RepeatPeriod.MONTHS) {
            next = next.plusMonths(config.getRepeat());
    
        } else if (config.getRepeatPeriod() == RepeatPeriod.YEARS) {
            
            next = next.plusYears(config.getRepeat());
            
            // next.setFullYear(start.getFullYear() + config.getRepeat());
            // next.setMonth(start.getMonth());
        }
        
        next = next.minusDays(1);
    
        return next;
      }

    @Override
    protected Collection<PeriodVO> handleCreateNextPeriods(String username) throws Exception {

        // Collection<PeriodVO> currentPeriods = loadCurrentPeriods();
        // Collection<PeriodVO> nextPeriods = new ArrayList<>();

        // currentPeriods.stream().forEach(period -> {
        //     if(period.getNext() == null || period.getNext().getId() == null) {
                    
        //         PeriodVO per = new PeriodVO();
        //         per.setCreatedBy(username);
        //         per.setCreatedDate(LocalDateTime.now());
        //         per.setPeriodConfig(period.getPeriodConfig());
        //         per.setPeriodStart(period.getPeriodEnd().plusDays(1));
    
        //         per.setPeriodEnd(calculateEndDate(per.getPeriodStart(), period.getPeriodConfig()));
    
        //         per.setPrevious(period);
    
        //         per = this.save(period);
    
        //         nextPeriods.add(per);
        //     }
        // });

        /**
         * First filter out the periods that have a next since that implies the next period has already
         * been created.
         * Next, for each current period, we create a new period with the current period as the previous.
         */
        return loadCurrentPeriods().stream()
            .filter(period -> period.getNext() != null && period.getNext().getId() != null)
            .map(period -> {
                PeriodVO per = new PeriodVO();
                per.setCreatedBy(username);
                per.setCreatedDate(LocalDateTime.now());
                per.setPeriodConfig(period.getPeriodConfig());
                per.setPeriodStart(period.getPeriodEnd().plusDays(1));

                per.setPeriodEnd(calculateEndDate(per.getPeriodStart(), period.getPeriodConfig()));

                per.setPrevious(period);

                per = this.save(period);

                return per;
            }).collect(Collectors.toList());

        // for (PeriodVO period : currentPeriods) {

        //     if(period.getNext() != null && period.getNext().getId() != null) {
        //         continue;
        //     }

        //     PeriodVO per = new PeriodVO();
        //     per.setCreatedBy(username);
        //     per.setCreatedDate(LocalDateTime.now());
        //     per.setPeriodConfig(period.getPeriodConfig());
        //     per.setPeriodStart(period.getPeriodEnd().plusDays(1));

        //     per.setPeriodEnd(calculateEndDate(per.getPeriodStart(), period.getPeriodConfig()));

        //     per.setPrevious(period);

        //     per = this.save(period);

        //     nextPeriods.add(per);
        // }
    }

}