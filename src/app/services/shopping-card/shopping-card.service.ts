import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCardService {
  private products: Map<string, number> = new Map<string, number>();

  public addProduct(productId: string): void {
    const oldAmountOfProducts = this.getAmountOfProduct(productId);

    this.products.set(productId, oldAmountOfProducts + 1);
  }

  public removeProduct(productId: string): void {
    const oldAmountOfProducts = this.getAmountOfProduct(productId);

    if (oldAmountOfProducts <= 1) {
      this.products.delete(productId);
    } else {
      this.products.set(productId, oldAmountOfProducts - 1);
    }
  }

  public getAmountOfProduct(productId: string): number {
    const amount = this.products.get(productId);

    if (!amount) {
      return 0;
    } else {
      return amount;
    }
  }
}
