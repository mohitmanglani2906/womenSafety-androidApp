package com.example.mohit2906.womensafety;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ImageButton registernumber,choosenumber,saveme,callpolice,emergencynumber,share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Don't comment below two lines for using toolbar
        setUpToolbar();

        registernumber = (ImageButton)findViewById(R.id.registernumber);
        choosenumber=(ImageButton)findViewById(R.id.choosenumber);
        saveme=(ImageButton)findViewById(R.id.saveme);
        callpolice=(ImageButton)findViewById(R.id.callpolice);
        emergencynumber=(ImageButton)findViewById(R.id.emergencynumber);
        share=(ImageButton)findViewById(R.id.share);
        navigationView=(NavigationView)findViewById(R.id.design_navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch(menuItem.getItemId())
                    {
                        case R.id.nav_update_contact:
                        {
                            navigationUpdate();
                            break;
                        }

                        case R.id.nav_developer:
                        {
                            Toast.makeText(MainActivity.this,"Mohit Manglani" + "\n" + "IT Engineer/Developer",Toast.LENGTH_SHORT).show();
                            break;
                        }

                        case R.id.nav_reset:
                        {
                            resetContact();
                            break;
                        }

                        case R.id.nav_about:
                        {
                            aboutUs();
                            break;
                        }

                    }
                    return false;
            }
        });


        registernumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,RegisterContacts.class);
                startActivity(i);
            }
        });

        choosenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(MainActivity.this,Added_Number_List.class);
                startActivity(i)    ;
            }
        });

        saveme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send_message = new Intent(MainActivity.this,Send_Message.class);
                startActivity(send_message);
            }
        });

        callpolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent call_police = new Intent(Intent.ACTION_CALL);
                call_police.setData(Uri.parse("tel:" + 100));
                if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }
                Toast.makeText(MainActivity.this,"Calling Police..",Toast.LENGTH_SHORT).show();
                startActivity(call_police);
            }
        });

        emergencynumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emergency_list = new Intent(MainActivity.this,Emergency_Contact_List.class);
                startActivity(emergency_list);
            }
        });



    }

    public void navigationUpdate()
    {
//        System.out.println("Hey I am In Update Contact");
//        Toast.makeText(MainActivity.this, "Hello Contacts", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this,Update_Contacts.class);
        startActivity(i);
    }

    public void resetContact()
    {
        Intent i = new Intent(MainActivity.this,Reset_Contact.class);
        startActivity(i);
    }

    public void aboutUs()
    {
        Intent call_about = new Intent(MainActivity.this,About_App.class);
        startActivity(call_about);
    }
    public void setUpToolbar()
    {
        drawerLayout =(DrawerLayout)findViewById(R.id.drawerlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

}
