package pe.worktime;

import java.util.ArrayList;
import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.model.entity.HorasConsumidor;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.util.AbstractActivity;
import pe.worktime.view.adapter.HistorialAdapter;
//import PlanillaGrupoAdapter;

import android.annotation.SuppressLint;

//import android.app.Activity;
import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.worktime.R;

public class VerDetalleHistorialActivity extends AbstractActivity {

	public static boolean isLoadProduct = false;
	public static List<HorasConsumidorDetalle> items = null;
	@SuppressWarnings("unused")
	private static AbstractActivity that;
	private static ListView list;
	private HistorialAdapter adap = null;
	private static VerDetalleHistorialActivity actual;




	protected void postLoadView() {
		
		actual = this;
		setContentView(R.layout.salida_asistencia);
		loadTitle();
		try {
			items = Application.context.getHorasConsumidorController().getSelected().getDetalle();
			if(items==null){
				items = new ArrayList<HorasConsumidorDetalle>();
			}
		} catch (Exception e) {
			items = new ArrayList<HorasConsumidorDetalle>();
		}
		list = (ListView) findViewById(R.id.list);
		View header = getHeader();
		if (header != null) {
			list.addHeaderView(header, null, false);			
		}
		adap = new HistorialAdapter(this, android.R.layout.simple_list_item_1, items, this);
		adap.setTemplate_row(R.layout.list_row_asistentes_ingreso);
		list.setAdapter(adap);
		list.setClickable(false);
		that = this;

		/*
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {
				int idx = position-1;
				
				Application.context.getHorasConsumidorController().seleccionar(idx);
				
				if (ContextTest.idxTipoEntradaSalida == 0) {
					Intent a = new Intent(getApplicationContext(),SalidaTabActivity.class);
					startActivity(a);
				}
				if (ContextTest.idxTipoEntradaSalida == 1) {
					Intent b = new Intent(getApplicationContext(),SalidaTabActivity.class);
					startActivity(b);
				}
			}
		});
		*/
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
		return R.layout.salida_asistencia;
	}

	@Override
	protected String getTextTitle() {
		return "Detalle Planilla";
	}

	@Override
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

		Log.d("INFO", "AKA 1");
		if (actual != null) {
			Log.d("INFO", "AKA 2");
			Log.d("INFO", Application.context.getHorasConsumidorController().getSelected().getFecha());
			Log.d("INFO", ""+ Application.context.getHorasConsumidorController().getSelected().getDetalle().size());
			
			List<HorasConsumidor> lsts_ = Application.context.getHorasConsumidorController().listar();
			
			//Filtramos solo las horasconsumidor que no hayan sido migrados
			List<HorasConsumidor> itemsfiltered =  new ArrayList<HorasConsumidor>();
			for (HorasConsumidor item : lsts_) {
				if (item.getMigrado() == 1){
					itemsfiltered.add(item);
				}
			}
			
			for (HorasConsumidor item : itemsfiltered) {
				Log.d("INFO", item.getCodGrupo());
			}
			actual.handler.sendEmptyMessage(0);
		}
	}
}
