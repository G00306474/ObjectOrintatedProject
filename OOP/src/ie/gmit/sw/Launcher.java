package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author KMora
 *The Launcher is where the threads are created. There are three threads created. They are initiated with the variables created in the menu class. 
 *The first two threads each take a in a file and pass them to the document parser. 
 * The third thread passes to the consumer class 
 */
public class Launcher {
	
	static Menu m = new Menu();
	
	public static  void launch(String f1, String f2) throws Exception{
		
		BlockingQueue<Shingle> q = new LinkedBlockingQueue<>(m.getBlockingQSize());
		Thread t1 = new Thread(new DocumentParser(f1,q,m.getShingleSize(),m.getK(),1),"T1");
		Thread t2 = new Thread(new DocumentParser(f2, q, m.getShingleSize(), m.getK(),2), "T2");
	    Thread t3 = new Thread(new Consumer( q,m.getK(),m.getThrPoolSize()),"T3");
	    //new Consumer(q,200,10),"T3");
		t1.start();
		t2.start();
	  	t3.start();
		
		t1.join();
		t2.join();
		t3.join();
	}
}
