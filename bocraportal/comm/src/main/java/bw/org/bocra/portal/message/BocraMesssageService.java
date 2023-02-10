// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::message::BocraMesssageService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import bw.org.bocra.portal.properties.RabbitProperties;

/**
 * @see bw.org.bocra.portal.message.BocraMesssageService
 */
@Service("bocraMesssageService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class BocraMesssageService
    extends BocraMesssageServiceBase
{

    private final EntityManager entityManager;
    private final RabbitTemplate rabbitTemplate;
    private final RabbitProperties rabbitProperties;

    public BocraMesssageService(
        BocraMesssageDao bocraMesssage, RabbitTemplate rabbitTemplate,
        BocraMesssageRepository bocraMesssageRepository, RabbitProperties rabbitProperties,
        MessageSource messageSource, EntityManager entityManager
    ) {
        
        super(
            bocraMesssage,
            bocraMesssageRepository,
            messageSource
        );

        this.entityManager = entityManager;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitProperties = rabbitProperties;
    }

    @RabbitListener(queues = "q.email-queue")
    public void readEmailQueue(Collection<BocraMesssageVO> emailMessages) {

        logger.info("Getting {} from email queue.", emailMessages.size());
        
        for (BocraMesssageVO emailMessage : emailMessages) {
            
            if(emailMessage.getId() != null) {
                emailMessage = findById(emailMessage.getId());
            }
            
            emailMessage = save(emailMessage);

            if(emailMessage.getSendNow()) {
                rabbitTemplate.convertAndSend("x.post-email-dispatch", rabbitProperties.getEmailDispatchRoutingKey(), emailMessage);
            }

        }

    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#findById(Long)
     */
    @Override
    protected BocraMesssageVO handleFindById(Long id)
        throws Exception
    {
        BocraMesssage message = bocraMesssageRepository.getReferenceById(id);

        return bocraMesssageDao.toBocraMesssageVO(message);
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#getAll()
     */
    @Override
    protected Collection<BocraMesssageVO> handleGetAll()
        throws Exception
    {
        Collection<BocraMesssage> messages = bocraMesssageRepository.findAll();
        return bocraMesssageDao.toBocraMesssageVOCollection(messages);
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<BocraMesssageVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Collection<BocraMesssage> messages = bocraMesssageRepository.findAll(pageable).getContent();

        return bocraMesssageDao.toBocraMesssageVOCollection(messages);
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        bocraMesssageRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#save(BocraMesssageVO)
     */
    @Override
    protected BocraMesssageVO handleSave(BocraMesssageVO BocraMesssage)
        throws Exception
    {
        BocraMesssage message = bocraMesssageDao.bocraMesssageVOToEntity(BocraMesssage);
        message = bocraMesssageRepository.save(message);

        return bocraMesssageDao.toBocraMesssageVO(message);
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#search(String)
     */
    @Override
    protected Collection<BocraMesssageVO> handleSearch(String criteria)
        throws Exception
    {
        Specification<BocraMesssage> spec = null;

        if(StringUtils.hasText(criteria)) {
            spec = this.findByAttributeLikeIgnoreCase("subject", criteria);
        }

        Collection<BocraMesssage> messages = bocraMesssageRepository.findAll(spec, Sort.by("id").descending());

        return bocraMesssageDao.toBocraMesssageVOCollection(messages);
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#search(Integer, Integer, String)
     */
    @Override
    protected Collection<BocraMesssageVO> handleSearch(Integer pageNumber, Integer pageSize, String criteria)
        throws Exception
    {
        Specification<BocraMesssage> spec = null;

        if(StringUtils.hasText(criteria)) {
            spec = this.findByAttributeLikeIgnoreCase("subject", criteria);
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        Collection<BocraMesssage> messages = bocraMesssageRepository.findAll(spec, pageable).toList();
        
        return bocraMesssageDao.toBocraMesssageVOCollection(messages);
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#loadTodayMessages()
     */
    @Override
    protected Collection<BocraMesssageVO> handleLoadTodayMessages()
        throws Exception
    {
        // TODO implement protected  Collection<BocraMesssageVO> handleLoadTodayMessages()
        throw new UnsupportedOperationException("bw.org.bocra.portal.message.BocraMesssageService.handleLoadTodayMessages() Not implemented!");
    }



    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#clearSentMessages()
     */
    @Override
    protected Integer handleClearSentMessages()
        throws Exception
    {
        String query = "delete from bw.org.bocra.portal.message.BocraMesssage cm where cm.status = :status";
        TypedQuery<BocraMesssage> q = entityManager.createQuery(query, BocraMesssage.class);
        q.setParameter("status", BocraMesssageStatus.SENT);
        return q.executeUpdate();
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#clearFailedMessages()
     */
    @Override
    protected Integer handleClearFailedMessages()
        throws Exception
    {
        String query = "delete from bw.org.bocra.portal.message.BocraMesssage cm where cm.status = :status";
        TypedQuery<BocraMesssage> q = entityManager.createQuery(query, BocraMesssage.class);

        q.setParameter("status", BocraMesssageStatus.FAILED);

        return q.executeUpdate();
    }



    @Override
    protected Collection<BocraMesssageVO> handleLoadDueSubmissionMessages() throws Exception {

        Specification<BocraMesssage> spec = this.<BocraMesssage, LocalDateTime>findByAttributeLessThan("dispatchDate", LocalDate.now().atStartOfDay())
                                    .and(this.<BocraMesssage, BocraMesssageStatus>findByAttribute("status", BocraMesssageStatus.DRAFT));

        Collection<BocraMesssage> messages = bocraMesssageRepository.findAll(spec);

        return bocraMesssageDao.toBocraMesssageVOCollection(messages);
    }

    @Override
    protected Boolean handleUpdateMessageStatus(Long id, BocraMesssageStatus status) throws Exception {
        
        BocraMesssage message = bocraMesssageRepository.getReferenceById(id);
        message.setStatus(status);
        message = bocraMesssageRepository.save(message);

        return true;
    }

    public <E, T extends Comparable<? super T>>Specification<E> findByAttributeLessThan(String attribute, T attributeValue){
        return (root, cq, cb) -> {
            
            return cb.lessThan(root.<T>get(attribute), attributeValue);
        };
    }

    public <E, T>Specification<E> findByAttribute(String attribute, T attributeValue) {
        return (root, cq, cb) -> {
            
            return cb.equal(root.<T>get(attribute), attributeValue);
        };
    }
    
    public <E, T>Specification<E> findByAttributeLikeIgnoreCase(String attribute, String attributeValue){
        return (root, cq, cb) -> {
            
            return cb.like(cb.upper(root.<String>get(attribute)), "%" + attributeValue.toUpperCase() + "%");
        };
    }
}