package se.lth.c2.Producers_Consumers;

import se.lth.control.realtime.ConditionVariable;

import se.lth.control.realtime.Semaphore;
/*
 *  class RingBufferWithSemaphore:
 *
 * Ringbuffer for the producer-consumer exercise
 */
public class RingBufferWithSemaphore implements RingBufferInterface {

    //TODO C2.E15: Declare private variables //
    private final int bufSize;
    private Object[] elements;
    private int nextRead = 0;
    private int nextWrite = 0;
    private int curSize = 0;

    private Semaphore sem;
    private ConditionVariable nonEmpty;
    private ConditionVariable nonFull;

    /*
     * Constructor for the class RingBufferWithSemaphore
     *
     * @param:
     *     bufSize (int): Size of buffer
     */
    public RingBufferWithSemaphore(int bufSize) {
        //TODO C2.E15: Write your code here //
        this.bufSize = bufSize;
        elements = new Object[bufSize];

        sem = new Semaphore(1);
        nonEmpty = new ConditionVariable(sem);
        nonFull = new ConditionVariable(sem);
    }

    /*
     * method get:
     *
     * @throws:
     *     InterruptedException
     * @return:
     *     Object: The next object in the buffer
     */
    public Object get() throws InterruptedException {
        //TODO C2.E15: Write your code here //
        sem.take();
        while (curSize == 0) {
            nonEmpty.cvWait();
        }
        Object ret = elements[nextRead];
        elements[nextRead] = null;
        nextRead = (nextRead + 1) % bufSize;
        curSize--;
        nonFull.cvNotifyAll();
        sem.give();
        return ret;
    }

    /*
     * method put:
     *
     * @throws:
     *     InterruptedException
     * @param:
     *     o (Object): Object to enter into ringbuffer.
     */
    public void put(Object o) throws InterruptedException {
        //TODO C2.E15: Write your code here //
        sem.take();
        while (curSize == bufSize) {
            nonFull.cvWait();
        }
        elements[nextWrite] = o;
        nextWrite = (nextWrite + 1) % bufSize;
        curSize++;
        nonEmpty.cvNotifyAll();
        sem.give();
    }
}
