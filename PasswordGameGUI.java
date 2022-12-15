
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Luis Munguia
 * passwordGameGUI will:
 * set-up the GUI interface,
 * has 3 private classes: PasswordListSelectionListener, PasswordButtonListener,
 * ScoreButtonListener
 * The game has the user enter a phrase, and based on the phrase entered
 * it will generate a password, also allows to generate a score from 1 - 10
 * giving the user indication on how difficult the password generated is.
 * the game will teach users - in a fun way - to enter phrases that are
 * difficult so that they can get
 * a high score
 * VSC
 */

public class PasswordGameGUI extends JFrame {
  // private fields: panels
  private JPanel panel1 = new JPanel();
  private JPanel panel2 = new JPanel();
  private JPanel panel3 = new JPanel();
  private JPanel panel4 = new JPanel();
  private JPanel panel5 = new JPanel();
  private JPanel panel6 = new JPanel();

  // private fields: Labels for stars

  private JLabel s1 = new JLabel();
  private JLabel s2 = new JLabel();
  private JLabel s3 = new JLabel();
  private JLabel s4 = new JLabel();
  private JLabel s5 = new JLabel();
  private JLabel s6 = new JLabel();
  private JLabel s7 = new JLabel();
  private JLabel s8 = new JLabel();
  private JLabel s9 = new JLabel();
  private JLabel s10 = new JLabel();
  // private fields: Labels
  private JLabel gameInfoLabel1 = new JLabel("***Enter a phrase to generate a password***");
  private JLabel gameInfoLabel2 = new JLabel(
      "***You will get a score (1-10) based on how 'strong' the generated password is***");
  private JLabel gameInfoLabel3 = new JLabel("Note: your score is based on the most recent phrase generated");
  private JLabel enterPhraselabel3 = new JLabel("Enter a phrase: ");
  private JLabel passwordListLabel4 = new JLabel("Password List: ");
  private JLabel scoreLabel5 = new JLabel("Score:");
  private JLabel generatePasswordLabel6 = new JLabel("Generated Password: ");
  private JLabel phraseLabel7 = new JLabel("Click to view phrases");
  // private fields: textbox
  private JTextField starsField = new JTextField(10);
  private JTextField enterPhraseField = new JTextField(20);
  private JTextField generatePasswordField = new JTextField(30);
  // private fields: list
  DefaultListModel<String> listModel = new DefaultListModel();
  private JList<String> passwordList = new JList(listModel);
  // private fields: buttons
  private JButton getScoreButton = new JButton("Get Score");
  private JButton getPasswordButton = new JButton("Generate Password");
  private JButton getPhrasesButton = new JButton("Get Phrases");
  // private fields: class aggregation
  private PasswordGenerator passwordGenerator = new PasswordGenerator();
  private ScoreGenerator scoreGenerator = new ScoreGenerator();

  /**
   * constructor
   */

