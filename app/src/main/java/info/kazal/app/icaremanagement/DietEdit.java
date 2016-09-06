package info.kazal.app.icaremanagement;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DietEdit extends ActionBarActivity {

    private EditText etEditBreakfast, etEditLunch, etEditDinner;
    private TextView txtDietEditDay;
    private Button btnEditSave;
    private DatabaseHelper databaseHelper;
    private Intent intent;
    private int id_diet;
    private Diet diet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_edit);

        databaseHelper = new DatabaseHelper(this);

        intent = getIntent();
        id_diet = intent.getIntExtra("id_diet", -1);
        diet = databaseHelper.getADietChart(id_diet);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        initialize();

        txtDietEditDay.setText(diet.getDay());
        etEditBreakfast.setText(diet.getBreakfast());
        etEditLunch.setText(diet.getLunch());
        etEditDinner.setText(diet.getDinner());

        btnEditSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!etEditBreakfast.getText().equals("")){

                    String breakfast = etEditBreakfast.getText().toString();
                    String lunch = etEditLunch.getText().toString();
                    String dinner = etEditDinner.getText().toString();

                    Diet d = new Diet(diet.getId_patient(),diet.getDay(),breakfast,lunch,dinner);
                    int updated = databaseHelper.updateDiet(d, id_diet);

                    if (updated > 0){

                        Toast.makeText(getApplicationContext(), "Update success!", Toast.LENGTH_SHORT).show();

                        Intent i= new Intent(DietEdit.this, MyDietChart.class);
                        startActivity(i);

                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                    }else {

                        Toast.makeText(getApplicationContext(), "Sorry unable to update!", Toast.LENGTH_SHORT).show();
                    }
                }else {

                    Toast.makeText(getApplicationContext(), "Diet menu can't be empty! ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initialize(){

        txtDietEditDay = (TextView)findViewById(R.id.txtDietEditDay);
        etEditBreakfast = (EditText)findViewById(R.id.etEditBreakfast);
        etEditLunch = (EditText)findViewById(R.id.etEditLunch);
        etEditDinner = (EditText)findViewById(R.id.etEditDinner);
        btnEditSave = (Button)findViewById(R.id.btnEditSave);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_diet_edit, menu);
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

        Intent i= new Intent(DietEdit.this, MyDietChart.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
