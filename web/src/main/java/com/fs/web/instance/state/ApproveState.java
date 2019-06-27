/*
 * ApproveState.java
 */

package com.fs.web.instance.state;

import org.springframework.stereotype.Component;

/**
 * @author fangzhang
 *
 */
@Component
public class ApproveState extends AbstractState {
    public ApproveState() {
        code = StateEnum.APPROVE;
    }
}
