// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on 02/11/2022 11:41:52+0200.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.licensee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import bw.org.bocra.portal.document.DocumentDao;
import bw.org.bocra.portal.document.DocumentRepository;
import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.submission.FormSubmissionDao;
import bw.org.bocra.portal.form.submission.FormSubmissionRepository;
import bw.org.bocra.portal.licence.Licence;
import bw.org.bocra.portal.licence.LicenceDao;
import bw.org.bocra.portal.licence.LicenceRepository;
import bw.org.bocra.portal.licence.LicenceVO;
import bw.org.bocra.portal.licensee.form.LicenseeFormRepository;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorRepository;
import bw.org.bocra.portal.licensee.shares.LicenseeShareholderRepository;
import bw.org.bocra.portal.licensee.shares.ShareholderDao;
import bw.org.bocra.portal.licensee.shares.ShareholderRepository;
import bw.org.bocra.portal.notification.NotificationDao;
import bw.org.bocra.portal.notification.NotificationRepository;
import bw.org.bocra.portal.report.ReportDao;
import bw.org.bocra.portal.report.ReportRepository;
import bw.org.bocra.portal.report.config.ReportConfigDao;
import bw.org.bocra.portal.report.config.ReportConfigRepository;
import bw.org.bocra.portal.sector.SectorDao;
import bw.org.bocra.portal.sector.SectorRepository;
import bw.org.bocra.portal.user.LicenseeUserDao;
import bw.org.bocra.portal.user.LicenseeUserRepository;

/**
 * @see Licensee
 */
@Repository("licenseeDao")
@Lazy
public class LicenseeDaoImpl
    extends LicenseeDaoBase
{


    public LicenseeDaoImpl(LicenseeUserRepository licenseeUserRepository,
            FormSubmissionRepository formSubmissionRepository, FormRepository formRepository,
            LicenceRepository licenceRepository, DocumentRepository documentRepository,
            ReportRepository reportRepository, ReportConfigRepository reportConfigRepository,
            SectorRepository sectorRepository, ShareholderRepository shareholderRepository,
            LicenseeShareholderRepository licenseeShareholderRepository,
            LicenseeSectorRepository licenseeSectorRepository, LicenseeFormRepository licenseeFormRepository,
            LicenseeReportConfigRepository licenseeReportConfigRepository,
            NotificationRepository notificationRepository, LicenseeRepository licenseeRepository) {

        super(licenseeUserRepository, formSubmissionRepository, formRepository, licenceRepository, documentRepository,
                reportRepository, reportConfigRepository, sectorRepository, shareholderRepository,
                licenseeShareholderRepository, licenseeSectorRepository, licenseeFormRepository, licenseeReportConfigRepository,
                notificationRepository, licenseeRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toLicenseeVO(
        Licensee source,
        LicenseeVO target)
    {
        super.toLicenseeVO(source, target);

        if(CollectionUtils.isNotEmpty(source.getLicences())) {

            target.setLicences(new ArrayList<>());

            for(Licence entity : source.getLicences()) {
                LicenceVO vo = new LicenceVO();
                licenceDao.toLicenceVO(entity, vo);
                target.getLicences().add(vo);
            }
        }

        // if(CollectionUtils.isNotEmpty(source.getLicenseeSectors())) {

        //     target.setSectors(new ArrayList<>());

        //     for(LicenseeSector entity : source.getLicenseeSectors()) {
        //         LicenseeSectorVO vo = new LicenseeSectorVO();
        //         vo.setLicenseeSectorId(entity.getId());
        //         vo.setId(entity.getSector().getId());
        //         vo.setCode(entity.getSector().getCode());
        //         vo.setName(entity.getSector().getName());
        //         target.getSectors().add(vo);
        //     }
        // }

        // if(CollectionUtils.isNotEmpty(source.getLicenseeForms())) {

        //     target.setSectors(new ArrayList<>());

        //     for(LicenseeForm entity : source.getLicenseeForms()) {
        //         FormVO vo = new FormVO();
        //         formDao.toFormVO(entity.getForm(), vo);
        //         target.getForms().add(vo);
        //     }
        // }

        //source.get


        // TODO: read users from keycloak
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LicenseeVO toLicenseeVO(final Licensee entity)
    {
        // TODO verify behavior of toLicenseeVO
        return super.toLicenseeVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private Licensee loadLicenseeFromLicenseeVO(LicenseeVO licenseeVO)
    {
        if (licenseeVO.getId() == null)
        {
            return  Licensee.Factory.newInstance();
        }
        else
        {
            return this.load(licenseeVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public Licensee licenseeVOToEntity(LicenseeVO licenseeVO)
    {
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
        boolean copyIfNull)
    {
        // TODO verify behavior of licenseeVOToEntity
        super.licenseeVOToEntity(source, target, copyIfNull);

        if(CollectionUtils.isNotEmpty(source.getLicences())) {
            Collection<Licence> types = new ArrayList<>();
            for(LicenceVO licence : source.getLicences()) {
                if(licence.getId() != null) {
                    Licence entity = licenceRepository.getById(licence.getId());
                    types.add(entity);
                }
            }

            target.setLicences(types);

        }

        // if(CollectionUtils.isNotEmpty(source.getSectors()) && source.getId() != null) {
        //     Collection<LicenseeSector> sectors = new ArrayList<>();
        //     for(LicenseeSectorVO sector : source.getSectors()) {
        //         if( sector.getLicenseeSectorId() != null) {
        //             sectors.add(licenseeSectorRepository.getById(sector.getLicenseeSectorId()));
        //         } else if(sector.getId() != null) {

        //             sectors.add(licenseeSectorDao.create(target, sectorDao.load(sector.getId())));
        //         }
        //     }

        //     target.setLicenseeSectors(sectors);
    //     }
    }
}