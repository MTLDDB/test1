package testfuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

public  class pool {
    private static final Logger logger = LoggerFactory.getLogger(main.class);
    static BlockingQueue<Runnable> queue=new LinkedBlockingQueue<Runnable>();
    private  ExecutorService taskpool = null;
    public  ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5,3, TimeUnit.SECONDS,queue);
    private static  pool Pool=null;
    public static pool  start(){
        Pool=new pool();
        return Pool;
    }
    public void stop(){
        threadPoolExecutor.shutdown();

        int shutNum = 0;
        try {
            while (!threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS)) {//等待未执行完的线程继续执行
                logger.info("thread pool is shutting");
                if (++shutNum > 5) threadPoolExecutor.shutdownNow();//所有的线程执行完毕，关闭线程池
            }
        } catch (InterruptedException e) {
            threadPoolExecutor.shutdownNow();
            logger.error("未处理错误！", e);
        }
    }

    public  void  ma() throws IOException {
        taskpool = Executors.newCachedThreadPool();//创建缓冲线程池
        logger.info("爬虫启动成功！ maxMemory:{}",Runtime.getRuntime().maxMemory());
        //Runtime.getRuntime().exec("D:\\Program Files\\PremiumSoft\\Navicat Premium 12\\navicat.exe");
        //(1) getRuntime()：该方法用于返回当前应用程序的运行环境对象。
        //(2) exec(String command)：该方法用于根据指定的路径执行对应的可执行文件。
        // freeMemory()：该方法用于返回Java虚拟机中的空闲内存量，以字节为单位。
        //maxMemory()：该方法用于返回Java虚拟机试图使用的最大内存量。
        //totalMemory()：该方法用于返回Java虚拟机中的内存总量。
        for(int i=0;i<10;i++){
            int j=i;
//            taskpool.execute(() -> {
//                Future<Integer> future=threadPoolExecutor.submit(new runable(j));
//                try {
//                    int re=future.get(5,TimeUnit.SECONDS);
//                    System.out.println(re);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (TimeoutException e) {
//                    e.printStackTrace();
//                }
//            });
        }
        //Thread.sleep(5*1000);
    }
}
