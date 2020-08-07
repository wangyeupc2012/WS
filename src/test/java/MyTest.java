package test.java;

public class MyTest {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("aaa");
        myThread.start();
        System.out.println(myThread.getName());
    }
}
