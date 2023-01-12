package bw.org.bocra.portal.licence.type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;

@Component
@Profile("test")
public class LicenceTypeTestData extends GenericTestData<LicenceTypeVO, LicenceTypeRepository, String, LicenceTypeRestController> {
    
    private final LicenceTypeRestController licenceTypeRestController;
    private final LicenceTypeService licenceTypeService;

    public LicenceTypeTestData(LicenceTypeRestController licenceTypeRestController, LicenceTypeRepository repository, LicenceTypeService licenceTypeService) {
        super(repository, licenceTypeRestController);
        this.licenceTypeRestController = licenceTypeRestController;
        this.licenceTypeService = licenceTypeService;
    }
    
    // public licenceTypeService getlicenceTypeService() {
    //     return licenceTypeRestController;
    // }

    public void clean() {
        repository.deleteAll();
    }
    public LicenceTypeVO createUnsavedLicenceType() {
        LicenceTypeVO type = new LicenceTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        return type;
    }

    public LicenceTypeVO createLicenceType() {
        LicenceTypeVO type = new LicenceTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());
        type = (LicenceTypeVO) licenceTypeRestController.save(type).getBody();
        
        return type;
    }

    public Collection<LicenceTypeVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(type -> (LicenceTypeVO)licenceTypeRestController.save(type).getBody())
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
    
    public Collection<LicenceTypeVO> generateSearchData() {
        Collection<LicenceTypeVO> types = new ArrayList<>();

        LicenceTypeVO type = new LicenceTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) licenceTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("serious");
        type.setName("Serious Type");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) licenceTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("onelove");
        type.setName("Top love");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) licenceTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("test6");
        type.setName("Test Type 6");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) licenceTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("sixteen");
        type.setName("One Six");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) licenceTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("test16");
        type.setName("Testing sixteen");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) licenceTypeRestController.save(type).getBody());

        type = new LicenceTypeVO();

        type.setCode("stop");
        type.setName("Test Type Stop");
        type.setDescription("This is a test");
        type.setCreatedBy("testuser4");
        type.setCreatedDate(LocalDateTime.now());

        types.add((LicenceTypeVO) licenceTypeRestController.save(type).getBody());
        return types;
    }

	@Override
	public LicenceTypeVO createUnsavedData() {
		// TODO Auto-generated method stub
		LicenceTypeVO type = new LicenceTypeVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        return type;
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
	public String searchCriteria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchCriteriaEmpty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchCriteriaNone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<LicenceTypeVO> getDataClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
