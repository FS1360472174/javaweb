/*
 * ExceptionUtil.java
 */

package com.fs.web.lottery.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author fangzhang
 *
 */
public class ExceptionUtil {
    public static void handleException(BlockException ex) {
        System.out.println("Oops: " + ex.getClass().getCanonicalName());
    }
}
