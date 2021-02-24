package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public  class WordAdapter extends BaseAdapter {

    private String[] mWords;
    private String[] mSubWords;
    private String[] mmoney;
    private int[] mIcons;
    private String[] mspinner;
    private String[] morder;
    final String[] sp2 = {
            "0", "1","2","3","4","5","6"
    };
    public WordAdapter(String[] words, String[] subwords, int[] icons,String[] money,String[] spinner,String[] order) {
        mWords = words;
        mSubWords = subwords;
        mIcons = icons;
        mmoney = money;
        mspinner = spinner;
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
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView subTitle = (TextView) convertView.findViewById(R.id.info);
        TextView money = (TextView) convertView.findViewById(R.id.money);
        TextView order = (TextView) convertView.findViewById(R.id.order);
//        Spinner spinner = (Spinner)convertView.findViewById(R.id.spinner);
        // 取出文字
        String text = (String) getItem(position);
        String subText = mSubWords[position];
        String money_1 = mmoney[position];
        String order_1 = morder[position];

        // 將文字內容設定給TextView
        title.setText(text);
        subTitle.setText(subText);
        money.setText("$ "+money_1);
        order.setText(order_1);




        // 找到ImageView
        ImageView icon = (ImageView) convertView.findViewById(R.id.img);
        // 依照位置算出對應的圖片
        int resId = mIcons[position % mIcons.length];
        // 將圖片設定給ImageView
        icon.setImageResource(resId);

        // 一定要將convertView回傳，供ListView呈現使用，並加入重用機制中
        return convertView;
    }

}