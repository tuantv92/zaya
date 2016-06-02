package com.zaya.test.job;

import com.zaya.job.BaseJob;
import com.zaya.job.JobResult;
import com.zaya.task.Task;
import com.zaya.test.task.Task1;

public class MyJob1 extends BaseJob {

	@Override
	public Task getFirstTask() {
		return new Task1();
	}

	@Override
	public void onJobCompleted(JobResult result) {
		System.out.println("job is completed: " + (result.isSuccess() ? "successful" : "failed"));
	}

}
