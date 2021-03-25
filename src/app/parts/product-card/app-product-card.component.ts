import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ProductWithAmount} from '../../data/product-with-amount';

@Component({
  selector: 'app-product-card',
  templateUrl: './app-product-card.component.html',
  styleUrls: ['./app-product-card.component.css']
})
export class AppProductCardComponent {
  @Input() public productWithAmount: ProductWithAmount;

  @Output('addProduct') public addProductEmitter = new EventEmitter<string>();
  @Output('removeProduct') public removeProductEmitter = new EventEmitter<string>();

  public addProduct(): void {
    this.addProductEmitter.emit(this.productWithAmount.product.id);
  }

  public removeProduct(): void {
    this.removeProductEmitter.emit(this.productWithAmount.product.id);
  }
}
