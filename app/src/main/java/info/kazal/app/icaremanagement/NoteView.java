package info.kazal.app.icaremanagement;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;


public class NoteView extends ActionBarActivity {

    TextView txtNoteViewTitle, txtNoteViewDate, txtNoteView;
    private Intent intent;
    private int note_id;
    private Note note;
    private DbHelperNoteCall dbHelperNoteCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_view);

        dbHelperNoteCall = new DbHelperNoteCall(this);

        intent = getIntent();
        note_id = intent.getIntExtra("note_id", 0);
        note = dbHelperNoteCall.getANote(note_id);

        txtNoteView = (TextView) findViewById(R.id.txtNoteView);
        txtNoteViewTitle = (TextView) findViewById(R.id.txtNoteViewTitle);
        txtNoteViewDate = (TextView) findViewById(R.id.txtNoteViewDate);

        txtNoteViewTitle.setText(note.getNote_title());
        txtNoteViewDate.setText(note.getModify_date());
        txtNoteView.setText(note.getNote_body());
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_note_view, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {

        Intent i= new Intent(NoteView.this, NoteList.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
