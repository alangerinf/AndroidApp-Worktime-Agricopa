package pe.worktime;

//import java.util.Calendar;
import java.util.Date;

import pe.worktime.app.Application;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.util.ModelHelper;
import pe.worktime.util.AbstractActivity;

//import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
//import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.worktime.R;

public class SalidaManualTareaActivity extends AbstractActivity {

	public static int viewLoad = R.layout.salida_manual_tarea;
	protected EditText txtdni = null;
	protected Button btnAgregar = null;
	protected TextView lblmensaje = null;
	protected EditText txtHoraInicio = null;
	private int horas;
	private int minutos;
	@SuppressWarnings("unused")
	private int segundos;
	static final int TIME_DIALOG_ID = 0;
	
	protected CheckBox chkSalida = null;
	protected Button btn_limpia_dni = null;
	
    protected HorasConsumidorDetalle hcd = null;
    
	
	protected Date horainicio;

	protected Date horafin;
	protected 	String horafinal;

	protected int canthoras;
	

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
	
	protected int isDecimal(String texto) {

		String numeros = "0123456789.";
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
		if (txtdni.getText().length() != 9) {
			Mensaje("Ingresar DNI", "DNI es requerido");
			return false;
		} else if (isText(txtdni.getText().toString()) == 0) {
			Mensaje("Formato Incorrecto", "Dni Incorrecto");
			return false;
		}

		return true;
	}
	
	protected boolean validateHora() {
		txtHoraInicio = (EditText) findViewById(R.id.txtHoraInicio);
		// txtHoraInicio = (EditText) findViewById(R.id.txtHoraInicioManual);
		if (txtHoraInicio.getText().length() == 0 || txtHoraInicio.getText().toString().compareTo("")==0) {
			Mensaje("Ingresar Cantidad de Horas.", "Cantidad es requerido");
			txtHoraInicio.requestFocus();
			return false;
		} else if (isDecimal(txtHoraInicio.getText().toString()) == 0) {
			Mensaje("Formato Incorrecto", "Solo numeros");
			txtHoraInicio.requestFocus();
			return false;
		} else if (Integer.parseInt(txtHoraInicio.getText().toString()) <= 0 || Integer.parseInt(txtHoraInicio.getText().toString()) > 16){
			Mensaje("Formato Incorrecto", "Cantidad debe ser mayor a cero y menor a 16.");
			txtHoraInicio.requestFocus();
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
			Log.e("IDX DIRECTA ENT. MANUAL",
					String.valueOf(ContextTest.idxDirecORIn));
			String CodTurno = ""; //Application.context.getFundoController().getTurnoSelected().getCod_turno().toString();
			Application.context.getHorasConsumidorController()
					.addDetalleDirecto(CodTurno, dni,
							txtHoraInicio.getText().toString());
		}
		// Indirecta Entrada Manual == 1
		if (ContextTest.idxDirecORIn == 1) {
			Log.e("IDX IND. ENT. MANUAL",
					String.valueOf(ContextTest.idxDirecORIn));
			String codConsumidor = ""; //Application.context.getConsumidorIndirectoController().getSelected().getCodConsumidor().toString();
			Application.context.getHorasConsumidorController()
					.addDetalleIndirecto(codConsumidor, dni,
							txtHoraInicio.getText().toString());
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
		/*
		txtHoraInicio.setOnClickListener(new View.OnClickListener() {
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
		 */
		txtHoraInicio.setText("");
		chkSalida = (CheckBox) findViewById(R.id.chkSalida);
		
	
	   
		
		btnAgregar = (Button) findViewById(R.id.btn_agregar_manual);
		btnAgregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try{
				
					
			

					 
					if(chkSalida.isChecked()  && validateHora() == true ){
						
						hcd = Application.context.getHorasConsumidorController().getSelected().buscarPrimerDetalle();
						ModelHelper helper = new ModelHelper();
						horainicio = helper.convertStringToDate(hcd.getHoraInicio());
					   	canthoras = Integer.parseInt(txtHoraInicio.getText().toString());
						horafin = Application.context.getHorasConsumidorController().sumarRestarHorasFecha(horainicio, canthoras);
						horafinal = helper.cambiarFormatoFecha(horafin);
										
						Application.context.getHorasConsumidorController().salidaAllOKTarea(horafinal);
						SalidaAsistenciaActivity.reloadProducts();
						ResumenSalidaActivity.reloadProducts();
						showSimpleMesage("Planilla Cerrada Exitosamente");
					}
					else if (validate() == true && validateHora() == true) {
						
						hcd = Application.context.getHorasConsumidorController().getSelected().buscarDetalle(txtdni.getText().toString());
						
						if (hcd.getCodTrabajador().compareTo("")==0){
							lblmensaje.setText("DNI No Encontrado: " + txtdni.getText().toString());
							lblmensaje.setTextColor(Color.RED);
							
						}else {
								ModelHelper helper = new ModelHelper(); 
								horainicio = helper.convertStringToDate(hcd.getHoraInicio());
							   	canthoras = Integer.parseInt(txtHoraInicio.getText().toString());
								horafin = Application.context.getHorasConsumidorController().sumarRestarHorasFecha(horainicio, canthoras);
								horafinal = helper.cambiarFormatoFecha(horafin);
	
						
								if (Application.context.getHorasConsumidorController().salidaOKTarea(txtdni.getText().toString(),horafinal ) == true) {		
									lblmensaje.setText("Salida Exitosa: " + txtdni.getText().toString());
									lblmensaje.setTextColor(Color.RED);
									txtdni.setText("");
									SalidaAsistenciaActivity.reloadProducts();
									ResumenSalidaActivity.reloadProducts();
								} else {
									lblmensaje.setText("DNI Incorrecto: " + txtdni.getText().toString());
									lblmensaje.setTextColor(Color.RED);
								}
						}
					}
					
					txtdni.requestFocus();
					
				
					
				} catch (Exception e) {					
					showSimpleMesage(e.getMessage());
				}
			}
		});
		
		btn_limpia_dni = (Button) findViewById(R.id.btn_limpia_dni);
		if(btn_limpia_dni != null)
		{
			btn_limpia_dni.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						txtdni.setText("");
						
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
		return "Salida por Tarea";
	}
}