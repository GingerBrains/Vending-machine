package bootstrap;

import domain.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.*;
import org.slf4j.*;
import org.slf4j.Logger;


public class Driver {
    static Logger logger = LoggerFactory.getLogger(Driver.class);
    public static void main(String[] args) throws Exception{

        configureLogging("candyLogs.log","INFO");

        DataStore dataStore = new DataStore();
        Processor processor = new Processor();


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
            double change = inputMoney - dataStore.getCandyPrice()[choice];
            int[] changeToGive = processor.checkChange(change, dataStore.getCoinBank());
            if (inputMoney >= dataStore.getCandyPrice()[choice]) {
                if (dataStore.getCandyBank()[choice] != 0) {
                    if (changeToGive[0] != -9 && changeToGive[1] != -9 && changeToGive[2] != -9 && changeToGive[3] != -9) {
                        //add money
                        dataStore.setCoinBank(processor.addArray(dataStore.getCoinBank(), processor.breakMoney(inputMoney)));


                        // remove candy from machine
                        int[] newCandyBank = dataStore.getCandyBank();
                        newCandyBank[choice] -= 1;
                        dataStore.setCandyBank(newCandyBank);
                        System.out.println("Here is your " + dataStore.getCandyName()[choice]);


                        //give back change
                        System.out.println("Here is Your change :\n" +
                                changeToGive[0] + "\t5 dollar notes\n" +
                                changeToGive[1] + "\t1 dollar notes\n" +
                                changeToGive[2] + "\t50 cent coins\n" +
                                changeToGive[3] + "\t25 cent coins\n");
                        dataStore.setCoinBank(processor.subArray(dataStore.getCoinBank(), changeToGive));


                        //write logs
                        logger.info(dataStore.getCandyName()[choice] + " was given");

                    } else {
                        System.out.println("Not enough Change left in Machine to process Transaction ");
                        System.out.println("Here is Your money :\n" +
                                processor.breakMoney(inputMoney)[0] + "\t5 dollar notes\n" +
                                processor.breakMoney(inputMoney)[1] + "\t1 dollar notes\n" +
                                processor.breakMoney(inputMoney)[2] + "\t50 cent coins\n" +
                                processor.breakMoney(inputMoney)[3] + "\t25 cent coins\n");
                        logger.info(dataStore.getCandyName()[choice] + "\tnot given");

                    }
                } else {
                    System.out.println(dataStore.getCandyName()[choice] + " is Out of Stock");
                    System.out.println("Here is Your money :\n" +
                            processor.breakMoney(inputMoney)[0] + "\t5 dollar notes\n" +
                            processor.breakMoney(inputMoney)[1] + "\t1 dollar notes\n" +
                            processor.breakMoney(inputMoney)[2] + "\t50 cent coins\n" +
                            processor.breakMoney(inputMoney)[3] + "\t25 cent coins\n");
                    logger.info(dataStore.getCandyName()[choice] + "\tnot given");

                }
            } else {
                System.out.println("Not enough Money Deposited to Buy candy");
                System.out.println("Here is Your money :\n" +
                        processor.breakMoney(inputMoney)[0] + "\t5 dollar notes\n" +
                        processor.breakMoney(inputMoney)[1] + "\t1 dollar notes\n" +
                        processor.breakMoney(inputMoney)[2] + "\t50 cent coins\n" +
                        processor.breakMoney(inputMoney)[3] + "\t25 cent coins\n");
                logger.info(dataStore.getCandyName()[choice] + "\tnot given");

            }
            //serialize write
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("DataStore.dat"));
            objectOutputStream.writeObject(dataStore);
            }catch (IOException e) {
                logger.error("There was an error ");
                System.out.println("ERROR");
            }catch (InputMismatchException m){
                logger.error("There was an error 1");
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
