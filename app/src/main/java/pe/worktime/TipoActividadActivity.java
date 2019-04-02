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
import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.Grupo;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.TurnoDia;
import pe.worktime.util.AbstractActivity;

public class TipoActividadActivity extends AbstractActivity {

	public static int viewLoad = R.layout.tipo_actividad;
	protected Spinner cboTipoActividad = null;
	private static String[] array_TipoActivity = null;
	//public static int idxTyActividad = 0;
	protected Button btnSiguiente = null;
	protected Spinner cboTurnos = null;
	protected Spinner cboActividades = null;
	protected Spinner cboGrupos = null;
	protected Spinner cboHorario = null;
	protected Spinner cboActividadIndirecta = null;
	protected TextView lblTurnos = null;
	private List<Grupo> lst1 = null;
	private List<Actividad> lstIndirecta = null;
	private List<Actividad> lstDirecta = null;
	private List<TurnoDia> lst3 = null;

	public void loadTurnoDia() {

		String[] datos;
		lst3 = Application.context.getTurnoDiaController().listar();
		int size = 0;
		size = lst3.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lst3.get(i).getNombre();
			Log.e("TURNO DIA", datos[i] = lst3.get(i).getCodigo());
		}

		ArrayAdapter<String> adaptadorHorario = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);
		cboHorario = (Spinner) findViewById(R.id.cboHorario);
		adaptadorHorario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboHorario.setAdapter(adaptadorHorario);
		cboHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {
						Application.context.getTurnoDiaController()
								.seleccionar(position);
						Log.e("TURNO DIA SELECCIONADO", lst3.get(position)
								.getCodigo());
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	public void loadActividadDirecta() {

		String[] datos;
		//lstDirecta = ContextTest.ctx.listaDirecta();
		lstDirecta = Application.context.getActividadController().listaDirecta();
		int size = 0;
		size = lstDirecta.size();
		System.out.println("lstDirecta:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstDirecta.get(i).getActividad();
			Log.e("ACTIVIDAD DIRECTA", datos[i] = lstDirecta.get(i).getCodActividad());
		}
		
		ArrayAdapter<String> adaptadorActividad = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, datos);
		cboActividades = (Spinner) findViewById(R.id.cboActividades);
		adaptadorActividad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboActividades.setAdapter(adaptadorActividad);
		cboActividades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,View v, int position, long id) {
						Application.context.getActividadController().seleccionarDirecta(position);
						//Log.e("ACTIVIDAD DIRECTA SELECCIONADO", lstDirecta.get(position).getCodAcividad());
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	public void loadActividadIndirecta() {
		
		String[] datos;
		//lstIndirecta = ContextTest.ctx.listaIndirecta();
		lstIndirecta = Application.context.getActividadController().listaIndirecta();
		int size = 0;
		size = lstIndirecta.size();
		System.out.println("lstIndirecta:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstIndirecta.get(i).getActividad();
			//Log.e("ACTIVIDAD INDIRECTA", datos[i] = lstIndirecta.get(i).getCodAcividad());
		}
		
		ArrayAdapter<String> adaptadorActividadIndirecta = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, datos);
		cboActividadIndirecta = (Spinner) findViewById(R.id.cboActividadIndirecta);
		adaptadorActividadIndirecta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboActividadIndirecta.setAdapter(adaptadorActividadIndirecta);
		cboActividadIndirecta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,View v, int position, long id) {
						Application.context.getActividadController().seleccionarIndirecta(position);
						//Log.e("ACTIVIDAD INDIRECTA SELECCIONADO", lst2.get(position).getCodAcividad());
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	public void loadGrupo() {

		String[] datos;
		lst1 = Application.context.getGrupoController().listar();
		int size = 0;
		size = lst1.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lst1.get(i).getCodGrupo();
			Log.e("GRUPOS", datos[i] = lst1.get(i).getCodGrupo());
		}

		ArrayAdapter<String> adaptadorGrupo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
		cboGrupos = (Spinner) findViewById(R.id.cboGrupos);
		adaptadorGrupo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboGrupos.setAdapter(adaptadorGrupo);

		cboGrupos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {
						Application.context.getGrupoController().seleccionar(
								position);
						Log.e("GRUPO SELECCIONADO", lst1.get(position)
								.getCodGrupo());
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	@Override
	protected void postLoadView() {

		cboActividades = (Spinner) findViewById(R.id.cboActividades);
		cboActividadIndirecta = (Spinner) findViewById(R.id.cboActividadIndirecta);
		
		array_TipoActivity = new String[2];
		array_TipoActivity[0] = "Directa";
		array_TipoActivity[1] = "Indirecta";

		cboTipoActividad = new Spinner(this);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, array_TipoActivity);

		cboTipoActividad = (Spinner) findViewById(R.id.cboTipoActividad);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboTipoActividad.setAdapter(adaptador);

		cboTipoActividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {

						Log.e("ITEM SELECCIONADO TIPO ACTIVIDAD",array_TipoActivity[position]);
						ContextTest.idxTyActividad = cboTipoActividad.getSelectedItemPosition();
						Log.e("ID SELECTED TIPO ACTIVITY",
								String.valueOf(ContextTest.idxTyActividad));

						switch (ContextTest.idxTyActividad) {
						case 0:
							loadActividadDirecta();
							cboActividades.setVisibility(View.VISIBLE);
							cboGrupos.setVisibility(View.VISIBLE);
							cboActividadIndirecta.setVisibility(View.GONE);
							loadActividadDirecta();
							break;
						case 1:
							cboActividades.setVisibility(View.GONE);
							cboActividadIndirecta.setVisibility(View.VISIBLE);
							cboGrupos.setVisibility(View.VISIBLE);
							loadActividadIndirecta();
							break;
						}
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

		//loadActividad();
		
		loadGrupo();
		loadTurnoDia();
		btnSiguiente = (Button) findViewById(R.id.btn_Siguiente);
		btnSiguiente.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				ContextTest.ctx.setHoras(new HorasConsumidor());
				Application.context.getHorasConsumidorController().setSelected(new HorasConsumidor());
				Application.context.getHorasConsumidorController().getSelected().setCodTurnoDia(Application.context.getTurnoDiaController().getSelected().getCodigo());
				Application.context.getHorasConsumidorController().getSelected().setCodActividad(Application.context.getActividadController().getSelected().getCodActividad());
				Application.context.getHorasConsumidorController().getSelected().setCodGrupo(Application.context.getGrupoController().getSelected().getCodGrupo());
				Intent i = new Intent(getApplicationContext(),
						AsistenciaTabActivity.class);
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
		return "Tipo Actividad";
	}
}