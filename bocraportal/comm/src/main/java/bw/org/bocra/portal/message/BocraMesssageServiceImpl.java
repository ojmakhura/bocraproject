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
import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.message.BocraMesssageService
 */
@Service("BocraMesssageService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class BocraMesssageServiceImpl
    extends BocraMesssageServiceBase
{

    private final EntityManager entityManager;

    public BocraMesssageServiceImpl(
        BocraMesssageDao BocraMesssage, 
        BocraMesssageRepository BocraMesssageRepository,
        MessageSource messageSource, EntityManager entityManager
    ) {
        
        super(
            BocraMesssage,
            BocraMesssageRepository,
            messageSource
        );

        this.entityManager = entityManager;
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#findById(Long)
     */
    @Override
    protected BocraMesssageVO handleFindById(Long id)
        throws Exception
    {
        BocraMesssage message = BocraMesssageRepository.getReferenceById(id);

        return BocraMesssageDao.toBocraMesssageVO(message);
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#getAll()
     */
    @Override
    protected Collection<BocraMesssageVO> handleGetAll()
        throws Exception
    {
        Collection<BocraMesssage> messages = BocraMesssageRepository.findAll();
        return BocraMesssageDao.toBocraMesssageVOCollection(messages);
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<BocraMesssageVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Collection<BocraMesssage> messages = BocraMesssageRepository.findAll(pageable).getContent();

        return BocraMesssageDao.toBocraMesssageVOCollection(messages);
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        BocraMesssageRepository.deleteById(id);
        return true;
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#save(BocraMesssageVO)
     */
    @Override
    protected BocraMesssageVO handleSave(BocraMesssageVO BocraMesssage)
        throws Exception
    {
        BocraMesssage message = BocraMesssageDao.BocraMesssageVOToEntity(BocraMesssage);
        message = BocraMesssageRepository.save(message);

        return BocraMesssageDao.toBocraMesssageVO(message);
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#search(String)
     */
    @Override
    protected Collection<BocraMesssageVO> handleSearch(String criteria)
        throws Exception
    {
        Specification<BocraMesssage> spec = null;

        if(StringUtils.isNotBlank(criteria)) {
            spec = this.findByAttributeLikeIgnoreCase("subject", criteria);
        }

        Collection<BocraMesssage> messages = BocraMesssageRepository.findAll(spec, Sort.by("id").descending());

        return BocraMesssageDao.toBocraMesssageVOCollection(messages);
    }

    /**
     * @see bw.org.bocra.portal.message.BocraMesssageService#search(Integer, Integer, String)
     */
    @Override
    protected Collection<BocraMesssageVO> handleSearch(Integer pageNumber, Integer pageSize, String criteria)
        throws Exception
    {
        Specification<BocraMesssage> spec = null;

        if(StringUtils.isNotBlank(criteria)) {
            spec = this.findByAttributeLikeIgnoreCase("subject", criteria);
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        Collection<BocraMesssage> messages = BocraMesssageRepository.findAll(spec, pageable).toList();
        
        return BocraMesssageDao.toBocraMesssageVOCollection(messages);
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

        Collection<BocraMesssage> messages = BocraMesssageRepository.findAll(spec);

        return BocraMesssageDao.toBocraMesssageVOCollection(messages);
    }

    @Override
    protected Boolean handleUpdateMessageStatus(Long id, BocraMesssageStatus status) throws Exception {
        
        BocraMesssage message = BocraMesssageRepository.getReferenceById(id);
        message.setStatus(status);
        message = BocraMesssageRepository.save(message);

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