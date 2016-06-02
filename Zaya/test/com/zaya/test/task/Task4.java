package com.zaya.test.task;

import com.zaya.task.BaseTask;
import com.zaya.task.Task;

public class Task4 extends BaseTask {

	@Override
	public Object _execute(Object data) {
		System.out.println("execute task 4...");
		return null;
	}

	@Override
	public Task getNextTask(Object result) {
		return new Task3();
	}

}
