package com.example.thigiuakydidong;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, email, contact, address;
    Button insert, update, delete, view;
    DBConnect dbConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.txt_name);
        email = findViewById(R.id.txt_email);
        contact = findViewById(R.id.txt_lienhe);
        address = findViewById(R.id.txt_diachi);

        insert = findViewById(R.id.btn_add);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);
        view = findViewById(R.id.btn_view);
        dbConnect = new DBConnect(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String contactTXT = contact.getText().toString();
                String addressTXT = address.getText().toString();

                Boolean checkinsertdata = dbConnect.insserusertdata(nameTXT, emailTXT, contactTXT, addressTXT);
                if(checkinsertdata==true)
                {
                    Toast.makeText(MainActivity.this, "dữ liệu đã được điền", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "điền dũ liệu vào", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String contactTXT = contact.getText().toString();
                String addressTXT = address.getText().toString();

                Boolean checkupdatetdata = dbConnect.updateusertdata(nameTXT, emailTXT, contactTXT, addressTXT);
                if(checkupdatetdata==true)
                {
                    Toast.makeText(MainActivity.this, "đã update", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "chưa được update", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = dbConnect.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "đã xóa", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "chưa xóa", Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbConnect.getdata();
                if(cursor.getCount()==0){
                    Toast.makeText(MainActivity.this, "không tồn tại", Toast.LENGTH_SHORT).show();

                    return;
                }
                StringBuffer stringBuffer = new StringBuffer();
                while(cursor.moveToNext()){
                    stringBuffer.append("Name :"+cursor.getString(0)+"\n");
                    stringBuffer.append("Email :"+cursor.getString(1)+"\n");
                    stringBuffer.append("Contact :"+cursor.getString(2)+"\n\n");
                    stringBuffer.append("Address :"+cursor.getString(3)+"\n\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(stringBuffer.toString());
                builder.show();
            }
        });
    }
}