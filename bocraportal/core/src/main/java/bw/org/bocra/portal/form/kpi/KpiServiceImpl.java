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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.form.kpi.KpiService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
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
        // TODO implement protected  KpiVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.kpi.KpiService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.kpi.KpiService#save(KpiVO)
     */
    @Override
    protected  KpiVO handleSave(KpiVO kpiVO)
        throws Exception
    {
        // TODO implement protected  KpiVO handleSave(KpiVO kpiVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.kpi.KpiService.handleSave(KpiVO kpiVO) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.kpi.KpiService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.kpi.KpiService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.kpi.KpiService#getAll()
     */
    @Override
    protected  Collection<KpiVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<KpiVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.kpi.KpiService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.kpi.KpiService#search(FormCriteria)
     */
    @Override
    protected  Collection<KpiVO> handleSearch(FormCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  Collection<KpiVO> handleSearch(FormCriteria searchCriteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.kpi.KpiService.handleSearch(FormCriteria searchCriteria) Not implemented!");
    }

}