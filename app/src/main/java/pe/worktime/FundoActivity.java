package pe.worktime;

import java.util.List;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.model.entity.Fundo;
import pe.worktime.util.AbstractActivity;

public class FundoActivity extends AbstractActivity {

	public static int viewLoad = R.layout.fundos;
	protected Button btnModulo = null;
	protected Button btnTrabajadores = null;
	protected Button btnSincronizacion = null;
	protected Spinner cboFundos = null;
	private List<Fundo> lst;

	@Override
	protected void postLoadView() {

		String[] datos;
		lst = Application.context.getFundoController().listar();		
		int size = 0;
		size = lst.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lst.get(i).getFundo();
			Log.e("Fundos", datos[i] = lst.get(i).getFundo());
		}
		cboFundos = new Spinner(this);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);

		cboFundos = (Spinner) findViewById(R.id.cboFundos);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboFundos.setAdapter(adaptador);
		cboFundos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {
						Log.e("ITEM SELECCIONADO", lst.get(position).getFundo());
						
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
		LoadPrincipal();
	}

	protected void LoadPrincipal() {

		btnModulo = (Button) findViewById(R.id.btn_Modelo);
		btnModulo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// ContextTest.context.seleccionarFundo(cboFundos.getSelectedItemPosition());
				//ContextTest.ctx.context.seleccionarFundo(cboFundos.getSelectedItemPosition());	
				Application.context.getFundoController().seleccionar(cboFundos.getSelectedItemPosition());				
				//System.err.println("FUNDO SELECCIONADO: " + ContextTest.ctx.context.getSelected().getFundo());
				Intent i = new Intent(getApplicationContext(),
						ModuloActivity.class);
				startActivity(i);
			}
		});
	
		btnSincronizacion = (Button) findViewById(R.id.btn_Sincronizar);
		btnSincronizacion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						SincronizarActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Menu Principal";
	}
}