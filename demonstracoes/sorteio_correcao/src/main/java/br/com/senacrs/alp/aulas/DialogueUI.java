package br.com.senacrs.alp.aulas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public interface DialogueUI {
	
	JFrame getJFrame();
	
	JButton getNext();
	
	JButton getClear();
	
	JTextField getMax();
	
	JTextArea getPicked();		
}
