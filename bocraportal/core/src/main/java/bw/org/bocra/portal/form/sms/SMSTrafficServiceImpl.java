// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::sms::SMSTrafficService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.sms;

import bw.org.bocra.portal.form.FormCriteria;
import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * @see bw.org.bocra.portal.form.sms.SMSTrafficService
 */
@Service("sMSTrafficService")
public class SMSTrafficServiceImpl
    extends SMSTrafficServiceBase
{

    /**
     * @see bw.org.bocra.portal.form.sms.SMSTrafficService#findById(Long)
     */
    @Override
    protected  SMSTrafficVO handleFindById(Long id)
        throws Exception
    {

        if(id == null) {
            return null;
        }

        return getSMSTrafficDao().toSMSTrafficVO(getSMSTrafficDao().get(id));
    }

    /**
     * @see bw.org.bocra.portal.form.sms.SMSTrafficService#save(SMSTrafficVO)
     */
    @Override
    protected  SMSTrafficVO handleSave(SMSTrafficVO smsTrafficVO)
        throws Exception
    {
        SMSTraffic traffic = getSMSTrafficDao().sMSTrafficVOToEntity(smsTrafficVO);

        if(smsTrafficVO.getId() == null) {
            traffic = getSMSTrafficDao().create(traffic);

        } else {
            sMSTrafficDao.update(traffic);
        }

        return getSMSTrafficDao().toSMSTrafficVO(traffic);

    }

    /**
     * @see bw.org.bocra.portal.form.sms.SMSTrafficService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id == null) {
            return false;
        }

        sMSTrafficDao.remove(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.form.sms.SMSTrafficService#getAll()
     */
    @Override
    protected  Collection<SMSTrafficVO> handleGetAll()
        throws Exception
    {
        return (Collection<SMSTrafficVO>) sMSTrafficDao.loadAll(SMSTrafficDao.TRANSFORM_SMSTRAFFICVO);
    }

    /**
     * @see bw.org.bocra.portal.form.sms.SMSTrafficService#search(FormCriteria)
     */
    @Override
    protected  Collection<SMSTrafficVO> handleSearch(FormCriteria searchCriteria)
        throws Exception
    {
        Collection<SMSTraffic> entities = getSMSTrafficDao().findByCriteria(searchCriteria);
        return getSMSTrafficDao().toSMSTrafficVOCollection(entities);
    }

    /**
     * @see bw.org.bocra.portal.form.sms.SMSTrafficService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<SMSTrafficVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        return (Collection<SMSTrafficVO>) getSMSTrafficDao().loadAll(SMSTrafficDao.TRANSFORM_SMSTRAFFICVO, pageNumber, pageSize);
    }

}