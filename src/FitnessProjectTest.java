import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class FitnessProjectTest {

    public static File exerciseFile = new File("res/ExtraExercises.txt");
    public static File cardioExerciseFile = new File("res/CardioExtraExercises.txt");
    public static File userDataFile = new File("res/userDataFile.txt");

    public static File[] cardioFiles = new File[10];
    public static File[] weightFiles = new File[10];

    public static ArrayList<String> cardioExercises = new ArrayList<String>();

    public static ArrayList<Exercise> weightliftingExercises = new ArrayList<Exercise>();
    public static ArrayList<String> exercises = new ArrayList<String>();

    public static void main(String[] args) throws IOException, FontFormatException {


        cardioFiles[0] = new File("res/cardioFile0.txt");
        weightFiles[0] = new File("res/weightFile0.txt");

        cardioFiles[1] = new File("res/cardioFile1.txt");
        weightFiles[1] = new File("res/weightFile1.txt");

        cardioFiles[2] = new File("res/cardioFile2.txt");
        weightFiles[2] = new File("res/weightFile2.txt");

        cardioFiles[3] = new File("res/cardioFile3.txt");
        weightFiles[3] = new File("res/weightFile3.txt");

        cardioFiles[4] = new File("res/cardioFile4.txt");
        weightFiles[4] = new File("res/weightFile4.txt");

        cardioFiles[5] = new File("res/cardioFile5.txt");
        weightFiles[5] = new File("res/weightFile5.txt");

        cardioFiles[6] = new File("res/cardioFile6.txt");
        weightFiles[6] = new File("res/weightFile6.txt");

        cardioFiles[7] = new File("res/cardioFile7.txt");
        weightFiles[7] = new File("res/weightFile7.txt");

        cardioFiles[8] = new File("res/cardioFile8.txt");
        weightFiles[8] = new File("res/weightFile8.txt");

        cardioFiles[9] = new File("res/cardioFile9.txt");
        weightFiles[9] = new File("res/weightFile9.txt");

//		InfoScreen frame = new InfoScreen();
//		frame.setVisible(true);

//        new Weightlifting();
//      new Cardio();
//    new NewExercise();

//    new CreateAccount();
//    new LoginScreen();

        new HomeScreen();
        HomeScreen frame = new HomeScreen();
        frame.setVisible(true);


    }

    public static void initalizeCardioExercises(){

        cardioExercises.clear();

        cardioExercises.add("Walking");
        cardioExercises.add("Running");
        cardioExercises.add("Biking");
        cardioExercises.add("Swimming");
        cardioExercises.add("Steps Machine");

//       try {
//           NewExercise.load2("res/CardioExtraExercises.txt");
//       } catch (IOException e) {
//           // TODO Auto-generated catch block
//           e.printStackTrace();
//       }

    }

    public static void initalizeWeightliftingExercises(){

        weightliftingExercises.clear();
        weightliftingExercises.add(new Exercise("Flat Bench Press", Weightlifting.FLAT_BENCH_PRESS, "Chest"));
        weightliftingExercises.add(new Exercise("Decline Bench Press", Weightlifting.DECLINE_BENCH_PRESS, "Chest"));
        weightliftingExercises.add(new Exercise("Incline Bench Press", Weightlifting.INCLINE_BENCH_PRESS, "Chest"));
        weightliftingExercises.add(new Exercise("Front Press (military or DB)", Weightlifting.FRONT_PRESS, ""));
        weightliftingExercises.add(new Exercise("Dead Lift", Weightlifting.DEADLIFT, "Legs"));
        weightliftingExercises.add(new Exercise("Squats", Weightlifting.SQUATS, "Legs"));
        weightliftingExercises.add(new Exercise("One Arm Dumbbell Deadlift", Weightlifting.ONE_ARM_DEADLIFT, "Back"));
        weightliftingExercises.add(new Exercise("Leg Extensions", Weightlifting.LEG_EXTENSIONS, "Legs"));
        weightliftingExercises.add(new Exercise("Hamstring Curls", Weightlifting.HAMSTRING_CURLS, ""));
        weightliftingExercises.add(new Exercise("Calf Raises", Weightlifting.CALF_RAISES, "Legs"));
        weightliftingExercises.add(new Exercise("Shoulder Press", Weightlifting.SHOULDER_PRESS, "Shoulders"));
        weightliftingExercises.add(new Exercise("Lateral Raises", Weightlifting.LATERAL_RAISES, ""));
        weightliftingExercises.add(new Exercise("Front Raises", Weightlifting.FRONT_RAISES, ""));
        weightliftingExercises.add(new Exercise("Upright Row", Weightlifting.UPRIGHT_ROW, "Shoulders"));
        weightliftingExercises.add(new Exercise("Straight Bar Curls", Weightlifting.STRAIGHT_BAR_CURLS, "Biceps"));
        weightliftingExercises.add(new Exercise("Dumbbell Hammer Curls", Weightlifting.DB_HAMMER_CURLS, "Biceps"));
        weightliftingExercises.add(new Exercise("Tricep Extension", Weightlifting.TRICEP_EXTENSION, ""));
        weightliftingExercises.add(new Exercise("Skull Crushers", Weightlifting.SKULL_CRUSHERS, "Triceps"));
        weightliftingExercises.add(new Exercise("Lateral Pulldowns", Weightlifting.LATERAL_PULLDOWNS, "Back"));
        weightliftingExercises.add(new Exercise("Single Arm DB Rows", Weightlifting.SINGLE_ARM_DB_ROWS, "Back"));
        weightliftingExercises.add(new Exercise("Bench Squat", Weightlifting.BENCH_SQUAT, "Legs"));
        weightliftingExercises.add(new Exercise("V-Bar Extensions", Weightlifting.V_BAR_EXTENSIONS, "Triceps"));
        weightliftingExercises.add(new Exercise("Fly's", Weightlifting.FLY, "Chest"));

        try {
            NewExercise.load("res/ExtraExercises.txt");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}