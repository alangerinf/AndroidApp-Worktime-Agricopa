package pe.worktime;

//import android.annotation.SuppressLint;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
import android.content.Intent;
//import android.util.Log;
//import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.worktime.R;

import pe.worktime.app.Application;
//import AbstractDelegate;
//import UserContext;
//import Application;
//import HorasConsumidorDetalle;
import pe.worktime.util.AbstractActivity;
//import CustomAlertDialogBuilder;


public class AutenticacionHistorialActivity extends AbstractActivity {

	public static int viewLoad = R.layout.autenticacion_historial;

	protected Button btn_historial_Login = null;
	protected Button btn_historial_cancelar = null;
	protected EditText inp_historial_pass = null;
	
	

	@Override
	protected void postLoadView() {
		inp_historial_pass = (EditText) findViewById(R.id.inp_historial_pass);
		btn_historial_Login = (Button) findViewById(R.id.btn_historial_Login);
		btn_historial_cancelar = (Button) findViewById(R.id.btn_historial_cancelar);
		btn_historial_Login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
//			Intent i = new Intent(getApplicationContext(),	GrupoActivity.class);
//			startActivity(i);
			//Validar que tenga ingresado una contrase?a
				if (inp_historial_pass.getText().toString().equals("")){
					showSimpleMesage("Debe ingresar una clave.");
					return;
				}
				
					
			try{
				if(Application.context.getUsuarioController().getSelected().getClave().equals(inp_historial_pass.getText().toString())){
					//Cuando la clave es correcta.
					//showSimpleMesage("Clave Correcta!");
					
		    		
		    		
		    		switch (ContextTest.idxOpcionHistorial) {
					case 0:     //BORRAR HISTORIAL
					
								Application.context.getHorasConsumidorController().limpiarHistorial();
								showSimpleMesage("Se limpio el historial con exito.");
									
								Intent i = new Intent(getApplicationContext(),	OpcionesHistorialActivity.class);
					    		startActivity(i);			
				
								break;
					case 1:		//VER HISTORIAL
					
								Intent ii = new Intent(getApplicationContext(),	PlanillaHistorialActivity.class);
								startActivity(ii);
					
						break;
					
					}
		    		
					
				}
				else
				{
					//Cuando la clave no es correcta.
					showSimpleMesage("Clave Incorrecta!");
					return;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				
			}
				
			};
		});
		
		
		btn_historial_cancelar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
			Intent i = new Intent(getApplicationContext(),	OpcionesHistorialActivity.class);
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
		return "Ver Historial";
	}
}
