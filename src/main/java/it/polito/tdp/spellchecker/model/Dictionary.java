package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
	
	public List<RichWord> spellCheckTextLinearA(List<String> inputTextList)
	{
		List<RichWord> output = new ArrayList<RichWord>();
		for(String s:inputTextList)
		{
			int fl=0;
			for(int i = 0;i<paroleRegistrate.size()&&fl==0;i++)
			{
				if(paroleRegistrate.get(i).equals(s))
				{
					fl=1;
					output.add(new RichWord(s, true));	
				}
			}
			if(fl==0)
				output.add(new RichWord(s, false));
		}
		return output;
	}
	
	public List<RichWord> spellCheckTextLinearL(List<String> inputTextList)
	{
		List<RichWord> output = new LinkedList<RichWord>();
		for(String s:inputTextList)
		{
			int fl=0;
			for(int i = 0;i<paroleRegistrate.size()&&fl==0;i++)
			{
				if(paroleRegistrate.get(i).equals(s))
				{
					fl=1;
					output.add(new RichWord(s, true));	
				}
			}
			if(fl==0)
				output.add(new RichWord(s, false));
		}
		return output;
	}
	
	public List<RichWord> spellCheckTextDichotomicA(List<String> inputTextList)
	{		//DA RICONTROLLARE FUNZIONAMENTO ED EFFICIENZA
		List<RichWord> output = new ArrayList<RichWord>();
		for(String s:inputTextList)
		{
			
			//int flNonTrovata=0;
			int nMax=paroleRegistrate.size();
			int nMin=0;
			String parolaPrecedente="";
			
			while(true)
			{
				int num=((nMax-nMin)/2)+nMin;
				String pMedia = paroleRegistrate.get(num);
				//System.out.println(pMedia);
				if(s.compareTo(pMedia)==0)
				{
					output.add(new RichWord(s,true));
					break;
				}
				if(s.compareTo(pMedia)>0)
					nMin=num;
				if(s.compareTo(pMedia)<0)
					nMax=num;
				/*if(nMax==nMin)
				{
					if(s.equals(paroleRegistrate.get(nMax)))
					{
						output.add(new RichWord(s,true));
						break;
					}
					else
					{
						output.add(new RichWord(s,false));
						break;
					}
				}*/
				if(parolaPrecedente.equals(pMedia))
				{
					output.add(new RichWord(s,false));
					break;
				}
				
				parolaPrecedente=pMedia;
				
			}
		}
		
		return output;
	}
	
	public List<RichWord> spellCheckTextDichotomicL(List<String> inputTextList)
	{
		List<RichWord> output = new LinkedList<RichWord>();
		for(String s:inputTextList)
		{
			
			//int flNonTrovata=0;
			int nMax=paroleRegistrate.size();
			int nMin=0;
			String parolaPrecedente="";
			
			while(true)
			{
				int num=((nMax-nMin)/2)+nMin;
				String pMedia = paroleRegistrate.get(num);
				//System.out.println(pMedia);
				if(s.compareTo(pMedia)==0)
				{
					output.add(new RichWord(s,true));
					break;
				}
				if(s.compareTo(pMedia)>0)
					nMin=num;
				if(s.compareTo(pMedia)<0)
					nMax=num;
				/*if(nMax==nMin)
				{
					if(s.equals(paroleRegistrate.get(nMax)))
					{
						output.add(new RichWord(s,true));
						break;
					}
					else
					{
						output.add(new RichWord(s,false));
						break;
					}
				}*/
				if(parolaPrecedente.equals(pMedia))
				{
					output.add(new RichWord(s,false));
					break;
				}
				
				parolaPrecedente=pMedia;
				
			}
		}
		
		return output;

}
}
