package th.ac.ku.cakeRunner.model;

public class Cart {

    private Cakes cakes;

    private int quantity;


    public Cart(Cakes cakes, int quantity){
        this.cakes = cakes;
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public String toString() {
        return cakes.toString()+
                "->" + quantity + "}";
    }

    public Cakes getCakes(){
        return cakes;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCakes(Cakes cakes) {
        this.cakes = cakes;
    }
}
