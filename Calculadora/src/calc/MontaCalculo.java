package calc;
import javax.swing.JOptionPane;

public class MontaCalculo {
	private static StringBuffer expressao = new StringBuffer();
	private static boolean numero = true;
	private static Double resultado = null;

	public static boolean isNumero() {
		return numero;
	}

	public static void efetuaCalculo( boolean zerar ) {
		// Aluno aqui

		EfetuaCalculo calc1 = new EfetuaCalculo();
		String[] vet = expressao.toString().split(" ");
		Double num1 = null;
		String num2 = null, ope = null;
		for (int i = 0; i < vet.length; i++) {
			String s = vet[i];
			if (num1 == null)
				num1 = Double.parseDouble(s);
			else if (ope == null)
				ope = s;
			else if (num2 == null)
				num2 = s;
			if (num1 != null && ope != null && (num2 != null || i + 1 >= vet.length)) {
				System.out.println(num1 + " " + ope + " " + num2);
				double doubleValor = 0.0;
				switch (ope) {
				case "+":
					if (num2 != null)
						doubleValor = Double.parseDouble(num2);
					num1 = calc1.Somar(num1, doubleValor);
					break;
				case "-":
					if (num2 != null)
						doubleValor = Double.parseDouble(num2);
					num1 = calc1.Subtrair(num1, doubleValor);
					break;
				case "*":
					doubleValor = 1;
					if (num2 != null)
						doubleValor = Double.parseDouble(num2);
					num1 = calc1.Mult(num1, doubleValor);
					break;
				case "/":
					doubleValor = 1;
					if (num2 != null)
						doubleValor = Double.parseDouble(num2);

					if (doubleValor == 0) {
						JOptionPane.showMessageDialog(null, "N�o � poss�vel dividir por zero!");
					} else {
						num1 = calc1.Dividir(num1, doubleValor);
					}
					break;
				case "^":
					doubleValor = 1;
					if (num2 != null)
						doubleValor = Double.parseDouble(num2);
					num1 = calc1.Pot(num1, doubleValor);
					break;
				case "Raiz":
					doubleValor = 2;
					if (num2 != null)
						doubleValor = Double.parseDouble(num2);
					num1 = calc1.Raiz(num1, doubleValor);
					break;
				case "Log":
					doubleValor = 10;
					if (num2 != null)
						doubleValor = Double.parseDouble(num2);
					num1 = calc1.logb(num1, doubleValor);
					break;
				}
				num2 = null;
				ope = null;
			}
		}
		resultado = 0.0;
		if (num1 != null)
			resultado = num1;

		/**
		 * Para apagar a express�o e mostrar somente o resultado e ultima ele
		 * depois
		 */
		if ( zerar ) {
			expressao = new StringBuffer(String.valueOf(resultado));
			resultado = null;
		}
	}
	
	public static void negativarExpressao() {
		if ( numero ) {
			int pos = expressao.lastIndexOf(" ", expressao.length() - 2);
			if (pos != -1)
				expressao.insert(pos + 1, "-");
			else
				expressao.insert(0, "-");
		} else {
			efetuaCalculo( false );
			expressao.insert(0, "-");
		}
		/* Remove dupla nega��o */
		expressao = new StringBuffer( expressao.toString().replace("--", "") );
	}

	public static void limparTudo() {
		expressao = new StringBuffer();
		numero = true;
	}

	public static void limparUltimo() {
		if (! expressao.toString().isEmpty()) {
			System.out.println("limparUltimo : " + (expressao.substring(expressao.length() - 1)));
			if (! expressao.substring(expressao.length() - 1).equals(" ")) {
				expressao.delete(expressao.length() - 1, expressao.length());
			} else {
				int pos = expressao.lastIndexOf(" ", expressao.length() - 2);
				if (pos != -1) {
					expressao.delete(pos, expressao.length());
				} else {
					expressao.delete(expressao.length() - 1, expressao.length());
				}
			}
	
		}
	}

	public static StringBuffer getExpressao() {
		return expressao;
	}

	public static void setExpressao(StringBuffer expressao) {
		MontaCalculo.expressao = expressao;
	}

	public static void setOperacao(String operacao) {
		/**
		 * Criada essa condi��o para n�o deixar dois sinais de opera��es
		 * seguidos
		 */
		if (! numero) {
			int pos = expressao.lastIndexOf(" ", expressao.length() - 2);
			if (pos != -1)
				expressao.delete(pos, expressao.length());
		}

		/** Adiciona o zero antes de qualquer conta */
		if (expressao.toString().isEmpty())
			expressao.append("0");

		/**
		 * Adiciona espa�o antes e depois para poder separar numeros e opera��o
		 * depois
		 */
		expressao.append(" ").append(operacao).append(" ");
		numero = false;
		efetuaCalculo(false);
	}

	public static void setDigito(String digito) {
		if (!numero)
			numero = true;
		expressao.append(digito);
		efetuaCalculo(false);
	}

	public static Double getResultado() {
		return resultado;
	}


}