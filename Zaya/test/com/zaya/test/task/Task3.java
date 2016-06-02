package com.zaya.test.task;

import com.zaya.task.BaseTask;
import com.zaya.task.Task;

public class Task3 extends BaseTask {

	@Override
	public Object _execute(Object data) {
		System.out.println("execute task 3...");
		return null;
	}

	@Override
	public Task getNextTask(Object result) {
		// TODO Auto-generated method stub
		return null;
	}

}
