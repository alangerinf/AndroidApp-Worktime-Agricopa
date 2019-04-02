package pe.worktime;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.util.AbstractActivity;

public class SincronizarActivity extends AbstractActivity {

	public static int viewLoad = R.layout.sincronizacion;
	protected Button btnSyncTodos = null;
	protected Button btnSyncAsist = null;
	protected Button btnSyncLoadMaster = null;
	protected ProgressDialog pd;

	@Override
	protected void postLoadView() {
		LoadPrincipal();
	}

	protected void LoadPrincipal() {

		btnSyncTodos = (Button) findViewById(R.id.btn_SincronizarTodo);
		btnSyncTodos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				progressDialog = ProgressDialog.show(SincronizarActivity.this, "",
						"Sincronizando...");
				new Thread() {

					public void run() {
						try {
							sleep(20000);
						} catch (Exception e) {
							e.printStackTrace();
							// Log.e("tag", e.getMessage());
						}
						// dismiss the progress dialog
						progressDialog.dismiss();

					}
				}.start();
			}
		});
		
		btnSyncAsist = (Button) findViewById(R.id.btn_asistencia);
		btnSyncAsist.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					Application.context.getHorasConsumidorController().sincronizar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnSyncLoadMaster = (Button) findViewById(R.id.btn_SincronizarTodo);
		btnSyncLoadMaster.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});
	}

	@Override
	protected int getViewId() {
		// TODO Auto-generated method stub
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		// TODO Auto-generated method stub
		return "Sincronizacion";
	}

}
