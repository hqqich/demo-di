package org.example.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.example.service.Application;
import org.example.service.LogService;
import org.example.service.UserService;
import org.example.service.impl.LogServiceImpl;
import org.example.service.impl.MyApp;
import org.example.service.impl.UserServiceImpl;

/**
 * Created by ChenHao on 2022/9/26 is 9:48.
 *
 * @author tsinglink
 */

public class MyAppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LogService.class).to(LogServiceImpl.class).in(Singleton.class);
        bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
        bind(Application.class).to(MyApp.class).in(Singleton.class);
    }
}