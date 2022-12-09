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
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
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
        if(compl.getId() == null) {
            String generatedString = RandomStringUtils.random(15, true, true);
            compl.setComplaintId(generatedString);
        }

        if(compl.getStatus() == null) {
            compl.setStatus(ComplaintStatus.NEW);
        }

        compl = complaintRepository.save(compl);

        return getComplaintDao().toComplaintVO(compl);
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
    protected Collection<ComplaintVO> handleSearch(ComplaintSeachCriteria criteria)
            throws Exception {

        Collection<ComplaintVO> complaints = new ArrayList<>();
        Specification<Complaint> spec = null;

        if (StringUtils.isNotBlank(criteria.getComplaintId())) {
            spec = BocraportalSpecifications.findByAttributeContainingIgnoreCase("complaintId", criteria.getComplaintId());
        }

        if (StringUtils.isNotBlank(criteria.getSubject())) {
            if(spec == null)
                spec = BocraportalSpecifications.findByAttributeContainingIgnoreCase("subject", criteria.getSubject());
            else
                spec = spec.and(BocraportalSpecifications.findByAttributeContainingIgnoreCase("subject", criteria.getSubject()));
        }

        if(criteria.getEmail() != null) {
            if(spec == null)
                spec = BocraportalSpecifications.findByAttribute("email", criteria.getEmail());
            else
                spec = spec.and(BocraportalSpecifications.findByAttribute("email", criteria.getEmail()));
        }

        if(criteria.getStatus() != null) {
            if(spec == null)
                spec = BocraportalSpecifications.findByAttribute("status", criteria.getStatus());
            else
                spec = spec.and(BocraportalSpecifications.findByAttribute("status", criteria.getStatus()));
        }

        if(criteria.getLicenseeId() != null) {
            if(spec == null)
                spec = BocraportalSpecifications.findByJoinAttribute("licensee", "id", criteria.getLicenseeId());
            else
                spec = spec.and(BocraportalSpecifications.findByJoinAttribute("licensee", "id", criteria.getLicenseeId()));
        }


        Collection<Complaint> specs = getComplaintRepository().findAll(spec, Sort.by("id").descending());

        for (Complaint complaint : specs) {
            complaints.add(complaintDao.toComplaintVO(complaint));
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
    protected ComplaintReplyVO handleAddComplaintReply(String complaintId, ComplaintReplyVO reply) throws Exception {

        ComplaintReply cr = complaintReplyDao.complaintReplyVOToEntity(reply);
        cr.setComplaint(this.complaintDao.searchUniqueComplaintId(complaintId));
        
        cr = complaintReplyRepository.save(cr);

        if (reply.getId() != null) {
            return complaintReplyDao.toComplaintReplyVO(cr);
        }

        return reply;
    }

    @Override
    protected Boolean handleRemoveComplaintReply(Long id) throws Exception {
        if (id == null) {
            return false;
        }
        this.complaintReplyRepository.deleteById(id);
        return true;
    }

    @Override
    protected ComplaintVO handleFindByComplaintId(String complaintId) throws Exception {

        if (StringUtils.isNotBlank(complaintId)) {
            Specification<Complaint> spec = BocraportalSpecifications.findByAttribute("complaintId", complaintId);

            List<Complaint> entities = getComplaintRepository().findAll(spec, Sort.by("id").descending());

            if(CollectionUtils.isEmpty(entities)) {
                return null;
            }

            return complaintDao.toComplaintVO(entities.get(0));
        }

        return null;
    }

}