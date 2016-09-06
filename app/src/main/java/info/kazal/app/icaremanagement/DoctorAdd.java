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


public class DoctorAdd extends ActionBarActivity {

    private EditText etDoctorName, etDoctorNumber, etDoctorEmail, etDoctorAddress, etAboutDoctor;
    private Button btnSaveDoctor;

    private Intent intent;
    private int patient_id;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_add);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        databaseHelper = new DatabaseHelper(this);
        intent = getIntent();
        patient_id = intent.getIntExtra("patient_id", -1);
        initialize();

        btnSaveDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etDoctorName.getText().toString();
                String number = etDoctorNumber.getText().toString();
                String email = etDoctorEmail.getText().toString();
                String address = etDoctorAddress.getText().toString();
                String about = etAboutDoctor.getText().toString();

                if (!name.equals("") && !number.equals("") && !address.equals("")){

                    Doctor d = new Doctor(patient_id,name,number,email,address,about);
                    long inserted = databaseHelper.addDoctor(d);

                    if (inserted > 0 ){

                        Intent i = new Intent(DoctorAdd.this, DoctorList.class);
                        startActivity(i);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                    }else {

                        Toast.makeText(getApplicationContext(), "Save not success!", Toast.LENGTH_SHORT).show();

                    }
                }
                else {

                    Toast.makeText(getApplicationContext(), "information not complete!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


    public void initialize(){

        etDoctorName = (EditText)findViewById(R.id.etDoctorName);
        etDoctorNumber = (EditText)findViewById(R.id.etDoctorNumber);
        etDoctorEmail = (EditText)findViewById(R.id.etDoctorEmail);
        etDoctorAddress = (EditText)findViewById(R.id.etDoctorAddress);
        etAboutDoctor = (EditText)findViewById(R.id.etAboutDoctor);

        btnSaveDoctor = (Button)findViewById(R.id.btnSaveDoctor);
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_doctor_add, menu);
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

        Intent i= new Intent(DoctorAdd.this, DoctorList.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
