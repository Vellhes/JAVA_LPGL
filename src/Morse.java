
public class Morse {
	
	char lettre;
	String code;
	
	
	public Morse() {
		super();
	}
	public Morse(char lettre, String code) {
		super();
		this.lettre = lettre;
		this.code = code;
	}
	public char getLettre() {
		return lettre;
	}
	public void setLettre(char lettre) {
		this.lettre = lettre;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return  lettre + " " + code + "";
	}
	
	
}
