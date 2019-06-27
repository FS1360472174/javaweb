/*
 * InstanceBySpringContext.java
 *
 */

package com.fs.web.instance;

import com.fs.web.instance.config.ApplicationContextUtil;
import com.fs.web.instance.state.AbstractState;
import com.fs.web.instance.state.StateEnum;
import lombok.var;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author fangzhang
 *
 */
public class InstanceBySpringContext implements StateFactory<AbstractState> {
    @Override
    public AbstractState getInstance(final StateEnum stateEnum) {
        final var mapBean = ApplicationContextUtil.getBeansOfType(AbstractState.class);
        return mapBean.values().stream().collect(Collectors.toMap(p -> p.getCode(),
                Function.identity(), (y1, y2) -> y1)).get(stateEnum);
    }
}
