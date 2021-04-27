package testScheduleThread;

import org.junit.Test;

import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class main {
    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) {
         main main=new main();
         for(int i=0;i<100;i++){
             main.test(i);
         }









    }
    public void test(int i){
        String ii="test"+i;
        System.out.println(ii);
//        scheduledExecutorService.schedule(new Thread(()->{
//            System.out.println("+++"+ii);
//        }), 11, TimeUnit.SECONDS);
    }
}
