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
import android.widget.Toast;

public class Emergency_Contact_List extends AppCompatActivity {

    Toolbar toolbar_emergency_contact;
    ListView emergency_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency__contact__list);

        final String[] emergencyName = {
                "Police", "All India Help Line", "Women In Distress", "Child Abuse","All In One"
        };
        final String[] emergencyContact = {
                "100", "1090", "1091", "1098","112"
        };

        setUpToolBar();
        AllEmergencyContactsList allEmergencyContactsList = new AllEmergencyContactsList(this,emergencyContact,emergencyName);
        emergency_list = (ListView)findViewById(R.id.emergency_list);
        emergency_list.setAdapter(allEmergencyContactsList);

        emergency_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Emergency_Contact_List.this, "Calling..." + emergencyName[i], Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + emergencyContact[i]));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (ActivityCompat.checkSelfPermission(Emergency_Contact_List.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });

    }

    public void setUpToolBar()
    {
        toolbar_emergency_contact = (Toolbar)findViewById(R.id.toolbar_emergency_contact);
        setSupportActionBar(toolbar_emergency_contact);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_emergency_contact.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
