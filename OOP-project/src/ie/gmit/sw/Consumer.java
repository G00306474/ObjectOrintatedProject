package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Consumer implements Runnable {
	private BlockingQueue<Shingle> q;
	private int k;
	private int[] minHashes;
	private Map<Integer, List<Integer>> map = new HashMap<>();
	private ExecutorService pool;

	public Consumer(BlockingQueue<Shingle> q, int k, int poolSize) {
		super();
		this.q = q;
		this.k = k;
		pool = Executors.newFixedThreadPool(poolSize);
		init();
	}

	public void init() {
		Random random = new Random();
		minHashes = new int[k];
		for (int i = 0; i < minHashes.length; i++) {
			minHashes[i] = random.nextInt();
		}
	}

	public void run(){
		try {
			int docCount = 2;
			while (docCount > 0) {
				Shingle s = q.take();
				if (s instanceof Poision) {
					docCount--;
				} else {
					pool.execute( new Runnable() {
						public void run() {
							for (int i = 0; i < minHashes.length; i++) {
								int value = s.getHashCode() ^ minHashes[i]; // ^ - xor(Random generated key)
								List<Integer> list = map.get(s.getDocId());
								if (list == null) {
									list = new ArrayList<Integer>(k);
									for (int j = 0; j < list.size(); j++) 
									{
										list.set(j , Integer.MAX_VALUE);
									}
									map.put(s.getDocId(), list);
								} else {
									if (list.get(i) > value) {
										list.set(i, value);
									}
								}
							}
						}
					});
					
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
