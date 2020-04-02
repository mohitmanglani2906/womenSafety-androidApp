package com.example.mohit2906.womensafety;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Custome_Adaptor_Contacts extends BaseAdapter
{

    private ArrayList<UserContactsAdded> userList;
    private Context context;

    public Custome_Adaptor_Contacts(ArrayList<UserContactsAdded> userList, Context context)
    {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.userList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder viewHolder = null;
        if(view==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =layoutInflater.inflate(R.layout.custome_list_numbers,null);
            viewHolder = new ViewHolder();
            viewHolder.set_phone =(TextView)view.findViewById(R.id.set_phone);
            viewHolder.set_name = (TextView)view.findViewById(R.id.set_name);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder =(ViewHolder)view.getTag();
        }

        UserContactsAdded uca =userList.get(i);
        viewHolder.set_phone.setText(uca.getPhoneno());
        viewHolder.set_name.setText(uca.getName());
        return view;
    }

    public static class ViewHolder
    {
        public TextView set_phone;
        public TextView set_name;

    }
}
