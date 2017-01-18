import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;


public class MoodAnalyser {

	public static void main(String args[]){
		MoodAnalyser mood = new MoodAnalyser();
		mood.readFile();
		mood.outputFromFile();
	}
	public void readFile(){
		String word,vec,firstLine[];
		firstLine = new String[4];
		int hap,sad,ang,sur,count = 0;
		FileInputStream fin = null;
		ObjectInputStream in = null;
		HashMap tokenVector = new HashMap();
		Scanner scan = new Scanner(System.in);
		File file = new File("newCorpus.txt");
		
		if(file.exists()){
			try{
				fin = new FileInputStream("newCorpus.txt");
				in = new ObjectInputStream(fin);
				tokenVector = (HashMap) in.readObject();
				//fin.getChannel().truncate(0);
			}catch(Exception e){
				System.out.println("Here");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}finally{
				try {
					in.close();
					fin.close();
					System.out.println("File closed");
				}catch(java.nio.channels.NonWritableChannelException j){
					
				}
				catch (Exception e) {
					System.out.println("in.close() : "+e.getMessage());
					e.printStackTrace();
				}
			}
		}else{
			try {
				if(file.createNewFile()){
					FileOutputStream fout;
					ObjectOutputStream os = null;
					try {
						fout = new FileOutputStream("newCorpus.txt");
						os = new ObjectOutputStream(fout);
					} catch (Exception e1) {
						System.out.println("FileOutputStream : "+e1.getMessage());
						e1.printStackTrace();
					}
					System.out.println("Blank Corpus Created");
					tokenVector.put("happi",new Vector(10,0, 0, 0));
					os.writeObject(tokenVector);
				}
				else
					System.out.println("Corpus didnt exist nor is it created");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileOutputStream fout = null;
		ObjectOutputStream os = null;
		try {
			fout = new FileOutputStream("newCorpus.txt");
			os = new ObjectOutputStream(fout);
			fout.getChannel().truncate(0);
			
		} catch (Exception e1) {
			System.out.println("FileOutputStream : "+e1.getMessage());
			e1.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader("words.txt"));
			while((word = reader.readLine())!= null){
				System.out.println("For word : "+word);
				System.out.println("Enter Vector values <happy,sad,angry,surprise> :");				
				vec = scan.nextLine(); //giving input as vector : "8 5 6 7"
				firstLine = vec.split(" ");
				hap = Integer.parseInt(firstLine[0]); 
				sad = Integer.parseInt(firstLine[1]);
				ang = Integer.parseInt(firstLine[2]);
				sur = Integer.parseInt(firstLine[3]); 
				
				tokenVector.put(word, new Vector(hap,sad,ang,sur));
				System.out.println("Total Words added :"+tokenVector.size());
				if(tokenVector.size()%5==0){
					System.out.println("More words ? Enter 0 for no");
					if(scan.nextInt()==0)
						break;
					scan.nextLine();
				}	
			}
		} catch (Exception e) {
			System.out.println("Buffered Reader :"+e.getMessage());
			e.printStackTrace();
		}
		try {
			os.writeObject(tokenVector);
			os.close();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void outputFromFile(){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("newCorpus.txt"));
			HashMap tokenVector = new HashMap();
			tokenVector = (HashMap) in.readObject();
			for(Object key : tokenVector.keySet()){
				System.out.println(key +" : "+tokenVector.get(key) );
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void addMoreWords() {
		
		String word,vec,firstLine[];
		firstLine = new String[4];
		int hap,sad,ang,sur,count = 0;
		FileInputStream fin = null;
		ObjectInputStream in = null;
		HashMap tokenVector = new HashMap();
		Scanner scan = new Scanner(System.in);
		try{
			fin = new FileInputStream("corpus.txt");
			in = new ObjectInputStream(fin);
			tokenVector = (HashMap) in.readObject();
//			fin.getChannel().truncate(0);
		}catch(Exception e){
			System.out.println("Here");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				in.close();
				fin.close();
				System.out.println("File closed");
			} catch (Exception e) {
				System.out.println("in.close() : "+e.getMessage());
				e.printStackTrace();
			}
		}
		while(true){
			word = scan.nextLine();
			System.out.println("For word : "+word);
			System.out.println("Enter Vector values <happy,sad,angry,surprise> :");				
			vec = scan.nextLine(); //giving input as vector : "8 5 6 7"
			firstLine = vec.split(" ");
			hap = Integer.parseInt(firstLine[0]); 
			sad = Integer.parseInt(firstLine[1]);
			ang = Integer.parseInt(firstLine[2]);
			sur = Integer.parseInt(firstLine[3]); 
			
			tokenVector.put(new NLP().obtainStem(word), new Vector(hap,sad,ang,sur));
			System.out.println(new NLP().obtainStem(word)+" added ");
			System.out.println("Total Words added :"+tokenVector.size());
			
			System.out.println("More words ? Enter 0 for no");
			if(scan.nextInt()==0)
				break;
			scan.nextLine();
				
		}
		FileOutputStream fout = null;
		ObjectOutputStream os = null;
		try {
			fout = new FileOutputStream("readiedCorpus.txt");
			os = new ObjectOutputStream(fout);
		} catch (Exception e1) {
			System.out.println("FileOutputStream : "+e1.getMessage());
			e1.printStackTrace();
		}
		
		try {
			fout.getChannel().truncate(0);

			os.writeObject(tokenVector);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

