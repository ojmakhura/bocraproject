// Generated by hibernate/SpringHibernateDaoImpl.vsl in andromda-spring-cartridge on $springUtils.date.
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package bw.org.bocra.portal.document.type;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import bw.org.bocra.portal.document.DocumentSpecifications;

/**
 * @see DocumentType
 */
@Repository("documentTypeDao")
public class DocumentTypeDaoImpl
    extends DocumentTypeDaoBase
{
    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<DocumentType> handleFindByCriteria(String criteria)
    {
        Specification<DocumentType> spec = null;

        if(StringUtils.isNotBlank(criteria)) {
            spec = DocumentTypeSpecifications.findByCodeContainingIgnoreCase(criteria)
                .or(DocumentTypeSpecifications.findByNameContainingIgnoreCase(criteria))
                .or(DocumentTypeSpecifications.findByDescriptionContainingIgnoreCase(criteria));
        }

        return documentTypeRepository.findAll(spec);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toDocumentTypeVO(
        DocumentType source,
        DocumentTypeVO target)
    {
        // TODO verify behavior of toDocumentTypeVO
        super.toDocumentTypeVO(source, target);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentTypeVO toDocumentTypeVO(final DocumentType entity)
    {
        // TODO verify behavior of toDocumentTypeVO
        return super.toDocumentTypeVO(entity);
    }

    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private DocumentType loadDocumentTypeFromDocumentTypeVO(DocumentTypeVO documentTypeVO)
    {
        if (documentTypeVO.getId() == null)
        {
            return  DocumentType.Factory.newInstance();
        }
        else
        {
            return this.load(documentTypeVO.getId());
        }
    }

    /**
     * {@inheritDoc}
     */
    public DocumentType documentTypeVOToEntity(DocumentTypeVO documentTypeVO)
    {
        // TODO verify behavior of documentTypeVOToEntity
        DocumentType entity = this.loadDocumentTypeFromDocumentTypeVO(documentTypeVO);
        this.documentTypeVOToEntity(documentTypeVO, entity, true);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void documentTypeVOToEntity(
        DocumentTypeVO source,
        DocumentType target,
        boolean copyIfNull)
    {
        // TODO verify behavior of documentTypeVOToEntity
        super.documentTypeVOToEntity(source, target, copyIfNull);

        
    }
}