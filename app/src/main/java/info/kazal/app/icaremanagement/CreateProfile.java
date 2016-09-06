package info.kazal.app.icaremanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;


public class CreateProfile extends ActionBarActivity {


    EditText etPatientName, etAge,etHeight,etWeight,etPhone,etEmail,etPatientCondition;
    ImageView imageView;
    TextView tvCurrentDate;
    Spinner spnType,spnBlood;
    Button btnSave;
    ArrayAdapter<String> adapterProfileType,adapterBlood;
    DatabaseHelper databaseHelper;
    byte[] fianalImage;

    private String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    private String[] typeList={"My Own","Father","Mother","Brother","Sister","Grand Father","Grand Mother","Children","Other"};
    private  String[] typeBlood = {"A+","A-","B+","B-","AB+","AB-","O+","O-"};
    private  String bloodType;
    private String profileType;
    private String gender;
    private int age;
    private  double height;
    private double weight;
    private String name;
    private String currentDate;
    private String phone;
    private String email;
    private String patientCondition;
    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));


        // setInitialize();
        etPatientName = (EditText)findViewById(R.id.etPatientName);
        tvCurrentDate= (TextView) findViewById(R.id.tvCurrentDate);
        etAge= (EditText) findViewById(R.id.etAge);
        etHeight= (EditText) findViewById(R.id.etHeight);
        etWeight= (EditText) findViewById(R.id.etWeight);
        etPhone= (EditText) findViewById(R.id.etPhone);
        etEmail= (EditText) findViewById(R.id.etEmail);
        etPatientCondition= (EditText) findViewById(R.id.etPatientCondition);
        imageView= (ImageView) findViewById(R.id.imageView);
        spnType= (Spinner) findViewById(R.id.spnType);
        spnBlood= (Spinner) findViewById(R.id.spnBlood);
        btnSave = (Button)findViewById(R.id.btnSave);


        databaseHelper = new DatabaseHelper(this);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Choose from Gallery"), 1);
            }
        });


        adapterProfileType = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_spinner,R.id.txtSpinner,typeList);
        spnType.setAdapter(adapterProfileType);
        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {

                profileType = typeList[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapterBlood = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_spinner,R.id.txtSpinner,typeBlood);
        spnBlood.setAdapter(adapterBlood);
        spnBlood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {

                bloodType = typeBlood[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tvCurrentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = etPatientName.getText().toString();
                currentDate = tvCurrentDate.getText().toString();
                if (etAge.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please input Height!", Toast.LENGTH_LONG).show();
                }else {
                    age = Integer.parseInt(etAge.getText().toString());
                }

                if (etHeight.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please input Height!", Toast.LENGTH_LONG).show();
                }else {
                    height = Double.parseDouble(etHeight.getText().toString());
                }

                if (etWeight.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please input Weight!", Toast.LENGTH_LONG).show();
                }else {
                    weight = Double.parseDouble(etWeight.getText().toString());
                }
                phone = etPhone.getText().toString();
                email = etEmail.getText().toString();
                patientCondition = etPatientCondition.getText().toString();



                if (!name.equals("") && !profileType.equals("") && !gender.equals("") && !bloodType.equals("") && !currentDate.equals("") && !patientCondition.equals("")){

                    PatientTemplate p = new PatientTemplate(name,profileType,gender,bloodType,currentDate,age,height,weight,phone,email,patientCondition,fianalImage);
                    long insert = databaseHelper.addPatient(p);

                    if (insert == -1){

                        String text="Sorry";
                        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
                    }else {


                        Toast.makeText(getApplicationContext(), "Save Success!", Toast.LENGTH_SHORT).show();

                        Intent i= new Intent(CreateProfile.this,MainActivity.class);
                        startActivity(i);

                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();

                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Complete Information!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void onRadioButtonClick(View v){

        boolean checked= ((RadioButton)v).isChecked();

        switch (v.getId()){

            case R.id.rbMale:
                if (checked)
                    gender = "Male";
                break;

            case R.id.rbFemale:
                if (checked)
                    gender = "Female";
                break;

        }
    }

    public void setInitialize() {

        etPatientName = (EditText)findViewById(R.id.etPatientName);
        tvCurrentDate= (TextView) findViewById(R.id.tvCurrentDate);
        etAge= (EditText) findViewById(R.id.etAge);
        etHeight= (EditText) findViewById(R.id.etHeight);
        etWeight= (EditText) findViewById(R.id.etWeight);
        etPhone= (EditText) findViewById(R.id.etPhone);
        etEmail= (EditText) findViewById(R.id.etEmail);
        etPatientCondition= (EditText) findViewById(R.id.etPatientCondition);
        imageView= (ImageView) findViewById(R.id.imageView);
        spnType= (Spinner) findViewById(R.id.spnType);
        spnBlood= (Spinner) findViewById(R.id.spnBlood);
        btnSave = (Button)findViewById(R.id.btnSave);

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

            if (tvCurrentDate.getText().toString() != null){
                tvCurrentDate.setText(" ");
                tvCurrentDate.setText(dayOfMonth+" - "+months[monthOfYear]+" - "+year);
            }else {

                tvCurrentDate.setText(dayOfMonth+" - "+months[monthOfYear]+" - "+year);
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
                fianalImage = stream.toByteArray();

            }
        }
    }



    @Override
    public void onBackPressed() {

        Intent i= new Intent(CreateProfile.this,MainActivity.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
