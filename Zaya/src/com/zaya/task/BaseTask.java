package com.zaya.task;

public abstract class BaseTask implements Task {

	@Override
	public final Object excute(Object data) {
		Object result = _execute(data);
		Task nextTask = getNextTask(result);
		if (nextTask != null) {
			return nextTask.excute(result);
		}
		return result;
	}

	public abstract Object _execute(Object data);

	/**
	 * @return null if have no next task
	 */
	public abstract Task getNextTask(Object result);

}
