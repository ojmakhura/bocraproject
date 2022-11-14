// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::complaint::ComplaintService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.complaint;

import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.complaint.ComplaintService
 */
@Service("complaintService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ComplaintServiceImpl
        extends ComplaintServiceBase {
    public ComplaintServiceImpl(ComplaintDao complaintDao, ComplaintRepository complaintRepository,
            MessageSource messageSource) {
        super(complaintDao, complaintRepository, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.complaint.ComplaintService#findById(Long)
     */
    @Override
    protected ComplaintVO handleFindById(Long id)
            throws Exception {
        return complaintDao.toComplaintVO(complaintRepository.getById(id));
    }

    /**
     * @see bw.org.bocra.portal.complaint.ComplaintService#save(ComplaintVO)
     */
    @Override
    protected ComplaintVO handleSave(ComplaintVO complaint)
            throws Exception {
        Complaint compl = getComplaintDao().complaintVOToEntity(complaint);
        compl = complaintRepository.save(compl);

        if (complaint.getId() != null) {
            return getComplaintDao().toComplaintVO(compl);
        }

        return complaint;
    }

    /**
     * @see bw.org.bocra.portal.complaint.ComplaintService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
            throws Exception {
        this.complaintRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.complaint.ComplaintService#getAll()
     */
    @Override
    protected Collection<ComplaintVO> handleGetAll()
            throws Exception {
        return (Collection<ComplaintVO>) getComplaintDao().loadAll(ComplaintDao.TRANSFORM_COMPLAINTVO);
    }

    /**
     * @see bw.org.bocra.portal.complaint.ComplaintService#search(String)
     */
    @Override
    protected Collection<ComplaintVO> handleSearch(String criteria)
            throws Exception {
        // TODO implement protected Collection<ComplaintVO> handleSearch(String
        // criteria)
        throw new UnsupportedOperationException(
                "bw.org.bocra.portal.complaint.ComplaintService.handleSearch(String criteria) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.complaint.ComplaintService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<ComplaintVO> handleGetAll(Integer pageNumber, Integer pageSize)
            throws Exception {
        return (Collection<ComplaintVO>) getComplaintDao().loadAll(ComplaintDao.TRANSFORM_COMPLAINTVO, pageNumber,
                pageSize);
    }

}