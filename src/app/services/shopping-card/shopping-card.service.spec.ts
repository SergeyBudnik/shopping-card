import {ShoppingCardService} from './shopping-card.service';

describe('test', () => {
  it('test1', () => {
    const shoppingCardService = new ShoppingCardService();

    shoppingCardService.addProduct('1');
    shoppingCardService.addProduct('1');
    shoppingCardService.addProduct('2');

    expect(shoppingCardService.getAmountOfProduct('1')).toBe(2);
    expect(shoppingCardService.getAmountOfProduct('2')).toBe(1);
  });
});
