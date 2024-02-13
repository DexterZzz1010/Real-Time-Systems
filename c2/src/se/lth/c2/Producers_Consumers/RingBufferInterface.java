package se.lth.c2.Producers_Consumers;

/*
 * interface RingBufferInterface:
 *
 * Interface for ringbuffers
 */
interface RingBufferInterface {

    /*
     * method get:
     *
     * @throws:
     *     InterruptedException
     * @return:
     *     Object: The next object in the buffer
     */
    public Object get() throws InterruptedException;

    /*
     * method put:
     *
     * @throws:
     *     InterruptedException
     * @param:
     *     o (Object): object to enter into ringbuffer.
     */
    public void put(Object o) throws InterruptedException; 
}
