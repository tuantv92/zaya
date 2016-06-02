package com.zaya.job;

public class JobResult {

	private Object data;
	private boolean isSuccess;
	private Exception exception;

	public JobResult(boolean isSuccess, Object data, Exception e) {
		setSuccess(isSuccess);
		setData(data);
		setException(e);
	}

	public JobResult() {
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
