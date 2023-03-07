package gym.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ExercisesListActivity extends AppCompatActivity {

    private ArrayList<String> exercisesListStrings;
    private List<Exercise> exercisesList;
    private List<ExercisesCategory> exercisesCategories;
    private int muscleCategoryPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_list);

        // Get the reference of movies
        ListView exercisesList=(ListView)findViewById(R.id.exercises_list);

        // get selected position (selected muscle category)
        muscleCategoryPosition = getIntent().getIntExtra("selected_category_position",0);

        // get data from application
        MyApplication app = (MyApplication) getApplicationContext();
        exercisesCategories = app.getExercisesCategories();

        // get exercises for selected muscle category
        this.exercisesList = exercisesCategories.get(muscleCategoryPosition).getExercisesList();

        // create ArrayList for arrayAdapter (to display list view)
        exercisesListStrings = new ArrayList<String>();

        for (Exercise exercise: this.exercisesList) {
            exercisesListStrings.add(exercise.getExerciseName());
        }

        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, exercisesListStrings);

        // Set The Adapter
        exercisesList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        exercisesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
            {
                String selectedExercise = exercisesListStrings.get(position);

                Intent intent = new Intent();

                // if we have video resource start video exercise activity
                if(exercisesCategories.get(muscleCategoryPosition).getExercisesList().get(position).getExerciseVideoResource() != 0){
                    intent.setClass(getApplicationContext(), VideoExerciseActivity.class);
                }
                else {
                    // else start image exercise activity
                    intent.setClass(getApplicationContext(), ImageExerciseActivity.class);
                }

                intent.putExtra("free_exercise",true);
                intent.putExtra("exercise_position", position);
                intent.putExtra("muscle_category_position", muscleCategoryPosition);

                startActivity(intent);
            }
        });

        // set title
        setTitle("Select Exercise");
    }
}
