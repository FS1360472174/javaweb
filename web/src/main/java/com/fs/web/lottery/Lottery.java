/*
 * Lottery.java
 */

package com.fs.web.lottery;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fangzhang
 *
 */
@Component
public class Lottery {
    /**
     *  奖池
     */
    static List<Prize> pool;

    static {
        final List<FlowRule> rules = new ArrayList<>();
        final FlowRule rule = new FlowRule("lottery");
        // set limit qps to 2
        rule.setCount(2);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");
        rules.add(rule);
        FlowRuleManager.loadRules(rules);

        final List<Prize> prize = new ArrayList<>();
        prize.add(new Prize(1L, "1", 10));
        prize.add(new Prize(2L, "2", 100));
        pool = prize;
    }

    @SentinelResource(value = "lottery", blockHandler = "contextExceptionHandler")
    public Prize choosePrize(final LotteryContext lotteryContext) {
        final Integer totalRate = pool.stream().mapToInt(p -> p.getRate()).sum();
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

    /**
     * 注意这边的方法参数，返回参数需要与调用方保持一致
     * 详见AbstractSentinelAspectSupport.findMethod方法
     * @param context
     * @param ex
     * @return
     */
    public Prize contextExceptionHandler(final LotteryContext context, final BlockException ex) {
        // Do some log here.
        System.out.println("context" + context + "ex:" + ex);
        return null;
    }
}
