package bootstrap;

import domain.*;
import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws Exception{

        DataStore dataStore = new DataStore();
        Logger logger = new Logger();
        Processor processor = new Processor();

        File g = new File("DataStore.dat");
        if(g.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("DataStore.dat"));
            dataStore = (DataStore) objectInputStream.readObject();
        }


        while(true) {
            System.out.println("########Candy List########\n" +
                    "1.Bertie Botts Every Flavor Beans->1$\n" +
                    "2.Chocolate Frogs->2.5$\n" +
                    "3.Exploding Bon Bons->5$\n" +
                    "4.Fudge Flies->6.5$\n" +
                    "Insert Money...");
            Scanner sc = new Scanner(System.in);
            double inputMoney = sc.nextDouble();
            try {

                System.out.println("Enter your choice number: ");
                int choice = sc.nextInt() - 1;
                double change = inputMoney - dataStore.getCandyPrice()[choice];
                int[] changeToGive = processor.checkChange(change, dataStore.getCoinBank());
                if (inputMoney > dataStore.getCandyPrice()[choice]) {
                    if (dataStore.getCandyBank()[choice] != 0) {
                        if (changeToGive[0] != 0 && changeToGive[1] != 0 && changeToGive[2] != 0 && changeToGive[3] != 0) {


                            //add candy cost to bank
                            dataStore.setCoinBank(processor.addArray(dataStore.getCoinBank(), processor.breakMoney(dataStore.getCandyPrice()[choice])));


                            // remove candy from machine
                            int[] newCandyBank = dataStore.getCandyBank();
                            newCandyBank[choice] -= 1;
                            dataStore.setCandyBank(newCandyBank);
                            System.out.println("Here is your\t" + dataStore.getCandyName()[choice]);


                            //give back change
                            System.out.println("Here is Your change :\n" +
                                    changeToGive[0] + "\t5 dollar notes\n" +
                                    changeToGive[1] + "\t1 dollar notes\n" +
                                    changeToGive[2] + "\t50 cent coins\n" +
                                    changeToGive[3] + "\t25 cent coins\n");


                            //write logs
                            logger.logs(dataStore.getCandyName()[choice] + " Given\n");
                        } else {
                            System.out.println("Not enough Change left in Machine to process Transaction ");
                            System.out.println("Here is Your money :\n" +
                                    processor.breakMoney(inputMoney)[0] + "\t5 dollar notes\n" +
                                    processor.breakMoney(inputMoney)[1] + "\t1 dollar notes\n" +
                                    processor.breakMoney(inputMoney)[2] + "\t50 cent coins\n" +
                                    processor.breakMoney(inputMoney)[3] + "\t25 cent coins\n");
                            logger.logs(dataStore.getCandyName()[choice] + " Not Given due to insufficient funds\n");
                        }
                    } else {
                        System.out.println(dataStore.getCandyName()[choice] + "is Out of Stock");
                        System.out.println("Here is Your money :\n" +
                                processor.breakMoney(inputMoney)[0] + "\t5 dollar notes\n" +
                                processor.breakMoney(inputMoney)[1] + "\t1 dollar notes\n" +
                                processor.breakMoney(inputMoney)[2] + "\t50 cent coins\n" +
                                processor.breakMoney(inputMoney)[3] + "\t25 cent coins\n");
                        logger.logs(dataStore.getCandyName()[choice] + " Not Given due to low stock\n");
                    }
                } else {
                    System.out.println("Not enough Money Deposited to Buy candy");
                    System.out.println("Here is Your money :\n" +
                            processor.breakMoney(inputMoney)[0] + "\t5 dollar notes\n" +
                            processor.breakMoney(inputMoney)[1] + "\t1 dollar notes\n" +
                            processor.breakMoney(inputMoney)[2] + "\t50 cent coins\n" +
                            processor.breakMoney(inputMoney)[3] + "\t25 cent coins\n");
                    logger.logs(dataStore.getCandyName()[choice] + " Not Given due to insufficient funds deposited\n");
                }
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("DataStore.dat"));
                objectOutputStream.writeObject(dataStore);
            }catch (Exception e){
                logger.fatal("Oops",e);
            }
        }
    }

}
