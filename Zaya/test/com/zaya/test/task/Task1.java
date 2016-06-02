package com.zaya.test.task;

import com.zaya.task.BaseTask;
import com.zaya.task.Task;

public class Task1 extends BaseTask {

	@Override
	public Object _execute(Object data) {
		System.out.println(Thread.currentThread().getName() + ": " + "execute task 1...");
		return null;
	}

	@Override
	public Task getNextTask(Object result) {
		return new Task2();
	}

}
