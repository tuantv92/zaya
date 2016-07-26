package com.zaya.job;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.zaya.callback.Callback;
import com.zaya.task.BaseTask;
import com.zaya.task.Task;

public abstract class BaseJob implements Job {
	private Callback callback;
	private Callback completing;
	private JobResult result;
	private final Map<String, Object> environmentVariables = new ConcurrentHashMap<>();

	@Override
	@Deprecated
	public Object start(Object data) {
		Task firstTask = getFirstTask();
		if (firstTask instanceof BaseTask) {
			((BaseTask) firstTask).setInitEnviroments(environmentVariables);
		}
		if (firstTask != null) {
			this.setCompleting(new Callback() {

				@Override
				public void call(Object result) {
					onJobCompleted((JobResult) result);
				}
			});
			return firstTask.excute(data);
		}
		return null;
	}

	public abstract void onJobCompleted(JobResult result);

	@Override
	public Callback getCallback() {
		return callback;
	}

	@Override
	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	@Override
	public Callback getCompleting() {
		return completing;
	}

	private void setCompleting(Callback completing) {
		this.completing = completing;
	}

	public JobResult getResult() {
		return result;
	}

	public void setResult(JobResult result) {
		this.result = result;
	}

	public void setEnviromentVariables(Map<String, Object> variables) {
		this.environmentVariables.putAll(variables);
	}

	public Map<String, Object> getEnvironmentVariables() {
		return environmentVariables;
	}

}
