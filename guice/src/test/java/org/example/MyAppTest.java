package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.module.MyAppModule;
import org.example.service.Application;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by ChenHao on 2022/9/26 is 9:48.
 *
 * @author tsinglink
 */

public class MyAppTest {

    private static Injector injector;

    @BeforeClass
    public static void init() {
        injector = Guice.createInjector(new MyAppModule());
    }

    @Test
    public void testMyApp() {
        Application myApp = injector.getInstance(Application.class);
        myApp.work();

        System.out.println(injector.getInstance(Application.class));
        System.out.println(injector.getInstance(Application.class));
        System.out.println(injector.getInstance(Application.class));

    }

}
