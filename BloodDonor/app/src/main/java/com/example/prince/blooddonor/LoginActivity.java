package com.example.nipu.blooddonor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText userNameET;
    EditText passwordET;
    SharedPreferences preferences;

    String userName;
    String password;


    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userNameET=(EditText)findViewById(R.id.user);
        passwordET=(EditText)findViewById(R.id.pass);
        this.check();


    }


    public boolean check() {
        preferences = getBaseContext().getSharedPreferences("myPre", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (this.getName().equals("")) {
            editor.putString("userName", userName);
            editor.apply();
            return true;
        } else {
            userNameET.setText(this.getName());
            return true;
        }
    }

    public boolean passwordCheck(){
        preferences=getBaseContext().getSharedPreferences("myPre",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();

        if(this.getPassword().equals(""))
        {
            editor.putString("password", password);
            editor.apply();
            return true;
        }
        else
        if(password.equals(this.getPassword()))
        {

            return true;
        }
        else
            return false;
    }


    public String getName(){
        String name=preferences.getString("userName", "");
        return name;
    }

    public String getPassword(){
        String pass=preferences.getString("password", "");
        return pass;
    }


    public void save (View view) {

        userName = userNameET.getText().toString();

        password = passwordET.getText().toString();
        boolean status = check();
        boolean pass = passwordCheck();

        if (status && pass) {
            Intent intent = new Intent(LoginActivity.this, SearchOption.class);
            startActivity(intent);

        } else {

            Toast.makeText(LoginActivity.this, "Password Don't Match", Toast.LENGTH_LONG).show();

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
