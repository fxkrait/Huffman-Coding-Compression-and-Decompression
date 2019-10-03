/* Gregory Hablutzel
 * CS 211 Summer 2018
 * August 7th
 * Bellevue College
 * HW: EXCR
 * [BJP 4th Page 1106 Project #9 "Huffman Coding"]
 *
 * This class implements the "Huffman Coding" compression algorithm using
 * priority queues and binary trees. Huffman coding is an algorihm devised 
 * by David A. Huffman of MIT in 1952 for compressing text data to make a file
 * occupy a smaller number of bytes. Normally text data is stored in a standard 
 * format of 8 bits per character, commonly using an encoding called ASCII that
 * maps every character to a binary integer value from 0-255. The idea of Huffman 
 * coding is to abandon the rigid 8-bits-per-character requirement and use 
 * different-length binary encodings for different characters. The advantage of 
 * doing this is that if a character occurs frequently in the file, such as the 
 * letter "e", it could be given a shorter encoding (fewer bits), making the file 
 * smaller.

 * I have also added the following functionality in addition to the specification.
     1. Printing of the Huffman Tree
     2. Decoding of the encoded text
     3. Outputting percent of compression.
  */

import java.util.*;
import java.io.*;

public class Huffman {
   private static String INPUT;
   private static String ENCODED;
   private static String DECODED;
   public static void main(String[] args) throws FileNotFoundException {
      Huffman huf = new Huffman("input.txt", "encoded.txt", "decoded.txt");
      huf.run();
      Huffman huf2 = new Huffman("input2.txt", "encoded2.txt", "decoded2.txt");
      huf2.run();	
      Huffman huf3 = new Huffman("input3.txt", "encoded3.txt", "decoded3.txt");
      huf3.run();
      Huffman huf4 = new Huffman("input4.txt", "encoded4.txt", "decoded4.txt");
      huf4.run();
   }

    // runs the Huffman encoding and decoding.
   public void run() throws FileNotFoundException{
      printFile(INPUT);
      TreeMap<String, HuffmanNode> map = processMap(INPUT);
      System.out.println("Input Frequency Map:" + map);
      PriorityQueue<HuffmanNode> pq = processQueue(map);
      HuffmanNode tree = processTree(pq);
   
      System.out.println("Binary Tree: ");
      printSideways(tree);
   
      Map<String, String> encoding = encode(tree);
      System.out.println("Encoding Map: ");
      System.out.println(encoding);
   
      encodeFile(encoding);
   
      Map<String, String> decoding = reverseMap(encoding);
      System.out.println("Decoding Map: ");
      System.out.println(decoding);
   
      decodeFile(decoding, tree);
   
      fileSize(Arrays.asList(INPUT, ENCODED, DECODED));
   
      printCompression();
   }

    // Constructs a new Huffman object, taking in the given input, decoded, and encoded
    // file names.
   public Huffman(String input, String encoded, String decoded) throws FileNotFoundException {
      INPUT = input;
      ENCODED = encoded;
      DECODED = decoded;
   }

   // Prints the file being read.
   public static void printFile(String input) throws FileNotFoundException {
      System.out.println("Input file is:");
      System.out.print("\"");
      Scanner file = new Scanner(new File(input));
      while (file.hasNextLine()) {
         System.out.print(file.nextLine());
      }
      System.out.println("\"");
   
   }

   // Prints the percent that the Huffman Coding compressed the input file.
   public static void printCompression() {
      File inputFile = new File(INPUT);
      File outputFile = new File(ENCODED);
      double compression = 1.0 * inputFile.length() / outputFile.length();
      compression = round(compression);
      System.out.println("You have compressed the file: " + compression * 100 + "%!");
   }

    // rounds a given percent to two decimal places.
   private static double round(double num) {
      return Math.round(num * 100) / 100.0;
   }

    // prints the file size of a given file.
   public static void fileSize(List<String> files) {
      for (int i = 0; i < files.size(); i++) {
         System.out.print("fileSize(" + files.get(i) + ") : ");
         File file = new File(files.get(i));
       
         if (file.exists()){
            System.out.println("bytes : " + file.length());
         } else {
            System.out.println("File does not exist!");
         }
      }
   }

    // Decodes the given file using the Huffman Tree, and outputs it as a new file.
   public static void decodeFile(Map<String, String> decoding, HuffmanNode tree) throws FileNotFoundException {
      PrintStream output = new PrintStream(new File(DECODED));
      BitInputStream input = new BitInputStream(ENCODED);
   // traverse decoding map
      String bitVal = "";
      HuffmanNode current = tree;
      int bit = -1;
      do {
         if (current.left != null && current.right != null) {
            bit = input.readBit();
            bitVal += Integer.toString(bit);
            if (bit == 0) {
               current = current.left;
            } else {
               current = current.right;
            }
         } else { // at leaf node
            output.print(decoding.get(bitVal));
            bitVal = "";
            current = tree;
         }
      }
      while (bit != -1);
   }
    
