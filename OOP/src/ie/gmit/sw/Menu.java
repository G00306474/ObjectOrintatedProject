package ie.gmit.sw;

import java.util.Scanner;

/**
 * @author KMora
 *Menu where the program takes in the file names to compare. It is also where the variables shingleSize = 3, k = 200, blockingQSize = 100,
 *thrPoolSize = 10 are set. They are hardcoded cause when they are taking in as user input the program crashes. I commented out the option 
 *for user input but left in the code for future use.
 * 
 */
public class Menu {
	 
	public int getShingleSize() {
		return shingleSize;
	}
	public void setShingleSize(int shingleSize) {
		this.shingleSize = shingleSize;
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public int getThrPoolSize() {
		return thrPoolSize;
	}
	public void setThrPoolSize(int thrPoolSize) {
		this.thrPoolSize = thrPoolSize;
	}
	public int getBlockingQSize() {
		return blockingQSize;
	}
	public void setBlockingQSize(int blockingQSize) {
		this.blockingQSize = blockingQSize;
	}
	Scanner Scanner = new Scanner(System.in);
	private String doc1;
	private String doc2;
	private int shingleSize;
	private int k;
	private int blockingQSize;
	private int thrPoolSize;
	private int option;

	public Menu() {
		doc1 = new String();
		doc2 = new String();
		shingleSize = 3;
		k = 200;
		blockingQSize = 100;
		thrPoolSize = 10;
		option = 0;
		/*
		shingleSize = 3;
		k = 10;
		blockingQSize = 10;
		thrPoolSize = 5;
		*/
		
	}
 	//  GetDoc gDoc = new GetDoc();
	public void show() {
		
			
				System.out.println("please enter the first document url");
				doc1 = Scanner.next();
				//gDoc.setPath(doc1);
				//doc1 = gDoc.getPath();
				System.out.println(doc1);
			
				System.out.println("please enter the second document url");
				doc2 = Scanner.next();
				//gDoc.setPath(doc2);
				//doc2 = gDoc.getPath();
				System.out.println(doc2);
		/*		 
				System.out.println("Please enter the Shingle size.");
				shingleSize = Scanner.nextInt();
			 
				System.out.println("Please enter the value for k (amount of minhash).");
				k = Scanner.nextInt();
				 
			//	System.out.println("Please enter the size of the Blocking Queue.");
			//	int bqs = Scanner.nextInt();
			//	setBlockingQSize(bqs);
				 
				System.out.println("Please enter the ThreadPool Size.");
				thrPoolSize = Scanner.nextInt();
			*/
				try {
					System.out.println("please "+doc1);
					Launcher.launch(doc1,doc2);
				} catch (Exception e) {
					 
					e.printStackTrace();
				}				
			}	
	}
	

