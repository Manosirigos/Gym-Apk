package gym.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Realcal1Activity extends AppCompatActivity {

    private TextView textView21;
    private TextView textView22;
    private TextView textView23;
    private TextView textView5;
    private TextView textView27;
    private TextView textView28;
    private TextView textView29;
    private TextView textView30;
    private TextView textView32;
    private TextView textView33;
    private TextView textView34;
    private TextView textView35;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realcal1);

        textView21 = (TextView) findViewById(R.id.textView21);
        textView22 = (TextView) findViewById(R.id.textView22);
        textView23 = (TextView) findViewById(R.id.textView23);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView27 = (TextView) findViewById(R.id.textView27);
        textView28 = (TextView) findViewById(R.id.textView28);
        textView29 = (TextView) findViewById(R.id.textView29);
        textView30 = (TextView) findViewById(R.id.textView30);
        textView32 = (TextView) findViewById(R.id.textView32);
        textView33 = (TextView) findViewById(R.id.textView33);
        textView34 = (TextView) findViewById(R.id.textView34);
        textView35 = (TextView) findViewById(R.id.textView35);

        textView21.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                Intent intent = new Intent(Realcal1Activity.this, MyVid1.class);
                startActivity(intent);
            }
        }

        );
        textView5.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                Intent intent = new Intent(Realcal1Activity.this, MyVid2.class);
                startActivity(intent);
            }
        }

        );
        textView22.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                Intent intent = new Intent(Realcal1Activity.this, MyVid13.class);
                startActivity(intent);

            }
        }

        );
        textView23.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                Intent intent = new Intent(Realcal1Activity.this, MyVid14.class);
                startActivity(intent);

            }
        }

        );
        textView27.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                Intent intent = new Intent(Realcal1Activity.this, MyVid1.class);
                startActivity(intent);

            }
        }

        );
        textView28.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                Intent intent = new Intent(Realcal1Activity.this, MyVid2.class);
                startActivity(intent);

                                         }
        }

        );
        textView29.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                Intent intent = new Intent(Realcal1Activity.this, MyVid3.class);
                startActivity(intent);

            }
        }

        );
        textView30.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                Intent intent = new Intent(Realcal1Activity.this, MyVid7.class);
                startActivity(intent);

                                         }
        }

        );
        textView32.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                Intent intent = new Intent(Realcal1Activity.this, MyVid5.class);
                startActivity(intent);

            }
        }

        );
        textView33.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                Intent intent = new Intent(Realcal1Activity.this, MyVid9.class);
                startActivity(intent);

                                         }
        }

        );
        textView34.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Intent intent = new Intent(Realcal1Activity.this, MyVid1.class);
                                              startActivity(intent);

                                          }
                                      }

        );
        textView35.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent intent = new Intent(Realcal1Activity.this, MyVid2.class);
                                             startActivity(intent);

                                         }
                                     }

        );



    }
}
