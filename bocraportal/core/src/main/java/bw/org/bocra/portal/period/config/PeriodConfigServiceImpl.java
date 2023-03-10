// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::period::instance::PeriodConfigService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.period.config;

import bw.org.bocra.portal.period.PeriodCriteria;
import bw.org.bocra.portal.period.PeriodSpecifications;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.period.config.PeriodConfigService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("periodConfigService")
public class PeriodConfigServiceImpl
    extends PeriodConfigServiceBase
{

    public PeriodConfigServiceImpl(PeriodConfigDao periodConfigDao, PeriodConfigRepository periodConfigRepository,
            MessageSource messageSource) {
        super(periodConfigDao, periodConfigRepository, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.period.config.PeriodConfigService#findById(Long)
     */
    @Override
    protected  PeriodConfigVO handleFindById(Long id)
        throws Exception
    {
        return (PeriodConfigVO) getPeriodConfigDao().get(PeriodConfigDao.TRANSFORM_PERIODCONFIGVO, id);
    }

    /**
     * @see bw.org.bocra.portal.period.config.PeriodConfigService#save(PeriodConfigVO)
     */
    @Override
    protected  PeriodConfigVO handleSave(PeriodConfigVO periodConfigVO)
        throws Exception
    {
        if(periodConfigVO == null) {
            return null;
        }

        PeriodConfig entity = getPeriodConfigDao().periodConfigVOToEntity(periodConfigVO);

        entity = periodConfigRepository.saveAndFlush(entity);

        return periodConfigDao.toPeriodConfigVO(entity);
    }

    /**
     * @see bw.org.bocra.portal.period.config.PeriodConfigService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {

        if(id == null) return false;

        this.periodConfigRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.period.config.PeriodConfigService#getAll()
     */
    @Override
    protected  Collection<PeriodConfigVO> handleGetAll()
        throws Exception
    {
        return (Collection<PeriodConfigVO>) getPeriodConfigDao().loadAll(PeriodConfigDao.TRANSFORM_PERIODCONFIGVO);
    }

    /**
     * @see bw.org.bocra.portal.period.config.PeriodConfigService#search(PeriodCriteria)
     */
    @Override
    protected  Collection<PeriodConfigVO> handleSearch(PeriodConfigCriteria criteria)
        throws Exception
    {

        return (Collection<PeriodConfigVO>) getPeriodConfigDao().findByCriteria(PeriodConfigDao.TRANSFORM_PERIODCONFIGVO, criteria);
    }

    @Override
    protected Collection<PeriodConfigVO> handleGetAll(Integer pageNumber, Integer pageSize) throws Exception {
        
        Collection<PeriodConfig> instances = null;

        if(pageNumber < 0 || pageSize < 1) {
            instances = periodConfigRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("periodConfigName").descending());
            instances = periodConfigRepository.findAll(pageable).getContent();
        }

        return instances == null ? null : getPeriodConfigDao().toPeriodConfigVOCollection(instances);
    }

}