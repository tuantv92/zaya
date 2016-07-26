package com.zaya.test.job;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zaya.event.ZayaEvent;
import com.zaya.workerpool.ZayaWorkerPool;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		ZayaWorkerPool pool = new ZayaWorkerPool(1, 1024);

		System.out.println(Thread.currentThread().getName() + ": " + "start execute job...");
		ExecutorService executor = Executors.newCachedThreadPool();
		CountDownLatch startSignal = new CountDownLatch(1);
		int count = 10;
		int numOperationPerThread = 100;
		CountDownLatch doneSignal = new CountDownLatch(count);
		for (int i = 0; i < count; i++) {
			executor.submit(new Runnable() {
				public void run() {
					try {
						startSignal.await();
						try {
							for (int j = 0; j < numOperationPerThread; j++) {
								pool.execute(new MyJob1(), null);
							}
						} finally {
							doneSignal.countDown();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		startSignal.countDown();
		doneSignal.await();
		pool.shutdown();
		System.out.println(ZayaEvent.count.get());
		System.exit(0);
	}

}
