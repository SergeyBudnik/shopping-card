import {Component} from '@angular/core';
import {ShoppingCardService, ShoppingStoreService} from '../../services';
import {Router} from '@angular/router';
import {ProductWithAmount} from '../../data/product-with-amount';
import {Product} from '../../data/product';

@Component({
  selector: 'app-list-page',
  templateUrl: './app-list-page.component.html',
  styleUrls: ['./app-list-page.component.css']
})
export class AppListPageComponent {
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

  public checkout(): void {
    this.router.navigate(['/checkout']).then(() => {});
  }

  private buildProducts(): Array<ProductWithAmount> {
    return this.products
      .map(product => new ProductWithAmount(
        product,
        this.shoppingCardService.getAmountOfProduct(product.id)
      ));
  }
}
