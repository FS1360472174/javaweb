/*
 * InstanceBySpring.java
 */

package com.fs.web.instance;

import com.fs.web.instance.state.AbstractState;
import com.fs.web.instance.state.StateEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author fangzhang
 * @Service不能少，需要注入进去
 */
@Service
public class InstanceBySpring implements StateFactory<AbstractState>, InitializingBean {
    private static Map<StateEnum, AbstractState> map = new HashMap<>();
    @Autowired
    private List<AbstractState> states;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!CollectionUtils.isEmpty(states)) {
            map = states.stream().collect(Collectors.toMap(v -> v
                    .getCode(), Function.identity()));
        }
    }

    @Override
    public AbstractState getInstance(final StateEnum stateEnum) {
        return map.get(stateEnum);
    }
}
