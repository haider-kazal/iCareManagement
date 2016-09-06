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


public class NoteEditor extends ActionBarActivity {

    EditText etNoteTitle, etNoteBody;
    TextView txtNoteDate;
    Button btnNoteSave;
    DbHelperNoteCall dbHelperNoteCall;

    private String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        dbHelperNoteCall = new DbHelperNoteCall(this);

        etNoteTitle = (EditText)findViewById(R.id.etNoteTitle);
        etNoteBody = (EditText)findViewById(R.id.etNoteBody);
        txtNoteDate = (TextView)findViewById(R.id.txtNoteDate);
        btnNoteSave = (Button)findViewById(R.id.btnNoteSave);

        txtNoteDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });


        btnNoteSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etNoteTitle.getText().toString();
                String body = etNoteBody.getText().toString();
                String date = txtNoteDate.getText().toString();

                if (!title.equals("") && !body.equals("") && !date.equals("")){

                    Note myNote = new Note(title,body, date);
                    long inserted = dbHelperNoteCall.addNote(myNote);

                    if (inserted == -1){

                        String text="Sorry";
                        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();

                    }else {

                        String text="Save success!";
                        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(NoteEditor.this, NoteList.class);
                        startActivity(i);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                    }

                }else {

                    Toast.makeText(getApplicationContext(), "Please complete information! ", Toast.LENGTH_LONG).show();
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

            if (txtNoteDate.getText().toString() != null){

                txtNoteDate.setText(" ");
                txtNoteDate.setText(dayOfMonth+" - "+months[monthOfYear]+" - "+year);

            }else {

                txtNoteDate.setText(dayOfMonth+" - "+months[monthOfYear]+" - "+year);
            }

        }
    };



    @Override
    public void onBackPressed() {

        Intent i = new Intent(NoteEditor.this, NoteList.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
