package info.kazal.app.icaremanagement;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddDietChart extends ActionBarActivity {

    private Spinner spnDay;
    private EditText etBreakfast, etLunch, etDinner;
    private Button btnSave;
    private Diet diet;

    private String[] dayList = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private String day;
    private int patient_id;
    private Intent intent;
    private DatabaseHelper databaseHelper;
    ArrayAdapter<String> adapterDayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diet_chart);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        intent = getIntent();
        patient_id = intent.getIntExtra("patient_id", 0);
        databaseHelper = new DatabaseHelper(this);


        spnDay = (Spinner)findViewById(R.id.spnDay);
        etBreakfast = (EditText)findViewById(R.id.etBreakfast);
        etLunch = (EditText)findViewById(R.id.etLunch);
        etDinner = (EditText)findViewById(R.id.etDinner);
        btnSave = (Button)findViewById(R.id.btnSave);


        adapterDayList = new ArrayAdapter<String>(getApplicationContext(), R.layout.custom_spinner, R.id.txtSpinner, dayList);
        spnDay.setAdapter(adapterDayList);
        spnDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                day = dayList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String breakfast = etBreakfast.getText().toString();
                String lunch = etLunch.getText().toString();
                String dinner = etDinner.getText().toString();

                if (!breakfast.equals("")){

                    diet = new Diet(patient_id,day,breakfast,lunch,dinner);
                    long inserted = databaseHelper.addDietChart(diet);

                    if (inserted == -1){

                        Toast.makeText(getApplicationContext(),"Sorry! save not success.",Toast.LENGTH_SHORT).show();

                    }else {

                        Toast.makeText(getApplicationContext(),"Save success.",Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(AddDietChart.this, MyDietChart.class);
                        startActivity(i);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                    }

                }else {

                    Toast.makeText(getApplicationContext(), "Please input your Breakfast menu", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    @Override
    public void onBackPressed() {

        Intent i= new Intent(AddDietChart.this, MyDietChart.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
