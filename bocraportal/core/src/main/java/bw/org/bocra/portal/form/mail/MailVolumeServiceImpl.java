// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::form::mail::MailVolumeService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.form.mail;

import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.form.mail.MailVolumeService
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
@Service("mailVolumeService")
public class MailVolumeServiceImpl
    extends MailVolumeServiceBase
{

    /**
     * @see bw.org.bocra.portal.form.mail.MailVolumeService#findById(Long)
     */
    @Override
    protected  MailVolumeVO handleFindById(Long id)
        throws Exception
    {

        if(id == null) {
            return null;
        }

        return (MailVolumeVO) getMailVolumeDao().get(MailVolumeDao.TRANSFORM_MAILVOLUMEVO, id);

    }

    /**
     * @see bw.org.bocra.portal.form.mail.MailVolumeService#save(MailVolumeVO)
     */
    @Override
    protected  MailVolumeVO handleSave(MailVolumeVO mailVolumeVO)
        throws Exception
    {
        MailVolume volume = getMailVolumeDao().mailVolumeVOToEntity(mailVolumeVO);

        if(mailVolumeVO.getId() == null) {

            volume = getMailVolumeDao().create(volume);

        } else {
            getMailVolumeDao().update(volume);
        }
        return getMailVolumeDao().toMailVolumeVO(volume);
    }

    /**
     * @see bw.org.bocra.portal.form.mail.MailVolumeService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.mail.MailVolumeService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.mail.MailVolumeService#getAll()
     */
    @Override
    protected  Collection<MailVolumeVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<MailVolumeVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.mail.MailVolumeService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.form.mail.MailVolumeService#search(MailVolumeCriteria)
     */
    @Override
    protected  Collection<MailVolumeVO> handleSearch(MailVolumeCriteria searchCriteria)
        throws Exception
    {
        // TODO implement protected  Collection<MailVolumeVO> handleSearch(MailVolumeCriteria searchCriteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.form.mail.MailVolumeService.handleSearch(MailVolumeCriteria searchCriteria) Not implemented!");
    }

}