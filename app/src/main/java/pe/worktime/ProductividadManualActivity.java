package pe.worktime;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.controller.ProductividadController;
import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.entity.Cultivo;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Grupo;
import pe.worktime.model.entity.Modulo;
import pe.worktime.model.entity.Productividad;
import pe.worktime.model.entity.Turno;
import pe.worktime.model.entity.TurnoDia;
import pe.worktime.util.AbstractActivity;

public class ProductividadManualActivity extends AbstractActivity {

	public static int viewLoad = R.layout.productividad_manual;
	protected EditText txtdni = null;
	protected Button btnAgregar = null;
	protected TextView lblmensaje = null;
	protected EditText txtCantidad = null;
	protected Button btn_limpia_dni = null;
	protected Button btnQR = null;

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
			Mensaje("Formato Incorrecto", "Dni Incorrecto");
			// showSimpleMesage("Dni incorrecto ");
			return false;
		}
		try {
			double cnt = Double.parseDouble(txtCantidad.getText().toString());
			if(cnt < 0)
				throw new Exception("Numero Error");
		} catch (Exception e) {
			Mensaje("Formato Incorrecto", "Cantidad no valida");
			return false;
		}
		return true;
	}

	public void addRegister(String dni) throws Exception {
		txtCantidad = (EditText) findViewById(R.id.txtCantidad);
		double cnt = Double.parseDouble(txtCantidad.getText().toString());
		//Application.context.getProductividadController().addDetalleManual(txtdni.getText().toString(), cnt);
		Application.context.getProductividadController().modificarProductividad(txtdni.getText().toString(), txtCantidad.getText().toString());
		txtCantidad.setText("");
		txtdni.setText("");
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
		txtCantidad = (EditText) findViewById(R.id.txtCantidad);

		btnQR = (Button)findViewById(R.id.btn_lecturar_manual);
		btnQR.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				txtdni.setText("");
				lblmensaje.setText("");
				Intent intQR = new Intent(ProductividadManualActivity.this, EscanearQR.class);
				startActivity(intQR);
			}
		});

		btnAgregar = (Button) findViewById(R.id.btn_agregar_manual);
		btnAgregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (validate() == true) {
					/*if(Application.context.getProductividadController().verificarDNI(txtdni.getText().toString())==true){
						lblmensaje.setText("DNI ya existe : " + txtdni.getText().toString());
						lblmensaje.setTextColor(Color.RED);
						ProductividadDetalleActivity.reloadProducts();
						ProductividadResumenActivity.reloadProducts();
					}else{
						try{
							addRegister(txtdni.getText().toString());
							lblmensaje.setText("DNI Agregado: " + txtdni.getText().toString());
							lblmensaje.setTextColor(Color.RED);
							txtdni.setText("");
							ProductividadDetalleActivity.reloadProducts();
							ProductividadResumenActivity.reloadProducts();
							try {
								txtdni.requestFocus();
							} catch (Exception e33) {
								// TODO: handle exception
							}
						} catch (Exception e) {
							showSimpleMesage(e.getMessage());
							e.printStackTrace();
						}
					}*/
					if(Application.context.getProductividadController().verificarDNI(txtdni.getText().toString())==true){
						try{
							addRegister(txtdni.getText().toString());

							ProductividadDetalleActivity.reloadProducts();
							ProductividadResumenActivity.reloadProducts();
							try {
								txtdni.requestFocus();
							} catch (Exception e33) {
								// TODO: handle exception
							}
						}catch (Exception e) {
							showSimpleMesage(e.getMessage());
							e.printStackTrace();
						}
					}else{
						lblmensaje.setText("Verifique DNI : " + txtdni.getText().toString());
						lblmensaje.setTextColor(Color.RED);
						ProductividadDetalleActivity.reloadProducts();
						ProductividadResumenActivity.reloadProducts();
					}
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
						lblmensaje.setText("");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		}

		txtdni.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				try {
					int idx2 = txtdni.getText().length();
					if(idx2== 8)
						txtCantidad.requestFocus();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after) {}

			@Override
			public void afterTextChanged(Editable s) {}
		});

		txtdni.setOnKeyListener(new View.OnKeyListener() {
			private int idx = -1;

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				int idx2 = txtdni.getText().length();
				if (idx != idx2) {
					idx = idx2;
					if (idx == 8) {
						try {
							txtCantidad.requestFocus();
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
				return false;
			}
		});


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

	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Agregar Productividad";
	}

}