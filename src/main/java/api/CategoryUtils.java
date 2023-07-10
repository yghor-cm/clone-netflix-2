package api;

public class CategoryUtils {

    private static final MovieAPI.Category[] categories = MovieAPI.getInstance().getCategories();

    public static String getCategoryPath(String categoryName) {
        for (MovieAPI.Category category : categories) {
            if (category.name.equalsIgnoreCase(categoryName)) {
                return category.path;
            }
        }

        return null;
    }
}

