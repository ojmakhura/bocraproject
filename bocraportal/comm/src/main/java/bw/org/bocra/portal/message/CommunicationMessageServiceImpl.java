// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::message::CommunicationMessageService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.message;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bw.org.bocra.portal.BocraportalSpecifications;
import bw.org.bocra.portal.form.submission.FormSubmission;
import bw.org.bocra.portal.form.submission.FormSubmissionCriteria;
import bw.org.bocra.portal.form.submission.FormSubmissionDao;

/**
 * @see bw.org.bocra.portal.message.CommunicationMessageService
 */
@Service("communicationMessageService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class CommunicationMessageServiceImpl
    extends CommunicationMessageServiceBase
{

    private final FormSubmissionDao formSubmissionDao;
    private final EntityManager entityManager;

    public CommunicationMessageServiceImpl(
        CommunicationMessageDao communicationMessage, FormSubmissionDao formSubmissionDao,
        CommunicationMessageRepository communicationMessageRepository,
        MessageSource messageSource, EntityManager entityManager
    ) {
        
        super(
            communicationMessage,
            communicationMessageRepository,
            messageSource
        );

        this.formSubmissionDao = formSubmissionDao;
        this.entityManager = entityManager;
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#findById(Long)
     */
    @Override
    protected CommunicationMessageVO handleFindById(Long id)
        throws Exception
    {
        CommunicationMessage message = communicationMessageDao.load(id);

        return communicationMessageDao.toCommunicationMessageVO(message);
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#getAll()
     */
    @Override
    protected Collection<CommunicationMessageVO> handleGetAll()
        throws Exception
    {
        return (Collection<CommunicationMessageVO>) communicationMessageDao.loadAll(CommunicationMessageDao.TRANSFORM_COMMUNICATIONMESSAGEVO);
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<CommunicationMessageVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        return (Collection<CommunicationMessageVO>) communicationMessageDao.loadAll(pageNumber, pageSize, CommunicationMessageDao.TRANSFORM_COMMUNICATIONMESSAGEVO);
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        communicationMessageRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#save(CommunicationMessageVO)
     */
    @Override
    protected CommunicationMessageVO handleSave(CommunicationMessageVO communicationMessage)
        throws Exception
    {
        CommunicationMessage message = communicationMessageDao.communicationMessageVOToEntity(communicationMessage);
        message = communicationMessageDao.createOrUpdate(message);

        return communicationMessageDao.toCommunicationMessageVO(message);
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#search(String)
     */
    @Override
    protected Collection<CommunicationMessageVO> handleSearch(String criteria)
        throws Exception
    {
        Specification<CommunicationMessage> spec = null;

        if(StringUtils.isNotBlank(criteria)) {
            spec = BocraportalSpecifications.findByAttributeLikeIgnoreCase("subject", criteria);
        }

        Collection<CommunicationMessage> messages = communicationMessageRepository.findAll(spec, Sort.by("id").descending());

        return communicationMessageDao.toCommunicationMessageVOCollection(messages);
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#search(Integer, Integer, String)
     */
    @Override
    protected Collection<CommunicationMessageVO> handleSearch(Integer pageNumber, Integer pageSize, String criteria)
        throws Exception
    {
        Specification<CommunicationMessage> spec = null;

        if(StringUtils.isNotBlank(criteria)) {
            spec = BocraportalSpecifications.findByAttributeLikeIgnoreCase("subject", criteria);
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        Collection<CommunicationMessage> messages = communicationMessageRepository.findAll(spec, pageable).toList();
        
        return communicationMessageDao.toCommunicationMessageVOCollection(messages);
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#loadTodayMessages()
     */
    @Override
    protected Collection<CommunicationMessageVO> handleLoadTodayMessages()
        throws Exception
    {
        // TODO implement protected  Collection<CommunicationMessageVO> handleLoadTodayMessages()
        throw new UnsupportedOperationException("bw.org.bocra.portal.message.CommunicationMessageService.handleLoadTodayMessages() Not implemented!");
    }



    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#clearSentMessages()
     */
    @Override
    protected Integer handleClearSentMessages()
        throws Exception
    {
        String query = "delete from bw.org.bocra.portal.message.CommunicationMessage cm where cm.status = :status";
        TypedQuery<CommunicationMessage> q = entityManager.createQuery(query, CommunicationMessage.class);
        q.setParameter("status", CommunicationMessageStatus.SENT);
        return q.executeUpdate();
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#clearFailedMessages()
     */
    @Override
    protected Integer handleClearFailedMessages()
        throws Exception
    {
        String query = "delete from bw.org.bocra.portal.message.CommunicationMessage cm where cm.status = :status";
        TypedQuery<CommunicationMessage> q = entityManager.createQuery(query, CommunicationMessage.class);

        q.setParameter("status", CommunicationMessageStatus.FAILED);

        return q.executeUpdate();
    }

    @Override
    protected Collection<CommunicationMessageVO> handleLoadDueSubmissionMessages() throws Exception {

        Specification<CommunicationMessage> spec = BocraportalSpecifications.<CommunicationMessage, LocalDateTime>findByAttributeLessThan("dispatchDate", LocalDate.now().atStartOfDay())
                                    .and(BocraportalSpecifications.<CommunicationMessage, CommunicationMessageStatus>findByAttribute("status", CommunicationMessageStatus.DRAFT));

        Collection<CommunicationMessage> messages = communicationMessageRepository.findAll(spec);

        return communicationMessageDao.toCommunicationMessageVOCollection(messages);
    }

    @Override
    protected Boolean handleUpdateMessageStatus(Long id, CommunicationMessageStatus status) throws Exception {
        
        CommunicationMessage message = communicationMessageRepository.getReferenceById(id);
        message.setStatus(status);
        message = communicationMessageRepository.save(message);

        return true;
    }

}