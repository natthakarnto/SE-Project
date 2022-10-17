package th.ac.ku.cakeRunner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.cakeRunner.model.Cart;
import th.ac.ku.cakeRunner.model.Order;
import th.ac.ku.cakeRunner.model.Cakes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.lang.Integer.parseInt;

@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    private List<Order> cart = new ArrayList<>();

    public List<Order> getAll(){
        String url = "http://localhost:8090/order";
        ResponseEntity<Order[]> response = restTemplate.getForEntity(url, Order[].class);
        Order[] cakes = response.getBody();
        return Arrays.asList(cakes);
    }

    public void addOrder(Order cakes){
        String url = "http://localhost:8090/order";
        Order cakesOrder = new Order();
        cakesOrder = cakes;
        cakesOrder.setUsername(userService.getUser().getUsername());
        cakesOrder.setCakes(cartService.getCart().toString());
        cakesOrder.setStatus("Underpayment");
        restTemplate.postForObject(url, cakesOrder, Order.class);
    }

    public void OrderConfig(){
        cart = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            hee = this.getAll().get(i).getCakes();
            hee = hee.replace("[", "").replace("]","");
            hee = hee.replace("{","[").replace("}","]");
            hee = hee.replace("[","").replace("]","");
            String[] split = hee.split(",");
            List<String> list = Arrays.asList(split);
            for (int j = 0;j < list.size();j++){
                String hee2 =list.get(j);
                split = list.get(j).trim().split("->");
                List<String> list1 = Arrays.asList(split);
                Cakes cakes = new Cakes(UUID.fromString(list1.get(0)),list1.get(1),Double.parseDouble(list1.get(2)),Double.parseDouble(list1.get(3)),parseInt(list1.get(4)));
                cart.get(i).add(new Cart(cakes,parseInt(list1.get(5))));
            }
        }
    }
    public List<Order> getDummy(String name){ //getall
        this.OrderConfig();
        List<Order> cart2 = new ArrayList<>();
        if (name.equals("admin")){
            return cart;
        }
        else {
            for (int i =0; i<cart.size();i++){
                if (name.equals(cart.get(i).getUsername())){
                    cart2.add(cart.get(i));
                }
            }
        }
        return cart2;
    }
    public Order getDummyByID(UUID id){
        Order cakesOrder = new Order();
        for (int i=0;i < cart.size();i++){
            if (cart.get(i).getOrderID().equals(id)){
                cakesOrder = cart.get(i);
                return cakesOrder;
            }
        }
        return cakesOrder;
    }

    public Order getOneById(UUID id) {
        String url = "http://localhost:8090/order/" + id;
        ResponseEntity<Order> response =
                restTemplate.getForEntity(url, Order.class);
        Order cakes = response.getBody();
        return cakes;
    }
    public void update(Order cakes) {
        String url = "http://localhost:8090/order/" + cakes.getOrderID();
        restTemplate.put(url, cakes, Order.class);
    }
    public void delete(Order cakes) {
        String url = "http://localhost:8090/order/" + cakes.getOrderID();
        restTemplate.delete(url, cakes, Order.class);
    }
}
