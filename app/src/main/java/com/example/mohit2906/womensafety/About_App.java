package com.example.mohit2906.womensafety;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class About_App extends AppCompatActivity {

    Toolbar toolbar_about_app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__app);

        setUpToolBar();
    }

    private void setUpToolBar()
    {
        toolbar_about_app= (Toolbar)findViewById(R.id.toolbar_about_app);
        setSupportActionBar(toolbar_about_app);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        toolbar_about_app.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }
}
