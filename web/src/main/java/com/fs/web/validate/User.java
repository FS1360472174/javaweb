/*
 * User.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.web.validate;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author fangzhang
 *
 */
@Data
public class User {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;
}
