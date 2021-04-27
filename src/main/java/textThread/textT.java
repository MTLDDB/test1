package textThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class textT {
    public static void main(String[]args) {

        BlockingQueue<Runnable> queue=new LinkedBlockingQueue<Runnable> ();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,6,1, TimeUnit.DAYS,queue);

        for(int i=0;i<20; i++){
            executor.execute(new Runnable(){
                int j=0;

                public void run() {
                    try{
                        System.out.println(j++);
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(j);
                    System.out.println(String.format("thread　%d　finished",this.hashCode()));
                }
            });
        }
        executor.shutdown();
    }
}
