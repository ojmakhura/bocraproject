// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::period::instance::PeriodInstanceService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.period.instance;

import bw.org.bocra.portal.period.PeriodCriteria;
import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.period.instance.PeriodInstanceService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("periodInstanceService")
public class PeriodInstanceServiceImpl
    extends PeriodInstanceServiceBase
{

    /**
     * @see bw.org.bocra.portal.period.instance.PeriodInstanceService#findById(Long)
     */
    @Override
    protected  PeriodInstanceVO handleFindById(Long id)
        throws Exception
    {
        return (PeriodInstanceVO) getPeriodInstanceDao().get(PeriodInstanceDao.TRANSFORM_PERIODINSTANCEVO, id);
    }

    /**
     * @see bw.org.bocra.portal.period.instance.PeriodInstanceService#save(PeriodInstanceVO)
     */
    @Override
    protected  PeriodInstanceVO handleSave(PeriodInstanceVO periodInstanceVO)
        throws Exception
    {
        if(periodInstanceVO == null) {
            return null;
        }

        PeriodInstance entity = getPeriodInstanceDao().periodInstanceVOToEntity(periodInstanceVO);

        if(periodInstanceVO.getId() != null) {
            getPeriodInstanceDao().update(entity);
        } else {
            periodInstanceVO = (PeriodInstanceVO) getPeriodInstanceDao().create(PeriodInstanceDao.TRANSFORM_PERIODINSTANCEVO, entity);
        }

        return periodInstanceVO;
    }

    /**
     * @see bw.org.bocra.portal.period.instance.PeriodInstanceService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {

        if(id == null) return false;

        this.periodInstanceRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.period.instance.PeriodInstanceService#getAll()
     */
    @Override
    protected  Collection<PeriodInstanceVO> handleGetAll()
        throws Exception
    {
        return (Collection<PeriodInstanceVO>) getPeriodInstanceDao().loadAll(PeriodInstanceDao.TRANSFORM_PERIODINSTANCEVO);
    }

    /**
     * @see bw.org.bocra.portal.period.instance.PeriodInstanceService#search(PeriodCriteria)
     */
    @Override
    protected  Collection<PeriodInstanceVO> handleSearch(PeriodInstanceCriteria searchCriteria)
        throws Exception
    {

        return (Collection<PeriodInstanceVO>) getPeriodInstanceDao().findByCriteria(PeriodInstanceDao.TRANSFORM_PERIODINSTANCEVO, searchCriteria);
    }

    @Override
    protected Collection<PeriodInstanceVO> handleGetAll(Integer pageNumber, Integer pageSize) throws Exception {
        
        Collection<PeriodInstance> instances = null;

        if(pageNumber < 0 || pageSize < 1) {
            instances = periodInstanceRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("periodInstanceName").descending());
            instances = periodInstanceRepository.findAll(pageable).getContent();
        }

        return instances == null ? null : getPeriodInstanceDao().toPeriodInstanceVOCollection(instances);
    }

}