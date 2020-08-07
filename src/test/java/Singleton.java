package test.java;

public class Singleton {
    //==================================================
    //懒汉模式
//    private static Singleton instance ;
//
//    private Singleton() {}
//
//    //线程安全，因为有同步锁
//    public static synchronized Singleton getInstance() {
//        if(instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }
    //==================================================

    //饿汉模式
//    private static Singleton instance = new Singleton();
//
//    private Singleton() {}
//
//    public static Singleton getInstance() {
//        return instance;
//    }
    //==================================================

    //双检锁/双重校验锁（DCL，即 double-checked locking）
    //==================================================
//    private volatile static Singleton singleton;
//
//    private Singleton() {}
//
//    public static Singleton getSingleton() {
//        if (singleton == null) {
//            synchronized (Singleton.class) {
//                if (singleton == null) {
//                    singleton = new Singleton();
//                }
//            }
//        }
//        return singleton;
//    }
    //==================================================

    //登记式/静态内部类
    //==================================================
//    public static class SingletonHolder{
//        private static final Singleton SINGLETON = new Singleton();
//    }
//
//    private Singleton() {};
//
//    public static final Singleton getInstance() {
//        return SingletonHolder.SINGLETON;
//    }
    //==================================================

    //枚举类
    //==================================================
    //turn to SingletonEnum.java


    public void show() {
        System.out.println("hello world");
    }
}
