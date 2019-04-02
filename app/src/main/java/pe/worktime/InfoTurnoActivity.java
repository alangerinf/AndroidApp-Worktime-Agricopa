package pe.worktime;

import android.widget.TextView;

import com.worktime.R;

import pe.worktime.util.AbstractActivity;

public class InfoTurnoActivity extends AbstractActivity {

	public static int viewLoad = R.layout.info_turno;
	protected TextView lblFundoName = null;
	protected TextView lblModulo = null;
	protected TextView lblTurno = null;
	
	@Override
	protected void postLoadView() {
		
		lblFundoName = (TextView) findViewById(R.id.txtfundoturno);
		//lblFundoName.setText("Fundo: "+ ContextTest.context.getSelectedFundo().getFundo());
		lblModulo = (TextView) findViewById(R.id.txtmodturno);
		//lblModulo.setText("Modulo: "+ ContextTest.context.getSelectedModulo().getModulo());
		lblTurno = (TextView) findViewById(R.id.txtturno);
		//lblTurno.setText("Turno: "+ ContextTest.context.getSelectedTurno().getCod_turno());
	}
	
	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Info Turno";
	}	
}
