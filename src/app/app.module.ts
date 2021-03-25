import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';

import * as Pages from './pages';
import * as Parts from './parts';
import * as Services from './services';
import * as Http from './http';
import {HttpClientModule} from '@angular/common/http';

const appRoutes: Routes = [
  { path: 'list', component: Pages.AppListPageComponent },
  { path: 'checkout', component: Pages.AppCheckoutPageComponent },
  { path: 'purchase', component: Pages.AppPurchasePageComponent },

  { path: '**', component: Pages.AppListPageComponent }
];

@NgModule({
  declarations: [
    AppComponent,

    Pages.AppListPageComponent,
    Pages.AppCheckoutPageComponent,
    Pages.AppPurchasePageComponent,

    Parts.AppProductCardComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,

    RouterModule.forRoot(appRoutes, {
      useHash: true
    }),

    Services.ShoppingCardModule.forRoot()
  ],
  providers: [
    Services.ShoppingStoreService,

    Http.ProductsHttp
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
