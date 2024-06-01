package com.shane.kafka;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore s1 = new Semaphore(0);
        Semaphore s2 = new Semaphore(0);
        Thread t1 = new Thread(() -> {
            System.out.println("A");
            s1.release();
        });

        Thread t2 = new Thread(() -> {
            try {
                s1.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("B");
            s2.release();
        });

        Thread t3 = new Thread(() -> {
            try {
                s2.acquire();
                System.out.println("C");
                s2.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        t3.start();
        Thread.sleep(100);
        t1.start();
        Thread.sleep(100);
        t2.start();

    }
}
