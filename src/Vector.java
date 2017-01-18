import java.io.Serializable;

class Vector implements Serializable{
	private static final long serialVersionUID = -3968445844868951841L;
	int happy,sad,angry,surprise;
	public Vector(){
		happy=sad=angry=surprise=0;
	}
	public Vector(int hap,int sad,int ang,int sur){
		happy = hap;
		this.sad = sad;
		angry = ang;
		surprise = sur;
	}
	public String toString(){
		return ""+happy+" "+sad+" "+angry+" "+surprise+"\n";
	}
	public void add(Vector tokenVector) {
		happy+=tokenVector.happy;
		sad+=tokenVector.sad;
		angry+=tokenVector.angry;
		surprise+=tokenVector.surprise;
		
	}
	public void subtract() {
		happy = happy + sad - ( sad = happy);
		angry = surprise = 0;

	}
}