<div class="card container">
  <div class="card-body">
    <form [formGroup]="reportElementGroup">
      <div>
        <div class="mb-3 row">
          <label for="report-type" class="col-sm-2 col-form-label">Report Type</label>
          <div class="col-sm-10">
            <select
              id="report-type"
              class="form-select"
              (change)="reportTypeChange()"
              formControlName="reportType"
              aria-label="Select report type"
            >
              <option></option>
              <option value="default">Default</option>
              <option value="custom">Custom</option>
            </select>
          </div>
        </div>
        <div *ngIf="reportType">
          <div class="mb-3" *ngIf="reportType === 'custom'">
            <div class="mb-3">
              <mat-tab-group animationDuration="0ms" class="view-tabs">
                <mat-tab label="{{ 'periods' | translate }}">
                  <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">Select</th>
                        <th scope="col">Periods</th>
                        <th scope="col">Alias</th>
                      </tr>
                    </thead>
                    <tbody formArrayName="periodSelections">
                      <tr *ngFor="let period of periodSelectionsArray.controls; let k = index" [formGroupName]="k">
                        <td scope="col">
                          <input
                            type="checkbox"
                            formControlName="selected"
                            aria-label="Checkbox for following text input"
                            (change)="periodSelectionChange($event, k)"
                          />
                        </td>
                        <td scope="col">
                          {{ period.value.period }}
                        </td>
                        <td scope="col">
                          <input
                            type="text"
                            class="form-control"
                            formControlName="alias"
                            (change)="periodAliasChange(period?.value, k)"
                          />
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </mat-tab>
                <mat-tab label="{{ 'licensees' | translate }}">
                  <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">Select</th>
                        <th scope="col">Licensee</th>
                      </tr>
                    </thead>
                    <tbody formArrayName="licenseeSelections">
                      <tr *ngFor="let licensee of licenseeSelectionsArray.controls; let j = index" [formGroupName]="j">
                        <td scope="col">
                          <input
                            type="checkbox"
                            formControlName="selected"
                            aria-label="Checkbox for following text input"
                            (change)="licenseeSelectionChange($event, j)"
                          />
                        </td>
                        <td scope="col">
                          {{ licensee.value.licensee }}
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </mat-tab>
                <mat-tab label="{{ 'data.fields' | translate }}">
                  <table class="table">
                    <thead>
                      <tr>
                        <th scope="col">Select</th>
                        <th scope="col">Field ID</th>
                        <th scope="col">Field Name</th>
                        <th scope="col">Alias</th>
                      </tr>
                    </thead>
                    <tbody formArrayName="fieldSelections">
                      <tr *ngFor="let fieldControl of fieldSelectionsArray.controls; let j = index" [formGroupName]="j">
                        <td scope="col">
                          <input
                            type="checkbox"
                            formControlName="selected"
                            aria-label="Checkbox for following text input"
                            (change)="fieldSelectionChange($event, j)"
                          />
                        </td>
                        <td scope="col">
                          {{ fieldControl?.value?.fieldId }}
                        </td>
                        <td scope="col">
                          {{ fieldControl?.value?.fieldName }}
                        </td>
                        <td scope="col">
                          <input
                            type="text"
                            class="form-control"
                            formControlName="alias"
                            (change)="fieldAliasChange($event, j)"
                          />
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </mat-tab>
              </mat-tab-group>
            </div>
          </div>

          <div class="mb-3 row">
            <label for="report-labels" class="col-sm-2 col-form-label">Columns</label>
            <div class="col-sm-10">
              <select
                id="report-labels"
                class="form-select"
                formControlName="dataColumns"
                aria-label="Default select example"
                (change)="createReportGrid()"
              >
                <option value="licensees">Licensees</option>
                <!-- <option value="periods">Periods</option> -->
                <option value="fields">Data Fields</option>
              </select>
            </div>
          </div>
          <div class="card mb-3">
            <table class="table" *ngIf="dataColumnsAnalyticsControl?.length > 0">
              <thead>
                <tr>
                  <th></th>
                  <th scope="col">Type</th>
                  <th scope="col">Tag</th>
                  <th scope="col">Analytic Name</th>
                  <th scope="col">Analytic Sources</th>
                </tr>
              </thead>
              <tbody formArrayName="dataColumnsAnalytics">
                <tr
                  *ngFor="let fieldControl of dataColumnsAnalyticsControl.controls; let j = index"
                  [formGroupName]="j"
                >
                  <td scope="col">
                    <button
                      type="button"
                      (click)="removeCustomColumns(j); dataColumnsAnalyticsControl.removeAt(j)"
                      class="btn btn-outline-primary"
                    >
                      <mat-icon>delete</mat-icon>
                    </button>
                  </td>
                  <td scope="col">
                    <div class="col-sm-10">
                      <select
                        id="report-labels"
                        class="form-select"
                        formControlName="type"
                        aria-label="Default select example"
                        (change)="additionalDataColumnChange(j)"
                      >
                        <option value="custom">Custom</option>
                        <option value="sum">Sum</option>
                        <option value="mean">Mean</option>
                        <option value="mode">Mode</option>
                        <option value="median">Median</option>
                        <option value="variance">Variance</option>
                        <option value="std">Standard Deviation</option>
                        <option value="min">Minimum</option>
                        <option value="max">Maximum</option>
                      </select>
                    </div>
                  </td>
                  <td scope="col">
                    <input type="text" class="form-control" formControlName="tag" />
                  </td>
                  <td scope="col">
                    <input
                      type="text"
                      class="form-control"
                      formControlName="name"
                      (change)="additionalDataColumnNameChange($event, j)"
                      *ngIf="fieldControl.value?.type"
                    />
                  </td>
                  <td scope="col">
                    <textarea
                      type="text"
                      class="form-control"
                      formControlName="sources"
                      (change)="additionalDataColumnChange(j)"
                      *ngIf="fieldControl.value?.name"
                    ></textarea>
                  </td>
                </tr>
              </tbody>
            </table>
            <div class="mb-3">
              <button type="button" class="btn btn-primary" (click)="addLabelsAnalytic('report')">Add</button>
            </div>
          </div>
          <div class="mb-3 row" *ngIf="dataColumns !== null">
            <label for="data-labels" class="col-sm-2 col-form-label">Rows</label>
            <div class="col-sm-10">
              <select
                id="data-labels"
                class="form-select"
                formControlName="dataRows"
                aria-label="Default select example"
                (change)="createReportGrid(); generateColors(true)"
              >
                <option></option>
                <option value="fields">Data Fields</option>
                <option value="licensees" *ngIf="dataColumns !== 'licensees'">Licensees</option>
                <!-- <option value="periods">Periods</option> -->
              </select>
            </div>
          </div>
        </div>

        <div class="card mb-3">
          <table class="table" *ngIf="dataRowsAnalyticsControl?.length > 0">
            <thead>
              <tr>
                <th></th>
                <th scope="col">Type</th>
                <th scope="col">Analytic Name</th>
                <th scope="col">Analytic Sources</th>
              </tr>
            </thead>
            <tbody formArrayName="dataRowsAnalytics">
              <tr *ngFor="let fieldControl of dataRowsAnalyticsControl.controls; let j = index" [formGroupName]="j">
                <td scope="col">
                  <button type="button" (click)="removeCustomRows(j)" class="btn btn-outline-primary">
                    <mat-icon>delete</mat-icon>
                  </button>
                </td>
                <td scope="col">
                  <div class="col-sm-10">
                    <select
                      id="report-labels"
                      class="form-select"
                      formControlName="type"
                      aria-label="Default select example"
                      (change)="additionalRowChange(j)"
                    >
                      <option value="custom">Custom</option>
                      <option value="sum">Sum</option>
                      <option value="mean">Mean</option>
                      <option value="mode">Mode</option>
                      <option value="median">Median</option>
                      <option value="variance">Variance</option>
                      <option value="std">Standard Deviation</option>
                      <option value="min">Minimum</option>
                      <option value="max">Maximum</option>
                    </select>
                  </div>
                </td>
                <td scope="col">
                  <input
                    type="text"
                    class="form-control"
                    formControlName="name"
                    (change)="additionalRowNameChange(j)"
                  />
                </td>
                <td scope="col">
                  <textarea
                    type="text"
                    class="form-control"
                    formControlName="sources"
                    (change)="additionalRowChange(j)"
                    *ngIf="fieldControl.value?.name"
                  ></textarea>
                </td>
              </tr>
            </tbody>
          </table>
          <div class="mb-3">
            <button type="button" class="btn btn-primary" (click)="addLabelsAnalytic('data')">Add</button>
          </div>
        </div>
        <div class="mb-3" *ngIf="this.chartsControl?.length > 0">
          <button type="button" class="btn btn-primary" (click)="refreshColors()">Colors</button>
        </div>
        <div class="accordion-body" *ngIf="gridDataPeriods.length > 0">
          <table
            mat-table
            matSort
            [dataSource]="gridDataSource"
            #gridSort="matSort"
            class="table table-bordered table-striped"
          >
            <ng-container matColumnDef="label">
              <th mat-header-cell *matHeaderCellDef [attr.rowspan]="2"></th>
              <td mat-cell *matCellDef="let data">{{ data.row }}: {{ data?.label }}</td>
            </ng-container>
            <ng-container *ngFor="let period of gridDataPeriods; let m = index" matColumnDef="{{ period }}">
              <th mat-header-cell *matHeaderCellDef [attr.colspan]="periodLengths[periods[m]?.name]">
                {{ periodAliases[periods[m]?.name] }}
              </th>
            </ng-container>
            <ng-container
              *ngFor="let ch of gridColumnHeaders; let m = index"
              matColumnDef="{{ ch.elementId }}_{{ ch.header }}"
            >
              <th mat-header-cell *matHeaderCellDef>{{ ch.label }} ({{ ch.header }})</th>
              <td mat-cell *matCellDef="let data">{{ data[ch?.header]?.value }}</td>
            </ng-container>

            <tr mat-header-row *matHeaderRowDef="gridDataPeriodHeaders"></tr>
            <tr mat-header-row *matHeaderRowDef="gridDataColumnHeaders"></tr>
            <tr mat-row *matRowDef="let row; columns: gridDataColDefs"></tr>
          </table>
          <mat-paginator
            #gridPaginator="matPaginator"
            [pageSize]="10"
            [pageSizeOptions]="[5, 10, 20, 30, 40, 50, 60, 70, 80, 90]"
            showFirstLastButtons
          ></mat-paginator>
        </div>
        <div *ngFor="let chartControl of chartsControl?.controls; let i = index" #test>
          <app-report-chart #reportChart
            [dataColumns]="dataColumns"
            [dataRows]="dataRows"
            [reportType]="reportType"
            [colors]="colors"
            [reportChartGroup]="chartControl"
            [selectedLicensees]="selectedLicensees"
            [selectedFields]="selectedFields"
            [selectedPeriods]="selectedPeriods"
            [chartIndex]="i"
            [grid]="grid"
          >
          </app-report-chart>
          <div class="mb-3">
            <button type="button" class="btn btn-info" (click)="removeReportChart(i)">
              <mat-icon>delete</mat-icon>
            </button>
          </div>
        </div>
        <div class="mb-3">
          <button type="button" class="btn bi bi-plus-circle" (click)="addReportChart()"></button>
        </div>
      </div>
    </form>
  </div>
</div>
