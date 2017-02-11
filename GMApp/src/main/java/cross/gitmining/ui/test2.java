package cross.gitmining.ui;

/**
 * Created by raychen on 16/4/17.
 */
public class test2 {
    class A{
        public int b;
    }
    class B{
        public A a;
    }

    public void test(){
        B b = new B();
        A a = new A();
        b.a = a;

//        b.a
    }

}
