package atmPack;

import java.util.HashMap;

public class CoinCount {
    
    /**
     * this is a dynamic solution to solve the ATM takeOut problem
     * if an unlimited number of bills were available. 
     * it accepts an array of currency denominations, and the 
     * targeted amount to break into those denominations. 
     * Returning a HashMap with each denomination as a key, associated
     * with the total number of times that denomination is used
     * to reach the target total.
     * 
     * It memoizes the total number of all denomination units to reach 
     * each possible subtotal up to and including the target total
     * in a one dimensional array, and the last denomination unit used 
     * to reach that subtotal in a second one dimensional array.
     *on units
     * used to reach the target total, by working backwards, getting 
     * the last denomination used from the second array, and subtracting 
     * this value from the current subtotal, to then get the number of 
     * denomination units for that subtotal, removing the last coin to 
     * reach it, and so on. 
     * 
     * Finally, it records the total instances of each denomination unit
     * in that array in a HashMap, which is then returned. 
     * @param denom array of usable denominations
     * @param targetChangeAmount the total amount to reach
     * @return HashMap key of each denomination, value total times used
     */
    public static HashMap<Integer, Integer> minChange(int[] denom, int targetChangeAmount)
    {
        int numDenoms = denom.length;
        // this is an array of the total number of coins or denominations
        // used to reach the given total, which is the index
        int[] totalNumOfAllDenomsUsedFor = new int[targetChangeAmount + 1];
        // an array of the last denomination used to reach the current
        // total, which is the index
        int[] lastDenomIndexToReach = new int[targetChangeAmount + 1];
        
        
        totalNumOfAllDenomsUsedFor[0] = 1;
        // for all possible values from zero to the desired total amount of change, 
        for (int curTotalChange = 0 ; curTotalChange < targetChangeAmount; curTotalChange++) {
            // if any number of coins is used to reach the current subtotal,
            if (totalNumOfAllDenomsUsedFor[curTotalChange] > 0) {
                // check every available denomination, 
                for (int curDenomIndex = 0; curDenomIndex < numDenoms; curDenomIndex++) {
                    // add the current denomination plus the current subtotal, to make a working total
                    int curTtlChngPlusNewCoin = curTotalChange + denom[curDenomIndex];
                    // if this working total, is less than the ultimate target amount, 
                    if (curTtlChngPlusNewCoin <= targetChangeAmount) {
                        // and no coins are currently recored to reach that working total, 
                        if (totalNumOfAllDenomsUsedFor[curTtlChngPlusNewCoin] == 0 || 
                                // or the total number of coins recorded to reach it 
                                // exceed that used to reach the sub total plus 1 (for the current, larger, coin denomination),
                                totalNumOfAllDenomsUsedFor[curTtlChngPlusNewCoin] > totalNumOfAllDenomsUsedFor[curTotalChange] + 1) {
                            // take the total count of coins used to reach the subtotal plus 1 for the new coin, 
                            // and set that count to the value for the working total
                            totalNumOfAllDenomsUsedFor[curTtlChngPlusNewCoin] = totalNumOfAllDenomsUsedFor[curTotalChange] + 1;
                            // update the record of coins used, to set the current working total 
                            // to the current denomination just used
                            lastDenomIndexToReach[curTtlChngPlusNewCoin] = curDenomIndex;
                        }
                    }
                }
            }
        }

        // if there is no possible solution
        if (totalNumOfAllDenomsUsedFor[targetChangeAmount] == 0)
            return null;

        // create an array to hold every instance of each coin used
        int[] coinsUsedAry = new int[totalNumOfAllDenomsUsedFor[targetChangeAmount] - 1];
        // create a value to count down from the target 
        // as each coin is pulled from the array recording each use
        int remainingTargetChangeAmount = targetChangeAmount;
        
        while (remainingTargetChangeAmount > 0)
        {
            // find the last coin denomiation used to reach the current 
            // remaining total, and add it to the reduced array of used coins
            // minus 2 because zero uzed once and to push back to zero index
            coinsUsedAry[totalNumOfAllDenomsUsedFor[remainingTargetChangeAmount] - 2] = denom[lastDenomIndexToReach[remainingTargetChangeAmount]]; 
            // reduce the amount of chainge by the denomination used
            remainingTargetChangeAmount = remainingTargetChangeAmount - denom[lastDenomIndexToReach[remainingTargetChangeAmount]];
        }
        
        // construct a dictionary combining the denomiation to the count 
        HashMap<Integer, Integer> denomCountsDict = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < coinsUsedAry.length; i++) {
            int currCoin = coinsUsedAry[i];
            if (denomCountsDict.containsKey(currCoin)) {
                denomCountsDict.put(currCoin, denomCountsDict.get(currCoin) + 1);
            } else 
                denomCountsDict.put(currCoin, 1);
        }

        return denomCountsDict;
    }


    

}
