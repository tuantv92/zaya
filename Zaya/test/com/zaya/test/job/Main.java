package com.zaya.test.job;

import com.zaya.workerpool.ZayaWorkerPool;

public class Main {

	public static void main(String[] args) {

		ZayaWorkerPool pool = new ZayaWorkerPool(1, 1024);
		System.out.println(Thread.currentThread().getName() + ": " + "start execute job...");
		pool.execute(MyJob1::new, null);
		pool.shutdown();
	}

}
