package calc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
- [1.0 PONTOS] Realizar as quatro operações (+, -, x, /).

- [2.0 PONTOS] Realizar corretamente as operações de negação, potenciação, radiciação (não apenas a raiz quadrada!!!) e logarítmo (não só na base 10!!!).

- [2.0 PONTOS] Criar um mostrador de expressões (que exibe toda a expressão) com o mesmo modo de funcionamento da calculadora do Windows (sobre o campo de edição).

- [2.0 PONTOS] Tratar o erro de divisão por ZERO quando o usuário entrar com denominador 0, mas permitir a operação (em float ou double) quando o usuário entrar com 0.0 (neste caso, se o numerador for diferente de zero, o resultado será INF)

- [1.0 PONTOS] Permitir ao usuário entrar valores com (.) ponto decimal

- [2.0 PONTOS] Encadear operações como na calculadora do Windows, ou seja, permitir operações com mais de dois operandos: "10 + 25 x 4 =", por exemplo

DATA DE ENTREGA CONFORME CRONOGRAMA
 
 */
public class Calculadora implements ActionListener {
	private JFrame frmCalculadora;
	private JPanel panel;
	private JTextField textFieldResultado;
	private JPanel panel_Teclado;
	private JButton btn_7;
	private JButton btn_8;
	private JButton btn_9;
	private JButton btn_Div;
	private JButton btn_4;
	private JButton btn_5;
	private JButton btn_6;
	private JButton btn_X;
	private JButton btn_1;
	private JButton btn_2;
	private JButton btn_3;
	private JButton btn_Menos;
	private JButton btn_Virgula;
	private JButton btn_0;
	private JButton btn_Negacao;
	private JButton btn_Igual;
	private JButton btn_Mais;
	private JTextField textFieldExpressao;
	private JButton btn_Clear;
	private JButton btn_Raiz;
	private JButton btn_Log;
	private JButton btn_Pot;
	private JButton btn_Deletar;

//	private StringBuffer operando = new StringBuffer();
//	private StringBuffer expressao = new StringBuffer();
//	private String operacao;
//	private String digito;
//	private Double resultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			/** Para ficar bonitinho */
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) { }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculadora window = new Calculadora();
					window.frmCalculadora.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Calculadora() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCalculadora = new JFrame();
		frmCalculadora.setSize(300, 375); // <==
		frmCalculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadora.setLocationRelativeTo(null);
		frmCalculadora.setResizable(false);
		
		panel = new JPanel();
		frmCalculadora.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textFieldResultado = new JTextField();
		/* Método para 'pegar' os teclas digitadas */
		textFieldResultado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed( KeyEvent e ) {
				String valueOf = null;
				if ( e.getKeyCode() == 8 )
					valueOf = "<";
				else if ( e.getKeyCode() == 10 )
					valueOf = "=";
				else if ( e.getKeyCode() == KeyEvent.VK_L )
					valueOf = "Log";
				else if ( e.getKeyCode() == KeyEvent.VK_R )
					valueOf = "Raiz";
				
				if ( valueOf == null )
					valueOf = String.valueOf( e.getKeyChar() );
				realizarOperacao( valueOf );
			}
		});
		textFieldResultado.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldResultado.setBounds(0, 22, 294, 36);
		textFieldResultado.setColumns(10);
		textFieldResultado.setEditable(false);
		panel.add(textFieldResultado);
		
		/** TextField com a expressão */
		textFieldExpressao = new JTextField();
		textFieldExpressao.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldExpressao.setText("Express\u00E3o");
		textFieldExpressao.setBackground(Color.WHITE);
		textFieldExpressao.setEnabled(false);
		textFieldExpressao.setColumns(10);
		textFieldExpressao.setBounds(0, 0, 294, 28);
		panel.add(textFieldExpressao);
		
		panel_Teclado = new JPanel();
		panel_Teclado.setBounds(0, 63, 294, 284);
		panel.add(panel_Teclado);
		GridBagLayout gbl_panel_Teclado = new GridBagLayout();
		gbl_panel_Teclado.columnWidths = new int[] { 73, 73, 73, 73, 0 };
		gbl_panel_Teclado.rowHeights = new int[] { 47, 47, 47, 47, 47, 47, 0 };
		gbl_panel_Teclado.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_Teclado.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		this.panel_Teclado.setLayout(gbl_panel_Teclado);
		
		btn_Deletar = new JButton("<");
		GridBagConstraints gbc_btn_Deletar = new GridBagConstraints();
		gbc_btn_Deletar.fill = GridBagConstraints.BOTH;
		gbc_btn_Deletar.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Deletar.gridx = 0;
		gbc_btn_Deletar.gridy = 0;
		panel_Teclado.add(btn_Deletar, gbc_btn_Deletar);
		
		btn_Clear = new JButton("C");
		GridBagConstraints gbc_btn_Clear = new GridBagConstraints();
		gbc_btn_Clear.fill = GridBagConstraints.BOTH;
		gbc_btn_Clear.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Clear.gridx = 1;
		gbc_btn_Clear.gridy = 0;
		panel_Teclado.add(btn_Clear, gbc_btn_Clear);
		
		btn_Log = new JButton("Log");
		GridBagConstraints gbc_btn_Log = new GridBagConstraints();
		gbc_btn_Log.fill = GridBagConstraints.BOTH;
		gbc_btn_Log.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Log.gridx = 2;
		gbc_btn_Log.gridy = 0;
		panel_Teclado.add(btn_Log, gbc_btn_Log);
		
		btn_Raiz = new JButton("Raiz");
		GridBagConstraints gbc_btn_Raiz = new GridBagConstraints();
		gbc_btn_Raiz.fill = GridBagConstraints.BOTH;
		gbc_btn_Raiz.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Raiz.gridx = 3;
		gbc_btn_Raiz.gridy = 0;
		panel_Teclado.add(btn_Raiz, gbc_btn_Raiz);
		
		btn_7 = new JButton("7");
		GridBagConstraints gbc_btn_7 = new GridBagConstraints();
		gbc_btn_7.fill = GridBagConstraints.BOTH;
		gbc_btn_7.insets = new Insets(0, 0, 5, 5);
		gbc_btn_7.gridx = 0;
		gbc_btn_7.gridy = 1;
		panel_Teclado.add(btn_7, gbc_btn_7);
		
		btn_8 = new JButton("8");
		GridBagConstraints gbc_btn_8 = new GridBagConstraints();
		gbc_btn_8.fill = GridBagConstraints.BOTH;
		gbc_btn_8.insets = new Insets(0, 0, 5, 5);
		gbc_btn_8.gridx = 1;
		gbc_btn_8.gridy = 1;
		panel_Teclado.add(btn_8, gbc_btn_8);
		
		btn_9 = new JButton("9");
		GridBagConstraints gbc_btn_9 = new GridBagConstraints();
		gbc_btn_9.fill = GridBagConstraints.BOTH;
		gbc_btn_9.insets = new Insets(0, 0, 5, 5);
		gbc_btn_9.gridx = 2;
		gbc_btn_9.gridy = 1;
		panel_Teclado.add(btn_9, gbc_btn_9);
		
		btn_Pot = new JButton("^");
		GridBagConstraints gbc_btn_Pot = new GridBagConstraints();
		gbc_btn_Pot.fill = GridBagConstraints.BOTH;
		gbc_btn_Pot.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Pot.gridx = 3;
		gbc_btn_Pot.gridy = 1;
		panel_Teclado.add(btn_Pot, gbc_btn_Pot);
		
		btn_4 = new JButton("4");
		GridBagConstraints gbc_btn_4 = new GridBagConstraints();
		gbc_btn_4.fill = GridBagConstraints.BOTH;
		gbc_btn_4.insets = new Insets(0, 0, 5, 5);
		gbc_btn_4.gridx = 0;
		gbc_btn_4.gridy = 2;
		panel_Teclado.add(btn_4, gbc_btn_4);
		
		btn_5 = new JButton("5");
		GridBagConstraints gbc_btn_5 = new GridBagConstraints();
		gbc_btn_5.fill = GridBagConstraints.BOTH;
		gbc_btn_5.insets = new Insets(0, 0, 5, 5);
		gbc_btn_5.gridx = 1;
		gbc_btn_5.gridy = 2;
		panel_Teclado.add(btn_5, gbc_btn_5);
		
		btn_6 = new JButton("6");
		GridBagConstraints gbc_btn_6 = new GridBagConstraints();
		gbc_btn_6.fill = GridBagConstraints.BOTH;
		gbc_btn_6.insets = new Insets(0, 0, 5, 5);
		gbc_btn_6.gridx = 2;
		gbc_btn_6.gridy = 2;
		panel_Teclado.add(btn_6, gbc_btn_6);
		
		btn_X = new JButton("*");
		GridBagConstraints gbc_btn_X = new GridBagConstraints();
		gbc_btn_X.fill = GridBagConstraints.BOTH;
		gbc_btn_X.insets = new Insets(0, 0, 5, 0);
		gbc_btn_X.gridx = 3;
		gbc_btn_X.gridy = 2;
		panel_Teclado.add(btn_X, gbc_btn_X);
		
		btn_1 = new JButton("1");
		GridBagConstraints gbc_btn_1 = new GridBagConstraints();
		gbc_btn_1.fill = GridBagConstraints.BOTH;
		gbc_btn_1.insets = new Insets(0, 0, 5, 5);
		gbc_btn_1.gridx = 0;
		gbc_btn_1.gridy = 3;
		panel_Teclado.add(btn_1, gbc_btn_1);
		
		btn_2 = new JButton("2");
		GridBagConstraints gbc_btn_2 = new GridBagConstraints();
		gbc_btn_2.fill = GridBagConstraints.BOTH;
		gbc_btn_2.insets = new Insets(0, 0, 5, 5);
		gbc_btn_2.gridx = 1;
		gbc_btn_2.gridy = 3;
		panel_Teclado.add(btn_2, gbc_btn_2);
		
		btn_3 = new JButton("3");
		GridBagConstraints gbc_btn_3 = new GridBagConstraints();
		gbc_btn_3.fill = GridBagConstraints.BOTH;
		gbc_btn_3.insets = new Insets(0, 0, 5, 5);
		gbc_btn_3.gridx = 2;
		gbc_btn_3.gridy = 3;
		panel_Teclado.add(btn_3, gbc_btn_3);
		
		btn_Div = new JButton("/");
		GridBagConstraints gbc_btn_Div = new GridBagConstraints();
		gbc_btn_Div.fill = GridBagConstraints.BOTH;
		gbc_btn_Div.insets = new Insets(0, 0, 5, 0);
		gbc_btn_Div.gridx = 3;
		gbc_btn_Div.gridy = 3;
		panel_Teclado.add(btn_Div, gbc_btn_Div);
		
		btn_0 = new JButton("0");
		GridBagConstraints gbc_btn_0 = new GridBagConstraints();
		gbc_btn_0.gridheight = 2;
		gbc_btn_0.fill = GridBagConstraints.BOTH;
		gbc_btn_0.insets = new Insets(0, 0, 0, 5);
		gbc_btn_0.gridx = 0;
		gbc_btn_0.gridy = 4;
		panel_Teclado.add(btn_0, gbc_btn_0);
		
		btn_Negacao = new JButton("~");
		GridBagConstraints gbc_btn_Negacao = new GridBagConstraints();
		gbc_btn_Negacao.fill = GridBagConstraints.BOTH;
		gbc_btn_Negacao.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Negacao.gridx = 1;
		gbc_btn_Negacao.gridy = 4;
		panel_Teclado.add(btn_Negacao, gbc_btn_Negacao);
		
		btn_Menos = new JButton("-");
		GridBagConstraints gbc_btn_Menos = new GridBagConstraints();
		gbc_btn_Menos.fill = GridBagConstraints.BOTH;
		gbc_btn_Menos.insets = new Insets(0, 0, 5, 5);
		gbc_btn_Menos.gridx = 2;
		gbc_btn_Menos.gridy = 4;
		panel_Teclado.add(btn_Menos, gbc_btn_Menos);
		
		btn_Igual = new JButton("=");
		GridBagConstraints gbc_btn_Igual = new GridBagConstraints();
		gbc_btn_Igual.gridheight = 2;
		gbc_btn_Igual.fill = GridBagConstraints.BOTH;
		gbc_btn_Igual.gridx = 3;
		gbc_btn_Igual.gridy = 4;
		panel_Teclado.add(btn_Igual, gbc_btn_Igual);
		
		btn_Virgula = new JButton(".");
		GridBagConstraints gbc_btn_Virgula = new GridBagConstraints();
		gbc_btn_Virgula.fill = GridBagConstraints.BOTH;
		gbc_btn_Virgula.insets = new Insets(0, 0, 0, 5);
		gbc_btn_Virgula.gridx = 1;
		gbc_btn_Virgula.gridy = 5;
		panel_Teclado.add(btn_Virgula, gbc_btn_Virgula);
		
		btn_Mais = new JButton("+");
		GridBagConstraints gbc_btn_Mais = new GridBagConstraints();
		gbc_btn_Mais.fill = GridBagConstraints.BOTH;
		gbc_btn_Mais.insets = new Insets(0, 0, 0, 5);
		gbc_btn_Mais.gridx = 2;
		gbc_btn_Mais.gridy = 5;
		panel_Teclado.add(btn_Mais, gbc_btn_Mais);
		
		Component[] c = panel_Teclado.getComponents();
		List<Component> cl = 
				new ArrayList<Component>(Arrays.asList(c));
		for (Component comp : cl) {
			((JButton)comp).addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String key = ((JButton)e.getSource()).getText();
		realizarOperacao(key);
	}

	private void realizarOperacao(String key) {
		System.out.println( "Key > " + key );
		switch (key) {
			case "~":
				MontaCalculo.negativarExpressao();
//				textField.setText(MontaCalculo.getExpressao().toString());
				break;
			case "C":
				MontaCalculo.limparTudo();
//				textField.setText(MontaCalculo.getExpressao().toString());
				break;
			case "<":
				MontaCalculo.limparUltimo();
//				textField.setText(MontaCalculo.getExpressao().toString());
				break;
			case ".":
				/* Verifica se o ultimo digitado não foi número ou a expressão está vazia
				 * acrescentando zero caso uma dessas duas seja verdade
				 */
				if ( ! MontaCalculo.isNumero() || MontaCalculo.getExpressao().toString().isEmpty() ) {
					MontaCalculo.setDigito("0");
				}
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9": 
				MontaCalculo.setDigito(key);
//				textField.setText(MontaCalculo.getExpressao().toString());
				break;
			case "+":
			case "-":
			case "*":
			case "/":
			case "^":
			case "Log":
			case "Raiz":
				MontaCalculo.setOperacao(key);
//				MontaCalculo.efetuaCalculo( false );
				//textField.setText("");
				break;
			case "=": 
				// você !!!
				MontaCalculo.efetuaCalculo( true );
//				textField.setText(MontaCalculo.getResultado().toString());
				break;
				
			default:
		}
		textFieldExpressao.setText(MontaCalculo.getExpressao().toString());
		if ( MontaCalculo.getResultado() == null )
			textFieldResultado.setText(MontaCalculo.getExpressao().toString());
		else
			textFieldResultado.setText(MontaCalculo.getResultado().toString());
	}


}