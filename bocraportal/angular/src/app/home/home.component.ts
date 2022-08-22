import { AfterViewInit, Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import * as SectorActions from '@app/store/sector/sector.actions';
import { SectorState } from '@app/store/sector/sector.state';
import * as SectorSelectors from '@app/store/sector/sector.selectors';
import { select, Store } from '@ngrx/store';
import { SectorVO } from '@app/model/bw/org/bocra/portal/sector/sector-vo';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, AfterViewInit, OnDestroy {

  quote: string | undefined;
  isLoading = false;

  sectorList: any;
  sectors$!: Observable<Array<SectorVO>>;
  protected store!: Store<SectorState>;


  constructor(public injector: Injector) {
    this.store = this.injector.get(Store);
    this.sectors$ = this.store.pipe(select(SectorSelectors.selectSectors));
  }

  ngAfterViewInit(): void {
    // console.log(3);
    // this.sectors$.subscribe(
    //   sectors => {
    //     this.sectorList = sectors;
    //     console.log(this.sectorList);
    //   }
    // );
    
  }

  ngOnDestroy(): void {
    throw new Error('Method not implemented.');
  }

  beforeOnInit(): void {
    // console.log(1);
    this.store.dispatch(
      SectorActions.getAll({ loading: true })
    );
  }

  ngOnInit() {
    this.store.dispatch(
      SectorActions.getAll({ loading: true })
    );
    console.log(2);
    
  }

}
