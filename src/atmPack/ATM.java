package atmPack;

import java.io.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Scanner;

/***********************************************************************
 * ATM class for CIS 163 project 01
 * @author Matthew Pische
 * @version 1.0
 **********************************************************************/

public class ATM {
    
    /** global suspend flag */
    private static boolean suspended = false;
    
    /** contents of an ATM */
    private int hundreds;
    private int fifties;
    private int twenties;
    
    /****************************************************************** 
     * Sets whether all ATM instances are suspended
     * @return boolean whether ATMs are suspended
     *****************************************************************/
    public static boolean isSuspended() {
        return suspended;
    }
    
    /******************************************************************
     * Suspends all ATMs from being mutated with takeOut/putIn methods
     * @param on whether to suspend all ATMs
     *****************************************************************/
    public static void suspend(Boolean on) {
        suspended = on;
    }

    /******************************************************************
     * Default constructor, holds 0 for all bill types 
     *****************************************************************/
    public ATM() {
        hundreds = 0;
        fifties = 0;
        twenties = 0;
    }
    
    /******************************************************************
     * Constructor for specific amounts of each dollar denomination
     * @param hundreds number of hundred dollar bills
     * @param fifties number of fifty dollar bills
     * @param twenties number of twenty dollar bills
     * @throws IllegalArgumentException
     *****************************************************************/
    public ATM(int hundreds, int fifties, int twenties) {
        if (hundreds >= 0 && fifties >= 0 && twenties >= 0) {
            this.hundreds = hundreds;
            this.fifties = fifties;
            this.twenties = twenties;                                                                                  
        } else
            throw new IllegalArgumentException(
                    "No negative currency amounts");
    }
    
