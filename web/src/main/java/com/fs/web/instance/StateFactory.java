/*
 * InstanceGet.java
 */

package com.fs.web.instance;

import com.fs.web.instance.state.StateEnum;

import java.util.List;

/**
 * @author fangzhang
 *
 */
public interface StateFactory<T> {
    T getInstance(StateEnum stateEnum);
}
