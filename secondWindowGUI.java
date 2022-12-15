import java.awt.*;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;

/**
 * Luis Munguia
 * 3/7/2022
 * VSC
 * This class's purpose is to retrieve saved passwords in the "allphrases.txt"
 * file
 * It will then generate a GUI Window displaying the phrases in a list'
 */
public class secondWindowGUI extends JFrame {
  private JPanel panel1 = new JPanel();
  private JPanel panel2 = new JPanel();
  private JLabel info = new JLabel(
      "**** Note: close window and click 'Get Phrases' button AGAIN to refresh phrases list ****");
  DefaultListModel<String> list = new DefaultListModel();
  private JList<String> phrases = new JList(list);

  public secondWindowGUI() throws IOException {
    // call super class
    super("<<<All Phrases>>>");

    // size of window
    setSize(875, 600);

    // specify an action for the close button
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // set layout for frame
    setLayout(new GridLayout(2, 1));

    // center align text and list
    info.setHorizontalAlignment(JLabel.CENTER);
    // phrases.setHorizontalAlignment(JLabel.CENTER);

    // make phrases list scrollable and set dimensions
    JScrollPane scrollPane = new JScrollPane(phrases);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    phrases.setPreferredSize(new Dimension(500, 350));
    scrollPane.setPreferredSize(new Dimension(500, 100));

    // set layout for panel1
    panel1.setLayout(new GridLayout(2, 1));

    // add elements to panel
    panel1.add(info);
    panel2.add(scrollPane);

    // get all phrases in random access file
    getAllPhrases();

    // add panels to window
    add(panel1);
    add(panel2);

    setVisible(true);

  }

  private void getAllPhrases() throws IOException {
    File file = new File("AllPhrases.txt");
    Scanner readFile = new Scanner(file);

    // read lines from the file until no more are left
    while (readFile.hasNext()) {
      // read the next phrase
      String phrase = readFile.nextLine();
      list.addElement(phrase);
    }
    // close the file
    readFile.close();
  }

  public static void main(String[] args) throws IOException {
    new secondWindowGUI();
  }
}
