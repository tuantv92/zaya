package com.zaya.job;

import com.zaya.callback.Callback;
import com.zaya.task.Task;

public interface Job {

	Task getFirstTask();

	/**
	 * 
	 * @param data for first task
	 * @return result after process all tasks
	 */
	Object start(Object data);

	Callback getCallback();

	void setCallback(Callback callback);

	Callback getCompleting();

	void setResult(JobResult result);

	JobResult getResult();
	
	
}
