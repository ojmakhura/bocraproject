import {
    Component,
    OnInit,
    AfterViewInit,
    Injector,
    ViewChild,
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UseCaseScope } from '@app/utils/use-case-scope';
import { ComplaintState } from '@app/store/complaint/complaint.state';
import { Store, select } from '@ngrx/store';
import * as ComplaintSelectors from '@app/store/complaint/complaint.selectors';
import { ComplaintVO } from '@app/model/bw/org/bocra/portal/complaint/complaint-vo';
import { Observable } from 'rxjs';
import { ComplaintSeachCriteria } from '@app/model/bw/org/bocra/portal/complaint/complaint-seach-criteria';
import { ChartConfiguration, ChartData, ChartEvent, ChartType, Color } from 'chart.js';
import { MatTableDataSource } from '@angular/material/table';
import DataLabelsPlugin from 'chartjs-plugin-datalabels';
import * as ComplaintActions from '@app/store/complaint/complaint.actions';
import { SectorVO } from '@app/model/bw/org/bocra/portal/sector/sector-vo';

@Component({
    selector: 'app-complaints-dashboard-base',
    template: '',
})

export abstract class ComplaintsDashboardComponent implements OnInit, AfterViewInit {

    reportLabel: string[] = [];
    reportData: number[] = [];

    reportLicenseesLabel: string[] = [];
    reportLicenseesData: number[] = [];

    reportTypeLabel: string[] = [];
    reportTypeData: number[] = [];

    reportStatusLabel: string[] = [];
    reportStatusData: number[] = [];

    reportSectorLabel: string[] = [];
    reportSectorData: number[] = [];

    yearFilter: string[] = [];
    licenseeFilter: string[] = [];
    typeFilter: string[] = [];
    statusFilter: string[] = [];
    sectorFilter: string[] = [];
    sectorNameFilter: string[] = [];
    year = new Date().getFullYear();

    complaints$: Observable<Array<ComplaintVO>>;
    complaintsDataSource = new Array<ComplaintVO>;
    complaintsFiltered = new Array<ComplaintVO>;
    protected route: ActivatedRoute;
    protected router: Router;
    protected useCaseScope: UseCaseScope;
    protected store: Store<ComplaintState>;
    protected _injector: Injector;
    loggedInSearch: ComplaintSeachCriteria | any;

    constructor(injector: Injector) {
        this.route = injector.get(ActivatedRoute);
        this.router = injector.get(Router);
        this.useCaseScope = injector.get(UseCaseScope);
        this.store = injector.get(Store);
        this._injector = injector;
        this.complaints$ = this.store.pipe(select(ComplaintSelectors.selectComplaints));
    }

    ngOnInit() {

        this.store.dispatch(
            ComplaintActions.search({
                criteria: { status: null, surname: null, email: null, subject: null, complaintId: null, licenseeName: null, complaintType: null },
                loading: true,
                loaderMessage: 'Searching complaints ...',
            })
        );
        this.ngAfterViewInit();
    }

    ngAfterViewInit(): void {
        this.complaints$?.subscribe((data) => {
            this.complaintsDataSource = data;
            this.doNgAfterViewInit();
        });

    }

