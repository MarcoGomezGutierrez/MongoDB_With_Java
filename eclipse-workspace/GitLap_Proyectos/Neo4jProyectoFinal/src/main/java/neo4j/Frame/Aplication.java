package neo4j.Frame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import neo4j.Business.Intelligence.BusinessIntelligence;
import neo4j.Function.Conexion;
import neo4j.Graph.GraphNeo4j;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Aplication {

	
	private static JFrame frame;
	private static Roles role = new Roles();
	private PanelCambios panelCambios = new PanelCambios();
	private JButton btn_aprender = new JButton("Aprender");
	private JButton btn_preguntar = new JButton("Preguntar");
	private final JButton btn_graph = new JButton("Ver Grafo");
	private final JButton btn_excel = new JButton("Mostrar Excel");
	private final JButton btn_grafica = new JButton("Ver Gr\u00E1fica");
	private final JButton btn_delete = new JButton("Eliminar Nodo");
	private final JButton btn_ayuda = new JButton("Ayuda");
	
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Aplication window = new Aplication();
					window.role.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Aplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_sur = new JPanel();
		frame.getContentPane().add(panel_sur, BorderLayout.SOUTH);
		btn_aprender.setEnabled(false);
		
		/**
		 * Botón que me cambia al panel IntelligenceLearn
		 */
		btn_aprender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelCambios.cambioPanelLearn();
				btn_aprender.setEnabled(false);
				btn_preguntar.setEnabled(true);
			}
		});
		panel_sur.add(btn_aprender);
		
		/**
		 * Botón que me cambia al panel IntelligenceQuestion
		 */
		btn_preguntar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelCambios.cambioPanelQuestion();
				btn_aprender.setEnabled(true);
				btn_preguntar.setEnabled(false);
			}
		});
		panel_sur.add(btn_preguntar);
		
		/**
		 * Botón que me reconstruye la Base de Datos en un Hilo pero dibujada en Java
		 */
		btn_graph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphNeo4j thread = new GraphNeo4j();
				thread.start();
			}
		});
		panel_sur.add(btn_graph);
		
		/**
		 * Botón que me Crea/Actualiza el Excel y luego me lo muestra
		 */
		btn_excel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BusinessIntelligence.crearExcel();
				BusinessIntelligence.mostrar();
			}
		});
		panel_sur.add(btn_excel);
		
		/**
		 * Botón que me abre un JFrame y me gráfica la Tabla Nodos Nuevos con Nº de Relaciones
		 */
		btn_grafica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							PanelBarChart frame = new PanelBarChart();
							frame.setVisible(true);
							frame.setDefaultCloseOperation(1);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel_sur.add(btn_grafica);
		
		/**
		 * Botón que me borra los nuevos nodos solo puede el Admin
		 */
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						if (Conexion.getUsuario().equals("admin")) {
							DeleteNode delete = new DeleteNode();
							delete.setVisible(true);
						} else {
							Thread hilo = new Thread(() -> {
								Color color = btn_delete.getBackground();
								btn_delete.setBackground(Color.red);
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e1) {}
								btn_delete.setBackground(color);
							});
							hilo.start();
						}
					}
				});
			}
		});
		panel_sur.add(btn_delete);
		
		btn_ayuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Ayuda ayuda = new Ayuda();
							ayuda.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel_sur.add(btn_ayuda);
		
		JPanel panel_central = new JPanel();
		frame.getContentPane().add(panel_central, BorderLayout.CENTER);
		
		panel_central.add(panelCambios);
		
	}
	
	public static void setFrameVisible() {
		Aplication.frame.setVisible(true);
		Aplication.role.setVisible(false);
	}

}
