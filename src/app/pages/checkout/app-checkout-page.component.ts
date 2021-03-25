import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {ShoppingCardService, ShoppingStoreService} from '../../services';
import {ProductWithAmount} from '../../data/product-with-amount';
import {Product} from '../../data/product';

@Component({
  selector: 'app-checkout-page',
  templateUrl: './app-checkout-page.component.html',
  styleUrls: ['./app-checkout-page.component.css']
})
export class AppCheckoutPageComponent {
  public productsWithAmounts: Array<ProductWithAmount> = [];

  private products: Array<Product> = [];

  public constructor(
    private router: Router,
    private shoppingStoreService: ShoppingStoreService,
    private shoppingCardService: ShoppingCardService
  ) {
    this.shoppingStoreService.getProducts().then(products => {
      this.products = products;
      this.productsWithAmounts = this.buildProducts();
    });
  }

  public addProduct(productId: string): void {
    this.shoppingCardService.addProduct(productId);

    this.productsWithAmounts = this.buildProducts();
  }

  public removeProduct(productId: string): void {
    this.shoppingCardService.removeProduct(productId);

    this.productsWithAmounts = this.buildProducts();
  }

  public purchase(): void {
    this.router.navigate(['/purchase']).then(() => {});
  }

  private buildProducts(): Array<ProductWithAmount> {
    return this.products
      .map(product => new ProductWithAmount(
        product,
        this.shoppingCardService.getAmountOfProduct(product.id)
      ))
      .filter(productWithAmount => productWithAmount.amount != 0);
  }
}
