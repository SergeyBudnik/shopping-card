import {ModuleWithProviders, NgModule} from '@angular/core';
import {ShoppingCardService} from './shopping-card.service';

@NgModule()
export class ShoppingCardModule {
  static forRoot(): ModuleWithProviders<any> {
    return {
      ngModule: ShoppingCardModule,
      providers: [ ShoppingCardService ]
    };
  }
}
