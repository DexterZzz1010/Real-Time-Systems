package se.lth.c2.Producers_Consumers;

/**
 * @Time ： 2024/1/28 17:43
 * @Author ： Dexter ZHANG
 * @File ：RingBuffer.java
 * @IDE ：IntelliJ IDEA
 */
public class RingBuffer implements RingBufferInterface {
    private final int bufSize;
    private Object[] elements;
    private int nextRead = 0;
    private int nextWrite = 0;
    private int curSize = 0;

    public RingBuffer(int bufSize) {
        this.bufSize = bufSize;
        elements = new Object[bufSize];
    }

    public synchronized Object get() throws InterruptedException {
        while (curSize == 0) {
            wait(); //如果没东西 ，等待
        }
        Object ret = elements[nextRead];
        elements[nextRead] = null;
        nextRead = (nextRead + 1) % bufSize;
        curSize--;
        notifyAll();
        return ret;
    }

    public synchronized void put(Object o) throws InterruptedException {
        while (curSize == bufSize) {
            wait();//如果写满了 ，等待
        }
        elements[nextWrite] = o;
        nextWrite = (nextWrite + 1) % bufSize;
        curSize++; // 写入之后 东西数量+1
        notifyAll();
    }
}
