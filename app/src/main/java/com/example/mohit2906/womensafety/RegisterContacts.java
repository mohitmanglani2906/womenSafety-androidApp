package com.example.mohit2906.womensafety;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterContacts extends AppCompatActivity {

    Toolbar toolbarregister;
    EditText registernumber,registername;
    Button btnsavenumber;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_contacts);

        dbHelper = new DBHelper(this);
        toolbarregister=(Toolbar)findViewById(R.id.toolbarregister);
        registername = (EditText)findViewById(R.id.registername);
        registernumber =  (EditText)findViewById(R.id.registernumber);
        btnsavenumber = (Button)findViewById(R.id.btnsavenumber);

        setSupportActionBar(toolbarregister);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        toolbarregister.setNavigationOnClickListener(new View.OnClickListener()
        {
           @Override
           public void onClick(View view)
           {
               onBackPressed();
               
           }
       });
        addData();

    }

    public void addData()
    {
        btnsavenumber.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    if(registernumber.getText().toString().isEmpty())
                    {
                        registernumber.setText("Enter Phono No.");
                        return;
                    }
                    else if(registernumber.getText().toString().length()!=10)
                    {
                        registernumber.setError("Phone No. should contain 10 digits!!");
                        return;
                    }
                    if(registername.getText().toString().isEmpty())
                    {
                        registername.setError("Enter Name.");
                        return;
                    }

                    Cursor cursor = dbHelper.checkPhone();
                    while(cursor.moveToNext())
                    {
                        if(cursor.getString(0).equals(registernumber.getText().toString()))
                        {
                            Toast.makeText(RegisterContacts.this,"Phone No. Already Exists",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    boolean isInserted = dbHelper.insertData(registernumber.getText().toString().trim(),registername.getText().toString().trim());
                    if(isInserted)
                    {
                        Toast.makeText(RegisterContacts.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                        registernumber.setText(" ");
                        registername.setText(" ");
                    }
                    else
                    {
                        Toast.makeText(RegisterContacts.this,"Something went wrong!",Toast.LENGTH_SHORT).show();
                    }

                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        });
    }


}
