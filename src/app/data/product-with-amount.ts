import {Product} from './product';

export class ProductWithAmount {
  public constructor(
    public product: Product,
    public amount: number
  ) {}
}
