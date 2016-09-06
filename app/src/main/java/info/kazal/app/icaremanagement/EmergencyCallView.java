package info.kazal.app.icaremanagement;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class EmergencyCallView extends ActionBarActivity {

    private TextView txtCallVisibility, txtCallName, txtCallNumber;
    private Button btnAddNumber, btnDial;
    private ListView listNumber;
    private DbHelperNoteCall dbHelperNoteCall;
    private EmergencyCall eCall;
    private ArrayList<EmergencyCall> callList;
    private CallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_call_view);

        dbHelperNoteCall = new DbHelperNoteCall(this);
        callList = dbHelperNoteCall.getAllEmergencyNumber();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));

        initialize();


        if (callList.size() == 0){

            txtCallVisibility.setVisibility(View.VISIBLE);
            btnAddNumber.setVisibility(View.VISIBLE);
        }else {

            adapter = new CallAdapter(this, callList);
            listNumber.setAdapter(adapter);
        }

        listNumber.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                eCall = callList.get(position);

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + eCall.getEmergencyNumber()));
                startActivity(callIntent);
            }
        });

        btnAddNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(EmergencyCallView.this, EmergencyNumberAdd.class);
                startActivity(i);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        registerForContextMenu(listNumber);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_call, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){

            case R.id.action_delete_number:

                EmergencyCall em = callList.get(info.position);
                dbHelperNoteCall.deleteEmergencyCall(em.getId_call());
                Toast.makeText(getApplicationContext(), "Delete success!", Toast.LENGTH_SHORT).show();
                adapter.remove(em);
                adapter.notifyDataSetChanged();

                break;

            case R.id.action_edit_number:

                EmergencyCall e = callList.get(info.position);
                Intent intent = new Intent(EmergencyCallView.this, EmergencyNumberEdit.class);
                intent.putExtra("id_call", e.getId_call());
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();

                break;
        }
        return super.onContextItemSelected(item);
    }

    public void initialize(){

        txtCallVisibility = (TextView)findViewById(R.id.txtCallVisibility);
        txtCallName = (TextView)findViewById(R.id.txtCallName);
        txtCallNumber = (TextView)findViewById(R.id.txtCallNumber);

        btnAddNumber = (Button)findViewById(R.id.btnAddNumber);
        btnDial = (Button)findViewById(R.id.btnDial);

        listNumber = (ListView)findViewById(R.id.listNumber);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_emergency_call_view, menu);
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
//        if (id == R.id.action_delete_number) {
//
//            if (!eCall.getNameNumberHolder().equals("")){
//
//                dbHelperNoteCall.deleteEmergencyCall(eCall.getId_call());
//                Toast.makeText(getApplicationContext(), "Delete success!", Toast.LENGTH_SHORT).show();
//            }else {
//                Toast.makeText(getApplicationContext(), "Nothing to Delete!", Toast.LENGTH_SHORT).show();
//            }
//            return true;
//        }
//
//        if (id == R.id.action_edit_number){
//
//            Intent intent = new Intent(EmergencyCallView.this, EmergencyNumberEdit.class);
//            intent.putExtra("id_call", eCall.getId_call());
//            startActivity(intent);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            finish();
//
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(EmergencyCallView.this, MainActivity.class);
        startActivity(i);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        super.onBackPressed();
    }
}
