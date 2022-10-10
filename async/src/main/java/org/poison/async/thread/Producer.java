package org.poison.async.thread;

import lombok.SneakyThrows;

public class Producer implements Runnable {

    private Box box;

    public Producer(Box box) {
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
            for (int i = 0; i < 5; i++) {
                if (box.getStock() > 0) {
                    box.wait();
                }
                Thread.sleep(100);
                box.add();
                box.notify();
            }
        }

    }
}
