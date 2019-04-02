package pe.worktime;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.model.entity.HorasConsumidorDetalle;
import pe.worktime.util.AbstractActivity;
import pe.worktime.view.CustomAlertDialogBuilder;
import pe.worktime.view.adapter.DHorasConsumidorAdapter;

public class ManualAsistenciaActivity extends AbstractActivity {
	
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */

	public static boolean isLoadProduct = false;
	public static List<HorasConsumidorDetalle> items = null;
	private static AbstractActivity that;
	private static ListView list;
	private DHorasConsumidorAdapter adap = null;
	private static ManualAsistenciaActivity actual;

	protected void postLoadView() {
		actual = this;
		setContentView(R.layout.manual_asistencia);
		loadTitle();
		items = Application.context.getHorasConsumidorController().getSelected().getDetalle();
		list = (ListView) findViewById(R.id.list);
		View header = getHeader();
		if (header != null) {
			list.addHeaderView(header, null, false);
			//loadTitle(header);
		}
		adap = new DHorasConsumidorAdapter(this, android.R.layout.simple_list_item_1,items, this);
		adap.setTemplate_row(R.layout.list_row_entrada);
		list.setAdapter(adap);
		list.setClickable(true);
		that = this;
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@SuppressLint("InflateParams") @Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {		
				LayoutInflater factory = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				final View textEntryView = factory.inflate(R.layout.add_product, null);
				final CustomAlertDialogBuilder alert = new CustomAlertDialogBuilder(that);
				alert.setTitle("Eliminar Trabajador");
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
										int idx = position -1;
										@SuppressWarnings("unused")
										HorasConsumidorDetalle item = Application.context.getHorasConsumidorController().getSelected().getDetalle().get(idx);
										Application.context.getHorasConsumidorController().getSelected().getDetalle().remove(idx);
										adap.notifyDataSetChanged();
										reloadProducts();
										ResumenAsistenciaActivity.reloadProducts();
										loginPrompt.dismiss();										
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

	@SuppressLint("InflateParams") private View getHeader() {
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
		return R.layout.manual_asistencia;
	}

	@Override
	protected String getTextTitle() {
		return "Asistencia Manual";
	}

	@Override
	protected boolean showTitle() {
		return true;
	}
	
	@SuppressLint("HandlerLeak") public Handler handler = new Handler() {
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
			
			List<HorasConsumidorDetalle> lsts_ = Application.context.getHorasConsumidorController().getSelected().getDetalle();
			
			for (HorasConsumidorDetalle item : lsts_) {
				Log.d("INFO", item.getCodTrabajador());
			}
			
			actual.handler.sendEmptyMessage(0);
		}
	}
}
