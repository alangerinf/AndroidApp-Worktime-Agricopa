package pe.worktime.util;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;

public abstract class ProgressHandler extends Handler {

	protected abstract ProgressDialog getProgressDialog();
	private AbstractActivity activity;
	
	public ProgressHandler(AbstractActivity activity) {
		super();
		this.activity = activity;
	}

	public void handleMessage(Message msg) {
		int total = msg.arg1;
		getProgressDialog().setProgress(total);
		if (total >= 100) {
			activity.dismissDialog(0);//PROGRESS_DIALOG
		}
	}
	
	public void setMessage(String msg) {
		//getProgressDialog().setMessage(msg);		
		//activity.progressDialog.setMessage(msg);
	}
	
	public void dismissDialog() {
		activity.dismissDialog(0);
	}
	
}
