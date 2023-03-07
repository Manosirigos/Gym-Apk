package gym.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Exercise {

    private String exerciseName;
    private int exerciseDescriptionResource;

    // if 0 we don't have image for this exercise
    private int exerciseImageResource;

    // if 0 we don't have video for this exercise
    private int exerciseVideoResource;

    // TAGS to say if an exercise is good for a specific goal (strength, mass, weight loss...)
    private String[] tags;

    public Exercise(String exerciseName, int exerciseDescription, int exerciseImageResource, int exerciseVideoResource, String[] tagsArray) {
        this.exerciseName = exerciseName;
        this.exerciseDescriptionResource = exerciseDescription;
        this.exerciseImageResource = exerciseImageResource;
        this.exerciseVideoResource = exerciseVideoResource;
        this.tags = tagsArray;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getExerciseDescriptionResource() {
        return exerciseDescriptionResource;
    }

    public int getExerciseImageResource() {
        return exerciseImageResource;
    }

    public int getExerciseVideoResource() {
        return exerciseVideoResource;
    }

    public boolean hasExerciseThisTag(String tag) {
        for(String currTag: tags) {
            if(currTag.equals(tag)) {
                return true;
            }
        }
        return false;
    }
}
