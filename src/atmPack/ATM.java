package atmPack;

import java.io.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Scanner;

public class ATM {
    
    private static boolean suspended = false;
    
    private int hundreds;
    private int fifties;
    private int twenties;

    public ATM() {
        hundreds = 0;
        fifties = 0;
        twenties = 0;
    }
    
    public static boolean isSuspended() {
        return suspended;
    }
    
    public static void suspend(Boolean on) {
        suspended = on;
    }
    
    public ATM(int hundreds, int fifties, int twenties) {
        if (hundreds >= 0 && fifties >= 0 && twenties >= 0) {
            this.hundreds = hundreds;
            this.fifties = fifties;
            this.twenties = twenties;                                                                                  
        } else
            throw new IllegalArgumentException(
                    "No negative currency amounts");

    }
    
    public ATM(ATM other) {
        if (other.getHundreds() >= 0 && 
            other.getFifties() >= 0 &&
            other.getTwenties() >=0)   {
            
        this.hundreds = other.getHundreds();
        this.fifties = other.getFifties();
        this.twenties = other.getTwenties();
        } else {
            throw new IllegalArgumentException(
                    "Amounts must be positive");
        }
    }

    
    public int getHundreds() {
        return hundreds;
    }

    public int getFifties() {
        return fifties;
    }

    public int getTwenties() {
        return twenties;
    }

    public void setHundreds(int hundreds) {
        if (hundreds < 0)
            throw new IllegalArgumentException(
                    "Amount must be positive");
        this.hundreds = hundreds;
    }

    public void setFifties(int fifties) {
        if (fifties < 0)
            throw new IllegalArgumentException(
                    "Amount must be positive");
        this.fifties = fifties;
    }

    public void setTwenties(int twenties) {
        if (twenties < 0)
            throw new IllegalArgumentException(
                    "Amount must be positive");
        this.twenties = twenties;
    }
    public static boolean equals(ATM other1, ATM other2) {
        boolean rtn = false;
        if (other1.getHundreds() == other2.getHundreds() &&
            other1.getFifties() == other2.getFifties() &&
            other1.getTwenties() == other2.getTwenties()
            )
            rtn = true;
        return rtn;
    }
    public boolean equals(ATM other) {
        return equals(this, other);
    }
    public boolean equals(Object other) {
        if (other != null) {
            if (other instanceof ATM) {
                ATM otherATM = (ATM)other;
                return equals(this, otherATM);
            } 
            else 
                throw new IllegalArgumentException("Not ATM object");
        }
        else 
            throw new IllegalArgumentException("Null Object Input");
    }
    public int totalValue() {
        return this.getHundreds() * 100 + this.getFifties() * 50 + 
                this.getTwenties() * 20;
    }
    public int compareTo(ATM other) {
        int rtn = 1;
        if (totalValue() == other.totalValue())
            rtn = 0;
        else if (totalValue() < other.totalValue())
            rtn = -1;
        return rtn;
    }
    public void putIn(int hundreds, int fifties, int twenties) {
        if (ATM.isSuspended())
            return;
        if (hundreds >= 0 && fifties >= 0 && twenties >= 0) {
            setHundreds(getHundreds() + hundreds);
            setFifties(getFifties() + fifties);
            setTwenties(getTwenties() + twenties); 
        }
        else 
            throw new IllegalArgumentException(
                    "No negative values for bills added to ATM");
    }
    public void takeOut(int hundreds, int fifties, int twenties) {
        if (ATM.isSuspended())
            return;
        if (hundreds >= 0 && fifties >= 0 && twenties >= 0) {
            setHundreds(getHundreds() - hundreds);
            setFifties(getFifties() - fifties); 
            setTwenties(getTwenties() - twenties);
        }
        else 
            throw new IllegalArgumentException(
                    "No negative values for bills");
    }
    public void takeOut(ATM other) {
        if (ATM.isSuspended())
            return;
        takeOut(other.getHundreds(), 
                     other.getFifties(), 
                     other.getTwenties());
    }
    
    public ATM takeOut(int amount) {
        if (ATM.isSuspended())
            return null;
        if (amount == 10 || amount == 30)
            return null;
        if (amount == 0) 
            return new ATM (0,0,0);
        if (amount < 0 || amount % 10 != 0)   {
            throw new IllegalArgumentException(
                    "Amount must be positive and divisible by 10");
        }
        if (totalValue() < amount)
            return null;
        
        int hCount = getHundreds();
        int hNeed = 0;
        int fCount = getFifties();
        int fNeed = 0;
        int tCount = getTwenties();
        int tNeed = 0;
        
        boolean isOdd = (amount % 100) / 10 % 2 == 1 ? true : false;
        if (isOdd) {
            if (!(getFifties() >= 1))
                return null;
            fCount -= 1;
            amount -= 50;
            fNeed++;
        }
        
        int minT = amount % 100 / 20;
        if (minT > tCount)
            return null;
        tCount -= minT;
        amount -= minT * 20;
        tNeed += minT;
        
        if (amount - hCount * 100 > 0) {
            hNeed = hCount;
            amount = amount - hCount * 100;
        } else {
            hNeed = amount / 100;
            amount -= hNeed * 100;
        }
        if (amount - (fCount / 2) * 100 > 0) {
            amount -= (fCount / 2) * 100;
            fNeed += (fCount / 2) * 2;
            fCount -= (fCount / 2) * 2;
        } else {
            fNeed += (amount / 100) * 2;
            amount -= (amount / 100) * 2 * 50;
        }
        if (amount - (tCount / 5) * 100 > 0)
            return null;
        else {
            tNeed += (amount / 100) * 5;
        }
        setHundreds(getHundreds() - hNeed);
        setFifties(getFifties() - fNeed);
        setTwenties(getTwenties() - tNeed);
        
        return new ATM(hNeed, fNeed, tNeed);    
        
    }
    
