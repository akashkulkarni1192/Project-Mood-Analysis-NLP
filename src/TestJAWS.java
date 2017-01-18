import java.util.Scanner;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;

/**
 * Displays word forms and definitions for synsets containing the word form
 * specified on the command line. To use this application, specify the word
 * form that you wish to view synsets for, as in the following example which
 * displays all synsets containing the word form "airplane":
 * <br>
 * java TestJAWS airplane
 */
public class TestJAWS
{

	/**
	 * Main entry point. The command-line arguments are concatenated together
	 * (separated by spaces) and used as the word form to look up.
	 */
	static Scanner scan = new Scanner(System.in);
	public void jwnlfunc(String wordForm)
	{
//		if (args.length > 0)
//		{
			//  Concatenate the command-line arguments
//			StringBuffer buffer = new StringBuffer();
//			String s;
//			for (int i = 0; i < args.length; i++)
//			{
//				buffer.append((i > 0 ? " " : "") + args[i]);
//			}
//			String wordForm = buffer.toString();
		System.setProperty("wordnet.database.dir", "/home/akash/programmingArena/Weapons/externalJavaLibraries/NLP/WordNet-3.0/dict/");
			//String wordForm=null;	//  Get the synsets containing the wrod form
			WordNetDatabase database = WordNetDatabase.getFileInstance();
//			System.out.println("ENter word :");
//			wordForm = scan.nextLine();
			Synset[] synsets = database.getSynsets(wordForm);
			
			//  Display the word forms and definitions for synsets retrieved
			if (synsets.length > 0)
			{
				System.out.println("The following synsets contain '" +
						wordForm + "' or a possible base form " +
						"of that text:");
				for (int i = 0; i < synsets.length; i++)
				{
					System.out.println("");
					String[] wordForms = synsets[i].getWordForms();
					for (int j = 0; j < wordForms.length; j++)
					{
						System.out.print(wordForms[j]+"\t");
					}
					//System.out.println(": " + synsets[i].getDefinition());
				}
			}
			else
			{
				System.err.println("No synsets exist that contain " +
						"the word form '" + wordForm + "'");
			}
//		}
//		else
//		{
//			System.err.println("You must specify " +
//					"a word form for which to retrieve synsets.");
//		}
	}
	
	
	
//	
//	public static void main(String[] args) {
//		TestJAWS t=new TestJAWS();
//		t.jwnlfunc("ecstasy");
//	}
	
	
	

}