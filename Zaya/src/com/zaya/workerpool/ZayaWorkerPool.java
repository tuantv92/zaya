package com.zaya.workerpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.zaya.callback.Callback;
import com.zaya.event.ZayaEvent;
import com.zaya.handler.ZayaEventHandler;
import com.zaya.job.Job;
import com.zaya.job.factory.JobFactory;
import com.zaya.producer.ZayaEventProducer;
import com.zaya.worker.ZayaWorker;

public class ZayaWorkerPool implements ZayaWorker, ExceptionHandler<ZayaEvent> {

	private final static String threadPatternName = "Zaya thread %d";
	private RingBuffer<ZayaEvent> ringBuffer;
	private Disruptor<ZayaEvent> disruptor;
	private ZayaEventProducer producer;

	public ZayaWorkerPool(int workerSize, int ringBufferSize) {
		this(workerSize, ringBufferSize, threadPatternName);
		System.out.println("init zaya workerpool with " + workerSize + " wokers and " + ringBufferSize + " ringBuffer");
	}

	public ZayaWorkerPool(int workerSize, int ringBufferSize, String threadPatternName) {
		ringBuffer = initWorkerPool(workerSize, ringBufferSize);
		producer = new ZayaEventProducer(ringBuffer);
	}

	private RingBuffer<ZayaEvent> initWorkerPool(int workerSize, int ringBufferSize) {
		ZayaEventHandler[] workers = new ZayaEventHandler[workerSize];
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new ZayaEventHandler();
		}
		this.disruptor = new Disruptor<>(ZayaEvent::new, ringBufferSize, new ThreadFactory() {
			private AtomicInteger threadNumber = new AtomicInteger(1);

			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, String.format(threadPatternName, threadNumber.getAndIncrement()));
			}
		});
		disruptor.handleEventsWithWorkerPool(workers);
		return disruptor.start();
	}

	@Override
	public void execute(JobFactory factory, Object data) {
		Job job = factory.getJob();
		if (job != null) {
			execute(job, data);
		}
	}

	public void execute(Job job, Object data) {
		if (job != null) {
			producer.onEvent(job, data);
		}
	}

	@Override
	public void execute(Job job, Object data, Callback callback) {
		job.setCallback(callback);
		execute(job, data);
	}

	@Override
	public void execute(Class<? extends Job> job, Object data) throws InstantiationException, IllegalAccessException {
		execute(job.newInstance(), data);

	}

	@Override
	public void execute(JobFactory jobFactory, Object data, Callback callback) {
		Job job = jobFactory.getJob();
		execute(job, data, callback);
	}

	@Override
	public void shutdown() {
		if (disruptor != null) {
			disruptor.shutdown();
		}
	}

	@Override
	public void handleEventException(Throwable arg0, long arg1, ZayaEvent arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleOnShutdownException(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleOnStartException(Throwable arg0) {
		// TODO Auto-generated method stub

	}

}
