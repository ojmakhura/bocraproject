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
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
            Period period = this.periodRepository.getReferenceById(id);
            periodVO = getPeriodDao().toPeriodVO(period);
        }

        return periodVO;
    }


    private String getPeriodName(LocalDate start, LocalDate next) {

        String val = "" + start.getYear();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM");

        if (start.getYear() != next.getYear()) {

            val = formatter.format(start) + " " + start.getYear() + " - " + formatter.format(next) + " " + next.getYear();
        } else {

            val = "" + formatter.format(start);
            if (start.getMonth() != next.getMonth()) {
                val = val + " - " + formatter.format(next);
            }

            val = val + " " +  next.getYear();
        }

        return val;
    }

    /**
     * @see bw.org.bocra.portal.period.PeriodService#save(PeriodVO)
     */
    @Override
    protected  PeriodVO handleSave(PeriodVO periodVO)
        throws Exception
    {

        if(periodVO.getPeriodStart() == null && periodVO.getPeriodEnd() == null) {

            LocalDate start = null;
            LocalDate end = null;
            
            if(periodVO.getPrevious() != null && periodVO.getPrevious().getId() != null) {

                start = periodRepository.getReferenceById(periodVO.getPrevious().getId()).getPeriodEnd().plusDays(1);
                end = calculateEndDate(start, periodVO.getPeriodConfig()); 

            } else if(periodVO.getNext() != null && periodVO.getNext().getId() != null) {

                end = periodRepository.getReferenceById(periodVO.getNext().getId()).getPeriodStart().minusDays(1); 
                start = this.calculateStartDate(end, periodVO.getPeriodConfig());

            } else {

                // Since we have no start or end, we use period configuration to initialise
                start = LocalDate.of(LocalDate.now().getYear(), periodVO.getPeriodConfig().getStartMonth(), periodVO.getPeriodConfig().getStartDay());
                end = calculateEndDate(start, periodVO.getPeriodConfig());
            }

            periodVO.setPeriodStart(start);
            periodVO.setPeriodEnd(end);

        } else if(periodVO.getPeriodEnd() == null) { // Only the end date is null

            periodVO.setPeriodEnd(this.calculateEndDate(periodVO.getPeriodStart(), periodVO.getPeriodConfig()));
        } else { // Only the start is null
            periodVO.setPeriodStart(this.calculateStartDate(periodVO.getPeriodEnd(), periodVO.getPeriodConfig()));
        }

        // If the period name is empty, we create a new one
        if(StringUtils.isEmpty(periodVO.getPeriodName())) {
            periodVO.setPeriodName(this.getPeriodName(periodVO.getPeriodStart(), periodVO.getPeriodEnd()));
        }

        Period period = periodDao.periodVOToEntity(periodVO);
        period = this.periodRepository.saveAndFlush(period);

        if(periodVO.getId() == null) {
            periodVO = getPeriodDao().toPeriodVO(period);
        }

        if(period.getPrevious() != null && period.getPrevious().getId() != null) {
            Period prev = period.getPrevious();
            if(prev.getNext() == null || (prev.getNext() != null && prev.getNext().getId() != period.getId())) {
                prev.setNext(period);
                getPeriodRepository().saveAndFlush(prev);
            }
        }

        if(period.getNext() != null && period.getNext().getId() != null) {
            Period next = period.getNext();
            if(next.getPrevious() == null || (next.getPrevious() != null && next.getPrevious().getId() != period.getId())) {
                next.setPrevious(period);
                getPeriodRepository().saveAndFlush(next);
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

        Period period = this.periodRepository.getReferenceById(id);
        
        if(period.getPrevious() != null) {
            Period prev = period.getPrevious();
            prev.setNext(null);
            period.setPrevious(null);

            this.periodRepository.saveAndFlush(prev);
        }

        if(period.getNext() != null) {
            Period next = period.getNext();
            next.setPrevious(null);
            period.setNext(null);

            this.periodRepository.saveAndFlush(next);
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
        }
        
        next = next.minusDays(1);
    
        return next;
    }

    private LocalDate calculateStartDate(LocalDate end, PeriodConfigVO config) {
        LocalDate next = end;
    
        if (config.getRepeatPeriod() == RepeatPeriod.MONTHS) {
            next = end.minusMonths(config.getRepeat());
    
        } else if (config.getRepeatPeriod() == RepeatPeriod.YEARS) {
            
            next = end.minusYears(config.getRepeat());
        }
        
        next = next.plusDays(1);
    
        return next;
    }

    @Override
    protected Collection<PeriodVO> handleCreateNextPeriods(String username, Set<Long> periodIds) throws Exception {

        Collection<PeriodVO> nextPeriods = new ArrayList<>();

        Collection<PeriodVO> periods = 
                    CollectionUtils.isEmpty(periodIds) ? 
                        loadCurrentPeriods() :
                        periodDao.toPeriodVOCollection(periodRepository.findByIdIn(periodIds.stream().collect(Collectors.toList())));


        for (PeriodVO period : periods) {

            if((period.getNext() != null && period.getNext().getId() != null) || // next period already created
                    (period.getPeriodEnd() != null && period.getPeriodEnd().isBefore(LocalDate.now().plusDays(1)))) { // period ends today or tomorrow
                continue;
            }

            PeriodVO per = new PeriodVO();
            per.setCreatedBy(username);
            per.setCreatedDate(LocalDateTime.now());
            per.setPeriodConfig(period.getPeriodConfig());
            per.setPeriodStart(period.getPeriodEnd().plusDays(1));

            per.setPeriodEnd(calculateEndDate(per.getPeriodStart(), period.getPeriodConfig()));

            per.setPrevious(period);

            per = this.save(per);

            nextPeriods.add(per);
        }

        return nextPeriods;
    }

}