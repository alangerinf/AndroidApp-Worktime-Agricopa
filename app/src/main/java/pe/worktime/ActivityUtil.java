package pe.worktime;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class ActivityUtil extends Activity {
	/**
	 * @see Activity#onCreate(Bundle)
	 */

	public void Mensaje(String title, String msj) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder.setMessage(msj);
		alertDialogBuilder.setNeutralButton("Ok",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Log.d("AlertDialog", "OK");
						return;
					}
				}).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Mensaje("", "");
	}
}
