/*
 * AbstractState.java
 */

package com.fs.web.instance.state;

import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @author fangzhang
 *
 */
@Getter
public abstract class AbstractState {
    protected StateEnum code;
}
