package expandingtext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ExpandingText {

	private static String originalString = "";
	private static String newString = "";
	private static String decreasingString = originalString;
	
	private static File output = new File("src/resources/output.txt");
	
	private static StringBuilder sb = new StringBuilder(decreasingString);
	
	private static void expandText() {
		
		PrintWriter pWriter;
		try {
			pWriter = new PrintWriter(output);
			pWriter.print("");
			pWriter.close();
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
		}
		
		for(int i = 0; i < originalString.length(); i++) {
			if(originalString.charAt(i) != ' ') {
				newString += originalString.charAt(i);
				try {
					FileWriter writer = new FileWriter(output, true);
					writer.write(newString);
					writer.write("\n");
					writer.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println(newString);
			} else {
				newString += " ";
			}
		}
		for(int i = originalString.length() - 1; i > 0; i--) {
			if(originalString.charAt(i) != ' ') {
				sb.deleteCharAt(i);
				decreasingString = sb.toString();
				try {
					FileWriter writer = new FileWriter(output, true);
					writer.write(decreasingString);
					writer.write("\n");
					writer.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println(decreasingString);
			} else {
				sb.deleteCharAt(i);
				decreasingString = sb.toString();
			}
		}
	}
	
	public static void main(String[] args) {
		expandText();
	}
	
}
