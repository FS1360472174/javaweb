/*
 * ScriptCommandLineRunner.java
 */

package com.fs.web;

import com.fs.web.instance.InstanceByServiceLoader;
import com.fs.web.instance.InstanceBySpring;
import com.fs.web.instance.InstanceBySpringContext;
import com.fs.web.instance.state.StateEnum;
import com.fs.web.serviceloader.ExtensionServiceLoader;
import com.fs.web.serviceloader.RefuseStrategy;
import com.fs.web.serviceloader.Strategy;
import lombok.var;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author fangzhang
 *
 */
@Component
public class ScriptCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(final String... strings) throws Exception {


        testInject();

    }
    private void testGetInstance() {
        final var instance1 = new InstanceByServiceLoader();
        System.out.println(instance1.getInstance(StateEnum.APPROVE));
        final var instance2 = new InstanceBySpring();
        System.out.println(instance2.getInstance(StateEnum.APPROVE));
        final var instance3 = new InstanceBySpringContext();
        System.out.println(instance3.getInstance(StateEnum.APPROVE));
    }
    private void testInject() {
        Strategy ext = ExtensionServiceLoader.getExtensionLoader(Strategy.class).getExtension
                (RefuseStrategy.class.getName());
    }
}
