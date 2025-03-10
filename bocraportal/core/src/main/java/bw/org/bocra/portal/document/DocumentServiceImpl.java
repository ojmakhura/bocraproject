// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::document::DocumentService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.document;

import bw.org.bocra.portal.BocraportalSpecifications;
import bw.org.bocra.portal.document.type.DocumentTypeVO;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see bw.org.bocra.portal.document.DocumentService
 */
@Service("documentService")
@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
public class DocumentServiceImpl
    extends DocumentServiceBase
{

    public DocumentServiceImpl(DocumentDao documentDao, DocumentRepository documentRepository,
            MessageSource messageSource) {
        super(documentDao, documentRepository, messageSource);
    }

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

        if(StringUtils.isBlank(entity.getDocumentId())) {
            String uid = RandomStringUtils.random(17, true, true);
            List<Document> docs = this.documentRepository.findAll(BocraportalSpecifications.<Document, String>findByAttribute("documentId", uid));

            while(CollectionUtils.isNotEmpty(docs)) {
                uid = RandomStringUtils.random(17, true, true);
                docs = this.documentRepository.findAll(BocraportalSpecifications.<Document, String>findByAttribute("documentId", uid));
            }

            entity.setDocumentId(uid);
        }

        entity = this.documentRepository.saveAndFlush(entity);
        
        return documentDao.toDocumentVO(entity);
    }

    /**
     * @see bw.org.bocra.portal.document.DocumentService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        
        this.documentRepository.deleteById(id);

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

    private List<Document> getDocuments(String documentId) {
        if(StringUtils.isBlank(documentId)) {
            throw new DocumentServiceException("Document ID should not be null.");
        }

        List<Document> docs = documentRepository.findByDocumentId(documentId, PageRequest.of(0, 1)).getContent();

        if(CollectionUtils.isEmpty(docs)) {
            throw new DocumentServiceException(String.format("No documents with document ID %s found.", documentId));
        }

        return docs;
    } 

    /**
     * @see bw.org.bocra.portal.document.DocumentService#search(String)
     */
    @Override
    protected  Collection<DocumentVO> handleSearch(DocumentCriteria criteria)
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

    @Override
    protected byte[] handleDownloadFile(String documentId) throws Exception {

        return getDocuments(documentId).get(0).getFile();
    }

    @Override
    protected Collection<DocumentVO> handleFindByIds(Set<Long> ids) throws Exception {
        
        return getDocumentDao().toDocumentVOCollection(documentRepository.findByIdIn(ids.stream().collect(Collectors.toList())));
    }

    @Override
    protected Collection<DocumentVO> handleFindByDocumentIds(Set<String> documentIds) throws Exception {
        return getDocumentDao().toDocumentVOCollection(documentRepository.findByDocumentIdIn(documentIds.stream().collect(Collectors.toList())));
    }

    @Override
    protected DocumentVO handleFindByDocumentId(String documentId) throws Exception {
        
        return documentDao.toDocumentVO(getDocuments(documentId).get(0));
    }

}