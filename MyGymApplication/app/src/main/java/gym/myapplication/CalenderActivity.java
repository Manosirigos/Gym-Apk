package gym.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CalenderActivity extends AppCompatActivity {
    private ImageView fitn;
    private ImageView losewei;
    private ImageView strngth;
    private ImageView musclegain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

         fitn = (ImageView) findViewById(R.id.fitn);
         losewei = (ImageView) findViewById(R.id.losewei);
         strngth = (ImageView) findViewById(R.id.strngth);
         musclegain = (ImageView) findViewById(R.id.musclegain);

         fitn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(CalenderActivity.this, Food1Activity.class);
                 startActivity(intent);
             }
         }

         );

        losewei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderActivity.this, Food2Activity.class);
                startActivity(intent);

            }
        }

        );

        strngth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderActivity.this, Food3Activity.class);
                startActivity(intent);

            }
        }

        );

        musclegain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderActivity.this, Food4Activity.class);
                startActivity(intent);

            }
        }

        );

    }
}
