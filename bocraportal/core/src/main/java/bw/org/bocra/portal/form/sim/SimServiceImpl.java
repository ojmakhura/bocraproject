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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        if(id == null) {
            return null;
        }

        return getSimDao().toSimVO(getSimDao().get(id));
    }

    /**
     * @see bw.org.bocra.portal.form.sim.SimService#save(SimVO)
     */
    @Override
    protected  SimVO handleSave(SimVO simVO)
        throws Exception
    {

        Sim sim = getSimDao().simVOToEntity(simVO);

        if(simVO.getId() == null) {
            sim = simDao.create(sim);
        } else {
            simDao.update(sim);
        }

        return getSimDao().toSimVO(sim);
    }

    /**
     * @see bw.org.bocra.portal.form.sim.SimService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {

        if(id == null) {
            return false;
        }

        simRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.form.sim.SimService#getAll()
     */
    @Override
    protected  Collection<SimVO> handleGetAll()
        throws Exception
    {
        return simDao.toSimVOCollection(simRepository.findAll(Sort.by("createdBy").descending()));
    }

    /**
     * @see bw.org.bocra.portal.form.sim.SimService#search(FormCriteria)
     */
    @Override
    protected Collection<SimVO> handleSearch(FormCriteria searchCriteria, Integer pageNumber, Integer pageSize)
            throws Exception {

        Collection<Sim> sims = null;

        if(pageNumber < 0 || pageSize < 1) {
            sims = simRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("createdBy").descending());
            sims = simRepository.findAll(pageable).getContent();
        }
        
        return sims == null ? null : getSimDao().toSimVOCollection(sims);
    }

    @Override
    protected Collection<SimVO> handleGetAll(Integer pageNumber, Integer pageSize) throws Exception {
        return getSimDao().toSimVOCollection(simRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("createdBy").descending())).getContent());
    }

}