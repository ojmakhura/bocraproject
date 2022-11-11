// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.complaint;

import bw.org.bocra.portal.document.DocumentRepository;
import bw.org.bocra.portal.licensee.LicenseeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see Complaint
 */
@Repository("complaintDao")
@Transactional
public class ComplaintDaoImpl
    extends ComplaintDaoBase
{
    
    public ComplaintDaoImpl(
        LicenseeRepository licenseeRepository, ComplaintReplyRepository complaintReplyRepository, DocumentRepository documentRepository, ComplaintRepository complaintRepository
    ) {

        super(
            licenseeRepository,
            complaintReplyRepository, documentRepository, complaintRepository
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toComplaintVO(
        Complaint source,
        ComplaintVO target)
    {
        // TODO verify behavior of toComplaintVO
        super.toComplaintVO(source, target);
        // WARNING! No conversion for target.licensee (can't convert source.getLicensee():bw.org.bocra.portal.licensee.Licensee to bw.org.bocra.portal.licensee.LicenseeVO
        this.licenseeDao.get(null)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComplaintVO toComplaintVO(final Complaint entity)
    {
        // TODO verify behavior of toComplaintVO
        return super.toComplaintVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Complaint loadComplaintFromComplaintVO(ComplaintVO complaintVO)
    {
        if (complaintVO.getId() == null)
        {
            return  Complaint.Factory.newInstance();
        }
        else
        {
            return this.load(complaintVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Complaint complaintVOToEntity(ComplaintVO complaintVO)
    {
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
        boolean copyIfNull)
    {
        // TODO verify behavior of complaintVOToEntity
        super.complaintVOToEntity(source, target, copyIfNull);
        if(source.getLicensee() != null) {
            target.setLicensee(getLicenseeDao().load(source.getLicensee().getId()));
        }
    }
}