    /******************************************************************
     * Constructor creating duplicate values from an existing ATM 
     * @param other the ATM whose inventory is to be duplicated
     * @throws IllegalArgumentException
     *****************************************************************/
    public ATM(ATM other) {
        if (other == null)
            throw new IllegalArgumentException("Can't have null ATM");
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

    /******************************************************************
     * gets current inventory of hundred dollar bills
     * @return current number of hundreds
     *****************************************************************/
    public int getHundreds() {
        return hundreds;
    }

    /******************************************************************
     * gets current number of fifty dollar bills
     * @return current number of fifties
     *****************************************************************/
    public int getFifties() {
        return fifties;
    }

    
    /******************************************************************
     * gets current number of twenty dollar bills
     * @return current number of twenties
     *****************************************************************/
    public int getTwenties() {
        return twenties;
    }

    /******************************************************************
     * sets this ATM's inventory of hundred dollar bills
     * @param hundreds new hundreds inventory
     * @throws IllegalArgumentException
     *****************************************************************/
    public void setHundreds(int hundreds) {
        if (hundreds < 0)
            throw new IllegalArgumentException(
                    "Amount must be positive");
        this.hundreds = hundreds;
    }

    /******************************************************************
     * sets this ATM's inventory of fifty dollar bills
     * @param fifties new fifties inventory
     * @throws IllegalArgumentException
     *****************************************************************/
    public void setFifties(int fifties) {
        if (fifties < 0)
            throw new IllegalArgumentException(
                    "Amount must be positive");
        this.fifties = fifties;
    }

    /******************************************************************
     * sets this ATM's inventory of twenty dollar bills
     * @param twenties new twenties inventory
     * @throws IllegalArgumentException
     *****************************************************************/
    public void setTwenties(int twenties) {
        if (twenties < 0)
            throw new IllegalArgumentException(
                    "Amount must be positive");
        this.twenties = twenties;
    }
    
    /******************************************************************
     * Returns a boolean indicating whether each of the two given 
     * ATMs have identical inventories 
     * @param other1 first ATM to compare
     * @param other2 second ATM to compare
     * @return boolean whether ATMs have identical inventories
     * @throws IllegalArgumentException
     *****************************************************************/
    public static boolean equals(ATM other1, ATM other2) {
        if (other1 == null || other2 == null)
            throw new IllegalArgumentException("Can't have null ATM");
        boolean rtn = false;
        if (other1.getHundreds() == other2.getHundreds() &&
            other1.getFifties() == other2.getFifties() &&
            other1.getTwenties() == other2.getTwenties()
            )
            rtn = true;
        return rtn;
    }
    
    /******************************************************************
     * Returns a boolean indicating whether this ATM's inventory is 
     * identical to that of the passed in ATM
     * @param other the ATM to compare
     * @return boolean whether both ATMs have identical inventories
     * @throws IllegalArgumentException
     *****************************************************************/
    public boolean equals(ATM other) {
        if (other == null)
            throw new IllegalArgumentException("Can't have null ATM");
        return equals(this, other);
    }
    
    /******************************************************************
     * Returns a boolean indicating whether this ATM's inventory is 
     * identical to that of the passed in ATM
     * @param other the ATM to compare
     * @throws IllegalArgumentException
     *****************************************************************/
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
    
    /******************************************************************
     * Returns the total value of the current ATM by totaling its
     * inventory of all bill denominations
     * @return int total value of this ATMs inventory
     *****************************************************************/
    public int totalValue() {
        return this.getHundreds() * 100 + this.getFifties() * 50 + 
                this.getTwenties() * 20;
    }
    
    /******************************************************************
     * Compares the total value of this ATM's inventory, 
     * to the passed in ATM's inventory, irrespective of how that 
     * inventory may be composed
     * @param other the ATM to be compared 
     * @return boolean whether the ATMs have equal inventory values 
     * @throws IllegalArgumentException
     *****************************************************************/
    public int compareTo(ATM other) {
        if (other == null)
            throw new IllegalArgumentException("Can't have null ATM");
        int rtn = 1;
        if (totalValue() == other.totalValue())
            rtn = 0;
        else if (totalValue() < other.totalValue())
            rtn = -1;
        return rtn;
    }
    
    /******************************************************************
     * adds specific numbers of bills to this ATMs inventory
     * @param hundreds number of hundreds to add
     * @param fifties number of fifties to add
     * @param twenties number of twenties to add
     * @throws IllegalArgumentException
     *****************************************************************/
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
    
    /******************************************************************
     * removes a specific number of each bill type from 
     * this ATM
     * @param hundreds number of hundreds to remove
     * @param fifties number of fifties to remove
     * @param twenties number of twenties to remove
     * @throws IllegalArgumentException
     *****************************************************************/
    public void takeOut(int hundreds, int fifties, int twenties) {
        if (ATM.isSuspended())
            return;
        if (hundreds >= 0 && fifties >= 0 && twenties >= 0) {
            if (getHundreds() - hundreds >= 0 &&
                getFifties() - fifties >= 0 &&
                getTwenties() - twenties >= 0) {
                
                setHundreds(getHundreds() - hundreds);
                setFifties(getFifties() - fifties); 
                setTwenties(getTwenties() - twenties);
                
            } 
            else
                throw new IllegalArgumentException(
                        "Not enough bills in this ATM");
        }
        else 
            throw new IllegalArgumentException(
                    "No negative values for bills");
    }
    
    /******************************************************************
     * removes from this ATM, the number of bills that are 
     * in the inventory of the passed in ATM
     * @param other the ATM whose inventory counts to remove
     * @throws IllegalArgumentException
     *****************************************************************/
    public void takeOut(ATM other) {
        if (other == null)
            throw new IllegalArgumentException("Can't have null ATM");
        if (ATM.isSuspended())
            return;
        if (other.getHundreds() < 0 ||
            other.getFifties() < 0 ||
            other.getTwenties() < 0 ||
            other.totalValue() < 0)
            throw new IllegalArgumentException(
                    "No negative values");
        if (getHundreds() - other.getHundreds() >= 0 &&
            getFifties() - other.getFifties() >= 0 &&
            getTwenties() - other.getTwenties() >= 0)
            
            takeOut(other.getHundreds(), 
                     other.getFifties(), 
                     other.getTwenties());
        else
            throw new IllegalArgumentException(
                    "Not enough bills in this ATM");
    }
    
    /******************************************************************
     * Removes enough bill items from this ATM's inventory to equal 
     * the passed in amount, using the largest available bills, and 
     * returns a new ATM comprised of those bills, if the current 
     * ATM has sufficient inventory to do so. If not, returns null.
     * @param amount the amount to remove from this ATM
     * @return a new ATM with a total value equal to that passed in
     * @throws IllegalArgumentException
     *****************************************************************/
    public ATM takeOut(int amount) {
        // eliminate unsolvable conditions
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
        
        // hold counts
        int hCount = getHundreds();
        int hNeed = 0;
        int fCount = getFifties();
        int fNeed = 0;
        int tCount = getTwenties();
        int tNeed = 0;
        
        // check if output requires a fifty
        boolean isOdd = (amount % 100) / 10 % 2 == 1 ? true : false;
        if (isOdd) {
            if (!(getFifties() >= 1))
                return null;
            fCount -= 1;
            amount -= 50;
            fNeed++;
        }
        
        // check minimum needed twenties
        int minT = amount % 100 / 20;
        if (minT > tCount)
            return null;
        tCount -= minT;
        amount -= minT * 20;
        tNeed += minT;
        
        // calculate needed number of hundreds
        if (amount - hCount * 100 > 0) {
            hNeed = hCount;
            amount = amount - hCount * 100;
        } else {
            hNeed = amount / 100;
            amount -= hNeed * 100;
        }
        // calculate needed number of fifties 
        if (amount - (fCount / 2) * 100 > 0) {
            amount -= (fCount / 2) * 100;
            fNeed += (fCount / 2) * 2;
            fCount -= (fCount / 2) * 2;
        } else {
            fNeed += (amount / 100) * 2;
            amount -= (amount / 100) * 2 * 50;
        }
        // calculate needed number of twenties
        if (amount - (tCount / 5) * 100 > 0)
            return null;
        else {
            tNeed += (amount / 100) * 5;
        }
        
        // update current atm inventory
        setHundreds(getHundreds() - hNeed);
        setFifties(getFifties() - fNeed);
        setTwenties(getTwenties() - tNeed);
        // create and return new ATM
        return new ATM(hNeed, fNeed, tNeed);    
        
    }
    
    /******************************************************************
     * Returns a string representation of the current ATMs inventory
     * @return string current inventory
     *****************************************************************/
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
    
    /******************************************************************
     * saves the “this” ATM to a file; use the parameter filename 
     * for the name of the file
     * @param filename name of the file to be written to
     * @throws FileNotFoundException 
     *****************************************************************/
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
    
    /******************************************************************
     * loads the “this” ATM object from a file; use the parameter 
     * filename for the name of the file
     * @param filename name of the file to be written to
     * @throws FileNotFoundException 
     *****************************************************************/
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
    
    /******************************************************************
     * Tests the ATM class
     * @param args
     * @throws FileNotFoundException
     *****************************************************************/
    public static void main(String[] args) throws FileNotFoundException {
        ATM s = new ATM(10,2,3);
        System.out.println("Created ChangeJar:$1160, result: " + 
                s.totalValue());
        
        ATM s1 = new ATM();
        System.out.println("\nCreated ChangeJar:$0, result: " + 
                s1.totalValue());

        s1.putIn(10,2,3);
        System.out.println("\nAdded ChangeJar:$1160, result: " + 
                s1.totalValue());

        ATM s2 = new ATM(10,2,3);
        s2.putIn(0,0,0);
        System.out.println("\nAdded ChangeJar:$1160, result: " + 
                s2.totalValue());

        s2 = new ATM(2,1,3);
        ATM temp = s2.takeOut(250);
        System.out.println ("\nTake out the following:\n" + 
                            temp.totalValue() + ": " + temp);
        System.out.println("Remaining ChangeJar:$60, result: " + 
                s2.totalValue());
        
        s2 = new ATM(2,1,5);
        temp = s2.takeOut(230);
        System.out.println ("\nTake out the following:\n" + 
                            temp.totalValue() + ": " + temp);
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

    }

    
}
