package com.example.d01k12bt7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.d01k12bt7.adapter.ContactAdapter;
import com.example.d01k12bt7.dao.AppDatabase;
import com.example.d01k12bt7.dao.ContactDAO;
import com.example.d01k12bt7.dialog.ContactDialog;
import com.example.d01k12bt7.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvContact;
    private FloatingActionButton fabAdd;

    // Adapter
    private ContactAdapter myAdapter;
    private List<Contact> dataSource;

    // Dialog
    private ContactDialog contactDialog;

    // Config Database
    private ContactDAO contactDAO;

    private void initUI(){
        lvContact = findViewById(R.id.lvMainContacts);
        fabAdd = findViewById(R.id.fabMainAdd);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        // Database init
        AppDatabase db = Room.databaseBuilder(this,
                AppDatabase.class, "database-name")
                .allowMainThreadQueries().build();

        contactDAO = db.getContactDAO();

        dataSource = contactDAO.getAll();

        myAdapter = new ContactAdapter(this, dataSource);

        lvContact.setAdapter(myAdapter);

        // Nhan giu xoa
        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = dataSource.get(i);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Cảnh báo")
                        .setMessage("Bạn có muốn xoá liên hệ " + contact.getPhone())
                        .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                contactDAO.delete(contact);
                                dataSource.remove(contact);
                                myAdapter.notifyDataSetChanged();
                            }
                        })
                        .create().show();
                return false;
            }
        });
        lvContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact contact = dataSource.get(i);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("ContactName", contact.getName());
                startActivity(intent);
            }
        });
    }

    public void showContactDialog(View view) {
        // Show dialog len
        if(contactDialog == null){
            contactDialog = new ContactDialog(this) {
                @Override
                public void sendToMain(Contact contact) {
                    // Them vao db truoc de co id
                    long lastId = contactDAO.insert(contact);
                    // Sau do them vao list
                    contact.setId(lastId);

                    dataSource.add(contact);
                    myAdapter.notifyDataSetChanged();
                }
            };
        }
        contactDialog.show();
    }
}