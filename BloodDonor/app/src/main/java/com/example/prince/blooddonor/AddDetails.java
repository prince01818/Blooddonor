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
import android.widget.Toast;

public class AddDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        // btnSave (Save)
        final Button save = (Button) findViewById(R.id.submit);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (SaveData()) {

                    Intent newActivity = new Intent(AddDetails.this, SearchOption.class);

                    startActivity(newActivity);

                }

            }
        });

    }
    public boolean SaveData()
    {

// txtMemberID, txtName, txtTel
        final EditText tMemberID = (EditText) findViewById(R.id.editid);
        final EditText tName = (EditText) findViewById(R.id.editname);
        final EditText tBlood = (EditText) findViewById(R.id.editblood);
        final EditText tEmail = (EditText) findViewById(R.id.editemail);
        final EditText tPhone = (EditText) findViewById(R.id.editphone);
        final EditText tDate = (EditText) findViewById(R.id.editdate);
        final EditText tAddress = (EditText) findViewById(R.id.editaddress);
// Dialog

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        AlertDialog ad = adb.create();



// Check MemberID

        if(tMemberID.getText().length() == 0)

        {

            ad.setMessage("Please input [MemberID] ");
            ad.show();
            tMemberID.requestFocus();

            return false;

        }

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
        // Check Email

        if(tEmail.getText().length() == 0)

        {
            ad.setMessage("Please input [Email Address] ");
            ad.show();
            tEmail.requestFocus();
            return false;

        }
        // Check phone

        if(tPhone.getText().length() == 0)

        {
            ad.setMessage("Please input [Phone Number] ");
            ad.show();
            tPhone.requestFocus();
            return false;

        }
        // Check date

        if(tDate.getText().length() == 0)

        {
            ad.setMessage("Please input [Last date of Donation] ");
            ad.show();
            tDate.requestFocus();
            return false;

        }
        // Check  Address

        if(tAddress.getText().length() == 0)

        {
            ad.setMessage("Please input [Address] ");
            ad.show();
            tAddress.requestFocus();
            return false;

        }
// new Class DB


        final myDBClass myDb = new myDBClass(this);

// Check Data (MemberID exists)

        String arrData[] = myDb.SelectData(tMemberID.getText().toString());

        if(arrData!= null)
        {
            ad.setMessage("MemberID already exists!  ");
            ad.show();
            tMemberID.requestFocus();

            return false;

        }

// Save Data

        long saveStatus = myDb.InsertData(tMemberID.getText().toString(),
                tName.getText().toString(),
                tBlood.getText().toString(),
                tEmail.getText().toString(),
                tPhone.getText().toString(),
                tDate.getText().toString(),
                tAddress.getText().toString());

        if(saveStatus <=  0)

        {

            ad.setMessage("Error!! ");
            ad.show();

            return false;

        }

        Toast.makeText(AddDetails.this,"Add Data Successfully. ",
                Toast.LENGTH_SHORT).show();

        return true;

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_details, menu);
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
