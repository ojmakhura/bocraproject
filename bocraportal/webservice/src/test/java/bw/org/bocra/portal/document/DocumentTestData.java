package bw.org.bocra.portal.document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;
import bw.org.bocra.portal.document.type.DocumentTypeTestData;
import bw.org.bocra.portal.document.type.DocumentTypeVO;

@Component
@Profile("test")
public class DocumentTestData extends GenericTestData<DocumentVO, DocumentRepository, DocumentCriteria, DocumentRestController> {

    // private final DocumentRestController DocumentRestController;
    private final DocumentTypeTestData documentTypeTestData;
    private final DocumentService documentService;
    // private final DocumentRepository DocumentRepository;

    public DocumentTestData(DocumentRestController documentRestController, DocumentTypeTestData documentTypeTestData, DocumentRepository documentRepository, DocumentService documentService) {
        super(documentRepository, documentRestController);
        this.documentTypeTestData = documentTypeTestData;
        this.documentService = documentService;
    }

    @Override
    public  DocumentVO createUnsavedData() {
        DocumentTypeVO type = documentTypeTestData.generateSequentialData(1).iterator().next();

        DocumentVO document = createUnsavedDocumentNoType();

        document.setDocumentType(type);

        return document;
    }
    
    public DocumentVO createUnsavedDocumentNoType() {

        DocumentVO document = new DocumentVO();

        document.setDocumentType(null);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile((null));

        return document;
    }
    
    public DocumentVO createUnsavedDocumentUnsavedType() {

        DocumentVO document = createUnsavedDocumentNoType();
        document.setDocumentType(documentTypeTestData.createUnsavedData());

        return document;
    }

    // public DocumentVO create() {
    //     return (DocumentVO)getRestController().save(createUnsavedDocument()).getBody();
    // }

    public Collection<DocumentVO> generateSequentialData(int size) {

        return generateUnsavedSequentialData(size)
            .stream()
            .map(document -> documentService.save(document))
            .collect(Collectors.toList());

    }

    @Override
    public Collection<DocumentVO> generateUnsavedSequentialData(int size) {
        Collection<DocumentVO> documents = new ArrayList<>();
        DocumentTypeVO type = documentTypeTestData.generateSequentialData(1).iterator().next();

        for (int i = 1; i <= size; i++) {

            DocumentVO document = new DocumentVO();

            document.setDocumentType(type);
            document.setCreatedBy("testuser4");
            document.setCreatedDate(LocalDateTime.now());
            document.setDocumentId("1234");
            document.setDocumentName("Test test");
            document.setExtension(".pdf");
            document.setFile(null);
        
            documents.add(document);
            
        }

        return documents;
    }
    
    @Override
    public Collection<DocumentVO> searchData() {
        
        DocumentTypeVO type = documentTypeTestData.generateSequentialData(1).iterator().next();
        Collection<DocumentVO> data = new ArrayList<>();

        DocumentVO document = new DocumentVO();

        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);
        data.add((DocumentVO) getRestController().save(document).getBody());

        document = new DocumentVO();

        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);
        data.add((DocumentVO) getRestController().save(document).getBody());

        document = new DocumentVO();

        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);
        data.add((DocumentVO) getRestController().save(document).getBody());

        document = new DocumentVO();

        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);
        data.add((DocumentVO) getRestController().save(document).getBody());

        document = new DocumentVO();

        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);
        data.add((DocumentVO) getRestController().save(document).getBody());

        document = new DocumentVO();

        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);
        data.add((DocumentVO) getRestController().save(document).getBody());

        document = new DocumentVO();

        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);
        data.add((DocumentVO) getRestController().save(document).getBody());

        document = new DocumentVO();

        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);
        data.add((DocumentVO) getRestController().save(document).getBody());

        document = new DocumentVO();
        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);
        data.add((DocumentVO) getRestController().save(document).getBody());

        document = new DocumentVO();
        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);
        data.add((DocumentVO) getRestController().save(document).getBody());

        document = new DocumentVO();
        document.setDocumentType(type);
        document.setCreatedBy("testuser4");
        document.setCreatedDate(LocalDateTime.now());
        document.setDocumentId("1234");
        document.setDocumentName("Test test");
        document.setExtension(".pdf");
        document.setFile(null);
        data.add((DocumentVO) getRestController().save(document).getBody());

        return data;
    }

    @Override
    public DocumentCriteria searchCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DocumentCriteria searchCriteriaEmpty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DocumentCriteria searchCriteriaNone() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<DocumentVO> getDataClass() {
        // TODO Auto-generated method stub
        return DocumentVO.class;
    }
}
