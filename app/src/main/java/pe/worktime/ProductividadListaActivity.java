package pe.worktime;

import android.annotation.SuppressLint;
import android.content.Context;
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
import pe.worktime.model.entity.Productividad;
import pe.worktime.util.AbstractActivity;
import pe.worktime.view.adapter.PlanillaGrupoAdapter;
import pe.worktime.view.adapter.ProductividadListaAdapter;

public class ProductividadListaActivity extends AbstractActivity {

	public static boolean isLoadProduct = false;
	public static List<Productividad> items = null;
	@SuppressWarnings("unused")
	private static AbstractActivity that;
	private static ListView list;
	private ProductividadListaAdapter adap = null;
	private static ProductividadListaActivity actual;

	protected void postLoadView() {

		actual = this;
		setContentView(R.layout.planilla_asistencia);
		loadTitle();
		try {
			items = Application.context.getProductividadController().listar();
			if (items == null) {
				items = new ArrayList<Productividad>();
			}
		} catch (Exception e) {
			items = new ArrayList<Productividad>();
		}
		list = (ListView) findViewById(R.id.list);
		View header = getHeader();
		if (header != null) {
			list.addHeaderView(header, null, false);
		}
		
		//Filtramos solo las productividades que no hayan sido migrados
		List<Productividad> itemsfiltered =  new ArrayList<Productividad>();
		for (Productividad item : items) {
			if (item.getMigrado() == 0){
				itemsfiltered.add(item);
			}
		}
		
		//adap = new PlanillaGrupoAdapter(this,android.R.layout.simple_list_item_1, items, this);
		adap = new ProductividadListaAdapter(this,android.R.layout.simple_list_item_1, itemsfiltered, this);
		adap.setTemplate_row(R.layout.list_row_planilla);
		list.setAdapter(adap);
		list.setClickable(true);
		that = this;
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {
				
				try{
				
					int idx = position - 1;
					Application.context.getProductividadController().seleccionar(idx);


					Intent ites = new Intent(getApplicationContext(),ProductividadTabActivity.class);
					startActivity(ites);
				
				}catch(Exception e){
					showSimpleMesage(e.getMessage());
				}
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
		return "Planilla";
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
//			Log.d("INFO", "AKA 2");
//			Log.d("INFO", Application.context.getHorasConsumidorController().getSelected().getFecha());
//			Log.d("INFO", "" + Application.context.getHorasConsumidorController().getSelected().getDetalle().size());
			List<Productividad> lsts_ = Application.context.getProductividadController().listar();
			
			//Filtramos solo las horasconsumidor que no hayan sido migrados
			List<Productividad> itemsfiltered =  new ArrayList<Productividad>();
			for (Productividad item : lsts_) {
				//if (item.getMigrado() == 0){
					itemsfiltered.add(item);
				//}
			}
//			for (Productividad item : itemsfiltered) {
//				Log.d("INFO", item.getCodGrupo());
//			}
			actual.handler.sendEmptyMessage(0);
		}
	}
}
