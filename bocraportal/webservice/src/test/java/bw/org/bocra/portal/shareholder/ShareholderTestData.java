package bw.org.bocra.portal.shareholder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;
import bw.org.bocra.portal.licensee.shares.ShareholderType;
import bw.org.bocra.portal.shareholder.ShareholderTestData;
import bw.org.bocra.portal.shareholder.ShareholderVO;

@Component
@Profile("test")
public class ShareholderTestData extends GenericTestData<ShareholderVO, ShareholderRepository, ShareholderCriteria, ShareholderRestController> {

    // private final ShareholderRestController ShareholderRestController;
    private final ShareholderService shareholderService;
    // private final ShareholderRepository ShareholderRepository;

    public ShareholderTestData(ShareholderRestController restController, ShareholderRepository repository, ShareholderService shareholderService) {
        super(repository, restController);
        this.shareholderService = shareholderService;
    }

    public void clean() {
        repository.deleteAll();
    }

    @Override
    public  ShareholderVO createUnsavedData() {

        ShareholderVO shareholder = new ShareholderVO();

        shareholder.setAddress("test");
        shareholder.setCreatedBy("Test User");
        shareholder.setCreatedDate(LocalDateTime.now());
        shareholder.setDocuments(shareholder.getDocuments());
        shareholder.setId(null);
        shareholder.setName("test");
        shareholder.setShareholderId("test");
        shareholder.setShares(shareholder.getShares());
        shareholder.setType(ShareholderType.INDIVIDUAL);
        shareholder.setUpdatedBy("Test user");
        shareholder.setUpdatedDate(LocalDateTime.now());

        return shareholder;
    }

    public ShareholderVO createUnsavedShareholderNoType() {

        ShareholderVO shareholder = new ShareholderVO();

        shareholder.setAddress("test");
        shareholder.setCreatedBy("Test User");
        shareholder.setCreatedDate(LocalDateTime.now());
        shareholder.setDocuments(shareholder.getDocuments());
        shareholder.setId(null);
        shareholder.setName("test");
        shareholder.setShareholderId("test");
        shareholder.setShares(shareholder.getShares());
        shareholder.setType(shareholder.getType());
        shareholder.setUpdatedBy("Test user");
        shareholder.setUpdatedDate(LocalDateTime.now());

        return shareholder;
    }
    

