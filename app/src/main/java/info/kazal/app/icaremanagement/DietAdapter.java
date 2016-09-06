package info.kazal.app.icaremanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mrFarhad on 10/03/15.
 */
public class DietAdapter extends ArrayAdapter<Diet> {

    public DietAdapter(Context context, List<Diet> objects) {
        super(context, 0 , objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_diet_list, null);

            holder = new ViewHolder();

            holder.txtListDay = (TextView)convertView.findViewById(R.id.txtListDay);
            holder.txtListBreakfast = (TextView)convertView.findViewById(R.id.txtListBreakfast);
            holder.txtListLunch = (TextView)convertView.findViewById(R.id.txtListLunch);
            holder.txtListDinner = (TextView)convertView.findViewById(R.id.txtListDinner);

            convertView.setTag(holder);

        }else {

            holder = (ViewHolder) convertView.getTag();
        }

        Diet d = getItem(position);
        holder.txtListDay.setText(d.getDay());

        if (d.getBreakfast() != null){

            holder.txtListBreakfast.setText("Breakfast.");
        }else {
            holder.txtListBreakfast.setText("Breakfast not set.");
        }

        if (d.getLunch() != null){

            holder.txtListLunch.setText("Lunch.");
        }else {
            holder.txtListLunch.setText("Lunch not set.");
        }

        if (d.getDinner() != null){

            holder.txtListDinner.setText("Dinner.");
        }else {
            holder.txtListDinner.setText("Dinner not set.");
        }


        return convertView;
    }


    static class ViewHolder{

        public TextView txtListDay;
        public TextView txtListBreakfast;
        public TextView txtListLunch;
        public TextView txtListDinner;

    }
}
