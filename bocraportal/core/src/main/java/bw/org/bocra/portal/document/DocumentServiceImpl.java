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
import java.util.UUID;

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

    @Override
    protected byte[] handleDownloadFile(Long id) throws Exception {
        return getDocumentDao().get(id).getFile();
    }

}