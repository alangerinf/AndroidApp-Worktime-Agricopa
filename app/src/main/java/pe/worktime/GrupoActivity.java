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
import pe.worktime.controller.util.LogController;
import pe.worktime.model.entity.Actividad;
import pe.worktime.model.entity.ConsumidorIndirecto;
import pe.worktime.model.entity.Cultivo;
import pe.worktime.model.entity.Fundo;
import pe.worktime.model.entity.Grupo;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.model.entity.Modulo;
import pe.worktime.model.entity.Turno;
import pe.worktime.model.entity.TurnoDia;
import pe.worktime.util.AbstractActivity;

public class GrupoActivity extends AbstractActivity {

	public static int viewLoad = R.layout.grupo;
	protected TextView lblmod = null;
	protected TextView lblTipoLabor = null;
	protected TextView lblTipoActividad = null;
	protected TextView lblHorario = null;
	protected TextView lblActividad = null;
	protected TextView lblGrupo = null;
	protected TextView lblTurno = null;
	protected TextView lblConsumidorIndirecto = null;
	protected Button btnSiguiente = null;
	protected Button btnTransferir = null;
	protected Spinner cboTipoLabor = null;
	protected Spinner cboFundo = null;
	protected Spinner cboModulo = null;
	protected Spinner cboTipoActividad = null;
	protected Spinner cboGrupo = null;
	protected Spinner cboHorario = null;
	protected Spinner cboActividad = null;
	protected Spinner cboActividadIndirecta = null;	
	protected Spinner cboTurno = null;
	protected Spinner cboConsumidorIndirecto = null;
	protected Spinner cboCultivo = null;
	private List<Fundo> lst;
	private List<Modulo> lstMod;
	private List<Grupo> lstGrupo;
	private List<TurnoDia> lstTurnoDia;
	private List<Turno> lstTurno;
	private List<ConsumidorIndirecto> lstConsumidorIndirecto;
	private List<Actividad> lstActividad;
	private List<Actividad> lstIndirecta = null;
	private List<Actividad> lstDirecta = null;
	private List<Cultivo> lstCultivo = null;
	
	private boolean autoSet = true;
	//-----------------------------------------------------------------------------
	
	public void loadTipoLabor() {
		
		final String[] tipolabor;
		tipolabor = new String[2];
		tipolabor[0] = "Hora Efectiva";
		tipolabor[1] = "Tarea";
		int size = 0;
		size = tipolabor.length;
		System.out.println("Size Tipo Labor:" + size);
		cboTipoLabor = new Spinner(this);
		ArrayAdapter<String> adaptadorTipoLabor = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tipolabor);
		cboTipoLabor = (Spinner) findViewById(R.id.cboTipoLabor);
		cboTipoLabor.setVisibility(View.GONE);

