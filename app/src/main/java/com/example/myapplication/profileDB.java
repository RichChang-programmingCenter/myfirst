package com.example.myapplication;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class profileDB {
    //建立SQLiteDatabase物件
    private static SQLiteDatabase db=null;

    private final static String TABLE_NAME="Tableprofile";
    private final static String _ID="_id";
    private final static String ACC="acc";
    private final static String PASS="pass";
    private final static String NAME="name";
    private final static String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+_ID+" INTEGER PRIMARY KEY,"+ACC+" VARCHAR(20),"+PASS+" VARCHAR(20) ,"+NAME+" VARCHAR(20)"+" )";
    private Context context;

    //    mealDB
    public profileDB(Context context){
        this.context=context;
    }
    //     建立open()方法，資料庫存執行開啟資料庫，尚未存在則建立資料庫
    public void open() throws SQLException {
        try{
            //        建立資料庫並指定權限
            db=context.openOrCreateDatabase("profileDB.db",Context.MODE_PRIVATE,null);
            //        建立表格
            db.execSQL(CREATE_TABLE);
        }
        catch (Exception e){
            Log.d("Debug","profileDB.db 已建立");
        }

    }
    //    建立新增、修改(更新)、刪除，資料操作
    //    execSQL完整輸入SQL語法實現，資料操作
    //    建立方法append()
    public void append(String acc,String pass,String name){
        String insert_text="INSERT INTO "+TABLE_NAME+"( "+ACC+","+PASS+","+NAME+") values ('"+acc+"','"+pass+"','"+name+"')";
        db.execSQL(insert_text);

    }
    //    建立方法update()
    public void update(int name,int price,long id){
        String update_text="UPDATE "+TABLE_NAME+" SET "+ACC+"="+name+","+PASS+"="+price+" WHERE "+_ID+"="+id;
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
    public int comparsion(String acc,String pass){
        String select_text="SELECT * FROM "+TABLE_NAME+" WHERE "+ACC+"='"+acc+"'";
        Cursor cursor=db.rawQuery(select_text,null);
        cursor.moveToPosition(0);
        if(pass.equals(cursor.getString(2))){
            return 0;
        }
        else return 1;
    }
}
