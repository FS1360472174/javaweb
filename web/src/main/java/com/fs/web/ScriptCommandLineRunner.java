/*
 * ScriptCommandLineRunner.java
 */

package com.fs.web;

import com.fs.web.alg.consisthash.ConsistHash;
import com.fs.web.alg.consisthash.NodeIP;
import com.fs.web.instance.InstanceByServiceLoader;
import com.fs.web.instance.InstanceBySpring;
import com.fs.web.instance.InstanceBySpringContext;
import com.fs.web.instance.state.StateEnum;
import com.fs.web.proxy.AsyncMethod;
import com.fs.web.serviceloader.ExtensionServiceLoader;
import com.fs.web.serviceloader.RefuseStrategy;
import com.fs.web.serviceloader.Strategy;
import com.fs.web.validate.User;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author fangzhang
 *
 */
@Component
public class ScriptCommandLineRunner implements CommandLineRunner {

    @Autowired
    @Lazy
    private AsyncMethod asyncMethod;
    @Override
    public void run(final String... strings) throws Exception {
        // testValidate();
        // testInject();
        // System.out.println(ServiceLoader.class.getClassLoader());
        //System.out.println(Thread.currentThread().getContextClassLoader());
        testAsync();
    }

    private void testAsync() {
        asyncMethod.testAsync();
    }

    private void testValidate() {
        final User user = new User();
        user.setId(1L);
        //ValidatorFactory factory = Validation
        //       .buildDefaultValidatorFactory();
        // Set<ConstraintViolation<User>> constraintViolations =
        //        factory.getValidator().validate(user);
        //System.out.println(constraintViolations);
    }

    private void testConsist() {
        final ConsistHash consistHash = new ConsistHash();
        System.out.println(consistHash.select(new NodeIP("host1", 1)));
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
        final Strategy ext = ExtensionServiceLoader.getExtensionLoader(Strategy.class).getExtension
                (RefuseStrategy.class.getName());
        System.out.println(ext);
    }


}
