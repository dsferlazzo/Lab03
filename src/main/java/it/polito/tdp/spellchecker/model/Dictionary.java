package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	
	List<String> paroleRegistrate;
	
	
	
	public Dictionary() {
		paroleRegistrate=new ArrayList<String>();
	}

	public void loadDictionary(String language)
	{
		try {
			FileReader fr = new FileReader(language + ".txt");
				BufferedReader br = new BufferedReader(fr);
			 // /spellchecker/src/main/resources/English.txt
			
			String word;
			while((word = br.readLine()) != null)
			{
				paroleRegistrate.add(word);
			} br.close();
			
		} catch(IOException ioe)
		{
			//IMPLEMENTARE EVENTUALE PRINT SU UI
			System.out.println("Errore nella lettura del file");
			System.out.println(ioe);
		}
	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList)
	{
		List<RichWord> output = new ArrayList<RichWord>();
		for(String s:inputTextList)
		{
			if(paroleRegistrate.contains(s))
				output.add(new RichWord(s, true));
				else output.add(new RichWord(s,false));
		}
		return output;
	}

}
