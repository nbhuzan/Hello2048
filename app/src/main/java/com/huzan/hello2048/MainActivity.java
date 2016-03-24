package com.huzan.hello2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] a=new int[]{2,0,2,2};
        int[] b = new int[4];
        int dex=0;
        for (int x = 0;x<4;x++){
            if (a[x]!=0){
                if (b[dex]==a[x]){
                    b[dex]=a[x]*2;
                    dex+=1;
                }else {
                    if (b[dex]==0) {
                        b[dex] = a[x];
                    }else {

                                dex+=1;
                                b[dex]=a[x];

                    }


                }

            }
        }
        a=b;
        System.out.println(a);
    }
}
