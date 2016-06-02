package com.zaya.handler;

import com.lmax.disruptor.WorkHandler;
import com.zaya.event.ZayaEvent;
import com.zaya.job.Job;
import com.zaya.job.JobResult;

public class ZayaEventHandler implements WorkHandler<ZayaEvent> {

	@Override
	public void onEvent(ZayaEvent event) throws Exception {
		Job job = event.getJob();
		if (job != null) {
			try {
				Object result = job.start(event.getData());
				job.setResult(new JobResult(true, result, null));
				job.getCompleting().call(job.getResult());
				if (job.getCallback() != null) {
					job.getCallback().call(job.getResult());
				}
			} catch (Exception e) {
				e.printStackTrace();
				job.setResult(new JobResult(false, null, e));
			}
		}
	}

}
