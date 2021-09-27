package domain;

public class CandyFactory {
    public static Candy getCandy(String chocolateName ){
        switch (chocolateName) {
            case "Bertie Botts Every Flavor Beans":
                return (new CandyBuilder())
                        .withName("Bertie Botts Every Flavor Beans")
                        .withCost(1.0)
                        .withQuantity(50)
                        .build();

            case "Chocolate Frogs":
                return (new CandyBuilder())
                        .withName("Chocolate Frogs")
                        .withCost(2.5)
                        .withQuantity(50)
                        .build();
            case "Exploding Bon Bons":
                return (new CandyBuilder())
                        .withName("Exploding Bon Bons")
                        .withCost(5.0)
                        .withQuantity(50)
                        .build();

            case "Fudge Flies":
                return (new CandyBuilder())
                        .withName("Fudge Flies")
                        .withCost(6.5)
                        .withQuantity(50)
                        .build();

            default:
                return null;
        }
    }
}
