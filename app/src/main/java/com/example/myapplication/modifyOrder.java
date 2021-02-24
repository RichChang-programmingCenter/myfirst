package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class modifyOrder extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_order);
        Button new_class = (Button)findViewById(R.id.new_class);
        Button new_meal = (Button)findViewById(R.id.new_meal);
        new_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(modifyOrder.this);
                View vv = getLayoutInflater().inflate(R.layout.modify_order_class,null);
                alertDialog.setView(vv);
                EditText order_class=(EditText)vv.findViewById(R.id.editTextTextPersonName2);

                TextView tv = (TextView)findViewById(R.id.test);
                alertDialog.setTitle("請輸入欲新建的類別");
                alertDialog.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str=order_class.getText().toString().trim();
                        if(str.length()==0){
                            AlertDialog.Builder alertDialogtwo = new AlertDialog.Builder(modifyOrder.this);
                            alertDialogtwo.setTitle("類別不能為空!");
                            alertDialogtwo.setPositiveButton("確定!", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog1, int which2) {
                                }
                            });
                            alertDialogtwo.show();
                        }
                        else{tv.setText(str);
                        }
                    }
                });
                alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        new_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(modifyOrder.this , login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意本行的FLAG設定
                startActivity(intent);
            }
        });


    }
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    }

