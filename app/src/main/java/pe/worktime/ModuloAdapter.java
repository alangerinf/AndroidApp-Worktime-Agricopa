package pe.worktime;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.worktime.R;

import pe.worktime.model.entity.Modulo;

public class ModuloAdapter extends ArrayAdapter<Modulo>{
	
	
	private LayoutInflater inflater = null;	
	private int template_row = R.layout.list_row_fundo;
	
	public ModuloAdapter(Context context, int textViewResourceId,List<Modulo> objects, Activity activity) {
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
        Modulo prod = getItem(position);        
        title.setText(prod.getModulo());
        artist.setText("");
        return vi;
	}
	
}
