package info.kazal.app.icaremanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class AllService extends ActionBarActivity {

    private ImageButton ibProfile,ibDiet,ibMyDoctor,ibHLocation;
    static int patient_id;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_service);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        initialize();

        intent = getIntent();

        if (intent.getIntExtra("patient_id",-1) != -1 ){

            patient_id = intent.getIntExtra("patient_id",0);
            SharedPreferences sharedPreferences=getSharedPreferences("allService",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("patient_id",patient_id);
            editor.commit();

        }

        else {

            SharedPreferences preferences = getSharedPreferences("allService", Context.MODE_PRIVATE);
            preferences.getInt("patient_id",patient_id);

        }

        ibDiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AllService.this, MyDietChart.class);
                i.putExtra("patient_id", patient_id);
                startActivity(i);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        ibProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AllService.this, ProfileView.class);
                i.putExtra("patient_id", patient_id);
                startActivity(i);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        ibMyDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AllService.this, DoctorList.class);
                i.putExtra("patient_id", patient_id);
                startActivity(i);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        ibHLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
             Intent i = new Intent(AllService.this, InsertHospitalAddress.class);
                i.putExtra("patient_id", patient_id);
                startActivity(i);

            }
        });

    }

    private void initialize() {

        ibProfile= (ImageButton) findViewById(R.id.ibProfile);
        ibHLocation=(ImageButton)findViewById(R.id.ibHLocation);
        ibDiet= (ImageButton) findViewById(R.id.ibDiet);
        ibMyDoctor= (ImageButton) findViewById(R.id.ibMyDoctor);

    }



    @Override
    public void onBackPressed() {

        Intent i= new Intent(AllService.this, MainActivity.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
