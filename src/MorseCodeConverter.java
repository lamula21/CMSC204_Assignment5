import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author pjose
 *
 */
public class MorseCodeConverter {

	static MorseCodeTree morseTree = new MorseCodeTree();
	
	
	
	
	
	/**
	 * Returns a string with all the data in the tree in LNR order with an space in between them.
	 * @return String - all letters from the MorseCodeTree
	 */
	public static String printTree() {
		
		ArrayList<String> arrayTree = morseTree.toArrayList();
		String printOut = "";
		for (int i = 0; i<arrayTree.size(); i++) {
			printOut += arrayTree.get(i) + " ";
		}
		return printOut.trim();
	}
	
	
	
	
	
	/**
	 * Converts Morse code into English.
	 * @param code
	 * @return a String
	 */
	public static String convertToEnglish(String code) {
		
		// If code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
		
		String[] letters;
		String[] words = code.split(" / ");	// words = { ".... . .-.. .-.. ---", ".-- --- .-. .-.. -.." }
		String wordDecrypted = "";

		
		
		for (String eachWord : words) {
			letters = eachWord.split(" ");	// letters = {"...." , "." , ".-.." , ".-.." , "---"}

			for (String eachLetter: letters)
				wordDecrypted += morseTree.fetch(eachLetter);	// WordDecrypted = "Hello"

			wordDecrypted += " ";	// WordDecrypted = "Hello ", then go to the second word
		}

	
		// return WordDecrypted = "Hello World"
		return wordDecrypted.trim();
	}
	
	
	
	
	/**
	 * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File file) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(file);
		String fileCode = "";
		
		while(scanner.hasNextLine()) 
			fileCode += scanner.nextLine() + "\n";
		
		scanner.close();
		
		return convertToEnglish(fileCode.trim());
		}
}