    public ATM minBills(double amount) {
        if (amount % 10 != 0) 
            throw new IllegalArgumentException(
                    "Amount must be divisible by 10");
        else {
            int[] tArray = {0,3,1,4,2};
            int lAmount = (int)(amount / 10);
            int tCount = 0;
            int fCount = 0;
            int hCount = 0;        
            if (lAmount >= 5) {
                tCount = tArray[(lAmount % 5)];
                fCount = lAmount % 2;
                hCount = lAmount / 10;
                if (lAmount % 10 == 1 || amount % 10 == 3)
                    hCount -= 1;
            }
            else if (lAmount % 2 == 0)
                tCount = lAmount / 2;
            else 
                throw new IllegalArgumentException(
                        "No possible combination");
            return new ATM(hCount, fCount, tCount);
        }
        
    }
    public String toString() {
        String rtn = MessageFormat.format("{0} hundred dollar " + 
                "{0,choice,0#bills|1#bill|1<bills}, " + 
                "{1} fifty dollar {1,choice,0#bills|1#bill|1<bills}, " + 
                "{2} twenty dollar {2,choice,0#bills|1#bill|1<bills}",
                getHundreds(), 
                getFifties(), 
                getTwenties());
        return rtn;
    }
    
    /**
     * saves the “this” ATM to a file; use the parameter filename 
     * for the name of the file
     * @param filename name of the file to be written to
     * @throws FileNotFoundException 
     */
    public void save(String filename) throws FileNotFoundException {
        try {
            PrintWriter pWriter = new PrintWriter(filename);
            pWriter.println(getHundreds());
            pWriter.println(getFifties());
            pWriter.println(getTwenties());
            
            pWriter.close();
        } catch (Exception e) {
            throw new FileNotFoundException(e.getMessage());
        }

    }
    
    /**
     * loads the “this” ATM object from a file; use the parameter 
     * filename for the name of the file
     * @param filename name of the file to be written to
     * @throws FileNotFoundException 
     */
    public void load(String filename) throws FileNotFoundException {
        try {
            Scanner scnr = new Scanner(new BufferedReader(
                           new FileReader(filename)));
            
            setHundreds(scnr.nextInt());
            setFifties(scnr.nextInt());
            setTwenties(scnr.nextInt());
            
            scnr.close();
        } catch (Exception e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }
    
    /**
     * turns ‘off’ or ‘on’  any takeOut/putIn methods in. 
     * In other words, when true, prevents any takeOut/putIn method 
     * from changing (mutating) the state of the “this” object 
     * as it relates to the amount in the ATM
     * @param on 
     */
    
    public static void main(String[] args) throws FileNotFoundException {
        ATM s = new ATM(10,2,3);
        //System.out.println("Created ChangeJar:$1160, result: " + 
        //s.getAmount());
        System.out.println("Created ChangeJar:$1160, result: " + 
                s.totalValue());
        
        ATM s1 = new ATM();
        //System.out.println("\nCreated ChangeJar:$0, result: " + 
        //s1.getAmount());
        System.out.println("\nCreated ChangeJar:$0, result: " + 
                s1.totalValue());

        s1.putIn(10,2,3);
        //System.out.println("\nAdded ChangeJar:$1160, result: " + 
        //s1.getAmount());
        System.out.println("\nAdded ChangeJar:$1160, result: " + 
                s1.totalValue());

        ATM s2 = new ATM(10,2,3);
        s2.putIn(0,0,0);
        //System.out.println("\nAdded ChangeJar:$1160, result: " + 
        //s2.getAmount());
        System.out.println("\nAdded ChangeJar:$1160, result: " + 
                s2.totalValue());

        s2 = new ATM(2,1,3);
        ATM temp = s2.takeOut(250);
        System.out.println ("\nTake out the following:\n" + 
                            temp.totalValue() + ": " + temp);
        //System.out.println("Remaining ChangeJar:$60, result: " + 
        //s2.getAmount());
        System.out.println("Remaining ChangeJar:$60, result: " + 
                s2.totalValue());
        
        s2 = new ATM(2,1,5);
        temp = s2.takeOut(230);
        System.out.println ("\nTake out the following:\n" + 
                            temp.totalValue() + ": " + temp);
        //System.out.println("Remaining ChangeJar:$60, result: " + 
        //s2.getAmount());
        System.out.println("Remaining ChangeJar:$120, result: " + 
                s2.totalValue());

        s2 = new ATM (5, 4, 3);
        s2.save("pizza");
        s2 = new ATM();
        s2.load("pizza");

        if (s2.equals(new ATM(5,4,3))) 
                System.out.println (
                        "\nLoad and Save and Equals works!");

        System.out.println (s2);

                // Create many more test cases in this driver method to
                // prove the class is functioning correctly.
        }

    
}
