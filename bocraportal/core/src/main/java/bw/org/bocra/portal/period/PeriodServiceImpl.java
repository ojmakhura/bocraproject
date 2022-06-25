// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::period::PeriodService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.period;

import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @see bw.org.bocra.portal.period.PeriodService
 */
@Service("periodService")
public class PeriodServiceImpl
    extends PeriodServiceBase
{

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

        if(periodVO.getId() != null) {
            periodVO = getPeriodDao().toPeriodVO(period);
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
        return getPeriodDao().toPeriodVOCollection(periods);
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

}