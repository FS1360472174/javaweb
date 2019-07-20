/*
 * Prize.java
 */

package com.fs.web.lottery;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author fangzhang
 *
 */
@Data
@AllArgsConstructor
public class Prize {
    private Long id;
    private String name;
    private Integer rate;
}
