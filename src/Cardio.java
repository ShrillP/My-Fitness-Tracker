import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Cardio extends JFrame implements ActionListener {

    //Initialized all components of the GUI
    //Date Inputs
    private static JTextField[] userInputs1 = new JTextField[3];
    private JComboBox[] userInputs2 = new JComboBox[3];

    //Image Icons that allow the user to get more information on the field that are going to enter
    private BufferedImage buttonIcon1 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button1 = new JButton(new ImageIcon(buttonIcon1));

    private BufferedImage buttonIcon2 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button2 = new JButton(new ImageIcon(buttonIcon2));

    private BufferedImage buttonIcon3 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button3 = new JButton(new ImageIcon(buttonIcon3));

    //Image Icon for adding their own personalized exercises
    private BufferedImage addIcon = ImageIO.read(new File("res/Images/add.png"));
    private JButton add = new JButton(new ImageIcon(addIcon));

    //Another image icon allowing the user to go to the previous "main" screen
    private BufferedImage backIcon = ImageIO.read(new File("res/Images/back-button.png"));
    private JButton back = new JButton(new ImageIcon(backIcon));

    //Array hodling exercise data
    private static String userOutputs[][] = new String[500][5];

    // private static String selectOutputs[][] = new String[6][5];
    private static ArrayList<String[]> selectOutputs = new ArrayList<String[]>();

    //Data array for graphing
    static String[] rawLoad = new String[1000];

    //Button to allow the graphing of data
    private JButton graph = new JButton("Graph");

    //Various fonts used within the program
    private Font font = new Font("Sylfaen", Font.BOLD, 60);
    private Font font2 = new Font("Sylfaen", Font.BOLD, 30);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

    //gif used in GUI
    private JLabel img1 = new JLabel(new ImageIcon("res/Images/heartbeat.gif"));

    //A label for the GUIs screen title
    private JLabel screenTitle = new JLabel("Cardio Tracker");

    //A button allowing the user to save their progress
    private JButton save = new JButton("Save");

    //A label for the data entry area
    private JLabel date = new JLabel("Date");

    //Label that lets the user know to enter the day of the month
    private JLabel day = new JLabel("Day:");

    //Lable that lets the user know to enter the month of the year
    private JLabel month = new JLabel("Month:");

    //Label for the distance entry field
    private JLabel distance = new JLabel("Distance:");

    //A label for the time entry field for the user
    private JLabel time = new JLabel("Time:");

    //A label letting the user know to select an exercise
    private JLabel exercise = new JLabel("Exercise:");

    //A label for the heart rate entry field
    private JLabel heartRate = new JLabel("Heart Rate:");

    //Array of months
    public static String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    //Variables to allow access of true date
    private int year = Calendar.getInstance().get(Calendar.YEAR);

    //gets the enumerated month field (corresponding to the month of the year)
    private int monthField = Calendar.getInstance().get(Calendar.MONTH);

    //Gets the name of the month from the computer
    private String dayField = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

    static int index = 0;

    //Constructor Method
    public Cardio() throws IOException, FontFormatException {

        //when a new instance of this GUI is opened, clears the exercise field
        if (FitnessProjectTest.cardioExercises.size() > 0) {

            FitnessProjectTest.cardioExercises.clear();

        }

        System.out.println(LoginScreen.currentUser + " fjdklsajfkdsla");

        //initalize the frame
        setLayout(null);
        setBounds(0, 0, 1000, 600);
        this.setTitle("Cardio");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/Images/RedGradient.jpg")))));

        //do not allow resizing and allow closing when "x" is clicked
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set siz of frame
        setSize(1000, 600);
        repaint();

        //setup all the exercises into the array
        FitnessProjectTest.initalizeCardioExercises();

        //Sets up the title of the screen
        screenTitle.setForeground(Color.BLACK);
        screenTitle.setFont(font);
        screenTitle.setBounds(263, 10, 475, 65);
        add(screenTitle);

        //add the gif
        img1.setBounds(200, 280, 250, 253);
        add(img1);

        //label for the date
        date.setForeground(Color.BLACK);
        date.setFont(font2);
        date.setBounds(20, 100, 200, 35);
        add(date);

        //label indicating to enter the day of the month
        day.setForeground(Color.BLACK);
        day.setFont(font3);
        day.setBounds(50, 210, 75, 20);
        add(day);

        //label indicating to enter the month for the user
        month.setForeground(Color.BLACK);
        month.setFont(font3);
        month.setBounds(50, 150, 75, 20);
        add(month);

        //label used to tell user to echose the exercise
        exercise.setForeground(Color.BLACK);
        exercise.setFont(font2);
        exercise.setBounds(605, 100, 200, 35);
        add(exercise);

        //label for distance
        distance.setForeground(Color.BLACK);
        distance.setFont(font3);
        distance.setBounds(635, 150, 200, 35);
        add(distance);

        //label for time
        time.setForeground(Color.BLACK);
        time.setFont(font3);
        time.setBounds(635, 210, 200, 35);
        add(time);

        //label for heart rate
        heartRate.setForeground(Color.BLACK);
        heartRate.setFont(font3);
        heartRate.setBounds(635, 270, 200, 35);
        add(heartRate);

        /*
         * Then graph buttons is made to become larger as they are hovered upon.
         * Added them to the screen.
         *
         * Does not have the default border and layout of a JButton.
         */
        graph.setOpaque(false);
        graph.setContentAreaFilled(false);
        graph.setBorderPainted(false);
        graph.setBorder(null);
        graph.setFont(new Font("Segoe Script", Font.PLAIN, 40));
        graph.setForeground(Color.BLACK);
        graph.setBounds(740, 400, 250, 75);
        add(graph);
        graph.addActionListener(this);

        // Makes button bigger when hovered over
        graph.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                graph.setForeground(Color.BLACK);
                graph.setFont(new Font("Segoe Script", Font.PLAIN, 70));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                graph.setForeground(Color.BLACK);
                graph.setFont(new Font("Segoe Script", Font.PLAIN, 40));
            }
        });

        /*
         * The save button is made to become larger as they are hovered upon.
         * Added them to the screen.
         *
         * Does not have the default border and layout of a JButton.
         */
        save.setOpaque(false);
        save.setContentAreaFilled(false);
        save.setBorderPainted(false);
        save.setBorder(null);
        save.setFont(new Font("Segoe Script", Font.PLAIN, 40));
        save.setForeground(Color.BLACK);
        save.setBounds(765, 475, 200, 75);
        add(save);
        save.addActionListener(this);

        // Makes button bigger when hovered over
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                save.setForeground(Color.BLACK);
                save.setFont(new Font("Segoe Script", Font.PLAIN, 70));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                save.setForeground(Color.BLACK);
                save.setFont(new Font("Segoe Script", Font.PLAIN, 40));
            }
        });

        /*
         * Adds the icon buttons/hoverable items.
         */
        back.setBorder(BorderFactory.createEmptyBorder());
        back.setContentAreaFilled(false);
        back.setBounds(10, 10, 100, 76);
        add(back);
        back.addActionListener(this);

        //sets the test that is to be shown when hovered upon
        back.setToolTipText("Go back to the info screen.");

        button1.setBorder(BorderFactory.createEmptyBorder());
        button1.setContentAreaFilled(false);
        button1.setBounds(590, 155, 30, 30);
        add(button1);
        button1.addActionListener(this);

        //sets the test that is to be shown when hovered upon
        button1.setToolTipText("Distance should be in kilometers!");

        button2.setBorder(BorderFactory.createEmptyBorder());
        button2.setContentAreaFilled(false);
        button2.setBounds(590, 210, 30, 30);
        add(button2);
        button2.addActionListener(this);

        //sets the test that is to be shown when hovered upon
        button2.setToolTipText("Time should be in minutes!");

        button3.setBorder(BorderFactory.createEmptyBorder());
        button3.setContentAreaFilled(false);
        button3.setBounds(590, 270, 30, 30);
        add(button3);
        button3.addActionListener(this);

        //sets the test that is to be shown when hovered upon
        button3.setToolTipText("Heart rate should be in BPM!");

        add.setBorder(BorderFactory.createEmptyBorder());
        add.setContentAreaFilled(false);
        add.setBounds(570, 102, 30, 30);
