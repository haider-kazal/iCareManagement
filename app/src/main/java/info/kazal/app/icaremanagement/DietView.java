package info.kazal.app.icaremanagement;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class DietView extends ActionBarActivity {

    TextView txtDietViewDay, txtDietViewBreakfast, txtDietViewLunch, txtDietViewDinner,
            txtDietViewCheckAlarm, txtDietViewAlarmTime, txtDietViewAlarmDate;
    Button btnDietViewSetAlarm, btnDietViewDeleteAlarm;
    private DatabaseHelper databaseHelper;

    private Intent intent, alertIntent;
    private  AlarmManager alarmManager;
    private int id_diet;
    private Diet diet;
    private String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    private Calendar currentCalender, calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_view);

        databaseHelper = new DatabaseHelper(this);
        intent = getIntent();
        id_diet = intent.getIntExtra("id_diet", -1);
        diet = databaseHelper.getADietChart(id_diet);

        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        calendar = Calendar.getInstance();

        currentCalender = Calendar.getInstance();
        currentCalender.get(Calendar.YEAR);
        currentCalender.get(Calendar.MONTH);
        currentCalender.get(Calendar.DAY_OF_MONTH);
        currentCalender.get(Calendar.HOUR_OF_DAY);
        currentCalender.get(Calendar.MINUTE);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        initialize();

        txtDietViewDay.setText(diet.getDay().toString());
        txtDietViewBreakfast.setText(diet.getBreakfast().toString());
        txtDietViewLunch.setText(diet.getLunch().toString());
        txtDietViewDinner.setText(diet.getDinner().toString());

        txtDietViewAlarmTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });

        txtDietViewAlarmDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(2);
            }
        });

        btnDietViewSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertIntent = new Intent(DietView.this, AlarmReceiverDiet.class);
                alertIntent.putExtra("id_diet", id_diet);

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        PendingIntent.getBroadcast(DietView.this, id_diet, alertIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT));

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24 * 60 * 60 * 1000 * 7, PendingIntent.getBroadcast(DietView.this, id_diet, alertIntent, PendingIntent.FLAG_CANCEL_CURRENT));

                Toast.makeText(getApplicationContext(), "Alarm is active", Toast.LENGTH_SHORT).show();
            }
        });

        if ((PendingIntent.getBroadcast(DietView.this, id_diet, new Intent(DietView.this, AlarmReceiverDiet.class), PendingIntent.FLAG_NO_CREATE )!=null)){

            txtDietViewCheckAlarm.setText("");
            txtDietViewCheckAlarm.setText("Alarm is set.");
            //txtDietViewCheckAlarm.setText(calendar.get(Calendar.YEAR) + "-" + months[calendar.get(Calendar.MONTH)] + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "\n" + calendar.get(Calendar.HOUR_OF_DAY) + "." + calendar.get(Calendar.MINUTE));

        }else {

            txtDietViewCheckAlarm.setText("");
            txtDietViewCheckAlarm.setText("Alarm is not set yet");
        }

        btnDietViewDeleteAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alarmManager.cancel(PendingIntent.getBroadcast(DietView.this, id_diet, new Intent(DietView.this, AlarmReceiverDiet.class), PendingIntent.FLAG_CANCEL_CURRENT));
                Toast.makeText(getApplicationContext(), "Alarm deleted!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (id == 2) {
            DatePickerDialog dialog = new DatePickerDialog(this, listener,
                    year, month, day);
            return dialog;
        } else {
            TimePickerDialog dialog = new TimePickerDialog(this, timeListener,
                    hour, minute, true);
            return dialog;
        }


    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            if (txtDietViewAlarmDate.getText().toString() != null){
                txtDietViewAlarmDate.setText(" ");
                txtDietViewAlarmDate.setText(dayOfMonth+" - "+months[monthOfYear]+" - "+year);
            }else {

                txtDietViewAlarmDate.setText(dayOfMonth+" - "+months[monthOfYear]+" - "+year);
            }
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        }
    };

    TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            if (txtDietViewAlarmTime.getText().toString() != null){

                txtDietViewAlarmTime.setText("");
                txtDietViewAlarmTime.setText(hourOfDay+"."+minute);

            }else {
                txtDietViewAlarmTime.setText(hourOfDay+"."+minute);
            }
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
        }
    };



    public void initialize(){

        txtDietViewDay = (TextView)findViewById(R.id.txtDietViewDay);
        txtDietViewBreakfast = (TextView)findViewById(R.id.txtDietViewBreakfast);
        txtDietViewLunch = (TextView)findViewById(R.id.txtDietViewLunch);
        txtDietViewDinner = (TextView)findViewById(R.id.txtDietViewDinner);
        txtDietViewCheckAlarm = (TextView)findViewById(R.id.txtDietViewCheckAlarm);
        txtDietViewAlarmTime = (TextView)findViewById(R.id.txtDietViewAlarmTime);
        txtDietViewAlarmDate = (TextView)findViewById(R.id.txtDietViewAlarmDate);

        btnDietViewSetAlarm = (Button)findViewById(R.id.btnDietViewSetAlarm);
        btnDietViewDeleteAlarm = (Button)findViewById(R.id.btnDietViewDeleteAlarm);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diet_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_dietView_done) {

            Intent i= new Intent(DietView.this, MyDietChart.class);
            startActivity(i);

            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();

            return true;
        }

        if (id == R.id.action_dietView_edit){

            alarmManager.cancel(PendingIntent.getBroadcast(DietView.this, id_diet, new Intent(DietView.this, AlarmReceiverDiet.class), PendingIntent.FLAG_CANCEL_CURRENT));

            Intent intent = new Intent(DietView.this, DietEdit.class);
            intent.putExtra("id_diet", id_diet);
            startActivity(intent);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent i= new Intent(DietView.this, MyDietChart.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
