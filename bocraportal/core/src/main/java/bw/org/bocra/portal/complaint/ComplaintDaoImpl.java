// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.complaint;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.BocraportalSpecifications;
import bw.org.bocra.portal.complaint.type.ComplaintTypeRepository;
import bw.org.bocra.portal.document.Document;
import bw.org.bocra.portal.document.DocumentMetadataTarget;
import bw.org.bocra.portal.document.DocumentRepository;
import bw.org.bocra.portal.document.DocumentVO;
import bw.org.bocra.portal.licensee.LicenseeRepository;
import bw.org.bocra.portal.licensee.LicenseeVO;
import bw.org.bocra.portal.licensee.sector.LicenseeSector;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorVO;
import bw.org.bocra.portal.sector.SectorVO;

/**
 * @see Complaint
 */
@Repository("complaintDao")
@Transactional
public class ComplaintDaoImpl
        extends ComplaintDaoBase {

    private final ComplaintReplyDao complaintReplyDao;

    public ComplaintDaoImpl(LicenseeRepository licenseeRepository, ComplaintReplyRepository complaintReplyRepository,
            DocumentRepository documentRepository, ComplaintTypeRepository complaintTypeRepository,
            ComplaintRepository complaintRepository, ComplaintReplyDao complaintReplyDao) {
        super(licenseeRepository, complaintReplyRepository, documentRepository, complaintTypeRepository,
                complaintRepository);
        // TODO Auto-generated constructor stub
        this.complaintReplyDao = complaintReplyDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toComplaintVO(
            Complaint source,
            ComplaintVO target) {
        // TODO verify behavior of toComplaintVO
        super.toComplaintVO(source, target);
        // WARNING! No conversion for target.licensee (can't convert
        // source.getLicensee():bw.org.bocra.portal.licensee.Licensee to
        // bw.org.bocra.portal.licensee.LicenseeVO

        if (source.getLicensee() != null && source.getLicensee().getId() != null) {
            LicenseeVO licensee = new LicenseeVO();
            licensee.setId(source.getLicensee().getId());
            licensee.setUin(source.getLicensee().getUin());
            licensee.setAlias(source.getLicensee().getAlias());
            licensee.setLicenseeName(source.getLicensee().getLicenseeName());

            licensee.setSectors(new HashSet<>());

            for(LicenseeSector sector : source.getLicensee().getLicenseeSectors()) {
                LicenseeSectorVO vo = new LicenseeSectorVO();
                vo.setId(sector.getId());
                vo.setSector(new SectorVO());
                vo.getSector().setId(sector.getSector().getId());
                vo.getSector().setCode(sector.getSector().getCode());
                vo.getSector().setName(sector.getSector().getName());
                licensee.getSectors().add(vo);
            }

            target.setLicensee(licensee);
        }

        if (CollectionUtils.isNotEmpty(source.getComplaintReplies())) {
            Collection<ComplaintReplyVO> replies = complaintReplyDao
                    .toComplaintReplyVOCollection(source.getComplaintReplies());
            target.setComplaintReplies(replies);
        }

        if (source.getComplaintType() != null) {
            target.setComplaintType(getComplaintTypeDao().toComplaintTypeVO(source.getComplaintType()));
        }

        Specification<Document> specs = BocraportalSpecifications.<Document, DocumentMetadataTarget>findByAttribute("metadataTarget", DocumentMetadataTarget.COMPLAINT)
                                            .and(BocraportalSpecifications.<Document, Long>findByAttribute("metadataTargetId", source.getId()));

        Collection<Document> entities = documentRepository.findAll(specs, Sort.by("id").ascending());
        if(CollectionUtils.isNotEmpty(entities)) {
            Collection<DocumentVO> docs = entities.stream().map(d -> {
                DocumentVO dv = new DocumentVO();
                dv.setId(d.getId());
                dv.setContentType(d.getContentType());
                dv.setDocumentId(d.getDocumentId());
                dv.setDocumentName(d.getDocumentName());
                dv.setExtension(d.getExtension());
                dv.setMetadataTargetId(d.getMetadataTargetId());
                dv.setSize(d.getSize());
                return dv;
            }).collect(Collectors.toSet());
            target.setDocuments(docs);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComplaintVO toComplaintVO(final Complaint entity) {
        // TODO verify behavior of toComplaintVO
        return super.toComplaintVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Complaint loadComplaintFromComplaintVO(ComplaintVO complaintVO) {
        if (complaintVO.getId() == null) {
            return Complaint.Factory.newInstance();
        } else {
            return this.load(complaintVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Complaint complaintVOToEntity(ComplaintVO complaintVO) {
        // TODO verify behavior of complaintVOToEntity
        Complaint entity = this.loadComplaintFromComplaintVO(complaintVO);
        this.complaintVOToEntity(complaintVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void complaintVOToEntity(
            ComplaintVO source,
            Complaint target,
            boolean copyIfNull) {
        // TODO verify behavior of complaintVOToEntity
        super.complaintVOToEntity(source, target, copyIfNull);

        if (source.getLicensee() != null) {
            target.setLicensee(getLicenseeDao().load(source.getLicensee().getId()));
        } else {
            throw new IllegalArgumentException(
                    "ComplaintDao.complaintVOToEntity - 'licensee' or its id can not be null");
        }

        if (source.getComplaintType() != null && StringUtils.isNotBlank(source.getComplaintType().getCode())) {
            target.setComplaintType(getComplaintTypeDao().complaintTypeVOToEntity(source.getComplaintType()));
        } else {
            throw new IllegalArgumentException(
                    "ComplaintDao.complaintVOToEntity - 'complaintType' or its id can not be null");
        }
    }
}