import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Product} from '../data/product';

@Injectable()
export class ProductsHttp {
  private root = `http://localhost:8080/shopping_card_api/products`;

  public constructor(private http: HttpClient) {}

  public getAllProducts(): Promise<Array<Product>> {
    return this.http.get<Array<Product>>(this.root).toPromise();
  }
}
