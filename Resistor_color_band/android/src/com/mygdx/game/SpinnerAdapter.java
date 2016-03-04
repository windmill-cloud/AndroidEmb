package com.mygdx.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by xuanwang on 3/3/16.
 */
public class SpinnerAdapter extends BaseAdapter
{
    ArrayList<Integer> colors;
    Context context;
    private int ringIdx;

    public SpinnerAdapter(Context context, int ringNum)
    {
        this.context=context;
        ringIdx = ringNum;
        colors=new ArrayList<Integer>();
        int retrieve []=context.getResources().getIntArray(R.array.androidcolors);
        if (ringNum == 1 || ringNum == 2 || ringNum == 3){
            for(int i = 0; i < 10; i++){
                colors.add(retrieve[i]);
            }
        }
        else if(ringNum == 4){
            for(int i = 0; i < 8; i++){
                colors.add(retrieve[i]);
            }
            colors.add(retrieve[10]);
            colors.add(retrieve[11]);
        }
        else if(ringNum == 5){
            colors.add(retrieve[10]);
            colors.add(retrieve[11]);

            colors.add(retrieve[0]);

            colors.add(retrieve[1]);
            colors.add(retrieve[2]);
            colors.add(retrieve[5]);
            colors.add(retrieve[6]);
            colors.add(retrieve[7]);

        }
        else if(ringNum == 6){
            colors.add(retrieve[1]);
            colors.add(retrieve[2]);
            colors.add(retrieve[3]);
            colors.add(retrieve[4]);
        }
    }
    @Override
    public int getCount()
    {
        return colors.size();
    }
    @Override
    public Object getItem(int arg0)
    {
        return colors.get(arg0);
    }
    @Override
    public long getItemId(int arg0)
    {
        return arg0;
    }
    @Override
    public View getView(int pos, View view, ViewGroup parent)
    {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        TextView txv=(TextView)view.findViewById(android.R.id.text1);
        txv.setBackgroundColor(colors.get(pos));
        txv.setTextSize(16f);
        if((ringIdx == 4 && pos == 8) || (ringIdx == 5 && pos == 0)) {
            txv.setText("SILVER");
        }
        else if((ringIdx == 4 && pos == 9) || (ringIdx == 5 && pos == 1)) {
            txv.setText("GOLD");
        }
        else{
            txv.setText("");
        }

        return view;
    }

}
