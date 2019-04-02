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
import android.widget.TextView;

import com.worktime.R;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.model.entity.ProductividadDetalle;
import pe.worktime.util.AbstractActivity;

public class ProductividadEntradaActivity extends AbstractActivity {

	public static int viewLoad = R.layout.productividad_automatica;
	protected EditText txtCantidad = null;
	protected Button btnAgregar = null;
	protected TextView lblmensaje = null;
	protected Button btn_limpia_cant = null;
	protected Button btnQR = null;

	public void addRegister() throws Exception {
		Application.context.getProductividadController().modificarProductividad("", txtCantidad.getText().toString());
		txtCantidad.setText("");
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

		lblmensaje = (TextView) findViewById(R.id.txtMsjAgregadoAutomatico);
		lblmensaje.setText("");

		txtCantidad = (EditText) findViewById(R.id.txt_cantidad);


		btnAgregar = (Button) findViewById(R.id.btn_agregar);
		btnAgregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				try{
					addRegister();
					ProductividadDetalleActivity.reloadProducts();
					ProductividadResumenActivity.reloadProducts();
						try {
							txtCantidad.requestFocus();
							} catch (Exception e33) {
								// TODO: handle exception
							}
						}catch (Exception e) {
							showSimpleMesage(e.getMessage());
							e.printStackTrace();
						}


			}
		});


		btn_limpia_cant = (Button) findViewById(R.id.btn_limpia_cantidad);
		if(btn_limpia_cant != null)
		{
			btn_limpia_cant.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						txtCantidad.setText("");
						lblmensaje.setText("");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		}

	}

//	@Override
//	protected void onResume() {
//		super.onResume();
//		if(EscanearQR.dato == "") {
//			lblmensaje.setText(EscanearQR.mensaje);
//			lblmensaje.setTextColor(Color.RED);
//			EscanearQR.mensaje = "";
//		} else {
//			txtdni.setText(EscanearQR.dato);
//			EscanearQR.dato = "";
//		}
//	}

	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Agregar Productividad";
	}

}