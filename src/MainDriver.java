import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;


public class MainDriver {

	String input;
	String sentences[],tokens[];
	Vector outputVector = new Vector();
	WordNetDatabase database;
	ObjectInputStream in;
	HashMap corpus;
	
	public static void main(String[] args) {
		MainDriver driver = new MainDriver();
		//driver.buildCorpus();
		driver.readyTheCorpus();
		driver.readyTheDatabase();
		driver.startNLP();
		driver.predictMood();
	}
	private void readyTheDatabase() {
		System.setProperty("wordnet.database.dir", "/home/akash/programmingArena/Weapons/externalJavaLibraries/NLP/WordNet-3.0/dict/");
		database = WordNetDatabase.getFileInstance();	
	}
	private HashSet<String> getSynonyms(String word){
		HashSet<String> synonyms = new HashSet();
		Synset[] sets ;
        sets = database.getSynsets(word);
        System.out.println("Collected Synsets :"+sets.length);
        for(Synset s:sets)
        	for(String e:s.getWordForms())
        		synonyms.add(e);
        return synonyms;
	}
	private void predictMood() {
		HashMap<Integer,String> mood = new HashMap<Integer, String>();
		mood.put(0,"HAPPY" );
		mood.put(1,"SAD" );
		mood.put(2,"ANGRY" );
		mood.put(3,"SURPRISE" );
		int arr[] = {outputVector.happy,outputVector.sad,outputVector.angry,outputVector.surprise};
		int max = -1,m=-1,flag=0,match=0;
		HashSet<Integer> multiMood = new HashSet<Integer>();
		for(int i=0;i<4;i++){
//			if(Math.abs(max-arr[i])<=2){
//				multiMood.add(i);
//				match = 1;
//			}
//			else 
			if(arr[i]>max && arr[i]>0){
				m=i;
				max = arr[i];
				flag=1;
				match = 0;
			}
	
		}
//		if(match==1){
//			for(int i:multiMood)
//				System.out.println("Mood : "+ mood.get(i));
//		}
		if(flag==1)
			System.out.println("\n\n\tMood : "+mood.get(m));
		else{
			System.out.println("\n\n\tInsufficient Data");
			System.out.println("Enter the word to train the machine : ");
			this.buildCorpus();
		}
	}
	private void readyTheCorpus() {
		//System.out.println("Corpus\nWORD\t:\tVECTOR");
		try{
			in = new ObjectInputStream(new FileInputStream("readiedCorpus.txt"));
			corpus = new HashMap();
			corpus = (HashMap) in.readObject();
			for(Object key : corpus.keySet()){
				System.out.println(key +"\t:\t"+corpus.get(key) );
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void startNLP() {
		int i;
		String stem = null,pos=null;
		NLP nlpProcessor = new NLP();
		//System.out.println(nlpProcessor.obtainStem("hell"));
		Vector tokenVector = new Vector();
		HashSet<String> negators = new HashSet<String>();
		negators.add("not");
		negators.add("never");
		negators.add("neither");
		negators.add("nor");
		HashSet<String> adjectiveSets = new HashSet<String>();
		adjectiveSets.add("JJ");
		adjectiveSets.add("JJR");
		adjectiveSets.add("JJS");
		adjectiveSets.add("RB");
		adjectiveSets.add("RBR");
		adjectiveSets.add("RBS");
		adjectiveSets.add("JJ");
		adjectiveSets.add("VB");
		adjectiveSets.add("VBD");
		adjectiveSets.add("VBG");
		adjectiveSets.add("VBN");
		adjectiveSets.add("NN");
		adjectiveSets.add("WRB");

		HashSet synonyms = new HashSet();
		boolean flag = false;
		input = nlpProcessor.receiveInput();
		sentences = nlpProcessor.obtainSentences(input);
		Iterator it;
		for(String sentence : sentences){
			tokens = nlpProcessor.obtainTokens(sentence);
			//System.out.println("Sentence : "+sentence);
			flag = false;
			for(String token : tokens){
				//System.out.print("Token : "+token);
				if(flag==false && negators.contains(token)){
					flag = true;
					continue;
				}
				stem = nlpProcessor.obtainStem(token.toLowerCase());
				//System.out.println("\tStem : "+stem);
				pos = nlpProcessor.obtainPOS(stem);
				System.out.println("POS : "+pos);
				if(adjectiveSets.contains(pos)==false)
					continue;
				tokenVector = nlpProcessor.obtainTokenVector(stem);
				if(tokenVector!=null){
					if(flag){
						tokenVector.subtract();
						flag=false;
					}
					outputVector.add(tokenVector);
				}else{
					System.out.println("Word : "+token+"\tstem : "+stem+" not found");
					synonyms = this.getSynonyms(token);
					//it = synonyms.;
					i=0;
					System.out.println("SYNSET :");
					for(Object s:synonyms.toArray()){
						System.out.print("Synonym : "+s);
						stem = nlpProcessor.obtainStem(s.toString().toLowerCase());
						System.out.println("\tStem :"+stem);
						tokenVector = nlpProcessor.obtainTokenVector(stem);
						if(tokenVector!=null){
							break;
						}else{
							i++;
						}
					}
					if(i==synonyms.size())
						System.out.println("Word : "+token+" not found in dictionary along with synsets");
					else{
						if(flag){
							tokenVector.subtract();
							flag=false;
						}
						outputVector.add(tokenVector);
					}
				}
//				if(flag){
//					//System.out.println("flag is true");
//					if(tokenVector!=null){
//						tokenVector.subtract();
//						//System.out.println(tokenVector);
//						flag = false;
//					}else{
//						System.out.println("Word : "+token+"\tstem : "+stem+" not found");
//						synonyms = this.getSynonyms(token);
//						//it = synonyms.;
//						i=0;
//						System.out.println("SYNSET :");
//						for(Object s:synonyms.toArray()){
//							stem = nlpProcessor.obtainStem(s.toString().toLowerCase());
//							tokenVector = nlpProcessor.obtainTokenVector(stem);
//							if(tokenVector!=null){
//								tokenVector.subtract();
//								flag = false;
//								break;
//							}else{
//								i++;
//							}
//						}
//						if(i==synonyms.size())
//							System.out.println("Word : "+token+" not found in dictionary along with synsets");
//					}
//				}
//				if(tokenVector!=null){
//					//System.out.println("tokenVector found in corpus : "+tokenVector);
//					outputVector.add(tokenVector);
//				}else{
//					System.out.println("Word : "+token+"\tstem : "+stem+" not found");
//				}
			}
		}
		//System.out.println("Output Vector : " + outputVector);
	}
	public void buildCorpus() {
		MoodAnalyser analyser = new MoodAnalyser();
		//analyser.readFile();
		//analyser.outputFromFile();
		analyser.addMoreWords();
		analyser.outputFromFile();
	}

}
// subtle , discombobulate ,frugal , freak, naiive , deterioarate
