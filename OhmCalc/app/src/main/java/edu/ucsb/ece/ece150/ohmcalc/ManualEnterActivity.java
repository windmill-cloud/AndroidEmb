package edu.ucsb.ece.ece150.ohmcalc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManualEnterActivity extends AppCompatActivity {
    public Button button0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_enter);


        button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent myIntent = new Intent(view.getContext(), agones.class);
                //startActivityForResult(myIntent, 0);
                final CharSequence colors[] = new CharSequence[]{"Black", "Brown", "Red", "Orange",
                        "Yellow", "Green", "Blue", "Violet", "Grey", "White"};

                AlertDialog.Builder builder = new AlertDialog.Builder(ManualEnterActivity.this);
                builder.setTitle("Pick a color");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        int color;
                        switch (which) {
                            case 0:
                                color = 0xFFFFFFFF;
                                break;
                            case 1:
                                color = 0xFFA52A2A;
                                break;
                            case 2:
                                color = 0xFFFFA500;
                                break;
                            case 3:
                                color = 0xFFFFFF00;
                                break;
                            case 4:
                                color = 0xFFFFFF00;
                                break;
                            case 5:
                                color = 0xFFFFFF00;
                                break;
                            case 6:
                                color = 0xFFFFFF00;
                                break;
                            default:
                                color = 0xFF000000;

                        }
                        button0.setBackgroundColor(color);
                    }
                });
                builder.show();
            }
        });

    }
}
