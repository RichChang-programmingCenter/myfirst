package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class order extends AppCompatActivity {
    int expensive=0;
    boolean order_key=false;
    boolean date_key=false;
    //-----------假裝有sql
    private static final String[] TITLES = {
            "燒臘便當", "三菜一飯","漁具的ㄐㄐ","油雞便當","燒肉便當"
    };
    private static final String[] SUB_TITLES = {
        "好吃的燒臘便當", "好吃的三菜一飯","好吃的漁具的ㄐㄐ","好吃的油雞便當","好吃的燒肉便當"
    };
    private static final int ICONS[] = {
        R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher
    };
    private static final String[] money = {
            "85", "50","1","85","85"
    };
    private static final String[] spinner = {
            "0", "1","2","3","4","5","6"
    };
    private static final String[] order = {
            "","","","","","",""
    };
    //-------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        for(int i=0;i<order.length;i++)
            order[i]="";
         //-----------------------------基本創造介面舞台
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //--------------------
        //建立Adapter，並將要顯示的結果陣列傳入
        WordAdapter adapter = new WordAdapter(TITLES, SUB_TITLES, ICONS,money,spinner,order);
        //將Adapter設定給ListView
        expensive=0;
        ListView list = (ListView) findViewById(R.id.listview);
        TextView price =(TextView)findViewById(R.id.price);
        setbutton_buy(date_key,order_key);
        price.setText("金額 :"+expensive);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {//點擊list
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder mbulider = new AlertDialog.Builder(order.this);
                View mview = getLayoutInflater().inflate(R.layout.dialogspinner,null);
                mbulider.setTitle("請選擇數量!");
                Spinner mspinner = (Spinner) mview.findViewById(R.id.spinner_1);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(order.this
                        , android.R.layout.simple_spinner_item
                        ,getResources().getStringArray(R.array.value));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mspinner.setAdapter(adapter);
                mbulider.setPositiveButton("確認!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!mspinner.getSelectedItem().toString().equalsIgnoreCase("請選擇要訂購的數量..")){
                            order_key=true;
                            setbutton_buy(date_key,order_key);
                            order[i]=mspinner.getSelectedItem().toString();
                            Toast.makeText(order.this,mspinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            WordAdapter adapter2 = new WordAdapter(TITLES, SUB_TITLES, ICONS,money,spinner,order);
                            list.setAdapter(adapter2);
                            expensive=0;
                            for(int i=0;i<order.length;i++){
                                if(!order[i].equals(""))
                                expensive+=Integer.parseInt(order[i])*Integer.parseInt(money[i].toString());
                            }
                            price.setText("金額 :"+expensive);
                        }
                    }
                });
                mbulider.setNegativeButton("取消!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mbulider.setView(mview);
                AlertDialog dialog = mbulider.create();
                dialog.show();
            }
        });
        Button button5 = (Button)findViewById(R.id.button5);//返回
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(order.this  , MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意本行的FLAG設定
                startActivity(intent);
            }
        });
        Button button4 = (Button)findViewById(R.id.button4);//選日期
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(order.this);
            }
        });
        Button button_buy = (Button)findViewById(R.id.buy);
        button_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView date =(TextView)findViewById(R.id.date_1);
                String my_order="取餐時間"+date.getText()+"\n";
                AlertDialog.Builder mbulider = new AlertDialog.Builder(order.this);
                View mview = getLayoutInflater().inflate(R.layout.order_meal,null);
                mbulider.setTitle("請確認數量!");
                TextView order_meal =(TextView)mview.findViewById(R.id.meal);
                for(int i=0;i<ICONS.length;i++){
                    if(!order[i].equals("")){
                        my_order+=TITLES[i];
                        my_order+="  共  ";
                        my_order+=Integer.parseInt(order[i]);
                        my_order+=" 個\n";
                    }
                }
                my_order+="----------------------\n";
                my_order+="總價格為 :"+expensive;
                order_meal.setText(my_order);
                mbulider.setPositiveButton("確認!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setClass(order.this  , MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //注意本行的FLAG設定
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                mbulider.setNegativeButton("取消!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mbulider.setView(mview);
                AlertDialog dialog = mbulider.create();
                dialog.show();
            }
        });
    }
//--------------------------
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
    public void setbutton_buy(boolean order_key,boolean date_key){
        Button button_buy = (Button)findViewById(R.id.buy);
        if (order_key&&date_key) {
            button_buy.setEnabled(true);
            button_buy.setBackgroundTintList(getResources().getColorStateList(R.color.yellow));
        }else{
            button_buy.setEnabled(false);
            button_buy.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
        }
    }
    public void showDatePickerDialog(Context context) {
        TextView date =(TextView)findViewById(R.id.date_1);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            final Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(context);
            dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(year, month, dayOfMonth);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
             date.setText("你選擇了 : "+year + "-" + (month + 1) + "-" + dayOfMonth);
             date_key=true;
             setbutton_buy(date_key,order_key);
                }
            });
            DatePicker datePicker = dialog.getDatePicker();
            calendar.add(Calendar.DATE, 1);
            datePicker.setMinDate(calendar.getTimeInMillis());
            calendar.add(Calendar.DATE, 4);
            datePicker.setMaxDate(calendar.getTimeInMillis());
            dialog.show();
        }
    }
}
