package domain;

public class CandyBuilder {
    private String name;
    private double cost;
    private int quantity;

    static Candy candy;

    public CandyBuilder withName(String name){
        this.name=name;
        return this ;
    }
    public CandyBuilder withCost(double cost){
        this.cost=cost;
        return this ;
    }
    public CandyBuilder withQuantity(int quantity){
        this.quantity=quantity;
        return this ;
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

    public static Candy getCandy() {
        return candy;
    }

    public static void setCandy(Candy candy) {
        CandyBuilder.candy = candy;
    }
    public Candy build(){
        return new Candy(this);
    }
}
