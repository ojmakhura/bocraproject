// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::contact::ContactMessageService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.contact;

import java.util.Collection;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.contact.ContactMessageService
 */
@Service("contactMessageService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class ContactMessageServiceImpl
    extends ContactMessageServiceBase
{
    public ContactMessageServiceImpl(
        ContactMessageDao contactMessage,
        ContactMessageRepository contactMessageRepository,
        MessageSource messageSource
    ) {
        
        super(
            contactMessage,
            contactMessageRepository,
            messageSource
        );
    }

    /**
     * @see bw.org.bocra.portal.contact.ContactMessageService#findById(Long)
     */
    @Override
    protected ContactMessageVO handleFindById(Long id)
        throws Exception
    {
        // TODO implement protected  ContactMessageVO handleFindById(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.contact.ContactMessageService.handleFindById(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.contact.ContactMessageService#save(ContactMessageVO)
     */
    @Override
    protected ContactMessageVO handleSave(ContactMessageVO contactMessage)
        throws Exception
    {
        // TODO implement protected  ContactMessageVO handleSave(ContactMessageVO contactMessage)
        throw new UnsupportedOperationException("bw.org.bocra.portal.contact.ContactMessageService.handleSave(ContactMessageVO contactMessage) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.contact.ContactMessageService#remove(Long)
     */
    @Override
    protected boolean handleRemove(Long id)
        throws Exception
    {
        // TODO implement protected  boolean handleRemove(Long id)
        throw new UnsupportedOperationException("bw.org.bocra.portal.contact.ContactMessageService.handleRemove(Long id) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.contact.ContactMessageService#getAll()
     */
    @Override
    protected Collection<ContactMessageVO> handleGetAll()
        throws Exception
    {
        // TODO implement protected  Collection<ContactMessageVO> handleGetAll()
        throw new UnsupportedOperationException("bw.org.bocra.portal.contact.ContactMessageService.handleGetAll() Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.contact.ContactMessageService#search(String)
     */
    @Override
    protected Collection<ContactMessageVO> handleSearch(String criteria)
        throws Exception
    {
        // TODO implement protected  Collection<ContactMessageVO> handleSearch(String criteria)
        throw new UnsupportedOperationException("bw.org.bocra.portal.contact.ContactMessageService.handleSearch(String criteria) Not implemented!");
    }

    /**
     * @see bw.org.bocra.portal.contact.ContactMessageService#getAll(Integer, Integer)
     */
    @Override
    protected Collection<ContactMessageVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        // TODO implement protected  Collection<ContactMessageVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throw new UnsupportedOperationException("bw.org.bocra.portal.contact.ContactMessageService.handleGetAll(Integer pageNumber, Integer pageSize) Not implemented!");
    }

}