    public Collection<ShareholderVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(shareholder -> shareholderService.save(shareholder))
            .collect(Collectors.toList());
    }

    @Override
    public Collection<ShareholderVO> generateUnsavedSequentialData(int size) {
        Collection<ShareholderVO> shareholders = new ArrayList<>();

        for (int i = 1; i <= size; i++) {

            ShareholderVO shareholder = new ShareholderVO();

            shareholder.setAddress("test" + 1);
            shareholder.setCreatedBy("Test User" + 1);
            shareholder.setCreatedDate(LocalDateTime.now());
            shareholder.setDocuments(shareholder.getDocuments());
            shareholder.setId(null);
            shareholder.setName("test" + 1);
            shareholder.setShareholderId("test" + 1);
            shareholder.setShares(shareholder.getShares());
            shareholder.setType(ShareholderType.INDIVIDUAL);
            shareholder.setUpdatedBy("Test user" + 1);
            shareholder.setUpdatedDate(LocalDateTime.now());

            shareholders.add(shareholder);
            
        }

        return shareholders;
    }
    
    @Override
    public Collection<ShareholderVO> searchData() {
        Collection<ShareholderVO> shareholders = new ArrayList<>();

        ShareholderVO shareholder = new ShareholderVO();

        shareholder.setAddress("test");
        shareholder.setCreatedBy("Test User");
        shareholder.setCreatedDate(LocalDateTime.now());
        shareholder.setDocuments(shareholder.getDocuments());
        shareholder.setId(null);
        shareholder.setName("test");
        shareholder.setShareholderId("test");
        shareholder.setShares(shareholder.getShares());
        shareholder.setType(shareholder.getType());
        shareholder.setUpdatedBy("Test user");
        shareholder.setUpdatedDate(LocalDateTime.now());

        shareholders.add(shareholderService.save(shareholder));

        shareholder = new ShareholderVO();

        shareholder.setAddress("test");
        shareholder.setCreatedBy("Test User");
        shareholder.setCreatedDate(LocalDateTime.now());
        shareholder.setDocuments(shareholder.getDocuments());
        shareholder.setId(null);
        shareholder.setName("test");
        shareholder.setShareholderId("test");
        shareholder.setShares(shareholder.getShares());
        shareholder.setType(shareholder.getType());
        shareholder.setUpdatedBy("Test user");
        shareholder.setUpdatedDate(LocalDateTime.now());

        shareholders.add(shareholderService.save(shareholder));

        shareholder = new ShareholderVO();

        shareholder.setAddress("test");
        shareholder.setCreatedBy("Test User");
        shareholder.setCreatedDate(LocalDateTime.now());
        shareholder.setDocuments(shareholder.getDocuments());
        shareholder.setId(null);
        shareholder.setName("test");
        shareholder.setShareholderId("test");
        shareholder.setShares(shareholder.getShares());
        shareholder.setType(shareholder.getType());
        shareholder.setUpdatedBy("Test user");
        shareholder.setUpdatedDate(LocalDateTime.now());

        shareholders.add(shareholderService.save(shareholder));

        shareholder = new ShareholderVO();

        shareholder.setAddress("test");
        shareholder.setCreatedBy("Test User");
        shareholder.setCreatedDate(LocalDateTime.now());
        shareholder.setDocuments(shareholder.getDocuments());
        shareholder.setId(null);
        shareholder.setName("test");
        shareholder.setShareholderId("test");
        shareholder.setShares(shareholder.getShares());
        shareholder.setType(shareholder.getType());
        shareholder.setUpdatedBy("Test user");
        shareholder.setUpdatedDate(LocalDateTime.now());

        shareholders.add(shareholderService.save(shareholder));

        shareholder = new ShareholderVO();

        shareholder.setAddress("test");
        shareholder.setCreatedBy("Test User");
        shareholder.setCreatedDate(LocalDateTime.now());
        shareholder.setDocuments(shareholder.getDocuments());
        shareholder.setId(null);
        shareholder.setName("test");
        shareholder.setShareholderId("test");
        shareholder.setShares(shareholder.getShares());
        shareholder.setType(shareholder.getType());
        shareholder.setUpdatedBy("Test user");
        shareholder.setUpdatedDate(LocalDateTime.now());

        shareholders.add(shareholderService.save(shareholder));

        shareholder = new ShareholderVO();

        shareholder.setAddress("test");
        shareholder.setCreatedBy("Test User");
        shareholder.setCreatedDate(LocalDateTime.now());
        shareholder.setDocuments(shareholder.getDocuments());
        shareholder.setId(null);
        shareholder.setName("test");
        shareholder.setShareholderId("test");
        shareholder.setShares(shareholder.getShares());
        shareholder.setType(shareholder.getType());
        shareholder.setUpdatedBy("Test user");
        shareholder.setUpdatedDate(LocalDateTime.now());

        shareholders.add(shareholderService.save(shareholder));

        shareholder = new ShareholderVO();

        shareholder.setAddress("test");
        shareholder.setCreatedBy("Test User");
        shareholder.setCreatedDate(LocalDateTime.now());
        shareholder.setDocuments(shareholder.getDocuments());
        shareholder.setId(null);
        shareholder.setName("test");
        shareholder.setShareholderId("test");
        shareholder.setShares(shareholder.getShares());
        shareholder.setType(shareholder.getType());
        shareholder.setUpdatedBy("Test user");
        shareholder.setUpdatedDate(LocalDateTime.now());

        shareholders.add(shareholderService.save(shareholder));

        return shareholders;
    }

    @Override
    public ShareholderCriteria searchCriteria() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ShareholderCriteria searchCriteriaEmpty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ShareholderCriteria searchCriteriaNone() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<ShareholderVO> getDataClass() {
        // TODO Auto-generated method stub
        return ShareholderVO.class;
    }
}
