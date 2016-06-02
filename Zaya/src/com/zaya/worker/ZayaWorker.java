package com.zaya.worker;

import com.zaya.callback.Callback;
import com.zaya.job.Job;
import com.zaya.job.factory.JobFactory;

public interface ZayaWorker {

	void execute(JobFactory job, Object data);

	void execute(Job job, Object data);

	void execute(Class<? extends Job> job, Object data) throws Exception;

	void execute(JobFactory jobFactory, Object data, Callback callback);

	void execute(Job job, Object data, Callback callback);

	void shutdown();
}
