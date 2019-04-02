package pe.worktime;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.util.AbstractActivity;

public class SalidaAutomaticoActivity extends AbstractActivity {

	public static int viewLoad = R.layout.salida_automatica;
	protected EditText txtdni = null;
	protected TextView lblAgregado = null;
	//protected Button btnAgregar = null;
	protected Button btn_limpia_dni = null;
	protected Button btnQR = null;

	int horas_descanso  = 0;

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

	protected boolean validate() {
		txtdni = (EditText) findViewById(R.id.txtDniAutomatico);
		if (txtdni.getText().length() != 8) {
			Mensaje("Ingresar DNI", "DNI es requerido");
			return false;
		}
		return true;
	}

	public void addRegister(String dni) {

		try{

		if (ContextTest.idxDirecORIn == 0) {
			String CodTurno = Application.context.getFundoController().getTurnoSelected().getCod_turno().toString();
			Application.context.getHorasConsumidorController().addDetalleDirecto(CodTurno, dni );
		}

		if (ContextTest.idxDirecORIn == 1) {
			Log.e("IDX IND EN AUTOMATICA",
					String.valueOf(ContextTest.idxDirecORIn));
			String codConsumidor = Application.context.getConsumidorIndirectoController().getSelected().getCodConsumidor().toString();
			Application.context.getHorasConsumidorController().addDetalleIndirecto(codConsumidor, dni );
		}
		
		} catch (Exception e) {
			showSimpleMesage(e.getMessage());
		}

	}

	@Override
	protected void postLoadView() {


		lblAgregado = (TextView) findViewById(R.id.txtMsjAgregadoAutomatico);
		lblAgregado.setText("");
		txtdni = (EditText) findViewById(R.id.txtDniAutomatico);
		txtdni.setEnabled(false);


//		NumberPicker np = (NumberPicker) findViewById(R.id.np);
//
//		np.setMinValue(0);
//		np.setMaxValue(4);
//		np.setWrapSelectorWheel(true);
//
//		np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
//			@Override
//			public void onValueChange(NumberPicker picker, int oldVal, int newVal){
//				horas_descanso = newVal;
//				Log.e("ITEM SELECCIONADO", "Selected Number : " + newVal);
//			}
//		});


		//btnAgregar = (Button) findViewById(R.id.btn_agregar_automatico);
		//btnAgregar.setOnClickListener(new View.OnClickListener() {
//
		//	@Override
		//	public void onClick(View view) {
		//		try {
		//			if (validate() == true) {
		//				if (Application.context.getHorasConsumidorController()
		//						.salidaOK(txtdni.getText().toString()) == true) {
		//					lblAgregado.setText("Salida Exitosa: "
		//							+ txtdni.getText().toString());
		//					lblAgregado.setTextColor(Color.RED);
		//					txtdni.setText("");
		//					SalidaAsistenciaActivity.reloadProducts();
		//					ResumenSalidaActivity.reloadProducts();
		//				} else {
		//					lblAgregado.setText("DNI Incorrecto: "
		//							+ txtdni.getText().toString());
		//					lblAgregado.setTextColor(Color.RED);
		//				}
		//			}
		//			txtdni.requestFocus();
		//		} catch (Exception e) {
		//			showSimpleMesage(e.getMessage());
		//		}
		//
		//	}
		//});
		
		txtdni.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				int idx2 = txtdni.getText().length();
				if(idx != idx2){					
					idx = idx2;	
					if(idx==8){
						if (validate() == true) {



							try {
								if (Application.context.getHorasConsumidorController().salidaOK(txtdni.getText().toString(),0) == true ) {
									lblAgregado.setText("Salida Exitosa: "+ txtdni.getText().toString());
									lblAgregado.setTextColor(Color.RED);
									SalidaAsistenciaActivity.reloadProducts();
									ResumenSalidaActivity.reloadProducts();
									txtdni.setText("");
								} else {
									lblAgregado.setText("Verifique DNI: "+ txtdni.getText().toString());
									lblAgregado.setTextColor(Color.RED);
								}
							} catch (Exception e) {
								showSimpleMesage(e.getMessage());
							}

						}
						//txtdni.requestFocus();
					}
				}
			}
			
			private int idx = -1;
			
		});
		
		txtdni.setOnKeyListener(new OnKeyListener() {
			private int idx = -1;
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				int idx2 = txtdni.getText().length();
				if(idx != idx2){					
					idx = idx2;	
					if(idx==8){
						if (validate() == true) {



								try {
									if (Application.context.getHorasConsumidorController().salidaOK(txtdni.getText().toString(),0) == true) {
										lblAgregado.setText("Salida Exitosa: "+ txtdni.getText().toString());
										lblAgregado.setTextColor(Color.RED);
										SalidaAsistenciaActivity.reloadProducts();
										ResumenSalidaActivity.reloadProducts();
										txtdni.setText("");
									} else {
										lblAgregado.setText("Verifique DNI: " + txtdni.getText().toString());
										lblAgregado.setTextColor(Color.RED);
										txtdni.setText("");
									}
								} catch (Exception e) {
									showSimpleMesage(e.getMessage());
								}

						}
					//	txtdni.requestFocus();
					}
				}
				return false  ;
			}
		});



		btnQR = (Button)findViewById(R.id.btn_lecturar_manual);
		btnQR.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				txtdni.setText("");
				lblAgregado.setText("");
				Intent intQR = new Intent(SalidaAutomaticoActivity.this, EscanearQR.class);
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
						txtdni.setText("");
						lblAgregado.setText("");
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
		return "Salida Automatico";
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(EscanearQR.dato == "") {
			lblAgregado.setText(EscanearQR.mensaje);
			lblAgregado.setTextColor(Color.RED);
			EscanearQR.mensaje = "";
		} else {
			txtdni.setText(EscanearQR.dato);
			EscanearQR.dato = "";
		}
	}
}
