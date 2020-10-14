package ThreadDemo.LinkedTransferQueueDemo;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author zhoushiya
 * @date 2020/10/13 14:55
 */
public class LinkedTransferQueueDemo {


    /**
     * 生产者线程
     */
    private static class ProducerRunnable implements Runnable {

        private LinkedTransferQueue<TempObject> linkedQueue;

        public ProducerRunnable(LinkedTransferQueue<TempObject> linkedQueue) {
            this.linkedQueue = linkedQueue;
        }

        @Override
        public void run() {
            for(int index = 1 ; index< 10 ; index++) {
                try {
                    // 向LinkedTransferQueue队列插入一个新的元素
                    // 然后生产者线程就会等待，直到有一个消费者将这个元素从队列中取走
                    this.linkedQueue.transfer(new TempObject(index));
                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
    }

    /**
     * 消费者线程
     */
    private static class ConsumerRunnable implements Runnable {

        private LinkedTransferQueue<TempObject> linkedQueue;

        public ConsumerRunnable(LinkedTransferQueue<TempObject> linkedQueue) {
            this.linkedQueue = linkedQueue;
        }

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            while(!currentThread.isInterrupted()) {
                try {
                    // 等待，直到从LinkedTransferQueue队列中得到一个元素
                    System.out.println("线程（" + currentThread.getId() + "）尝试取得元素");
                    TempObject targetObject = this.linkedQueue.take();
                    System.out.println("线程（" + currentThread.getId() + "）取得targetObject.index = " + targetObject.getIndex());
                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {

        LinkedTransferQueue<TempObject> linkedQueue = new LinkedTransferQueue<TempObject>();
        // 这是一个生产者线程
        Thread producerThread = new Thread(new ProducerRunnable(linkedQueue));
        // 这里有两个消费者线程
        Thread consumerRunnable1 = new Thread(new ConsumerRunnable(linkedQueue));
        Thread consumerRunnable2 = new Thread(new ConsumerRunnable(linkedQueue));

        // 开始运行
        producerThread.start();
        consumerRunnable1.start();
        consumerRunnable2.start();
    }
}
