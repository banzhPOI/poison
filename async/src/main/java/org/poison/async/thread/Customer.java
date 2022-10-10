package org.poison.async.thread;

import lombok.SneakyThrows;

public class Customer implements Runnable {

    private Box box;

    public Customer(Box box) {
        this.box = box;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @SneakyThrows
    @Override
    public void run() {
        synchronized (box) {
            while (true) {
                if (box.getStock() == 0) {
                    box.wait();
                }
                Thread.sleep(100);
                box.get();
                box.notify();
            }
        }
    }
}
