package info.kazal.app.icaremanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class NoteUpdate extends ActionBarActivity {

    EditText etNoteUpdateTitle, etNoteUpdateBody;
    TextView txtNoteUpdateDate;
    Button btnNoteUpdate;
    private Intent intent;
    private int note_id;
    private Note note;
    private String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    private DbHelperNoteCall dbHelperNoteCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_update);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        dbHelperNoteCall = new DbHelperNoteCall(this);

        intent = getIntent();
        note_id = intent.getIntExtra("note_id", 0);
        note = dbHelperNoteCall.getANote(note_id);


        etNoteUpdateTitle = (EditText) findViewById(R.id.etNoteUpdateTitle);
        etNoteUpdateBody = (EditText) findViewById(R.id.etNoteUpdateBody);
        txtNoteUpdateDate = (TextView) findViewById(R.id.txtNoteUpdateDate);

        btnNoteUpdate = (Button) findViewById(R.id.btnNoteUpdate);

        etNoteUpdateTitle.setText(note.getNote_title());
        etNoteUpdateBody.setText(note.getNote_body());
        txtNoteUpdateDate.setText(note.getModify_date());

        txtNoteUpdateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });

        btnNoteUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etNoteUpdateTitle.getText().toString();
                String body = etNoteUpdateBody.getText().toString();
                String date = txtNoteUpdateDate.getText().toString();

                Note n = new Note(title,body,date);
                int updated = dbHelperNoteCall.updateNote(n, note_id);

                if (updated > 0){

                    Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(NoteUpdate.this, NoteList.class);
                    startActivity(i);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finish();

                }else {

                    Toast.makeText(getApplicationContext(),"Sorry! Update not success",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    protected Dialog onCreateDialog(int id) {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        switch (id){

            case 1:

                return new DatePickerDialog(this,datePickerListener, year, month, day);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

            if (txtNoteUpdateDate.getText().toString() != null){

                txtNoteUpdateDate.setText(" ");
                txtNoteUpdateDate.setText(dayOfMonth+" - "+months[monthOfYear]+" - "+year);

            }else {

                txtNoteUpdateDate.setText(dayOfMonth+" - "+months[monthOfYear]+" - "+year);
            }

        }
    };




    @Override
    public void onBackPressed() {

        Intent i = new Intent(NoteUpdate.this, NoteList.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
