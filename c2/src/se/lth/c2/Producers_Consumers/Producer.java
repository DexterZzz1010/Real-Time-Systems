package se.lth.c2.Producers_Consumers;

public class Producer extends Thread {
	private RingBufferInterface rb;
	
	public Producer(RingBufferInterface r) {
		rb = r;
	}
	
	public void run() {
		int i = 0;
		try {
			while(!Thread.interrupted()) {
				++i;
				System.out.println("Producer: Attempting to write msg nbr " + i);
				rb.put("Msg nbr " + i);
				System.out.println("Producer: Msg nbr " + i + " written");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// Thread interrupted
		}
		
		System.out.println("Producer stopped.");
	}
}
