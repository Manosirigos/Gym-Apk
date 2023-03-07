 package gym.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class SelectTargetActivity extends AppCompatActivity {

    // available exercises tags
    private final static String STRENGTH = "strength";
    private final static String MASS = "mass";
    private final static String WEIGHT_LOSS = "weight_loss";
    private final static String ENDURANCE = "endurance";
    private final static String LINE = "line";
    private final static String LOCAL_FAT = "local_fat";
    private final static String FLEXIBILITY = "flexibility";

    private Button resetBtn;
    private Button selectBtn;

    private CheckBox checkboxStrength;
    private CheckBox checkboxMass;
    private CheckBox checkboxWeightLoss;
    private CheckBox checkboxStamina;
    private CheckBox checkboxLine;
    private CheckBox checkboxLocalFat;
    private CheckBox checkboxFlexibility;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_target);

        setTitle(R.string.select_target_activity_title);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // get all buttons
        resetBtn = (Button) findViewById(R.id.select_target_btn_reset_selections);
        selectBtn = (Button) findViewById(R.id.select_target_btn_confirm_selections);

        // get all checkboxes
        checkboxStrength = (CheckBox) findViewById(R.id.checkbox_strength);
        checkboxMass = (CheckBox) findViewById(R.id.checkbox_mass);
        checkboxWeightLoss = (CheckBox) findViewById(R.id.checkbox_weight_loss);
        checkboxStamina = (CheckBox) findViewById(R.id.checkbox_stamina);
        checkboxLine = (CheckBox) findViewById(R.id.checkbox_line);
        checkboxLocalFat = (CheckBox) findViewById(R.id.checkbox_local_fat);
        checkboxFlexibility = (CheckBox) findViewById(R.id.checkbox_flexibility);

        // RESET Button
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkboxStrength.setChecked(false);
                checkboxMass.setChecked(false);
                checkboxWeightLoss.setChecked(false);
                checkboxStamina.setChecked(false);
                checkboxLine.setChecked(false);
                checkboxLocalFat.setChecked(false);
                checkboxFlexibility.setChecked(false);
            }
        });

        // SELECT Button
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.i("*1", "Select clicked...");

                // check if user has at least one selection - otherwise toast a message
                if (!checkboxStrength.isChecked() && !checkboxMass.isChecked() && !checkboxWeightLoss.isChecked()
                        && !checkboxStamina.isChecked() && !checkboxLine.isChecked()
                        && !checkboxLocalFat.isChecked() && !checkboxFlexibility.isChecked()) {
                    Toast.makeText(getApplicationContext(),"You have to select at least one goal", Toast.LENGTH_LONG).show();
                }
                else {
                    String[] tempTagsArray = new String[7];

                    int i = 0;

                    if(checkboxStrength.isChecked()) {
                        tempTagsArray[i] = STRENGTH;
                        i++;
                    }
                    if(checkboxMass.isChecked()) {
                        tempTagsArray[i] = MASS;
                        i++;
                    }
                    if(checkboxWeightLoss.isChecked()) {
                        tempTagsArray[i] = WEIGHT_LOSS;
                        i++;
                    }
                    if(checkboxStamina.isChecked()) {
                        tempTagsArray[i] = ENDURANCE;
                        i++;
                    }
                    if(checkboxLine.isChecked()) {
                        tempTagsArray[i] = LINE;
                        i++;
                    }
                    if(checkboxLocalFat.isChecked()) {
                        tempTagsArray[i] = LOCAL_FAT;
                        i++;
                    }
                    if(checkboxFlexibility.isChecked()) {
                        tempTagsArray[i] = FLEXIBILITY;
                        i++;
                    }

                    String[] userSelectedTags = new String[i];

                    // copy array tempTagsArray to new one, to "fix" array size to selected tags
                    for(int j=0; j<i; j++) {
                        userSelectedTags[j] = tempTagsArray[j];
                    }

                    // pass to SelectMuscleActivity user selections
                    Bundle b = new Bundle();
                    b.putStringArray("user_selected_tags", userSelectedTags);

                    Intent intent = new Intent(getBaseContext(), SelectMuscleActivity.class);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });
    }
}