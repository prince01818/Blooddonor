package com.example.nipu.blooddonor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SearchOption extends AppCompatActivity {
    Button add,click,about;
  /*  private static  String[] bloodgroup={"Select Blood Group","A+","A-","B+","B-","AB+","AB-","O+","O-"};
    private Spinner spnBloodGroup;
    ArrayAdapter<String>adapter;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_option);
      /*  spnBloodGroup=(Spinner)findViewById(R.id.spinner);
        adapter=new ArrayAdapter<String>(SearchOption.this,R.layout.support_simple_spinner_dropdown_item,bloodgroup);
        spnBloodGroup.setAdapter(adapter);*/

        about=(Button)findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intabout=new Intent(SearchOption.this,KnowledgeDonate.class);
                startActivity(intabout);
            }
        });


        add=(Button)findViewById(R.id.addDoner);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intadd=new Intent(SearchOption.this,AddDetails.class);
                startActivity(intadd);
            }
        });

        click=(Button)findViewById(R.id.donorlist);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intclick=new Intent(SearchOption.this,DonorList.class);
                startActivity(intclick);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_option, menu);
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
