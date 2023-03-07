package gym.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class VideoExerciseActivity extends AppCompatActivity {

    TextView exerciseDescriptionTextView;
    VideoView videoView;
    MediaController mediaController;

    private List<ExercisesCategory> exercisesCategories;
    private Exercise selectedExercise;

    private boolean isFreeExercise;

    private MediaPlayer mediaPlayer;
    private MenuItem playItem, pauseItem;

    private AlertDialog.Builder builder;

    //-------------------------------
    // xrisi gia to xronometro
    private CountDownTimer countDownTimer;
    private long millis; // posa deuterolepta apomenou apo ton metriti (to xrisimopoioume otan tha patisei pause o xristis san eisodo)
    private long timer; // posa deutera tha metrisei o xronometro antistrofa
    private Menu menu; // theloume prosvasi sto menou gia ton metriti
    //------------------------------

    private int exerciseDurationSecs = 30;

    private boolean isMediaPlayerCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_exercise);

        builder = new AlertDialog.Builder(this);

        // mas voithaei na kanoume invisible to menu [resume] - ksana dimiourgei to menu -> des onCreateOptionsMenu
        invalidateOptionsMenu();

        isFreeExercise = getIntent().getBooleanExtra("free_exercise",true);

        if(isFreeExercise) {
            selectedExercise = getFreeExercise();
        }
        else {
            // get exercise from app (workout program)
            MyApplication app = (MyApplication) getApplicationContext();

            // TODO (for update) You can select an exercise with a random way
            selectedExercise = app.getTheExerciseForUser(0);

            //TODO ... remove the exercise from list
            app.removeTheExerciseForUser(0);

        }
        timer = exerciseDurationSecs * 1000;
        loadExerciseContent(selectedExercise);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(!isFreeExercise) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.workout_menu, menu);

            this.menu = menu;

            // o kodikas autos mas eksafanizei to menu [resume]
            playItem = menu.findItem(R.id.action_resume);
            pauseItem = menu.findItem(R.id.action_pause);
            pauseItem.setVisible(false);


            millis = timer;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_pause) {
            mediaPlayer.pause();
            item.setVisible(false);

            playItem.setVisible(true);

            // pause counter - ousiastika ton stamatame kai ksekiname enan neo me to xrono pou exei apominei
            countDownTimer.cancel();

            return true;
        }

        if(item.getItemId() == R.id.action_resume) {

            // we load mp3 file in first time Resume (Play) is clicked
            if(isMediaPlayerCreated != true) {
                // get music file
                int selectedSong = R.raw.music;

                // play music
                mediaPlayer = MediaPlayer.create(this, selectedSong);
            }

            mediaPlayer.start();

            item.setVisible(false);

            pauseItem.setVisible(true);

            //-----------------------------------------------------------------------
            // ksekiname neo counter me ton xrono pou exei apominei apo ton proigoume mias pou den exoume dinatotita gia pause kai resume

            timer = millis;

            final MenuItem  counter =  menu.findItem(R.id.counter);
            countDownTimer = new CountDownTimer(timer, 1000) {

                public void onTick(long millisUntilFinished) {
                    millis = millisUntilFinished;

                    String diviter = ":";
                    if(millis < 10000) {
                        diviter = ":0";
                    }

                    String  hms = (TimeUnit.MILLISECONDS.toMinutes(millis) -TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)))+ diviter + (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

                    counter.setTitle(hms);
                    timer = millis;

                }

                public void onFinish() {

                    Log.i("Video Exercise", "Exercise ended!");

                    //------------------------

                    // stop music
                    mediaPlayer.stop();

                    MyApplication app = (MyApplication) getApplicationContext();
                    Exercise nextExercise = app.getTheExerciseForUser(0);

                    // check if workout program has more exercises
                    if(nextExercise != null) {
                        builder.setTitle("Exercise finished")
                                .setMessage("Do you want to continue with the next exercise")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        startNextWorkoutProgramExercise();
                                    }
                                })
                                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // move to main activity
                                        Intent intent = new Intent();
                                        intent.setClass(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }
                    else {
                        // inform about workout program end
                        builder.setTitle("Workout program finished")
                                .setMessage("congratulations you completed your training successfully!")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // move to main activity
                                        Intent intent = new Intent();
                                        intent.setClass(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }
                }
            }.start();
            //-------------------------------------------------------------------------

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
    *   Load and display exercise main content (Title, Description, Video)
    */
    private void loadExerciseContent(Exercise exercise) {

        // get exercise name and set it as activity title
        String title = exercise.getExerciseName();
        setTitle(title);

        // set exercise description
        int exerciseDescriptionResource = exercise.getExerciseDescriptionResource();
        exerciseDescriptionTextView = (TextView) findViewById(R.id.exercise_description);
        exerciseDescriptionTextView.setText(exerciseDescriptionResource);

        // setup video view and controllers
        videoView = (VideoView) findViewById(R.id.video_view);
        mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        // get video resource
        int video = exercise.getExerciseVideoResource();

        // start video
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + video));
        videoView.start();
    }

    /**
     * Get user selected exercise (Free Exercises)
     * We get category and exercise list position from intents
     *
     * @return User selected exercise
     */
    private Exercise getFreeExercise() {

        // get user selection (category and exercise)
        int muscleCategoryPosition = getIntent().getIntExtra("muscle_category_position",0);
        int exercisePosition = getIntent().getIntExtra("exercise_position",0);

        // get data from application
        MyApplication app = (MyApplication) getApplicationContext();
        exercisesCategories = app.getExercisesCategories();

        // get current exercise
        Exercise currentExercise = exercisesCategories.get(muscleCategoryPosition).getExercisesList().get(exercisePosition);

        return currentExercise;
    }

    /**
     * start next suggested exercise
     */
    private void startNextWorkoutProgramExercise() {

        MyApplication app = (MyApplication) getApplicationContext();

        // we need exercise only to check if is a [video] or an [image] exercise
        Exercise nextExercise = app.getTheExerciseForUser(0);

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
}
