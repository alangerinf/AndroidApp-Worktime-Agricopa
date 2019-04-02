package pe.worktime.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.worktime.R;

import java.util.List;

import pe.worktime.app.Application;
import pe.worktime.model.entity.Productividad;

public class ProductividadListaAdapter extends ArrayAdapter<Productividad>{

	private LayoutInflater inflater = null;
	private int template_row = R.layout.list_row_planilla;

	public ProductividadListaAdapter(Context context, int textViewResourceId, List<Productividad> objects, Activity activity) {
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
		View vi=convertView;
        if(convertView==null){
            vi = inflater.inflate(template_row, null);            
        }
        TextView title = (TextView)vi.findViewById(R.id.title);
        TextView artist = (TextView)vi.findViewById(R.id.artist);
        TextView info = (TextView)vi.findViewById(R.id.txtLocation);
        ImageView img = (ImageView)vi.findViewById(R.id.list_image_2);
		Productividad obj = getItem(position);
        //title.setText("Grupo: "+ obj.getCodGrupo());
        //title.setText("Grupo: "+ Application.context.getGrupoController().getNameOrDefault(obj.getCodGrupo()));
		title.setText("Fundo: " + Application.context.getFundoController().getNameOrDefault(obj.getCodFundo()) + "/ Cul: "+Application.context.getCultivoController().getNameOrDefault(obj.getIdCultivo()));
        //artist.setText("Act: "+ obj.getCodActividad() + " - " + Application.context.getActividadController().getNameOrDefault(obj.getCodActividad()));
		artist.setText("Act: "+ Application.context.getActividadController().getNameOrDefault(obj.getCodActvidad()));
        //info.setText("Turn: " + obj.getCodTurnoDia() + "  -  Fecha: " + obj.getFecha().substring(0,10));
		info.setText("Fecha: " + obj.getFecha().substring(0,10));
        //info.setText("F: " + Application.context.getFundoController().getSelected().getFundo() + "/" + "M: " + Application.context.getFundoController().getModuloSelected().getModulo()); 
        if(img!=null){
//        	if(obj.isPlanillaCerrada()){
        		//img.setImageResource(R.drawable.ico_p_roduct2);
//        		title.setTextColor(0xffff0000);
//        		artist.setTextColor(0xffff0000);
//        		info.setTextColor(0xffff0000);
//        	}else{
        		title.setTextColor(0xff040404);
        		artist.setTextColor(0xff343434);
        		info.setTextColor(0xff343434);
//        	}
        	//img.setImageResource(prod.getIdIconInicio());
        }
        return vi;
	}
}
