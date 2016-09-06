package info.kazal.app.icaremanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;


public class UpdateProfile extends ActionBarActivity {

    EditText etPatientName, etAge,etHeight,etWeight,etPhone,etEmail,etPatientCondition;
    ImageView imageView;
    TextView tvUpdateDate;
    Button btnSave;

    private Intent intent;
    private DatabaseHelper databaseHelper;
    private int patient_id;
    private int age;
    private double height;
    private double weight;
    private String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    private PatientTemplate patient;
    byte[] finalImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        databaseHelper = new DatabaseHelper(this);
        intent = getIntent();
        patient_id = intent.getIntExtra("patient_id", -1);
        patient = databaseHelper.getAPatient(patient_id);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        etPatientName = (EditText)findViewById(R.id.etPatientName);
        tvUpdateDate = (TextView) findViewById(R.id.tvCurrentDate);
        etAge= (EditText) findViewById(R.id.etAge);
        etHeight= (EditText) findViewById(R.id.etHeight);
        etWeight= (EditText) findViewById(R.id.etWeight);
        etPhone= (EditText) findViewById(R.id.etPhone);
        etEmail= (EditText) findViewById(R.id.etEmail);
        etPatientCondition= (EditText) findViewById(R.id.etPatientCondition);
        imageView= (ImageView) findViewById(R.id.imageView);
        btnSave = (Button)findViewById(R.id.btnUpdate);

        tvUpdateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Choose from Gallery"), 1);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etPatientName.getText().toString();
                String patientType = patient.getPatientType();
                String gender = patient.getGender();
                String bloodGroup = patient.getBloodGroup();
                String currentDate = tvUpdateDate.getText().toString();

                if (etAge.getText().toString() == null){

                    Toast.makeText(getApplicationContext(), "Age can't be empty.", Toast.LENGTH_SHORT).show();
                }else {

                    age = Integer.parseInt(etAge.getText().toString());
                }

                if (etHeight.getText().toString() == null){

                    Toast.makeText(getApplicationContext(), "Height can't be empty.", Toast.LENGTH_SHORT).show();
                }else {

                    height = Double.parseDouble(etHeight.getText().toString());
                }

                if (etWeight.getText().toString() == null){

                    Toast.makeText(getApplicationContext(), "Weight can't be empty.", Toast.LENGTH_SHORT).show();
                }else {

                    weight = Double.parseDouble(etWeight.getText().toString());
                }
                String phone = etPhone.getText().toString();
                String email = etEmail.getText().toString();
                String condition = etPatientCondition.getText().toString();

                if (!etPatientName.equals("") && !etPatientCondition.equals("")){

                    PatientTemplate p = new PatientTemplate(name,patientType,gender,bloodGroup,currentDate,age,height,weight,phone,email,condition,finalImage);
                    int updated = databaseHelper.updatePatient(p,patient_id);

                    if (updated > 0){

                        Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(UpdateProfile.this, MainActivity.class);
                        startActivity(i);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();

                    }else {

                        Toast.makeText(getApplicationContext(),"Sorry! Update not success",Toast.LENGTH_LONG).show();
                    }
                }else {

                    Toast.makeText(getApplicationContext(),"Please all complete information",Toast.LENGTH_LONG).show();

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

            if (tvUpdateDate.getText().toString() != null){
                tvUpdateDate.setText(" ");
                tvUpdateDate.setText(dayOfMonth+" - "+months[monthOfYear]+" - "+year);
            }else {

                tvUpdateDate.setText(dayOfMonth+" - "+months[monthOfYear]+" - "+year);
            }

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                imageView.setImageURI(data.getData());
                BitmapDrawable d = (BitmapDrawable) imageView.getDrawable();
                Bitmap image = d.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 10, stream);
                finalImage = stream.toByteArray();

            }
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_update_profile, menu);
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

        Intent i = new Intent(UpdateProfile.this, MainActivity.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }

}
