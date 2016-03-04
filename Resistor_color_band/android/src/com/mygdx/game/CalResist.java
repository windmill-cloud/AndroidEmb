package com.mygdx.game;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Benn on 2/26/16.
 */
public class CalResist {
    public void CalResist(double resistance) {
        res = resistance;
    }

    double res;

    public static int[] main(double rss) {
        int rss2=(int)rss*100;
        int rss3=0;
        int[] color = new int[4];
        Arrays.fill(color,-9);
        if(rss2<0) {
            Log.e("ERROR","Cannot negative"+"Number");
        }

        if (rss2 == 0) {
            Arrays.fill(color,-8);
        }
        if(rss2 > 0 ) {
            int i=0;
            rss3=rss2;
        while (rss3 > 100) {
            rss3=rss3/10;
            i=i++;
        }
            color[0]=rss3/100;
            color[1]=(rss3/10)%10;
            color[2]=rss3%10;
        }
        return color;
    }

}

