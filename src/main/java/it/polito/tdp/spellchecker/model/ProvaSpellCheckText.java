package it.polito.tdp.spellchecker.model;

import java.util.ArrayList;
import java.util.List;

public class ProvaSpellCheckText {

	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.loadDictionary("Italian");
		
		List<String> lista = new ArrayList();
		lista.add("puzza");
		lista.add("puzzo");
		lista.add("tanto");
		lista.add("tanti");
		lista.add("tante");
		lista.add("vlte");
		
		long t1 = System.nanoTime();
		d.spellCheckTextLinearA(lista);
		long tTot=System.nanoTime()-t1;
		System.out.println("Linear ArrayList: " + tTot);
		
		t1 = System.nanoTime();
		d.spellCheckTextLinearL(lista);
		tTot=System.nanoTime()-t1;
		System.out.println("Linear LinkedList: " + tTot);
		
		t1 = System.nanoTime();
		d.spellCheckTextDichotomicA(lista);
		tTot=System.nanoTime()-t1;
		System.out.println("Dichotomic ArrayList: " + tTot);
		
		t1 = System.nanoTime();
		d.spellCheckTextDichotomicL(lista);
		tTot=System.nanoTime()-t1;
		System.out.println("Dichotomic LinkedList: " + tTot);
		
		t1 = System.nanoTime();
		d.spellCheckText(lista);
		tTot=System.nanoTime()-t1;
		System.out.println("Contains ArrayList " + tTot);
		
		

	}

}
