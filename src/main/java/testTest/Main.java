package testTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : huang
 * @date :  2021/2/3
 * @time : 11:22
 * To change this template use File | Settings | File and Code Templates.
 */
public class Main {
}
class Leaf {
    private int i = 0; //此属性值用于检验
    Leaf increment(){ //定义方法increment()，返回值是Leaf类的对象
        i++;
        System.out.println(" i = " + i);
        return this;//将当前对象的地址作为返回值返回
    }
    void print() { System.out.println(" i = " + i); }
    public static void main(String args[]){
        Leaf x =new Leaf(); //创建Leaf类的对象x
        x.increment().increment().increment().print();
    }//多次调用方法increment()，返回的都是x的地址，i 值表示调用次数
}