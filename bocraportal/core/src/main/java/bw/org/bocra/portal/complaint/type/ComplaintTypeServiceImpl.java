// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::complaint::type::ComplaintTypeService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.complaint.type;

import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService
 */
@Service("complaintTypeService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class ComplaintTypeServiceImpl
    extends ComplaintTypeServiceBase
{
    public ComplaintTypeServiceImpl(
        ComplaintTypeDao complaintType,
        ComplaintTypeRepository complaintTypeRepository,
        MessageSource messageSource
    ) {
        
        super(
            complaintType,
            complaintTypeRepository,
            messageSource
        );
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#findById(Long)
     */
    @Override
    protected ComplaintTypeVO handleFindById(Long id)
        throws Exception
    {
        // TODO implement protected  ComplaintTypeVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.complaint.type.ComplaintTypeService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#save(ComplaintTypeVO)
     */
    @Override
    protected ComplaintTypeVO handleSave(ComplaintTypeVO complaintType)
        throws Exception
    {
        // TODO implement protected  ComplaintTypeVO handleSave(ComplaintTypeVO complaintType)
        throw new UnsupportedOperationException("bw.org.bocra.portal.complaint.type.ComplaintTypeService.handleSave(ComplaintTypeVO complaintType) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.complaint.type.ComplaintTypeService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#getAll()
     */
    @Override
    protected Collection<ComplaintTypeVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<ComplaintTypeVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.complaint.type.ComplaintTypeService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#search(String)
     */
    @Override
    protected Collection<ComplaintTypeVO> handleSearch(String criteria)
        throws Exception
    {
        // TODO implement protected  Collection<ComplaintTypeVO> handleSearch(String criteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.complaint.type.ComplaintTypeService.handleSearch(String criteria) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.complaint.type.ComplaintTypeService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<ComplaintTypeVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        // TODO implement protected  Collection<ComplaintTypeVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throw new UnsupportedOperationException("bw.org.bocra.portal.complaint.type.ComplaintTypeService.handleGetAll(Integer pageNumber, Integer pageSize) Not implemented!");
    }

}