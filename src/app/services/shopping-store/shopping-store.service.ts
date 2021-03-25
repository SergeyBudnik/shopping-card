import {Injectable} from '@angular/core';
import {Product} from '../../data/product';
import {ProductsHttp} from '../../http/products.http';

@Injectable()
export class ShoppingStoreService {
  public constructor(
    private productsHttp: ProductsHttp
  ) {}

  public getProducts(): Promise<Array<Product>> {
    return this.productsHttp.getAllProducts();
  }
}
