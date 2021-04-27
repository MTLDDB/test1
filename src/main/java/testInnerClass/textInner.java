package testInnerClass;

public class textInner {
    public void connection()  {

        Ot o=new Ot("jjj") {
            public void hand(String a) {
                System.out.println("hello");
                a="aa";
                System.out.println(a);
            }
        };
    }
    public static void main(String args[]) {
        textInner t=new textInner();
        t.connection();

    }
}
