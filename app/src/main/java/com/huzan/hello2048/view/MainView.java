package com.huzan.hello2048.view;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huzan on 16/3/23.
 */
public class MainView extends GridLayout {

    private FigureView[][] figureMap = new FigureView[4][4];
    FigureView[][] a = new FigureView[4][4];
    FigureView[][] b = new FigureView[4][4];

    public MainView(Context context) {
        super(context);
        initView();
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
        setOnTouchListener(new OnTouchListener() {

            private float startx, starty, x, y;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startx = event.getX();
                        starty = event.getY();

                        break;
                    case MotionEvent.ACTION_UP:
                        x = event.getX() - startx;
                        y = event.getY() - starty;

                        // 判断是上下还是左右滑动
                        if (Math.abs(x) > Math.abs(y)) {

                            if (x < -5) {
                                leftUpdate();
                                
                            } else if (x > 5) {
                                rightUpdate();

                            }
                        } else {

                            if (y < -5) {
                                upUpdate();
                            } else if (y > 5) {
                                downUpdate();
                            }


                        }

                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int figureWidth = (Math.min(w, h) - 10) / 4;
        addFigure(figureWidth, figureWidth);
        start();

    }

    private void addFigure(int figureWidth, int figureHeigh) {
        FigureView figureView;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                figureView = new FigureView(getContext());
                figureView.setNum(2);
                figureMap[x][y] = figureView;
                addView(figureView, figureWidth, figureHeigh);
            }
        }
    }

