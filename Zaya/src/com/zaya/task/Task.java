package com.zaya.task;

public interface Task {

	Object excute(Object data);

	Task getNextTask(Object result);

}
