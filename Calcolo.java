import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


public class Calcolo {

	private JFrame frame;
	private JLabel[][] lblNumber;
	private JLabel[] lblOperator;
	private ImageIcon[] imageNumber;
	private ImageIcon[] imageOperator;
	
	private Object labelClicked;
	
	private JFrame frameSymbols;
	private JLabel[] lblSymbolNumber;
	
	private JFrame frameOperator;
	private JLabel[] lblSymbolOperator;
	
	private Schema schema;
	private Thread thread;
	private JButton btnEsempio;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calcolo window = new Calcolo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calcolo() {
		initializeImage();
		initializeChooseNumberFrame();
		initializeChooseOperatorFrame();
		initialize();
	}
	
	private void initializeChooseOperatorFrame() {
		lblSymbolOperator = new JLabel[4];
		
		frameOperator = new JFrame();
		frameOperator.getContentPane().setBackground(new Color(200,200,200));
		frameOperator.setBounds(200,100,200,200);
		frameOperator.setUndecorated(true);
		frameOperator.getContentPane().setLayout(null);
		frameOperator.setVisible(false);
		frameOperator.isAlwaysOnTop();
		
		for (int i=0; i<4; i++) {
			lblSymbolOperator[i] = new JLabel("");
			lblSymbolOperator[i].setBounds(10+i*34,10,32,32);
			lblSymbolOperator[i].setIcon(imageOperator[i+1]);
			lblSymbolOperator[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					symbolOperatorListener(e);
				}
			});
			frameOperator.getContentPane().add(lblSymbolOperator[i]);
		}
	}
	
	private void initializeChooseNumberFrame() {
		
		lblSymbolNumber = new JLabel[11];
		
		frameSymbols = new JFrame();
		frameSymbols.getContentPane().setBackground(new Color(200,200,200));
		frameSymbols.setBounds(200,100,500,200);
		frameSymbols.setUndecorated(true);
		frameSymbols.getContentPane().setLayout(null);
		frameSymbols.setVisible(false);
		frameSymbols.isAlwaysOnTop();
		
		for (int i=0; i<10; i++) {
			lblSymbolNumber[i] = new JLabel("");
			lblSymbolNumber[i].setBounds(10+i*34,10,32,32);
			lblSymbolNumber[i].setIcon(new ImageIcon(Calcolo.class.getResource("/image/" + i + ".png")));
			
			lblSymbolNumber[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					symbolNumberListener(e);
				}
			});
			
			frameSymbols.getContentPane().add(lblSymbolNumber[i]);
		}
		
		lblSymbolNumber[10] = new JLabel("");
		lblSymbolNumber[10].setBounds(350,10,32,32);
		lblSymbolNumber[10].setIcon(new ImageIcon(Calcolo.class.getResource("/image/void.png")));
		lblSymbolNumber[10].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				symbolNumberListener(e);
			}
		});
		frameSymbols.getContentPane().add(lblSymbolNumber[10]);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(238, 238, 238));
		frame.setBounds(100, 100, 485, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNumber = new JLabel[3][10];
		lblOperator = new JLabel[6];
		JLabel lbluguale_2 = new JLabel("");
		JLabel lbluguale_3 = new JLabel("");
		
		int xSpace = 0;
		int ySpace = 0;
		
		for (int i = 0; i < 3; i++) {
			xSpace = 0;
			for (int j = 0; j < 9; j++) {
				lblNumber[i][j] = new JLabel("");
				xSpace = (j == 3) ? xSpace = 70 : xSpace;
				xSpace = (j == 6) ? xSpace = 140 : xSpace;
				ySpace = (i == 1) ? ySpace = 50 : ySpace;
				ySpace = (i == 2) ? ySpace = 70 : ySpace;
				lblNumber[i][j].setBounds(12+j*35 + xSpace, 12+i*50 + ySpace, 32,32);
				lblNumber[i][j].setIcon(imageNumber[10]);
				lblNumber[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				lblNumber[i][j].setFont(new Font("Dialog", Font.BOLD, 30));
				
				lblNumber[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						labelClicked = e.getSource();
						int x = e.getXOnScreen();
						int y = e.getYOnScreen();
						frameSymbols.setBounds(x,y,392,50);
						frameSymbols.setVisible(true);
					}
				});
				
				frame.getContentPane().add(lblNumber[i][j]);
			}
		}
		for (int i = 0; i < 6; i++) {
			lblOperator[i] = new JLabel("");
			lblOperator[i].setIcon(imageOperator[0]);
			lblOperator[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					labelClicked = e.getSource();
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					frameOperator.setBounds(x,y,150,50);
					frameOperator.setVisible(true);
				}
			});
		}
			
		lblOperator[0].setBounds(134, 12, 32,32);
		frame.getContentPane().add(lblOperator[0]);
		lblOperator[1].setBounds(12+2*35, 61, 32,32);
		frame.getContentPane().add(lblOperator[1]);
		lblOperator[2].setBounds(12+5*35+70, 61, 32,32);
		frame.getContentPane().add(lblOperator[2]);
		lblOperator[3].setBounds(12+8*35+140, 61, 32,32);
		frame.getContentPane().add(lblOperator[3]);
		lblOperator[4].setBounds(134, 112, 32,32);
		frame.getContentPane().add(lblOperator[4]);
		lblOperator[5].setBounds(134, 112+70, 32,32);
		frame.getContentPane().add(lblOperator[5]);
		
		JLabel lbluguale_1 = new JLabel("");
		lbluguale_1.setBounds(12+5*35+122, 12, 32,32);
		lbluguale_1.setIcon(imageOperator[5]);
		frame.getContentPane().add(lbluguale_1);
		lbluguale_2.setBounds(12+5*35+122, 112, 32,32);
		lbluguale_2.setIcon(imageOperator[5]);
		frame.getContentPane().add(lbluguale_2);
		lbluguale_3.setBounds(12+5*35+122, 182, 32,32);
		lbluguale_3.setIcon(imageOperator[5]);
		frame.getContentPane().add(lbluguale_3);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(12, 164, 452, 3);
		frame.getContentPane().add(separator);
		
		JButton btnRisolvi = new JButton("Risolvi");
		btnRisolvi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (thread != null && thread.isAlive()) {
					return;
				} else {
					thread = new Thread(new Solve());
					thread.start();
				}
			}
		});
		btnRisolvi.setBounds(359, 231, 105, 27);
		frame.getContentPane().add(btnRisolvi);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnReset.setBounds(12, 231, 105, 27);
		frame.getContentPane().add(btnReset);
		
		btnEsempio = new JButton("Esempio");
		btnEsempio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				int[][] es = {{10,1,2,10,3,4,2,5,1},{6,1,6,10,10,6,3,5,2},{6,2,7,10,7,8,6,4,9}};
				int[] op = {2,1,1,3,4,1};
				for (int j=0; j<9; j++) {
					for (int i=0; i<3; i++) {
						lblNumber[i][j].setIcon(imageNumber[es[i][j]]);
						schema.setNumber(i, j, es[i][j]);
					}
				}
				
				for (int i=0; i<6; i++) {
					lblOperator[i].setIcon(imageOperator[op[i]]);
					schema.setOperator(i, op[i]);
				}
			}
		});
		btnEsempio.setBounds(185, 231, 105, 27);
		frame.getContentPane().add(btnEsempio);
		
		lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(12, 270, 452, 17);
		frame.getContentPane().add(lblStatus);
		
		schema = new Schema();
	}

	public void initializeImage() {
		imageNumber = new ImageIcon[11];
		imageOperator = new ImageIcon[6];
		
		for (int i=0; i<10; i++) {
			imageNumber[i] = new ImageIcon(Calcolo.class.getResource("/image/" + i + ".png"));
		}
		imageNumber[10] = new ImageIcon(Calcolo.class.getResource("/image/void.png"));
		
		imageOperator[0] = new ImageIcon(Calcolo.class.getResource("/image/void.png"));
		imageOperator[1] = new ImageIcon(Calcolo.class.getResource("/image/piu.png"));
		imageOperator[2] = new ImageIcon(Calcolo.class.getResource("/image/per.png"));
		imageOperator[3] = new ImageIcon(Calcolo.class.getResource("/image/meno.png"));
		imageOperator[4] = new ImageIcon(Calcolo.class.getResource("/image/diviso.png"));
		imageOperator[5] = new ImageIcon(Calcolo.class.getResource("/image/uguale.png"));
	}
	
	public void symbolNumberListener(MouseEvent e) {
		int result = 10;
		for (int j = 0; j < lblSymbolNumber.length; j++) {
			if (e.getSource() == lblSymbolNumber[j]) {
				result = j;
			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				if (labelClicked == lblNumber[i][j]) {
					lblNumber[i][j].setIcon(imageNumber[result]);
					schema.setNumber(i, j, result);
				}
			}
		}
		
		frameSymbols.setVisible(false);
	}
	
	public void symbolOperatorListener(MouseEvent e) {
		int result = 0;
		for (int j=0; j<lblSymbolOperator.length; j++) {
			if (e.getSource() == lblSymbolOperator[j]) {
				result = j;
			}
		}
		
		for (int j=0; j<lblOperator.length; j++) {
			if (labelClicked == lblOperator[j]) {
				lblOperator[j].setIcon(imageOperator[result+1]);
				schema.setOperator(j, result+1);
			}
		}
		
		frameOperator.setVisible(false);
	}
	
	private void risolvi(Runnable runner) {	
		long inizio = System.currentTimeMillis();
		if (schema.risolvi()) {
			int[][] s = new int[3][9];
			s = schema.getSolution();
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 9; j++) {
					if (lblNumber[i][j].getIcon() == imageNumber[10]) {
						lblNumber[i][j].setVisible(false);
					}
					lblNumber[i][j].setIcon(null);
					if (s[i][j] != -1 ) {
						lblNumber[i][j].setText(Integer.toString(s[i][j]));
					}
				}
			}
			long fine = System.currentTimeMillis();
			lblStatus.setText("Risolto! Tempo impegato:" + (fine - inizio)/1000.0 + " secondi");
		} else {
			long fine = System.currentTimeMillis();
			lblStatus.setText("Impossibile da risolvere. Tempo impegato:" + (fine - inizio)/1000.0 + " secondi");
		}
	}
	
	public void reset() {
		lblStatus.setText("");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				lblNumber[i][j].setText("");
				lblNumber[i][j].setVisible(true);
				lblNumber[i][j].setIcon(imageNumber[10]);
				schema.setNumber(i, j, 10);
			}
		}
		for (int i = 0; i < 6; i++) {
			lblOperator[i].setIcon(imageOperator[0]);
		}
	}
	
	private class Solve extends Thread {
		@Override
		public void run() {
			Runnable runner = new Runnable() {
				@Override
				public void run() {
				}
			};
			risolvi(runner);
		}
	}
}
