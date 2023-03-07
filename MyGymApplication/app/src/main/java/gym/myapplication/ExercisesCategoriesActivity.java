package gym.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ExercisesCategoriesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<ExercisesCategory> exercisesCategories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_categories);


        // set title
        setTitle("Select muscle (free exercise)");

        // get data from application
        MyApplication app = (MyApplication) getApplicationContext();
        exercisesCategories = app.getExercisesCategories();

        recyclerView = (RecyclerView) findViewById(R.id.categoriesRecyclerView);

        // specify an adapter (see also next example)
        mAdapter = new CategoryAdapter(this, exercisesCategories);

        // use a grid layout manager
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);
    }
}
