package info.kazal.app.icaremanagement;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EmergencyNumberEdit extends ActionBarActivity {

    private EditText etEmergencyNameUpdate, etEmergencyNumberUpdate;
    private Button btnUpdateNumber;
    private DbHelperNoteCall dbHelperNoteCall;
    private EmergencyCall eCall;
    private Intent intent;
    private int id_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_number_edit);

        dbHelperNoteCall = new DbHelperNoteCall(this);
        intent = getIntent();
        id_call = intent.getIntExtra("id_call", -1);
        eCall = dbHelperNoteCall.getAEmergencyNumber(id_call);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        initialize();

        etEmergencyNameUpdate.setText(eCall.getNameNumberHolder());
        etEmergencyNumberUpdate.setText(eCall.getEmergencyNumber());

        btnUpdateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!etEmergencyNameUpdate.getText().equals("") && !etEmergencyNumberUpdate.getText().equals("")){

                    String name = etEmergencyNameUpdate.getText().toString();
                    String number = etEmergencyNumberUpdate.getText().toString();

                    EmergencyCall em = new EmergencyCall(name, number);
                    int updated = dbHelperNoteCall.updateEmergencyCall(em, id_call);

                    if (updated > 0){

                        Toast.makeText(getApplicationContext(), "Update success!", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(EmergencyNumberEdit.this, EmergencyCallView.class);
                        startActivity(in);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "Update not success!", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    Toast.makeText(getApplicationContext(), "Name and number can't be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initialize(){

        etEmergencyNameUpdate = (EditText)findViewById(R.id.etEmergencyNameUpdate);
        etEmergencyNumberUpdate = (EditText)findViewById(R.id.etEmergencyNumberUpdate);
        btnUpdateNumber = (Button)findViewById(R.id.btnUpdateNumber);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_emergency_number_edit, menu);
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

        Intent i = new Intent(EmergencyNumberEdit.this, EmergencyCallView.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
