package com.dxc.case6;

public class Excel_Database  {

	public static void main(String[] args) throws InterruptedException {		

		Thread1 t1 = new Thread1();
		Thread thread1 = new Thread(t1);
		thread1.start();	
		
		Thread2 t2 = new Thread2();
		Thread thread2 = new Thread(t2);
		thread2.start();
		
		Thread3 t3 = new Thread3();
		Thread thread3 = new Thread(t3);
		thread3.start();

	}

}