import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Luis Munguia
 * 2/15/22
 * PasswordGenerator allows the user to enter a phrase to
 * its GeneratePass method: which will generate a password
 * using an alogirthm. The strength of the password will depend on the phrase.
 * It also has other built in methods to get previous phrases and passwords
 * VSC
 */

public class PasswordGenerator {
  private ArrayList<String> phrases; // to hold phrases
  private ArrayList<String> passwords; // to hold passwords
  private String[] specialChar = { "!", "@", "#", "$", "%", "^", "&", "*", "+" }; // holds special characters
  private String[] numbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9" }; // holds numbers

  /**
   * Constructor
   */
  public PasswordGenerator() {
    this.phrases = new ArrayList<String>();
    this.passwords = new ArrayList<String>();
    System.out.println("PasswordGenerator Class has been Initialized");
  }

  /**
   * GeneratePass will:
   * take in a string "phrase" as input
   * it will create an array out of "phrase"
   * it will assign a char from "phrase2" to "tempArr" at random index
   * it will handle edge cases for white space; use "specialChar" or "numbers"
   * it will find new random index if char has already been assigned to that index
   * it will convert the "tempArr" to a string and add it to "passwords" array
   */
  public String GeneratePass(String phrase) {
    Random random = new Random(); // initialize random number generator class
    phrases.add(phrase); // add phrase to phrases array
    String[] phrase2 = phrase.split("(?<=\\G.)"); // convert string to array and split @ character
    String[] tempArr = new String[phrase2.length]; // create a temp array for password

    int index = random.nextInt(phrase2.length); // generate a number 0 to phrase
    Boolean boo = true; // For use of specialChar or numbers
    int specialIndex = 0;

    for (int i = 0; i < phrase2.length; i++) {
      // tempArr index has to be empty
      if (tempArr[index] == null) {
        // if phrase contains a empty string
        if (phrase2[i].isBlank()) {
          // add special character
          if (boo) {
            specialIndex = random.nextInt(specialChar.length);// get random index
            tempArr[index] = specialChar[specialIndex]; // assign random char
            boo = false;
          }
          // add number
          else {
            specialIndex = random.nextInt(numbers.length); // get random index
            tempArr[index] = numbers[specialIndex]; // asign random number
            boo = true;
          }
        }
        // assign char at index
        else {
          tempArr[index] = phrase2[i];
        }

      }
      // tempArr index is not empty
      else {
        // generate random number until unassigned index is found
        while (tempArr[index] != null) {
          index = random.nextInt(phrase2.length);
        }
        // stay at same index in for-loop
        i--;
      }
    }

    String convertedPassword = ""; // to hold converted password

    // Convert tempArr to a string
    for (String i : tempArr) {
      convertedPassword += i;
    }

    passwords.add(convertedPassword); // add converted password to passwords array
    return convertedPassword;
  }

  /**
   * getNumOfPasswords will:
   * return number of passwords generated
   */
  public int getNumOfPasswords() {
    return passwords.size();
  }

  /**
   * displayAllPass will:
   * iterate through passwords array and return a string of all passwords
   */

  public String displayAllPass() {
    String allPasswords = "";
    for (int i = 0; i < passwords.size(); i++) {
      allPasswords += "Password " + (i + 1) + ": " + passwords.get(i) + " ";
    }
    return allPasswords;
  }

  /**
   * DisplayAllPhra will:
   * iterate though phrases array and return a string of all phrases
   */

  public String displayAllPhra() {
    String allPhrases = "";
    for (int i = 0; i < phrases.size(); i++) {
      allPhrases += "Phrase " + (i + 1) + ": " + phrases.get(i) + " ";
    }
    return allPhrases;
  }

  /**
   * getRecentPass will:
   * get the most recent appened password from the password list
   * 
   */
  public String getRecentPass() {
    int index = getNumOfPasswords();
    return passwords.get(index - 1);
  }

  /**
   * getRecentPhra will:
   * get the most recent phrase appended to phrase list
   */

  public String getRecentPhra() {
    int index = getNumOfPasswords();
    return phrases.get(index - 1);
  }

  /**
   * writeToFile will:
   * create a new file and then subsequently
   * add a new phrase to the file everytime a password is
   * generated in the Game
   * 
   * @throws IOException
   */

  public void writeToFile() throws IOException {
    if (getNumOfPasswords() - 1 == 0) {
      System.out.println("Inn new");
      PrintWriter allPhrases = new PrintWriter("./AllPhrases.txt");
      allPhrases.close();
    }
    FileWriter fw = new FileWriter("./AllPhrases.txt", true);
    PrintWriter allPhrases = new PrintWriter(fw);
    allPhrases.println(getRecentPhra());
    allPhrases.close();
  }

  /**
   * 
   */

  // public static void main(String[] args) {
  // // demonstrate passwordGenerator works
  // PasswordGenerator temp = new PasswordGenerator();
  // String pass = temp.GeneratePass("Hello everyone How are you doing today");
  // System.out.println("Password: " + pass);
  // System.out.println("All passwords: " + temp.displayAllPass());
  // System.out.println("All phrases: " + temp.displayAllPhra());
  // System.out.println("Number of passwords: " + temp.getNumOfPasswords());
  // System.out.println("Recent password: " + temp.getRecentPass());
  // System.out.println("");
  // // demonstrate ScoreGenerator works
  // ScoreGenerator temp2 = new ScoreGenerator();
  // int score = temp2.GenerateScore(temp.getRecentPass());
  // System.out.println("Score: " + score);
  // }

  // public static void main(String[] args) {
  // PasswordGenerator temp = new PasswordGenerator();
  // System.out.println(temp.getNumOfPasswords());
  // }
}