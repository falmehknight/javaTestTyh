package org.example;

/**
 * @ClassName ImplementThread
 * @Description 多线程实现方式
 * @Author 谭颍豪
 * @Date 2024/4/22 23:12
 * @Version 1.0
 **/
public class ImplementThread {

    public static void main(String[] args) {
        ImplementThread implementThread = new ImplementThread();
        implementThread.Test1();
    }
    /**
     *
     * @Author TanYingHao
     * @Description 第一种方式，继承Thread类
     * @Date 23:15 2024/4/22
     **/
    static class Thread1 extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread1:" + Thread.currentThread().getName() + " " + i);
            }
        }
    }
    public void Test1() {
        Thread myThread1 = new Thread1();
        Thread myThread2 = new Thread1();
        myThread1.start();
        myThread2.start();
    }

}
