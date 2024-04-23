package org.example;

import java.util.concurrent.*;

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
        // implementThread.Test1();
        // implementThread.Test2();
//        try {
//            implementThread.Test3();
//        } catch (ExecutionException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        implementThread.Test4();
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
    /*
     *
     * @Author TanYingHao
     * @Description 第二种方式，实现Runnable接口
     * @Date 20:29 2024/4/23
     **/
    static class Thread2 implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread1:" + Thread.currentThread().getName() + " " + i);
            }
        }
    }

    public void Test2() {
        Thread t1 = new Thread(new Thread2());
        Thread t2 = new Thread(new Thread2());
        t1.start();
        t2.start();
    }
    /**
     *
     * @Author TanYingHao
     * @Description 第三种方式，通过实现callable接口，传入到FutureTask中，可以获取返回值
     * @Date 20:49 2024/4/23
     **/
    static class Call implements Callable<Integer> {
        int flag;
        Call (int f) {
            flag = f;
        }
        @Override
        public Integer call() throws Exception {
            System.out.println("Call:" + Thread.currentThread().getName());
            return flag;
        }
    }
    public void Test3() throws ExecutionException, InterruptedException {
        FutureTask<Integer> ft1 = new FutureTask<Integer>(new Call(0));
        FutureTask<Integer> ft2 = new FutureTask<Integer>(new Call(1));
        Thread thread1 = new Thread(ft1);
        Thread thread2 = new Thread(ft2);
        thread1.start();
        thread2.start();
        System.out.println(ft1.get());
        System.out.println(ft2.get());
    }
    /**
     *
     * @Author TanYingHao
     * @Description 线程实现的第四种，通过线程池来实现
     * @Date 20:58 2024/4/23
     **/
    public void Test4() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            Future<Integer> submit = executorService.submit(new Call(i));
            try {
                System.out.println(submit.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();
    }

}
