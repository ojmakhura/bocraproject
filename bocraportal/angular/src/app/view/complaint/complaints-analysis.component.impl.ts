// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { ComplaintsAnalysisComponent } from '@app/view/complaint/complaints-analysis.component';
import { ComplaintState } from '@app/store/complaint/complaint.state';
import * as ComplaintSelectors from '@app/store/complaint/complaint.selectors';
import * as ComplaintActions from '@app/store/complaint/complaint.actions';
import { MatRadioChange } from '@angular/material/radio';
import { MatCheckboxChange } from '@angular/material/checkbox';

@Component({
  selector: 'app-complaints-analysis',
  templateUrl: './complaints-analysis.component.html',
  styleUrls: ['./complaints-analysis.component.scss']
})
export class ComplaintsAnalysisComponentImpl extends ComplaintsAnalysisComponent {

  constructor(private injector: Injector) {
    super(injector);
  }
  yearFilter: string[] = [];
  licenseeFilter: string[] = [];
  typeFilter: string[] = [];
  override doNgAfterViewInit(): void {
    this.yearFilter.push(this.useCaseScope.pageVariables.map((entry: { createdDate: string; }) => entry.createdDate.substring(0, 4)));
    this.reportLabel = [...new Set(this.yearFilter[0])];

    for (let year of this.reportLabel) {
      let data = this.useCaseScope.pageVariables.filter((entry: { createdDate: string | string[]; }) => entry.createdDate.includes(year)).length;
      this.reportData.push(data);
    }

    this.barChartData.datasets = [{ data: this.reportData, label: 'Complaints Report Analysis' }];
    this.pieChartData.datasets = [{ data: this.reportData, label: 'Complaints Report Analysis' }];

    this.barChartData.labels = this.reportLabel;
    this.pieChartData.labels = this.reportLabel;

    this.licenseeFilter.push(this.useCaseScope.pageVariables.map((entry: { licensee: { licenseeName: any; }; }) => entry.licensee.licenseeName));
    this.reportLicenseesLabel = [...new Set(this.licenseeFilter[0])];
    for (let licensee of this.reportLicenseesLabel){
      let data = this.useCaseScope.pageVariables.filter((entry: { licensee: { licenseeName: string | string[]; }; }) => entry.licensee.licenseeName.includes(licensee)).length;
      this.reportLicenseesData.push(data);
    }

    this.barChartLicenseeData.datasets = [{ data: this.reportLicenseesData, label: 'Licensee Complaints Analysis'}];
    this.pieChartLicenseeData.datasets = [{ data: this.reportLicenseesData, label: 'Licensee Complaints Analysis'}];
    this.barChartLicenseeData.labels = this.reportLicenseesLabel;
    this.pieChartLicenseeData.labels = this.reportLicenseesLabel;
  }

  doNgOnDestroy(): void {
  }
}