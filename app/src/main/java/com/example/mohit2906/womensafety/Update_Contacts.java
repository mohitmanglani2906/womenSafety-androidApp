package com.example.mohit2906.womensafety;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Contacts extends AppCompatActivity {

    Toolbar toolbarupdatecontact;
    EditText old_contact,new_contact,new_name;
    Button update_contact;
    DBHelper dbHelper;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__contacts);



        dbHelper = new DBHelper(this);
        old_contact=(EditText)findViewById(R.id.old_contact);
        new_contact=(EditText)findViewById(R.id.new_contact);
        new_name=(EditText)findViewById(R.id.new_name);
        update_contact=(Button)findViewById(R.id.update_contact_btn);

        setupToolbar();
        updateData();

    }

    public void updateData()
    {
        update_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(old_contact.getText().toString().isEmpty())
                {
                    old_contact.setError("Enter Old Phone No.");
                    return;
                }
                else if(new_contact.getText().toString().isEmpty())
                {
                    new_contact.setError("Enter New Phone No.");
                    return;
                }
                else if(old_contact.getText().toString().length()!=10)
                {
                    old_contact.setError("Phone No. should contain 10 digits!");
                    return;
                }
                else if(new_contact.getText().toString().length()!=10)
                {
                    new_contact.setError("Phone No. should contain 10 digits!");
                    return;
                }
                else if(new_name.getText().toString().isEmpty())
                {
                    new_name.setError("Enter Any Name.");
                    return;
                }
                else
                {
                    Cursor cursor = dbHelper.checkPhone();
                    Cursor new_cursor  = dbHelper.checkPhone();
                    while(cursor.moveToNext())
                    {
                        if(cursor.getString(0).equals(old_contact.getText().toString()))
                        {
                            flag=1;
                            break;
                        }
                    }

                    while(new_cursor.moveToNext())
                    {
                        if(new_cursor.getString(0).equals(new_contact.getText().toString()))
                        {
                            Toast.makeText(Update_Contacts.this,"New Phone No. Already Exits!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    if(flag==0)
                    {
                        Toast.makeText(Update_Contacts.this,"Old Phone No. does not Exits",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        boolean isUpdated = dbHelper.updateData(old_contact.getText().toString().trim(),new_contact.getText().toString().trim(),new_name.getText().toString().trim());
                        if(isUpdated==true)
                        {
                            Toast.makeText(Update_Contacts.this,"Updated Successfully!!",Toast.LENGTH_SHORT).show();
                            old_contact.setText(" ");
                            new_contact.setText(" ");
                            new_name.setText(" ");
                            flag=0;
                        }
                        else
                        {
                            Toast.makeText(Update_Contacts.this,"Not Updated Successfully",Toast.LENGTH_SHORT).show();
                        }

                    }



                }
            }
        });
    }

    public void setupToolbar()
    {
        toolbarupdatecontact = (Toolbar)findViewById(R.id.toolbar_update_contact);
        setSupportActionBar(toolbarupdatecontact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        toolbarupdatecontact.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }
}
