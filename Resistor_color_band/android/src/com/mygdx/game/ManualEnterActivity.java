package com.mygdx.game;


        import android.app.Activity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemSelectedListener;
        import android.widget.Button;
        import android.widget.Spinner;
        import android.widget.Switch;
        import android.widget.TextView;

public class ManualEnterActivity extends Activity {

    public double code1, code2, code3, code4, code5, code6;
    public TextView r, t, c;
    private boolean s3on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_enter);

        final Button b1 = (Button) findViewById(R.id.band4);
        final Button b2 = (Button) findViewById(R.id.band5);
        final Button b3 = (Button) findViewById(R.id.band6);

        r = (TextView) findViewById(R.id.resistance);
        t = (TextView) findViewById(R.id.tolerance);
        c = (TextView) findViewById(R.id.tempCoefficient);


        final Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        s1.setAdapter(new SpinnerAdapter(this, 1));
        final Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        s2.setAdapter(new SpinnerAdapter(this, 2));
        final Spinner s3 = (Spinner) findViewById(R.id.spinner3);
        s3.setAdapter(new SpinnerAdapter(this, 3));
        final Spinner s4 = (Spinner) findViewById(R.id.spinner4);
        s4.setAdapter(new SpinnerAdapter(this, 4));
        final Spinner s5 = (Spinner) findViewById(R.id.spinner5);
        s5.setAdapter(new SpinnerAdapter(this, 5));
        final Spinner s6 = (Spinner) findViewById(R.id.spinner6);
        s6.setAdapter(new SpinnerAdapter(this, 6));

        final TextView t3 = (TextView) findViewById(R.id.textView_ring3);
        final TextView tc = (TextView) findViewById(R.id.textView_ring6);

        b1.setBackgroundColor(0xFF00BFFF);
        b2.setBackgroundColor(0xFFBFBFBF);
        b3.setBackgroundColor(0xFFBFBFBF);
        s3.setVisibility(View.INVISIBLE);
        s6.setVisibility(View.INVISIBLE);
        c.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        tc.setVisibility(View.INVISIBLE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackgroundColor(0xFF00BFFF);
                b2.setBackgroundColor(0xFFBFBFBF);
                b3.setBackgroundColor(0xFFBFBFBF);
                s3.setVisibility(View.INVISIBLE);
                s6.setVisibility(View.INVISIBLE);
                c.setVisibility(View.INVISIBLE);
                t3.setVisibility(View.INVISIBLE);
                tc.setVisibility(View.INVISIBLE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackgroundColor(0xFFBFBFBF);
                b2.setBackgroundColor(0xFF00BFFF);
                b3.setBackgroundColor(0xFFBFBFBF);
                s3.setVisibility(View.VISIBLE);
                if(s3on == true) {
                    s3on = false;
                }
                else {
                    s3on = true;
                }

                s6.setVisibility(View.INVISIBLE);
                c.setVisibility(View.INVISIBLE);
                t3.setVisibility(View.VISIBLE);
                tc.setVisibility(View.INVISIBLE);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackgroundColor(0xFFBFBFBF);
                b2.setBackgroundColor(0xFFBFBFBF);
                b3.setBackgroundColor(0xFF00BFBF);
                s3.setVisibility(View.VISIBLE);
                s6.setVisibility(View.VISIBLE);
                c.setVisibility(View.VISIBLE);
                t3.setVisibility(View.INVISIBLE);
                tc.setVisibility(View.INVISIBLE);
                t3.setVisibility(View.VISIBLE);
                tc.setVisibility(View.VISIBLE);
            }
        });

        s1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Code1", String.valueOf(position));
                //String text_ring1 = s1.getSelectedItem().toString();
                code1 = Double.valueOf(position);
                ManualEnterActivity.this.updateText();
                //Log.e("Code1", String.valueOf(code1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        s2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String text_ring2 = s2.getSelectedItem().toString();
                code2 = Double.valueOf(position);
                ManualEnterActivity.this.updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        s3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String text_ring3 = s3.getSelectedItem().toString();
                code3 = Double.valueOf(position);
                ManualEnterActivity.this.updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s4.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String text_ring4 = s4.getSelectedItem().toString();
                if(position <= 7){
                    code4 = position;
                }
                else if (position == 8){
                    code4 = -2;
                }
                else if(position == 9){
                    code4 = -1;
                }
                ManualEnterActivity.this.updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s5.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String text_ring5 = s5.getSelectedItem().toString();
                code5 = tolerance(position);
                ManualEnterActivity.this.updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s6.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String text_ring6 = s6.getSelectedItem().toString();
                code6 = tc(position);
                ManualEnterActivity.this.updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateText() {
        double resistance4;
        if(s3on){
            resistance4 = (100.0 * code1 + 10.0 * code2 + code3) * Math.pow(10.0, code4);
        }
        else {
            resistance4 = (10.0 * code1 + code2) * Math.pow(10.0, code4);
        }
        r.setText(String.valueOf(resistance4)+" Ohm");
        t.setText("   +/-"+ String.valueOf(code5)+"%");
        c.setText("   " + String.valueOf(code6)+"ppm");
    }

    private double decode(String text) {
        switch (text) {
            case "Silver":
                return -2.0;
            case "Gold":
                return -1.0;
            case "Black":
                return 0.0;
            case "Brown":
                return 1.0;
            case "Red":
                return 2.0;
            case "Orange":
                return 3.0;
            case "Yellow":
                return 4.0;
            case "Green":
                return 5.0;
            case "Blue":
                return 6.0;
            case "Purple":
                return 7.0;
            case "Grey":
                return 8.0;
            case "White":
                return 9.0;
            default:
                return -999.0;
        }
    }

    private double tolerance(int code) {
        switch (code) {
            case 0:
                return 10;
            case 1:
                return 5;
            case 2:
                return 1.0;
            case 3:
                return 2.0;
            case 4:
                return 0.5;
            case 5:
                return 0.25;
            case 6:
                return 0.1;
            case 7:
                return 0.05;
            default:
                return 0;
        }
    }

    private double tc(int code) {
        switch (code) {
            case 0:
                return 100;
            case 1:
                return 50;
            case 2:
                return 15;
            case 3:
                return 25;
            default:
                return 0;
        }
    }
}