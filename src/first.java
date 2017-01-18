import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Scanner;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import org.tartarus.snowball.ext.PorterStemmer;

public class first 
{

	private static final String NULL = null;


	public void SentenceDetect() throws InvalidFormatException,IOException
	{
		String paragraph = NULL;
		Scanner in=new Scanner(System.in);
		paragraph=in.nextLine();
	/*	
		//String[] paragraph = new String[20];
		
		// always start with a model, a model is learned from training data
		InputStream is = new FileInputStream("en-sent.bin");
		SentenceModel model = new SentenceModel(is);
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
 
		String sentences[] = sdetector.sentDetect(paragraph);
		
 		int i;
// 		for(i=0;i<sentences.length;i++)
// 			System.out.println(sentences[i]);
 		for(String s:sentences)
 			System.out.println(s);
		
		//System.out.println(sentences[1]);
		is.close();
		
	*/			
		//Tokenising begins
		InputStream os = new FileInputStream("en-token.bin");
		TokenizerModel model1 = new TokenizerModel(os);
		Tokenizer tokenizer = new TokenizerME(model1);
		String tokens[] = tokenizer.tokenize(paragraph);
		System.out.println("\n\n\n------------TOKENISED --------------");
		for (String a : tokens)
			System.out.println(a);
	 
		os.close();
		//Tokenising katham
		
		
		
		
		
		POSSample sample;
		//Parts Of Speech Tagging startttt
		
		System.out.println("\n\n\n --------POS TAGGER--------");
		
		POSModel model = new POSModelLoader()	
		.load(new File("en-pos-maxent.bin"));
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);
		
		String input = paragraph;
		ObjectStream<String> lineStream = new PlainTextByLineStream(
				new StringReader(input));

		perfMon.start();
		String line;
		while ((line = lineStream.read()) != null) {

			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
					.tokenize(line);
			String[] tags = tagger.tag(whitespaceTokenizerLine);

			sample = new POSSample(whitespaceTokenizerLine, tags);
			System.out.println(sample.toString());

			perfMon.incrementCounter();
		}
		perfMon.stopAndPrintFinalResult();
		//Parts Of Speech Tagging katham
		
		
			
	//	EnglishSnowballStemmerFactory.getInstance().process("buyers") : String;
				
		PorterStemmer stemmer = new PorterStemmer();
		String d[]= new String[20];
		
		System.out.println("\n\n\n -----------STEMMED WORDS--------");
		for(int i = 0;i<tokens.length;i++)
		{
			stemmer.setCurrent(tokens[i]); //set string you need to stem
			//System.out.println(tokens[i] + "is the word sent to stem");
			stemmer.stem();  //stem the word
			d[i] = stemmer.getCurrent();//get the stemmed word
			System.out.println(d[i]);
	
		
		}
			
		algo x=new algo();

		for(int i = 0;i<tokens.length;i++)
		{
			
			x.func(d[i]);
		}
		x.result();
		
		System.out.println("Resuuuuult");
			
			for(int h = 0;h<5;h++)
			{
				System.out.print(x.resultm[h]+" ");
			}
		System.out.println();
		
		
		
						
	/*	TestJAWS t=new TestJAWS();
		for(int i = 0;i<tokens.length;i++)
		{
			t.jwnlfunc(d[i]);
		}
				
		in.close();*/
	}
	
	
	public static void main(String[] args) 
	{
		first f = new first();
		try 
		{
			f.SentenceDetect();
		} 
		catch (InvalidFormatException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}




