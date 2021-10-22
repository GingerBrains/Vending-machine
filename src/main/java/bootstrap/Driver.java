package bootstrap;

import domain.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Driver {
    static Logger logger = LoggerFactory.getLogger(Driver.class);
    public static void main(String[] args) throws Exception{

        configureLogging("candyLogs.log","INFO");

        DataStore dataStore = new DataStore();
        Processor processor = new Processor();

        dataStore.candy[0] = CandyFactory.getCandy("Bertie Botts Every Flavor Beans");
        dataStore.candy[1] = CandyFactory.getCandy("Chocolate Frogs");
        dataStore.candy[2] = CandyFactory.getCandy("Exploding Bon Bons");
        dataStore.candy[3] = CandyFactory.getCandy("Fudge Flies");

        //serialize read
        File g = new File("DataStore.dat");
        if (g.exists()) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("DataStore.dat"));
            dataStore = (DataStore) objectInputStream.readObject();
        }


        while (true) {
            try{
            System.out.println("########Candy List########\n" +
                    "1.Bertie Botts Every Flavor Beans->1$\n" +
                    "2.Chocolate Frogs->2.5$\n" +
                    "3.Exploding Bon Bons->5$\n" +
                    "4.Fudge Flies->6.5$\n" +
                    "Insert Money...");
            Scanner sc = new Scanner(System.in);
            double inputMoney = sc.nextDouble();

            System.out.println("Enter your choice number: ");
            int choice = sc.nextInt() - 1;

            if(choice==-1){
                System.exit(0);
            }

            double change = inputMoney - dataStore.candy[choice].getCost();
            int[] changeToGive = processor.checkChange(change, dataStore.getCoinBank());
            if (inputMoney >= dataStore.candy[choice].getCost()) {
                if (dataStore.candy[choice].getQuantity() != 0) {
                    if (changeToGive[0] != -9 && changeToGive[1] != -9 && changeToGive[2] != -9 && changeToGive[3] != -9) {
                        //add money
                        dataStore.setCoinBank(processor.addArray(dataStore.getCoinBank(), processor.breakMoney(inputMoney)));


                        // remove candy from machine
                        dataStore.candy[choice].setQuantity(dataStore.candy[choice].getQuantity()- 1);
                        System.out.println("Here is your " + dataStore.candy[choice].getName());


                        //give back change
                        System.out.println("Here is Your change :\n" +
                                changeToGive[0] + "\t5 dollar notes\n" +
                                changeToGive[1] + "\t1 dollar notes\n" +
                                changeToGive[2] + "\t50 cent coins\n" +
                                changeToGive[3] + "\t25 cent coins\n");
                        dataStore.setCoinBank(processor.subArray(dataStore.getCoinBank(), changeToGive));


                        //write logs
                        logger.info(dataStore.candy[choice].getName() + " was given");

                    } else {
                        System.out.println("Not enough Change left in Machine to process Transaction ");
                        processor.giveAllMoneyBack(inputMoney);
                        logger.info(dataStore.candy[choice].getName() + "\tnot given");

                    }
                } else {
                    System.out.println(dataStore.candy[choice].getName() + " is Out of Stock");
                    processor.giveAllMoneyBack(inputMoney);
                    logger.info(dataStore.candy[choice].getName() + "\tnot given");

                }
            } else {
                System.out.println("Not enough Money Deposited to Buy candy");
                processor.giveAllMoneyBack(inputMoney);
                logger.info(dataStore.candy[choice].getName() + "\tnot given");

            }
            //serialize write
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("DataStore.dat"));
            objectOutputStream.writeObject(dataStore);
            }catch (IOException e) {
                logger.error("There was an error " + e);
                System.out.println("ERROR");
            }catch (InputMismatchException m){
                logger.error("There was an error 1" + m);
                System.out.println("ERROR");
            }
        }
    }

    public static String configureLogging(String logFile,String logLevel){
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();

        String logFilename="";
        switch(logLevel){
            case "DEBUG":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            }
            case "WARN":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.WARN_INT));
            }
            case "ERROR":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.ERROR_INT));
            }
            default:{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
            }
            break;
        }
        System.out.println("Log files written out at " + logFile);
        dailyRollingFileAppender.setFile(logFile);
        dailyRollingFileAppender.setLayout(new EnhancedPatternLayout("%d [%t] %-5p %c - %m%n"));

        dailyRollingFileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(dailyRollingFileAppender);
        return dailyRollingFileAppender.getFile();
    }

}
