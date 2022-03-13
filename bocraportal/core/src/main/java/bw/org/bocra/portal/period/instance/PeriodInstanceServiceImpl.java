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
        // TODO implement protected  PeriodInstanceVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.period.instance.PeriodInstanceService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.period.instance.PeriodInstanceService#save(PeriodInstanceVO)
     */
    @Override
    protected  PeriodInstanceVO handleSave(PeriodInstanceVO periodInstanceVO)
        throws Exception
    {
        // TODO implement protected  PeriodInstanceVO handleSave(PeriodInstanceVO periodInstanceVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.period.instance.PeriodInstanceService.handleSave(PeriodInstanceVO periodInstanceVO) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.period.instance.PeriodInstanceService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.period.instance.PeriodInstanceService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.period.instance.PeriodInstanceService#getAll()
     */
    @Override
    protected  Collection<PeriodInstanceVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<PeriodInstanceVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.period.instance.PeriodInstanceService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.period.instance.PeriodInstanceService#search(PeriodCriteria)
     */
    @Override
    protected  Collection<PeriodInstanceVO> handleSearch(PeriodCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  Collection<PeriodInstanceVO> handleSearch(PeriodCriteria searchCriteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.period.instance.PeriodInstanceService.handleSearch(PeriodCriteria searchCriteria) Not implemented!");
    }

}