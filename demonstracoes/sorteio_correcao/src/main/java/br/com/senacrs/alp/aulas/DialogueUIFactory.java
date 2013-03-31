package br.com.senacrs.alp.aulas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class DialogueUIFactory {
	
	private static final DialogueUIFactory instance = new DialogueUIFactory();
	
	private static class DialogueUIImpl implements DialogueUI {

		private static final String LOOK_AND_FEEL_COM_JGOODIES_PLAF_PLASTIC_PLASTIC_XP_LOOK_AND_FEEL = "com.jgoodies.plaf.plastic.PlasticXPLookAndFeel";
		private static final String ROW_SPEC_PREF_12PX_PREF_12PX_PREF_12PX_PREF = "pref, 12px, pref, 12px, pref, 12px, pref";
		private static final String COLUMN_SPEC_LEFT_PREF_15PX_100PX = "left:pref, 15px, 100px";
		
		private final DialogueLogic logic;
		private final Properties properties;
		private JButton next = null;
		private JButton clear = null;
		private JFrame frame = null;
		private JTextField max = null;
		private JTextArea picked = null;
		
		public DialogueUIImpl(DialogueLogic logic, Properties properties) {

			super();
			
			if (logic == null) {
				throw new IllegalArgumentException();
			}
			if (properties == null) {
				throw new IllegalArgumentException();
			}
			this.logic = logic;
			this.properties = properties;
	        try {
	            UIManager.setLookAndFeel(LOOK_AND_FEEL_COM_JGOODIES_PLAF_PLASTIC_PLASTIC_XP_LOOK_AND_FEEL);
	        } catch (Exception e) {
	            // Likely PlasticXP is not in the class path; ignore.
	        }
		}

		@Override
		public JFrame getJFrame() {
			
			JComponent panel = null;
			
			if (frame == null) {
				panel = getPanel();
		        frame = new JFrame();
		        frame.setTitle(getProperty(Labels.TITLE));
		        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		        frame.getContentPane().add(panel);
		        frame.pack();
		        frame.setVisible(true);				
			}

			return frame;
		}

		private String getProperty(Labels label) {
			
			return properties.getProperty(label.getTag());
		}

		private JComponent getPanel() {
			
			JComponent result = null;
			PanelBuilder builder = null;

			builder = getPanelBuilder();
			addFormElements(builder);
			result = builder.getPanel();
			
			return result;
		}

		private PanelBuilder getPanelBuilder() {

			PanelBuilder result = null;
			FormLayout layout = null;			

			layout = getFormLayout();
			result = new PanelBuilder(layout);
			result.setBorder(Borders.DIALOG_BORDER);
			
			return result;
		}

		private FormLayout getFormLayout() {
			
			return new FormLayout(
					COLUMN_SPEC_LEFT_PREF_15PX_100PX,
					ROW_SPEC_PREF_12PX_PREF_12PX_PREF_12PX_PREF);			
		}

		private void addFormElements(PanelBuilder builder) {
			
			CellConstraints cc = null;
			String lblStr = null;

			cc = new CellConstraints();
			lblStr = getProperty(Labels.MAX);
			builder.addLabel(lblStr, cc.xy(1, 1));
			builder.add(getMax(), cc.xy(3, 1));
			lblStr = getProperty(Labels.PICKED);
			builder.addLabel(lblStr, cc.xy(1, 3));	
			builder.add(getPicked(), cc.xyw(1, 5, 3));
			builder.add(getClear(), cc.xy(1, 7));
			builder.add(getNext(), cc.xy(3, 7));
		}

		@Override
		public JButton getNext() {
			
			String lblStr = null;

			if (next == null) {
				lblStr = getProperty(Labels.NEXT);
				next = new JButton(lblStr);
				next.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						clickNext();
						updateText();
					}
				});
			}

			return next;
		}
		
		private void clickNext() {
			
			logic.setMax(Integer.valueOf(getMax().getText()));
			logic.nextClickStartAtOne();
		}
		
		private void updateText() {
			
			getPicked().setText(logic.getText());			
		}

		@Override
		public JButton getClear() {
			
			String lblStr = null;

			if (clear == null) {
				lblStr = getProperty(Labels.CLEAR);
				clear = new JButton(lblStr);
				clear.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						logic.clearText();
						updateText();
					}
				});
			}

			return clear;
		}

		@Override
		public JTextField getMax() {
			
			if (max == null) {
				max = new JTextField();
			}

			return max;
		}

		@Override
		public JTextArea getPicked() {
			
			if (picked == null) {
				picked = new JTextArea();
			}

			return picked;
		}
				 
	}
	
	private DialogueUIFactory() {
		
		super();
	}
	
	public DialogueUI createDialogueUI(DialogueLogic logic, Properties properties) {
		
		DialogueUI result = null;
		
		result = new DialogueUIImpl(logic, properties);
		
		return result;
	}
	
	public static DialogueUIFactory getInstance() {
		
		return instance;
	}
}
