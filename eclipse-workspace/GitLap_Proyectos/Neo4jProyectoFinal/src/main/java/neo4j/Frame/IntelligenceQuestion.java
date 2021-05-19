package neo4j.Frame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import neo4j.Function.Intelligence;
import python.voz.TextToVoice;

import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * Este JPanel me va hacer una busqueda de si existe lo que le paso
 * 		-Sí existe. Lanzara un Hilo donde salga una voz y leera lo que le pase
 * 			la algoritmia y la base de datos.
 * 				-Tambien se le puede interrumpir.
 * 				-Puede ser el caso de que solo quieras leerlo, no escucharlo de la 
 * 				máquina.
 * 		-No existe. Lanzara un Hilo que diga lo correspondiente. "No existe".
 * @author marco
 *
 */
public class IntelligenceQuestion extends JPanel {


	private static final long serialVersionUID = -5357610572592925684L;
	private JTextField text_question;
	private TextArea textArea = new TextArea();
	private Thread hilo;
	
	public IntelligenceQuestion() {
		setBounds(100, 100, 1920, 1080);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_norte = new JPanel();
		add(panel_norte, BorderLayout.NORTH);
		
		text_question = new JTextField();
		panel_norte.add(text_question);
		text_question.setColumns(10);
		
		JCheckBox check_voz = new JCheckBox("Voz");
		panel_norte.add(check_voz);
		
		JButton btn_preguntar = new JButton("Preguntar");
		btn_preguntar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text_question.getText().length() != 0) {
					if (check_voz.isSelected()) lanzarHiloVoz(text_question.getText());
					else lanzarHiloSinVoz(text_question.getText());
				}
			}

			private void lanzarHiloVoz(String text) {
				hilo = new Hilo(text, textArea, true);
				hilo.start();
			}
			
			private void lanzarHiloSinVoz(String text) {
				hilo = new Hilo(text, textArea, false);
				hilo.start();
			}

		});
		panel_norte.add(btn_preguntar);
		add(textArea, BorderLayout.CENTER);
		/*JPanel panel_central = new JPanel();
		add(panel_central, BorderLayout.CENTER);
		
		textArea.setSize(500, 500);
		panel_central.add(textArea);*/
		
	}
	
}

class Hilo extends Thread {
	
	private String text;
	private boolean check;
	private TextArea textArea = new TextArea();
	private TextToVoice voz = new TextToVoice();
	
	public Hilo(String text, TextArea textArea, boolean check) {
		this.text = text;
		this.textArea = textArea;
		this.check = check;
	}
	
	@Override
	public void run() {
		String dialogo = Intelligence.questionIfKnowElem(text);
		if (check) voz.hablar(dialogo);
		textArea.setText(dialogo);
	}
	
}

