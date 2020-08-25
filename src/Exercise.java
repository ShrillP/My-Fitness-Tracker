public class Exercise {

    String exercise;
    String description;
    String bodyType;

    public Exercise(String exercise, String description, String bodyType) {
        this.exercise = exercise;
        this.description = description;
        this.bodyType = bodyType;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}