package com.example.nipu.blooddonor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DonorList extends AppCompatActivity {


    ArrayList<HashMap<String, String>> MebmerList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);
        ShowListData();

        final myDBClass myDb = new myDBClass(this);
        final ArrayList<HashMap<String, String>> MebmerList = myDb.SelectAllData();

// listView1
        ListView lisView1 = (ListView)findViewById(R.id.list_item);

        SimpleAdapter sAdap;

        sAdap = new SimpleAdapter(DonorList.this, MebmerList, R.layout.column,
                new String[] {"MemberID", "Name"}, new int[] {R.id.ColId, R.id.ColName});
        lisView1.setAdapter(sAdap);
        lisView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {

// Show on new activity

                Intent newActivity = new Intent(DonorList.this, ShowDetails.class);
                newActivity.putExtra("MemID", MebmerList.get(position).get("MemberID").toString());

                startActivity(newActivity);


            }
        });

        // Show List data
    }
        public void ShowListData()

        {

            myDBClass myDb = new myDBClass(this);
            MebmerList = myDb.SelectAllData();



// listView1

            ListView lisView1 = (ListView)findViewById(R.id.list_item);
            SimpleAdapter sAdap;
            sAdap = new SimpleAdapter(DonorList.this, MebmerList, R.layout.column,

            new String[] {"MemberID", "Name", }, new int[] {R.id.ColId, R.id.ColName});

            lisView1.setAdapter(sAdap);

            registerForContextMenu(lisView1);
        }

        @Override

        public void onCreateContextMenu(ContextMenu menu, View v,

        ContextMenu.ContextMenuInfo menuInfo) {
//if (v.getId()==R.id.list) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

            menu.setHeaderTitle("Command for : " + MebmerList.get(info.position).get("Name").toString());
            String[] menuItems = getResources().getStringArray(R.array.CmdMenu);

            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }

        }


        @Override

        public boolean onContextItemSelected(MenuItem item) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            int menuItemIndex = item.getItemId();

            String[] menuItems = getResources().getStringArray(R.array.CmdMenu);
            String CmdName = menuItems[menuItemIndex];
            String MemID = MebmerList.get(info.position).get("MemberID").toString();

//String MemName = MebmerList.get(info.position).get("Name").toString();

// Check Event Command
            if ("Edit".equals(CmdName)) {
// Show on new activity
                Intent newActivity = new Intent(DonorList.this,UpdateActivity.class);
                   newActivity.putExtra("MemID", MebmerList.get(info.position).get("MemberID").toString());
                   startActivity(newActivity);

// for Delete Command

            } else if ("Delete".equals(CmdName)) {

                myDBClass myDb = new myDBClass(this);
                long flg = myDb.DeleteData(MemID);
                if(flg > 0)
                {
                    Toast.makeText(DonorList.this,"Delete Data Successfully",

                            Toast.LENGTH_LONG).show();

                }

                else

                {

                    Toast.makeText(DonorList.this, "Delete Data Failed.",
                            Toast.LENGTH_LONG).show();

                }

// Call Show Data again
                ShowListData();

            }

            return true;

        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donor_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
