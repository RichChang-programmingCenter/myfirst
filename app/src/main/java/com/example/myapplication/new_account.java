package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class new_account extends AppCompatActivity {
    private profileDB db;
    private Cursor cursor;
    private ListView pressureList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        setTitle("訂餐APP");
        // 尋找各個元件的ID
        findViews();
        db=new profileDB(new_account.this);
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
        db=new profileDB(new_account.this);
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
                    String name=cursor.getString(3);
                    String acc=cursor.getString(1);
                    String pass=cursor.getString(2);
                    new AlertDialog.Builder(new_account.this)
                            .setMessage("確定刪除\nID="+id+"\n帳號名稱:"
                                    +name+"\n帳號:"+acc+"\n密碼:"+pass)
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
                    Toast.makeText(new_account.this,"刪除失敗",Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(new_account.this , register.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意本行的FLAG設定
                startActivity(intent);
            }
        });

    }
    public void UpdateListView(Cursor pressurecursor){
        new_account.MyAdapter adapter = new new_account.MyAdapter(pressurecursor);
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
            getview=getLayoutInflater().inflate(R.layout.account_view,null);

            TextView txtHigh=(TextView)getview.findViewById(R.id.textView24);
            txtHigh.setText("用戶名稱:"+cursor.getString(3));
            TextView txtBump=(TextView)getview.findViewById(R.id.textView25);
            txtBump.setText("帳號:"+cursor.getString(1));
            TextView txtLow=(TextView)getview.findViewById(R.id.textView26);
            txtLow.setText("密碼:"+String.valueOf(cursor.getString(2)));

            return getview;
        }
    }
}