    doNgAfterViewInit(): void {
        if (this.complaintsDataSource.length > 0) {
            for(let complaint of this.complaintsDataSource){
                if(complaint.createdDate[0] === this.year){
                    this.complaintsFiltered.push(complaint);
                }
            }
            this.useCaseScope.pageVariables = this.complaintsFiltered;
            if (this.useCaseScope.pageVariables.length > 0) {
                this.licenseeFilter.push(
                    this.useCaseScope.pageVariables.map((entry: { licensee: { licenseeName: any } }) => entry.licensee.licenseeName)
                );
                this.reportLicenseesLabel = [...new Set(this.licenseeFilter[0])];
                for (let licensee of this.reportLicenseesLabel) {
                    let data = this.useCaseScope.pageVariables.filter((entry: { licensee: { licenseeName: string | string[] } }) =>
                        entry.licensee.licenseeName.includes(licensee)
                    ).length;
                    this.reportLicenseesData.push(data);
                }

                this.barChartLicenseeData.datasets = [
                    {
                        data: this.reportLicenseesData,
                        label: 'Complaints Per Licensee Analysis',
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.5)',
                            'rgba(255, 159, 64, 0.5)',
                            'rgba(54, 162, 235, 0.5)',
                            'rgba(153, 102, 255, 0.5)',
                            'rgba(201, 203, 207, 0.5)',
                            'rgba(0, 172, 230, 0.5)',
                            'rgba(230, 0, 115, 0.5)',
                            'rgba(255, 51, 102, 0.5)',
                            'rgba(255, 51, 204, 0.5)',
                            'rgba(255, 140, 26, 0.5)',
                            'rgba(255, 140, 102, 0.5)',
                            'rgba(0, 204, 102, 0.5)',
                        ],
                        borderColor: [
                            'rgba(255, 99, 132)',
                            'rgba(255, 159, 64)',
                            'rgba(54, 162, 235)',
                            'rgba(153, 102, 255)',
                            'rgba(201, 203, 207)',
                            'rgba(0, 172, 230)',
                            'rgba(230, 0, 115)',
                            'rgba(255, 51, 102)',
                            'rgba(255, 51, 204)',
                            'rgba(255, 140, 26)',
                            'rgba(255, 140, 102)',
                            'rgba(0, 204, 102)',
                        ],
                    },
                ];
                // this.pieChartLicenseeData.datasets = [{ data: this.reportLicenseesData, label: 'Licensee Complaints Analysis' }];

                this.barChartLicenseeData.labels = this.reportLicenseesLabel;
                // this.pieChartLicenseeData.labels = this.reportLicenseesLabel;

                this.typeFilter.push(
                    this.useCaseScope.pageVariables.map((entry: { complaintType: { typeName: any } }) => entry.complaintType.typeName)
                );
                this.reportTypeLabel = [...new Set(this.typeFilter[0])];

                for (let type of this.reportTypeLabel) {
                    let data = this.useCaseScope.pageVariables.filter((entry: { complaintType: { typeName: any } }) =>
                        entry.complaintType.typeName.includes(type)
                    ).length;
                    this.reportTypeData.push(data);
                }

                this.barChartTypeData.datasets = [
                    {
                        data: this.reportTypeData,
                        label: 'Complaints Per Complaint Type Analysis',
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.5)',
                            'rgba(255, 159, 64, 0.5)',
                            'rgba(54, 162, 235, 0.5)',
                            'rgba(153, 102, 255, 0.5)',
                            'rgba(201, 203, 207, 0.5)',
                            'rgba(0, 172, 230, 0.5)',
                            'rgba(230, 0, 115, 0.5)',
                            'rgba(255, 51, 102, 0.5)',
                            'rgba(255, 51, 204, 0.5)',
                            'rgba(255, 140, 26, 0.5)',
                            'rgba(255, 140, 102, 0.5)',
                            'rgba(0, 204, 102, 0.5)',
                        ],
                        borderColor: [
                            'rgba(255, 99, 132)',
                            'rgba(255, 159, 64)',
                            'rgba(54, 162, 235)',
                            'rgba(153, 102, 255)',
                            'rgba(201, 203, 207)',
                            'rgba(0, 172, 230)',
                            'rgba(230, 0, 115)',
                            'rgba(255, 51, 102)',
                            'rgba(255, 51, 204)',
                            'rgba(255, 140, 26)',
                            'rgba(255, 140, 102)',
                            'rgba(0, 204, 102)',
                        ],
                    },
                ];
                // this.pieChartTypeData.datasets = [{ data: this.reportTypeData, label: 'Complaint Type Complaints Analysis' }];

                this.barChartTypeData.labels = this.reportTypeLabel;
                // this.pieChartTypeData.labels = this.reportTypeLabel;

                this.statusFilter.push(
                    this.useCaseScope.pageVariables.map((entry: { status: string }) => entry.status)
                );
                this.reportStatusLabel = [...new Set(this.statusFilter[0])];
                for (let status of this.reportStatusLabel) {
                    let data = this.useCaseScope.pageVariables.filter((entry: { status: string | string[] }) =>
                        entry.status.includes(status)).length;
                    this.reportStatusData.push(data);
                }

                this.barChartStatusData.datasets = [
                    {
                        data: this.reportStatusData,
                        label: 'Complaints Per Status Analysis',
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.5)',
                            'rgba(255, 159, 64, 0.5)',
                            'rgba(54, 162, 235, 0.5)',
                            'rgba(153, 102, 255, 0.5)',
                            'rgba(201, 203, 207, 0.5)',
                            'rgba(0, 172, 230, 0.5)',
                            'rgba(230, 0, 115, 0.5)',
                            'rgba(255, 51, 102, 0.5)',
                            'rgba(255, 51, 204, 0.5)',
                            'rgba(255, 140, 26, 0.5)',
                            'rgba(255, 140, 102, 0.5)',
                            'rgba(0, 204, 102, 0.5)',
                        ],
                        borderColor: [
                            'rgba(255, 99, 132)',
                            'rgba(255, 159, 64)',
                            'rgba(54, 162, 235)',
                            'rgba(153, 102, 255)',
                            'rgba(201, 203, 207)',
                            'rgba(0, 172, 230)',
                            'rgba(230, 0, 115)',
                            'rgba(255, 51, 102)',
                            'rgba(255, 51, 204)',
                            'rgba(255, 140, 26)',
                            'rgba(255, 140, 102)',
                            'rgba(0, 204, 102)',
                        ],
                    },
                ];
                // this.pieChartStatusData.datasets = [{ data: this.reportStatusData, label: 'Complaints Report Analysis' }];

                this.barChartStatusData.labels = this.reportStatusLabel;
                // this.pieChartStatusData.labels = this.reportStatusLabel;
                for (let complaint of this.useCaseScope.pageVariables) {
                    for (let sector of complaint.licensee.sectors) {
                        this.sectorFilter.push(sector.sector.name);
                    }
                }
                this.reportSectorLabel = [...new Set(this.sectorFilter)];

                for (let sectorName of this.reportSectorLabel) {
                    let total = 0;
                    for (let complaint of this.useCaseScope.pageVariables) {
                        for (let sector of complaint.licensee.sectors) {
                            if (sector.sector.name.includes(sectorName)) {
                                total = total + 1;
                            }
                        }
                    }
                    this.reportSectorData.push(total);
                }

                this.barChartSectorData.datasets = [
                    {
                        data: this.reportSectorData,
                        label: 'Complaints Per Sector Analysis',
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.5)',
                            'rgba(255, 159, 64, 0.5)',
                            // 'rgba(75, 192, 192, 0.5)',
                            'rgba(54, 162, 235, 0.5)',
                            'rgba(153, 102, 255, 0.5)',
                            'rgba(201, 203, 207, 0.5)',
                            'rgba(0, 172, 230, 0.5)',
                            'rgba(230, 0, 115, 0.5)',
                            'rgba(255, 51, 102, 0.5)',
                            'rgba(255, 51, 204, 0.5)',
                            'rgba(255, 140, 26, 0.5)',
                            'rgba(255, 140, 102, 0.5)',
                            'rgba(0, 204, 102, 0.5)',
                        ],
                        borderColor: [
                            'rgba(255, 99, 132)',
                            'rgba(255, 159, 64)',
                            // 'rgba(75, 192, 192)',
                            'rgba(54, 162, 235)',
                            'rgba(153, 102, 255)',
                            'rgba(201, 203, 207)',
                            'rgba(0, 172, 230)',
                            'rgba(230, 0, 115)',
                            'rgba(255, 51, 102)',
                            'rgba(255, 51, 204)',
                            'rgba(255, 140, 26)',
                            'rgba(255, 140, 102)',
                            'rgba(0, 204, 102)',
                        ],
                    },
                ];
                // this.pieChartSectorData.datasets = [{ data: this.reportSectorData, label: 'Sector Complaints Analysis' }];

                this.barChartSectorData.labels = this.reportSectorLabel;
                // this.pieChartSectorData.labels = this.reportLicenseesLabel;
            }
        }
    }

    public barChartOptions: ChartConfiguration['options'] = {
        responsive: true,
        // We use these empty structures as placeholders for dynamic theming.
        scales: {
            x: {},
            y: {
                min: 0,
            },
        },
        plugins: {
            legend: {
                display: true,
            },
            datalabels: {
                anchor: 'center',
                align: 'start',
            },
        },
    };
    public barChartType: ChartType = 'bar';
    public barChartPlugins = [DataLabelsPlugin];

    public barChartData: ChartData<'bar'> = {
        labels: [],
        datasets: [],
    };

    public barChartLicenseeData: ChartData<'bar'> = {
        labels: [],
        datasets: [],
    };

    public barChartTypeData: ChartData<'bar'> = {
        labels: [],
        datasets: [],
    };

    public barChartStatusData: ChartData<'bar'> = {
        labels: [],
        datasets: [],
    };

    public barChartSectorData: ChartData<'bar'> = {
        labels: [],
        datasets: [],
    };

    // events
    public chartClicked({ event, active }: { event?: ChartEvent; active?: {}[] }): void {
        console.log(event, active);
    }

    public chartHovered({ event, active }: { event?: ChartEvent; active?: {}[] }): void {
        console.log(event, active);
    }

}