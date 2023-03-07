package gym.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<MainCategory> mainCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set title
        setTitle("myGym App");

        // get data from application
        MyApplication app = (MyApplication) getApplicationContext();
        mainCategories = app.getMainCategories();

        recyclerView = (RecyclerView) findViewById(R.id.mainMenuRecyclerView);

        // specify an adapter (see also next example)
        mAdapter = new MainCategoryAdapter(this, mainCategories);

        // use a grid layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);
    }
}