  public PasswordGameGUI() {
    // the JFrame constructor
    super("<<Password Game>>");

    // size of window
    setSize(875, 600);

    // specify an action for the close button
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // set layout for frame
    setLayout(new GridLayout(6, 1));
    // make stars field and generated password field not-editable
    starsField.setEditable(false);
    generatePasswordField.setEditable(false);

    // make password list scrollable and set dimensions
    JScrollPane scrollPane = new JScrollPane(passwordList);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    passwordList.setPreferredSize(new Dimension(300, 250));
    scrollPane.setPreferredSize(new Dimension(300, 90));

    // add event listener to getScore button
    getScoreButton.addActionListener(new ScoreButtonListener());

    // add event listener to generate password button
    getPasswordButton.addActionListener(new PasswordButtonListener());

    // add evet listener to password list
    passwordList.addListSelectionListener(new PasswordListSelectionListener());

    // add event listener to getPhraseButton
    getPhrasesButton.addActionListener(new PhrasesButtonListener());

    // Center align text: I'm very proud that I figured out how to do this
    gameInfoLabel1.setHorizontalAlignment(JLabel.CENTER);
    gameInfoLabel2.setHorizontalAlignment(JLabel.CENTER);
    gameInfoLabel3.setHorizontalAlignment(JLabel.CENTER);
    scoreLabel5.setHorizontalAlignment(JLabel.CENTER);
    starsField.setHorizontalAlignment(JLabel.CENTER);
    generatePasswordField.setHorizontalAlignment(JLabel.CENTER);
    generatePasswordLabel6.setHorizontalAlignment(JLabel.CENTER);
    panel1.setLayout(new GridLayout(5, 1));

    // add elements to panel
    panel1.add(gameInfoLabel1);
    panel1.add(gameInfoLabel2);
    panel1.add(gameInfoLabel3);
    panel2.add(enterPhraselabel3);
    panel2.add(enterPhraseField);
    panel3.add(getScoreButton);
    panel3.add(getPasswordButton);
    panel4.add(passwordListLabel4);
    panel4.add(scrollPane);
    panel5.add(scoreLabel5);
    // add star labels
    panel5.add(s1);
    panel5.add(s2);
    panel5.add(s3);
    panel5.add(s4);
    panel5.add(s5);
    panel5.add(s6);
    panel5.add(s7);
    panel5.add(s8);
    panel5.add(s9);
    panel5.add(s10);
    // add elements
    panel5.add(generatePasswordLabel6);
    panel5.add(generatePasswordField);
    panel6.add(phraseLabel7);
    panel6.add(getPhrasesButton);

    // add panels to window
    add(panel1);
    add(panel2);
    add(panel3);
    add(panel4);
    add(panel5);
    add(panel6);

    setVisible(true);

  }

  /**
   * ScoreButtonListener will:
   * utilize generatescore class to return a score
   * stars will be added based on score of password.
   * it will handle if no password has been generated
   */

  private class ScoreButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // to handle empty password list
      if (passwordList.getModel().getSize() == 0) {
        JOptionPane.showMessageDialog(null, "You must generate a password first");
      } else {
        // get recent password in list
        int index = passwordList.getModel().getSize();

        // get password value
        String password = passwordList.getModel().getElementAt(index - 1);

        // enter passwrod to scoreGenerator class and retrieve score
        int score = scoreGenerator.generateScore(password);
        // get Image
        ImageIcon icon = new ImageIcon("star.png");
        // create JLabel array
        JLabel[] starLabelsArr = { s1, s2, s3, s4, s5, s6, s7, s8, s9, s10 };
        // remove any existing stars
        for (int i = 0; i < starLabelsArr.length; i++) {
          starLabelsArr[i].setIcon(null);
        }

        // based on score, add stars to labels
        for (int i = 0; i < score; i++) {
          starLabelsArr[i].setIcon(icon);
        }
      }
    }

  }

  /**
   * PasswordButtonListener will:
   * utilize passwordGenerator class to generate password out of entered phrase
   * it will return a password and add it to the list
   */
  private class PasswordButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (enterPhraseField.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "You must enter a phrase first");
      } else {
        String password = passwordGenerator.GeneratePass(enterPhraseField.getText());
        listModel.addElement(password);
        generatePasswordField.setText(password);
        try {
          passwordGenerator.writeToFile();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    }
  }

  /**
   * PasswordListSelectionListener will:
   * update the generatePasswordField with the selected password in the List.
   */
  private class PasswordListSelectionListener implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
      generatePasswordField.setText((String) passwordList.getSelectedValue());
    }

  }

  /**
   * PhrasesButtonListener will:
   * open a 2nd window displaying a list of all phrases entered
   * 
   * @param args
   */

  private class PhrasesButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      // handle edge case: if no phrases have been entered
      if (passwordList.getModel().getSize() == 0) {
        JOptionPane.showMessageDialog(null, "Phrases window will be shown once a password has been generated");
      } else {
        // call the secondWindowGUI
        try {
          new secondWindowGUI();
        } catch (IOException e1) {
          // to handle exception
          e1.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    new PasswordGameGUI();
  }

}
