// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::sim::SimService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.sim;

import bw.org.bocra.portal.form.FormCriteria;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.form.sim.SimService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("simService")
public class SimServiceImpl
    extends SimServiceBase
{

    /**
     * @see bw.org.bocra.portal.form.sim.SimService#findById(Long)
     */
    @Override
    protected  SimVO handleFindById(Long id)
        throws Exception
    {
        // TODO implement protected  SimVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.sim.SimService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.sim.SimService#save(SimVO)
     */
    @Override
    protected  SimVO handleSave(SimVO simVO)
        throws Exception
    {
        // TODO implement protected  SimVO handleSave(SimVO simVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.sim.SimService.handleSave(SimVO simVO) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.sim.SimService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.sim.SimService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.sim.SimService#getAll()
     */
    @Override
    protected  Collection<SimVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<SimVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.sim.SimService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.sim.SimService#search(FormCriteria)
     */
    @Override
    protected  Collection<SimVO> handleSearch(FormCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  Collection<SimVO> handleSearch(FormCriteria searchCriteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.sim.SimService.handleSearch(FormCriteria searchCriteria) Not implemented!");
    }

}