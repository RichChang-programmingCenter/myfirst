package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public  class WordAdapter_1 extends BaseAdapter {

    private String[] mWords;
    private String[] mSubWords;
    private String[] mmoney;
    private String[] mtextfield;
    private String[] morder;

    public WordAdapter_1(String[] words, String[] subwords, String[] money, String[] textfield, String[] order) {
        mWords = words;
        mSubWords = subwords;
        mmoney = money;
        mtextfield = textfield;
        morder = order;
    }

    @Override
    public int getCount() {
        return mWords.length;
    }

    @Override
    public Object getItem(int position) {
        return mWords[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 檢查convertView是否有值，有值表示是重複使用的
        if (convertView == null) {
            // 沒有值就要自己建立一個
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_1, null);
        }

        // 找到TextView
        TextView title = (TextView) convertView.findViewById(R.id.date);
        TextView subTitle = (TextView) convertView.findViewById(R.id.order_meal);
        TextView order = (TextView) convertView.findViewById(R.id.confirm);
        TextView money = (TextView) convertView.findViewById(R.id.spend);
        TextView textfield = (TextView) convertView.findViewById(R.id.textfield);
//        Spinner spinner = (Spinner)convertView.findViewById(R.id.spinner);
        // 取出文字
        String text = (String) getItem(position);
        String subText = mSubWords[position];
        String money_1 = mmoney[position];
        String order_1 = morder[position];
        String textfield_1 = mtextfield[position];

        // 將文字內容設定給TextView
        title.setText(text);
        subTitle.setText(subText);
        money.setText(money_1);
        order.setText(order_1);
        textfield.setText(textfield_1);


        return convertView;
    }

}