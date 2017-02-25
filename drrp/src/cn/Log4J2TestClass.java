package cn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J2TestClass {
    // 配置文件中logger的名字
    static Logger logger = LogManager.getLogger("test1Logger");
    static Logger logger2 = LogManager.getLogger("test2Logger");

    public static void main(String[] args) {

        logger.info("log1++++++++++");

        logger2.info("log2++++++++++");
    }

}
