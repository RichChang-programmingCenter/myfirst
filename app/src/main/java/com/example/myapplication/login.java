package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    private profileDB db = new profileDB(login.this);
    private Cursor cursor;
    private EditText acc,pass;
    private TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button_6 = (Button)findViewById(R.id.button6);
        Button button_10 = (Button)findViewById(R.id.button10);
        acc = (EditText) findViewById(R.id.editTextTextPersonName);
        pass = (EditText) findViewById(R.id.editTextTextPassword);
        test = (TextView) findViewById(R.id.textView22);
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                RadioGroup portType = findViewById(R.id. portType );
                if(acc.getText().length()==0 ||pass.getText().length()==0)  {
                    AlertDialog.Builder alertDialog =
                            new AlertDialog.Builder(login.this);
                    alertDialog.setTitle("燈燈");
                    alertDialog.setMessage("請輸入帳號跟密碼");
                    alertDialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }
                else {
                    if (portType.getCheckedRadioButtonId() == R.id.student) {
                        intent.setClass(com.example.myapplication.login.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意本行的FLAG設定
                        startActivity(intent);
                    } else if (portType.getCheckedRadioButtonId() == R.id.retailer) {
                        db.open();
                        if (db.comparsion(acc.getText().toString(),pass.getText().toString())==0) {
                            intent.setClass(login.this, retail.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意本行的FLAG設定
                            startActivity(intent);
                        }
                        else {
                            AlertDialog.Builder alertDialog =
                                    new AlertDialog.Builder(login.this);
                            alertDialog.setTitle("燈燈");
                            alertDialog.setMessage("帳號密碼錯誤!");
                            alertDialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.setCancelable(false);
                            alertDialog.show();
                        }
                    }
                }
            }
        });

        button_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                    intent.setClass(login.this , register.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意本行的FLAG設定
                    startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
