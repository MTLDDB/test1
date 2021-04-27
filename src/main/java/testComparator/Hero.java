package testComparator;

import java.util.Random;

public class Hero {
    public int num;
    private int id;
    Hero(int i){
        Random r =new Random();
        this.id=i;
        this.num=r.nextInt();
    }
}
