import java.util.ArrayList;

public class algo
{
	String[] mood={"Happy :) ","Sad :(","Fear :O", "Surprise"," Angry "};
//	int anumber;
//	int intensity;
//	String word;
	static int resultm[]=new int[5];
	//ArrayList<Integer> resultm=new ArrayList<Integer>();
	int flag=0,temp,adjective=0,present=0;
	
	
//	int resultint[]=new int[5];
//	public void tablecreation() {
//		// TODO Auto-generated method stub
//		
//	}

	public void func(String message) 
	{
		// TODO Auto-generated method stub
		
				
		String[] happysyn={"happi","cheer","wonder","joi","glad","jolli","content","happi","bliss","laugh","good","jubili","pleasant","exult","excit","love"};
		ArrayList<String> happysynadd=new ArrayList<String>();
		for(String s:happysyn)
		{
			happysynadd.add(s);
		}
				
		String[] sadsyn={"sad","unhappi", "sorrow", "deject", "regret", "depress", "miser", "downheart", "down", "despair", " heartbroken","pathet","fail","bore","collaps","sad"};
		ArrayList<String> sadsynadd=new ArrayList<String>();
		for(String s:sadsyn)
		{
			sadsynadd.add(s);
		}
		
		String[] fearsyn={"fear", "terror", "fright", "fear", "horror", "alarm", "panic", "agitate", "dread", "dismay", "distress"};
		ArrayList<String> fearsynadd=new ArrayList<String>();
		for(String s:happysyn)
		{
			fearsynadd.add(s);
		}
		
		String[] surprisesyn={ "surprise", "shock","astonish", "amaz", "bewilder","bombshell","reveal","incredul","stupefact","confuse","disbilief"};
		ArrayList<String> surprisesynadd=new ArrayList<String>();
		for(String s:happysyn)
		{
			surprisesynadd.add(s);
		}
		
		String[] angrysyn={"angry" ,"irrit", "annoi", "vex", "displease", "provok", "resent",};
		ArrayList<String> angrysynadd=new ArrayList<String>();
		for(String s:angrysyn)
		{
			angrysynadd.add(s);
		}
		
		
		
		
		int[] happyinten={8,9,7,9,10,6,10,6,9,5,4,4,5,9,7};
		ArrayList<Integer> happyintenadd=new ArrayList<Integer>();
		for(int s:happyinten)
		{
			happyintenadd.add(s);
		}		
		
		int[] sadinten={7,8,9,10,10,7,10,6,4,9,8,7,6,8,10};
		ArrayList<Integer> sadintenadd=new ArrayList<Integer>();
		for(int s:sadinten)
		{
			sadintenadd.add(s);
		}		
		
		int[] fearinten={9,8,10,10,7,6,5,8,5,4};
		ArrayList<Integer> fearintenadd=new ArrayList<Integer>();
		for(int s:fearinten)
		{
			fearintenadd.add(s);
		}		
		
		int[] surpriseinten={10,9,8,9,7,5,4,2,6,7};
		ArrayList<Integer> surpriseintenadd=new ArrayList<Integer>();
		for(int s:surpriseinten)
		{
			surpriseintenadd.add(s);
		}
		
		int[] angryinten={9,8,5,8,9,6};
		ArrayList<Integer> angryintenadd=new ArrayList<Integer>();
		for(int s:angryinten)
		{
			angryintenadd.add(s);
		}
		
		
		int k,q;
		
		
//		System.out.println("Displayingggggg");
//		for(k=0;k<message.length();k++)
//		{

		
		/*
		if(happysynadd.contains(message))
			resultm[0]=resultm[0]+happyintenadd.get(happysynadd.indexOf(message));

		if(sadsynadd.contains(message))
			resultm[1]=resultm[1]+sadintenadd.get(sadsynadd.indexOf(message));

		if(fearsynadd.contains(message))
			resultm[2]=resultm[2]+fearintenadd.get(fearsynadd.indexOf(message));

		if(surprisesynadd.contains(message))
			resultm[3]=resultm[3]+surpriseintenadd.get(surprisesynadd.indexOf(message));

		if(angrysynadd.contains(message))		
			resultm[4]=resultm[4]+angryintenadd.get(angrysynadd.indexOf(message));
			
		
		*/
		
		
		if(message.equals("not"))
		{
			 flag=1;
		}
		if((message.equals("veri"))||message.equals("extreme"))
		{
			adjective=1;
		}
		
			for(q=1;q<happysyn.length;q++)
			{
				if(message.equals(happysyn[q]))
				{			
					resultm[0]=resultm[0]+happyinten[q-1];
					present=1;
				//	System.out.println("Result[0]=" +resultm[0] +"\t" + happyinten[q-1]);
				}
			}			
			
			for(q=1;q<sadsyn.length;q++)
			{
				if(message.equals(sadsyn[q]))
				{			
					resultm[1]=resultm[1]+sadinten[q-1];
					present=1;
				}
			}			
		
			for(q=1;q<fearsyn.length;q++)
			{
				if(message.equals(fearsyn[q]))
				{			
					resultm[2]+=fearinten[q-1];
					present=1;
				}
			}			
		
			for(q=1;q<surprisesyn.length;q++)
			{
				if(message.equals(surprisesyn[q]))
				{			
					resultm[3]+=surpriseinten[q-1];
					present=1;
				}
			}			
		
			for(q=1;q<angrysyn.length;q++)
			{
				if(message.equals(angrysyn[q]))
				{			
					resultm[4]+=angryinten[q-1];
					present=1;
				}
			}			
			
	
////		result();
	}
	
	
	 
	
	
	public int result() 
	{	
		if(present==0)
		{
			System.out.println("No emotion detected \n\n\n\n\n\n");
			return 0;
		}
		
//		for(int h = 0;h<5;h++)
//		{
//			System.out.println(resultm[h]);
//		}
	
		if(flag==1)
		{
			temp=resultm[0];
			resultm[0]=resultm[1];
			resultm[1]=temp;
		}
		
		
		
		int big=resultm[0],f,in=0;
		for(f=1;f<5;f++)
		{
			if(resultm[f]>big)
			{
				big=resultm[f];
				in=f;
			}
		}
		//System.out.println("Adddjecctiveee "+ adjective);
		if(adjective==1)
		{
			big+=10;
		}
		
		
		
		System.out.println("Mood of the person is   " +mood[in]+" and intensity is   "+big +"\n\n\n\n\n\n\n\n");
		
		return 0;
	}





	
	
	
	
	//Arrays.sort(resultm);
		
		
		
		
		
		
		
		
		
	

	//public algo();
	//algo[] a=new algo[100];
	
	
		
	
}
	