    private void cleanB() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {

                FigureView figureView1 = new FigureView(getContext());
                figureView1.setNum(0);
                b[x][y] = figureView1;

            }
        }
    }

    private List<Point> list = new ArrayList<Point>();  //空点

    //生成随机
    private void addRandomNum() {
        list.clear();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (figureMap[x][y].getNum() <= 0) {
                    list.add(new Point(x, y));
                }
            }
        }
        Point p = list.remove((int) (Math.random() * list.size()));  //随机取点
        figureMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4); // 添加随机数字 2:4=9:1

    }


    //开始游戏
    private void start() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                figureMap[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();

    }

    private void left() {
        boolean isRandom = false;  //是否添加随机数
        cir:
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4 && x >= 0; x++) {

                for (int x1 = x + 1; x1 < 4; x1++) {
                    if (figureMap[x1][y].getNum() > 0) {
                        if (figureMap[x][y].getNum() == 0) {
                            figureMap[x][y].setNum(figureMap[x1][y].getNum());
                            figureMap[x1][y].setNum(0);
                            x--;
                            isRandom = true;
                            break;

                        } else if (figureMap[x][y].equals(figureMap[x1][y])) {
                            figureMap[x][y].setNum(figureMap[x][y].getNum() * 2);
                            figureMap[x1][y].setNum(0);
                            isRandom = true;
                            break;
                        }
//                        break cir;
                    }
                }
            }
        }
        if (isRandom) {
            addRandomNum();
        }

    }

    private void right() {
        boolean isRandom = false;  //是否添加随机数
        cir:
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {

                for (int x1 = x - 1; x1 >= 0; x1--) {

                    if (figureMap[x1][y].getNum() > 0) {
                        if (figureMap[x][y].getNum() == 0) {
                            figureMap[x][y].setNum(figureMap[x1][y].getNum());
                            figureMap[x1][y].setNum(0);
                            x++;
                            isRandom = true;
                            break;
                        } else if (figureMap[x][y].equals(figureMap[x1][y])) {
                            figureMap[x][y].setNum(figureMap[x1][y].getNum() * 2);
                            figureMap[x1][y].setNum(0);
                            isRandom = true;
                            break;
                        }

//                        break cir;
                    }
                }
            }
        }
        if (isRandom) {
            addRandomNum();
        }
    }

    private void up() {
        boolean isRandom = false;  //是否添加随机数
        cir:
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {

                for (int y1 = y + 1; y1 < 4; y1++) {
                    if (figureMap[x][y1].getNum() > 0) {
                        if (figureMap[x][y].getNum() == 0) {
                            figureMap[x][y].setNum(figureMap[x][y1].getNum());
                            figureMap[x][y1].setNum(0);
                            y--;
                            isRandom = true;
                            break;
                        } else if (figureMap[x][y].equals(figureMap[x][y1])) {
                            figureMap[x][y].setNum(figureMap[x][y].getNum() * 2);
                            figureMap[x][y1].setNum(0);
                            isRandom = true;
                            break;
                        }
//                        break cir;
                    }
                }
            }
        }
        if (isRandom) {
            addRandomNum();
        }
    }

    private void down() {
        boolean isRandom = false;  //是否添加随机数
        cir:
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {

                for (int y1 = y - 1; y1 >= 0; y1--) {

                    if (figureMap[x][y1].getNum() > 0) {
                        if (figureMap[x][y].getNum() == 0) {
                            figureMap[x][y].setNum(figureMap[x][y1].getNum());
                            figureMap[x][y1].setNum(0);
                            y++;
                            isRandom = true;
                            break;
                        } else if (figureMap[x][y].equals(figureMap[x][y1])) {
                            figureMap[x][y].setNum(figureMap[x][y1].getNum() * 2);
                            figureMap[x][y1].setNum(0);
                            isRandom = true;
                            break;
                        }

//                        break cir;
                    }
                }
            }
        }
        if (isRandom) {
            addRandomNum();
        }
    }



    private void leftUpdate() {
        cleanB();
        boolean isRandom = false;  //是否添加随机数

        for (int y = 0; y < 4; y++) {
            int dex = 0;
            for (int x = 0; x < 4; x++) {
                if (figureMap[x][y].getNum() != 0) {
                    if (b[dex][y].getNum() == figureMap[x][y].getNum()) {
                        b[dex][y].setNum( figureMap[x][y].getNum() * 2);
                        dex += 1;
                        isRandom =true;
                    } else {
                        if (b[dex][y].getNum() == 0) {
                            b[dex][y].setNum(figureMap[x][y].getNum());
                            isRandom =true;
                        } else {

                            dex += 1;
                            b[dex][y].setNum(figureMap[x][y].getNum());
                            isRandom =true;

                        }

                    }

                }
            }
        }
        change();
//        if (isRandom) {
//            addRandomNum();
//        }
    }
   private void rightUpdate() {
       cleanB();
       boolean isRandom = false;  //是否添加随机数

        for (int y = 0; y < 4; y++) {
            int dex = 3;
            for (int x = 3; x >= 0; x--) {
                if (figureMap[x][y].getNum() != 0) {
                    if (b[dex][y].getNum() == figureMap[x][y].getNum()) {
                        b[dex][y].setNum( figureMap[x][y].getNum() * 2);
                        dex -= 1;
                        isRandom =true;
                    } else {
                        if (b[dex][y].getNum() == 0) {
                            b[dex][y].setNum(figureMap[x][y].getNum());
                            isRandom =true;
                        } else {

                            dex -= 1;
                            b[dex][y].setNum(figureMap[x][y].getNum());
                            isRandom =true;

                        }

                    }

                }
            }
        }
       change();
//       if (isRandom) {
//           addRandomNum();
//       }
    }
    private void downUpdate() {
        cleanB();
        boolean isRandom = false;  //是否添加随机数
        for (int x = 0; x < 4; x++) {
            int dex = 3;
            for (int y = 3; y >= 0; y--) {
                if (figureMap[x][y].getNum() != 0) {
                    if (b[x][dex].getNum() == figureMap[x][y].getNum()) {
                        b[x][dex].setNum( figureMap[x][y].getNum() * 2);
                        dex -= 1;
                        isRandom =true;
                    } else {
                        if (b[x][dex].getNum() == 0) {
                            b[x][dex].setNum(figureMap[x][y].getNum());
                            isRandom =true;
                        } else {

                            dex -= 1;
                            b[x][dex].setNum(figureMap[x][y].getNum());
                            isRandom =true;

                        }

                    }

                }
            }
        }
        change();
//        if (isRandom) {
//            addRandomNum();
//        }
    }

    private void upUpdate() {
        cleanB();
        boolean isRandom = false;  //是否添加随机数

        for (int x = 0; x < 4; x++) {
            int dex = 0;
            for (int y = 0; y < 4; y++) {
                if (figureMap[x][y].getNum() != 0) {
                    if (b[x][dex].getNum() == figureMap[x][y].getNum()) {
                        b[x][dex].setNum( figureMap[x][y].getNum() * 2);
                        dex += 1;
                        isRandom =true;
                    } else {
                        if (b[x][dex].getNum() == 0) {
                            b[x][dex].setNum(figureMap[x][y].getNum());
                            isRandom =true;
                        } else {

                            dex += 1;
                            b[x][dex].setNum(figureMap[x][y].getNum());
                            isRandom =true;

                        }

                    }

                }
            }
        }
        change();
//        if (isRandom) {
//            addRandomNum();
//        }
    }

    private void change(){
        boolean isRandom = false;  //是否添加随机数
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (b[x][y].getNum()!=figureMap[x][y].getNum()){
                    isRandom = true;
                }
                figureMap[x][y].setNum(b[x][y].getNum());
            }
        }
        if (isRandom){
            addRandomNum();
        }
    }



}
