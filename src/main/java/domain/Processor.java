package domain;


public class Processor {
    public int[] breakMoney(double inputMoney){
        double five = inputMoney/5.0;
        double temp = inputMoney%5;
        double one = temp/1;
        temp = temp%1;
        double fiftyCents = temp/0.5;
        temp = temp%0.5;
        double twentyFiveCents = temp/0.25;
        temp = temp%0.25;
        int[] coins= {(int)five,(int)one,(int)fiftyCents,(int)twentyFiveCents};
        return coins;
    }

    public double countMoney(int[] coinCount){
        double change=(5*coinCount[0])+(1*coinCount[1])+(0.5*coinCount[2])+(0.25*coinCount[3]);
        return change;
    }

    public int checkCandyStock(int candyStock){
        if(candyStock==0){
            return 0;
        }
        else{
            return 1;
        }
    }


    public int[] checkChange(double change , int[] bank){
        int five = 0;
        int one = 0;
        int fiftyCents = 0;
        int twentyFiveCents =0;
        //int[] coinBank = getCoinBank();
        while(change - 5 >= 0)
        {
            if((bank[0]-five)>=0) {
                change = change - 5;
                five++;
            }
            else{
                break;
            }
        }

        while(change - 1 >= 0)
        {
            if((bank[1]-one)>=0) {
                change = change - 1;
                one++;
            }
            else{
                break;
            }
        }

        while(change - 0.5 >= 0)
        {
            if((bank[2]-fiftyCents)>=0) {
                change = change - 0.5;
                fiftyCents++;
            }
            else{
                break;
            }
        }

        while(change - 0.25 >= 0)
        {
            if((bank[3]-twentyFiveCents)>=0) {
                change = change - 0.25;
                twentyFiveCents++;
            }
            else{
                break;
            }
        }
        if(change==0) {
            int[] coins = {five, one, fiftyCents, twentyFiveCents};
            return coins;
        }
        else{
            int[] zero = {0,0,0,0};
            return zero;
        }
    }

    public int[] addArray(int[] first, int[] second) {
        int[] result = new int[4];
        for (int i = 0; i < 4; i++) {
            result[i] = first[i] + second[i];
        }
        return result;
    }

}


