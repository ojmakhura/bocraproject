// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on 02/11/2022 11:41:52+0200.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.licensee;

import bw.org.bocra.portal.shareholder.ShareholderVO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.complaint.ComplaintRepository;
import bw.org.bocra.portal.document.DocumentRepository;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.submission.FormSubmissionRepository;
import bw.org.bocra.portal.licence.Licence;
import bw.org.bocra.portal.licence.LicenceRepository;
import bw.org.bocra.portal.licence.LicenceVO;
import bw.org.bocra.portal.licensee.form.LicenseeForm;
import bw.org.bocra.portal.licensee.form.LicenseeFormRepository;
import bw.org.bocra.portal.licensee.form.LicenseeFormVO;
import bw.org.bocra.portal.licensee.sector.LicenseeSector;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorRepository;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorVO;
import bw.org.bocra.portal.licensee.shares.LicenseeShareholderRepository;
import bw.org.bocra.portal.licensee.shares.LicenseeShareholderVO;
import bw.org.bocra.portal.licensee.shares.LicenseeShareholder;
import bw.org.bocra.portal.report.ReportRepository;
import bw.org.bocra.portal.report.config.ReportConfigRepository;
import bw.org.bocra.portal.sector.SectorRepository;
import bw.org.bocra.portal.user.LicenseeUserRepository;

/**
 * @see Licensee
 */
@Repository("licenseeDao")
@Lazy
@Transactional
public class LicenseeDaoImpl
        extends LicenseeDaoBase {

    public LicenseeDaoImpl(LicenseeUserRepository licenseeUserRepository,
            FormSubmissionRepository formSubmissionRepository, FormRepository formRepository,
            LicenceRepository licenceRepository, DocumentRepository documentRepository,
            ReportRepository reportRepository, ReportConfigRepository reportConfigRepository,
            SectorRepository sectorRepository, LicenseeShareholderRepository licenseeShareholderRepository,
            LicenseeSectorRepository licenseeSectorRepository, LicenseeFormRepository licenseeFormRepository,
            LicenseeReportConfigRepository licenseeReportConfigRepository, ComplaintRepository complaintRepository,
            LicenseeRepository licenseeRepository) {
        super(licenseeUserRepository, formSubmissionRepository, formRepository, licenceRepository, documentRepository,
                reportRepository, reportConfigRepository, sectorRepository, licenseeShareholderRepository,
                licenseeSectorRepository, licenseeFormRepository, licenseeReportConfigRepository, complaintRepository,
                licenseeRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toLicenseeVO(
            Licensee source,
            LicenseeVO target) {
        super.toLicenseeVO(source, target);

        if (CollectionUtils.isNotEmpty(source.getLicences())) {

            target.setLicences(new ArrayList<>());

            for (Licence entity : source.getLicences()) {
                LicenceVO vo = new LicenceVO();

                licenceDao.toLicenceVO(entity, vo);
                target.getLicences().add(vo);
            }
        }

        if (CollectionUtils.isNotEmpty(source.getLicenseeSectors())) {

            target.setSectors(new ArrayList<>());

            for (LicenseeSector entity : source.getLicenseeSectors()) {
                LicenseeSectorVO vo = getLicenseeSectorDao().toLicenseeSectorVO(entity);
                target.getSectors().add(vo);
            }
        }

        if (CollectionUtils.isNotEmpty(source.getDocumentIds())) {
            target.setDocuments(
                    documentDao.toDocumentVOCollection(documentRepository.findByDocumentIdIn(source.getDocumentIds())));
        }

        target.setForms(new ArrayList<>());
        for (LicenseeForm form : source.getLicenseeForms()) {
            LicenseeFormVO lf = new LicenseeFormVO();
            lf.setId(form.getId());

            lf.setForm(new FormVO());
            lf.getForm().setId(form.getForm().getId());
            lf.getForm().setCode(form.getForm().getCode());
            lf.getForm().setEntryType(form.getForm().getEntryType());
            lf.getForm().setFormName(form.getForm().getFormName());

            target.getForms().add(lf);
        }

        target.setShareholders(new ArrayList<>());
        for (LicenseeShareholder holder : source.getLicenseeShareholders()) {
            LicenseeShareholderVO ls = new LicenseeShareholderVO();
            ls.setId(holder.getId());

            ls.setShareholder(new ShareholderVO());
            ls.getShareholder().setId(holder.getShareholder().getId());
            ls.getShareholder().setShareholderId(holder.getShareholder().getShareholderId());
            ls.getShareholder().setType(holder.getShareholder().getType());
            ls.getShareholder().setName(holder.getShareholder().getName());
            ls.setNumberOfShares(holder.getNumberOfShares());

            target.getShareholders().add(ls);
        }

        // TODO: read users from keycloak
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LicenseeVO toLicenseeVO(final Licensee entity) {
        // TODO verify behavior of toLicenseeVO
        return super.toLicenseeVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value
     * object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Licensee loadLicenseeFromLicenseeVO(LicenseeVO licenseeVO) {
        if (licenseeVO.getId() == null) {
            return Licensee.Factory.newInstance();
        } else {
            return this.load(licenseeVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Licensee licenseeVOToEntity(LicenseeVO licenseeVO) {
        // TODO verify behavior of licenseeVOToEntity
        Licensee entity = this.loadLicenseeFromLicenseeVO(licenseeVO);
        this.licenseeVOToEntity(licenseeVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void licenseeVOToEntity(
            LicenseeVO source,
            Licensee target,
            boolean copyIfNull) {
        // TODO verify behavior of licenseeVOToEntity
        super.licenseeVOToEntity(source, target, copyIfNull);

        if (CollectionUtils.isNotEmpty(source.getLicences())) {
            Collection<Licence> types = new ArrayList<>();
            for (LicenceVO licence : source.getLicences()) {
                if (licence.getId() != null) {
                    Licence entity = licenceRepository.getReferenceById(licence.getId());
                    types.add(entity);
                }
            }

            target.setLicences(types);

        }

        if (CollectionUtils.isNotEmpty(source.getDocuments())) {
            target.setDocumentIds(
                    source.getDocuments().stream().map(doc -> doc.getDocumentId()).collect(Collectors.toList()));
        }

        // if(CollectionUtils.isNotEmpty(source.getSectors()) && source.getId() != null)
        // {
        // Collection<LicenseeSector> sectors = new ArrayList<>();
        // for(LicenseeSectorVO sector : source.getSectors()) {
        // if( sector.getLicenseeSectorId() != null) {
        // sectors.add(licenseeSectorRepository.getReferenceById(sector.getLicenseeSectorId()));
        // } else if(sector.getId() != null) {

        // sectors.add(licenseeSectorDao.create(target,
        // sectorDao.load(sector.getId())));
        // }
        // }

        // target.setLicenseeSectors(sectors);
        // }
    }
}