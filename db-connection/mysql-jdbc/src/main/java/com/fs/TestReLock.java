package com.fs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fangzhang
 *
 */
public class TestReLock {
    public static void main() {
        ReentrantLock connectionsLock = new ReentrantLock();
        Condition condition = connectionsLock.newCondition();
        connectionsLock.lockInterruptibly();
    }
}
