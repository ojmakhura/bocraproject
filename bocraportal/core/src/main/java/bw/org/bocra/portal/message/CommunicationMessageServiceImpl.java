// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::message::CommunicationMessageService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.message;

import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.message.CommunicationMessageService
 */
@Service("communicationMessageService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class CommunicationMessageServiceImpl
    extends CommunicationMessageServiceBase
{
    public CommunicationMessageServiceImpl(
        CommunicationMessageDao communicationMessage,
        CommunicationMessageRepository communicationMessageRepository,
        MessageSource messageSource
    ) {
        
        super(
            communicationMessage,
            communicationMessageRepository,
            messageSource
        );
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#findById(Long)
     */
    @Override
    protected CommunicationMessageVO handleFindById(Long id)
        throws Exception
    {
        // TODO implement protected  CommunicationMessageVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.message.CommunicationMessageService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#getAll()
     */
    @Override
    protected Collection<CommunicationMessageVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<CommunicationMessageVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.message.CommunicationMessageService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<CommunicationMessageVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        // TODO implement protected  Collection<CommunicationMessageVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throw new UnsupportedOperationException("bw.org.bocra.portal.message.CommunicationMessageService.handleGetAll(Integer pageNumber, Integer pageSize) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.message.CommunicationMessageService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#save(CommunicationMessageVO)
     */
    @Override
    protected CommunicationMessageVO handleSave(CommunicationMessageVO communicationMessage)
        throws Exception
    {
        // TODO implement protected  CommunicationMessageVO handleSave(CommunicationMessageVO communicationMessage)
        throw new UnsupportedOperationException("bw.org.bocra.portal.message.CommunicationMessageService.handleSave(CommunicationMessageVO communicationMessage) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#search(String)
     */
    @Override
    protected Collection<CommunicationMessageVO> handleSearch(String criteria)
        throws Exception
    {
        // TODO implement protected  Collection<CommunicationMessageVO> handleSearch(String criteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.message.CommunicationMessageService.handleSearch(String criteria) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#search(Integer, Integer, String)
     */
    @Override
    protected Collection<CommunicationMessageVO> handleSearch(Integer pageNumber, Integer pageSize, String criteria)
        throws Exception
    {
        // TODO implement protected  Collection<CommunicationMessageVO> handleSearch(Integer pageNumber, Integer pageSize, String criteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.message.CommunicationMessageService.handleSearch(Integer pageNumber, Integer pageSize, String criteria) Not implemented!");
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
        // TODO implement protected  Integer handleClearSentMessages()
        throw new UnsupportedOperationException("bw.org.bocra.portal.message.CommunicationMessageService.handleClearSentMessages() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.message.CommunicationMessageService#clearFailedMessages()
     */
    @Override
    protected Integer handleClearFailedMessages()
        throws Exception
    {
        // TODO implement protected  Integer handleClearFailedMessages()
        throw new UnsupportedOperationException("bw.org.bocra.portal.message.CommunicationMessageService.handleClearFailedMessages() Not implemented!");
    }

}