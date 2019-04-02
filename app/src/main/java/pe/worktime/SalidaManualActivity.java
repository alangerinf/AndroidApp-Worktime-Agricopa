package pe.worktime;

import java.util.Calendar;

import pe.worktime.app.Application;
import pe.worktime.util.AbstractActivity;
import com.travijuu.numberpicker.library.NumberPicker;

//import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
//import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
//import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.worktime.R;

public class SalidaManualActivity extends AbstractActivity {

	public static int viewLoad = R.layout.salida_manual;
	protected EditText txtdni = null;
	protected Button btnAgregar = null;
	protected Button btnhora = null;
	protected TextView lblmensaje = null;
	protected EditText txtHoraInicio = null;

	private int horas;
	private int minutos;
	@SuppressWarnings("unused")
	private int segundos;
	static final int TIME_DIALOG_ID = 0;
	
	protected CheckBox chkSalida = null;
	protected Button btn_limpia_dni = null;
	protected Button btnQR = null;
	int horas_descanso  = 0;
	

	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			horas = hourOfDay;
			minutos = minute;
			updateDisplay();
			// displayToast();
		}
	};

	private void updateDisplay() {
		txtHoraInicio.setText(new StringBuilder().append(pad(horas))
				.append(":").append(pad(minutos)));
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
					true);
		}
		return null;
	}

	protected int isText(String texto) {

		String numeros = "0123456789";
		for (int i = 0; i < texto.length(); i++) {
			if (numeros.indexOf(texto.charAt(i), 0) != -1) {
				return 1;
			}
		}
		return 0;
	}

	protected boolean validate() {
		txtdni = (EditText) findViewById(R.id.txtDniManual);
		// txtHoraInicio = (EditText) findViewById(R.id.txtHoraInicioManual);
		if (txtdni.getText().length() != 8) {
			Mensaje("Ingresar DNI", "DNI es requerido");
			return false;
		} else if (isText(txtdni.getText().toString()) == 0) {
			Mensaje("Formato Incorrecto", "DNI Incorrecto");
			return false;
		}

		return true;
	}

	public void addRegister(String dni) throws Exception {
		txtHoraInicio = (EditText) findViewById(R.id.txtHoraInicio);
		// Entrada Manual = 0

		Log.e("IDX ENTRADA MANUAL", String.valueOf(ContextTest.idxTipoEntrada));
		// Directa Entrada Manual ==0
		if (ContextTest.idxDirecORIn == 0) {
			Log.e("IDX EM - DIRECTA",
					String.valueOf(ContextTest.idxDirecORIn));
			String CodTurno =  Application.context.getFundoController().getTurnoSelected().getCod_turno().toString();
			//Application.context.getHorasConsumidorController().addDetalleDirecto(CodTurno, dni,txtHoraInicio.getText().toString() );
			Application.context.getHorasConsumidorController().addDetalleDirecto(CodTurno, dni);
		}
		// Indirecta Entrada Manual == 1
		if (ContextTest.idxDirecORIn == 1) {
			Log.e("IDX EM - INDIRECTA",
					String.valueOf(ContextTest.idxDirecORIn));
			String codConsumidor =  Application.context.getConsumidorIndirectoController().getSelected().getCodConsumidor().toString();
			//Application.context.getHorasConsumidorController().addDetalleIndirecto(codConsumidor, dni,txtHoraInicio.getText().toString() );
			Application.context.getHorasConsumidorController().addDetalleIndirecto(codConsumidor, dni );
		}

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

	@Override
	protected void postLoadView() {

		lblmensaje = (TextView) findViewById(R.id.txtMsjAgregado);
		lblmensaje.setText("");
		txtdni = (EditText) findViewById(R.id.txtDniManual);
		txtHoraInicio = (EditText) findViewById(R.id.txtHoraInicio);
		txtHoraInicio.setEnabled(false);

		btnhora = (Button) findViewById(R.id.btnhora);
		btnhora.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG_ID);
			}
		});

		final Calendar cal = Calendar.getInstance();
		horas = cal.get(Calendar.HOUR_OF_DAY);
		minutos = cal.get(Calendar.MINUTE);
		//segundos = cal.get(Calendar.SECOND);
		updateDisplay();
		
		chkSalida = (CheckBox) findViewById(R.id.chkSalida);


//		NumberPicker np = (NumberPicker) findViewById(R.id.np);
//		np.setMinValue(0);
//		np.setMaxValue(4);
//		np.setWrapSelectorWheel(true);


//		np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//			@Override
//			public void onValueChange(NumberPicker picker, int oldVal, int newVal){
//				Log.e("ITEM SELECCIONADO", "Selected Number : " + newVal);
//				horas_descanso = newVal;
//			}
//		});



		btnAgregar = (Button) findViewById(R.id.btn_agregar_manual);
		btnAgregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try{
					if(chkSalida.isChecked() ){
						Application.context.getHorasConsumidorController().salidaAllOK(txtHoraInicio.getText().toString(),0);
						SalidaAsistenciaActivity.reloadProducts();
						ResumenSalidaActivity.reloadProducts();
						showSimpleMesage("Planilla Cerrada Exitosamente");
					}
					else if (validate() == true) {

						if (Application.context.getHorasConsumidorController().salidaOK(txtdni.getText().toString(), txtHoraInicio.getText().toString(), 0) == true  ) {
							lblmensaje.setText("Salida Exitosa: " + txtdni.getText().toString());
							lblmensaje.setTextColor(Color.RED);
							txtdni.setText("");
							SalidaAsistenciaActivity.reloadProducts();
							ResumenSalidaActivity.reloadProducts();
						} else {
							lblmensaje.setText("Verifique DNI: " + txtdni.getText().toString());
							lblmensaje.setTextColor(Color.RED);
							txtdni.setText("");
						}

					}
					txtdni.requestFocus();
				} catch (Exception e) {					
					showSimpleMesage(e.getMessage());
				}
			}
		});

		btnQR = (Button)findViewById(R.id.btn_lecturar_manual);
		btnQR.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				txtdni.setText("");
				lblmensaje.setText("");
				Intent intQR = new Intent(SalidaManualActivity.this, EscanearQR.class);
				startActivity(intQR);
			}
		});
		//btnQR.setVisibility(View.GONE);
		
		btn_limpia_dni = (Button) findViewById(R.id.btn_limpia_dni);
		if(btn_limpia_dni != null)
		{
			btn_limpia_dni.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						txtdni.setText("");
						lblmensaje.setText("");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		}
		
	}

	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Salida Manual";
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(EscanearQR.dato == "") {
			lblmensaje.setText(EscanearQR.mensaje);
			lblmensaje.setTextColor(Color.RED);
			EscanearQR.mensaje = "";
		} else {
			txtdni.setText(EscanearQR.dato);
			EscanearQR.dato = "";
		}
	}
}