package info.kazal.app.icaremanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mrfarhad on 10/02/15.
 */
public class NoteAdapter extends ArrayAdapter<Note> {


    public NoteAdapter(Context context, List<Note> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_note_view,null);

            holder = new ViewHolder();
            holder.txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
            holder.txtDate = (TextView)convertView.findViewById(R.id.txtDate);

            convertView.setTag(holder);
        }else {

            holder = (ViewHolder) convertView.getTag();
        }

        Note currentNote = getItem(position);

        holder.txtTitle.setText(currentNote.getNote_title());
        holder.txtDate.setText(currentNote.getModify_date());

        return convertView;
    }

    static class ViewHolder{

        public TextView txtTitle;
        public TextView txtDate;
    }
}
