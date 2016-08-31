package com.example.nipu.blooddonor;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowDetails extends AppCompatActivity{
    EditText numberId;
    EditText smsSend;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        numberId=(EditText)findViewById(R.id.editnum);
        smsSend=(EditText)findViewById(R.id.editmsg);
        send=(Button)findViewById(R.id.btnsend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thenumber=numberId.getText().toString();
                String mMsg=smsSend.getText().toString();
                sendMsg(thenumber,mMsg);
            }
        });

        final Button cancel = (Button) findViewById(R.id.btnEdit);
        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

// Open Form Main
                Intent newActivity = new Intent(ShowDetails.this,DonorList.class);

                startActivity(newActivity);

            }

        });



        // Read var from Intent
        Intent intent = getIntent();
        String MemID = intent.getStringExtra("MemID");
// Show Data
        ShowData(MemID);



    }

    protected void sendMsg(String theNumber,String mMsg){
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(theNumber,null,mMsg,null,null);

        Toast.makeText(ShowDetails.this,"Messege Sent.",Toast.LENGTH_LONG).show();

    }






    public void ShowData(String MemID)
    {
        final TextView tMemberID = (TextView) findViewById(R.id.txtMemberID);
        final TextView tName = (TextView) findViewById(R.id.txtMemberName);
        final TextView tBlood = (TextView) findViewById(R.id.txtMemberBlood);
        final TextView tEmail = (TextView) findViewById(R.id.txtMemberEmail);
        final TextView tPhone = (TextView) findViewById(R.id.txtMemberPhone);
        final TextView tDate = (TextView) findViewById(R.id.txtMemberDate);
        final TextView tAddress = (TextView) findViewById(R.id.txtMemberAddress);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_details, menu);
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
