package com.huzan.hello2048.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by huzan on 16/3/23.
 */
public class FigureView extends FrameLayout {

    int num = 0;
    private TextView label;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if(num == 0){
            label.setText("");
            label.setBackgroundColor(0x22ffffff);
        }else {
            label.setText(num + "");
            label.setTextColor(Color.WHITE);
            if (num==2){
                label.setBackgroundColor(0xffeee4da);
            }else if (num==4){
                label.setBackgroundColor(0xffede0c8);
            }else if (num==8){
                label.setBackgroundColor(0xfff2b179);
            }else if (num==16){
                label.setBackgroundColor(0xfff59563);
            }else if (num==32){
                label.setBackgroundColor(0xfff67c5f);
            }else if (num==64){
                label.setBackgroundColor(0xfff65e3b);
            }else if (num==128){
                label.setBackgroundColor(0xffedcf72);
            }else if (num==256){
                label.setBackgroundColor(0xffedcc61);
            }else if (num == 512){
                label.setBackgroundColor(0xffedc850);
            }else if (num == 1024){
                label.setBackgroundColor(0xffedc53f);
            }else if (num == 2048){
                label.setBackgroundColor(0xffedc22e);
            }
        }

    }

    public FigureView(Context context) {
        super(context);
        label = new TextView(getContext());
        label.setTextSize(32);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.setMargins(10,10,0,0);
        addView(label, layoutParams);
//        label.setBackgroundColor(0x77ffffff);
        label.setGravity(Gravity.CENTER);



        setNum(0);
    }

    public boolean equals(FigureView o) {
        return getNum() == o.getNum();   //判断是不是一样的牌   (不是很理解做法)
    }
}
