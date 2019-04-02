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
import pe.worktime.model.entity.Turno;
import pe.worktime.util.AbstractActivity;

public class TurnoActivity extends AbstractActivity {

	public static int viewLoad = R.layout.turnos;
	protected Button btnTarea = null;
	protected Button btnInfo = null;
	protected Spinner cboTurno = null;	
	private List<Turno> lst;
	protected TextView lblNombreFundo=null;
	protected TextView lblNombreModulo=null;
	
	@Override
	protected void postLoadView() {
		
		lblNombreFundo = (TextView) findViewById(R.id.lblFundo);
		//lblNombreFundo.setText("Fundo: " + ContextTest.ctx.context.getSelected().getFundo());		
		lblNombreFundo.setText("Fundo: " + Application.context.getFundoController().getSelected().getFundo());
		lblNombreModulo = (TextView) findViewById(R.id.lbl_modulo1);
		//lblNombreModulo.setText("Modulo: " + ContextTest.ctx.context.getSelectedModulo().getModulo());
		lblNombreModulo.setText("Modulo: " + Application.context.getFundoController().getModuloSelected().getModulo());
		
		String[] datos;
		//lst = ContextTest.ctx.context.getSelectedModulo().getTurnos();
		lst = Application.context.getFundoController().getModuloSelected().getTurnos();
		int size = 0;
		size = lst.size();
		System.out.println("Size:" + size );
		datos = new String[size];
		for (int i = 0; i < size; i++) {
			datos[i] = lst.get(i).getCod_turno();
			Log.e("TURNOS", datos[i] = lst.get(i).getCod_turno());		
		}
		//cboModulo = new Spinner(this);
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, datos);

		cboTurno = (Spinner) findViewById(R.id.cboTurnos);
		
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cboTurno.setAdapter(adaptador);

		cboTurno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent,
							View v, int position, long id) {
						Log.e("ITEM SELECCIONADO", lst.get(position)
								.getCod_turno());
						
					}
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
		
		loadPrincipal();
	}
	
	protected void loadPrincipal(){
			
		btnTarea = (Button) findViewById(R.id.btn_Tarea);
		btnTarea.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//ContextTest.context.seleccionarTurno(cboTurno.getSelectedItemPosition());
				//ContextTest.ctx.context.seleccionarTurno(cboTurno.getSelectedItemPosition());
				Application.context.getFundoController().seleccionarTurno(cboTurno.getSelectedItemPosition());
				Intent i = new Intent(getApplicationContext(),
						TareaActivity.class);
				startActivity(i);
			}
		});
		
		btnInfo = (Button) findViewById(R.id.btn_Info);
		btnInfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//ContextTest.ctx.context.seleccionarTurno(cboTurno.getSelectedItemPosition());
				Application.context.getFundoController().seleccionarTurno(cboTurno.getSelectedItemPosition());
				Intent i = new Intent(getApplicationContext(),
						InfoTurnoActivity.class);
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
		return "Turno";
	}
}