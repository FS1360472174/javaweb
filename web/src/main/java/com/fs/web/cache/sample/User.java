/*
 * User.java
 */

package com.fs.web.cache.sample;

import lombok.Builder;
import lombok.Data;

/**
 * @author fangzhang
 *
 */
@Data
@Builder
public class User {
    private Long userId;
    private String name;
}
