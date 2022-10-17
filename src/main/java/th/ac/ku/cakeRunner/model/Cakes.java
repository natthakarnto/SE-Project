package th.ac.ku.cakeRunner.model;

import java.util.UUID;

public class Cakes {

    private UUID id;
    private String name;
    private double weight;
    private double price;
    private int amount;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public Cakes(UUID id , String name , double weight, double price, int amount){
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{" +
                id +
                "->" + name +
                "->" + weight +
                "->" + price +
                "->" + amount ;
    }
}
