import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {
	
	static Map dictionary = new HashMap(); //pıhi sınastik
	static Map dictionary2 = new HashMap(); //v‰ljaj‰‰vate sınade sınastik

	//uudiste arvud
	static int p=56;
	static int d=34;
	static int e=16;
	static int[][] limit;
	
	public static void main(String[] args) throws IOException {
		
		//piirid aastate ja v‰ljaannete kaupa
		limit=new int[3][4];
		limit[0][0]=1;
		limit[1][0]=1;		
		limit[2][0]=1;
		limit[0][1]=38;
		limit[1][1]=20;		
		limit[2][1]=10;
		limit[0][2]=46;
		limit[1][2]=25;		
		limit[2][2]=11;
		limit[0][3]=56;
		limit[1][3]=34;		
		limit[2][3]=16;
		
		//lisa v‰ljaj‰‰vad sınad
		dictionary2.put("ja", 1);
		dictionary2.put("ning", 1);
		dictionary2.put("et", 1);
		dictionary2.put("ka", 1);
		dictionary2.put("mis", 1);
		dictionary2.put("on", 1);
		dictionary2.put("kui", 1);
		dictionary2.put("aga", 1);
		dictionary2.put("ei", 1);
		dictionary2.put("kes", 1);
		dictionary2.put("the", 1);
		dictionary2.put("in", 1);
		dictionary2.put("nii", 1);
		dictionary2.put("to", 1);
		dictionary2.put("see", 1);
		dictionary2.put("off", 1);
		dictionary2.put("vıi", 1);
		dictionary2.put("ta", 1);
		dictionary2.put("nad", 1);
		dictionary2.put("is", 1);
		dictionary2.put("oli", 1);
		dictionary2.put("of", 1);
		dictionary2.put("and", 1);
		dictionary2.put("for", 1);
		dictionary2.put("are", 1);
		
		//m‰‰ra periood
		int start=1;
		int end=2;
		for(int i=limit[0][start];i<limit[0][end];i++){
			readData("Postimees "+i+".txt"," ");
		}
		for(int i=limit[1][start];i<limit[1][end];i++){
			readData("Delfi "+i+".txt"," ");
		}
		for(int i=limit[2][start];i<limit[2][end];i++){
			readData("ERR "+i+".txt"," ");
		}
		
		
		Writer writer = null;
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("post_all.txt"), "Windows-1257"));
		
		for(Object key : dictionary.keySet()){
			writer.write((String)key + ":" + dictionary.get(key)+"\n");
		}
		System.out.println(dictionary.size());
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer=null;
		
		
      
	}
	
	
	static void readData(String filename, String split) {
		BufferedReader br = null;
		try {

			String sCurrentLine;
			br = new BufferedReader(new FileReader(filename)); //open file
			int c = 0;
			while ((sCurrentLine = br.readLine()) != null) { //while there are lines
				String[] words = sCurrentLine.split(split); //split current line, separate name and features
				if(sCurrentLine.equals(""))
					continue;
				
				for(String word: words) {
					//eemalda ebavajalikud s¸ndmused
					 word = word.replace(".", "").replace("!","").replace("?","");
					 word = word.replace(",", "").replace(")","").replace("Ñ","").replace("ì","").replace("-","");
					 word=word.replace("ª","").replace("\"","").replace("î","").replace("´","").replace("(","");
					 word=word.toLowerCase();
					 
					 
					 if(dictionary2.containsKey(word)){ //kui sınade hulgas mida ei uuri, j‰ta vahele
						 
					 }else{
						 
					 
						if(dictionary.containsKey(word)) { //kui olemas
							Integer val = (Integer) dictionary.get(word);
							dictionary.put(word, val + 1); //lisa +1
						}
						else
						  dictionary.put(word, 1); //kui ei, pane sınaraamatusse
						}
					}

				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close(); //close file
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

}
