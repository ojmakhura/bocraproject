package bw.org.bocra.portal.licensee.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import bw.org.bocra.portal.form.FormTestData;
import bw.org.bocra.portal.form.FormVO;
import bw.org.bocra.portal.licensee.LicenseeTestData;
import bw.org.bocra.portal.licensee.LicenseeVO;

@Component
public class LicenseeFormTestData {
    
    private final LicenseeFormRestController licenseeFormRestController;
    private final FormTestData formTestData;
    private final LicenseeTestData licenseeTestData;

    public LicenseeFormTestData(LicenseeFormRestController licenseeFormRestController, LicenseeTestData licenseeTestData, FormTestData formTestData) {
        this.licenseeFormRestController = licenseeFormRestController;
        this.licenseeTestData = licenseeTestData;
        this.formTestData = formTestData;
    }
    
    public LicenseeFormRestController getLicenseeFormRestController() {
        return licenseeFormRestController;
    }

    public LicenseeFormVO createUncreated() {
        LicenseeFormVO licenseeForm = new LicenseeFormVO();

        licenseeForm.setForm(formTestData.create());
        licenseeForm.setLicensee(licenseeTestData.createLicensee());

        return licenseeForm;
    }

    public LicenseeFormVO create() {
        LicenseeFormVO lf = createUncreated();
        return (LicenseeFormVO) licenseeFormRestController.create(lf.getLicensee().getId(), lf.getForm().getId()).getBody();
    }

    public Collection<LicenseeFormVO> generateSequentialData(int size) {
        
        return generateUncreatedSequentialData(size)
            .stream()
            .map(lf -> (LicenseeFormVO)licenseeFormRestController.create(lf.getLicensee().getId(), lf.getForm().getId()).getBody())
            .collect(Collectors.toList());
    }

    public Collection<LicenseeFormVO> generateUncreatedSequentialData(int size) {
        ArrayList<FormVO> forms = (ArrayList<FormVO>) formTestData.generateSequentialData(size);
        ArrayList<LicenseeVO> licensees = (ArrayList<LicenseeVO>) licenseeTestData.generateSequentialData(size);
        Collection<LicenseeFormVO> licenseeForms = new ArrayList<>();

        for (int i = 1; i <= size; i++) {

            LicenseeFormVO lf = new LicenseeFormVO();
            lf.setForm(forms.get(i));
            lf.setLicensee(licensees.get(i));

            licenseeForms.add(lf);

        }
        return licenseeForms;
    }
    
    public Collection<LicenseeFormVO> generateSearchData() {
        Collection<FormVO> forms = formTestData.generateSearchData();
        Iterator<FormVO> formIter = forms.iterator();

        Collection<LicenseeVO> licensees = licenseeTestData.generateSearchData();
        Iterator<LicenseeVO> lcIter = licensees.iterator();

        Collection<LicenseeFormVO> licenseeForms = new ArrayList<>();

        FormVO f = formIter.next();
        LicenseeVO lic = lcIter.next();

        LicenseeFormVO lf = new LicenseeFormVO();
        lf.setForm(f);
        lf.setLicensee(lic);

        licenseeForms.add((LicenseeFormVO) licenseeFormRestController.create(lf.getLicensee().getId(), lf.getForm().getId()).getBody());

        lic = lcIter.next();
        lf = new LicenseeFormVO();
        lf.setForm(f);
        lf.setLicensee(lic);

        licenseeForms.add((LicenseeFormVO) licenseeFormRestController.create(lf.getLicensee().getId(), lf.getForm().getId()).getBody());

        f = formIter.next();
        lf = new LicenseeFormVO();
        lf.setForm(f);
        lf.setLicensee(lic);

        licenseeForms.add((LicenseeFormVO) licenseeFormRestController.create(lf.getLicensee().getId(), lf.getForm().getId()).getBody());

        lic = lcIter.next();
        lf = new LicenseeFormVO();
        lf.setForm(f);
        lf.setLicensee(lic);

        licenseeForms.add((LicenseeFormVO) licenseeFormRestController.create(lf.getLicensee().getId(), lf.getForm().getId()).getBody());

        f = formIter.next();
        lf = new LicenseeFormVO();
        lf.setForm(f);
        lf.setLicensee(lic);

        licenseeForms.add((LicenseeFormVO) licenseeFormRestController.create(lf.getLicensee().getId(), lf.getForm().getId()).getBody());

        lic = lcIter.next();
        lf = new LicenseeFormVO();
        lf.setForm(f);
        lf.setLicensee(lic);

        licenseeForms.add((LicenseeFormVO) licenseeFormRestController.create(lf.getLicensee().getId(), lf.getForm().getId()).getBody());

        f = formIter.next();
        lf = new LicenseeFormVO();
        lf.setForm(f);
        lf.setLicensee(lic);

        licenseeForms.add((LicenseeFormVO) licenseeFormRestController.create(lf.getLicensee().getId(), lf.getForm().getId()).getBody());
        return licenseeForms;
    }
}
