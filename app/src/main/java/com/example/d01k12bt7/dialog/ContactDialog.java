package com.example.d01k12bt7.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.d01k12bt7.R;
import com.example.d01k12bt7.model.Contact;

public abstract class ContactDialog extends Dialog {
    public ContactDialog(@NonNull Context context) {
        super(context);
    }

    public abstract void sendToMain(Contact contact);

    // Bind id
    private Button btnSave, btnCancel;
    private EditText edtName, edtPhone, edtAddress;

    private void initUI(){
        btnCancel = findViewById(R.id.btnDialogCancel);
        btnSave = findViewById(R.id.btnDialogSave);
        edtAddress = findViewById(R.id.edtDialogAddress);
        edtName = findViewById(R.id.edtDialogName);
        edtPhone = findViewById(R.id.edtDialogPhone);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_contact);
        setCancelable(false);
        initUI();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();
                if(name.isEmpty()|| phone.isEmpty()||address.isEmpty()){
                    Toast.makeText(getContext(),"Không để trống",Toast.LENGTH_SHORT).show();
                    return;
                }
                Contact contact = new Contact(name,address,phone);
                sendToMain(contact);

                // Reset
                edtName.setText("");
                edtAddress.setText("");
                edtPhone.setText("");
                // An dialog
                dismiss();

            }
        });
    }
}