		adaptadorTipoLabor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboTipoLabor.setAdapter(adaptadorTipoLabor);
		cboTipoLabor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> parent,
						View v, int position, long id) {
						int tl = cboTipoLabor.getSelectedItemPosition();
						switch (tl){
						case 0:
							//HORA EFECTIVA
							ContextTest.idxTipoLabor = 0; //TipoEntrada 0 para Horas Efectivas
							break;
						case 1:
							//TAREA
							ContextTest.idxTipoLabor = 1; //TipoEntrada 1 para TAREAS
							break;
						}
						
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
		
	}

	//-----------------------------------------------------------------------------
	public void loadFundo() {

		// TODO Load Fundo
		
		String[] datos;
		//lst = Application.context.getFundoController().listar();
		lst = Application.context.getFundoController().listarDeEmpresa(Application.context.getEmpresaController().getSelected());
		int size = 0;
		size = lst.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lst.get(i).getFundo();
			Log.e("Fundos", datos[i] = lst.get(i).getFundo());
		}
		cboFundo = new Spinner(this);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,	android.R.layout.simple_spinner_item, datos);
		cboFundo = (Spinner) findViewById(R.id.cboFundoGrupo);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboFundo.setAdapter(adaptador);
		cboFundo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
				Application.context.getFundoController().seleccionar(position);				
				//Log.e("ITEM SELECCIONADO", lst.get(position).getFundo());
				loadModulo();
				loadCultivo();
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		if(autoSet)
			Application.context.getFundoController().seleccionar(cboFundo.getSelectedItemPosition());
	}

	public void loadCultivo() {

		// TODO Load Cultivo

		String[] datos;

		//lstCultivo = Application.context.getCultivoController().listar();
		lstCultivo = Application.context.getCultivoController().listarPorFundo();
		int size = 0;
		size = lstCultivo.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {

				datos[i] = lstCultivo.get(i).getCultivo();
				Log.e("Cultivo", datos[i] = lstCultivo.get(i).getCultivo());

		}

		cboCultivo = new Spinner(this);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,	android.R.layout.simple_spinner_item, datos);
		cboCultivo = (Spinner) findViewById(R.id.cboCultivo);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboCultivo.setAdapter(adaptador);

			cboCultivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
					Application.context.getCultivoController().seleccionarPorFundo(position);
					//Log.e("ITEM SELECCIONADO", lst.get(position).getFundo());
					loadTipoActividad();
				}

				public void onNothingSelected(AdapterView<?> parent) {
				}
			});



		if(autoSet)
			Application.context.getCultivoController().seleccionar(cboCultivo.getSelectedItemPosition());
	}
	
	public void loadActividadDirecta() {

		String[] datos;
		lstDirecta = Application.context.getActividadController().listaDirecta();
		int size = 0;
		size = lstDirecta.size();
		System.out.println("lstDirecta:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstDirecta.get(i).getActividad();
		}

		ArrayAdapter<String> adaptadorActividad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
		cboActividad = (Spinner) findViewById(R.id.cboActividadGrupo);
		adaptadorActividad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboActividad.setAdapter(adaptadorActividad);

		cboActividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,View v, int position, long id) {
						Application.context.getActividadController().seleccionarDirecta(position);
						//loadModulo();
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	public void loadActividadIndirecta() {
		
		String[] datos;
		lstIndirecta = Application.context.getActividadController().listaIndirecta();

		int size = 0;
		size = lstIndirecta.size();
		System.out.println("lstIndirecta:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			//datos[i] = lstIndirecta.get(i).getCodActividad()+" - "+lstIndirecta.get(i).getActividad();
			datos[i] = lstIndirecta.get(i).getActividad();
		}
		
		ArrayAdapter<String> adaptadorActividadIndirecta = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, datos);
		cboActividadIndirecta = (Spinner) findViewById(R.id.cboActividadIndirectaGrupo);
		adaptadorActividadIndirecta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboActividadIndirecta.setAdapter(adaptadorActividadIndirecta);
		cboActividadIndirecta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,View v, int position, long id) {
						Log.e("ACT.IND.SELECCIONADO", ":"+position);
						Application.context.getActividadController().seleccionarIndirecta(position);
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}
	
	// TODO  Load Modulo
	public void loadModulo() {

		Log.d("INFO", "Reload Load Modulo");
		
		String[] datos;
		lstMod = Application.context.getFundoController().getSelected().getModulos();
		int size = 0;
		size = lstMod.size();
		System.out.println("Size Load Modulo :" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstMod.get(i).getModulo();
			System.out.println("Modulo:" + lstMod.get(i).getModulo() + " - "+lstMod.get(i).getCodModulo());
		}
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);

		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboModulo.setAdapter(adaptador);
		cboModulo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {

						Application.context.getFundoController().seleccionarModulo(position);
						//loadGrupo();
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});

		if(autoSet)
			Application.context.getFundoController().seleccionarModulo(cboModulo.getSelectedItemPosition());
		//else
			//loadGrupo();

	}

	public void loadGrupo() {

		Log.d("INFO", "Reload Load Grupo");
		
		String[] datos;
		lstGrupo = Application.context.getGrupoController().listar();
		int size = 0;
		size = lstGrupo.size();
		System.out.println("Size Load Grupo :" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstGrupo.get(i).getGrupo();
			System.out.println("Grupo:" + lstGrupo.get(i).getGrupo() + " - "+lstGrupo.get(i).getCodGrupo());
		}
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);

		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboGrupo.setAdapter(adaptador);
		cboGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,
					View v, int position, long id) {
				//Log.e("ITEM SELECCIONADO", lstGrupo.get(position).getCodGrupo());
				
				Application.context.getGrupoController().seleccionar(position);
				
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		if(autoSet)
			Application.context.getGrupoController().seleccionar(cboGrupo.getSelectedItemPosition());
//		else
//			loadTurno();
	}

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
						View v, int position, long id) {
						ContextTest.idxTyActividad = cboTipoActividad.getSelectedItemPosition();
						ContextTest.idxDirecORIn = cboTipoActividad.getSelectedItemPosition();
						Log.e("ITEM idxTyActividad", String.valueOf( ContextTest.idxTyActividad));
						switch (ContextTest.idxTyActividad) {
						case 0:
							loadActividadDirecta();
							//loadTurno();
							loadModulo();
							//lblTurno.setVisibility(View.VISIBLE);
							//cboTurno.setVisibility(View.VISIBLE);
							lblmod.setVisibility(View.VISIBLE);
							cboModulo.setVisibility(View.VISIBLE);
							lblConsumidorIndirecto.setVisibility(View.GONE);
							cboConsumidorIndirecto.setVisibility(View.GONE);
							cboActividad.setVisibility(View.VISIBLE);
							cboActividadIndirecta.setVisibility(View.GONE);
							ContextTest.idxDirecORIn = 0;
							break;
						case 1:
							loadActividadIndirecta();
							loadConsumidorIndirecto();
							//lblTurno.setVisibility(View.GONE);
							//cboTurno.setVisibility(View.GONE);
							lblmod.setVisibility(View.GONE);
							cboModulo.setVisibility(View.GONE);
							lblConsumidorIndirecto.setVisibility(View.VISIBLE);
							cboConsumidorIndirecto.setVisibility(View.VISIBLE);
							cboActividad.setVisibility(View.GONE);
							cboActividadIndirecta.setVisibility(View.VISIBLE);
							ContextTest.idxDirecORIn = 1;
							break;
						}
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
	}

	public void loadHorario() {

		String[] datos;
		lstTurnoDia = Application.context.getTurnoDiaController().listar();
		int size = 0;
		size = lstTurnoDia.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstTurnoDia.get(i).getCodigo();
		}
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);
		cboHorario = (Spinner) findViewById(R.id.cboHorarioGrupo);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboHorario.setAdapter(adaptador);
		cboHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {
						/*
						Log.e("ITEM SELECCIONADO", lstTurnoDia.get(position)
								.getCodigo());
						*/
						Application.context.getTurnoDiaController().seleccionar(position);
						
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
		if(autoSet)
			Application.context.getTurnoDiaController().seleccionar(cboHorario.getSelectedItemPosition());
	}

	public void loadConsumidorIndirecto() {

		String[] datos;
		lstConsumidorIndirecto = Application.context.getConsumidorIndirectoController().listar();
		int size = 0;
		size = lstConsumidorIndirecto.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			//datos[i] = lstConsumidorIndirecto.get(i).getDescripcion();
			datos[i] = lstConsumidorIndirecto.get(i).getCodConsumidor()+" - "+lstConsumidorIndirecto.get(i).getDescripcion();
			
		}
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboConsumidorIndirecto.setAdapter(adaptador);
		cboConsumidorIndirecto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {
						/*Log.e("ITEM SELECCIONADO",
								lstConsumidorIndirecto.get(position).getCodConsumidor());
						*/
						Application.context.getConsumidorIndirectoController().seleccionar(position);
						
					}

					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
		if(autoSet)
			Application.context.getConsumidorIndirectoController().seleccionar(cboConsumidorIndirecto.getSelectedItemPosition());
	}

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

