/*
 * StateEnum.java
 */

package com.fs.web.instance.state;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fangzhang
 *
 */
@Getter
@AllArgsConstructor
public enum StateEnum {
    APPROVE(1),
    REFUSE(2);
    private final int code;
}
