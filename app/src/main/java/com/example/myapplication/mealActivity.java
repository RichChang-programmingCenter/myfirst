package com.example.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class mealActivity extends AppCompatActivity {
    private mealDB db;
    private Cursor cursor;
    private ListView pressureList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        setTitle("訂餐APP");
        // 尋找各個元件的ID
        findViews();
        db=new mealDB(mealActivity.this);
        db.open();
        cursor=db.select_all();
        // 更新ListView重新顯示資料
        UpdateListView(cursor);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //        尋找各個元件的ID
        findViews();
        db=new mealDB(mealActivity.this);
        db.open();
        cursor=db.select_all();
        UpdateListView(cursor);
    }

    private void findViews() {
        pressureList=(ListView)findViewById(R.id.pressureList);
        pressureList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                try{
                    cursor.moveToPosition(position);
                    final int _id=Integer.parseInt(cursor.getString(0));
                    String tit=cursor.getString(1);
                    String money=cursor.getString(2);
                    String sub=cursor.getString(3);
                    new AlertDialog.Builder(mealActivity.this)
                            .setMessage("確定刪除\nID="+id+"\n餐點名稱:"
                                    +tit+"\n金額:"+money+"\n介紹:"+sub)
                            .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //執行刪除動作
                                    db.delete(cursor.getInt(0));
                                    //更新顯示畫面
                                    UpdateListView(cursor=db.select_all());
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            })
                            .show();
                }catch (Exception e){
                    Toast.makeText(mealActivity.this,"刪除失敗",Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(mealActivity.this, mealCreateActivity.class);
                startActivity(intent);
            }
        });

    }
    public void UpdateListView(Cursor pressurecursor){
        MyAdapter adapter =new MyAdapter(pressurecursor);
        pressureList.setAdapter(adapter);
        cursor=pressurecursor;
    }

    public class MyAdapter extends BaseAdapter {
        private Cursor cursor;
        public MyAdapter(Cursor cursor){
            this.cursor=cursor;
        }
        @Override
        public int getCount() {
            return cursor.getCount();
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            cursor.moveToPosition(position);
            return cursor.getInt(0);
        }
        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View getview=view;
            cursor.moveToPosition(position);
            getview=getLayoutInflater().inflate(R.layout.meal_view,null);
            TextView txtDate=(TextView)getview.findViewById(R.id.txtDate);
            txtDate.setText(cursor.getString(0));
            TextView txtHigh=(TextView)getview.findViewById(R.id.txtHigh);
            txtHigh.setText("餐點名稱:"+cursor.getString(1));
            TextView txtBump=(TextView)getview.findViewById(R.id.txtBump);
            txtBump.setText(String.valueOf(cursor.getString(2)));
            TextView txtLow=(TextView)getview.findViewById(R.id.txtLow);
            txtLow.setText("介紹:"+String.valueOf(cursor.getString(3)));

            return getview;
        }
    }
}
