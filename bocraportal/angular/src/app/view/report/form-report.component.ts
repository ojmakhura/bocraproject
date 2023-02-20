// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { AfterViewInit, Component, Injector, Input, OnDestroy, OnInit, ViewChildren } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { FormSubmissionVO } from '@app/model/bw/org/bocra/portal/form/submission/form-submission-vo';
import { ReportRestController } from '@app/service/bw/org/bocra/portal/report/report-rest-controller';
import { ReportElement, ReportElementComponent } from './report-element.component';
import { saveAs } from 'file-saver';

export class FormReport {
  formName: string = '';
  formCode: string = '';
  formSubmissions: FormSubmissionVO[] = [];
  reportElements: ReportElement[] = [];
}

@Component({
  selector: 'app-form-report',
  templateUrl: './form-report.component.html',
})
export class FormReportComponent implements OnInit, AfterViewInit, OnDestroy {
  @ViewChildren('reportElement') reportElementComponents: ReportElementComponent[];
  @Input() formReportGroup: FormGroup | any;
  protected formBuilder: FormBuilder;
  reportController: ReportRestController;

  constructor(private injector: Injector) {
    this.formBuilder = this.injector.get(FormBuilder);
    this.reportController = this.injector.get(ReportRestController)
  }

  ngOnInit(): void { }

  ngAfterViewInit(): void { }

  ngOnDestroy(): void { }

  clearReport() { }

  get reportElementsControl(): FormArray {
    return this.formReportGroup.get('reportElements') as FormArray;
  }

  get reportElements(): ReportElement[] {
    return this.reportElementsControl.value;
  }

  get formSubmissionsControl(): FormArray {
    return this.formReportGroup.get('formSubmissions') as FormArray;
  }

  get formSubmissions(): FormSubmissionVO[] {
    return this.formSubmissionsControl.value;
  }

  get formNameControl(): FormArray {
    return this.formReportGroup.get('formName') as FormArray;
  }

  get formName(): string {
    return this.formNameControl.value;
  }

  removeReportElement(elementIndex: number) {
    this.reportElementsControl.removeAt(elementIndex);
  }

  addReportElement() {
    this.reportElementsControl.push(this.createReportElementGroup(new ReportElement()));
  }

  createReportElementGroup(reportElement: ReportElement): FormGroup {
    return this.formBuilder.group({
      groupBy: [reportElement?.groupBy],
      reportType: [reportElement?.reportType],
      selectAllLicensees: [reportElement?.selectAllLicensees],
      selectAllPeriods: [reportElement?.selectAllPeriods],
      selectAllForms: [reportElement?.selectAllForms],
      dataColumns: [reportElement?.dataColumns ? reportElement?.dataColumns : 'licensees'],
      dataRows: [reportElement?.dataRows ? reportElement?.dataRows : 'fields'],
      licenseeSelections: this.formBuilder.array([]),
      periodSelections: this.formBuilder.array([]),
      fieldSelections: this.formBuilder.array([]),
      charts: this.formBuilder.array([]),
    });
  }

  createFormReportGroup(formReport: FormReport): FormGroup {
    return this.formBuilder.group({
      formName: [{ value: formReport?.formName, disabled: false }],
      formCode: [{ value: formReport?.formCode, disabled: false }],
      licensees: this.formBuilder.array([
        ...new Set(formReport?.formSubmissions?.map((sub) => sub?.licensee?.licenseeName)),
      ]),
      formSubmissions: this.formBuilder.array(formReport?.formSubmissions),
      reportElements: this.formBuilder.array([]),
    });
  }

  dataURItoBlob(dataURI: string) {
    const byteString = window.atob(dataURI);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);
    for (let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i);
    }
    const blob = new Blob([int8Array], { type: 'image/png' });
    return blob;
  }

  private base64toBlob(base64Data: string, contentType: string): Blob {
    contentType = contentType || '';
    const sliceSize = 1024;
    const byteCharacters = atob(base64Data);
    const bytesLength = byteCharacters.length;
    const slicesCount = Math.ceil(bytesLength / sliceSize);
    const byteArrays = new Array(slicesCount);

    for (let sliceIndex = 0; sliceIndex < slicesCount; ++sliceIndex) {
      const begin = sliceIndex * sliceSize;
      const end = Math.min(begin + sliceSize, bytesLength);

      const bytes = new Array(end - begin);
      for (let offset = begin, i = 0; offset < end; ++i, ++offset) {
        bytes[i] = byteCharacters[offset].charCodeAt(0);
      }
      byteArrays[sliceIndex] = new Uint8Array(bytes);
    }
    return new Blob(byteArrays, { type: contentType });
  }

  downloadFormReport() {
    let d: any = {};
    d.formName = this.formName;
    d.reportElements = []

    this.reportElementComponents.forEach(elementComponent => {

      d.reportElements.push({
        charts: elementComponent.getChartImageData()
      });
    });

    // this.downloadFormReport1();
    this.reportController.createWordDocument(d).subscribe(file => {
      if (file) {

        let blob: any = file as Blob;
        const url = window.URL.createObjectURL(blob);
        saveAs(blob, `${d.formName}.docx`);
      }
    });
  }

  downloadFormReport1() {

    let value = this.formReportGroup.value;

    let d: any = {};
    let images: File[] = [];

    d.formName = value.formName;
    d.reportElements = []
    value.reportElements.forEach((element: any) => {

      let el: any = {
        charts: []
      }

      element.charts.forEach((ch: any) => {
        let sarr: string[] = ch.chartImage?.split(',')

        let bstr: string | undefined = atob(sarr[1])
        let n = bstr.length
        let u8arr = new Uint8Array(n);

        while(n--){
          u8arr[n] = bstr.charCodeAt(n);
        }

        const blob = new Blob([u8arr], { type: 'image/png' });

        const link = document.createElement("a");
        link.href = ch.chartImage;
        link.download = `x.png`
        link.click();

        let image: File = new File([blob], `${ch.chartType}Chart`);

        el.charts.push({
          caption: ch.chartCaption,
          label: ch.chartLabel,
          type: ch.chartType,
          imageIndex: images.length,
          t: ch.chartImage
        });

        images.push(image)
      });

      d.reportElements.push(el);
    });
  }
}
