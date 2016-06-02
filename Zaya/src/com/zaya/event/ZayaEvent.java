package com.zaya.event;

import com.zaya.job.Job;

public class ZayaEvent {

	private Job job;
	private Object data;

	public ZayaEvent() {
	}

	public ZayaEvent(Job job, Object data) {
		this.setJob(job);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
}
