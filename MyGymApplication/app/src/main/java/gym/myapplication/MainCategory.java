package gym.myapplication;

public class MainCategory {

    private String categoryTitle;
    private int imageResource;

    public MainCategory(String categoryTitle, int imageResource) {
        this.categoryTitle = categoryTitle;
        this.imageResource = imageResource;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public int getImageResource() {
        return imageResource;
    }
}
