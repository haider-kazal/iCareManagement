package info.kazal.app.icaremanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class DoctorList extends ActionBarActivity {

    private ListView listDoctor;
    private TextView txtDoctorVisibility;
    static int patient_id;
    private Intent intent;

    private DatabaseHelper databaseHelper;
    private ArrayList<Doctor> allDoctor;
    private DoctorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));


        listDoctor = (ListView)findViewById(R.id.listDoctor);
        txtDoctorVisibility = (TextView)findViewById(R.id.txtDoctorVisibility);

        databaseHelper = new DatabaseHelper(this);

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

        allDoctor = databaseHelper.getAllDoctor(patient_id);

        if (allDoctor.size() == 0){

            txtDoctorVisibility.setVisibility(View.VISIBLE);
            listDoctor.setVisibility(View.GONE);
        }else {

            adapter = new DoctorAdapter(this, allDoctor);
            listDoctor.setAdapter(adapter);
        }

        listDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Doctor doctor = allDoctor.get(position);
                int id_doctor = doctor.getId_doctor();
                Intent i = new Intent(DoctorList.this, DoctorView.class);
                i.putExtra("id_doctor", id_doctor);
                startActivity(i);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctor_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_doctor) {

            Intent i = new Intent(DoctorList.this, DoctorAdd.class);
            i.putExtra("patient_id", patient_id);
            startActivity(i);

            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent i= new Intent(DoctorList.this, AllService.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
