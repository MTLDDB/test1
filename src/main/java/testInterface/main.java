package testInterface;

import java.util.ArrayList;
import java.util.List;

public class main {
    List<String> list=new ArrayList<>();

    public static void main(String[] args) {
        main main=new main();
        main.test();
    }
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("lll");
        list.add("kkk");
        ThreadPoolTool<String> tool = new ThreadPoolTool(1,list);
        tool.setCallBack(new ThreadPoolTool.CallBack<String>() {
            @Override
            public void method(List<String> list) {
                if(list.size()==0){
                    System.out.println("null");
                }
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(Thread.currentThread().getId()+":"+list.get(i)+" ");
                }
                System.out.println();
            }
        });
        try {
            tool.excute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
