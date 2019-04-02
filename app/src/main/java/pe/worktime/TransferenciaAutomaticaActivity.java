package pe.worktime;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.util.AbstractActivity;

//import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
//import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.worktime.R;

public class TransferenciaAutomaticaActivity extends AbstractActivity {

	public static int viewLoad = R.layout.transferencia_automatica;
	protected EditText txtdni = null;
	protected TextView lblAgregado = null;
	protected Button btnAgregar = null;
	private Spinner cboDestino = null;
	private List<HorasConsumidor> lst;
	protected Button btn_limpia_dni = null;
	protected Button btnQR = null;

	public void loadDestino() {

		String cecoTurno = "";
		String nombrececoTurno = "";
		String[] datos;
		lst = Application.context.getHorasConsumidorController().listar_transferencia();
		
		if(lst == null){
			btnAgregar.setEnabled(false);
			showSimpleMesage("No existen planillas válidas.");
			btnAgregar.setVisibility(View.GONE);
			return ;
		}else if(lst.size() == 0){
			btnAgregar.setEnabled(false);
			showSimpleMesage("No existen planillas válidas.");
			btnAgregar.setVisibility(View.GONE);
			return ;
		}
		
		int size = 0;
		size = lst.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {

			nombrececoTurno = lst.get(i).getNombreCecoOModulo();
//			if(lst.get(i).getTipoActividad().equalsIgnoreCase("D")) {
//				cecoTurno = "Parcela :"+nombrececoTurno;
//			}else if(lst.get(i).getTipoActividad().equalsIgnoreCase("I")){
//				cecoTurno = "CentroCosto: "+nombrececoTurno;
//			}

			datos[i] = nombrececoTurno+" /"+Application.context.getActividadController().getNameOrDefault(lst.get(i).getCodActividad());
		}
		cboDestino = new Spinner(this);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);
		cboDestino = (Spinner) findViewById(R.id.cboDestino);
		adaptador
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboDestino.setAdapter(adaptador);
		/*
		cboDestino
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							android.view.View v, int position, long id) {
						Application.context.getHorasConsumidorController()
								.seleccionar(
										cboDestino.getSelectedItemPosition());
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
				*/
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

	protected boolean validate() {
		txtdni = (EditText) findViewById(R.id.txtDniAutomatico);
		if (txtdni.getText().length() != 8) {
			Mensaje("Ingresar DNI", "DNI es requerido");
			return false;
		}
		return true;
	}

	

	@Override
	protected void postLoadView() {
		btnAgregar = (Button) findViewById(R.id.btn_agregar_automatico);
		cboDestino = (Spinner) findViewById(R.id.cboDestino);
		loadDestino();
		lblAgregado = (TextView) findViewById(R.id.txtMsjAgregadoAutomatico);
		lblAgregado.setText("");
		txtdni = (EditText) findViewById(R.id.txtDniAutomatico);
		txtdni.setEnabled(false);
		btnAgregar = (Button) findViewById(R.id.btn_agregar_automatico);
		btnAgregar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				
				try {
				
					
					if (validate() == true) {

						if (Application.context.getHorasConsumidorController().verificarDNITransferencia(txtdni.getText().toString())) {
							//Application.context.getHorasConsumidorController().transferir(txtdni.getText().toString(),cboDestino.getSelectedItemPosition());

							ContextTest.dniTransferencia = txtdni.getText().toString();
							ContextTest.idxTransferencia = cboDestino.getSelectedItemPosition();

							for (HorasConsumidorDetalle deta : Application.context.getHorasConsumidorController().getSelected().getDetalle()) {
								if (deta.getCodTrabajador().equalsIgnoreCase(txtdni.getText().toString()) && deta.getHoraFin().equalsIgnoreCase("")) {
									deta.setHoraFinIntent(Application.context.getFecha());
									deta.setHoraFin(Application.context.getFecha());
									deta.setHoraFinMovil(Application.context.getFecha());
								}
							}


							lblAgregado.setText("Transferencia Exitosa : "+ txtdni.getText().toString());
							lblAgregado.setTextColor(Color.RED);
							txtdni.setText("");
							TransferenciaAsistenciaActivity.reloadProducts();
							ResumenTransferenciaActivity.reloadProducts();
						} else {
							lblAgregado.setText("Verifique DNI: " + txtdni.getText().toString());
							lblAgregado.setTextColor(Color.RED);
						}


					}
					
					
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
				lblAgregado.setText("");
				Intent intQR = new Intent(TransferenciaAutomaticaActivity.this, EscanearQR.class);
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
		return "Transf. Automático";
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