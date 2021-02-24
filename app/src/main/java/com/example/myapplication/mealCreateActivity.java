package com.example.myapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;

public class mealCreateActivity extends AppCompatActivity {
    private mealDB db = new mealDB(mealCreateActivity.this);
    private EditText titles, money, subtitles;
    private Button btnAppend;
    private Button btnselectpic;
    private ImageView imageview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_create);

        findViews();
        //  獲得目前時間
        //gettime();
    }

    private void findViews() {
        btnselectpic = (Button) findViewById(R.id.img);
        imageview = (ImageView) findViewById(R.id.imageView);
        titles = (EditText) findViewById(R.id.titles);
        money = (EditText) findViewById(R.id.money);
        subtitles = (EditText) findViewById(R.id.subtitles);
        btnAppend = (Button) findViewById(R.id.button);
        btnselectpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                //回傳時，要如何處理。請重新Override onActivityResult函式。
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
            }
        });
        btnAppend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.append(titles.getText().toString(), money.getText().toString(), subtitles.getText().toString());
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            //取得圖檔的路徑位置
            Uri uri = data.getData();
            //寫log
            Log.e("uri", uri.toString());
            //抽象資料的接口
            ContentResolver cr = this.getContentResolver();
            try {
                //由抽象資料接口轉換圖檔路徑為Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                //取得圖片控制項ImageView
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                // 將Bitmap設定到ImageView
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
