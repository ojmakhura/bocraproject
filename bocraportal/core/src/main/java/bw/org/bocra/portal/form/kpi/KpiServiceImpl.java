// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::kpi::KpiService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.kpi;

import bw.org.bocra.portal.form.FormCriteria;
import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.form.kpi.KpiService
 */
//@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("kpiService")
public class KpiServiceImpl
    extends KpiServiceBase
{

    /**
     * @see bw.org.bocra.portal.form.kpi.KpiService#findById(Long)
     */
    @Override
    protected  KpiVO handleFindById(Long id)
        throws Exception
    {

        if(id == null) {
            return null;
        }

        return (KpiVO) getKpiDao().get(KpiDao.TRANSFORM_KPIVO, id);

    }

    /**
     * @see bw.org.bocra.portal.form.kpi.KpiService#save(KpiVO)
     */
    @Override
    protected  KpiVO handleSave(KpiVO kpiVO)
        throws Exception
    {
        Kpi kpi = getKpiDao().kpiVOToEntity(kpiVO);
        if(kpiVO.getId() == null) {
            kpi = getKpiDao().create(kpi);
            return getKpiDao().toKpiVO(kpi);
        } else {
            getKpiDao().update(kpi);
            return kpiVO;
        }
    }

    /**
     * @see bw.org.bocra.portal.form.kpi.KpiService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {

        if(id == null) {
            return false;
        } 

        kpiRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.form.kpi.KpiService#getAll()
     */
    @Override
    protected  Collection<KpiVO> handleGetAll()
        throws Exception
    {
        return (Collection<KpiVO>) getKpiDao().loadAll(KpiDao.TRANSFORM_KPIVO);
    }

    /**
     * @see bw.org.bocra.portal.form.kpi.KpiService#search(FormCriteria)
     */
    @Override
    protected  Collection<KpiVO> handleSearch(FormCriteria searchCriteria)
        throws Exception
    {
        return getKpiDao().toKpiVOCollection(getKpiDao().findByCriteria(searchCriteria));
    }

}