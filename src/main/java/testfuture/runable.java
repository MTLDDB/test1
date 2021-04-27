package testfuture;

import java.util.concurrent.Callable;

public class runable implements Callable {
    int i;

    public runable(int i){
        this.i=i;
    }

    public Object call() throws Exception {

        int  n= (int) (1000000000*Math.random());
        int t = (int) (10*Math.random());
        int d=n;
        //while(i<9){
        Thread.sleep(1000*t);
       // }
        while(n>i){
            n--;
        }
        String s= t+" s "+ i + "和" + d;
        System.out.println(s);
        return i;
    }
}
