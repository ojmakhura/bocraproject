// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: AndroMDAModel::backend::bw.org.bocra.portal::licensee::LicenseeService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.licensee;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.document.Document;
import bw.org.bocra.portal.document.DocumentCriteria;
import bw.org.bocra.portal.document.DocumentMetadataTarget;
import bw.org.bocra.portal.document.DocumentService;
import bw.org.bocra.portal.document.DocumentVO;
import bw.org.bocra.portal.document.type.DocumentTypeVO;
import bw.org.bocra.portal.form.Form;
import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.submission.FormSubmission;
import bw.org.bocra.portal.form.submission.FormSubmissionDao;
import bw.org.bocra.portal.form.submission.FormSubmissionRepository;
import bw.org.bocra.portal.form.submission.FormSubmissionVO;
import bw.org.bocra.portal.licence.Licence;
import bw.org.bocra.portal.licence.LicenceDao;
import bw.org.bocra.portal.licence.LicenceRepository;
import bw.org.bocra.portal.licence.LicenceVO;
import bw.org.bocra.portal.licensee.form.LicenseeForm;
import bw.org.bocra.portal.licensee.form.LicenseeFormDao;
import bw.org.bocra.portal.licensee.form.LicenseeFormRepository;
import bw.org.bocra.portal.licensee.form.LicenseeFormVO;
import bw.org.bocra.portal.licensee.sector.LicenseeSector;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorDao;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorRepository;
import bw.org.bocra.portal.licensee.sector.LicenseeSectorVO;
import bw.org.bocra.portal.licensee.shares.LicenseeShareholder;
import bw.org.bocra.portal.licensee.shares.LicenseeShareholderDao;
import bw.org.bocra.portal.licensee.shares.LicenseeShareholderRepository;
import bw.org.bocra.portal.licensee.shares.ShareholderVO;
import bw.org.bocra.portal.report.Report;
import bw.org.bocra.portal.report.ReportVO;
import bw.org.bocra.portal.report.config.ReportConfigVO;
import bw.org.bocra.portal.sector.Sector;
import bw.org.bocra.portal.sector.SectorDao;
import bw.org.bocra.portal.sector.SectorRepository;
import bw.org.bocra.portal.sector.SectorVO;

