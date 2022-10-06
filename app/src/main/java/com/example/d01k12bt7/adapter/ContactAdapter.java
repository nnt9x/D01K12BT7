package com.example.d01k12bt7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.d01k12bt7.R;
import com.example.d01k12bt7.model.Contact;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> dataSource;

    public ContactAdapter(Context context, List<Contact> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Bind id va do du lieu
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_contact,viewGroup,false);
        }
        TextView tvName, tvPhone;
        tvName = view.findViewById(R.id.tvItemContact);
        tvPhone = view.findViewById(R.id.tvItemPhone);

        Contact contact = dataSource.get(i);
        tvName.setText(contact.getName());
        tvPhone.setText(contact.getPhone());

        return view;
    }
}
