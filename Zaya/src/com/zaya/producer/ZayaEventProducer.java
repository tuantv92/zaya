package com.zaya.producer;

import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.RingBuffer;
import com.zaya.event.ZayaEvent;
import com.zaya.job.Job;

public class ZayaEventProducer {

	private final RingBuffer<ZayaEvent> ringBuffer;
	private EventTranslatorTwoArg<ZayaEvent, Job, Object> translator;

	public ZayaEventProducer(RingBuffer<ZayaEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
		this.setTranslator(new EventTranslatorTwoArg<ZayaEvent, Job, Object>() {

			@Override
			public void translateTo(ZayaEvent event, long sequence, Job job, Object data) {
				ZayaEvent zayaEvent = ringBuffer.get(sequence);
				zayaEvent.setData(data);
				zayaEvent.setJob(job);
			}
		});
	}

	public void onEvent(Job job, Object data) {
		ringBuffer.publishEvent(getTranslator(), job, data);
	}

	public EventTranslatorTwoArg<ZayaEvent, Job, Object> getTranslator() {
		return translator;
	}

	private void setTranslator(EventTranslatorTwoArg<ZayaEvent, Job, Object> translator) {
		this.translator = translator;
	}

}
