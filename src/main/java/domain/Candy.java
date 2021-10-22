package domain;

import java.io.Serializable;

public class Candy implements Serializable {
    private String name;
    private double cost;
    private int quantity;

    public Candy(CandyBuilder candyBuilder){
        this.name=candyBuilder.getName();
        this.cost=candyBuilder.getCost();
        this.quantity=candyBuilder.getQuantity();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
