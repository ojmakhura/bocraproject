package bw.org.bocra.portal.licence.type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;

@Component
@Profile("test")
public class LicenceTypeTestData extends GenericTestData<LicenceTypeVO, LicenceTypeRepository, LicenceTypeCriteria, LicenceTypeRestController> {
    
    // private final LicenceTypeRestController licenceTypeRestController;
    private final LicenceTypeService licenceTypeService;

    // @Autowired
    public LicenceTypeTestData(LicenceTypeRestController restController, LicenceTypeRepository repository, LicenceTypeService licenceTypeService) {
        super(repository, restController);
        // this.licenceTypeRestController = licenceTypeRestController;
        this.licenceTypeService = licenceTypeService;
    }
    
    // public licenceTypeService getlicenceTypeService() {
    //     return licenceTypeRestController;
    // }

    public void clean() {
        repository.deleteAll();
    }

    public LicenceTypeVO createUnsavedData() {
		LicenceTypeVO type = new LicenceTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");

        return type;
	}
    // public LicenceTypeVO createUnsavedLicenceType() {
    //     LicenceTypeVO type = new LicenceTypeVO();

    //     type.setCode("test");
    //     type.setName("Test Type");
    //     type.setDescription("This is a test");
    //     type.setCreatedBy("testuser4");
    //     type.setCreatedDate(LocalDateTime.now());

    //     return type;
    // }

    // public LicenceTypeVO createLicenceType() {
    //     LicenceTypeVO type = new LicenceTypeVO();

    //     type.setCode("test");
    //     type.setName("Test Type");
    //     type.setDescription("This is a test");
    //     type.setCreatedBy("testuser4");
    //     type.setCreatedDate(LocalDateTime.now());
    //     type = licenceTypeService.save(type);
        
    //     return type;
    // }

    public Collection<LicenceTypeVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(type -> licenceTypeService.save(type))
            .collect(Collectors.toList());
    }

    public Collection<LicenceTypeVO> generateUnsavedSequentialData(int size) {
        Collection<LicenceTypeVO> types = new ArrayList<>();
        for (int i = 1; i <= size; i++) {

            LicenceTypeVO type = new LicenceTypeVO();
            
            
            type.setCode("test" + i);
            type.setName("Test Type " + i);
            type.setDescription("This is a test " + i);
            type.setCreatedBy("testuser4");
            type.setCreatedDate(LocalDateTime.now());

            types.add(type);

        }
        return types;
    }

	public Collection<LicenceTypeVO> searchData() {
        Collection<LicenceTypeVO> types = new ArrayList<>();

        LicenceTypeVO type = new LicenceTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        types.add(licenceTypeService.save(type));

        type = new LicenceTypeVO();

        type.setCode("serious");
        type.setName("Serious Type");
        type.setDescription("This is a test");

        types.add(licenceTypeService.save(type));

        type = new LicenceTypeVO();

        type.setCode("onelove");
        type.setName("Top love");
        type.setDescription("This is a test");

        types.add(licenceTypeService.save(type));

        type = new LicenceTypeVO();

        type.setCode("test6");
        type.setName("Test Type 6");
        type.setDescription("This is a test");

        types.add(licenceTypeService.save(type));

        type = new LicenceTypeVO();

        type.setCode("sixteen");
        type.setName("One Six");
        type.setDescription("This is a test");

        types.add(licenceTypeService.save(type));

        type = new LicenceTypeVO();

        type.setCode("test16");
        type.setName("Testing sixteen");
        type.setDescription("This is a test");

        types.add(licenceTypeService.save(type));

        type = new LicenceTypeVO();

        type.setCode("stop");
        type.setName("Test Type Stop");
        type.setDescription("This is a test");

        types.add(licenceTypeService.save(type));
        return types;
    }

	@Override
	public LicenceTypeCriteria searchCriteria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LicenceTypeCriteria searchCriteriaEmpty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LicenceTypeCriteria searchCriteriaNone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<LicenceTypeVO> getDataClass() {
		// TODO Auto-generated method stub
		return LicenceTypeVO.class;
	}
}
