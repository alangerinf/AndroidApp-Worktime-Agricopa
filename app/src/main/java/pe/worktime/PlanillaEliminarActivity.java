package pe.worktime;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.worktime.R;

import java.util.ArrayList;
import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.util.AbstractActivity;
import pe.worktime.view.CustomAlertDialogBuilder;
import pe.worktime.view.adapter.PlanillaGrupoAdapter;

public class PlanillaEliminarActivity extends AbstractActivity {
	public static boolean isLoadProduct = false;
	public static List<HorasConsumidor> items = null;
	@SuppressWarnings("unused")
	private static AbstractActivity that;
	private static ListView list;
	private PlanillaGrupoAdapter adap = null;
	private static PlanillaEliminarActivity actual;

	protected void postLoadView() {

		actual = this;
		setContentView(R.layout.planilla_asistencia);
		loadTitle();
		try {
			items = Application.context.getHorasConsumidorController().listar();
			if (items == null) {
				items = new ArrayList<HorasConsumidor>();
			}
		} catch (Exception e) {
			items = new ArrayList<HorasConsumidor>();
		}
		list = (ListView) findViewById(R.id.list);
		View header = getHeader();
		if (header != null) {
			list.addHeaderView(header, null, false);
		}

		//Filtramos solo las horasconsumidor que no hayan sido migrados
		List<HorasConsumidor> itemsfiltered =  new ArrayList<HorasConsumidor>();
		for (HorasConsumidor item : items) {
			if (item.getMigrado() == 0){
				itemsfiltered.add(item);
			}
		}

		//adap = new PlanillaGrupoAdapter(this,android.R.layout.simple_list_item_1, items, this);
		adap = new PlanillaGrupoAdapter(this,android.R.layout.simple_list_item_1, itemsfiltered, this);
		adap.setTemplate_row(R.layout.list_row_planilla);
		list.setAdapter(adap);
		list.setClickable(true);
		that = this;
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
									final int position, long arg3) {
				LayoutInflater factory = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				final View textEntryView = factory.inflate(R.layout.add_product, null);
				final CustomAlertDialogBuilder alert = new CustomAlertDialogBuilder(that);
				alert.setTitle("Eliminar Planilla");
				alert.setMessage("");
				alert.setView(textEntryView);
				final AlertDialog loginPrompt = alert.create();
				alert.setPositiveButton("Eliminar",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
										int whichButton) {
						boolean bandera = false;
						try {
							bandera = true;
						} catch (Exception e) {
						}
						if (bandera == true) {
							try {
								@SuppressWarnings("unused")
								int idx = position -1;
								Application.context.getHorasConsumidorController().seleccionarPlanillaEliminar(idx);

								adap.notifyDataSetChanged();
								reloadProducts();

								loginPrompt.dismiss();
								Intent i = new Intent(getApplicationContext(),PlanillaEliminarActivity.class);
								startActivity(i);
							} catch (Exception e) {
								Log.e("ERROR", e.getMessage(), e);
							}
						}
					}
				});
				alert.setNegativeButton("Cancelar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
												int whichButton) {
								loginPrompt.dismiss();
							}
						});
				alert.setCancelable(false);
				alert.show();


			}
		});
	}

	@SuppressLint("InflateParams")
	private View getHeader() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View header = inflater.inflate(R.layout.util_list_title_simple_view,
				null);
		if (header != null) {
			TextView textView = (TextView) header.findViewById(R.id.textTitle);
			if (textView != null) {
				textView.setText(getTextTitle());
			}
		}
		return header;
	}


	@Override
	protected int getViewId() {
		return R.layout.planilla_asistencia;
	}

	@Override
	protected String getTextTitle() {
		return "Historial de Planillas";
	}

	@SuppressLint("HandlerLeak") @Override
	protected boolean showTitle() {
		return true;
	}

	@SuppressLint("HandlerLeak")
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// reloadProducts();
			adap.notifyDataSetChanged();
			Log.d("TRACE", "RELOAD PROD" + adap.getCount());
		}
	};

	public static void reloadProducts() {
		if (actual != null) {

			List<HorasConsumidor> lsts_ = Application.context.getHorasConsumidorController().listar();

			//Filtramos solo las horasconsumidor que no hayan sido migrados
			List<HorasConsumidor> itemsfiltered =  new ArrayList<HorasConsumidor>();
			for (HorasConsumidor item : lsts_) {
				if (item.getMigrado() == 0){
					itemsfiltered.add(item);
				}
			}

			actual.handler.sendEmptyMessage(0);


		}
	}
	@Override
	public void onBackPressed (){
		Intent itet = new Intent(getApplicationContext(),MenuPrincipalActivity.class);
		startActivity(itet);
	}
}
