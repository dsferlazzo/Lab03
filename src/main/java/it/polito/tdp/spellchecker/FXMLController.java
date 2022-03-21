package it.polito.tdp.spellchecker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbLingua;

    @FXML
    private Label lblErrori;

    @FXML
    private Label lblTempo;

    @FXML
    private TextArea txtErrori;

    @FXML
    private TextArea txtParole;

    @FXML
    void doClearText(ActionEvent event) {
    	lblErrori.setText("");
    	lblTempo.setText("Spell check completed in 0 seconds");
    	txtErrori.setText("");
    	txtParole.setText("");

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	Long t1 = System.nanoTime();
    	//CARICA IL DIZIONARIO
    	model.loadDictionary(cmbLingua.getValue());
    	
    	
    	//DIVIDI L'INPUT IN UNA LISTA DI PAROLE, PER " " E "\n"
    	
    	String input = txtParole.getText().toLowerCase();
    	input=input.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	//System.out.println(input); //DEBUIGGING
    	
    	//CREO LA LISTA DA USARE COME INPUT PER IL METODO spellCheckText()
    	
    	input.replaceAll("\n", " ");
    	List<String> listaParole = new ArrayList<String>();
    	String array[] = input.split(" ");
    	for(String s : array)
    	{
    		listaParole.add(s);
    	}
    	
    	List<RichWord> lrw = model.spellCheckText(listaParole);
    	
    	int flErrori=0;
    	
    	for(RichWord rw:lrw)
    	{
    		if(rw.getCorretta()==false)
    		{
    			txtErrori.appendText(rw.getParola()+"\n");
    			flErrori++;
    		}
    	}
    	
    	if(flErrori!=0)
    		lblErrori.setText("The text contains " + flErrori + " errors");
    	
    	lblTempo.setText("Spell check completed in " + (System.nanoTime()-t1) + " seconds");
    	
    	

    }

    @FXML
    void initialize() {
        assert cmbLingua != null : "fx:id=\"cmbLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParole != null : "fx:id=\"txtParole\" was not injected: check your FXML file 'Scene.fxml'.";
        
        cmbLingua.getItems().add("English");
        cmbLingua.getItems().add("Italian");

    }
    
    public void setModel(Dictionary model)
    {
    	this.model=model;
    }

}
