import java.lang.String;

/**
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a
 * move and therefore the other person will be the winner.
 * <p>
 * Write a function to compute all possible states of the string after one valid move.
 * <p>
 * For example, given s = "++++", after one move, it may become one of the following states:
 * <p>
 * [
 * "--++",
 * "+--+",
 * "++--"
 * ]
 * If there is no valid move, return an empty list [].
 **/

public class Solution {
  public List<String> generatePossibleNextMoves(String s) {
    List<String> res = new ArrayList<String>();
    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
        res.add(s.substring(0, i) + "--" + s.substring(i + 2));
      }
    }
    return res;
  }
}