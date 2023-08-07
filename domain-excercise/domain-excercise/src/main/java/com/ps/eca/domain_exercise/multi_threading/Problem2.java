package com.ps.eca.domain_exercise.multi_threading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * How do you implement producer consumer problem where there are ten producers and ten consumers.
 */
public class Problem2 {

    private static final int QUEUE_CAPACITY = 5; // Capacity of the shared BlockingQueue
    private static final int NUM_PRODUCERS = 10;
    private static final int NUM_CONSUMERS = 10;

    static BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    static AtomicInteger item = new AtomicInteger(1);

    public static void main(String[] args) {

        // Create and start producer threads
        for (int i = 0; i < NUM_PRODUCERS; i++) {
            Thread producerThread = new Thread(() -> {
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            producerThread.setName("Producer: " + i);
            producerThread.start();
        }

        // Create and start consumer threads
        for (int i = 0; i < NUM_CONSUMERS; i++) {
            Thread consumerThread = new Thread(() -> {
                try {
                    consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            consumerThread.setName("Consumer: " + i);
            consumerThread.start();
        }
    }

    private static void produce() throws InterruptedException {
        while (true) {
            // Produce an item
            int itemInt = item.getAndIncrement();
            System.out.println("Producing: " + itemInt + " by:- " + Thread.currentThread().getName());
            // Put the item into the queue (will wait if the queue is full)
            sharedQueue.put(itemInt);
            // Introduce a small delay to simulate production time
            Thread.sleep(1000);
        }
    }

    private static void consume() throws InterruptedException {
        while (true) {
            // Take an item from the queue (will wait if the queue is empty)
            Integer itemInt = sharedQueue.take();
            // Consume the item
            System.out.println("Consuming: " + itemInt + " by:- " + Thread.currentThread().getName());
            // Introduce a small delay to simulate consumption time
            Thread.sleep(5000);
        }
    }
}
