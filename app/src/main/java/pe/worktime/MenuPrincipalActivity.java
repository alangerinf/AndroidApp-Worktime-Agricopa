package pe.worktime;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.util.AbstractActivity;
import pe.worktime.view.ProgressMsg;

import static android.Manifest.permission.CAMERA;

public class MenuPrincipalActivity extends AbstractActivity {

	public static int viewLoad = R.layout.menu_principal;
	protected Button btnNuevaPlanilla = null;
	protected Button btnIngresoPlanilla = null;
	protected Button btnEliminarPlanilla = null;
	protected Button btnSalidaPlanilla = null;
	protected Button btnTransferencias = null;
	protected Button btnProductividad = null;
	protected Button btnSincronizar = null;
	protected Button btnSincronizarAsistencia = null;
	protected Button btn_Historial = null;

	private ProgressDialog dialog;
	
	@Override
	protected void postLoadView() {

		validarPermisos();

		btnNuevaPlanilla = (Button) findViewById(R.id.btn_new_planilla);
		btnNuevaPlanilla.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						TipoEntradaActivity.class);
				startActivity(i);
			}
		});

		btnIngresoPlanilla = (Button) findViewById(R.id.btn_ingreso_planilla);
		btnIngresoPlanilla.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						TipoEntradaIngresoActivity.class);
				startActivity(i);
			}
		});

		btnSalidaPlanilla = (Button) findViewById(R.id.btn_Salida_Planilla);
		btnSalidaPlanilla.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						TipoEntradaSalidaActivity.class);
				startActivity(i);
			}
		});

		btnEliminarPlanilla = (Button) findViewById(R.id.btn_Eliminar_Planilla);
		btnEliminarPlanilla.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						PlanillaEliminarActivity.class);
				startActivity(i);
			}
		});

		btnTransferencias = (Button) findViewById(R.id.btn_Transferencia);
		btnTransferencias.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						TipoEntradaTransferenciaActivity.class);
				startActivity(i);
			}
		});

		btnProductividad = (Button) findViewById(R.id.btn_Reg_Productividad);
		btnProductividad.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//Intent i = new Intent(getApplicationContext(),ProductividadManualActivity.class);startActivity(i);
				Intent i = new Intent(getApplicationContext(),TipoProductividadActivity.class);startActivity(i);
			}
		});
		//btnProductividad.setVisibility(View.GONE);

		final MenuPrincipalActivity that = this;
		
		btnSincronizar = (Button) findViewById(R.id.btn_Sincronizar);
		btnSincronizar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog = new ProgressDialog(that);
				dialog.setMessage("Sincronizando...");
				dialog.setTitle("Progreso");
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setCancelable(false);
				
				new Sincronizar().execute(true);
				
				/*
				try {
					Application.context.getHorasConsumidorController()
							.sincronizar();
				} catch (Exception e) {
					e.printStackTrace();
				}
				*/
			}
		});

		btnSincronizarAsistencia = (Button) findViewById(R.id.btn_Sincronizar_Asistencia);
		btnSincronizarAsistencia.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog = new ProgressDialog(that);
				dialog.setMessage("Enviando Asistencia...");
				dialog.setTitle("Progreso");
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setCancelable(false);

				new Sincronizar_Asistencia().execute(true);
			}
		});
		btnSincronizarAsistencia.setVisibility(View.GONE);


		btn_Historial = (Button) findViewById(R.id.btn_Historial);
		btn_Historial.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),OpcionesHistorialActivity.class);
				startActivity(i);
			}
		});
		
	}

	private boolean validarPermisos() {
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
			return true;

		if( checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED )
			return true;

		if(shouldShowRequestPermissionRationale(CAMERA))
			CargarDialogoRecomendacion();
		else
			requestPermissions(new String[]{CAMERA},100);

		return false;
	}

	private void CargarDialogoRecomendacion() {
		AlertDialog.Builder dialogo = new AlertDialog.Builder(MenuPrincipalActivity.this);
		dialogo.setTitle("Permisos Desactivados");
		dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

		dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			@RequiresApi(api = Build.VERSION_CODES.M)
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				requestPermissions(new String[]{CAMERA},100);
			}
		});
		dialogo.show();
	}

	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Menú Principal";
	}

	public void showDialog() {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Mensaje");
		alert.setMessage("¿Desea Salir de la Aplicación?");
		alert.setPositiveButton("SI", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.i("Dialogos", "Confirmación Aceptada.");
				Intent i = new Intent(getApplicationContext(),
						MainActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});

		alert.setNegativeButton("NO", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Log.i("Dialogos", "Confirmación Cancelada.");
				dialog.cancel();
			}
		});

		alert.show();
	}

	@Override
	public void onBackPressed() {
		showDialog();
	}

	//------------Sincronizar
	public class Sincronizar extends AsyncTask<Boolean, ProgressMsg, Boolean> {
		
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
				Application.context.getHorasConsumidorController().sincronizar();
				publishProgress(new ProgressMsg(75,""));
				Application.context.getProductividadController().sincronizar();				
				publishProgress(new ProgressMsg(100,""));
				
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
		}
	}


	//------------Sincronizar Asistencia
	public class Sincronizar_Asistencia extends AsyncTask<Boolean, ProgressMsg, Boolean> {

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
				Thread.sleep(1000);
				Application.context.getHorasConsumidorController().sincronizarAsistencia();
				publishProgress(new ProgressMsg(70,""));
				Thread.sleep(1000);
				publishProgress(new ProgressMsg(100,""));

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
		}
	}

}
