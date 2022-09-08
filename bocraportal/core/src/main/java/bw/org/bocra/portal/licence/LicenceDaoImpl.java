// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.licence;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.document.Document;
import bw.org.bocra.portal.document.DocumentRepository;
import bw.org.bocra.portal.document.DocumentVO;
import bw.org.bocra.portal.document.type.DocumentTypeVO;
import bw.org.bocra.portal.licence.type.LicenceType;
import bw.org.bocra.portal.licence.type.LicenceTypeRepository;
import bw.org.bocra.portal.licence.type.LicenceTypeVO;
import bw.org.bocra.portal.licensee.Licensee;
import bw.org.bocra.portal.licensee.LicenseeRepository;
import bw.org.bocra.portal.licensee.LicenseeVO;

/**
 * @see Licence
 */
@Repository("licenceDao")
@Transactional
public class LicenceDaoImpl
    extends LicenceDaoBase
{

    public LicenceDaoImpl(LicenseeRepository licenseeRepository, LicenceTypeRepository licenceTypeRepository,
            DocumentRepository documentRepository, LicenceRepository licenceRepository) {
                
        super(licenseeRepository, licenceTypeRepository, documentRepository, licenceRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toLicenceVO(
        Licence source,
        LicenceVO target)
    {
        // TODO verify behavior of toLicenceVO
        super.toLicenceVO(source, target);
        // WARNING! No conversion for target.licensee (can't convert source.getLicensee():bw.org.bocra.portal.licensee.Licensee to bw.org.bocra.portal.licensee.LicenseeVO
        if(source.getLicensee() != null && source.getLicensee().getId() != null) {
            LicenseeVO licensee = new LicenseeVO();
            licensee.setId(source.getLicensee().getId());
            licensee.setUin(source.getLicensee().getUin());
            licensee.setLicenseeName(source.getLicensee().getLicenseeName());

            target.setLicensee(licensee);
        }

        // WARNING! No conversion for target.documents (can't convert source.getDocuments():bw.org.bocra.portal.document.Document to bw.org.bocra.portal.document.DocumentVO
        if(source.getLicenceType() != null) {
            LicenceTypeVO type = new LicenceTypeVO();
            type.setId(source.getLicenceType().getId());
            type.setCode(source.getLicenceType().getCode());
            type.setName(source.getLicenceType().getName());
            target.setLicenceType(type);
        }

        Collection<DocumentVO> docs = new HashSet<>();

        for(Document doc : source.getDocuments()) {
            System.out.println("source.getDocuments()");
            
            DocumentVO dvo = new DocumentVO();
            dvo.setId(doc.getId());
            dvo.setDocumentName(doc.getDocumentName());
            dvo.setDocumentId(doc.getDocumentId());

            DocumentTypeVO type = new DocumentTypeVO();
            type.setCode(doc.getDocumentType().getCode());
            type.setId(doc.getDocumentType().getId());
            type.setName(doc.getDocumentType().getName());

            dvo.setDocumentType(type);
            docs.add(dvo);
        }

        target.setDocuments(docs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LicenceVO toLicenceVO(final Licence entity)
    {
        // TODO verify behavior of toLicenceVO
        return super.toLicenceVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Licence loadLicenceFromLicenceVO(LicenceVO licenceVO)
    {

        if (licenceVO.getId() == null)
        {
            return  Licence.Factory.newInstance();
        }
        else
        {
            return this.load(licenceVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Licence licenceVOToEntity(LicenceVO licenceVO)
    {
        // TODO verify behavior of licenceVOToEntity
        Licence entity = this.loadLicenceFromLicenceVO(licenceVO);
        this.licenceVOToEntity(licenceVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void licenceVOToEntity(
        LicenceVO source,
        Licence target,
        boolean copyIfNull)
    {
        // TODO verify behavior of licenceVOToEntity
        super.licenceVOToEntity(source, target, copyIfNull);
        // WARNING! No conversion for target.licensee (can't convert source.getLicensee():bw.org.bocra.portal.licensee.Licensee to bw.org.bocra.portal.licensee.LicenseeVO
        if(source.getLicensee() != null && source.getLicensee().getId() != null) {
            Licensee licensee = getLicenseeDao().load(source.getLicensee().getId());
            target.setLicensee(licensee);
        }

        // WARNING! No conversion for target.documents (can't convert source.getDocuments():bw.org.bocra.portal.document.Document to bw.org.bocra.portal.document.DocumentVO
        if(source.getLicenceType() != null) {
            LicenceType type = licenceTypeRepository.getReferenceById(source.getLicenceType().getId());
            target.setLicenceType(type);
        }
    }
}