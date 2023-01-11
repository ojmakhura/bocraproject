package bw.org.bocra.portal.sector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class SectorTestData {
    
    private final SectorRestController sectorRestController;

    public SectorTestData(SectorRestController sectorRestController) {
        this.sectorRestController = sectorRestController;
    }
    
    public SectorRestController getSectorRestController() {
        return sectorRestController;
    }

    public SectorVO createUnsavedSector() {
        SectorVO type = new SectorVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        return type;
    }

    public SectorVO createSector() {
        SectorVO type = new SectorVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");
        type = (SectorVO) sectorRestController.save(type).getBody();
        
        return type;
    }

    public Collection<SectorVO> generateSequentialData(int size) {
        
        return generateUnsavedSequentialData(size)
            .stream()
            .map(type -> (SectorVO)sectorRestController.save(type).getBody())
            .collect(Collectors.toList());
    }

    public Collection<SectorVO> generateUnsavedSequentialData(int size) {
        Collection<SectorVO> types = new ArrayList<>();
        for (int i = 1; i <= size; i++) {

            SectorVO type = new SectorVO();

            type.setCode("test" + i);
            type.setName("Test Type " + i);
            type.setDescription("This is a test " + i);

            types.add(type);

        }
        return types;
    }
    
    public Collection<SectorVO> generateSearchData() {
        Collection<SectorVO> types = new ArrayList<>();

        SectorVO type = new SectorVO();

        type.setCode("test");
        type.setName("Test Type");
        type.setDescription("This is a test");

        types.add((SectorVO) sectorRestController.save(type).getBody());

        type = new SectorVO();

        type.setCode("serious");
        type.setName("Serious Type");
        type.setDescription("This is a test");

        types.add((SectorVO) sectorRestController.save(type).getBody());

        type = new SectorVO();

        type.setCode("onelove");
        type.setName("Top love");
        type.setDescription("This is a test");

        types.add((SectorVO) sectorRestController.save(type).getBody());

        type = new SectorVO();

        type.setCode("test6");
        type.setName("Test Type 6");
        type.setDescription("This is a test");

        types.add((SectorVO) sectorRestController.save(type).getBody());

        type = new SectorVO();

        type.setCode("sixteen");
        type.setName("One Six");
        type.setDescription("This is a test");

        types.add((SectorVO) sectorRestController.save(type).getBody());

        type = new SectorVO();

        type.setCode("test16");
        type.setName("Testing sixteen");
        type.setDescription("This is a test");

        types.add((SectorVO) sectorRestController.save(type).getBody());

        type = new SectorVO();

        type.setCode("stop");
        type.setName("Test Type Stop");
        type.setDescription("This is a test");

        types.add((SectorVO) sectorRestController.save(type).getBody());
        return types;
    }
}