    // Generates and returns a reverseMap used for decoding.
    // Maps the bits, to a character.
   public Map<String, String> reverseMap(Map<String, String> encoding) {
      Map<String, String> decoding = new TreeMap<String, String>();
      for(String key: encoding.keySet()) {
         decoding.put(encoding.get(key), key);
      }
      return decoding;
   }

    // Encodes the input file to the output file using the Huffman Coding algorithm.
   public void encodeFile(Map<String, String> encoding) throws FileNotFoundException {
      BitOutputStream output = new BitOutputStream(ENCODED);
      Scanner file = new Scanner(new File(INPUT));
      while (file.hasNextLine()) {
         String line = file.nextLine();
         for(int i = 0; i < line.length(); i++) {
            String c = line.substring(i, i + 1); //a, b, c, etc
            String charCode = encoding.get(c);
            for (int j = 0; j < charCode.length(); j++) {
               output.writeBit(Integer.parseInt(charCode.substring(j, j + 1)));// exception
            }
         }
      }
   }

    // Creates and returns the map of the Huffman Encoding of the file.
    // The map contains the character, and the bits it represents.
   public Map<String, String> encode(HuffmanNode tree) {
      Map<String, String> encoding = new TreeMap<String, String>();
      encode(tree, encoding, "-1");
      return encoding;
   }
    
   // post: prints in reversed preorder the tree with given
    //       root, indenting each line to the given level
   private void encode(HuffmanNode root, Map<String, String> encoding, String current) {
      if (root != null) {
         String left;
         String right;
       // fencepost (first run)
         if (current.equals("-1")) {
            left = "0";
            right = "1";
         } else {
            left = current + "0";
            right = current + "1";
         }
          // add it to map, recurse left and right
         if (root.letter != null) {
            encoding.put(root.letter, current);
         }
         encode(root.left, encoding, left);
         encode(root.right, encoding, right);
      }
   }

    // Creates a binary tree in the form of a HuffmanNode, using the values from the
    // Priority Queue.
    // It removes two nodes at a time from the queue, combines them into a new node
    // containing the combined frequency, and the two nodes being the childs.
    // This continues until only 1 node is left in the queue, and that is the completed
    // binary tree.
   public HuffmanNode processTree(PriorityQueue<HuffmanNode> pq) throws FileNotFoundException {
      while (pq.size() >= 2) {
         HuffmanNode n1 = pq.remove();
         HuffmanNode n2 = pq.remove();
         int freq = n1.frequency + n2.frequency;
         HuffmanNode n3 = new HuffmanNode(null, freq, n1, n2);
         pq.add(n3);
       
      }
      return pq.remove();
   }
     
    // post: prints the tree contents, one per line, following an
    //       inorder traversal and using indentation to indicate
    //       node depth; prints right to left so that it looks
    //       correct when the output is rotated.
   public void printSideways(HuffmanNode overallRoot) {
      printSideways(overallRoot, 0);
   }
    
    // post: prints in reversed preorder the tree with given
    //       root, indenting each line to the given level
   private void printSideways(HuffmanNode root, int level) {
      if (root != null) {
         printSideways(root.right, level + 1);
         for (int i = 0; i < level; i++) {
            System.out.print("    ");
         }
         System.out.println(root);
         printSideways(root.left, level + 1);
      }
   }

    // Takes a string indicating the input file, and creates and returns a map of the
    // characters and their frequencies.. 
   public TreeMap<String, HuffmanNode> processMap(String input) throws FileNotFoundException {
      Scanner file = new Scanner(new File(input));
      TreeMap<String, HuffmanNode> map = new TreeMap<String, HuffmanNode>();
   
      while (file.hasNextLine()) {
         String line = file.nextLine();
         for(int i = 0; i < line.length(); i++) {
            String c = line.substring(i, i + 1);
            if (map.containsKey(c)) {
               map.get(c).frequency++;
            } else {
               map.put(c, new HuffmanNode(c));
            }
         }
      }
      return map;
   }

    // Takes a map of all the characters in the text and their frequency, and populates
    // and returns a new PriorityQueue containing these values.
   public PriorityQueue<HuffmanNode> processQueue(TreeMap<String, HuffmanNode> map) throws FileNotFoundException {
      PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
      for (String s: map.keySet()) {
         pq.add(map.get(s));
      }
      return pq;
   }
}
