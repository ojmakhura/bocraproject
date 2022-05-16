// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::document::DocumentService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.document;

import bw.org.bocra.portal.document.type.DocumentTypeVO;
import java.util.Collection;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @see bw.org.bocra.portal.document.DocumentService
 */
@Service("documentService")
public class DocumentServiceImpl
    extends DocumentServiceBase
{

    /**
     * @see bw.org.bocra.portal.document.DocumentService#findById(Long)
     */
    @Override
    protected  DocumentVO handleFindById(Long id)
        throws Exception
    {
        return (DocumentVO) getDocumentDao().get(DocumentDao.TRANSFORM_DOCUMENTVO, id);
    }

    /**
     * @see bw.org.bocra.portal.document.DocumentService#save(DocumentVO)
     */
    @Override
    protected  DocumentVO handleSave(DocumentVO document)
        throws Exception
    {
        Document entity = getDocumentDao().documentVOToEntity(document);
        entity = this.documentRepository.save(entity);
        
        return documentDao.toDocumentVO(entity);
    }

    /**
     * @see bw.org.bocra.portal.document.DocumentService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id == null) {
            return false;
        }

        this.documentDao.remove(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.document.DocumentService#getAll()
     */
    @Override
    protected  Collection<DocumentVO> handleGetAll()
        throws Exception
    {
        return (Collection<DocumentVO>) documentDao.loadAll(DocumentDao.TRANSFORM_DOCUMENTVO);
    }

    /**
     * @see bw.org.bocra.portal.document.DocumentService#search(String)
     */
    @Override
    protected  Collection<DocumentVO> handleSearch(String criteria)
        throws Exception
    {
        Collection<Document> entities = this.documentDao.findByCriteria(criteria);
        return documentDao.toDocumentVOCollection(entities);
    }

    /**
     * @see bw.org.bocra.portal.document.DocumentService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<DocumentVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        Collection<Document> documents = null;

        if(pageNumber < 0 || pageSize < 1) {
            documents = documentRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("documentName").descending());
            documents = documentRepository.findAll(pageable).getContent();
        }

        return documents == null ? null : getDocumentDao().toDocumentVOCollection(documents);
    }

    /**
     * @see bw.org.bocra.portal.document.DocumentService#getLicenseeDocuments(Long)
     */
    @Override
    protected  Collection<DocumentVO> handleGetLicenseeDocuments(Long licenseeId)
        throws Exception
    {
        Collection<Document> docs = getDocumentDao().getLicenseeDocuments(licenseeId);

        return getDocumentDao().toDocumentVOCollection(docs);
    }

    /**
     * @see bw.org.bocra.portal.document.DocumentService#getLicenceDocuments(Long)
     */
    @Override
    protected  Collection<DocumentVO> handleGetLicenceDocuments(Long licenceId)
        throws Exception
    {
        Collection<Document> docs = getDocumentDao().getLicenceDocuments(licenceId);
        return getDocumentDao().toDocumentVOCollection(docs);

    }

}