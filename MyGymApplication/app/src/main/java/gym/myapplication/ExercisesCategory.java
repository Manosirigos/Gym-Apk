package gym.myapplication;
import java.io.Serializable;
import java.util.List;

public class ExercisesCategory{
    private String categoryTitle;
    private int imageResource;
    private List<Exercise> exercisesList;

    public ExercisesCategory(String categoryTitle, int imageResource, List<Exercise> exercisesList) {
        this.categoryTitle = categoryTitle;
        this.imageResource = imageResource;
        this.exercisesList = exercisesList;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public int getImageResource() {
        return imageResource;
    }

    public List<Exercise> getExercisesList() {
        return exercisesList;
    }
}