//        add(add);
        add.addActionListener(this);

        //sets the test that is to be shown when hovered upon
        add.setToolTipText("Add new exercises!");

        //calls the ethod used to setup the textfields
        textFieldSetup();

        ////       load("res/cardioFile"+ LoginScreen.currentUser +".txt");
        //
        //       load("res/cardioFile3.txt");

        //shows all components of the GUI
        setVisible(true);
        repaint();
    }

    //initalizes all of the exercises into the array that wil be used with the combo box, and all related fields
    public void textFieldSetup() {

        //makes the month combo box and sets it to the current month
        userInputs2[0] = new JComboBox(months);
        userInputs2[0].setSelectedIndex(monthField);
        userInputs2[0].addActionListener(this);
        userInputs2[0].setBounds(125, 142, 235, 40);
        add(userInputs2[0]);

        //makes the days combo box and sets it to the current day
        userInputs2[1] = new JComboBox(daysGenerator());
        userInputs2[1].setSelectedItem(dayField);
        userInputs2[1].addActionListener(this);
        userInputs2[1].setBounds(100, 202, 260, 40);
        add(userInputs2[1]);

        System.out.println(FitnessProjectTest.cardioExercises.size() + "                      FJKDLSAJKLFDASJKLFDJSKLFDSJAKLFDJSKLA");
        userInputs2[2] = new JComboBox(FitnessProjectTest.cardioExercises.toArray());
        userInputs2[2].setSelectedItem(0);
        userInputs2[2].addActionListener(this);
        userInputs2[2].setBounds(755, 100, 225, 40);
        add(userInputs2[2]);

        /*
         * error checking for all fields on the cardio GUI (does not allow non numeric entries)
         */
        userInputs1[0] = new JTextField(20);
        userInputs1[0].addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        userInputs1[0].setBounds(730, 150, 250, 40);
        add(userInputs1[0]);
        TextFieldListener tfListener = new TextFieldListener();
        userInputs1[0].addActionListener(tfListener);

        userInputs1[1] = new JTextField(20);
        userInputs1[1].addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        userInputs1[1].setBounds(695, 210, 285, 40);
        add(userInputs1[1]);
        TextFieldListener tf1Listener = new TextFieldListener();
        userInputs1[1].addActionListener(tf1Listener);

        userInputs1[2] = new JTextField(20);
        userInputs1[2].addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        userInputs1[2].setBounds(745, 268, 235, 40);
        add(userInputs1[2]);
        TextFieldListener tf2Listener = new TextFieldListener();
        userInputs1[2].addActionListener(tf2Listener);

    }

    //Writes the related data to the associated file
    private void save() {

        try {

            /* This logic is to create the file if the
             * file is not already present
             */
            if (!FitnessProjectTest.cardioFiles[LoginScreen.currentUser].exists()) {
                FitnessProjectTest.cardioFiles[LoginScreen.currentUser].createNewFile();
            }

            //Here true is to append the content to file
            FileWriter fWriter = new FileWriter(FitnessProjectTest.cardioFiles[LoginScreen.currentUser], true);
            PrintWriter pWriter = new PrintWriter(fWriter);

            for (int x = 0; x < userInputs2.length; x++) {

                pWriter.write(String.valueOf(userInputs2[x].getSelectedItem()) + ",");

            }

            for (int y = 0; y < userInputs1.length; y++) {

                pWriter.write(userInputs1[y].getText() + ",");

            }

            pWriter.println();

            //Closing PrintWriter Stream
            pWriter.close();

        } catch (IOException ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        }
    }

    //Allows the laoding of data (MOSTLY USED BY GRAPHING)
    private static void load(String fileName) throws IOException {

        Path path = Paths.get(fileName);
        Scanner scanner = new Scanner(path);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //           System.out.println(line);
            //           userOutputs[index][0] = line;
            //       rawLoad.add(line);
            rawLoad[index] = line;

            userOutputs[index] = line.split(",");

            index++;
        }
        //
        //    String temp = "1,January,ghj,ghj,hkjl,";
        //
        //    ArrayList<String> tempS = new ArrayList<String>();
        //    rawLoad = temp.split(",");

        int selectingIndex = 0;
        for (int x = 0; x < index; x++) {

            for (int y = 0; y < 5; y++) {

                //          if (userOutputs[x][y].equals("running")) {

                //               System.out.println(userOutputs[x][y]);
                selectOutputs.add(userOutputs[x]);
                selectingIndex++;
                //        }

            }
        }

        for (int x = 0; x < selectOutputs.size(); x++) {

            String[] f = new String[5];
            f = selectOutputs.get(x);

            for (int y = 0; y < 5; y++) {

                System.out.println(f[y]);

            }
        }
    }

    //generates the days that corresponds to the month
    public String[] daysGenerator() {

        int temp = userInputs2[0].getSelectedIndex();
        String[] daysTemp;

        if (temp == 1) {
            if (isLeapYear())
                daysTemp = new String[29];
            else
                daysTemp = new String[28];
        } else if (bigMonth((temp + 1))) {
            daysTemp = new String[31];
        } else {
            daysTemp = new String[30];
        }

        for (int i = 1; i <= daysTemp.length; i++) {
            daysTemp[i - 1] = Integer.toString(i);
        }

        return daysTemp;
    }

    //determines what months have 31/30 days
    public boolean bigMonth(int month) {
        int[] arr = {1, 3, 5, 7, 8, 10, 12};
        for (int n : arr) {
            if (month == n) {
                return true;
            }
        }
        return false;
    }

    //Checks if the current year is a lea year
    public boolean isLeapYear() {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0)
                    return true;
                return false;
            }
            return true;
        }
        return false;
    }

    //pop up message when this error occurs
    public void saveError(String description) {
        JTextArea textArea = new JTextArea(description);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(200, 50));
        JOptionPane.showMessageDialog(textArea, scrollPane, "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == save) {

            if (userInputs1[0].getText().equals("") || userInputs1[1].getText().equals("") || userInputs1[2].getText().equals("")) {
                saveError("Please fill in all fields before pressing SAVE!");
            } else {
                save();


                userInputs1[0].setText("");
                userInputs1[1].setText("");
                userInputs1[2].setText("");


                try {
                    load("res/cardioFile" + LoginScreen.currentUser + ".txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        if (e.getSource() == graph) {

            System.out.println("res/cardioFile" + LoginScreen.currentUser + ".txt" + " fjdkfdjsaljdfskaljfdkslajfdkslajfdkslajfdkslajfdkslalsajfkdsla");

            try {
                load("res/cardioFile" + LoginScreen.currentUser + ".txt");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            new Graph(userOutputs, selectOutputs);
            dispose();

        }

        if (e.getSource() == back) {

            dispose();
            InfoScreen frame = null;
            try {
                frame = new InfoScreen();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            frame.setVisible(true);

        }

        if (e.getSource() == add) {
            dispose();

            if (FitnessProjectTest.exercises.size() > 0) {

                FitnessProjectTest.exercises.clear();

            }

            if (FitnessProjectTest.weightliftingExercises.size() > 0) {

                FitnessProjectTest.weightliftingExercises.clear();

            }

            try {
                new NewExercise();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class TextFieldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}