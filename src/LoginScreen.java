import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginScreen extends JFrame implements ActionListener {

    //Varibale that keeps track of the user that is logged in
    public static int currentUser = 0;

    //an image icon responsible for backtracking to the previous sceen
    private BufferedImage backIcon = ImageIO.read(new File("res/Images/back-button.png"));
    private JButton back = new JButton(new ImageIcon(backIcon));

    //All the labels
    private JLabel screenTitle = new JLabel("Sign Into Tracker");
    private JLabel subScreenTitle = new JLabel("Login");
    private JLabel usernameLabel = new JLabel("Username:");
    private JLabel passwordLabel = new JLabel("Password:");
    private JCheckBox checkBox = new JCheckBox("Show Password");

    private JTextField username;
    private JPasswordField password;

    private Font font = new Font("Sylfaen", Font.BOLD, 60);
    private Font font2 = new Font("Sylfaen", Font.PLAIN, 30);
    private Font font3 = new Font("Sylfaen", Font.PLAIN, 20);

    private JButton login = new JButton("Login");

    public LoginScreen() throws IOException {

        setLayout(null);
        setBounds(0, 0, 1000, 600);
        this.setTitle("Login");
        setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/Images/login.jpg")))));

        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1000, 600);
        repaint();

        back.setBorder(BorderFactory.createEmptyBorder());
        back.setContentAreaFilled(false);
        back.setBounds(10, 10, 100, 76);
        add(back);
        back.addActionListener(this);
        back.setToolTipText("Go back to the home screen.");

        screenTitle.setForeground(Color.BLACK);
        screenTitle.setFont(font);
        screenTitle.setBounds(250, 10, 575, 70);
        add(screenTitle);

        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setFont(font2);
        usernameLabel.setBounds(310, 125, 200, 35);
        add(usernameLabel);

        username = new JTextField(20);
        username.setBounds(480, 125, 250, 40);
        add(username);
        TextFieldListener tf1Listener = new TextFieldListener();
        username.addActionListener(tf1Listener);

        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(font2);
        passwordLabel.setBounds(315, 200, 200, 35);
        add(passwordLabel);

        password  = new JPasswordField(20);
        password.setEchoChar('*');
        password.setBounds(480, 200, 250, 40);
        add(password);
        TextFieldListener tf2Listener = new TextFieldListener();
        password.addActionListener(tf2Listener);

        login.setOpaque(true);
        login.setBorderPainted(false);
        login.setFont(new Font("Segoe Script", Font.PLAIN, 30));
        login.setForeground(Color.BLACK);
        login.setBounds(400, 300, 200, 50);
        add(login);
        login.addActionListener(this);

        checkBox.setFont(font3);
        checkBox.setBounds(490, 250, 200, 25);
        checkBox.addActionListener(this);
        add(checkBox);

        setVisible(true);

    }

    private boolean validateCredentials() throws IOException {

        CreateAccount.load(FitnessProjectTest.userDataFile.toString());

        String tempUsername = username.getText();
        String tempPassword = password.getText();

        for(int x = 0; x < CreateAccount.userArray.size(); x++){

            if(tempUsername.equals(CreateAccount.userArray.get(x).getUsername()) && tempPassword.equals(CreateAccount.userArray.get(x).getPassword())) {
                currentUser = x;
                return true;
            }

        }
        return false;
    }

    public void loginError(String description) {
        JTextArea textArea = new JTextArea(description);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(300, 50));
        JOptionPane.showMessageDialog(textArea, scrollPane, "Login Error", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == login){

            try {

                if (validateCredentials() == true) {

                    login.setBackground(Color.green);
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {

                                    login.setBackground(Color.WHITE);
                                    InfoScreen frame = null;
                                    try {
                                        frame = new InfoScreen();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    frame.setVisible(true);
                                    dispose();

                                }
                            },
                            1000
                    );

                } else {

                    login.setBackground(Color.red);
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {

                                    login.setBackground(Color.WHITE);

                                }
                            },
                            1000
                    );

                    loginError("Username and/or password is incorrect!");

                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        if(e.getSource() == checkBox){

            if(checkBox.isSelected() == true)
                password.setEchoChar((char)0);
            else
                password.setEchoChar('*');
        }

        if(e.getSource() == back){
            dispose();
            new HomeScreen();
            HomeScreen frame = new HomeScreen();
            frame.setVisible(true);
        }
    }

    private class TextFieldListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}