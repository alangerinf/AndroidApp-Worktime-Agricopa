package pe.worktime;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

//import Application;
//import HorasConsumidorDetalle;
import com.worktime.R;

import pe.worktime.util.AbstractActivity;
import pe.worktime.view.CustomAlertDialogBuilder;

public class OpcionesHistorialActivity extends AbstractActivity {

	public static int viewLoad = R.layout.opciones_historial;
	
	private static AbstractActivity that;

	protected Button btn_borrar_historial = null;
	protected Button btn_recuperar_historial = null;
	protected Button btn_salir_historial = null;

	@Override
	protected void postLoadView() {
		that = this;
		btn_borrar_historial = (Button) findViewById(R.id.btn_borrar_historial);
		btn_borrar_historial.setOnClickListener(new View.OnClickListener() {
			
			
			@SuppressLint("InflateParams") 
			public void onClick(View view) {
				
				ContextTest.idxOpcionHistorial = 0;
				LayoutInflater factory = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				final View textEntryView = factory.inflate(R.layout.add_product, null);
				final CustomAlertDialogBuilder alert = new CustomAlertDialogBuilder(that);
				alert.setTitle("Borrar Historial");
				alert.setMessage("¿Está seguro que desea eliminar esta información crítica?");
				alert.setView(textEntryView);
				final AlertDialog loginPrompt = alert.create();
				alert.setPositiveButton("Borrar",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int whichButton) {
								Intent i = new Intent(getApplicationContext(),	AutenticacionHistorialActivity.class);
						    	startActivity(i);
									
							}
						});
				alert.setNegativeButton("Cancelar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int whichButton) {
								loginPrompt.dismiss();
							}
						});
				alert.setCancelable(false);
				alert.show();
				
			}
		});

		btn_recuperar_historial = (Button) findViewById(R.id.btn_recuperar_historial);
	//	btn_recuperar_historial.setVisibility(View.GONE);
		btn_recuperar_historial.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ContextTest.idxOpcionHistorial = 1;
				Intent i = new Intent(getApplicationContext(),	AutenticacionHistorialActivity.class);
		    	startActivity(i);
				
			};
		});
		
		
		
		btn_salir_historial = (Button) findViewById(R.id.btn_salir_historial);
		btn_salir_historial.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
				Intent i = new Intent(getApplicationContext(),	MenuPrincipalActivity.class);
				startActivity(i);
				
			};
		});
	}

	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Opciones Historial";
	}
}
