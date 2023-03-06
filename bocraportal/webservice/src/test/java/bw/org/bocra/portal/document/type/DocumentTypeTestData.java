package bw.org.bocra.portal.document.type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;

@Component
@Profile("test")
public class DocumentTypeTestData extends GenericTestData<DocumentTypeVO, DocumentTypeRepository, String, DocumentTypeRestController> {

    private final DocumentTypeService DocumentTypeService;

    public DocumentTypeTestData(DocumentTypeRestController restController, DocumentTypeRepository repository, DocumentTypeService DocumentTypeService) {
        super(repository, restController);
        this.DocumentTypeService = DocumentTypeService;
    }

    public void clean() {
        repository.deleteAll();
    }

    public DocumentTypeVO createUnsavedData() {
        DocumentTypeVO type = new DocumentTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        return type;
    }

    // public DocumentTypeVO createDocumentType() {
    //     DocumentTypeVO type = new DocumentTypeVO();

    //     type.setCode("test");
    //     type.setName("Test Type");
    //     type.setDescription("This is a test");
    //     type = DocumentTypeService.save(type);
        
    //     return type;
    // }

    public Collection<DocumentTypeVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(type -> DocumentTypeService.save(type))
            .collect(Collectors.toList());
    }

    public Collection<DocumentTypeVO> generateUnsavedSequentialData(int size) {
        Collection<DocumentTypeVO> types = new ArrayList<>();
        for (int i = 1; i <= size; i++) {

            DocumentTypeVO type = new DocumentTypeVO();

            type.setCode("test" + i);
            type.setName("Test Type " + i);
            type.setDescription("This is a test " + i);
            type.setCreatedBy("test test" + 1);
            type.setCreatedDate(LocalDateTime.now() );

            types.add(type);

        }
        return types;
    }
    
    public Collection<DocumentTypeVO> searchData() {
        Collection<DocumentTypeVO> types = new ArrayList<>();

        DocumentTypeVO type = new DocumentTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        types.add(DocumentTypeService.save(type));

        type = new DocumentTypeVO();

        type.setCode("serious");
        type.setName("Serious Type");
        type.setDescription("This is a test");

        types.add(DocumentTypeService.save(type));

        type = new DocumentTypeVO();

        type.setCode("onelove");
        type.setName("Top love");
        type.setDescription("This is a test");

        types.add(DocumentTypeService.save(type));

        type = new DocumentTypeVO();

        type.setCode("test6");
        type.setName("Test Type 6");
        type.setDescription("This is a test");

        types.add(DocumentTypeService.save(type));

        type = new DocumentTypeVO();

        type.setCode("sixteen");
        type.setName("One Six");
        type.setDescription("This is a test");

        types.add(DocumentTypeService.save(type));

        type = new DocumentTypeVO();

        type.setCode("test16");
        type.setName("Testing sixteen");
        type.setDescription("This is a test");

        types.add(DocumentTypeService.save(type));

        type = new DocumentTypeVO();

        type.setCode("stop");
        type.setName("Test Type Stop");
        type.setDescription("This is a test");

        types.add(DocumentTypeService.save(type));
        return types;
    }

    @Override
    public String searchCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String searchCriteriaEmpty() {
        
        return "";
    }

    @Override
    public String searchCriteriaNone() {
        return "null";
    }

    @Override
    public Class<DocumentTypeVO> getDataClass() {
        // TODO Auto-generated method stub
        return DocumentTypeVO.class;
    }
}
