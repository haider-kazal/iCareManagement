package info.kazal.app.icaremanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mrFarhad on 10/01/15.
 */
public class CallAdapter extends ArrayAdapter<EmergencyCall> {


    public CallAdapter(Context context, List<EmergencyCall> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_call, null);

            holder = new ViewHolder();

            holder.txtName = (TextView)convertView.findViewById(R.id.txtName);
            holder.txtNumber = (TextView)convertView.findViewById(R.id.txtNumber);

            convertView.setTag(holder);

        }else {

            holder = (ViewHolder) convertView.getTag();
        }

        EmergencyCall em = getItem(position);

        holder.txtName.setText(em.getNameNumberHolder());
        holder.txtNumber.setText(em.getEmergencyNumber());

        return convertView;
    }

    static class ViewHolder{

        public TextView txtName;
        public TextView txtNumber;

    }
}
