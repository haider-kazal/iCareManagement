package info.kazal.app.icaremanagement;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class NoteList extends ActionBarActivity {

    ListView lvNote;
    TextView txtNoteAvailable;
    DbHelperNoteCall dbHelperNoteCall;
    private ArrayList<Note> allNote;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        dbHelperNoteCall = new DbHelperNoteCall(this);
        allNote = dbHelperNoteCall.getAllNote();

        lvNote = (ListView) findViewById(R.id.lvNote);
        txtNoteAvailable = (TextView) findViewById(R.id.txtNoteAvailable);

        if (allNote.size() == 0) {

            txtNoteAvailable.setVisibility(View.VISIBLE);
            lvNote.setVisibility(View.GONE);
        } else {

            adapter = new NoteAdapter(this, allNote);
            lvNote.setAdapter(adapter);
        }
        lvNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Note singleNote = allNote.get(position);
                int note_id = singleNote.getId_note();

                Intent i = new Intent(NoteList.this, NoteView.class);
                i.putExtra("note_id", note_id);
                startActivity(i);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        registerForContextMenu(lvNote);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_note_list, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){

            case R.id.action_note_edit:

                Note aNote = allNote.get(info.position);
                int note_id = aNote.getId_note();

                Intent intent = new Intent(NoteList.this, NoteUpdate.class);
                intent.putExtra("note_id", note_id);
                startActivity(intent);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();

                break;
            case R.id.action_note_delete:

                Note myNote = allNote.get(info.position);
                int id = myNote.getId_note();
                int deleted = dbHelperNoteCall.deleteNote(id);

                if (deleted > 0){

                    Toast.makeText(getApplicationContext(), "Delete success!", Toast.LENGTH_SHORT).show();
                    adapter.remove(myNote);
                    adapter.notifyDataSetChanged();

                }else {

                    Toast.makeText(getApplicationContext(), "Delete not success!", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_addNote) {

            Intent i = new Intent(NoteList.this, NoteEditor.class);
            startActivity(i);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(NoteList.this, MainActivity.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
