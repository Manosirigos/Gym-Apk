
package gym.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.Arrays;

public class SelectMuscleActivity extends AppCompatActivity {

    // available muscles
    private final static String BICEPS = "biceps";
    private final static String CHEST = "chest";
    private final static String TRICEPS = "triceps";
    private final static String BACK = "back";
    private final static String SHOULDERS = "shoulders";
    private final static String ABS = "abs";
    private final static String LEGS = "legs";

    private boolean isStrengthSelected = false;

    private Button resetBtn;
    private Button selectBtn;

    private CheckBox checkboxBiceps;
    private CheckBox checkboxChest;
    private CheckBox checkboxTriceps;
    private CheckBox checkboxBack;
    private CheckBox checkboxShoulders;
    private CheckBox checkboxAbs;
    private CheckBox checkboxLegs;
    private CheckBox checkboxFullBody;

    private String[] userTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_muscle);
        setTitle(R.string.select_muscle_activity_title);

        userTags = getIntent().getExtras().getStringArray("user_selected_tags");

        Log.i("*1","" + Arrays.toString(userTags));

    }

    @Override
    protected void onResume() {
        super.onResume();

        // get all buttons
        resetBtn = (Button) findViewById(R.id.select_muscle_btn_reset_selections);
        selectBtn = (Button) findViewById(R.id.select_muscle_btn_confirm_selections);

        // get all checkboxes
        checkboxBiceps = (CheckBox) findViewById(R.id.checkbox_biceps);
        checkboxChest = (CheckBox) findViewById(R.id.checkbox_chest);
        checkboxTriceps = (CheckBox) findViewById(R.id.checkbox_triceps);
        checkboxBack = (CheckBox) findViewById(R.id.checkbox_back);
        checkboxShoulders = (CheckBox) findViewById(R.id.checkbox_shoulders);
        checkboxAbs = (CheckBox) findViewById(R.id.checkbox_abs);
        checkboxLegs = (CheckBox) findViewById(R.id.checkbox_legs);
        checkboxFullBody = (CheckBox) findViewById(R.id.checkbox_full_body);

        checkboxFullBody.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()) {

                    checkboxBiceps.setChecked(true);
                    checkboxChest.setChecked(true);
                    checkboxTriceps.setChecked(true);
                    checkboxBack.setChecked(true);
                    checkboxBack.setChecked(true);
                    checkboxShoulders.setChecked(true);
                    checkboxAbs.setChecked(true);
                    checkboxLegs.setChecked(true);
                }
                else {

                    checkboxBiceps.setChecked(false);
                    checkboxChest.setChecked(false);
                    checkboxTriceps.setChecked(false);
                    checkboxBack.setChecked(false);
                    checkboxBack.setChecked(false);
                    checkboxShoulders.setChecked(false);
                    checkboxAbs.setChecked(false);
                    checkboxLegs.setChecked(false);
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkboxBiceps.setChecked(false);
                checkboxChest.setChecked(false);
                checkboxTriceps.setChecked(false);
                checkboxBack.setChecked(false);
                checkboxBack.setChecked(false);
                checkboxShoulders.setChecked(false);
                checkboxAbs.setChecked(false);
                checkboxLegs.setChecked(false);
                checkboxFullBody.setChecked(false);
            }
        });

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check if user has at least one selection - otherwise toast a message
                if (!checkboxBiceps.isChecked() && !checkboxChest.isChecked() && !checkboxTriceps.isChecked()
                        && !checkboxBack.isChecked() && !checkboxShoulders.isChecked()
                        && !checkboxAbs.isChecked() && !checkboxLegs.isChecked()) {
                    Toast.makeText(getApplicationContext(),"You have to select at least one muscle", Toast.LENGTH_LONG).show();
                }
                else {
                    // create user exercises list (based on his selections)
                    // TODO add here selected tags and body parts

                    MyApplication app = (MyApplication) getApplicationContext();

                    app.createUserSelectedExercises(userTags, checkboxBiceps.isChecked(), checkboxChest.isChecked(),
                            checkboxTriceps.isChecked(), checkboxBack.isChecked(), checkboxShoulders.isChecked(), checkboxAbs.isChecked(),
                            checkboxLegs.isChecked());

                    // we need exercise only to check if is a [video] or an [image] exercise
                    Exercise nextExercise = app.getTheExerciseForUser(0);

                    // if we have exercises with this tags
                    if (nextExercise != null) {

                        Intent intent = new Intent();

                        if(nextExercise.getExerciseVideoResource() != 0) {
                            // we have a video exercise
                            intent.setClass(getApplicationContext(), VideoExerciseActivity.class);
                        }
                        else {
                            // we have an image exercise
                            intent.setClass(getApplicationContext(), ImageExerciseActivity.class);
                        }

                        intent.putExtra("free_exercise",false);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"There are no exercises that meet your criteria", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}