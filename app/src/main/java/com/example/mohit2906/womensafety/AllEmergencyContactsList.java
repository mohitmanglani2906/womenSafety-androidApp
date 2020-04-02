package com.example.mohit2906.womensafety;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class AllEmergencyContactsList extends ArrayAdapter
{
    public final Activity context;
    public final String[] emergencyContact;
    public final String[] emergencyName;


    public AllEmergencyContactsList(Activity context,String[] emergencyContact,String[] emergencyName)
    {
        super(context,R.layout.emergency_list,emergencyContact);
        this.context=context;
        this.emergencyContact=emergencyContact;
        this.emergencyName=emergencyName;
    }

    public View getView(int position, View view, ViewGroup parnet)
    {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView  = layoutInflater.inflate(R.layout.emergency_list,null,true);
        TextView emergency_contact_custom = (TextView)rowView.findViewById(R.id.emergency_contact_custom);
        TextView emergency_name_custom = (TextView)rowView.findViewById(R.id.emergency_name_custom);

        emergency_contact_custom.setText(emergencyContact[position]);
        emergency_name_custom.setText(emergencyName[position]);
        return rowView;
    }
}
