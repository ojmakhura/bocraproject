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

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.document.Document;
import bw.org.bocra.portal.document.DocumentVO;
import bw.org.bocra.portal.document.type.DocumentTypeVO;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.form.submission.FormSubmission;
import bw.org.bocra.portal.form.submission.FormSubmissionVO;
import bw.org.bocra.portal.licence.Licence;
import bw.org.bocra.portal.licence.LicenceSpecifications;
import bw.org.bocra.portal.licence.LicenceVO;
import bw.org.bocra.portal.licensee.shares.ShareholderVO;
import bw.org.bocra.portal.report.Report;
import bw.org.bocra.portal.report.ReportVO;
import bw.org.bocra.portal.report.config.ReportConfigVO;
import bw.org.bocra.portal.sector.Sector;
import bw.org.bocra.portal.sector.SectorVO;

/**
 * @see bw.org.bocra.portal.licensee.LicenseeService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("licenseeService")
public class LicenseeServiceImpl
    extends LicenseeServiceBase
{

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

        Licensee licensee = this.licenseeRepository.getById(id);

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

        Licensee licensee = licenseeDao.get(id);

        Collection<Document> docs = licensee.getDocuments();
        Collection<DocumentVO> vos = new ArrayList<>();

        for (Document document : docs) {
            DocumentVO vo = new DocumentVO();
            vo.setCreatedBy(document.getCreatedBy());
            vo.setCreatedDate(document.getCreatedDate());
            vo.setDocumentName(document.getDocumentName());
            vo.setId(document.getId());
            vo.setUpdatedBy(document.getUpdatedBy());
            vo.setUpdatedDate(document.getUpdatedDate());

            DocumentTypeVO type = new DocumentTypeVO();
            type.setCode(document.getDocumentType().getCode());
            type.setName(document.getDocumentType().getName());
            type.setCreatedBy(document.getDocumentType().getCreatedBy());
            type.setCreatedDate(document.getDocumentType().getCreatedDate());
            type.setDescription(document.getDocumentType().getDescription());
            type.setId(document.getDocumentType().getId());
            type.setUpdatedBy(document.getDocumentType().getUpdatedBy());
            type.setUpdatedDate(document.getDocumentType().getUpdatedDate());
            vo.setDocumentType(type);

            vos.add(vo);
        }

        return vos;
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
        LicenseeSectorVO lVo = new LicenseeSectorVO();
        lVo.setLicenseeSectorId(licenseeSector.getId());
        lVo.setCode(sector.getCode());
        lVo.setId(sector.getId());
        lVo.setName(sector.getName());

        return lVo;
    }

}