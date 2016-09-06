package info.kazal.app.icaremanagement;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * Created by Mrfarhad on 10/02/15.
 */
public class ListAdapter extends ArrayAdapter<PatientTemplate>{


    public ListAdapter(Context context, List<PatientTemplate> objects) {
        super(context, 0 , objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list,null);

            holder = new ViewHolder();
            holder.imageView = ((ImageView)convertView.findViewById(R.id.imgViewList));
            holder.txtListName = ((TextView)convertView.findViewById(R.id.txtListName));
            holder.txtListType = ((TextView)convertView.findViewById(R.id.txtListType));

            convertView.setTag(holder);

        }else {
            
            holder = (ViewHolder) convertView.getTag();
        }

        PatientTemplate patient = getItem(position);
        if (patient.getPatient_image() != null){

            byte[] outImage = patient.getPatient_image();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            holder.imageView.setImageBitmap(theImage);
        }else {


            holder.imageView.setImageResource(R.drawable.pa);
        }

        holder.txtListName.setText(patient.getName());
        holder.txtListType.setText(patient.getPatientType());

        return convertView;
    }

    static class ViewHolder{

        public ImageView imageView;
        public TextView txtListName;
        public TextView txtListType;
    }
}
