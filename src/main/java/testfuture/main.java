package testfuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class main {
    private static final Logger logger = LoggerFactory.getLogger(main.class);
    public static void main(String args[]){
        try {
            pool.start().ma();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bindJVMHook();
        try {
            Thread.sleep(1*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
    private static void bindJVMHook() {
//        logger.info("添加勾子程序",Runtime.getRuntime().maxMemory());
//        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
//            logger.info("huanbi");
//            pool.start().stop();
//        }));

    }

}

