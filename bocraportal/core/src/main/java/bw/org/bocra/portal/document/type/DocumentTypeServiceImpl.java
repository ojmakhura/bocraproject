// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 * TEMPLATE:    SpringServiceImpl.vsl in andromda-spring cartridge
 * MODEL CLASS: bocraportal::backend::bw.org.bocra.portal::document::type::DocumentTypeService
 * STEREOTYPE:  Service
 */
package bw.org.bocra.portal.document.type;

import java.util.Collection;

import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import bw.org.bocra.portal.document.DocumentDao;
import bw.org.bocra.portal.document.DocumentRepository;

/**
 * @see bw.org.bocra.portal.document.type.DocumentTypeService
 */
@Service("documentTypeService")
public class DocumentTypeServiceImpl
    extends DocumentTypeServiceBase
{

    public DocumentTypeServiceImpl(DocumentDao documentDao, DocumentRepository documentRepository,
            DocumentTypeDao documentTypeDao, DocumentTypeRepository documentTypeRepository,
            MessageSource messageSource) {
        super(documentDao, documentRepository, documentTypeDao, documentTypeRepository, messageSource);
        //TODO Auto-generated constructor stub
    }

    /**
     * @see bw.org.bocra.portal.document.type.DocumentTypeService#findById(Long)
     */
    @Override
    protected  DocumentTypeVO handleFindById(Long id)
        throws Exception
    {
        if(id == null) {
            return null;
        }

        return documentTypeDao.toDocumentTypeVO(documentTypeRepository.getById(id));

    }

    /**
     * @see bw.org.bocra.portal.document.type.DocumentTypeService#save(DocumentTypeVO)
     */
    @Override
    protected  DocumentTypeVO handleSave(DocumentTypeVO documentType)
        throws Exception
    {

        return (DocumentTypeVO) documentTypeDao.create(DocumentTypeDao.TRANSFORM_DOCUMENTTYPEVO, documentTypeDao.documentTypeVOToEntity(documentType));

    }

    /**
     * @see bw.org.bocra.portal.document.type.DocumentTypeService#remove(Long)
     */
    @Override
    protected  boolean handleRemove(Long id)
        throws Exception
    {
        if(id == null) return false;

        documentTypeRepository.deleteById(id);

        return true;
    }

    /**
     * @see bw.org.bocra.portal.document.type.DocumentTypeService#getAll()
     */
    @Override
    protected  Collection<DocumentTypeVO> handleGetAll()
        throws Exception
    {
        return documentTypeDao.toDocumentTypeVOCollection(documentTypeDao.loadAll());
    }

    /**
     * @see bw.org.bocra.portal.document.type.DocumentTypeService#search(String)
     */
    @Override
    protected  Collection<DocumentTypeVO> handleSearch(String criteria)
        throws Exception
    {
        Collection<DocumentType> types = documentTypeDao.findByCriteria(criteria);
        return documentTypeDao.toDocumentTypeVOCollection(types);
    }

    /**
     * @see bw.org.bocra.portal.document.type.DocumentTypeService#getAll(Integer, Integer)
     */
    @Override
    protected  Collection<DocumentTypeVO> handleGetAll(Integer pageNumber, Integer pageSize)
        throws Exception
    {
        Collection<DocumentType> types = null;

        if(pageNumber < 0 || pageSize < 1) {
            types = documentTypeRepository.findAll();
        } else {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("documentName").descending());
            types = documentTypeRepository.findAll(pageable).getContent();
        }

        return types == null ? null : getDocumentTypeDao().toDocumentTypeVOCollection(types);
    }

}