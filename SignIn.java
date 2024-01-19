import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
public class SignIn extends JFrame implements ActionListener {

    JPasswordField jPasswordField;
    JTextField userText= new JTextField(20);
    JPasswordField passwordField= new JPasswordField(20);

    SignIn()//create sign in frame
    {
        JFrame frame= new JFrame("Sign in to iFurnex");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel= new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        JLabel userLabel= new JLabel("Username");
        userLabel.setBounds(40, 30, 80, 25);
        panel.add(userLabel);

        userText.setBounds(120, 30, 165, 25);
        panel.add(userText);

        JLabel passwordLabel= new JLabel("Password");
        passwordLabel.setBounds(40, 60, 80, 25);
        panel.add(passwordLabel);

        passwordField.setBounds(120, 60, 165, 25);
        panel.add(passwordField);

        JFrame unsuccessful= new JFrame();//this frame is visible when login is unsuccessful to terminate program
        JLabel unsuccessfulLabel= new JLabel("Incorrect Entry");
        unsuccessful.add(unsuccessfulLabel);
        unsuccessful.setSize(150,80);
        unsuccessful.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton loginButton= new JButton("Login");
        loginButton.setBounds(40, 100, 80, 25);
        panel.add(loginButton);
        JLabel successfulLogIn = new JLabel();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getText().length()>0 && userText.getText().length()>0)//check that text has been entered for both username and password
                {
                    panel.remove(successfulLogIn);
                    successfulLogIn.setBounds(120, 120, 200, 25);
                    panel.add(successfulLogIn);
                    try {
                        if (readUsersCSV(userText, passwordField)) {//check if username and password are correct
                            successfulLogIn.setText("Login is successful.");
                            frame.setVisible(false);
                            Furniture.readProductsCSV();//if yes, move to furniture frame
                            Furniture.displayProducts();
                        }else
                        {
                            successfulLogIn.setText("Unsuccessful, please try again.");//if not successful, display message
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }else {
                    System.out.println("Incomplete");
                    unsuccessful.setVisible(true);//if nothing has been entered, show new frame and terminate when closed
                }
            }
        });
        JButton signUpButton= new JButton("Sign up");
        signUpButton.setBounds(40, 130, 80, 25);
        panel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new SignUp();//move to sign up frame if button is clicked
            }
        });
        frame.setVisible(true);
    }

    static boolean readUsersCSV(JTextField userText, JPasswordField passwordField) throws IOException {
        String path = "UsersFile.csv";

        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = "";

        while ((line = bufferedReader.readLine()) != null)//go through Users file and read usernames and passwords
        {
            String [] values = line.split(",");
            if (values[0].equals(userText.getText()))//if username in file is equal to username entered
            {
                if (values[1].equals(passwordField.getText()))//check if password is the same in the same row and return true
                {
                    System.out.println(values[0]);
                    System.out.println(values[1]);
                    return true;
                }
                else//otherwise return false
                {
                    System.out.println(values[0]);
                    System.out.println(values[1]);
                    return false;
                }
            }
        }
        fileReader.close();
        return false;
    }


    public static void main(String[] args) throws IOException {
        SignIn signInPage= new SignIn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
