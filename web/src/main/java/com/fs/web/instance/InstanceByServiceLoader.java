/*
 * InstanceByServiceLoader.java
 */
package com.fs.web.instance;

import com.fs.web.instance.state.AbstractState;

import com.fs.web.instance.state.StateEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author fangzhang
 *
 */
public class InstanceByServiceLoader implements StateFactory<AbstractState> {
    private final ServiceLoader<AbstractState> abstractStateServiceLoader = ServiceLoader.load
            (AbstractState.class);

    @Override
    public AbstractState getInstance(final StateEnum stateEnum) {
        final List<AbstractState> result = new ArrayList<>();
        for (final AbstractState state : abstractStateServiceLoader) {
            result.add(state);
        }
        return result.stream().collect(Collectors.toMap(p -> p.getCode(), Function.identity())).get
                (stateEnum);
    }
}
