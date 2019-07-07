/*
 * LotteryController.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.web.lottery;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @SentinelResource
    public ResponseEntity play() {
        LotteryContext lotteryContext = new LotteryContext();
        lottery.choosePrize(lotteryContext);
        return ResponseEntity.of(null);
    }
}
