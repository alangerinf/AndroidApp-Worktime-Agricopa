package pe.worktime.view;

public class ProgressMsg {

	private int progress;
	private String message;


	public ProgressMsg() {
		super();
	}

	public ProgressMsg(int progress, String message) {
		super();
		this.progress = progress;
		this.message = message;
	}

	public int getProgress() {
		return progress;
	}

	
	public void setProgress(int progress) {
		this.progress = progress;
	}

	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

}