//		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, datos);
//		cboActividad = (Spinner) findViewById(R.id.cboActividadGrupo);
//		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		cboActividad.setAdapter(adaptador);

		
		cboActividad = (Spinner) findViewById(R.id.cboActividadGrupo);
		/*
		ArrayAdapter<String> adaptadorActividad = new ArrayAdapter<String>(this, R.layout.my_spinner_textview, datos);
		adaptadorActividad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboActividad.setAdapter(adaptadorActividad);
		*/
		cboActividad.setAdapter(new ArrayAdapter<String>(this, R.layout.my_spinner_textview, R.id.textview_spinner, datos));
		
		
		cboActividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,
				View v, int position, long id) {
				Log.i("onItemSelected", "GrupoActivity:cboActividad");
				Application.context.getActividadController().seleccionar(position);
			}
					
			public void onNothingSelected(AdapterView<?> parent) {

					}
		});
		
	}

	public void loadTurno() {

		String[] datos;
		lstTurno = Application.context.getFundoController().getModuloSelected().getTurnos();
		int size = 0;
		size = lstTurno.size();
		System.out.println("Size:" + size);
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lstTurno.get(i).getNombre();
		}
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);

		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboTurno.setAdapter(adaptador);
		cboTurno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent,
					View v, int position, long id) {
				Log.e("ITEM SELECCIONADO", lstTurno.get(position)
						.getCod_turno());
				Application.context.getFundoController().seleccionarTurno(position);
				//Application.context.getFundoController().seleccionarTurno(0);
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		if(autoSet)
			Application.context.getFundoController().seleccionarTurno(cboTurno.getSelectedItemPosition());
	}



	@Override
	protected void postLoadView() {
		
		// TODO Post Load View

		//Spinner
		cboConsumidorIndirecto = (Spinner) findViewById(R.id.cboConsumidorIndirectoGrupo);
		cboActividad = (Spinner) findViewById(R.id.cboActividadGrupo);
		cboActividadIndirecta = (Spinner) findViewById(R.id.cboActividadIndirectaGrupo);
		cboGrupo = (Spinner) findViewById(R.id.cboGrupoGrup);
		cboGrupo.setVisibility(View.GONE);
		cboTurno = (Spinner) findViewById(R.id.cboTurnoGrupo);
		cboTurno.setVisibility(View.GONE);
		cboModulo = (Spinner) findViewById(R.id.cboModuloGrupo);
		cboModulo.setVisibility(View.GONE);
		btnSiguiente = (Button) findViewById(R.id.btn_Siguiente_Grupo);
		btnTransferir = (Button) findViewById(R.id.btn_Tranferir);

		lblmod = (TextView) findViewById(R.id.lblmodulo);
		lblmod.setVisibility(View.GONE);
		lblTipoActividad = (TextView) findViewById(R.id.lblTipoActividad);
		lblTipoLabor = (TextView) findViewById(R.id.lblTipoLabor);
		lblTipoLabor.setVisibility(View.GONE);
		lblActividad = (TextView) findViewById(R.id.lblActividad);
		lblGrupo = (TextView) findViewById(R.id.lblGrupo);
		lblGrupo.setVisibility(View.GONE);
		lblTurno = (TextView) findViewById(R.id.lblTurnoGrupo);
		lblTurno.setVisibility(View.GONE);
		lblConsumidorIndirecto = (TextView) findViewById(R.id.lblConsumidorIndirecto);


//		lblHorario = (TextView) findViewById(R.id.lblHorario);
//		lblHorario.setText("Horario:");
//		lblHorario.setVisibility(View.GONE);

		//Load Spinner
		loadTipoLabor();
		loadFundo();
		//loadCultivo();
		//loadModulo();
		//loadTipoActividad();
		//loadGrupo();

		//loadHorario();
		
		autoSet = false;



		if (ContextTest.idxTipoEntradaTransferencia == 3) {
			btnTransferir.setVisibility(View.VISIBLE);
			btnSiguiente.setVisibility(View.GONE);
		}else{
			btnTransferir.setVisibility(View.GONE);
			btnSiguiente.setVisibility(View.VISIBLE);
		}

		btnTransferir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String hInicioFin = Application.context.getFecha();
				//ContextTest.idxTipoEntradaTransferencia = -1;
				/// <Logica>

				try
				{
					HorasConsumidor planillaOrigen  = Application.context.getHorasConsumidorController().getSelected();


					/// PLANILLA DE DESTINO
				Application.context.getHorasConsumidorController().setSelected(new HorasConsumidor());


					if(cboTipoActividad.getSelectedItemPosition() == 0){
						//DIRECTA
						String CodModulo = Application.context.getFundoController().getModuloSelected().getCodModulo().toString();
						String modulo = Application.context.getFundoController().getModuloSelected().getModulo().toString();

						Application.context.getActividadController().seleccionarDirecta(cboActividad.getSelectedItemPosition());
						Application.context.getHorasConsumidorController().getSelected().setCodigoCecoOModulo(CodModulo);
						Application.context.getHorasConsumidorController().getSelected().setNombreCecoOModulo(modulo);
						Application.context.getHorasConsumidorController().getSelected().setTipoActividad("D");
					}else if(cboTipoActividad.getSelectedItemPosition() == 1){
						//INDIRECTA
						String codConsumidor = Application.context.getConsumidorIndirectoController().getSelected().getCodConsumidor().toString();
						String Consumidor = Application.context.getConsumidorIndirectoController().getSelected().getDescripcion().toString();

						Application.context.getActividadController().seleccionarIndirecta(cboActividadIndirecta.getSelectedItemPosition());
						Application.context.getHorasConsumidorController().getSelected().setCodigoCecoOModulo(codConsumidor);
						Application.context.getHorasConsumidorController().getSelected().setNombreCecoOModulo(Consumidor);
						Application.context.getHorasConsumidorController().getSelected().setTipoActividad("I");
					}

					Application.context.getHorasConsumidorController().getSelected().setImei(Application.context.getIMEI());
					Application.context.getHorasConsumidorController().getSelected().setCodSupervisor(Application.context.getUser());
					Application.context.getHorasConsumidorController().getSelected().setFecha(Application.context.getFecha());
					Application.context.getHorasConsumidorController().getSelected().setFechaRegistroMovil(Application.context.getFecha());
					Application.context.getHorasConsumidorController().getSelected().setTipolabor(ContextTest.idxTipoLabor);
					Application.context.getHorasConsumidorController().getSelected().setCodEmpresa(Application.context.getEmpresaController().getSelected().getCodEmpresa());
					Application.context.getHorasConsumidorController().getSelected().setCodFundo(Application.context.getFundoController().getSelected().getCodFundo());
					Application.context.getHorasConsumidorController().getSelected().setCodActividad(Application.context.getActividadController().getSelected().getCodActividad());
					Application.context.getHorasConsumidorController().getSelected().setCodGrupo("");
					Application.context.getHorasConsumidorController().getSelected().setIdCultivo(Application.context.getCultivoController().getSelected().getCodCultivo());


					for (HorasConsumidorDetalle deta : planillaOrigen.getDetalle()) {
						if ( deta.getHoraFin().equalsIgnoreCase("")) {
							HorasConsumidorDetalle objHorasConsumidirDetalle = new HorasConsumidorDetalle();
							objHorasConsumidirDetalle.setCodTrabajador(deta.getCodTrabajador());
							objHorasConsumidirDetalle.setHoraInicioMovil(hInicioFin);
							objHorasConsumidirDetalle.setHoraInicio(hInicioFin);
							objHorasConsumidirDetalle.setHoras_descanso(0);
							if (cboTipoActividad.getSelectedItemPosition() == 0) {
								objHorasConsumidirDetalle.setCodTurno(Application.context.getFundoController().getModuloSelected().getCodModulo().toString());
							} else if (cboTipoActividad.getSelectedItemPosition() == 1) {
								objHorasConsumidirDetalle.setCodConsumidor(Application.context.getConsumidorIndirectoController().getSelected().getCodConsumidor().toString());
							}
							Application.context.getHorasConsumidorController().getSelected().getDetalle().add(objHorasConsumidirDetalle);
						}
					}



					/// PLANILLA DE ORIGEN
					for (HorasConsumidorDetalle deta : planillaOrigen.getDetalle()) {
						if ( deta.getHoraFin().equalsIgnoreCase("")) {
							deta.setHoraFinIntent(hInicioFin);
							deta.setHoraFin(hInicioFin);
							deta.setHoraFinMovil(hInicioFin);
						}
					}

					Application.context.getHorasConsumidorController().getDelegate().addOrUpdateHorasConsumidors(Application.context.getUser(),planillaOrigen);
					Application.context.getHorasConsumidorController().getDelegate().addOrUpdateHorasConsumidors(Application.context.getUser(),Application.context.getHorasConsumidorController().getSelected());

					Application.context.getHorasConsumidorController().setSelected( new HorasConsumidor() );
                /// </Logica>
				Intent i = new Intent(getApplicationContext(),PlanillaAsistenciaActivity.class);
				startActivity(i);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});



		btnSiguiente.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Application.context.getHorasConsumidorController().setSelected(new HorasConsumidor());


				if(cboTipoActividad.getSelectedItemPosition() == 0){
					//DIRECTA
					String CodModulo = Application.context.getFundoController().getModuloSelected().getCodModulo().toString();
					String modulo = Application.context.getFundoController().getModuloSelected().getModulo().toString();

					Application.context.getActividadController().seleccionarDirecta(cboActividad.getSelectedItemPosition());
					Application.context.getHorasConsumidorController().getSelected().setCodigoCecoOModulo(CodModulo);
					Application.context.getHorasConsumidorController().getSelected().setNombreCecoOModulo(modulo);
					Application.context.getHorasConsumidorController().getSelected().setTipoActividad("D");

				}else if(cboTipoActividad.getSelectedItemPosition() == 1){
					//INDIRECTA
					String codConsumidor = Application.context.getConsumidorIndirectoController().getSelected().getCodConsumidor().toString();
					String Consumidor = Application.context.getConsumidorIndirectoController().getSelected().getDescripcion().toString();

					Application.context.getActividadController().seleccionarIndirecta(cboActividadIndirecta.getSelectedItemPosition());
					Application.context.getHorasConsumidorController().getSelected().setCodigoCecoOModulo(codConsumidor);
					Application.context.getHorasConsumidorController().getSelected().setNombreCecoOModulo(Consumidor);
					Application.context.getHorasConsumidorController().getSelected().setTipoActividad("I");
				}

				Application.context.getHorasConsumidorController().getSelected().setCodActividad(Application.context.getActividadController().getSelected().getCodActividad());
				Application.context.getHorasConsumidorController().getSelected().setCodGrupo("");
				Application.context.getHorasConsumidorController().getSelected().setIdCultivo(Application.context.getCultivoController().getSelected().getCodCultivo());


				Intent i = new Intent(getApplicationContext(),AsistenciaTabActivity.class);
				startActivity(i);



//				if((cboTipoActividad.getSelectedItemPosition() == 1 && Application.context.getActividadController().getSelected().getCodActividad().equalsIgnoreCase("IN29")) ||(cboTipoActividad.getSelectedItemPosition() == 1 && Application.context.getActividadController().getSelected().getCodActividad().equalsIgnoreCase("IN30") )){
//							//Si es un servicio a terceros abrir la  ventana de servicios
//							Intent iii = new Intent(getApplicationContext(),ServicioATercerosActivity.class);
//							startActivity(iii);
//				}
//				else{
//							switch (ContextTest.idxTipoEntrada) {
//							case 0:     //Manual
//
//										Intent i = new Intent(getApplicationContext(),AsistenciaTabActivity.class);
//										startActivity(i);
//										break;
//							case 1:		//Automatica
//
//										Intent ii = new Intent(getApplicationContext(),	AsistenciaTabActivity.class);
//										startActivity(ii);
//								break;
//							}
//				}
				
				
			}
		});
	}

	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Grupo";
	}

	@Override
	public void onBackPressed (){
			super.onBackPressed();
	}


}
