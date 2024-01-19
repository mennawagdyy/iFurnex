import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SignUp implements ActionListener {//the signup function assumes all users entered would have different names
    JPasswordField jPasswordField;
    JTextField userText= new JTextField(20);
    JPasswordField passwordField= new JPasswordField(20);
    SignUp()//sign up page
    {
        JFrame frame= new JFrame("Sign up to iFurnex");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel= new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        JLabel userLabel= new JLabel("Username");
        userLabel.setBounds(40, 30, 80, 25);
        panel.add(userLabel);
        JLabel errorLabel= new JLabel();

        userText.setBounds(120, 30, 165, 25);
        panel.add(userText);


        JLabel passwordLabel= new JLabel("Password");
        passwordLabel.setBounds(40, 60, 80, 25);
        panel.add(passwordLabel);

        passwordField.validate();
        passwordField.setBounds(120, 60, 165, 25);
        panel.add(passwordField);
        JFrame unsuccessful= new JFrame();//if username or password are not entered, display this frame and terminate when closed
        JLabel unsuccessfulLabel= new JLabel("Incorrect Entry");
        unsuccessful.add(unsuccessfulLabel);
        unsuccessful.setSize(150,80);
        unsuccessful.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton signUpButton= new JButton("Sign Up");
        signUpButton.setBounds(40, 100, 80, 25);
        panel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getText().length()==0 || userText.getText().length()==0)
                {
                    unsuccessful.setVisible(true);
                }else//if username and password are entered, write them to the users file
                {
                    try {
                        writeToUsersCSV(userText, passwordField);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    new SignIn();//go to sign in page
                    frame.setVisible(false);
                }
                panel.remove(errorLabel);
            }
        });
        frame.setVisible(true);
    }

    static void writeToUsersCSV(JTextField userText, JPasswordField passwordField) throws IOException {//this function writes new user info to users file
        FileReader fr = new FileReader("UsersFile.csv");
        String str = "";
        int i;
        while ((i = fr.read()) != -1) {
            str += (char)i;
        }
        FileWriter fileWriter= new FileWriter("UsersFile.csv");
        fileWriter.write(str);
        fileWriter.write("\n");
        fileWriter.write(userText.getText());
        fileWriter.write(",");
        fileWriter.write(passwordField.getText());
        fileWriter.write("\n");
        fileWriter.close();
        fr.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
