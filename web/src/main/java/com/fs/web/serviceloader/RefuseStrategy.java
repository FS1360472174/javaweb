/*
 * RefuseStrategy.java
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
    // 这种获取值的方法不可取
    public void setExt1(RefuseState ext1) {
        //inject数据
        this.state = ext1;
        System.out.println("inject data");
    }
}
