package org.example;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.zeroturnaround.exec.ProcessExecutor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        for (String arg : args) {
            System.out.println(arg);
        }
        if (Objects.equals(args[0], "0")) {
            System.out.println("zt");
            try {
                new ProcessExecutor().command("sh /opt/tsinglink/run_ts_alg_cvt_ss626.sh").execute();

            } catch (IOException | InterruptedException | TimeoutException e) {
                throw new RuntimeException(e);
            }
        }

        if (Objects.equals(args[0], "1")) {
            System.out.println("jdk");
            // 不使用工具类的写法
            List<String> strs = new ArrayList<>();
            strs.add("docker stop ss_626");
            strs.add("docker rm ss_626");
            strs.add("docker container run --name ss_626 -id --privileged -v /opt/tsinglink:/tsinglink his_ss_626:221029 /bin/bash");
            strs.add("/bin/qxac_auto");

            for (String str : strs) {
                Process process = Runtime.getRuntime().exec(str);
                //process.exitValue();
                int exitCode = process.waitFor(); // 阻塞等待完成
                if (exitCode == 0) { // 状态码0表示执行成功
                    String result = IOUtils.toString(process.getInputStream(), Charset.defaultCharset()); // "IOUtils" commons io中的工具类，详情可以参见前续文章介绍
                    System.out.println(result);
                } else {
                    String errMsg = IOUtils.toString(process.getErrorStream(), Charset.defaultCharset());
                    System.out.println(errMsg);
                }
            }
        }

        if (Objects.equals(args[0], "2")) {
            System.out.println("apache");
            String command = "sh /opt/tsinglink/run_ts_alg_cvt_ss626.sh";
            //接收正常结果流
            ByteArrayOutputStream susStream = new ByteArrayOutputStream();
            //接收异常结果流
            ByteArrayOutputStream errStream = new ByteArrayOutputStream();
            CommandLine commandLine = CommandLine.parse(command);
            DefaultExecutor exec = new DefaultExecutor();
            PumpStreamHandler streamHandler = new PumpStreamHandler(susStream, errStream);
            exec.setStreamHandler(streamHandler);
            int code = exec.execute(commandLine);
            System.out.println("result code: " + code);
            // 不同操作系统注意编码，否则结果乱码
            String suc = susStream.toString("GBK");
            String err = errStream.toString("GBK");
            System.out.println(suc);
            System.out.println(err);
        }


        if (Objects.equals(args[0], "3")) {
            System.out.println("apache3");
            String command = "sh /opt/tsinglink/run_ts_alg_cvt_ss626.sh";
            ByteArrayOutputStream susStream = new ByteArrayOutputStream();
            ByteArrayOutputStream errStream = new ByteArrayOutputStream();
            CommandLine commandLine = CommandLine.parse(command);
            DefaultExecutor exec = new DefaultExecutor();
//设置一分钟超时
            ExecuteWatchdog watchdog = new ExecuteWatchdog(60*1000);
            exec.setWatchdog(watchdog);
            PumpStreamHandler streamHandler = new PumpStreamHandler(susStream, errStream);
            exec.setStreamHandler(streamHandler);
            try {
                int code = exec.execute(commandLine);
                System.out.println("result code: " + code);
                // 不同操作系统注意编码，否则结果乱码
                String suc = susStream.toString("GBK");
                String err = errStream.toString("GBK");
                System.out.println(suc+err);
            } catch (ExecuteException e) {
                if (watchdog.killedProcess()) {
                    // 被watchdog故意杀死
                    System.err.println("超时了");
                }
            }
        }




    }
}
