package com.ps.eca.domain_exercise.multi_threading;

import java.util.concurrent.Semaphore;

/**
 * Three threads t1,t2,t3. t1 prints 1,4,7.. And t2 prints 2,5,8.. and t3 prints 3,6,9….
 * Make them to print 1,2,3,4,5,6,7,8,9… in a sequence and then repeat after 9.
 */
public class Problem1 {

    private static Semaphore s1 = new Semaphore(1);
    private static Semaphore s2 = new Semaphore(0);
    private static Semaphore s3 = new Semaphore(0);

    private static int sequenceNumber = 1;

    public static void main(String[] args) {

        Thread t1 = new Thread(Problem1::thread1);
        Thread t2 = new Thread(Problem1::thread2);
        Thread t3 = new Thread(Problem1::thread3);

        t1.start();
        t2.start();
        t3.start();
    }

    public static void thread1() {
        while (true) {
            try {
                s1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(sequenceNumber);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sequenceNumber++;
            s2.release();
            if (sequenceNumber > 9) {
                sequenceNumber = 1;
            }
        }
    }

    public static void thread2() {
        while (true) {
            try {
                s2.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(sequenceNumber);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sequenceNumber++;
            s3.release();
            if (sequenceNumber > 9) {
                sequenceNumber = 1;
            }
        }
    }

    public static void thread3() {
        while (true) {
            try {
                s3.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(sequenceNumber);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sequenceNumber++;
            s1.release();
            if (sequenceNumber > 9) {
                sequenceNumber = 1;
            }
        }
    }
}
