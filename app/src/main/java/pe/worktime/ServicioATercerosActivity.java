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
import pe.worktime.model.entity.Empresa;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Modulo;
import pe.worktime.model.entity.Turno;

import pe.worktime.util.AbstractActivity;

public class ServicioATercerosActivity extends AbstractActivity {

	public static int viewLoad = R.layout.servicioaterceros;
	protected TextView lblempresa = null;
	protected TextView lblfundo = null;
	protected TextView lblmodulo = null;
	protected TextView lblActividad = null;
	protected TextView lblTurnoGrupo = null;
	
	protected Spinner cboempresa = null;
	protected Spinner cboFundoGrupo = null;
	protected Spinner cboModuloGrupo = null;
	protected Spinner cboActividadGrupo = null;
	protected Spinner cboTurnoGrupo = null;
	
	protected Button btn_Siguiente_servicio = null;
	
	
	private List<Fundo> lst;	
	private List<Modulo> lstMod;
	private List<Turno> lstTurno;
	private List<Actividad> lstDirecta = null;
	
	
	private boolean autoSet = true;
	
	//--------------------------------------------------------------------------------------------------
	public void loadEmpresa() {
		
		// TODO Load Empresas
		
		List<Empresa> lst = Application.context.getEmpresaController().listar();
		String [] datos = new String[lst.size()];
		for (int i = 0; i < lst.size(); i++) {
			datos[i] = lst.get(i).getEmpresa();
		}				
		ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
		adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboempresa.setAdapter(adaptador2);
		
		//cboempresa = new Spinner(this);		
		cboempresa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
				
				Application.context.getEmpresaController().seleccionarEmpresaServida(position);
				
				//Application.context.getFundoController().seleccionar(position);				
				//Log.e("ITEM SELECCIONADO", lst.get(position).getFundo());
				loadFundo();
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		if(autoSet)
			Application.context.getEmpresaController().seleccionarEmpresaServida(cboempresa.getSelectedItemPosition());
			//Application.context.getEmpresaController().seleccionarEmpresaServida(0);
		
	
	}
	
	
	//---------------------------------------------------------------------------------------------------
	
	
	public void loadFundo() {

		// TODO Load Fundo
		
		//System.out.println("Empresa Servida: " + Application.context.getEmpresaController().getEmpresaServida().toString());
		
		String[] datos;
		//lst = Application.context.getFundoController().listar();
		lst = Application.context.getFundoController().listarDeEmpresa(Application.context.getEmpresaController().getEmpresaServida());
		int size = 0;
		size = lst.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lst.get(i).getFundo();
			Log.e("Fundos", datos[i] = lst.get(i).getFundo());
		}
		cboFundoGrupo = new Spinner(this);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,	android.R.layout.simple_spinner_item, datos);
		cboFundoGrupo = (Spinner) findViewById(R.id.cboFundoGrupo);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboFundoGrupo.setAdapter(adaptador);
		cboFundoGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
				Application.context.getFundoController().seleccionarFundoServido(position,Application.context.getEmpresaController().getEmpresaServida());				
				//Log.e("ITEM SELECCIONADO", lst.get(position).getFundo());
				loadModulo();
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		if(autoSet)
			Application.context.getFundoController().seleccionarFundoServido(cboFundoGrupo.getSelectedItemPosition(),Application.context.getEmpresaController().getEmpresaServida());
	}
	
	public void loadActividadDirecta() {

		String[] datos;
		lstDirecta = Application.context.getActividadController().listaDirecta();
		int size = 0;
		size = lstDirecta.size();
		System.out.println("lstDirecta:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstDirecta.get(i).getCodActividad()+" - "+lstDirecta.get(i).getActividad();
			Log.e("ACTIVIDAD DIRECTA", lstDirecta.get(i).getActividad());
		}
		
		ArrayAdapter<String> adaptadorActividad = new ArrayAdapter<String>(	this, android.R.layout.simple_spinner_item, datos);
		cboActividadGrupo = (Spinner) findViewById(R.id.cboActividadGrupo);
		adaptadorActividad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboActividadGrupo.setAdapter(adaptadorActividad);
		cboActividadGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,View v, int position, long id) {
						Application.context.getActividadController().seleccionarActividadServida(position);
						//Log.e("ACTIVIDAD DIRECTA SELECCIONADO", lstDirecta.get(position).getCodAcividad());
						//TODO Comentado Actual
						//loadModulo();
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
		if(autoSet)
			Application.context.getActividadController().seleccionarActividadServida(cboActividadGrupo.getSelectedItemPosition());
	}
