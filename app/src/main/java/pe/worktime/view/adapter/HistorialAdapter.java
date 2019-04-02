package pe.worktime.view.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
//import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import R.color;
//import R.id;
//import R.layout;
import com.worktime.R;

import pe.worktime.model.entity.HorasConsumidorDetalle;

public class HistorialAdapter extends ArrayAdapter<HorasConsumidorDetalle>{

	private LayoutInflater inflater = null;	
	private int template_row = R.layout.list_row_entrada;
	
	public HistorialAdapter(Context context, int textViewResourceId,List<HorasConsumidorDetalle> objects, Activity activity) {
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
      //  TextView info = (TextView)vi.findViewById(R.id.txtLocation); 
        ImageView img = (ImageView)vi.findViewById(R.id.list_image_2);
        HorasConsumidorDetalle prod = getItem(position);        
        title.setText("DNI: "+prod.getCodTrabajador());
        img.setImageResource(R.drawable.ico_chk);
        if(!prod.getHoraFin().equalsIgnoreCase("")){
        	img.setImageResource(R.drawable.ico_unchk);
        //	artist.setText("Turno / CI: " + prod.getCodTurno());
        	artist.setText("Inicio: "+prod.getHoraInicio() + "  Fin: "+prod.getHoraFin());
        	//artist.setTextColor(Color.parseColor("#AARRGGBB"));
        	
        	//artist.setTextColor(Color.parseColor("#FFFF0000"));
        }
//        else if(prod.getHoraInicio().equalsIgnoreCase("")){
//            artist.setText("Inicio: "+prod.getHoraInicioMovil());            
//            artist.setTextColor(Color.parseColor("#FF0000FF"));
//        }else{
//            artist.setText("Inicio: "+prod.getHoraInicio());   
//            artist.setTextColor(Color.parseColor("#FF0000FF"));
//        }        	    
        if(img!=null){
        	//img.setImageResource(prod.getIdIconInicio());
        }
        return vi;
	}
}