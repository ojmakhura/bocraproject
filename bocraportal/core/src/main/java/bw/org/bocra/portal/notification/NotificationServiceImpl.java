// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::notification::NotificationService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.notification;

import java.util.Collection;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.notification.NotificationService
 */
@Service("notificationService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class NotificationServiceImpl
    extends NotificationServiceBase
{

    public NotificationServiceImpl(NotificationDao notificationDao, NotificationRepository notificationRepository,
            MessageSource messageSource) {
        super(notificationDao, notificationRepository, messageSource);
    }

    /**
     * @see bw.org.bocra.portal.notification.NotificationService#findById(Long)
     */
    @Override
    protected  NotificationVO handleFindById(Long id)
        throws Exception
    {
        Notification notification = getNotificationDao().load(id);
        return getNotificationDao().toNotificationVO(notification);
    }

    /**
     * @see bw.org.bocra.portal.notification.NotificationService#save(NotificationVO)
     */
    @Override
    protected  NotificationVO handleSave(NotificationVO notification)
        throws Exception
    {
        Notification entity = getNotificationDao().notificationVOToEntity(notification);
        entity = notificationRepository.save(entity);

        return notificationDao.toNotificationVO(entity);

    }

    /**
     * @see bw.org.bocra.portal.notification.NotificationService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        notificationRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.notification.NotificationService#getAll()
     */
    @Override
    protected  Collection<NotificationVO> handleGetAll()
        throws Exception
    {
        
        // TODO implement protected  Collection<NotificationVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.notification.NotificationService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.notification.NotificationService#search(NotificationCriteria)
     */
    @Override
    protected  Collection<NotificationVO> handleSearch(NotificationCriteria criteria)
        throws Exception
    {
        // TODO implement protected  Collection<NotificationVO> handleSearch(NotificationCriteria criteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.notification.NotificationService.handleSearch(NotificationCriteria criteria) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.notification.NotificationService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<NotificationVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        // TODO implement protected  Collection<NotificationVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throw new UnsupportedOperationException("bw.org.bocra.portal.notification.NotificationService.handleGetAll(Integer pageNumber, Integer pageSize) Not implemented!");
    }

}