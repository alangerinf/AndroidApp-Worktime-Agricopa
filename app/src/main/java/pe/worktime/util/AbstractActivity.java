package pe.worktime.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.app.Application;

//public abstract class AbstractActivity extends Activity {
public abstract class AbstractActivity extends AppCompatActivity {
	protected static final int PROGRESS_DIALOG = 0;
	protected static final int ERROR_DIALOG = 2;
	protected ProgressThread progressThread= null;
	public ProgressDialog progressDialog = null;	
	protected final ProgressHandler handler = new ProgressHandler(this) {		
		@Override
		protected ProgressDialog getProgressDialog() {
			return progressDialog;
		}              
    };
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		preLoadView();
		setContentView(getViewId());
		loadTitle();
		postLoadView();
		Application.context.getManagerView().actual = this;
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
        switch(id) {
        case PROGRESS_DIALOG:
            progressDialog = new ProgressDialog(AbstractActivity.this);
            //progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("Loading...");
            return progressDialog;
        case ERROR_DIALOG:
        	 progressDialog = new ProgressDialog(AbstractActivity.this); 
        	 progressDialog.setMessage("Info");
            return progressDialog;
        default:
            return super.onCreateDialog(id);
        }
    }

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		View v = getCurrentFocus();
		boolean ret = super.dispatchTouchEvent(event);
		if (v instanceof EditText) {
			View w = getCurrentFocus();
			int scrcoords[] = new int[2];
			w.getLocationOnScreen(scrcoords);
			float x = event.getRawX() + w.getLeft() - scrcoords[0];
			float y = event.getRawY() + w.getTop() - scrcoords[1];
			if (event.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom())) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
			}
		}
		return ret;
	}

	/* Métodos Abstractos */
	protected abstract int getViewId();
	
	protected abstract String getTextTitle();
	
	/* Metodos Pre Definidos */
	protected void postLoadView() {
	}

	protected void preLoadView() {
	}

	protected void loadTitle() {
		if (!showTitle()) {
			View titleView = (View) findViewById(R.id.title);
			if (titleView != null) {
				titleView.setVisibility(View.INVISIBLE);
			}
		} else {
			TextView textView = (TextView) findViewById(R.id.textTitleF);
			if(textView!=null){
				textView.setText(getTextTitle());
			}			
			if (showIconTitle()) {
				//View iconTitleView = (View) findViewById(R.id.i);
				//if (iconTitleView != null) {
					//iconTitleView.setVisibility(View.VISIBLE);
				//}
			}
			if (showBackButtonTitle()) {
				//ImageButton buttonTitleView = (ImageButton) findViewById(R.id.buttonTitle);
				//if (buttonTitleView != null) {
					//buttonTitleView.setVisibility(View.VISIBLE);
				//}
			}
		}
	}

	protected boolean showIconTitle() {
		return true;
	}

	protected boolean showTitle() {
		return true;
	}

	protected boolean showBackButtonTitle() {
		return true;
	}

	protected void showSimpleMesage(String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message)
		       .setCancelable(false)
		       .setPositiveButton("Ok", null);
		AlertDialog alert = builder.create();
		alert.show();
	}
		
	protected abstract class ProgressThread extends Thread {
		protected ProgressHandler mHandler;
		       
        public ProgressThread(ProgressHandler h) {
            mHandler = h;
        }
                       
        public void setMessage(String msg) {
        	mHandler.setMessage(msg);
        }
        
        public void setMessage(Message msg) {
        	mHandler.sendMessage(msg);
        }
	}


}