/**
 * @see bw.org.bocra.portal.licensee.LicenseeService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("licenseeService")
public class LicenseeServiceImpl
    extends LicenseeServiceBase
{
    private final DocumentService documentService;

    public LicenseeServiceImpl(LicenseeDao licenseeDao, LicenseeRepository licenseeRepository,
            LicenseeShareholderDao licenseeShareholderDao, LicenseeShareholderRepository licenseeShareholderRepository,
            LicenseeFormDao licenseeFormDao, LicenseeFormRepository licenseeFormRepository, DocumentService documentService,
            LicenseeSectorDao licenseeSectorDao, LicenseeSectorRepository licenseeSectorRepository,
            LicenceDao licenceDao, LicenceRepository licenceRepository, FormSubmissionDao formSubmissionDao,
            FormSubmissionRepository formSubmissionRepository, SectorDao sectorDao, SectorRepository sectorRepository,
            FormDao formDao, FormRepository formRepository, MessageSource messageSource) {

        super(licenseeDao, licenseeRepository, licenseeShareholderDao, licenseeShareholderRepository, licenseeFormDao,
                licenseeFormRepository, licenseeSectorDao, licenseeSectorRepository, licenceDao, licenceRepository,
                formSubmissionDao, formSubmissionRepository, sectorDao, sectorRepository, formDao, formRepository,
                messageSource);

        this.documentService = documentService;
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#findById(Long)
     */
    @Override
    protected  LicenseeVO handleFindById(Long id)
        throws Exception
    {
        if(id == null) {
            return null;
        }

        Licensee licensee = this.licenseeRepository.getReferenceById(id);

        return licenseeDao.toLicenseeVO(licensee);
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#save(LicenseeVO)
     */
    @Override
    protected  LicenseeVO handleSave(LicenseeVO licenseeVO)
        throws Exception
    {
        Licensee entity = licenseeDao.licenseeVOToEntity(licenseeVO);
        
        if(licenseeVO.getId() == null) {
            entity = licenseeDao.create(entity);
        } else {
            licenseeDao.update(entity);
        }

        return getLicenseeDao().toLicenseeVO(entity);

    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id != null) {
            licenseeRepository.deleteById(id);

            return true;
        }

        return false;
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#getAll()
     */
    @Override
    protected  Collection<LicenseeVO> handleGetAll()
        throws Exception
    {
        return (Collection<LicenseeVO>) licenseeDao.loadAll(LicenseeDao.TRANSFORM_LICENSEEVO);
    }

    /**
     * @see bw.org.bocra.portal.licensee.LicenseeService#search(LicenseeCriteria)
     */
    @Override
    protected  Collection<LicenseeVO> handleSearch(LicenseeCriteria criteria)
        throws Exception
    {

        Collection<Licensee> licenses = this.licenseeDao.findByCriteria(criteria);
        Collection<LicenseeVO> vos = new ArrayList<>();

        for (Licensee licensee : licenses) {
            LicenseeVO vo = new LicenseeVO();
            getLicenseeDao().toLicenseeVO(licensee, vo);
            vos.add(vo);
        }
        return vos;
    }

    @Override
    protected Collection<LicenseeVO> handleGetAll(Integer pageNumber, Integer pageSize) throws Exception {
        Collection<Licensee> licensees = null;

        if(pageNumber < 0 || pageSize < 1) {
            licensees = licenseeRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("uin").descending());
            licensees = licenseeRepository.findAll(pageable).getContent();
        }

        Collection<LicenseeVO> vos = new ArrayList<>();

        for (Licensee licensee : licensees) {
            LicenseeVO vo = new LicenseeVO();
            getLicenseeDao().toLicenseeVO(licensee, vo);
            vos.add(vo);
        }

        return vos;
    }

    @Override
    protected Collection<LicenceVO> handleGetLicences(Long id) throws Exception {
        
        Collection<Licence> licences = licenseeDao.load(id).getLicences();

        Collection<LicenceVO> vos = new ArrayList<>();

        for(Licence licence : licences) {
            LicenceVO vo = new LicenceVO();
            //getLicenceDao().toLicenceVO(licence, vo);
            vo.setId(licence.getId());
            vo.setCreatedBy(licence.getCreatedBy());
            vo.setCreatedDate(licence.getCreatedDate());
            vo.setEndDate(licence.getEndDate());
            vo.setLicenceNumber(licence.getLicenceNumber());
            vo.setProvisional(licence.getProvisional());
            vo.setStartDate(licence.getStartDate());
            vo.setStatus(licence.getStatus());
            vo.setUpdatedBy(licence.getUpdatedBy());
            vo.setUpdatedDate(licence.getUpdatedDate());

            vos.add(vo);
        }

        return vos;
    }

    @Override
    protected Collection<FormSubmissionVO> handleGetFormSubmissions(Long id) throws Exception {
        
        Licensee licensee = licenseeDao.get(id);
        Collection<FormSubmission> submissions = licensee.getFormSubmissions();
        Collection<FormSubmissionVO> vos = new ArrayList<>();

        for (FormSubmission formSubmission : submissions) {
            FormSubmissionVO vo = new FormSubmissionVO();
            vo.setCreatedBy(formSubmission.getCreatedBy());
            vo.setCreatedDate(formSubmission.getCreatedDate());
            vo.setId(formSubmission.getId());
            vo.setSubmissionDate(formSubmission.getSubmissionDate());
            vo.setSubmissionStatus(formSubmission.getSubmissionStatus());
            vo.setUpdatedBy(formSubmission.getUpdatedBy());
            vo.setUpdatedDate(formSubmission.getUpdatedDate());

            vos.add(vo);
        }

        return vos;
    }

    @Override
    protected Collection<DocumentVO> handleGetDocuments(Long id) throws Exception {

        DocumentCriteria criteria = new DocumentCriteria();
        criteria.setMetadataTargetId(id);
        criteria.setMetadataTarget(DocumentMetadataTarget.LICENSEE);
        return documentService.search(criteria);

        // for (Document document : docs) {
        //     DocumentVO vo = new DocumentVO();
        //     vo.setCreatedBy(document.getCreatedBy());
        //     vo.setCreatedDate(document.getCreatedDate());
        //     vo.setDocumentName(document.getDocumentName());
        //     vo.setId(document.getId());
        //     vo.setUpdatedBy(document.getUpdatedBy());
        //     vo.setUpdatedDate(document.getUpdatedDate());

        //     DocumentTypeVO type = new DocumentTypeVO();
        //     type.setCode(document.getDocumentType().getCode());
        //     type.setName(document.getDocumentType().getName());
        //     type.setCreatedBy(document.getDocumentType().getCreatedBy());
        //     type.setCreatedDate(document.getDocumentType().getCreatedDate());
        //     type.setDescription(document.getDocumentType().getDescription());
        //     type.setId(document.getDocumentType().getId());
        //     type.setUpdatedBy(document.getDocumentType().getUpdatedBy());
        //     type.setUpdatedDate(document.getDocumentType().getUpdatedDate());
        //     vo.setDocumentType(type);

        //     vos.add(vo);
        // }

        // return vos;
    }

    @Override
    protected Collection<ReportVO> handleGetReports(Long id) throws Exception {
        Licensee licensee = licenseeDao.get(id);
        Collection<ReportVO> vos = new ArrayList<>();

        for (Report report : licensee.getReports()) {

            ReportVO vo = new ReportVO();
            vo.setCreatedBy(report.getCreatedBy());
            vo.setCreatedDate(report.getCreatedDate());
            vo.setId(report.getId());
            vo.setUpdatedBy(report.getUpdatedBy());
            vo.setUpdatedDate(report.getUpdatedDate());

            vos.add(vo);
        }
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Collection<ShareholderVO> handleGetShareholders(Long id) throws Exception {
        Licensee licensee = licenseeDao.get(id);
        Collection<ShareholderVO> holders = new ArrayList<>();
        
        for(LicenseeShareholder holder : licensee.getLicenseeShareholders()) {
            ShareholderVO vo = new ShareholderVO();
            vo.setAddress(holder.getShareholder().getAddress());
            vo.setCreatedBy(holder.getShareholder().getCreatedBy());
            vo.setCreatedDate(holder.getShareholder().getCreatedDate());
            vo.setId(holder.getShareholder().getId());
            vo.setName(holder.getShareholder().getName());
            vo.setNumberOfShares(holder.getShareholder().getNumberOfShares());
            vo.setPercentageShares(holder.getShareholder().getPercentageShares());
            vo.setType(holder.getShareholder().getType());
            vo.setUpdatedBy(holder.getShareholder().getUpdatedBy());
            vo.setUpdatedDate(holder.getShareholder().getUpdatedDate());

            holders.add(vo);
        }

        return holders;
    }

    @Override
    protected Collection<SectorVO> handleGetSectors(Long id) throws Exception {
        Licensee licensee = licenseeDao.get(id);
        Collection<SectorVO> vos = new ArrayList<>();

        for(LicenseeSector sector : licensee.getLicenseeSectors()) {
            SectorVO vo = new SectorVO();

            vo.setCode(sector.getSector().getCode());
            vo.setCreatedBy(sector.getSector().getCreatedBy());
            vo.setCreatedDate(sector.getSector().getCreatedDate());
            vo.setDescription(sector.getSector().getDescription());
            vo.setId(sector.getSector().getId());
            vo.setName(sector.getSector().getName());
            vo.setUpdatedBy(sector.getSector().getUpdatedBy());
            vo.setUpdatedDate(sector.getSector().getUpdatedDate());

            vos.add(vo);
        }

        return vos;
    }

    @Override
    protected Collection<FormVO> handleGetForms(Long id) throws Exception {
        Licensee licensee = licenseeDao.get(id);
        Collection<FormVO> vos = new ArrayList<>();

        for(LicenseeForm form : licensee.getLicenseeForms()) {
            FormVO vo = new FormVO();
            vo.setCode(form.getForm().getCode());
            vo.setCreatedBy(form.getForm().getCreatedBy());
            vo.setCreatedDate(form.getForm().getCreatedDate());
            vo.setEntryType(form.getForm().getEntryType());
            vo.setFormName(form.getForm().getFormName());
            vo.setId(form.getForm().getId());
            vo.setUpdatedBy(form.getForm().getUpdatedBy());
            vo.setUpdatedDate(form.getForm().getUpdatedDate());

            vos.add(vo);
        }
        return vos;
    }

    @Override
    protected Collection<ReportConfigVO> handleGetReportConfigurations(Long id) throws Exception {
        Licensee licensee = licenseeDao.get(id);
        Collection<ReportConfigVO> vos = new ArrayList<>();

        for(LicenseeReportConfig config : licensee.getLicenseeReportConfigs()) {
            ReportConfigVO vo = new ReportConfigVO();
            vo.setCode(config.getReportConfig().getCode());
            vo.setCreatedBy(config.getReportConfig().getCreatedBy());
            vo.setCreatedDate(config.getReportConfig().getCreatedDate());
            vo.setDescription(config.getReportConfig().getDescription());
            vo.setId(config.getReportConfig().getId());
            vo.setName(config.getReportConfig().getName());
            vo.setUpdatedBy(config.getReportConfig().getUpdatedBy());
            vo.setUpdatedDate(config.getReportConfig().getUpdatedDate());

            vos.add(vo);
        }
        
        return vos;
    }

    @Override
    protected LicenseeSectorVO handleAddSector(Long licenseeId, Long sectorId) throws Exception {
        Sector sector = getSectorDao().load(sectorId);
        Licensee licensee = getLicenseeDao().load(licenseeId);
        LicenseeSector licenseeSector = getLicenseeSectorDao().create(licensee, sector);
        return getLicenseeSectorDao().toLicenseeSectorVO(licenseeSector);
    }

    @Override
    protected Boolean handleRemoveSector(Long licenseeSectorId) throws Exception {

        getLicenseeSectorDao().remove(licenseeSectorId);
        return true;
    }

    @Override
    protected LicenseeFormVO handleAddForm(Long licenseeId, Long formId) throws Exception {
        Licensee licensee = getLicenseeDao().load(licenseeId);
        Form form = getFormDao().load(formId);
        LicenseeForm lf = LicenseeForm.Factory.newInstance();
        lf.setLicensee(licensee);
        lf = getLicenseeFormDao().create(form, licensee);

        return getLicenseeFormDao().toLicenseeFormVO(lf);
    }

    @Override
    protected Boolean handleRemoveForm(Long licenseeFormId) throws Exception {
        getLicenseeFormDao().remove(licenseeFormId);
        return true;
    }

    @Override
    protected LicenseeFormVO handleUpdateForm(Long licenseeFormId, Long formId) throws Exception {
        Form form = getFormDao().load(formId);
        LicenseeForm lf = getLicenseeFormDao().load(licenseeFormId);
        lf.setForm(form);
        getLicenseeFormDao().update(lf);

        return getLicenseeFormDao().toLicenseeFormVO(lf);
    }

    @Override
    protected LicenseeSectorVO handleUpdateSector(Long licenseeSectorId, Long sectorId)
            throws Exception {
        
        Sector sector = getSectorDao().get(sectorId);
        LicenseeSector ls = getLicenseeSectorDao().load(licenseeSectorId);
        ls.setSector(sector);

        getLicenseeSectorDao().update(ls);

        // TODO Auto-generated method stub
        return null;
    }

}