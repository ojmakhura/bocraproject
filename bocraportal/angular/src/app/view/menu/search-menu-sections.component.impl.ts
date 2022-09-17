// Generated by andromda-angular cartridge (view\view.component.imp.ts.vsl) CAN EDIT!
import { Component, Injector } from '@angular/core';
import { SearchMenuSectionsComponent } from '@app/view/menu/search-menu-sections.component';
import { SearchMenuSectionsSearchForm } from '@app/view/menu/search-menu-sections.component';
import { SearchMenuSectionsVarsForm } from '@app/view/menu/search-menu-sections.component';
import * as MenuSectionActions from '@app/store/menu/menu-section.actions';

@Component({
  selector: 'app-search-menu-sections',
  templateUrl: './search-menu-sections.component.html',
  styleUrls: ['./search-menu-sections.component.scss']
})
export class SearchMenuSectionsComponentImpl extends SearchMenuSectionsComponent {

    constructor(private injector: Injector) {
        super(injector);
    }

    override beforeOnInit(form: SearchMenuSectionsVarsForm): SearchMenuSectionsVarsForm{     
        return form;
    }

    doNgOnDestroy(): void {
    }

    override beforeSearchMenuSectionsSearch(form: SearchMenuSectionsSearchForm): void {
        this.store.dispatch(
            MenuSectionActions.search({
                criteria: form.criteria ? form.criteria : '',
                loading: true,
                loaderMessage: 'Searching menu sections ...'
            })
        );
    }
}