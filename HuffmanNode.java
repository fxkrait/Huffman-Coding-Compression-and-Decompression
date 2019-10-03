/* Gregory Hablutzel
 * CS 211 Summer 2018
 * August 7th
 * Bellevue College
 * HW: EXCR
 * [BJP 4th Page 1106 Project #9 "Huffman Coding"]
 *
 * This class implements the Huffman Node as part of the "Huffman Coding".
 * Each node stores a letter, a frequency of the letter, and a left and right
 * pointer. It also implements a comparable interface, and has a toString method.
 */

public class HuffmanNode implements Comparable<HuffmanNode> {
   public String letter;
   public int frequency;
   public HuffmanNode left;
   public HuffmanNode right;

    // Constructs a new HuffmanNode, with the given letter, frequency, and left and right
    // children.
   public HuffmanNode(String letter, int frequency, HuffmanNode left, HuffmanNode right) {
      this.letter = letter;
      this.frequency = frequency;
      this.left = left;
      this.right = right;
   }

    // Constructs a new HuffmanNode with the given letter and frequency, with no children.
   public HuffmanNode(String letter, int frequency) {
      this(letter, frequency, null, null);
   }

    // Constructs a new HuffmanNode with a given letter, a frequency of 1, and no children.
   public HuffmanNode(String letter) {
      this(letter, 1);
   }

    // Takes a HuffmanNode, and returns the current Node's frequency minus the
    // given nodes frequency.
    // This will sort HuffmanNodes in Descending order.
   public int compareTo(HuffmanNode other) {
      return frequency - other.frequency;
   }

    // Returns a string containing the frequency and letter of the HuffmanNode,
    // separated by a comma and contained in brackets.
   public String toString() {
      return ("[" + frequency + ", " + letter + "]");
   }
}
