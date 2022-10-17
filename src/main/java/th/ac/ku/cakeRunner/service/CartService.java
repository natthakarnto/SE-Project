package th.ac.ku.cakeRunner.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.cakeRunner.model.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class CartService {
    @Autowired
    private CakesService cakesService;

    private List<Cart> cart = new ArrayList<>();

    public void addCart(UUID id,Integer num){
        cart.add(new Cart(cakesService.getOneById(id), num));
    }
    public List<Cart> getCart() {
        return cart;
    }

    public void removeCart(UUID id){
        for(int i = 0 ; i < cart.size(); i++){
            if (id.equals(cart.get(i).getCakes().getId())){
                cart.remove(i);
            }

        }
    }

    public int priceCalculate(){
        int total = 0;
        for (int i = 0 ; i< cart.size(); i++){
            total += (cart.get(i).getQuantity() * cart.get(i).getCakes().getPrice());
        }
        return total;
    }
    public void removeall(){
        cart = new ArrayList<>();
    }

}
