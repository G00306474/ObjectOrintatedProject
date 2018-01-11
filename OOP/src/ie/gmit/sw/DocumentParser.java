package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.BlockingQueue;

public class DocumentParser implements Runnable {
	private int docId;// = Shingle.getDocId();
	private Deque<String> buffer = new LinkedList<>();
	private BlockingQueue<Shingle> q;
	private String file;
	private int ss, k;

	/**
	 * Program enters document parser after 
	 */
	public DocumentParser(String file,BlockingQueue<Shingle> q,  int ss, int k,int docId) {
		super();
		
		this.docId = docId;
		this.q = q;
		this.file = file;
		this.ss = ss;
		this.k = k;
	}
	@Override
	public void run() {

		try {
		 	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			//BufferedReader br = new BufferedReader(new FileReader(new FileInputStream(file)));
			String line = null;
			while ((line = br.readLine()) != null) {
				String uLine = line.toUpperCase();
				String[] words = uLine.split("");
				addWordsToBuffer(words);
				Shingle s = getNextShingle();
				q.put(s);
			}
			flushBuffer();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addWordsToBuffer(String[] words) {
		for (String s : words) {
			buffer.addLast(s);
			//System.out.print(s);//print to screen what goes through buffer
		}

	}

	private Shingle getNextShingle() {
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		while (counter < ss) {
			if (buffer.peek() != null) {
				sb.append(buffer.poll());
				counter++;
			}
		}
		if (sb.length() > 0) {
			
			return (new Shingle(docId, sb.toString().hashCode()));
		} else {
			return null;
		}

	}

	private void flushBuffer() throws Exception{
		while(buffer.size()>0) {
			Shingle  s = getNextShingle();
			if(s!=null) {
				q.put(s);
			}else {
				q.put(new Posion(docId,0)); 
			}
		}
	}

}
