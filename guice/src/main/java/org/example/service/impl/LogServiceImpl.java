package org.example.service.impl;

import org.example.service.LogService;

/**
 * Created by ChenHao on 2022/9/26 is 9:46.
 *
 * @author tsinglink
 */

public class LogServiceImpl implements LogService {

    @Override
    public void log(String msg) {
        System.out.println("------LOG:" + msg);
    }
}
