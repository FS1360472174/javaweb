package plan;

/**
 * @author cnstonefang@gmail.com
 * 动态规划解题思路
 *
 */
public class Stock {
    public  static  void main(String[] args) {
        int [] test1 = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit1(test1));
        System.out.println(maxProfit2(test1));
        System.out.println(maxProfit4(0,test1));
    }

    /***
     * 只允许交易一次，求最大利润
     * 迭代，保存中间利润最大值
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices) {
        if (prices.length <=1) {
            return 0;
        }
        int maxProfix = 0;
        int buy = prices[0];
        int profix = 0;
        for (int i=1; i< prices.length; i ++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else {
                profix = prices [i] - buy;
                if (profix > maxProfix) {
                    maxProfix = profix;
                }
            }
        }
        return maxProfix;
    }
    /**
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
     * 这种解法感觉不对，并没有符合题意，没有遵循买了，才能卖
     * 贪婪算法
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int maxProfix = 0;
        int deal = 0;
        for (int i=1; i< prices.length; i ++) {
            deal = prices[i] - prices[i-1];
            if (deal > 0) {
                maxProfix += deal;
            }
        }
        return maxProfix;
    }

    /**
     * 允许交易K次
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfit4(int k , int[] prices) {
        if (prices.length <= 1 || k==0) {
            return 0;
        }
        int tradeCount = k > prices.length/2 ? prices.length/2: k;
        int[] buyProfit = new int[tradeCount];
        int[] sellProfit = new int[tradeCount];
        for (int i = 0; i< tradeCount ;i ++ ){
            buyProfit [i] = -prices[0];
            sellProfit[i] = 0;
        }
        for (int i =1;i < prices.length;i++) {
            buyProfit[0]  = Math.max(buyProfit[0],-prices[i]);
            sellProfit[0] = Math.max(sellProfit[0], buyProfit[0] + prices[i]);
            for (int j = 1; j<tradeCount; j++) {
                buyProfit[j] = Math.max(buyProfit[j], sellProfit[j-1] - prices[i]);
                sellProfit[j] = Math.max(sellProfit[j], buyProfit[j] + prices[i]);
            }
        }
        return sellProfit[tradeCount -1];
    }

    public static int maxProfit4Refer(int k, int[] prices) {
        //用II的解法优化k > prices.length / 2的情况
        if(k > prices.length / 2){
            int sum = 0;
            for(int i = 1; i < prices.length; i++){
                if(prices[i]>prices[i-1]) sum += prices[i] - prices[i-1];
            }
            return sum;
        }
        //初始化买卖股票后剩余金钱的数组
        int[] release = new int[k+1];
        int[] hold = new int[k+1];
        for(int i = 0; i < k+1; i++){
            hold[i]=Integer.MIN_VALUE;
        }
        for(int i = 0; i < prices.length; i++){
            for(int j = 1; j < k+1; j++){
                //卖出第j笔交易，所剩余的钱
                release[j] = Math.max(release[j], hold[j]+prices[i]);
                //买入第j笔交易，所剩余的钱
                hold[j] = Math.max(hold[j], release[j-1]-prices[i]);
            }
        }
        return release[k];
        }
}
