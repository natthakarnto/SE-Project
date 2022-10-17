package th.ac.ku.cakeRunner.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID orderID;
    private Date date;
    private String name;
    private String surname;
    private String mobile;
    private String address;
    private String cakes;
    private List<Cart> cartList = new ArrayList<>();
    private int amount;
    private String username;
    private double price;
    private String status;
    private Date payment;

    public List<Cart> getCartList(){
        return cartList;
    }

    public void add(Cart cartList){
        this.cartList.add(cartList);
    }

    public UUID getOrderID() {
        return orderID;
    }

    public void setOrderID(UUID orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCakes() {
        return cakes;
    }

    public void setCakes(String cakes) {
        this.cakes = cakes;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getPayment() {
        return payment;
    }

    public void setPayment(Date payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", cakes='" + cakes + '\'' +
                ", cartList=" + cartList +
                ", amount=" + amount +
                ", username='" + username + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", payment=" + payment +
                '}';
    }
}
