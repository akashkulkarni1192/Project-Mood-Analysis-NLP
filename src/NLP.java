import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import org.tartarus.snowball.ext.PorterStemmer;

public class NLP {
	Scanner scan = new Scanner(System.in);
	public String receiveInput() {
		String msg ;
		System.out.println("Enter message : ");
		msg = scan.nextLine();
		return msg;
	}

	public String[] obtainSentences(String msg) {
		InputStream modelIn = null;
		String sentences[] = null;
		try {
			modelIn = new FileInputStream("en-sent.bin");
			SentenceModel model = new SentenceModel(modelIn);
			SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
			sentences = sentenceDetector.sentDetect(msg);
		} 
		catch (Exception e) {
		  e.printStackTrace();
		}
		return sentences;
	}

	public String[] obtainTokens(String sentence) {
		String tokens[] = null;
		try {
			InputStream modelIn = new FileInputStream("en-token.bin");
			TokenizerModel model = new TokenizerModel(modelIn);
			Tokenizer tokenizer = new TokenizerME(model);
			tokens = tokenizer.tokenize(sentence);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		return tokens;
	}

	public String obtainStem(String token) {
		String stem;
		PorterStemmer stemmer = new PorterStemmer();
		stemmer.setCurrent(token); 
		stemmer.stem();
		stem = stemmer.getCurrent();
		return stem;
	}

	public Vector obtainTokenVector(String stem) {
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("readiedCorpus.txt"));
			HashMap corpus = new HashMap();
			corpus = (HashMap) in.readObject();
			if(corpus.containsKey(stem))
				return (Vector) corpus.get(stem);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public String obtainPOS(String stem) {
		String tag=null;
		try {
			InputStream modelIn = null;
			modelIn = new FileInputStream("en-pos-maxent.bin");
			POSModel model = new POSModel(modelIn);
			POSTaggerME tagger = new POSTaggerME(model);
			tag = tagger.tag(stem);
			tag = tag.split("/")[1];
		}
		catch (IOException e) {
		  // Model loading failed, handle the error
		  e.printStackTrace();
		}
		return tag;
	}
}
