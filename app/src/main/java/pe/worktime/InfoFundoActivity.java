package pe.worktime;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.model.entity.Modulo;
import pe.worktime.util.AbstractActivity;

public class InfoFundoActivity extends AbstractActivity {
		
	public static int viewLoad = R.layout.info_fundo;
	protected TextView lblFundoName = null;
	protected TextView lblNroMod = null;
	protected ListView listViewMod = null;
	private List<Modulo> lst;
	int size = 0;
	
	private ModuloAdapter adap ;

	@Override
	protected void postLoadView() {
		
		//lst = ContextTest.ctx.context.getSelected().getModulos();
		lst = Application.context.getFundoController().getSelected().getModulos();
		size = lst.size();
		
		listViewMod = (ListView) findViewById(R.id.list);
		View header = getHeader();
		if (header != null) {
			listViewMod.addHeaderView(header, null, false);
			lblFundoName = (TextView) header.findViewById(R.id.lblNombreFundo);
			//lblFundoName.setText("Fundo: " + ContextTest.ctx.context.getSelected().getFundo());
			lblFundoName.setText("Fundo: " + Application.context.getFundoController().getSelected().getFundo());
			lblNroMod = (TextView) header.findViewById(R.id.lblnroModulos);
			lblNroMod.setText("Nro Modulos: " + size);
			
		}
		
		adap = new ModuloAdapter(this,
				android.R.layout.simple_list_item_1, lst, this);
		adap.setTemplate_row(R.layout.list_row_fundo);
		listViewMod.setAdapter(adap);
		listViewMod.setClickable(true);
		
	
		listViewMod.setOnItemClickListener(new OnItemClickListener() {
       	@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub	
			}
          });
    }
	
	
	private View getHeader() {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View header = inflater.inflate(R.layout.info_fundo_header,
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
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Info Fundos";
	}	
}
