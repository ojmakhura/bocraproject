// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::complaint::ComplaintService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.complaint;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.BocraportalSpecifications;

/**
 * @see bw.org.bocra.portal.complaint.ComplaintService
 */
@Service("complaintService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ComplaintServiceImpl extends ComplaintServiceBase {

    private final ComplaintReplyRepository complaintReplyRepository;
    private final ComplaintReplyDao complaintReplyDao;

    public ComplaintServiceImpl(ComplaintDao complaintDao, ComplaintRepository complaintRepository,
            MessageSource messageSource, ComplaintReplyRepository complaintReplyRepository,
            ComplaintReplyDao complaintReplyDao) {
        super(complaintDao, complaintRepository, messageSource);
        this.complaintReplyRepository = complaintReplyRepository;
        this.complaintReplyDao = complaintReplyDao;
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

        Collection<ComplaintVO> complaints = new ArrayList<>();

        if (StringUtils.isNotBlank(criteria)) {
            Specification<Complaint> spec = BocraportalSpecifications.findByAttribute("complaintId", criteria);

            Collection<Complaint> specs = getComplaintRepository().findAll(spec, Sort.by("id").descending());

            for (Complaint complaint : specs) {
                complaints.add(complaintDao.toComplaintVO(complaint));
            }
        }

        return complaints;
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

    @Override
    protected ComplaintReplyVO handleAddComplaintReply(Long complaintId, ComplaintReplyVO reply) throws Exception {

        return null;
    }

    @Override
    protected Boolean handleRemoveComplaintReply(Long id) throws Exception {
        this.complaintReplyRepository.deleteById(id);
        return true;
    }

}