/*
	public void loadActividadIndirecta() {
		
		String[] datos;
		lstIndirecta = Application.context.getActividadController().listaIndirecta();
		int size = 0;
		size = lstIndirecta.size();
		System.out.println("lstIndirecta:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstIndirecta.get(i).getCodActividad()+" - "+lstIndirecta.get(i).getActividad();
		}
		
		ArrayAdapter<String> adaptadorActividadIndirecta = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, datos);
		cboActividadIndirecta = (Spinner) findViewById(R.id.cboActividadIndirectaGrupo);
		adaptadorActividadIndirecta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboActividadIndirecta.setAdapter(adaptadorActividadIndirecta);
		cboActividadIndirecta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,android.view.View v, int position, long id) {
						Log.e("ACTIVIDAD INDIRECTA SELECCIONADO", ":"+position);
						Application.context.getActividadController().seleccionarIndirecta(position);
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}
	
	*/
	
	// TODO  Load Modulo
	public void loadModulo() {

		Log.d("INFO", "Reload Load Modulo");
		
		String[] datos;
		lstMod = Application.context.getFundoController().getFundoServido().getModulos();
		int size = 0;
		size = lstMod.size();
		System.out.println("Size Load Modulo :" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstMod.get(i).getModulo();
			System.out.println("Modulo:" + lstMod.get(i).getModulo() + " - "+lstMod.get(i).getCodModulo());
		}
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
		cboModuloGrupo = (Spinner) findViewById(R.id.cboModuloGrupo);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboModuloGrupo.setAdapter(adaptador);
		cboModuloGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {
						/*
						Log.e("ITEM SELECCIONADO", lstMod.get(position)
								.getModulo());
						*/
						Application.context.getFundoController().seleccionarModuloServido(position);
						loadTurno();
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

		if(autoSet)
			Application.context.getFundoController().seleccionarModuloServido(cboModuloGrupo.getSelectedItemPosition());
		//else
		//	loadTurno();

	}

	
	
	/*
	public void loadTipoActividad() {
		
		final String[] datos;
		datos = new String[2];
		datos[0] = "Directa";
		datos[1] = "Indirecta";
		int size = 0;
		size = datos.length;
		System.out.println("Size Tipo Actividad:" + size);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
		cboTipoActividad = (Spinner) findViewById(R.id.cboTipoActividadGrupo);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboTipoActividad.setAdapter(adaptador);
		cboTipoActividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					
			public void onItemSelected(AdapterView<?> parent,
						android.view.View v, int position, long id) {
						ContextTest.idxTyActividad = cboTipoActividad.getSelectedItemPosition();
						ContextTest.idxDirecORIn = cboTipoActividad.getSelectedItemPosition();
						Log.e("ITEM idxTyActividad", String.valueOf( ContextTest.idxTyActividad));
						switch (ContextTest.idxTyActividad) {
						case 0:
							loadActividadDirecta();
							loadTurno();
							cboTurno.setVisibility(View.VISIBLE);
							lblConsumidorIndirecto.setVisibility(View.GONE);
							lblTurno.setVisibility(View.VISIBLE);
							cboConsumidorIndirecto.setVisibility(View.GONE);
							cboActividad.setVisibility(View.VISIBLE);
							cboActividadIndirecta.setVisibility(View.GONE);
							break;
						case 1:
							loadActividadIndirecta();
							loadConsumidorIndirecto();
							cboConsumidorIndirecto.setVisibility(View.VISIBLE);
							lblConsumidorIndirecto.setVisibility(View.VISIBLE);
							lblTurno.setVisibility(View.GONE);
							cboTurno.setVisibility(View.GONE);
							cboActividad.setVisibility(View.GONE);
							cboActividadIndirecta.setVisibility(View.VISIBLE);
							break;
						}
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}
*/
	

/*
	public void loadActividad() {

		String[] datos;
		lstActividad = Application.context.getActividadController().listar();
		int size = 0;
		size = lstActividad.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstDirecta.get(i).getCodActividad()+" - "+lstActividad.get(i).getCodActividad();
		}
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
		cboActividadGrupo = (Spinner) findViewById(R.id.cboActividadGrupo);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboActividadGrupo.setAdapter(adaptador);
		cboActividadGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,
				android.view.View v, int position, long id) {
				Log.i("onItemSelected", "GrupoActivity:cboActividad");
				Application.context.getActividadController().seleccionar(position);
			}
					
			public void onNothingSelected(AdapterView<?> parent) {

					}
		});
		
	}*/

	public void loadTurno() {

		String[] datos;
		lstTurno = Application.context.getFundoController().getModuloServido().getTurnos();
		int size = 0;
		size = lstTurno.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstTurno.get(i).getNombre();
		}
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);
		cboTurnoGrupo = (Spinner) findViewById(R.id.cboTurnoGrupo);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboTurnoGrupo.setAdapter(adaptador);
		cboTurnoGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,
					View v, int position, long id) {
				Log.e("ITEM SELECCIONADO", lstTurno.get(position)
						.getCod_turno());
				Application.context.getFundoController().seleccionarTurnoServido(position);
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		if(autoSet)
			Application.context.getFundoController().seleccionarTurnoServido(cboTurnoGrupo.getSelectedItemPosition());
	}

	
	@Override
	protected void postLoadView() {
		
		// TODO Post Load View
		cboempresa = (Spinner) findViewById(R.id.cboEmpresaServicio);
		cboTurnoGrupo = (Spinner) findViewById(R.id.cboTurnoGrupo);
		//cboConsumidorIndirecto = (Spinner) findViewById(R.id.cboConsumidorIndirectoGrupo);
		cboActividadGrupo = (Spinner) findViewById(R.id.cboActividadGrupo);
	//	cboActividadIndirecta = (Spinner) findViewById(R.id.cboActividadIndirectaGrupo);
		lblmodulo = (TextView) findViewById(R.id.lblmodulo);
		lblmodulo.setText("Modulo:");
		lblempresa = (TextView) findViewById(R.id.lblempresa);
		//lblTipoActividad.setText("Tipo Actividad: ");
		//lblHorario = (TextView) findViewById(R.id.lblHorario);
		//lblHorario.setText("Horario:");
		lblActividad = (TextView) findViewById(R.id.lblActividad);
		lblActividad.setText("Actividad: ");
		lblfundo = (TextView) findViewById(R.id.lblFundo);
		lblfundo.setText("Fundo:");
		lblTurnoGrupo = (TextView) findViewById(R.id.lblTurnoGrupo);
		lblTurnoGrupo.setText("Turno:");
	//	lblConsumidorIndirecto = (TextView) findViewById(R.id.lblConsumidorIndirecto);
	//	lblConsumidorIndirecto.setText("Consumidor Indirecto:");
	//	loadTipoLabor();
		loadEmpresa();
		loadFundo();
		loadModulo();
		loadActividadDirecta();
		loadTurno();
	//	loadTipoActividad();
	//	loadGrupo();
	//	loadHorario();
		
		autoSet = false;
		
		//loadActividad();
		btn_Siguiente_servicio = (Button) findViewById(R.id.btn_Siguiente_servicio);
		btn_Siguiente_servicio.setOnClickListener(new View.OnClickListener() {
			
		
			@Override
			public void onClick(View view) {
			//	Log.e("INGRESO","Ingreso2");
				
			//	Application.context.getTurnoDiaController().seleccionar(cboHorario.getSelectedItemPosition());
				
				//if(cboTipoActividad.getSelectedItemPosition() == 0){
					//DIRECTA
				////	Application.context.getActividadController().seleccionarDirecta(cboActividadGrupo.getSelectedItemPosition());
				//}else if(cboTipoActividad.getSelectedItemPosition() == 1){
					//INDIRECTA
			    //		Application.context.getActividadController().seleccionarIndirecta(cboActividadIndirecta.getSelectedItemPosition());
			    //	}
				
				//Application.context.getActividadController().seleccionar(cboActividadIndirecta.getSelectedItemPosition());
				//Application.context.getGrupoController().seleccionar(cboGrupo.getSelectedItemPosition());
					
					
					
				//empresa -> Application.context.getEmpresaController.seleccionarEmpresaServida(cboEmpresa.getSelectedItemPosition());
				
					
					
				////Application.context.getFundoController().seleccionarModulo(cboModuloGrupo.getSelectedItemPosition());
				String codEmpresa;
				codEmpresa = Application.context.getEmpresaController().getEmpresaServida().getCodEmpresa().toString();
				
				Log.e("codEmpresa: ", codEmpresa);
				Log.e("codFundo: ", Application.context.getFundoController().getFundoServido().getCodFundo());
				Log.e("codModulo: ",Application.context.getFundoController().getModuloServido().getCodModulo());
				Log.e("codTurno: ", Application.context.getFundoController().getTurnoServido().getCod_turno());
				Log.e("codActividad: ", Application.context.getActividadController().getActividadServida().getCodActividad());
		
				
				Log.e("INGRESO","Antes");
				try {
					Application.context.getHorasConsumidorController().addServicioATerceros(
							Application.context.getEmpresaController().getEmpresaServida().getCodEmpresa(), 
							Application.context.getFundoController().getFundoServido().getCodFundo(),
							Application.context.getFundoController().getModuloServido().getCodModulo(), 
							Application.context.getFundoController().getTurnoServido().getCod_turno(),
							Application.context.getActividadController().getActividadServida().getCodActividad()
						    );
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				Log.e("INGRESO","Despues");
				
				switch (ContextTest.idxTipoEntrada) {
				case 0:     //Manual
					//if (ContextTest.idxTipoLabor == 0){ //MANUAL - HORA EFECTIVA
							//Application.context.getHorasConsumidorController().setSelected(new HorasConsumidor());						
							Intent i = new Intent(getApplicationContext(),AsistenciaTabActivity.class);
							startActivity(i);			
				//	}else if(ContextTest.idxTipoLabor == 1){ // TAREA - MANUAL
							
						
				//	}
							break;
				case 1:		//Automatica
				//	if (ContextTest.idxTipoLabor == 0){  //AUTOMATICA - HORA EFECTIVA
							//Application.context.getHorasConsumidorController().setSelected(new HorasConsumidor());						
							Intent ii = new Intent(getApplicationContext(),	AsistenciaTabActivity.class);
							startActivity(ii);
				//	} else if(ContextTest.idxTipoLabor == 1){ //AUTOMATICA - TAREA
						
				//	}
					break;
				
				}
			}
		});
	}

	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Servicio a Terceros";
	}
}
