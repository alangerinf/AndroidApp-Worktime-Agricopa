package pe.worktime;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.worktime.R;

import pe.worktime.app.Application;

public class ResumenAsistenciaActivity extends Activity {
	/**
	 * @see Activity#onCreate(Bundle)
	 */

	private TextView txtPresentes;
	private TextView txtAusentes;
	private TextView txtGrupo;
	private TextView txtActividad;
	private TextView txtInicioGeneral;
	private TextView textTitleF;
	private String p = null;
	private String au = null;
	private String p1 = null;
	private String au1 = null;
	private String h = null;
	private String h1 = null;
	private int horas;
	private int minutos;
	static final int TIME_DIALOG_ID = 0;

	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			horas = hourOfDay;
			minutos = minute;
			updateDisplay();
			// displayToast();
		}
	};

	private void updateDisplay() {
		txtInicioGeneral.setText(new StringBuilder().append(pad(horas)).append(":").append(pad(minutos)));
	}

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			return new TimePickerDialog(this, mTimeSetListener, horas, minutos,
					false);
		}
		return null;
	}
	
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

	private static ResumenAsistenciaActivity actual;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resumen_asistencia);
		actual = this;
		txtPresentes = (TextView) findViewById(R.id.txt_nro_Asistentes);
		txtAusentes = (TextView) findViewById(R.id.txt_faltas);
		txtGrupo = (TextView) findViewById(R.id.txt_grupo);
		txtActividad = (TextView) findViewById(R.id.txt_actividad);
		txtInicioGeneral = (TextView) findViewById(R.id.txtInicioGeneralResumen);
		textTitleF = (TextView)findViewById(R.id.textTitleF);
		textTitleF.setText("Resumen Entrada");

		if (txtPresentes != null) {
			if (Application.context.getHorasConsumidorController().getSelected().nroPresentes() == 0) {
				txtPresentes.setText("0");
			} else {
				p = String.valueOf(Application.context.getHorasConsumidorController().getSelected().nroPresentes());
				Log.e("presentes", p);
				txtPresentes.setText(p);
			}
		}

		if (txtAusentes != null) {
			au = String.valueOf(Application.context.getHorasConsumidorController().getSelected().nroAusentes());
			txtAusentes.setText(au);
			Log.e("ausentes", au);
		}
		if (txtGrupo != null) {
			//txtGrupo.setText(Application.context.getGrupoController().getSelected().getGrupo());
			txtGrupo.setText(Application.context.getHorasConsumidorController().getSelected().getFecha());
		}

		if (txtActividad != null) {
			txtActividad.setText(Application.context.getActividadController().getSelected().getActividad());
		}

		if (ContextTest.idxTipoEntrada == 0) {

			if (txtInicioGeneral != null) {
				txtInicioGeneral.setEnabled(true);
				h = Application.context.getHorasConsumidorController()
						.getInicioGeneralAutomatico();
				txtInicioGeneral.setText(h);
			}
		}

		if (ContextTest.idxTipoEntrada == 1) {
			if (txtInicioGeneral != null) {
				txtInicioGeneral.setEnabled(false);
				h = Application.context.getHorasConsumidorController()
						.getInicioGeneralAutomatico();
				txtInicioGeneral.setText(h);
			}
		}

		txtInicioGeneral.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});

		final Calendar cal = Calendar.getInstance();
		horas = cal.get(Calendar.HOUR_OF_DAY);
		minutos = cal.get(Calendar.MINUTE);
		updateDisplay();
	
		Button btn_continue = (Button) findViewById(R.id.btn_continue);
		btn_continue.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				try {
					if(Application.context.getHorasConsumidorController().getSelected().getDetalle().size()==0){
						Mensaje("Lista Vacia","Lista de Entrada Vacia");
					}else{
						Application.context.getHorasConsumidorController().saveHorasConsumidor();
						Intent i = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
						startActivity(i);
						// onBackPressed();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressLint("HandlerLeak") 
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// reloadProducts();
			if (txtPresentes != null) {
				Log.e("presentes xd",String.valueOf(Application.context.getHorasConsumidorController().getSelected().nroPresentes()));
				p1 = String.valueOf(Application.context.getHorasConsumidorController().getSelected().nroPresentes());
				txtPresentes.setText(p1);
			}
			
			if (txtAusentes != null) {
				au1 =String.valueOf(Application.context.getHorasConsumidorController().getSelected().nroAusentes());
				txtAusentes.setText(au1);		
			}

			if (ContextTest.idxTipoEntrada == 0) {

				if (txtInicioGeneral != null) {
					txtInicioGeneral.setEnabled(true);
					h1 = Application.context.getHorasConsumidorController()
							.getInicioGeneralAutomatico();
					txtInicioGeneral.setText(h1);
					txtInicioGeneral.setText("c");
				}
			}

			if (ContextTest.idxTipoEntrada == 1) {
				if (txtInicioGeneral != null) {
					txtInicioGeneral.setEnabled(false);
					h1 = Application.context.getHorasConsumidorController()
							.getInicioGeneralAutomatico();
					txtInicioGeneral.setText(h1);
				}
			}
		}
	};

	public static void reloadProducts() {
		if (actual != null) {
			actual.handler.sendEmptyMessage(0);
		}
	}
}
