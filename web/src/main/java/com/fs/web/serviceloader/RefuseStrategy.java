/*
 * RefuseStrategy.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.web.serviceloader;

import com.fs.web.instance.state.AbstractState;
import com.fs.web.instance.state.RefuseState;

/**
 * @author fangzhang
 *
 */
public class RefuseStrategy implements Strategy {
    private AbstractState state;
    @Override
    public void handle() {

    }
    public void setExt1(RefuseState ext1) {
        //inject数据
        this.state = ext1;
        System.out.println("inject data");
    }
}
