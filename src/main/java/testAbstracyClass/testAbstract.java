package testAbstracyClass;

public abstract class testAbstract {
    private static testAbstract a = null;

    public abstract void Comp();
    public static testAbstract get() {
        return a;
    }
    public static testAbstract build() {
        System.out.println("build");
        a = new Child();
        return a;
    }
}
