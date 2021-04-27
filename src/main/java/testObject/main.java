package testObject;

import com.alibaba.fastjson.JSON;
import test.model.Task;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        Task task=new Task();
        List<Task> taskList=new ArrayList<>();
        for(int i=0;i<5;i++){
          //  task=new Task();
            task.setObjectid("iii"+i);
            taskList.add(task);
        }
        System.out.println(JSON.toJSONString(taskList.get(1)));
        System.out.println(JSON.toJSONString(taskList.get(2)));
     }
}
