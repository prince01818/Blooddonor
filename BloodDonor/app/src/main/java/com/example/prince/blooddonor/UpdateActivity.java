package com.example.nipu.blooddonor;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent= getIntent();

        final String MemID = intent.getStringExtra("MemID");
// Show Data

        ShowData(MemID);

// btnSave (Save)

        final Button save = (Button) findViewById(R.id.submit);

        save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

// If Save Complete

                if(UpdateData(MemID))

                {
// Open Form ListUpdate

                    Intent newActivity = new Intent(UpdateActivity.this,DonorList.class);

                    startActivity(newActivity);

                }

            }

        });


    }

    public void ShowData(String MemID)
    {

        final TextView tMemberID = (TextView) findViewById(R.id.editid);

        final EditText tName = (EditText) findViewById(R.id.editname);

        final EditText tBlood = (EditText) findViewById(R.id.editblood);

        final EditText tEmail = (EditText) findViewById(R.id.editemail);

        final EditText tPhone = (EditText) findViewById(R.id.editphone);

        final EditText tDate = (EditText) findViewById(R.id.editdate);
        final TextView tAddress = (TextView) findViewById(R.id.editaddress);



// new Class DB

        final myDBClass myDb = new myDBClass(this);

// Show Data

        String arrData[] = myDb.SelectData(MemID);

        if(arrData != null)

        {

            tMemberID.setText(arrData[0]);

            tName.setText(arrData[1]);
            tBlood.setText(arrData[2]);
            tEmail.setText(arrData[3]);
            tPhone.setText(arrData[4]);
            tDate.setText(arrData[5]);
            tAddress.setText(arrData[6]);


        }

    }


    public boolean UpdateData(String MemID)
    {
        final EditText tName = (EditText) findViewById(R.id.editname);
        final EditText tBlood = (EditText) findViewById(R.id.editblood);
        final EditText tEmail = (EditText) findViewById(R.id.editemail);
        final EditText tPhone = (EditText) findViewById(R.id.editphone);
        final EditText tDate= (EditText) findViewById(R.id.editdate);
        final EditText tAddress = (EditText) findViewById(R.id.editaddress);



// Dialog

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        AlertDialog ad = adb.create();

// Check Name

        if(tName.getText().length() == 0)
        {

            ad.setMessage("Please input [Name] ");

            ad.show();
            tName.requestFocus();

            return false;
        }

// Check Blood
        if(tBlood.getText().length() == 0)

        {

            ad.setMessage("Please input [Blood Group] ");
            ad.show();

            tBlood.requestFocus();

            return false;
        }
        //Check Email
        if(tEmail.getText().length() == 0)

        {

            ad.setMessage("Please input [Email Address] ");
            ad.show();

            tEmail.requestFocus();

            return false;
        }
        //Check Phone
        if(tPhone.getText().length() == 0)

        {

            ad.setMessage("Please input [Phone Number] ");
            ad.show();

            tPhone.requestFocus();

            return false;
        }
        //Check date
        if(tDate.getText().length() == 0)

        {

            ad.setMessage("Please input [Last Donation date] ");
            ad.show();

            tDate.requestFocus();

            return false;
        }
        //Check Address
        if(tAddress.getText().length() == 0)

        {

            ad.setMessage("Please input [Address] ");
            ad.show();

            tAddress.requestFocus();

            return false;
        }

// new Class DB

        final myDBClass myDb = new myDBClass(this);

// Save Data

        long saveStatus = myDb.UpdateData(MemID, tName.getText().toString(),tBlood.getText().toString(),tEmail.getText().toString(),tPhone.getText().toString(),tDate.getText().toString(),
                tAddress.getText().toString());
        if(saveStatus <=  0)

        {

            ad.setMessage("Error!! ");

            ad.show();

            return false;
        }

        Toast.makeText(UpdateActivity.this,"Update Data Successfully. ",
                Toast.LENGTH_SHORT).show();

        return true;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update, menu);
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
