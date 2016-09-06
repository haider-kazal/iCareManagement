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


public class EmergencyNumberAdd extends ActionBarActivity {

    private EditText etEmergencyName, etEmergencyNumber;
    private Button btnSaveNumber;
    private DbHelperNoteCall dbHelperNoteCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_number_add);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        dbHelperNoteCall = new DbHelperNoteCall(this);

        initialize();

        btnSaveNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etEmergencyName.getText().toString();
                String number = etEmergencyNumber.getText().toString();

                if (!name.equals("") && !number.equals("")){

                    EmergencyCall ec = new EmergencyCall(name, number);
                    long inserted = dbHelperNoteCall.addEmergencyCall(ec);
                    if (inserted == -1){

                        Toast.makeText(getApplicationContext(), "Sorry number can't save!", Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(getApplicationContext(), "Save success!", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(EmergencyNumberAdd.this, EmergencyCallView.class);
                        startActivity(i);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                    }
                }
            }
        });
    }

    public void initialize(){

        etEmergencyName = (EditText)findViewById(R.id.etEmergencyName);
        etEmergencyNumber = (EditText)findViewById(R.id.etEmergencyNumber);
        btnSaveNumber = (Button)findViewById(R.id.btnSaveNumber);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_emergency_number_add, menu);
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

        Intent i = new Intent(EmergencyNumberAdd.this, EmergencyCallView.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
