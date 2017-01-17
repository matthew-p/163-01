package atmPack;

public class ATM {
    private int hundreds;
    private int fifties;
    private int twenties;

    public ATM() {
        hundreds = 0;
        fifties = 0;
        twenties = 0;
    }
    public ATM(int hundreds, int fifties, int twenties) {
        this.hundreds = hundreds;
        this.fifties = fifties;
        this.twenties = twenties;
    }
    
    public ATM(ATM other) {
        this.hundreds = other.getHundreds();
        this.fifties = other.getFifties();
        this.twenties = other.getTwenties();
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
        this.hundreds = hundreds;
    }

    public void setFifties(int fifties) {
        this.fifties = fifties;
    }

    public void setTwenties(int twenties) {
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
        return this.getHundreds() + this.getFifties() + this.getTwenties();
    }
    public int compareTo(ATM other) {
        int rtn = 1;
        if (this.totalValue() == other.totalValue())
            rtn = 0;
        else if (this.totalValue() < other.totalValue())
            rtn = -1;
        return rtn;
    }
    public void putIn(int hundreds, int fifties, int twenties) {
        if (hundreds >= 0 && fifties >= 0 && twenties >= 0) {
            this.setHundreds(this.getHundreds() + hundreds);
            this.setFifties(this.getFifties() + fifties);
            this.setTwenties(this.getTwenties() + twenties); 
        }
        else 
            throw new IllegalArgumentException("No negative values for bills added to ATM");
    }
    public void takeOut(int hundreds, int fifties, int twenties) {
        if (hundreds >= 0 && fifties >= 0 && twenties >= 0) {
            this.setHundreds(this.getHundreds() - hundreds);
            this.setFifties(this.getFifties() - fifties); 
            this.setTwenties(this.getTwenties() - twenties);
        }
    }
    public void takeOut(ATM other) {
        this.takeOut(other.getHundreds(), other.getFifties(), other.getTwenties());
    }
    public ATM takeOut(double amount) {
        if (amount % 10 != 0) 
            throw new IllegalArgumentException("Amount must be divisible by 10");
        else {
            int[] tArray = {0,3,1,4,2};
            long lAmount = (long)(amount / 10);
            long tCount = 0;
            long fCount = 0;
            long hCount = 0;        
            if (lAmount >= 5) {
                tCount = tArray[(int)(lAmount % 5)];
                fCount = lAmount % 2;
                hCount = lAmount / 10;
                if (lAmount % 10 == 1 || amount % 10 == 3)
                    hCount -= 1;
            }
            else if (lAmount % 2 == 0)
                tCount = lAmount / 2;
            else 
                throw new IllegalArgumentException("No possible combination");
            return new ATM(hCount, fCount, tCount);
        }
        
    }
}
