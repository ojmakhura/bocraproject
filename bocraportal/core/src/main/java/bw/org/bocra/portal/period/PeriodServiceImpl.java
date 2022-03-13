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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.period.PeriodService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
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
        // TODO implement protected  PeriodVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.period.PeriodService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.period.PeriodService#save(PeriodVO)
     */
    @Override
    protected  PeriodVO handleSave(PeriodVO periodVO)
        throws Exception
    {
        // TODO implement protected  PeriodVO handleSave(PeriodVO periodVO)
        throw new UnsupportedOperationException("bw.org.bocra.portal.period.PeriodService.handleSave(PeriodVO periodVO) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.period.PeriodService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.period.PeriodService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.period.PeriodService#getAll()
     */
    @Override
    protected  Collection<PeriodVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<PeriodVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.period.PeriodService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.period.PeriodService#search(PeriodCriteria)
     */
    @Override
    protected  Collection<PeriodVO> handleSearch(PeriodCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  Collection<PeriodVO> handleSearch(PeriodCriteria searchCriteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.period.PeriodService.handleSearch(PeriodCriteria searchCriteria) Not implemented!");
    }

}