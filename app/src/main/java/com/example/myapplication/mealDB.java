package com.example.myapplication;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class mealDB {
    //建立SQLiteDatabase物件
    private static SQLiteDatabase db=null;

    private final static String TABLE_NAME="Tablemeal";
    private final static String _ID="_id";
    private final static String TITLES="title";
    private final static String MONEY="money";
    private final static String SUBTITLE="subtitle";
    private final static String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+_ID+" INTEGER PRIMARY KEY,"+TITLES+" VARCHAR(20),"+MONEY+" VARCHAR(20) ,"+SUBTITLE+" VARCHAR(20)"+" )";
    private Context context;

    //    mealDB
    public mealDB(Context context){
        this.context=context;
    }
    //     建立open()方法，資料庫存執行開啟資料庫，尚未存在則建立資料庫
    public void open() throws SQLException {
        try{
            //        建立資料庫並指定權限
            db=context.openOrCreateDatabase("mealDB.db",Context.MODE_PRIVATE,null);
            //        建立表格
            db.execSQL(CREATE_TABLE);
        }
        catch (Exception e){
            Log.d("Debug","mealDB.db 已建立");
        }

    }
    //    建立新增、修改(更新)、刪除，資料操作
    //    execSQL完整輸入SQL語法實現，資料操作
    //    建立方法append()
    public void append(String tit,String mon,String sub){
        String insert_text="INSERT INTO "+TABLE_NAME+"( "+TITLES+","+MONEY+","+SUBTITLE+") values ('"+tit+"','"+mon+"','"+sub+"')";
        db.execSQL(insert_text);

    }
    //    建立方法update()
    public void update(int name,int price,long id){
        String update_text="UPDATE "+TABLE_NAME+" SET "+TITLES+"="+name+","+MONEY+"="+price+" WHERE "+_ID+"="+id;
        db.execSQL(update_text);
    }
    //    建立方法delete()
    public void delete(long id){
        String delete_text="DELETE FROM "+TABLE_NAME+" WHERE "+_ID+"="+id;
        db.execSQL(delete_text);
    }
    //    建立查詢方法select()，查詢單筆資料
    //    rawQuery完整輸入SQL語法實現資料查詢
    public Cursor select(long id){
        String select_text="SELECT * FROM "+TABLE_NAME+" WHERE "+_ID+"="+id;
        Cursor cursor=db.rawQuery(select_text,null);
        return cursor;
    }
    //    建立查詢方法select_all()，查詢所有資料
    //    rawQuery完整輸入SQL語法實現資料查詢
    public Cursor select_all(){
        String select_text="SELECT * FROM "+TABLE_NAME;
        Cursor cursor=db.rawQuery(select_text,null);
        return cursor;
    }
    public Cursor select_last(){
        String select_text="SELECT * FROM "+TABLE_NAME+" ORDER BY "+_ID+" DESC LIMIT 0 , 1";
        Cursor cursor=db.rawQuery(select_text,null);
        return cursor;
    }
}
