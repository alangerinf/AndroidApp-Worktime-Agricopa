package pe.worktime;

import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.entity.Turno;

public class AutoAsistencia extends Activity {

	protected Spinner cboTurno = null;
	protected Spinner cboConsumidorIndirecto = null;
	protected TextView lblConsumidor = null;
	protected TextView lblTurno = null;
	protected CheckBox CheckAutoIngresar = null;
	protected EditText txtDoc = null;
	protected Button btnAgregar = null;
	protected TextView txtDetalle = null;
	private List<Turno> lst1 = null;

	public void loadConsumidor() {

		final List<ConsumidorIndirecto> lst = Application.context
				.getConsumidorIndirectoController().listar();
		int size = lst.size();
		String[] datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lst.get(i).getDescripcion();
		}

		ArrayAdapter<String> adaptadorConsumidor = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, datos);

		cboConsumidorIndirecto = (Spinner) findViewById(R.id.spinner1);

		adaptadorConsumidor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboConsumidorIndirecto.setAdapter(adaptadorConsumidor);

		cboConsumidorIndirecto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {
						Application.context.getConsumidorIndirectoController()
								.seleccionar(position);
						Log.e("TURNO SELECCIONADO", String.valueOf(lst.get(
								position).getCodConsumidor()));
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	public void loadTurno() {

		String[] datos;

		lst1 = Application.context.getFundoController().getModuloSelected()
				.getTurnos();
		int size = 0;
		size = lst1.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lst1.get(i).getNombre();
			// Log.e("TURNO", datos[i] = lst1.get(i).getCod_turno());
		}

		ArrayAdapter<String> adaptadorGrupo = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);
		cboTurno = (Spinner) findViewById(R.id.cboTurno);
		adaptadorGrupo
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboTurno.setAdapter(adaptadorGrupo);

		cboTurno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,
					View v, int position, long id) {

				Application.context.getFundoController().seleccionarTurno(
						position);
				// ContextTest.ctx.context.getSelectedTurno().getCod_turno();
				// Log.e("TURNO SELECCIONADO",String.valueOf(lst1.get(position).getCod_turno()));
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		
	}

	public void loadData() {

		lblConsumidor = (TextView) findViewById(R.id.lblConsumidorIndirecto);
		lblTurno = (TextView) findViewById(R.id.lblTurno);
		cboTurno = (Spinner) findViewById(R.id.cboTurno);
		cboConsumidorIndirecto = (Spinner) findViewById(R.id.spinner1);

		Log.e("INDICE TIPO ACTIVIDAD",
				String.valueOf(ContextTest.idxTyActividad));

		switch (ContextTest.idxTyActividad) {

		case 0:
			loadTurno();
			lblConsumidor.setVisibility(View.GONE);
			cboConsumidorIndirecto.setVisibility(View.GONE);
			lblTurno.setVisibility(View.VISIBLE);
			cboTurno.setVisibility(View.VISIBLE);
			break;

		case 1:
			loadConsumidor();

			lblConsumidor.setVisibility(View.VISIBLE);
			cboConsumidorIndirecto.setVisibility(View.VISIBLE);
			lblTurno.setVisibility(View.GONE);
			cboTurno.setVisibility(View.GONE);
			break;
		}
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

		txtDoc = (EditText) findViewById(R.id.txt_doc);

		if (txtDoc.getText().length() != 8) {
			// showSimpleMesage("DNI es requerido ");
			Log.e("DNI", "INGRESAR DNI");
			return false;
		} else if (isText(txtDoc.getText().toString()) == 0) {
			Log.e("DNI", "ES INCORRECTO");
			// showSimpleMesage("Dni incorrecto ");
			return false;
		}
		return true;
	}

	public void addRegister(String dni) throws Exception {

		String doc = dni;
		switch (ContextTest.idxTyActividad) {
		case 0:
			// String CodTurno =
			// ContextTest.ctx.context.getSelectedTurno().getCod_turno();
			String CodTurno = Application.context.getFundoController().getTurnoSelected().getCod_turno();
			//try{
				Application.context.getHorasConsumidorController().addDetalleDirecto(CodTurno, doc);
			//} catch (Exception e) {
			//	showSimpleMesage(e.getMessage());
			//}
			break;
		case 1:
			String codConsumidor = Application.context
					.getConsumidorIndirectoController().getSelected()
					.getCodConsumidor();
			//try {
				Application.context.getHorasConsumidorController()
				.addDetalleIndirecto(codConsumidor, doc);
			//} catch (Exception e) {
			//	showSimpleMesage(e.getMessage());
			//}
			
			break;
		}
	}
	
	protected void showSimpleMesage(String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message)
		       .setCancelable(false)
		       .setPositiveButton("Ok", null);
		AlertDialog alert = builder.create();
		alert.show();
	}
	

	public void IsNotCheked() {

		txtDetalle = (TextView) findViewById(R.id.txt_detalle);
		txtDetalle.setText("");
		txtDoc = (EditText) findViewById(R.id.txt_doc);
		btnAgregar = (Button) findViewById(R.id.btn_agregar);
		btnAgregar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (validate() == true) {
					try{
						addRegister(txtDoc.getText().toString());
						txtDetalle.setText("Agregado DNI: "+ txtDoc.getText().toString());
						txtDetalle.setText("");
					} catch (Exception e) {
						showSimpleMesage(e.getMessage());
					}
				}
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.auto_asistencia);
		loadData();
		@SuppressWarnings("unused")
		final AutoAsistencia that = this;
		txtDoc = (EditText) findViewById(R.id.txt_doc);
		CheckAutoIngresar = (CheckBox) findViewById(R.id.checkAutoAgregado);

		if (CheckAutoIngresar.isChecked() == false) {
			Log.e("ENTRO", "IS NOT CHEKED");
			IsNotCheked();
		}

		CheckAutoIngresar.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							Log.e("Entro", "IsChecked");
							txtDetalle = (TextView) findViewById(R.id.txt_detalle);
							txtDoc.addTextChangedListener(new TextWatcher() {
								@Override
								public void onTextChanged(CharSequence arg0,
										int arg1, int arg2, int arg3) {
									// TODO Auto-generated method stub
									if (arg0.length() == 8) {
										txtDetalle.setText("Agregado DNI: " + arg0);
										Log.e("DNI", "DNI AGREGADO");
										try {
											addRegister(txtDoc.getText().toString());
										} catch (Exception e) {
											showSimpleMesage(e.getMessage());
										}
										
										// ResumenAsistenciaActivity.reloadProducts();
									}
								}

								@Override
								public void beforeTextChanged(
										CharSequence arg0, int arg1, int arg2,
										int arg3) {
									// TODO Auto-generated method stub
								}

								@Override
								public void afterTextChanged(Editable arg0) {
									// TODO Auto-generated method stub
								}
							});
						} else {
							IsNotCheked();
							Log.e("ENTRO", "IS NOT CHEKED ONCHANGE");
						}
					}
				});
	}
}