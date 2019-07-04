/*
 * Semaphere.java
 */

package com.fs.web.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fangzhang
 *
 */
public class SemaphoreTest {
    private static final Semaphore LIMIT = new Semaphore(10);
    private static AtomicInteger counter = new AtomicInteger(0);
    public void invoke() {
        counter.decrementAndGet();
        if (!LIMIT.tryAcquire()) {
            System.out.println("exceed limit");
            return ;
        }
        try {
            System.out.println("invoke");
        }finally {
            LIMIT.release();
        }
    }

}
