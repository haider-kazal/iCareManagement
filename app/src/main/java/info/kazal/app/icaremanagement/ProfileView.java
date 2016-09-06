package info.kazal.app.icaremanagement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;


public class ProfileView extends ActionBarActivity {

    TextView tvPatientName,tvGender,tvBloodGroup,tvCurrentDate,tvAge,tvHeight,tvWeight,tvPhone,tvEmail,tvPatientCondition;
    Button btnOk;
    ImageView imgPatientView;
    private Intent intent;
    private int patient_id;
    private DatabaseHelper databaseHelper;
    private PatientTemplate patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);
        initialize();

        databaseHelper = new DatabaseHelper(this);
        intent = getIntent();
        patient_id = intent.getIntExtra("patient_id", 0);
        patient = databaseHelper.getAPatient(patient_id);

        tvPatientName.setText(patient.getName());
        tvGender.setText(patient.getGender());
        tvBloodGroup.setText(patient.getBloodGroup());
        tvCurrentDate.setText(patient.getCurrentDate());
        tvAge.setText(String.valueOf(patient.getAge()));
        tvHeight.setText(String.valueOf(patient.getHeight()));
        tvWeight.setText(String.valueOf(patient.getWeight()));
        tvPhone.setText(patient.getPhoneNumber());
        tvEmail.setText(patient.getEmail());
        tvPatientCondition.setText(patient.getPatientCondition());

        if (patient.getPatient_image() != null){

            byte[] outImage = patient.getPatient_image();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            imgPatientView.setImageBitmap(theImage);
        }else {


            imgPatientView.setImageResource(R.drawable.pa);
        }


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i= new Intent(ProfileView.this, AllService.class);
                startActivity(i);

                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

    }

    private void initialize() {

        tvPatientName = (TextView) findViewById(R.id.tvPatientName);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvBloodGroup = (TextView) findViewById(R.id.tvBloodGroup);
        tvCurrentDate = (TextView) findViewById(R.id.tvCurrentDate);
        tvAge = (TextView) findViewById(R.id.tvAge);
        tvHeight = (TextView) findViewById(R.id.tvHeight);
        tvWeight = (TextView) findViewById(R.id.tvWeight);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPatientCondition = (TextView) findViewById(R.id.tvPatientCondition);
        imgPatientView = (ImageView)findViewById(R.id.imgPatientView);
        btnOk = (Button) findViewById(R.id.btnOk);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_profile_view, menu);
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

        Intent i= new Intent(ProfileView.this, AllService.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }

}
