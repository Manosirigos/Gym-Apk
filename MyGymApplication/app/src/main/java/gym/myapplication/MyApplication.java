  package gym.myapplication;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application {

    // available exercises tags
    private final static String STRENGTH = "strength";
    private final static String MASS = "mass";
    private final static String WEIGHT_LOSS = "weight_loss";
    private final static String ENDURANCE = "endurance";
    private final static String LINE = "line";
    private final static String LOCAL_FAT = "local_fat";
    private final static String FLEXIBILITY = "flexibility";

    private List<ExercisesCategory> exercisesCategories;
    private List<MainCategory> mainCategoriesList;

    // lists of Exercises
    private List<Exercise> BicepsExercisesList;
    private List<Exercise> ChestExercisesList;
    private List<Exercise> TricepsExercisesList;
    private List<Exercise> BackExercisesList;
    private List<Exercise> ShouldersExercisesList;
    private List<Exercise> ABSExercisesList;
    private List<Exercise> LegsExercisesList;

    private List<Exercise> userSuggestedExercises;

    @Override
    public void onCreate() {
        super.onCreate();

        // create main categories
        mainCategoriesList = new ArrayList<>();

        // create exercises categories
        exercisesCategories = new ArrayList<>();

        // load exercises categories, exercises data (titles, videos, images, texts,...)
        loadApplicationData();

        // TODO call this function on user end selection [goals - muscles]
        //createUserSelectedExercises();
    }

    public List<ExercisesCategory> getExercisesCategories() {
        return exercisesCategories;
    }

    public List<MainCategory> getMainCategories() { return mainCategoriesList;}

    // TODO get random exercise from list and returned
    public Exercise getTheExerciseForUser(int position) {

        Exercise nextExercise = null;

        if (!userSuggestedExercises.isEmpty()) {
            nextExercise = userSuggestedExercises.get(position);
        }
        return nextExercise;
    }

    public void removeTheExerciseForUser(int position) {
        userSuggestedExercises.remove(position);
    }

    /**
     *  Initialize all categories, images and videos exercises and tags per exercise
     */
    private void loadApplicationData() {

        // Main Categories
        mainCategoriesList.add( new MainCategory("Free exercise", R.drawable.free_exercise));
        mainCategoriesList.add( new MainCategory("Workout program", R.drawable.workout_program));
        mainCategoriesList.add( new MainCategory("Workout calendar", R.drawable.calender));


        // Biceps
        BicepsExercisesList = new ArrayList<>();

        // give tags to every exercise
        String[] exercise1Tags = {STRENGTH, MASS};
        BicepsExercisesList.add( new Exercise("Concentration Curl Biceps",R.string.biceps_description_exercise_1,0,R.raw.biceps_video_1, exercise1Tags));

        String[] exercise2Tags = {STRENGTH, ENDURANCE};
        BicepsExercisesList.add( new Exercise("TRX Biceps Curl",R.string.biceps_description_exercise_2,0,R.raw.biceps_video_2, exercise2Tags));

        // Chest
        ChestExercisesList = new ArrayList<>();

        String[] exercise3Tags = {STRENGTH, MASS, LINE};
        ChestExercisesList.add( new Exercise("Dumbbell Bench Press",R.string.chest_description_exercise_1,0,R.raw.chest_video_1, exercise3Tags));

        String[] exercise4Tags = {ENDURANCE, STRENGTH, LINE};
        ChestExercisesList.add( new Exercise("TRX Push-ups",R.string.chest_description_exercise_2,0,R.raw.chest_video_2, exercise4Tags));

        String[] exercise5Tags = {FLEXIBILITY, LINE, ENDURANCE};
        ChestExercisesList.add( new Exercise("Pyramid Push-Ups",R.string.chest_description_exercise_3,R.drawable.pyramid_push_ups,0, exercise5Tags));

        // Triceps
        TricepsExercisesList = new ArrayList<>();

        String[] exercise6Tags = {FLEXIBILITY, LOCAL_FAT, LINE, STRENGTH};
        TricepsExercisesList.add( new Exercise("Dumbbell French Press",R.string.triceps_description_exercise_1,0,R.raw.triceps_video_1, exercise6Tags));

        String[] exercise7Tags = {LINE, ENDURANCE, STRENGTH};
        TricepsExercisesList.add( new Exercise("TRX Tricep Extension",R.string.triceps_description_exercise_2,0,R.raw.triceps_video_2, exercise7Tags));

        // Back
        BackExercisesList = new ArrayList<>();

        String[] exercise8Tags = {STRENGTH, MASS, WEIGHT_LOSS};
        BackExercisesList.add( new Exercise("Chest Supported Dumbbell Row",R.string.back_description_exercise_1,0,R.raw.back_video_1, exercise8Tags));

        String[] exercise9Tags = {ENDURANCE, WEIGHT_LOSS, STRENGTH, MASS};
        BackExercisesList.add( new Exercise("TRX Row",R.string.back_description_exercise_2,0,R.raw.back_video_2, exercise9Tags));

        // Shoulders
        ShouldersExercisesList = new ArrayList<>();

        String[] exercise10Tags = {STRENGTH, MASS};
        ShouldersExercisesList.add( new Exercise("The Seated Arnold Press",R.string.shoulders_description_exercise_1,0,R.raw.shoulders_video_1, exercise10Tags));

        String[] exercise11Tags = {STRENGTH, ENDURANCE, FLEXIBILITY, LINE};
        ShouldersExercisesList.add( new Exercise("TRX Y Fly",R.string.shoulders_description_exercise_2,0,R.raw.shoulders_video_2, exercise11Tags));

        // ABS
        ABSExercisesList = new ArrayList<>();

        String[] exercise12Tags = {WEIGHT_LOSS, LINE, FLEXIBILITY, LOCAL_FAT};
        ABSExercisesList.add( new Exercise("Lying Leg Raises",R.string.abs_description_exercise_1,0,R.raw.abs_video_1, exercise12Tags));

        String[] exercise13Tags = {WEIGHT_LOSS, LINE, FLEXIBILITY, LOCAL_FAT};
        ABSExercisesList.add( new Exercise("TRX Ab Fallout",R.string.abs_description_exercise_2,0,R.raw.abs_video_2, exercise13Tags));

        // Legs
        LegsExercisesList = new ArrayList<>();

        String[] exercise14Tags = {WEIGHT_LOSS, LOCAL_FAT, LINE, STRENGTH, ENDURANCE, MASS};
        LegsExercisesList.add( new Exercise("The Goblet Squat Exercise Guide",R.string.legs_description_exercise_1,0,R.raw.legs_video_1, exercise14Tags));

        String[] exercise15Tags = {WEIGHT_LOSS, LOCAL_FAT, LINE, STRENGTH, ENDURANCE};
        LegsExercisesList.add( new Exercise("TRX Squat— Single Leg",R.string.legs_description_exercise_2,0,R.raw.legs_video_2, exercise15Tags));

        exercisesCategories.add(new ExercisesCategory("Biceps", R.drawable.biceps_thumbnail, BicepsExercisesList));
        exercisesCategories.add(new ExercisesCategory("Chest", R.drawable.chest_thumbnail, ChestExercisesList));
        exercisesCategories.add(new ExercisesCategory("Triceps", R.drawable.triceps_thumbnail, TricepsExercisesList));
        exercisesCategories.add(new ExercisesCategory("Back", R.drawable.back_thumbnail, BackExercisesList));
        exercisesCategories.add(new ExercisesCategory("Shoulders", R.drawable.shoulders_thumbnail, ShouldersExercisesList));
        exercisesCategories.add(new ExercisesCategory("ABS", R.drawable.abs_thumbnail, ABSExercisesList));
        exercisesCategories.add(new ExercisesCategory("Legs", R.drawable.legs_thumbnail, LegsExercisesList));
    }

    /**
     * add exercises based on TAGS and selected muscles categories to a list - userSuggestedExercises
     *
     * @param tags User selected tags
     * @param isBicepsSelected boolean parameter that informs if user want to train biceps muscles
     * @param isChestSelected ...
     * @param isTricepsSelected ...
     * @param isBackSelected ...
     * @param isShouldersSelected ...
     * @param isABSSelected ...
     * @param isLegsSelected ...
     */
    public void createUserSelectedExercises(String[] tags, boolean isBicepsSelected, boolean isChestSelected,
                                            boolean isTricepsSelected, boolean isBackSelected, boolean isShouldersSelected,
                                            boolean isABSSelected, boolean isLegsSelected) {

        userSuggestedExercises = new ArrayList<>();

        // Biceps
        if(isBicepsSelected) {

            for(Exercise currExercise : BicepsExercisesList) {
                for (String tag : tags) {
                    if(currExercise.hasExerciseThisTag(tag)) {
                        userSuggestedExercises.add(currExercise);
                        break; // to have only one time each exercise
                    }
                }
            }
        }

        // Chest
        if(isChestSelected) {

            for(Exercise currExercise : ChestExercisesList) {
                for (String tag : tags) {
                    if(currExercise.hasExerciseThisTag(tag)) {
                        userSuggestedExercises.add(currExercise);
                        break; // to have only one time each exercise
                    }
                }
            }
        }

        // Triceps
        if(isTricepsSelected) {

            for(Exercise currExercise : TricepsExercisesList) {
                for (String tag : tags) {
                    if(currExercise.hasExerciseThisTag(tag)) {
                        userSuggestedExercises.add(currExercise);
                        break; // to have only one time each exercise
                    }
                }
            }
        }

        // Back
        if(isBackSelected) {

            for(Exercise currExercise : BackExercisesList) {
                for (String tag : tags) {
                    if(currExercise.hasExerciseThisTag(tag)) {
                        userSuggestedExercises.add(currExercise);
                        break; // to have only one time each exercise
                    }
                }
            }
        }

        // Shoulders
        if(isShouldersSelected) {

            for(Exercise currExercise : ShouldersExercisesList) {
                for (String tag : tags) {
                    if(currExercise.hasExerciseThisTag(tag)) {
                        userSuggestedExercises.add(currExercise);
                        break; // to have only one time each exercise
                    }
                }
            }
        }

        // ABS
        if (isABSSelected) {

            for(Exercise currExercise : ABSExercisesList) {
                for (String tag : tags) {
                    if(currExercise.hasExerciseThisTag(tag)) {
                        userSuggestedExercises.add(currExercise);
                        break; // to have only one time each exercise
                    }
                }
            }
        }

        // Legs
        if(isLegsSelected) {

            for(Exercise currExercise : LegsExercisesList) {
                for (String tag : tags) {
                    if(currExercise.hasExerciseThisTag(tag)) {
                        userSuggestedExercises.add(currExercise);
                        break; // to have only one time each exercise
                    }
                }
            }
        }
    }
}
