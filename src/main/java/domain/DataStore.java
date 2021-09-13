package domain;

import java.io.Serializable;

public class DataStore implements Serializable {

    private int[] coinBank={50,50,50,50};
    private int[] candyBank={10,10,10,10};
    private double[] denomination={0.25,0.50,1,5};
    private double[] candyPrice={1,2.5,5,6.5};
    private String[] candyName={"beans","frogs","bonbons","flies"};


    public int[] getCoinBank() {
        return coinBank;
    }

    public void setCoinBank(int[] coinBank) {
        this.coinBank = coinBank;
    }

    public int[] getCandyBank() {
        return candyBank;
    }

    public void setCandyBank(int[] candyBank) {
        this.candyBank = candyBank;
    }

    public double[] getDenomination() {
        return denomination;
    }

    public void setDenomination(double[] denomination) {
        this.denomination = denomination;
    }

    public String[] getCandyName() {
        return candyName;
    }

    public void setCandyName(String[] candyName) {
        this.candyName = candyName;
    }

    public double[] getCandyPrice() {
        return candyPrice;
    }

    public void setCandyPrice(double[] candyPrice) {
        this.candyPrice = candyPrice;
    }
}
