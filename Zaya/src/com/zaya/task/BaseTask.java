package com.zaya.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseTask implements Task {
	private final Map<String, Object> environmentVariables = new ConcurrentHashMap<>();

	@Override
	public final Object excute(Object data) {
		Object result = _execute(data);
		Task nextTask = getNextTask(result);
		if (nextTask instanceof BaseTask) {
			((BaseTask) nextTask).setInitEnviroments(environmentVariables);
		}
		if (nextTask != null) {
			return nextTask.excute(result);
		}
		return result;
	}

	protected abstract Object _execute(Object data);

	/**
	 * @return null if have no next task
	 */
	public abstract Task getNextTask(Object result);

	public void setInitEnviroments(Map<String, Object> enviroments) {
		this.environmentVariables.putAll(enviroments);
	}

	public Map<String, Object> getEnviromentVariables() {
		return this.environmentVariables;
	}
}
