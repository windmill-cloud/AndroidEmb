package edu.ucsb.ece.ece150.ohmcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonEnt = (Button) findViewById(R.id.buttonEnter);
        buttonEnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ManualEnterActivity.class);
                //myIntent.putExtra("")
                startActivity(myIntent);
                //finish();
            }
        });
        final Button buttonDet = (Button) findViewById(R.id.buttonDetect);
        buttonDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, DetectActivity.class);
                //myIntent.putExtra("")
                startActivity(myIntent);
                //finish();
            }
        });

        final Button buttonDis = (Button) findViewById(R.id.buttonDisplay);
        buttonDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, DisplayActivity.class);
                //myIntent.putExtra("")
                startActivity(myIntent);
                //finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
