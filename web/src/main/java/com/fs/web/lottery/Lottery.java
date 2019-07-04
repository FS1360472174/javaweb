/*
 * Lottery.java
 */

package com.fs.web.lottery;
import java.security.SecureRandom;
import java.util.List;
/**
 * @author fangzhang
 *
 */
public class Lottery {
    /**
     *  奖池
     */
    private List<Prize> pool;

    public Prize choosePrize(LotteryContext lotteryContext) {
        Integer totalRate = pool.stream().mapToInt(p -> p.getRate()).sum();
        // 生成随机数
        final int randResult = new SecureRandom().nextInt(10000);
        // 重新计算奖品中奖概率，用随机数按顺序匹配奖品
        int rangeEnd = 0;
        for (final Prize prize : pool) {
            final int newRate = (int) (prize.getRate() * 1.0 / totalRate * 10000);
            rangeEnd += newRate;
            if (randResult <= rangeEnd) {
                return prize;
            }
        }
        return null;
    }
}
