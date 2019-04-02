package pe.worktime;

import java.lang.reflect.Method;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.util.AbstractActivity;

public class EntradaAutomaticaActivity extends AbstractActivity {

	public static int viewLoad = R.layout.entrada_automatica;
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

	public void addRegister(String dni) throws Exception {
		if (ContextTest.idxDirecORIn == 0) {
			String CodModulo = Application.context.getHorasConsumidorController().getSelected().getCodigoCecoOModulo().toString();
			Application.context.getHorasConsumidorController().addDetalleDirecto(CodModulo, dni );
		}
		if (ContextTest.idxDirecORIn == 1) {
			String codConsumidor =  Application.context.getHorasConsumidorController().getSelected().getCodigoCecoOModulo().toString();
			Application.context.getHorasConsumidorController().addDetalleIndirecto(codConsumidor, dni );
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
		//			if (Application.context.getHorasConsumidorController()
		//					.verificarDNI(txtdni.getText().toString()) == true) {
		//				lblAgregado.setText("Dni ya existe : "
		//						+ txtdni.getText().toString());
		//				lblAgregado.setTextColor(Color.RED);
		//				ManualAsistenciaActivity.reloadProducts();
		//				ResumenAsistenciaActivity.reloadProducts();
		//			} else {
		//				try{
		//					addRegister(txtdni.getText().toString());
		//					lblAgregado.setText("Dni Agregado: "
		//							+ txtdni.getText().toString());
		//					lblAgregado.setTextColor(Color.RED);
		//					txtdni.setText("");
		//					ResumenAsistenciaActivity.reloadProducts();
		//				} catch (Exception e) {
		//					showSimpleMesage(e.getMessage());
		//				}
		//			}
		//		}
		//		txtdni.requestFocus();
		//	}
		//});

		Log.d("INF", "Request Focus");
		txtdni.setText("");

		//txtdni.requestFocus();

		Method method = null;
		try {
			method = txtdni.getClass().getMethod("setOnTouchListener",
					OnTouchListener.class);
			method.setAccessible(true);
			method.invoke(txtdni, new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					v.requestFocusFromTouch();
					return false;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

		txtdni.addTextChangedListener(new TextWatcher() {
			private int idx = -1;
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				int idx2 = txtdni.getText().length();
				if (idx != idx2) {
					idx = idx2;
					if (idx == 8) {


						if (validate() == true) {
							if (Application.context.getHorasConsumidorController().verificarDNI(txtdni.getText().toString()) == true) {
								lblAgregado.setText("DNI ya existe : " + txtdni.getText().toString());
								lblAgregado.setTextColor(Color.RED);
								ManualAsistenciaActivity.reloadProducts();
								ResumenAsistenciaActivity.reloadProducts();
							} else {
								try {
									addRegister(txtdni.getText().toString());
									lblAgregado.setText("DNI Agregado: " + txtdni.getText().toString());
									lblAgregado.setTextColor(Color.RED);
									txtdni.setText("");
									ResumenAsistenciaActivity.reloadProducts();
								} catch (Exception e) {
									showSimpleMesage(e.getMessage());
								}
							}
						}

					}
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
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
							if (Application.context.getHorasConsumidorController().verificarDNI(txtdni.getText().toString()) == true) {
								lblAgregado.setText("DNI ya existe : "+ txtdni.getText().toString());
								lblAgregado.setTextColor(Color.RED);
								ManualAsistenciaActivity.reloadProducts();
								ResumenAsistenciaActivity.reloadProducts();
							} else {								
								try{
									addRegister(txtdni.getText().toString());
									lblAgregado.setText("DNI Agregado: "+ txtdni.getText().toString());
									lblAgregado.setTextColor(Color.RED);
									txtdni.setText("");
									ResumenAsistenciaActivity.reloadProducts();
								} catch (Exception e) {
									showSimpleMesage(e.getMessage());
								}
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
				Intent intQR = new Intent(EntradaAutomaticaActivity.this, EscanearQR.class);
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
