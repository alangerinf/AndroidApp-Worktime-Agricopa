package pe.worktime;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.util.AbstractActivity;

public class EntradaManualActivity extends AbstractActivity {

	public static int viewLoad = R.layout.entrada_manual;
	protected EditText txtdni = null;
	protected Button btnAgregar = null;
	protected Button btnhora = null;
	protected TextView lblmensaje = null;
	protected EditText txtHoraInicio = null;
	private int horas;
	private int minutos;
	static final int TIME_DIALOG_ID = 0;
	protected Button btn_limpia_dni = null;
	protected Button btnQR = null;

	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			horas = hourOfDay;
			minutos = minute;
			updateDisplay();
			//displayToast();
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
	            return new TimePickerDialog(this,
	                    mTimeSetListener, horas,minutos,false);
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
			// showSimpleMesage("DNI es requerido ");
			Mensaje("Ingresar DNI", "DNI es requerido");
			Log.e("DNI", "INGRESAR DNI");
			return false;
		} else if (isText(txtdni.getText().toString()) == 0) {
			Log.e("DNI", "ES INCORRECTO");
			Mensaje("Formato Incorrecto", "DNI Incorrecto");
			// showSimpleMesage("Dni incorrecto ");
			return false;
		}
		return true;
	}

	public void addRegister(String dni) throws Exception {

		txtHoraInicio = (EditText) findViewById(R.id.txtHoraInicio);
		if(ContextTest.idxDirecORIn ==0){
			String CodModulo = Application.context.getHorasConsumidorController().getSelected().getCodigoCecoOModulo().toString();
			Application.context.getHorasConsumidorController().addDetalleDirecto(CodModulo, dni , txtHoraInicio.getText().toString());

		}
		if(ContextTest.idxDirecORIn ==1){
			String codConsumidor = Application.context.getHorasConsumidorController().getSelected().getCodigoCecoOModulo().toString();
			Application.context.getHorasConsumidorController().addDetalleIndirecto(codConsumidor, dni , txtHoraInicio.getText().toString());

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
	        updateDisplay();

		btnAgregar = (Button) findViewById(R.id.btn_agregar_manual);
		btnAgregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (validate() == true) {




						if (Application.context.getHorasConsumidorController().verificarDNI(txtdni.getText().toString()) == true) {
							lblmensaje.setText("DNI ya existe : " + txtdni.getText().toString());
							lblmensaje.setTextColor(Color.RED);
							ManualAsistenciaActivity.reloadProducts();
							ResumenAsistenciaActivity.reloadProducts();
						} else {
							try {
								addRegister(txtdni.getText().toString());
								lblmensaje.setText("DNI Agregado: " + txtdni.getText().toString());
								lblmensaje.setTextColor(Color.RED);
								txtdni.setText("");
								ResumenAsistenciaActivity.reloadProducts();
							} catch (Exception e) {
								showSimpleMesage(e.getMessage());
							}
						}
				}
			}
		});

		btnQR = (Button)findViewById(R.id.btn_lecturar_manual);
		btnQR.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				txtdni.setText("");
				lblmensaje.setText("");
				Intent intQR = new Intent(EntradaManualActivity.this, EscanearQR.class);
				startActivity(intQR);
			}
		});

		
		btn_limpia_dni = (Button) findViewById(R.id.btn_limpia_dni);
		if(btn_limpia_dni != null)
		{
			btn_limpia_dni.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						Log.e("Limpiar DNI", "Entrada Manual");
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
		return "Agregar Trabajador";
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