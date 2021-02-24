package com.example.myapplication;


import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    private profileDB db = new profileDB(register.this);
    private Button b_new,profile_a;
    private EditText acc,pass,pass_two,name;
    private TextView test,acc_t,pass_t,name_t;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        db.open();
    }

    private void findViews() {
        b_new = (Button) findViewById(R.id.b_new);
        profile_a = (Button) findViewById(R.id.profiea);
        acc = (EditText) findViewById(R.id.acc);
        pass = (EditText) findViewById(R.id.pass);
        pass_two = (EditText) findViewById(R.id.pass_two);
        name = (EditText) findViewById(R.id.name);
        test = (TextView) findViewById(R.id.textView21);
        acc_t = (TextView) findViewById(R.id.acc_t);
        pass_t = (TextView) findViewById(R.id.pass_t);
        name_t= (TextView) findViewById(R.id.name_t);
        b_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(acc.getText().toString().length() == 0|| name.getText().toString().length() == 0 ||pass.getText().toString().length() == 0 || pass_two.getText().toString().length() == 0) {
                    test.setText("字串空!");
                }
                else {
                    if (pass.getText().toString().equals(pass_two.getText().toString())) {
                        //丟進DB
                        db.append(acc.getText().toString(), pass.getText().toString(), name.getText().toString());
                        test.setText("succ!");
                    }
                    else test.setText("false!");
                }
                //finish();
            }
        });
        profile_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.open();
                cursor=db.select_last();
                cursor.moveToPosition(0);
                acc_t.setText(cursor.getString(1));
                pass_t.setText(cursor.getString(2));
                name_t.setText(cursor.getString(3));

            }
        });
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
