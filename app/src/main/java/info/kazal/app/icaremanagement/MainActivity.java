package info.kazal.app.icaremanagement;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ListView lvName, mDrawerList;
    TextView txtMainVisibility;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] mListItems = {"Add Profile", "Important note","Emergency Call"};
    private ArrayList<PatientTemplate> allPatient;
    DatabaseHelper databaseHelper;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        allPatient = databaseHelper.getAllPatient();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2196F3")));
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);


        lvName = (ListView)findViewById(R.id.lvName);
        txtMainVisibility = (TextView) findViewById(R.id.txtMainVisibility);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.left_drawer);


        if (allPatient.size() == 0){
            txtMainVisibility.setVisibility(View.VISIBLE);
            lvName.setVisibility(View.GONE);
        }else {

            listAdapter = new ListAdapter(this, allPatient);
            lvName.setAdapter(listAdapter);
        }

        lvName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                PatientTemplate patient = allPatient.get(position);

                int patient_id = patient.getId();

                Intent i = new Intent(MainActivity.this, AllService.class);
                i.putExtra("patient_id", patient_id);
                startActivity(i);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();

            }
        });
        registerForContextMenu(lvName);

        //mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        ArrayAdapter<String> drawerAdapter = new ArrayAdapter<String>(this,R.layout.drawer_list_item,mListItems);
        mDrawerList.setAdapter(drawerAdapter);
        setupDrawer();
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                switch (position){

                    case 0:

                        Intent i = new Intent(MainActivity.this, CreateProfile.class);
                        startActivity(i);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();

                        break;
                    case 1:

                        Intent intent = new Intent(MainActivity.this, NoteList.class);
                        startActivity(intent);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();

                        break;
                    case 2:

                        Intent in = new Intent(MainActivity.this, EmergencyCallView.class);
                        startActivity(in);
                        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();

                        break;
                }
            }
        });



    }

    public void setupDrawer(){

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("iCare");
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {

                super.onDrawerClosed(view);
                getSupportActionBar().setTitle("iCare");
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_main, menu);;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){

            case R.id.action_delete:
                break;
            case R.id.action_edit:

                PatientTemplate patient = allPatient.get(info.position);
                int patient_id = patient.getId();

                Intent intent = new Intent(MainActivity.this, UpdateProfile.class);
                intent.putExtra("patient_id", patient_id);
                startActivity(intent);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();

                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_add) {

            Intent i = new Intent(MainActivity.this, CreateProfile.class);
            startActivity(i);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();

            return true;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
