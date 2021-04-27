package test.DynamicProxy;



import java.util.Random;

public class Bird implements Flyable {
    @Override
    public void fly(String name) {

        System.out.println(name+"\tBird is flying...");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
