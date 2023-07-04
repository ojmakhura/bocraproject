// Generated by andromda-jsf cartridge (view\table\table.component.impl.ts.vsl)
import { Component, Injector } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { AccessPointVO } from '@app/model/bw/org/bocra/portal/access/access-point-vo';
import { DataPage } from '@app/model/bw/org/bocra/portal/data-page';
import { AccessPointRestController } from '@app/service/bw/org/bocra/portal/access/access-point-rest-controller';
import { SearchAccessPointsAccessPointsComponent } from '@app/view/access/search-access-points-access-points.component';
import { Observable, map } from 'rxjs';
import * as AccessPointSelectors from '@app/store/access/access-point.selectors';
import * as AccessPointActions from '@app/store/access/access-point.actions';
import { select } from '@ngrx/store';
import { AccessPointCriteria } from '@app/model/bw/org/bocra/portal/access/access-point-criteria';

@Component({
  selector: 'search-access-points-access-points',
  templateUrl: './search-access-points-access-points.component.html',
  styleUrls: ['./search-access-points-access-points.component.scss'],
})
export class SearchAccessPointsAccessPointsComponentImpl extends SearchAccessPointsAccessPointsComponent {

  accessPointPage$: Observable<DataPage>;
  criteria: AccessPointCriteria = new AccessPointCriteria();

  constructor(private injector: Injector, private accessPointRestController: AccessPointRestController) {
    super(injector);
    this.accessPointPage$ = this.store.pipe(select(AccessPointSelectors.selectAccessPointPage));
  }

  override doSearchAccessPointsEdit(form: any): any {
    return form;
  }

  override ngAfterViewInit() {
    this.accessPointsPaginator.page.subscribe((paginator) => {
      this.store.dispatch(
        AccessPointActions.pagedSearch({
          pageNumber: paginator.pageIndex + 1,
          pageSize: paginator.pageSize,
          criteria: this.criteria,
          loading: true,
          loaderMessage: 'Searching access points ...',
        })
      );
    });

    this.accessPointPage$
      .pipe(
        map((dataPage) => {
          if (dataPage == null) return [];
          this.totalElements = dataPage['totalElements'];
          return dataPage['elements'] as AccessPointVO[];
        })
      )
      .subscribe((pageData) => {
        
        this.accessPointsDataSource = new MatTableDataSource(pageData);
      });
  }
}
