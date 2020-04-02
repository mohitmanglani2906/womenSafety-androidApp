package com.example.mohit2906.womensafety;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Added_Number_List extends AppCompatActivity {

    ListView list_added_number;
    Toolbar toolbar_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added__number__list);


        list_added_number = (ListView)findViewById(R.id.list_added_number);
        DBHelper dbHelper = new DBHelper(this);
        final ArrayList<UserContactsAdded> userList =dbHelper.getAllData();

        Custome_Adaptor_Contacts custome_adaptor_contacts =  new Custome_Adaptor_Contacts(userList,this);
        list_added_number.setAdapter(custome_adaptor_contacts);

        setupToolBar();
        clickAndCall();

    }

    private void clickAndCall()
    {
        list_added_number.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String selected_contact = ((TextView)view.findViewById(R.id.set_phone)).getText().toString();
                String selected_name = ((TextView)view.findViewById(R.id.set_name)).getText().toString();
                Toast.makeText(Added_Number_List.this,"Calling..... " + selected_name,Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + selected_contact));
                if(ActivityCompat.checkSelfPermission(Added_Number_List.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(Added_Number_List.this,"Permission Not Given...", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(callIntent);
            }
        });
    }

    public void setupToolBar()
    {
        toolbar_call = (Toolbar)findViewById(R.id.toolbar_added_contacts);
        setSupportActionBar(toolbar_call);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        toolbar_call.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
