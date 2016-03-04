package edu.ucsb.ece.ece150.ohmcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class ManualEnterActivity extends AppCompatActivity {

    public double code1, code2, code3, code4, code5, code6;
    public TextView r, t, c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_enter);

        Button b1 = (Button) findViewById(R.id.band4);
        Button b2 = (Button) findViewById(R.id.band5);
        Button b3 = (Button) findViewById(R.id.band6);

        r = (TextView) findViewById(R.id.resistance);
        t = (TextView) findViewById(R.id.tolerance);
        c = (TextView) findViewById(R.id.tempCoefficient);


        final Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        final Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        final Spinner s3 = (Spinner) findViewById(R.id.spinner3);
        final Spinner s4 = (Spinner) findViewById(R.id.spinner4);
        final Spinner s5 = (Spinner) findViewById(R.id.spinner5);
        final Spinner s6 = (Spinner) findViewById(R.id.spinner6);

        s3.setVisibility(View.INVISIBLE);
        s6.setVisibility(View.INVISIBLE);
        c.setVisibility(View.INVISIBLE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s3.setVisibility(View.INVISIBLE);
                s6.setVisibility(View.INVISIBLE);
                c.setVisibility(View.INVISIBLE);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s3.setVisibility(View.VISIBLE);
                s6.setVisibility(View.INVISIBLE);
                c.setVisibility(View.INVISIBLE);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s3.setVisibility(View.VISIBLE);
                s6.setVisibility(View.VISIBLE);
                c.setVisibility(View.VISIBLE);
            }
        });

        s1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text_ring1 = s1.getSelectedItem().toString();
                code1 = decode(text_ring1);
                ManualEnterActivity.this.updateText();
                Log.e("Code1", String.valueOf(code1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        s2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text_ring2 = s2.getSelectedItem().toString();
                code2 = decode(text_ring2);
                ManualEnterActivity.this.updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        s3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text_ring3 = s3.getSelectedItem().toString();
                code3 = decode(text_ring3);
                ManualEnterActivity.this.updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s4.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text_ring4 = s4.getSelectedItem().toString();
                code4 = decode(text_ring4);
                ManualEnterActivity.this.updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s5.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text_ring5 = s5.getSelectedItem().toString();
                code5 = tolerance(text_ring5);
                ManualEnterActivity.this.updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s6.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text_ring6 = s6.getSelectedItem().toString();
                code6 = tc(text_ring6);
                ManualEnterActivity.this.updateText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateText() {
        double resistance4 = (10.0 * code1 + code2) * Math.pow(10.0, code4);
        r.setText(String.valueOf(resistance4)+" Ohm");
        t.setText("+/-"+ String.valueOf(code5)+"%");
        c.setText(String.valueOf(code6)+"ppm");
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

    private double tolerance(String text) {
        switch (text) {
            case "Silver":
                return 10;
            case "Gold":
                return 5;
            case "Brown":
                return 1.0;
            case "Red":
                return 2.0;
            case "Green":
                return 0.5;
            case "Blue":
                return 0.25;
            case "Purple":
                return 0.1;
            case "Grey":
                return 0.05;
            default:
                return 0;
        }
    }

    private double tc(String text) {
        switch (text) {
            case "Brown":
                return 100;
            case "Red":
                return 50;
            case "Orange":
                return 15;
            case "Yellow":
                return 25;
            default:
                return 0;
        }
    }
}
