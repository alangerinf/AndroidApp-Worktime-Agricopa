package pe.worktime;

import java.util.List;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
//import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.ProgressBar;
import android.widget.Spinner;
//import android.widget.TextView;

//import MenuPrincipalActivity.Sincronizar;
import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.model.entity.Empresa;
import pe.worktime.util.AbstractActivity;
import pe.worktime.view.ProgressMsg;

public class MainActivity extends AbstractActivity {

	protected boolean block = false;
	protected EditText user = null;
	protected EditText pass = null;
	public static int indiceLoginSupervisor = 0;
	//protected TextView lblError = null;
	//private ProgressBar progress;
	//private TextView txtMsg;
	private Button btn_continue;
	private Spinner cbxEmpresas;

	private ProgressDialog dialog;

	private MainActivity that;

	@Override
	protected void postLoadView() {
		if (Application.context.context == null) {
			Application.context.context = getApplicationContext();
		}

		int PERMISSION_ALL = 1;
		String[] PERMISSIONS = {Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
		if (!hasPermissions(this, PERMISSIONS)) {
			ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
		}


		try {
			TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				return;
			}
			Application.context.IMEI = telephonyManager.getDeviceId();

			//Application.context.IMEI = "0000000000";

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//progress = (ProgressBar) findViewById(R.id.progressBar);
		user = (EditText) findViewById(R.id.inp_user);
		pass = (EditText) findViewById(R.id.inp_pass);
		//lblError = (TextView) findViewById(R.id.lblErrorMsjIncorrect);
		//lblError.setText("");
		//txtMsg = lblError;
		//progress.setVisibility(View.INVISIBLE);
		btn_continue = (Button) findViewById(R.id.btn_Login);
		
		cbxEmpresas = (Spinner) findViewById(R.id.cboEmpressa);
		
		that = this;
		
		dialog = new ProgressDialog(that);
		dialog.setMessage("Cargando Data Empresas...");
		dialog.setTitle("Progreso");
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		dialog.setCancelable(false);
		
		new LoadEmpresa().execute(true);
		
		btn_continue.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(final View v) {
				
				int index = cbxEmpresas.getSelectedItemPosition();
				Application.context.getEmpresaController().seleccionar(index);
				
				dialog = new ProgressDialog(that);
				dialog.setMessage("Autenticando");
				dialog.setTitle("Progreso");
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setCancelable(false);
				
				//LoadMaestros sync = new LoadMaestros();
				new LoadMaestros().execute(true);
			}

		});
		
	}


	public static boolean hasPermissions(Context context, String... permissions)
	{
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null)
		{
			for (String permission : permissions)
			{
				if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
				{
					return false;
				}
			}
		}
		return true;
	}


	@Override
	protected int getViewId() {
		return R.layout.main;
	}

	@Override
	protected String getTextTitle() {
		return "Iniciar Sesión";
	}

	public class LoadMaestros extends AsyncTask<Boolean, ProgressMsg, Boolean> {
		
		Exception ex = null;
		
		protected void onPreExecute() {
			dialog.setProgress(0);
			dialog.setMax(100);
			dialog.show();
		}
		
		@Override
		protected Boolean doInBackground(Boolean... params) {
			boolean result = false;
			try {
				//publishProgress(new ProgressMsg(0, "Conectando"));
				traceMsg(0, "Conectando");
				Application.context.getManagerView().buff = this;
				Application.context.getUsuarioController().autenticar(user.getText().toString(), pass.getText().toString());
				//Application.context.getUsuarioController().autenticar("818129", "862780");

				result = true;
			} catch (Exception e) {
				e.printStackTrace();
				ex = e;
			}
			Application.context.getManagerView().buff = null;
			return result;
		}

		public void traceMsg(int porc, String msg) {
			publishProgress(new ProgressMsg(porc, msg));
			//onProgressUpdate(new ProgressMsg(porc, msg));
		}

		protected void onProgressUpdate(ProgressMsg... valores) {
			ProgressMsg p = null;
			try {
				p = valores[0];
			} catch (Exception e) {
			}
			if (p != null) {
				if (p.getProgress() == 0) {
					user.setEnabled(false);
					pass.setEnabled(false);
					btn_continue.setEnabled(false);
					//progress.setVisibility(View.VISIBLE);
				}
				//progress.setProgress(p.getProgress());
				//txtMsg.setText(p.getMessage());
				dialog.setProgress(p.getProgress());
				dialog.setMessage(p.getMessage());
			}
		}

		protected void onPostExecute(Boolean result) {
			dialog.dismiss();			
			if (result) {
				
				Intent i = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
				startActivity(i);
				
				//showSimpleMesage("All Ok");
			} else {
				if(ex == null){
					showSimpleMesage("Autenticación Errónea");
				}else{
					showSimpleMesage(ex.getMessage());
				}
				user.setEnabled(true);
				pass.setEnabled(true);
				btn_continue.setEnabled(true);
				//progress.setVisibility(View.INVISIBLE);
				user.setText("");
				pass.setText("");
				//txtMsg.setText("Credenciales Incorrectas");
			}
		}
	}

	/* Progress Message */
	/*
	public class ProgressMsg {

		private int progress;
		private String message;

		@SuppressWarnings("unused")
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

		@SuppressWarnings("unused")
		public void setProgress(int progress) {
			this.progress = progress;
		}

		public String getMessage() {
			return message;
		}

		@SuppressWarnings("unused")
		public void setMessage(String message) {
			this.message = message;
		}

	}
	*/




	public class LoadEmpresa extends AsyncTask<Boolean, ProgressMsg, Boolean> {

		protected void onPreExecute() {
			dialog.setProgress(0);
			dialog.setMax(100);
			dialog.show();
		}

		@Override
		protected Boolean doInBackground(Boolean... params) {
			boolean result = false;
			try {
				publishProgress(new ProgressMsg(50,""));
				Application.context.getEmpresaController().loadMaster();
				publishProgress(new ProgressMsg(100,""));
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}

		public void traceMsg(int porc, String msg) {
			publishProgress(new ProgressMsg(porc, msg));
		}

		protected void onProgressUpdate(ProgressMsg... valores) {
			ProgressMsg p = null;
			try {
				p = valores[0];
			} catch (Exception e) {
			}
			if (p != null) {
				dialog.setProgress(p.getProgress());
			}
		}

		protected void onPostExecute(Boolean result) {
			dialog.dismiss();
			if(result == false){
				onBackPressed();
			}else{
				List<Empresa> lst = Application.context.getEmpresaController().listar();
				String [] datos = new String[lst.size()];
				for (int i = 0; i < lst.size(); i++) {
					datos[i] = lst.get(i).getEmpresa();
				}
				ArrayAdapter<String> adaptador = new ArrayAdapter<String>(that, android.R.layout.simple_spinner_item, datos);
				adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				cbxEmpresas.setAdapter(adaptador);
//				if(lst.size() >2)
//				cbxEmpresas.setSelection(2);
			}
		}
	}
}