package pe.worktime;

import pe.worktime.app.Application;
import pe.worktime.util.AbstractActivity;

//import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
//import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.worktime.R;

public class PlanillaEntradaAutomaticaActivity extends AbstractActivity {

	public static int viewLoad = R.layout.planilla_entrada_automatica;
	protected EditText txtdni = null;
	protected TextView lblAgregado = null;
	//protected Button btnAgregar = null;
	protected Button btn_limpia_dni = null;
	protected Button btnQR = null;

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
		try {

			if (ContextTest.idxDirecORIn == 0) {
				//String CodTurno = Application.context.getFundoController().getTurnoSelected().getCod_turno().toString();
				String CodTurno = Application.context.getHorasConsumidorController().getSelected().getDetalle().get(0).getCodTurno();
				Application.context.getHorasConsumidorController().addDetalleDirecto(CodTurno, dni );
			}

			if (ContextTest.idxDirecORIn == 1) {
				//String codConsumidor = Application.context.getConsumidorIndirectoController().getSelected().getCodConsumidor().toString();
				String codConsumidor = Application.context.getHorasConsumidorController().getSelected().getDetalle().get(0).getCodConsumidor();
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
		//btnAgregar = (Button) findViewById(R.id.btn_agregar_automatico);
		//btnAgregar.setOnClickListener(new View.OnClickListener() {
//
		//	@Override
		//	public void onClick(View view) {
		//		if (validate() == true) {
//
		//			if (Application.context.getHorasConsumidorController()
		//					.verificarDNI(txtdni.getText().toString()) == true) {
		//				lblAgregado.setText("Dni ya existe : "
		//						+ txtdni.getText().toString());
		//				lblAgregado.setTextColor(Color.RED);
		//				IngresoAsistenciaActivity.reloadProducts();
		//				ResumenIngresoActivity.reloadProducts();
//
		//			} else {
		//				lblAgregado.setText("Dni Agregado: "
		//						+ txtdni.getText().toString());
		//				lblAgregado.setTextColor(Color.RED);
		//				addRegister(txtdni.getText().toString());
		//				txtdni.setText("");
		//				IngresoAsistenciaActivity.reloadProducts();
		//				ResumenAsistenciaActivity.reloadProducts();
		//			}
		//			// ResumenAsistenciaActivity.reloadProducts();
		//		}
		//		txtdni.requestFocus();
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
				if (idx != idx2) {
					idx = idx2;
					if (idx == 8) {
						if (validate() == true) {

							if (Application.context
									.getHorasConsumidorController()
									.verificarDNI(txtdni.getText().toString()) == true) {
								lblAgregado.setText("DNI ya existe : "
										+ txtdni.getText().toString());
								lblAgregado.setTextColor(Color.RED);
								IngresoAsistenciaActivity.reloadProducts();
								ResumenIngresoActivity.reloadProducts();
							} else {
								lblAgregado.setText("DNI Agregado: "
										+ txtdni.getText().toString());
								lblAgregado.setTextColor(Color.RED);
								addRegister(txtdni.getText().toString());
								txtdni.setText("");
								IngresoAsistenciaActivity.reloadProducts();
								ResumenIngresoActivity.reloadProducts();
							}

						}
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
				if (idx != idx2) {
					idx = idx2;
					if (idx == 8) {
						if (validate() == true) {
							if (Application.context
									.getHorasConsumidorController()
									.verificarDNI(txtdni.getText().toString()) == true) {
								lblAgregado.setText("DNI ya existe : "
										+ txtdni.getText().toString());
								lblAgregado.setTextColor(Color.RED);
								IngresoAsistenciaActivity.reloadProducts();
								ResumenIngresoActivity.reloadProducts();
							} else {
								lblAgregado.setText("DNI Agregado: "
										+ txtdni.getText().toString());
								lblAgregado.setTextColor(Color.RED);
								addRegister(txtdni.getText().toString());
								txtdni.setText("");
								IngresoAsistenciaActivity.reloadProducts();
								ResumenIngresoActivity.reloadProducts();
							}
						}
					}
				}
				return false;
			}
		});

		btnQR = (Button)findViewById(R.id.btn_lecturar_manual);
		btnQR.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				txtdni.setText("");
				lblAgregado.setText("");
				Intent intQR = new Intent(PlanillaEntradaAutomaticaActivity.this, EscanearQR.class);
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
						Log.e("Limpiar DNI", "Planilla Entrada Manual");
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
		return "Agregar Trabajador";
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
