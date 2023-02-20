package bw.org.bocra.portal.sector.form;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import bw.org.bocra.portal.GenericTestData;
import bw.org.bocra.portal.form.FormEntryType;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.sector.SectorVO;

@Component
@Profile("test")
public class SectorFormTestData extends GenericTestData<SectorFormVO, SectorFormRepository, String, SectorFormRestController>{
    
    // private final FormRestController formRestController;
    // private final FormRepository formRepository;
    private final SectorFormService sectorFormService;

    public SectorFormTestData(SectorFormRestController restController, SectorFormRepository repository, SectorFormService sectorFormService) {
        super(repository, restController);
        this.sectorFormService = sectorFormService;
    }

    public void clean() {
        repository.deleteAll();
    }

    public SectorFormVO createUnsavedData() {

        FormVO form= new FormVO();
        SectorVO sector= new SectorVO();

        SectorFormVO sectorForm = new SectorFormVO();
        sectorForm.setId(null);
        sectorForm.setForm(form);
        sectorForm.setSector(sector);
        
        return sectorForm;
    }

    // public FormVO create() {
        
    //     return (FormVO) formRestController.save(createUnsaved()).getBody();
    // }


    public Collection<FormVO> generateSequentialData(int size) {

        return generateUnsavedSequentialData(size)
            .stream()
            .map(form -> formService.save(form))
            .collect(Collectors.toList());

    }

    public Collection<FormVO> generateUnsavedSequentialData(int size) {
        Collection<FormVO> forms = new ArrayList<>();

        for (int i = 1; i <= size; i++) {

            FormVO form = new FormVO();
            
            form.setCode("TEST" + i);
            form.setCreatedBy("testuser4");
            form.setCreatedDate(LocalDateTime.now());
            form.setDescription("This is a test " + i);
            form.setEntryType(FormEntryType.SINGLE);
            form.setFormName("Test Form " + i);

            forms.add(form);
            
        }

        return forms;
    }
    
    public Collection<FormVO> searchData() {
        
        Collection<FormVO> data = new ArrayList<>();

        FormVO form = new FormVO();
        
        form.setCreatedBy("testuser4");
        form.setCreatedDate(LocalDateTime.now());
        form.setFormName("Coverage");
        form.setCode("COVER");
        form.setEntryType(FormEntryType.SINGLE);
        data.add(formService.save(form));

        form = new FormVO();

        form.setCreatedBy("testuser4");
        form.setCreatedDate(LocalDateTime.now());
        form.setFormName("Quarterly Postal Mail Volume");
        form.setCode("QPMV-V1");
        form.setEntryType(FormEntryType.MULTIPLE);
        data.add(formService.save(form));

        form = new FormVO();

        form.setCreatedBy("testuser4");
        form.setCreatedDate(LocalDateTime.now());
        form.setFormName("Submit Turnover (Postal Operators)");
        form.setCode("ST-V3");
        form.setEntryType(FormEntryType.SINGLE);
        data.add(formService.save(form));

        form = new FormVO();

        form.setCreatedBy("testuser4");
        form.setCreatedDate(LocalDateTime.now());
        form.setFormName("Submit Postal Tariffs – Letter, Parcels and EMS KPIs");
        form.setCode("SPTLPE-V5");
        form.setEntryType(FormEntryType.MULTIPLE);
        data.add(formService.save(form));

        form = new FormVO();

        form.setCreatedBy("testuser4");
        form.setCreatedDate(LocalDateTime.now());
        form.setFormName("Submit Mobile Money Services");
        form.setCode("SBMS-V1");
        form.setEntryType(FormEntryType.SINGLE);
        data.add(formService.save(form));

        form = new FormVO();

        form.setCreatedBy("testuser4");
        form.setCreatedDate(LocalDateTime.now());
        form.setFormName("Submit Average Call Setup Time (2G and 3G)");
        form.setCode("SACST");
        form.setEntryType(FormEntryType.SINGLE);
        data.add(formService.save(form));

        form = new FormVO();

        form.setCreatedBy("testuser4");
        form.setCreatedDate(LocalDateTime.now());
        form.setFormName("Submit Emergency Services Provided");
        form.setCode("SESP-V1");
        form.setEntryType(FormEntryType.MULTIPLE);
        data.add(formService.save(form));

        form = new FormVO();

        form.setCreatedBy("testuser4");
        form.setCreatedDate(LocalDateTime.now());
        form.setFormName("Submit Number of Active SIMs");
        form.setCode("SNAS-V1");
        form.setEntryType(FormEntryType.SINGLE);
        data.add(formService.save(form));

        form = new FormVO();
        
        form.setCreatedBy("testuser4");
        form.setCreatedDate(LocalDateTime.now());
        form.setFormName("Submit Active Datacards");
        form.setCode("SAD");
        form.setEntryType(FormEntryType.MULTIPLE);
        data.add(formService.save(form));

        form = new FormVO();
        
        form.setCreatedBy("testuser4");
        form.setCreatedDate(LocalDateTime.now());
        form.setFormName("Submit Percentage of Population Coverage");
        form.setCode("SPPC-V1");
        form.setEntryType(FormEntryType.MULTIPLE);
        data.add(formService.save(form));

        form = new FormVO();
        
        form.setCreatedBy("testuser4");
        form.setCreatedDate(LocalDateTime.now());
        form.setFormName("Submit Transmission Parameters");
        form.setCode("STP-V1");
        form.setEntryType(FormEntryType.MULTIPLE);
        data.add(formService.save(form));

        return data;
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
    public Class<FormVO> getDataClass() {
        // TODO Auto-generated method stub
        return FormVO.class;
    }
}
