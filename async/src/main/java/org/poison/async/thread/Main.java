package org.poison.async.thread;

public class Main {
    public static void main(String args[]) {

        Box box = new Box();
        Producer p = new Producer(box);
        Customer c = new Customer(box);
        Thread t1 = new Thread(p, "生产");
        Thread t2 = new Thread(c, "消费");
        t1.start();
        t2.start();
    }
}
