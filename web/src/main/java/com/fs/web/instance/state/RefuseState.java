/*
 * RefuseState.java
 */

package com.fs.web.instance.state;

import org.springframework.stereotype.Component;

/**
 * @author fangzhang
 *
 */
@Component
public class RefuseState extends AbstractState {
    public RefuseState() {
        code = StateEnum.REFUSE;
    }
}
