import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {ShoppingCardService, ShoppingStoreService} from '../../services';

@Component({
  selector: 'app-purchase-page',
  templateUrl: './app-purchase-page.component.html',
  styleUrls: ['./app-purchase-page.component.css']
})
export class AppPurchasePageComponent {
  public totalPrice: number = null;

  public constructor(
    private router: Router,
    private shoppingStoreService: ShoppingStoreService,
    private shoppingCardService: ShoppingCardService
  ) {
    this.shoppingStoreService.getProducts().then(products => {
      this.totalPrice = products
        .map(product => {
          const price = product.priceInCents;
          const amount = this.shoppingCardService.getAmountOfProduct(product.id);

          // Bug here:
          // return price * amount;
          if (amount === 0) {
            return 0;
          } else {
            return price;
          }
        })
        .reduce((previousValue, currentValue) => previousValue + currentValue);
    });
  }
}
