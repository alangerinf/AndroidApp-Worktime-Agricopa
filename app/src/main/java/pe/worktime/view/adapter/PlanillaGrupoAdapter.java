package pe.worktime.view.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.ContextTest;
import pe.worktime.app.Application;
import pe.worktime.model.entity.HorasConsumidor;

public class PlanillaGrupoAdapter extends ArrayAdapter<HorasConsumidor>{
	
	private LayoutInflater inflater = null;	
	private int template_row = R.layout.list_row_planilla;
	
	public PlanillaGrupoAdapter(Context context, int textViewResourceId,List<HorasConsumidor> objects, Activity activity) {
		super(context, textViewResourceId, objects);		
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
	}

	public int getTemplate_row() {
		return template_row;
	}

	public void setTemplate_row(int template_row) {
		this.template_row = template_row;
	}

	@Override
	public int getCount() {
		return super.getCount();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {		

		String nombrececoTurno = "";

		View vi=convertView;
        if(convertView==null){
            vi = inflater.inflate(template_row, null);            
        }
        TextView title = (TextView)vi.findViewById(R.id.title);
        TextView artist = (TextView)vi.findViewById(R.id.artist);
        TextView info = (TextView)vi.findViewById(R.id.txtLocation);
		TextView ceco = (TextView)vi.findViewById(R.id.txtceco);
        ImageView img = (ImageView)vi.findViewById(R.id.list_image_2);
        HorasConsumidor obj = getItem(position);

		nombrececoTurno = obj.getNombreCecoOModulo();


//        if(obj.getTipoActividad().equalsIgnoreCase("D")) {
//			cecoTurno = "Parcela :"+nombrececoTurno;
//		}else if(obj.getTipoActividad().equalsIgnoreCase("I")){
//			cecoTurno = "CentroCosto: "+nombrececoTurno;
//        }


		//artist.setText("Act: "+ obj.getCodActividad() + " - " + Application.context.getActividadController().getNameOrDefault(obj.getCodActividad()));
		//info.setText("Turn: " + obj.getCodTurnoDia() + "  -  Fecha: " + obj.getFecha().substring(0,10));
		//info.setText("F: " + Application.context.getFundoController().getSelected().getFundo() + "/" + "M: " + Application.context.getFundoController().getModuloSelected().getModulo());
		//title.setText( "Cultivo: "+Application.context.getCultivoController().getNameOrDefault(obj.getIdCultivo()));


		title.setText(Application.context.getFundoController().getNameOrDefault(obj.getCodFundo())+ " / "+Application.context.getCultivoController().getNameOrDefault(obj.getIdCultivo()));
		artist.setText("Actividad: "+ Application.context.getActividadController().getNameOrDefault(obj.getCodActividad()));
		ceco.setText("Centro Costo: "+nombrececoTurno);
		info.setText("Fecha: " + obj.getFecha().substring(0,10));


        if(img!=null){
//        	if(ContextTest.idxTipoEntrada!=3) {
				if (obj.isPlanillaCerrada()) {
					//img.setImageResource(R.drawable.ico_p_roduct2);
					title.setTextColor(0xffff0000);
					artist.setTextColor(0xffff0000);
					ceco.setTextColor(0xffff0000);
					info.setTextColor(0xffff0000);
				} else {
					title.setTextColor(0xff040404);
					artist.setTextColor(0xff343434);
					ceco.setTextColor(0xff343434);
					info.setTextColor(0xff343434);
				}
//			}else if(ContextTest.idxTipoEntrada==3){
//				title.setTextColor(0xff040404);
//				artist.setTextColor(0xff343434);
//				info.setTextColor(0xff343434);
//			}
        	//img.setImageResource(prod.getIdIconInicio());
        }
        return vi;
	}
}
