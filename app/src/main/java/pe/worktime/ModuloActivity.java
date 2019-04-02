package pe.worktime;

import java.util.List;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.model.entity.Modulo;
import pe.worktime.util.AbstractActivity;

public class ModuloActivity extends AbstractActivity {

	public static int viewLoad = R.layout.modulos;
	protected Button btninfo = null;
	protected Button btnTipoActividad = null;
	protected TextView lblFundo = null;
	protected Spinner cboModulo = null;
	private List<Modulo> lst;
	

	@Override
	protected void postLoadView() {

		lblFundo = (TextView) findViewById(R.id.lblFundo);
		// lblFundo.setText("Fundo: " +
		// ContextTest.ctx.context.getSelected().getFundo());
		lblFundo.setText("Fundo: "
				+ Application.context.getFundoController().getSelected()
						.getFundo());
		String[] datos;
		// lst = ContextTest.ctx.context.getSelected().getModulos();
		lst = Application.context.getFundoController().getSelected().getModulos();
		int size = 0;
		size = lst.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lst.get(i).getModulo();
		}
		// cboModulo = new Spinner(this);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);
		cboModulo = (Spinner) findViewById(R.id.cboModulos);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboModulo.setAdapter(adaptador);
		cboModulo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {
						Log.e("ITEM SELECCIONADO", lst.get(position)
								.getModulo());
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
		loadPrincipal();
	}

	protected void loadPrincipal() {

		btnTipoActividad = (Button) findViewById(R.id.btn_TipoActividad);
		btnTipoActividad.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// ContextTest.ctx.context.seleccionarModulo(cboModulo.getSelectedItemPosition());
				Application.context.getFundoController().seleccionarModulo(
						cboModulo.getSelectedItemPosition());
				// ContextTest.ctx.context.seleccionarModulo(cboModulo.getSelectedItemPosition());
				Log.e("click boton", "entro tipoactividad");
				Intent i = new Intent(getApplicationContext(),
						TipoActividadActivity.class);
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
		return "Modulo";
	}
}