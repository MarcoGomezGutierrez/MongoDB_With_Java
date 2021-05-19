package neo4j.Frame;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import TextPrompt.TextPrompt;
import neo4j.Function.Conexion;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Roles extends JFrame {

	
	private static final long serialVersionUID = 2455586042840714372L;
	
	private JPanel contentPane;
	private JTextField text_usuario;
	private JTextField text_contraseña;
	@SuppressWarnings("unused")
	private TextPrompt placeholder;

	public Roles() {
		
		try {UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {}
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.lightGray);
		
		text_usuario = new JTextField();
		text_usuario.setBounds(150, 70, 200, 50);
		placeholder = new TextPrompt("Usuario...", text_usuario);
		contentPane.add(text_usuario);
		
		text_contraseña = new JTextField();
		text_contraseña.setBounds(150, 150, 200, 50);
		placeholder = new TextPrompt("Contraseña...", text_contraseña);
		contentPane.add(text_contraseña);
		
		JButton btn_rol = new JButton("Usar Rol");
		btn_rol.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unused", "resource" })
			public void actionPerformed(ActionEvent e) {
				if (text_usuario.getText().length() != 0 && text_contraseña.getText().length() != 0) {
					Conexion conexion = new Conexion("bolt://localhost:7687", text_usuario.getText(), text_contraseña.getText());
					Aplication.setFrameVisible();
				}
			}
		});
		btn_rol.setBounds(200, 230, 100, 50);
		contentPane.add(btn_rol);
	}
}
