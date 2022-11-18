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
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        List<Notification> entities = notificationRepository.findAll(Sort.by("notificationDate").descending());
        return notificationDao.toNotificationVOCollection(entities);
    }

    /**
     * @see bw.org.bocra.portal.notification.NotificationService#search(NotificationCriteria)
     */
    @Override
    protected  Collection<NotificationVO> handleSearch(NotificationCriteria criteria)
        throws Exception
    {
        Collection<Notification> entities = getNotificationDao().findByCriteria(criteria);
        return notificationDao.toNotificationVOCollection(entities);
    }

    /**
     * @see bw.org.bocra.portal.notification.NotificationService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<NotificationVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {

        if (pageNumber < 0 || pageSize < 1) {
            return notificationDao.toNotificationVOCollection(notificationRepository.findAll());
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("notificationDate").descending());
            List<Notification> entities = notificationRepository.findAll(pageable).getContent();
            return notificationDao.toNotificationVOCollection(entities);
        }
    }

    @Override
    protected NotificationVO handleLoadUserNotifications(String userId) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}