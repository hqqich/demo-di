package org.example.service.impl;

import com.google.inject.Inject;
import org.example.service.Application;
import org.example.service.LogService;
import org.example.service.UserService;

/**
 * Created by ChenHao on 2022/9/26 is 9:47.
 *
 * @author tsinglink
 */

public class MyApp implements Application {

    private UserService userService;
    private LogService logService;

    @Inject
    public MyApp(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @Override
    public void work() {
        userService.process();
        logService.log("程序正常运行");
    }
}
