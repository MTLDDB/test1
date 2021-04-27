package testruntime;

import com.sun.corba.se.spi.activation.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class runTimeThread extends Thread {
        private static final Logger log = LoggerFactory.getLogger(runTimeThread.class);


        public void run() {
            log.info("开始停止");

        }
    }

