// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.report.config;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.form.FormDao;
import bw.org.bocra.portal.form.FormReportConfigDao;
import bw.org.bocra.portal.form.FormReportConfigRepository;
import bw.org.bocra.portal.form.FormRepository;
import bw.org.bocra.portal.licence.type.LicenceTypeDao;
import bw.org.bocra.portal.licence.type.LicenceTypeRepository;
import bw.org.bocra.portal.licensee.LicenseeDao;
import bw.org.bocra.portal.licensee.LicenseeReportConfigDao;
import bw.org.bocra.portal.licensee.LicenseeReportConfigRepository;
import bw.org.bocra.portal.licensee.LicenseeRepository;
import bw.org.bocra.portal.report.ReportDao;
import bw.org.bocra.portal.report.ReportRepository;

/**
 * @see ReportConfig
 */
@Repository("reportConfigDao")
@Transactional
public class ReportConfigDaoImpl
    extends ReportConfigDaoBase
{

    public ReportConfigDaoImpl(LicenseeRepository licenseeRepository, LicenceTypeRepository licenceTypeRepository,
            FormRepository formRepository, ReportRepository reportRepository,
            FormReportConfigRepository formReportConfigRepository,
            ReportConfigLicenceTypeRepository reportConfigLicenceTypeRepository,
            LicenseeReportConfigRepository licenseeReportConfigRepository,
            ReportConfigRepository reportConfigRepository) {

        super(licenseeRepository, licenceTypeRepository, formRepository, reportRepository, formReportConfigRepository,
                reportConfigLicenceTypeRepository, licenseeReportConfigRepository, reportConfigRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toReportConfigVO(
        ReportConfig source,
        ReportConfigVO target)
    {
        // TODO verify behavior of toReportConfigVO
        super.toReportConfigVO(source, target);
        // WARNING! No conversion for target.forms (can't convert source.getForms():bw.org.bocra.portal.form.Form to bw.org.bocra.portal.form.FormVO
        // WARNING! No conversion for target.licensees (can't convert source.getLicensees():bw.org.bocra.portal.licensee.Licensee to bw.org.bocra.portal.licensee.LicenseeVO
        // WARNING! No conversion for target.licenceTypes (can't convert source.getLicenceTypes():bw.org.bocra.portal.licence.type.LicenceType to bw.org.bocra.portal.licence.type.LicenceTypeVO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReportConfigVO toReportConfigVO(final ReportConfig entity)
    {
        // TODO verify behavior of toReportConfigVO
        return super.toReportConfigVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private ReportConfig loadReportConfigFromReportConfigVO(ReportConfigVO reportConfigVO)
    {
        // TODO implement loadReportConfigFromReportConfigVO
        throw new UnsupportedOperationException("bw.org.bocra.portal.report.config.loadReportConfigFromReportConfigVO(ReportConfigVO) not yet implemented.");

        /* A typical implementation looks like this:
        if (reportConfigVO.getId() == null)
        {
            return  ReportConfig.Factory.newInstance();
        }
        else
        {
            return this.load(reportConfigVO.getId());
        }
        */
    }

    /**
     * {@inheritDoc}
     */
    public ReportConfig reportConfigVOToEntity(ReportConfigVO reportConfigVO)
    {
        // TODO verify behavior of reportConfigVOToEntity
        ReportConfig entity = this.loadReportConfigFromReportConfigVO(reportConfigVO);
        this.reportConfigVOToEntity(reportConfigVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportConfigVOToEntity(
        ReportConfigVO source,
        ReportConfig target,
        boolean copyIfNull)
    {
        // TODO verify behavior of reportConfigVOToEntity
        super.reportConfigVOToEntity(source, target, copyIfNull);
    }
}