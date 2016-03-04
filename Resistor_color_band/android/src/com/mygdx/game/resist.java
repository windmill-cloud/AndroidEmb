package com.mygdx.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class resist extends AndroidApplication implements MyGdxGame.MyGameCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_resist);
//        final MyGdxGame game1= new MyGdxGame();
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        final MyGdxGame game1 = new MyGdxGame();
        initialize(game1, config);
        game1.setMyGameCallback(this);
        final View gameView = initializeForView(game1, config);


        setContentView(R.layout.activity_resist);

        Button calColor = (Button) findViewById(R.id.btn3D);
        final Spinner tor=(Spinner) findViewById(R.id.tolerance);
        final Spinner tc=(Spinner) findViewById(R.id.tc);
        final Spinner CBT=(Spinner) findViewById(R.id.CBT);

        calColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resistValue = (EditText) findViewById(R.id.resistValue);
                double resist = Double.parseDouble(resistValue.getText().toString());
                String torStr=tor.getSelectedItem().toString();
                String tcStr=tc.getSelectedItem().toString();
                String cbtStr=CBT.getSelectedItem().toString();
                int[] flag1=getttc(torStr,tcStr,cbtStr);
               final int[] color=ResCal(resist,flag1);
                Log.d("INFO: \t", "tor string " + torStr);
                Log.d("INFO: \t", "tc string " + tcStr);
                Log.d("INFO: \t", "cbt string " + cbtStr);

                Log.d("INFO: \t", "flag int " + Arrays.toString(flag1));

                Log.d("INFO: \t", "color int " + Arrays.toString(color));


                Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            game1.pressed();
                            game1.addColor(color);
                        }
                    });
                setContentView(gameView);
//
//                }
            }

        });
    }
    @Override
    public void onStartActivityA() {
        Intent intent = new Intent(this, resist.class);
        startActivity(intent);
    }

    public static int[] ResCal(double rss, int[] flag) {
        // double rss1=rss*100;
        // int rss2=(int)rss1;
        int rss2=(int)(rss*1000.0);

        int rss3=0;
        int[] color = new int[7];
        Arrays.fill(color, -9);
        System.out.printf("rss2 is " + rss2 + "\n");

        if(rss2<0) {
            // Log.e("ERROR","Cannot negative"+"Number");
            System.out.printf("ERRORLOLOLOLOL", rss2);
        }

        if (rss2 == 0) {
            color[4]=0;
        }
        if(flag[2]==3) {
            if (rss2 > 0) {
                int i = 1;
                rss3 = rss2 / 10;
                while (rss3 >= 100) {
                    rss3 = rss3 / 10;
                    i = i+1;
                }
                color[0] = (rss3 / 10) % 10;
                color[1] = rss3 % 10;
                int o=i-3;
                color[3] = multiColor(o);
            }
        }

        if (flag[2]==4) {
            if (rss2 > 0) {
                int i = 1;
                rss3 = rss2/10;
                while (rss3 >= 100) {
                    rss3 = rss3 / 10;
                    i = i+1;
                    Log.d("INFO","i"+i);
                }
                color[0] = (rss3/10)%10;
                color[1] = rss3 % 10;
                int o=i-3;
                Log.d("INFO","o"+o);
                color[3] = multiColor(o);
                color[5]=tcColor(flag[1]);
            }
        }

        if(flag[2]==5) {
            if (rss2 > 0) {
                int i = 1;
                rss3 = rss2;
                while (rss3 >= 1000) {
                    rss3 = rss3 / 10;
                    i = i+1;
                }
                color[0] = (rss3 / 100) % 10;
                color[1] = (rss3/10)%10;
                color[2] = rss3 % 10;
                int o=i-3;
                color[3] = multiColor(o);
                color[5]=torColor(flag[0]);
            }
        }
        if(flag[2]==6) {
            if (rss2 > 0) {
                int i = 1;
                rss3 = rss2;
                while (rss3 >= 1000) {
                    rss3 = rss3 / 10;
                    i = i+1;
                }
                color[0] = (rss3 / 100) % 10;
                color[1] = (rss3/10)%10;
                color[2] = rss3 % 10;
                int o=i-3;
                color[3] = multiColor(o);
                color[5]=torColor(flag[0]);
                color[6]=tcColor(flag[1]);
            }
        }

        return color;
    }

    public static int[] getttc(String tor,String tc, String cbt) {
        int[] flagout=new int[3];

        if (tor.equals("10%")) {flagout[0]=6;}
        if (tor.equals("5%")) {flagout[0]=5;}
        if (tor.equals("2%")) {flagout[0]=4;}
        if (tor.equals("1%")) {flagout[0]=3;}
        if (tor.equals("0.5%")) {flagout[0]=2;}
        if (tor.equals("0.25%")) {flagout[0]=1;}
        if (tor.equals("0.1%")) {flagout[0]=0;}

        if (tc.equals("100ppm")) {flagout[1]=3;}
        if (tc.equals("50ppm")) {flagout[1]=2;}
        if (tc.equals("25ppm")) {flagout[1]=1;}
        if (tc.equals("15ppm")) {flagout[1]=0;}

        if(cbt.equals("6 Band")) {flagout[2]=6;}
        if(cbt.equals("5 Band")) {flagout[2]=5;}
        if(cbt.equals("4 Band")) {flagout[2]=4;}
        if(cbt.equals("3 Band")) {flagout[2]=3;}


        return flagout;


    }
    public static int torColor(int num) {
        switch (num){
            case 0: return 7;
            case 1: return 6;
            case 2: return 5;
            case 3: return 2;
            case 4: return 1;
            case 5: return 10;
            case 6: return 11;
            default: return 99;
        }
    }
    private static int tcColor(int num) {
        switch (num){
            case 0: return 3;
            case 1: return 4;
            case 2: return 2;
            case 3: return 1;
            default: return 99;
        }
    }
    public static int multiColor(int n) {
        switch (n){
            case -2: return 11;
            case -1: return 10;
            case 1: return 0;
            case 2: return 1;
            case 3: return 2;
            case 4: return 3;
            case 5: return 4;
            case 6: return 5;
            case 7: return 6;
            case 8: return 7;
            default: return 99;
        }
    }

}

