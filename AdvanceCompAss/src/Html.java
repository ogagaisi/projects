
import java.util.ArrayList;
import java.util.List;

public class Html {
	private List<String> codeList;
	
	public Html(){
		codeList = new ArrayList<>();
	}
	
	public void add(String input){ // adds one line
		codeList.add(input);
	}
	
	public void addLines(String input[]){// adds multiple lines 
		for(String n: input){
			codeList.add(n);
		}
	}
	public List<String> getCodeList(){
			return codeList;
	}

}
