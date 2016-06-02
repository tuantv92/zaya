package com.zaya.test.task;

import com.zaya.task.BaseTask;
import com.zaya.task.Task;

public class Task2 extends BaseTask {

	@Override
	public Object _execute(Object data) {
		System.out.println(Thread.currentThread().getName() + ": " + "execute task 2...");
		return null;
	}

	@Override
	public Task getNextTask(Object result) {
		if (result != null) {
			return new Task3();
		}
		return null;
	}

}
