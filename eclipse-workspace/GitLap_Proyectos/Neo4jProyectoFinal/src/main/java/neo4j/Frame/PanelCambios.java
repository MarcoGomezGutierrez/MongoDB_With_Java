package neo4j.Frame;

import javax.swing.JPanel;

public class PanelCambios extends JPanel {
	
	private static final long serialVersionUID = 6774333751192549795L;
	private final static IntelligenceLearn panelLearn = new IntelligenceLearn();
	private final static IntelligenceQuestion panelQuestion = new IntelligenceQuestion();

	public PanelCambios() {
		setBounds(100, 100, 1920, 1080);
		panelQuestion.setVisible(false);
		add(panelLearn);
		add(panelQuestion);
	}
	
	public static  void cambioPanelLearn() {
		panelLearn.setVisible(true);
		panelQuestion.setVisible(false);
	}
	
	public static  void cambioPanelQuestion() {
		panelLearn.setVisible(false);
		panelQuestion.setVisible(true);
	}

}
