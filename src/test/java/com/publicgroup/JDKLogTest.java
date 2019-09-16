package com.publicgroup;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.publicgroup.util.log.LogFactory;
import com.publicgroup.util.log.LogUtil;
import org.junit.Test;

public class JDKLogTest {

    // 自定义的全局log（个人一般用这个记录）
    private static Logger log = LogFactory.getGlobalLog();
    // Jdk1.7以后自带的全局log（后面我添加了FileHandler，用于写入文件日志）
    private static Logger sysLog = Logger.getGlobal();

    static {
        //由于jdk自带的全局log没有写入文件的功能，我这里手动添加了文件handler
        LogUtil.addFileHandler(sysLog, Level.INFO, LogFactory.logFolder + File.separator + "sys.log");
    }

    @Test
    public void test01() {
        // 级别从上往下依次降低
        log.severe("severe-->   this is severe!");
        log.warning("warning-->   this is warning!");
        log.info("info-->   this is info!");
        log.config("config-->   this is config!");
        log.fine("fine-->   this is fine!");
        log.finer("finer-->   this is finer!");
        log.finest("finest-->   this is finest!");
    }

    @Test
    public void test02() {
        log.info("info-->   this is test02 log");
    }

    @Test
    public void test03() {
        sysLog.info("test03 info!");
    }

    @Test
    public void test04() {
        sysLog.info("test04 info!");
    }
}
