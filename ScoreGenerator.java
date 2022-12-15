
/**
 * Luis Munguia
 * 2/15/22
 * ScoreGenerator allows for a score to be generated based on
 * the strength of the password: Length, special characters, numbers.
 * It has built in method to display the score.
 */

public class ScoreGenerator {
  private int score; // to hold score
  private int numOfSpecialChars; // to hold num of special characters
  private int numOfNumbs; // to hold num of integers
  private String[] specialChar = { "!", "@", "#", "$", "%", "^", "&", "*", "+" }; // holds special characters
  private String[] numbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9" }; // holds numbers

  // constructor
  ScoreGenerator() {
    System.out.println("ScoreGenerator class has been initialized");
  }

  /**
   * GenerateScore will:
   * iterate through password and score difficulty of
   * password based on length, numbers,special characters
   */
  public int generateScore(String password) {
    // reset score
    score = 0;
    numOfSpecialChars = 0;
    numOfNumbs = 0;
    // get password length
    int passLength = password.length();
    // split password string to array
    String[] passwordArr = password.split("(?<=\\G.)");
    // outer for loop will iterate through password
    for (int i = 0; i < passwordArr.length; i++) {
      // inner for loop will iterate through specialChar arr
      for (String x : specialChar) {
        if (x.equals(passwordArr[i])) {
          numOfSpecialChars += 1; // add to
          passLength -= 1;
        }
      }
      // inner for loop will iterate through numbers arr
      for (String y : numbers) {
        if (y.equals(passwordArr[i])) {
          System.out.println("Inside digits" + y);
          numOfNumbs += 1; // add to
          passLength -= 1;
        }
      }
    }
    // determine score based on special characters
    if (numOfSpecialChars == 0) {
      score -= 1;
    } else if (numOfSpecialChars == 1) {
      score += 1;
    } else if (numOfSpecialChars == 2) {
      score += 2;
    } else if (numOfSpecialChars >= 3) {
      score += 2.5;
    }

    // determine score based on integers
    if (numOfNumbs == 0) {
      score -= 1;
    } else if (numOfNumbs == 1) {
      score += 1;
    } else if (numOfNumbs == 2) {
      score += 2;
    } else if (numOfNumbs >= 3) {
      score += 2.5;
    }
    // determine score based on length
    if (passLength >= 10) {
      score += 6;
    } else if (passLength <= 9 && passLength >= 8) {
      score += 3;
    } else if (passLength <= 7 && passLength >= 6) {
      score += 2;
    } else if (passLength < 6) {
      score += 1;
    }

    // if score == 0: show 1 star to give user hint that it is a bad password
    if (score <= 0) {
      score = 1 + numOfNumbs + numOfSpecialChars;
    }

    // return score of password
    return score;
  }

  /**
   * displayScore will:
   * return the latest score of generated password
   * 
   */
  public String displayScore() {
    return Integer.toString(score);
  }

  // public static void main(String[] args) {
  // }
}
