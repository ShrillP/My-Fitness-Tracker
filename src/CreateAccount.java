import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CreateAccount extends JFrame implements ActionListener{

    private static String userOutputs[][] = new String[500][27];

    private static ArrayList <String[]>selectOutputs = new ArrayList <String[]>();

    public static ArrayList <User> userArray = new ArrayList <User>();

    static int index = 0;

    private static JComboBox exerciseInput;

    private Font font = new Font("Sylfaen", Font.BOLD, 60);
    private Font font2 = new Font("Sylfaen", Font.BOLD, 30);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

    private static JTextField goalInput = new JTextField();
    private static JTextField usernameInput = new JTextField();
    private static JPasswordField passwordInput;
    private JCheckBox checkBox = new JCheckBox("Show Password");
    private static JTextField ageInput= new JTextField();
    private static JTextField weightInput = new JTextField();
    private static JButton addGoal = new JButton("Add Goal");
    private static JButton createAccountButton = new JButton("Create User");

    private BufferedImage buttonIcon1 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button1 = new JButton(new ImageIcon(buttonIcon1));

    private BufferedImage buttonIcon2 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button2 = new JButton(new ImageIcon(buttonIcon2));

    private BufferedImage buttonIcon3 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button3 = new JButton(new ImageIcon(buttonIcon3));

    private BufferedImage buttonIcon4 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button4 = new JButton(new ImageIcon(buttonIcon4));

    private BufferedImage buttonIcon5 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button5 = new JButton(new ImageIcon(buttonIcon5));

    private BufferedImage buttonIcon6 = ImageIO.read(new File("res/Images/info.png"));
    private JButton button6 = new JButton(new ImageIcon(buttonIcon6));

    private BufferedImage backIcon = ImageIO.read(new File("res/Images/back-button.png"));
    private JButton back = new JButton(new ImageIcon(backIcon));

    private JLabel img = new JLabel(new ImageIcon("res/Images/user-id-icon.png"));

    private JLabel screenTitle = new JLabel("Create Personal User");
    private JLabel credentials = new JLabel("Credentials:");
    private JLabel username = new JLabel("Username:");
    private JLabel password = new JLabel("Password:");
    private JLabel perosnalInfo = new JLabel("Personal Info:");
    private JLabel age = new JLabel("Age:");
    private JLabel weight = new JLabel("Weight:");
    private JLabel goals = new JLabel("Goals:");
    private JLabel exercise = new JLabel("Exercise:");
    private JLabel goalValue = new JLabel("Goal Value:");

    private ArrayList<String> allExercises = new ArrayList<String>();

    int goalValues[] = new int[23];

    public CreateAccount () throws IOException {

        setLayout(null);
        setBounds(0, 0, 1000, 600);
        this.setTitle("Create Personal User");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/Images/gradient.png")))));

        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        back.setBorder(BorderFactory.createEmptyBorder());
        back.setContentAreaFilled(false);
        back.setBounds(10, 10, 100, 76);
        add(back);
        back.addActionListener(this);
        back.setToolTipText("Go back to the home screen.");

        checkBox.setFont(font3);
        checkBox.setBounds(200, 250, 200, 25);
        checkBox.addActionListener(this);
        add(checkBox);

        img.setBounds(650, 300, 300, 300);
        add(img);

        screenTitle.setForeground(Color.BLACK);
        screenTitle.setFont(font);
        screenTitle.setBounds(175, 10, 650, 65);
        add(screenTitle);

        credentials.setForeground(Color.BLACK);
        credentials.setFont(font2);
        credentials.setBounds(20, 100, 200, 35);
        add(credentials);

        username.setForeground(Color.BLACK);
        username.setFont(font3);
        username.setBounds(80, 165, 150, 20);
        add(username);

        button1.setBorder(BorderFactory.createEmptyBorder());
        button1.setContentAreaFilled(false);
        button1.setBounds(40, 162, 30, 30);
        add(button1);
        button1.addActionListener(this);
        button1.setToolTipText("Enter a username that will be associated with your account.");

        usernameInput.setForeground(Color.BLACK);
        usernameInput.setFont(font3);
        usernameInput.setBounds(190, 163, 250, 30);
        add(usernameInput);

        password.setForeground(Color.BLACK);
        password.setFont(font3);
        password.setBounds(80, 220, 150, 20);
        add(password);

        button2.setBorder(BorderFactory.createEmptyBorder());
        button2.setContentAreaFilled(false);
        button2.setBounds(40, 217, 30, 30);
        add(button2);
        button2.addActionListener(this);
        button2.setToolTipText("Enter a secure and memorable password.");

        passwordInput  = new JPasswordField(20);
        passwordInput.setEchoChar('*');
        passwordInput.setBounds(183, 218, 255, 30);
        add(passwordInput);
        TextFieldListener tf2Listener = new TextFieldListener();
        passwordInput.addActionListener(tf2Listener);

        perosnalInfo.setForeground(Color.BLACK);
        perosnalInfo.setFont(font2);
        perosnalInfo.setBounds(20, 300, 300, 35);
        add(perosnalInfo);

        age.setForeground(Color.BLACK);
        age.setFont(font3);
        age.setBounds(80, 365, 75, 25);
        add(age);

        button3.setBorder(BorderFactory.createEmptyBorder());
        button3.setContentAreaFilled(false);
        button3.setBounds(40, 363, 30, 30);
        add(button3);
        button3.addActionListener(this);
        button3.setToolTipText("Enter your current age.");

        ageInput.setForeground(Color.BLACK);
        ageInput.setFont(font3);
        ageInput.setBounds(130, 365, 250, 30);
        ageInput.addKeyListener(new KeyAdapter() {
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

        add(ageInput);

        weight.setForeground(Color.BLACK);
        weight.setFont(font3);
        weight.setBounds(80, 420, 100, 25);
        weightInput.addKeyListener(new KeyAdapter() {
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

        add(weight);

        button4.setBorder(BorderFactory.createEmptyBorder());
        button4.setContentAreaFilled(false);
        button4.setBounds(40, 418, 30, 30);
        add(button4);
        button4.addActionListener(this);
        button4.setToolTipText("Enter your current weight.");

        weightInput.setForeground(Color.BLACK);
        weightInput.setFont(font3);
        weightInput.setBounds(155, 418, 225, 30);
        add(weightInput);

        goals.setForeground(Color.BLACK);
        goals.setFont(font2);
        goals.setBounds(600, 100, 200, 35);
        add(goals);

        exercise.setForeground(Color.BLACK);
        exercise.setFont(font3);
        exercise.setBounds(660, 165, 100, 25);
        add(exercise);

        button5.setBorder(BorderFactory.createEmptyBorder());
        button5.setContentAreaFilled(false);
        button5.setBounds(620, 163, 30, 30);
        add(button5);
        button5.addActionListener(this);
        button5.setToolTipText("Select exercise to set a goal for.");

        goalValue.setForeground(Color.BLACK);
        goalValue.setFont(font3);
        goalValue.setBounds(660, 220, 150, 20);
        add(goalValue);
        goalInput.addKeyListener(new KeyAdapter() {
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


        button6.setBorder(BorderFactory.createEmptyBorder());
        button6.setContentAreaFilled(false);
        button6.setBounds(620, 218, 30, 30);
        add(button6);
        button6.addActionListener(this);
        button6.setToolTipText("Enter numeric goal for exercise.");

        goalInput.setForeground(Color.BLACK);
        goalInput.setFont(font3);
        goalInput.setBounds(775, 218, 210, 30);
        add(goalInput);

        addGoal.setOpaque(false);
        addGoal.setContentAreaFilled(false);
        addGoal.setBorderPainted(false);
        addGoal.setBorder(null);
        addGoal.setFont(new Font("Segoe Script", Font.PLAIN, 30));
        addGoal.setForeground(Color.BLACK);
        addGoal.setBounds(675, 275, 250, 75);
        add(addGoal);
        addGoal.addActionListener(this);

        // Makes button bigger when hovered over
        addGoal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addGoal.setForeground(Color.BLACK);
                addGoal.setFont(new Font("Segoe Script", Font.PLAIN, 40));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                addGoal.setForeground(Color.BLACK);
                addGoal.setFont(new Font("Segoe Script", Font.PLAIN, 30));
            }
        });

        createAccountButton.setOpaque(false);
        createAccountButton.setContentAreaFilled(false);
        createAccountButton.setBorderPainted(false);
        createAccountButton.setBorder(null);
        createAccountButton.setFont(new Font("Segoe Script", Font.PLAIN, 30));
        createAccountButton.setForeground(Color.BLACK);
        createAccountButton.setBounds(50, 475, 300, 75);
        add(createAccountButton);
        createAccountButton.addActionListener(this);

        // Makes button bigger when hovered over
        createAccountButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createAccountButton.setForeground(Color.BLACK);
                createAccountButton.setFont(new Font("Segoe Script", Font.PLAIN, 40));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                createAccountButton.setForeground(Color.BLACK);
                createAccountButton.setFont(new Font("Segoe Script", Font.PLAIN, 30));
            }
        });


        FitnessProjectTest.initalizeWeightliftingExercises();
        exerciseInput = new JComboBox(FitnessProjectTest.exercises.toArray());

        exerciseInput.setForeground(Color.BLACK);
        exerciseInput.setBounds(750, 165, 240, 30);
        exerciseInput.addActionListener(this);
        add(exerciseInput);

        setVisible(true);
        repaint();

    }

    private void allExercises(){

//       allExercises.addAll(FitnessProjectTest.exercises);
        //    allExercises.addAll(Cardio.cardioExercises);

    }

    private void save() {

        try {

            /* This logic is to create the file if the
             * file is not already present
             */
            if (!FitnessProjectTest.userDataFile.exists()) {
                FitnessProjectTest.userDataFile.createNewFile();
            }

            //Here true is to append the content to file
            FileWriter fWriter = new FileWriter(FitnessProjectTest.userDataFile, true);
            PrintWriter pWriter = new PrintWriter(fWriter);

            pWriter.write(usernameInput.getText() + ",");
            pWriter.write(passwordInput.getText() + ",");
            pWriter.write(ageInput.getText() + ",");
            pWriter.write(weightInput.getText() + ",");

            for (int x = 0; x < goalValues.length; x++) {

                pWriter.write(goalValues[x] + ",");

            }

            pWriter.println();

            //Closing PrintWriter Stream
            pWriter.close();

        } catch (IOException ioe) {
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
        }
    }

    static void load(String fileName) throws IOException{

        index = 0;

        userArray.clear();
        selectOutputs.clear();


        Path path = Paths.get(fileName);

        Scanner scanner = new Scanner(path);

        while(scanner.hasNextLine()){

            String line = scanner.nextLine();

            userOutputs[index] = line.split(",");

            index++;

        }

        int selectingIndex = 0;

        for (int x = 0; x < index; x++) {

            selectOutputs.add(userOutputs[x]);
            selectingIndex++;
        }

        for (int x = 0; x < selectOutputs.size(); x++) {

            String[] f = new String[27];

            f = selectOutputs.get(x);

            for (int y = 0; y < 27; y++) {


//          System.out.println(f[y]);

            }
        }



        for (int x = 0; x < selectOutputs.size(); x++) {

            String[] f = new String[27];

            f = selectOutputs.get(x);

            int tempGoalsArray[] = new int[23];

            for (int y = 0; y < 23; y++) {

                tempGoalsArray[y] = Integer.parseInt(f[y+4]);

            }

            for (int g = 0; g < tempGoalsArray.length; g++) {
//          System.out.println(tempGoalsArray[g]);

            }

            userArray.add(new User(f[0], f[1], Integer.parseInt(f[2]), Integer.parseInt(f[3]), tempGoalsArray));

        }

    }

    public void saveError(String description){
        JTextArea textArea = new JTextArea(description);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize( new Dimension( 200, 50 ) );
        JOptionPane.showMessageDialog(textArea, scrollPane, "Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == createAccountButton) {

//    	   private static JTextField usernameInput = new JTextField();
//    	   private static JTextField passwordInput = new JTextField();
//    	   private static JTextField ageInput= new JTextField();
//    	   private static JTextField weightInput = new JTextField();

            if(usernameInput.getText().equals("") || passwordInput.getText().equals("") || ageInput.getText().equals("") || weightInput.getText().equals("")){
                saveError("Please fill in all fields before pressing SAVE!");
            } else {
                save();
                try {
                    load("res/userDataFile.txt");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }

        if (e.getSource() == addGoal) {

            if (goalInput.getText().equals("")) {

            } else {

                int index = exerciseInput.getSelectedIndex() - 1;
                goalValues[index] = Integer.parseInt(goalInput.getText());
                goalInput.setText("");

            }
        }

        if(e.getSource() == back){
            dispose();
            new HomeScreen();
            HomeScreen frame = new HomeScreen();
            frame.setVisible(true);
        }

        if(e.getSource() == checkBox){

            if(checkBox.isSelected() == true)
                passwordInput.setEchoChar((char)0);
            else
                passwordInput.setEchoChar('*');
        }
    }

    private class TextFieldListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}