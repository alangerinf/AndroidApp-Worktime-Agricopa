package pe.worktime;

import android.widget.TextView;

import com.worktime.R;

import pe.worktime.app.Application;
import pe.worktime.util.AbstractActivity;

public class InfoModuloActivity extends AbstractActivity {

	public static int viewLoad = R.layout.info_modulo;
	protected TextView lblFundoName = null;
	protected TextView lblCodModulo = null;
	protected TextView lblNombreModulo = null;

	// protected TextView lblNroTurnos = null;
	// protected ListView listViewMod = null;
	// private List<Turno> lst;
	// int size = 0;

	@Override
	protected void postLoadView() {

		lblFundoName = (TextView) findViewById(R.id.txtFundo);
		// lblFundoName.setText("Fundo: "+
		// ContextTest.ctx.context.getSelected().getFundo());
		lblFundoName.setText("Fundo: "
				+ Application.context.getFundoController().getSelected()
						.getFundo());
		lblCodModulo = (TextView) findViewById(R.id.lblCodMod);
		// lblCodModulo.setText("Cod Modulo: "+
		// ContextTest.ctx.context.getSelectedModulo().getCodModulo());
		lblCodModulo.setText("Cod Modulo: "
				+ Application.context.getFundoController().getModuloSelected()
						.getCodModulo());
		lblNombreModulo = (TextView) findViewById(R.id.lblModInfo);
		// lblNombreModulo.setText("Modulo: "+
		// ContextTest.ctx.context.getSelectedModulo().getModulo());
		lblNombreModulo.setText("Modulo: "
				+ Application.context.getFundoController().getModuloSelected()
						.getModulo());
	}

	@Override
	protected int getViewId() {
		return viewLoad;
	}

	@Override
	protected String getTextTitle() {
		return "Info Modulo";
	}
}