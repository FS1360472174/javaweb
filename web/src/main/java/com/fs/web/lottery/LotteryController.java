/*
 * LotteryController.java
 */

package com.fs.web.lottery;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fangzhang
 *
 */
@RestController
@RequestMapping("/api/lottery")
public class LotteryController {
    @Autowired
    private Lottery lottery;

    @PostMapping("/play")
    public Prize play(@RequestParam(value = "userId")final Long userId) {
        final LotteryContext lotteryContext = new LotteryContext();
        lotteryContext.setUserId(userId);
        final Prize prize = lottery.choosePrize(lotteryContext);
        return prize;
    }
}
