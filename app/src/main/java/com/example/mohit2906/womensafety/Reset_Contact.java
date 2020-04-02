package com.example.mohit2906.womensafety;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Reset_Contact extends AppCompatActivity {

    Toolbar toolbar_reset_contact;
    EditText reset_contact_edit;
    Button reset_contact_btn,reset_all_contact;
    DBHelper dbHelper;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset__contact);

        reset_contact_edit = (EditText)findViewById(R.id.reset_contact_edit);
        reset_contact_btn = (Button)findViewById(R.id.reset_contact_btn);
        reset_all_contact = (Button)findViewById(R.id.reset_all_contact);
        dbHelper = new DBHelper(this);

        setUpToolBar();
        deleteByContact();
        deleteAll();

    }

    private void deleteAll()
    {
        reset_all_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder aleBuilder = new AlertDialog.Builder(Reset_Contact.this);
                aleBuilder   .setMessage("Are you Sure?")
                             .setCancelable(true)
                             .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialogInterface, int i)
                                 {
                                     boolean isAllDeleted = dbHelper.deleteAll();
                                     if(isAllDeleted)
                                     {
                                         Toast.makeText(Reset_Contact.this, "All Phone No. Deleted!!", Toast.LENGTH_SHORT).show();
                                     }
                                     else {
                                         Toast.makeText(Reset_Contact.this,"Something went wrong!!",Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             })
                             .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialogInterface, int i) {
                                     dialogInterface.cancel();
                                 }
                             });
                AlertDialog alert = aleBuilder.create();
                alert.setTitle("Delete all registered contacts!!");
                alert.show();

            }
        });
    }

    private void deleteByContact()
    {
        reset_contact_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(reset_contact_edit.getText().toString().isEmpty())
                {
                    reset_contact_edit.setError("Phone No. is Empty!!");
                    return;
                }
                if(reset_contact_edit.getText().toString().length()!=10)
                {
                    reset_contact_edit.setError("Phone No. must contain 10 digits!!");
                    return;
                }
                Cursor cursor = dbHelper.checkPhone();
                while(cursor.moveToNext())
                {
                    if(cursor.getString(0).equals(reset_contact_edit.getText().toString()))
                    {
                        flag=1;
                        break;
                    }
                }
                if(flag==0)
                {
                    Toast.makeText(Reset_Contact.this, "Phone No. does not exits!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    boolean isDeleted = dbHelper.deleteData(reset_contact_edit.getText().toString().trim());
                    if(isDeleted)
                    {
                        Toast.makeText(Reset_Contact.this,"Phone No. deleted successfully!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Reset_Contact.this,"Something went wrong!!",Toast.LENGTH_SHORT).show();
                    }

                }
                reset_contact_edit.setText("");
                flag=0;

            }
        });
    }

    public void setUpToolBar()
    {
        toolbar_reset_contact = (Toolbar)findViewById(R.id.toolbar_reset_contact);
        setSupportActionBar(toolbar_reset_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        toolbar_reset_contact.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
