import java.util.ArrayList;

/**
 * schema del calcolo enigmatico
 * 2 variabili, number[][] e operator[]
 * per i number lo schema è il seguente:
 * 0 -> [0][1][2] op [3][4][5] = [6][7][8]
 *            op           op          op
 * 1 -> [0][1][2] op [3][4][5] = [6][7][8]
 * ---------------------------------------
 * 2 -> [0][1][2] op [3][4][5] = [6][7][8]
 * mentre per gli operatori è:
 *                0
 *            1            2           3
 *                4
 *                5
 */
public class Schema {

	private char[][] number; // abcdefghij ' '=void

	/* 0 = void
	 * 1 = +
	 * 2 = x
	 * 3 = -
	 * 4 = :
	 */
	private char[] operator; 

	private int[] n;

	private ArrayList<Integer[]> ABC = new ArrayList<Integer[]>();
	private ArrayList<Integer[]> DEF = new ArrayList<Integer[]>();
	private ArrayList<Integer[]> GHI = new ArrayList<Integer[]>();
	
	private int[][] solution = new int[3][9];


	/**
	 * inizializza la classe
	 */
	public Schema() {
		this.number = new char[3][9];
		this.operator = new char[6];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.number[i][j] = ' ';
			}
		}
		for (int i = 0; i < 6; i++) {
			operator[i] = ' ';
		}
		n = new int[10];
	}

	public void setNumber(int i, int j, int symbol) {
		if (symbol == 10) {
			this.number[i][j] = ' ';
			return;
		}
		this.number[i][j] = (char)(symbol+97);
	}

	public void setOperator(int i, int op) {
		char c;
		switch (op) {
		case 1:
			c = '+';
			break;
		case 2:
			c = 'x';
			break;
		case 3:
			c = '-';
			break;
		case 4:
			c = ':';
			break;
		default:
			c = ' ';
			break;
		}

		this.operator[i] = c;
	}

	public String toString() {
		String s = "\n\n";

		s += this.number[0][0] + "" + this.number[0][1] + "" + this.number[0][2];
		s += this.operator[0];
		s += this.number[0][3] + "" + this.number[0][4] + "" + this.number[0][5];
		s += '=';
		s += this.number[0][6] + "" + this.number[0][7] + "" + this.number[0][8];
		s += '\n';
		s += "  " + this.operator[1] + "   " + this.operator[2] + "   " + this.operator[3];
		s += '\n';
		s += this.number[1][0] + "" + this.number[1][1] + "" + this.number[1][2];
		s += this.operator[4];
		s += this.number[1][3] + "" + this.number[1][4] + "" + this.number[1][5];
		s += '=';
		s += this.number[1][6] + "" + this.number[1][7] + "" + this.number[1][8];
		s += '\n';
		s += "-----------";
		s += '\n';
		s += this.number[2][0] + "" + this.number[2][1] + "" + this.number[2][2];
		s += this.operator[5];
		s += this.number[2][3] + "" + this.number[2][4] + "" + this.number[2][5];
		s += '=';
		s += this.number[2][6] + "" + this.number[2][7] + "" + this.number[2][8];
		return s;
	}

	public int[][] getSolution() {
		return this.solution;
	}

	private boolean getIfOpIsCorrect(int a, char op, int b, int c) {
		switch (op) {
		case '+':
			return a>0 && b>0 ? a+b == c : false;
		case 'x':
			return a>0 && b>0 ? a*b == c : false;
		case '-':
			return a>0 && b>0 ? a-b == c : false;
		case ':':
			if (b==0) return false;
			return a>0 && b>0 ? a/b == c : false;
		default:
			return false;
		}
	}

	private int getCifra(int i, int[] n) {

		if (i==0) return (this.number[0][0] != ' ' ? n[this.number[0][0]-97]*100 : 0) 
				+ (this.number[0][1] != ' ' ? n[this.number[0][1]-97]*10 : 0)
				+ (this.number[0][2] != ' ' ? n[this.number[0][2]-97] : 0);
		if (i==1) return (this.number[0][3] != ' ' ? n[this.number[0][3]-97]*100 : 0) 
				+ (this.number[0][4] != ' ' ? n[this.number[0][4]-97]*10 : 0)
				+ (this.number[0][5] != ' ' ? n[this.number[0][5]-97] : 0);
		if (i==2) return (this.number[0][6] != ' ' ? n[this.number[0][6]-97]*100 : 0) 
				+ (this.number[0][7] != ' ' ? n[this.number[0][7]-97]*10 : 0)
				+ (this.number[0][8] != ' ' ? n[this.number[0][8]-97] : 0);
		if (i==3) return (this.number[1][0] != ' ' ? n[this.number[1][0]-97]*100 : 0) 
				+ (this.number[1][1] != ' ' ? n[this.number[1][1]-97]*10 : 0)
				+ (this.number[1][2] != ' ' ? n[this.number[1][2]-97] : 0);
		if (i==4) return (this.number[1][3] != ' ' ? n[this.number[1][3]-97]*100 : 0) 
				+ (this.number[1][4] != ' ' ? n[this.number[1][4]-97]*10 : 0)
				+ (this.number[1][5] != ' ' ? n[this.number[1][5]-97] : 0);
		if (i==5) return (this.number[1][6] != ' ' ? n[this.number[1][6]-97]*100 : 0) 
				+ (this.number[1][7] != ' ' ? n[this.number[1][7]-97]*10 : 0)
				+ (this.number[1][8] != ' ' ? n[this.number[1][8]-97] : 0);
		if (i==6) return (this.number[2][0] != ' ' ? n[this.number[2][0]-97]*100 : 0) 
				+ (this.number[2][1] != ' ' ? n[this.number[2][1]-97]*10 : 0)
				+ (this.number[2][2] != ' ' ? n[this.number[2][2]-97] : 0);
		if (i==7) return (this.number[2][3] != ' ' ? n[this.number[2][3]-97]*100 : 0) 
				+ (this.number[2][4] != ' ' ? n[this.number[2][4]-97]*10 : 0)
				+ (this.number[2][5] != ' ' ? n[this.number[2][5]-97] : 0);
		if (i==8) return (this.number[2][6] != ' ' ? n[this.number[2][6]-97]*100 : 0) 
				+ (this.number[2][7] != ' ' ? n[this.number[2][7]-97]*10 : 0)
				+ (this.number[2][8] != ' ' ? n[this.number[2][8]-97] : 0);

		return 0;
	}

	private boolean isAllDifferent(int[] n) {
		for (int i = 0; i < n.length; i++) {
			if (n[i] != -1) {
				for (int j = i+1; j < n.length; j++) {
					if (n[i] == n[j]) return false;
				}
			}
		}
		return true;
	}
	
	private ArrayList<Integer[]> getLine(int l) {
		boolean unique;
		ArrayList<Character> line = new ArrayList<Character>();
		ArrayList<Integer[]> result = new ArrayList<Integer[]>();
		int A,B,C;
		int startCifra = 0;
		int operazione = 0;
		int x=0;
		
		switch (l) {
		case 1:
			x=0;
			startCifra = 0;
			operazione = 0;
			break;
		case 2:
			x=1;
			startCifra = 3;
			operazione = 4;
			break;
		case 3:
			x=2;
			startCifra = 6;
			operazione = 5;
			break;
		}
		
		for (int i=0; i<9; i++) {
			if (this.number[x][i] != ' ') {
				unique = true;
				for (int t=0; t< line.size(); t++) {
					unique &= (this.number[x][i] != line.get(t));
				}
				if (unique) {
					line.add(this.number[x][i]);
				}
			}
		}
		
		int size = line.size();
		
		// in questo punto nell'arraylist firstRow ho tutti i simboli della linea
		// cerchiamo tutti i valori per cui l'operazione è vera
		
		result.clear();
		
		for (int i=0; i<10; i++) this.n[i] = -1;
		
		if (size == 1) {
			for (int a=0; a<10; a++) {
				n[line.get(0)-97] = a;
				A = this.getCifra(startCifra + 0, n);
				B = this.getCifra(startCifra + 1, n);
				C = this.getCifra(startCifra + 2, n);
				if (this.isAllDifferent(n) && this.getIfOpIsCorrect(A,this.operator[operazione],B,C)) {
					Integer[] nums = {A,B,C};
					result.add(nums);
				}
			}
		}

		if (size == 2) {
			for (int a=0; a<10; a++) {
				for (int b=0; b<10; b++) {
					n[line.get(0)-97] = a;
					n[line.get(1)-97] = b;
					A = this.getCifra(startCifra + 0, n);
					B = this.getCifra(startCifra + 1, n);
					C = this.getCifra(startCifra + 2, n);
					if (this.isAllDifferent(n) && this.getIfOpIsCorrect(A,this.operator[operazione],B,C)) {
						Integer[] nums = {A,B,C};
						result.add(nums);
					}
				}
			}
		}

		if (size == 3) {
			for (int a=0; a<10; a++) {
				for (int b=0; b<10; b++) {
					for (int c=0; c<10; c++) {
						n[line.get(0)-97] = a;
						n[line.get(1)-97] = b;
						n[line.get(2)-97] = c;
						A = this.getCifra(startCifra + 0, n);
						B = this.getCifra(startCifra + 1, n);
						C = this.getCifra(startCifra + 2, n);
						if (this.isAllDifferent(n) && this.getIfOpIsCorrect(A,this.operator[operazione],B,C)) {
							Integer[] nums = {A,B,C};
							result.add(nums);
						}
					}
				}
			}
		}

		if (size == 4) {
			for (int a=0; a<10; a++) {
				for (int b=0; b<10; b++) {
					for (int c=0; c<10; c++) {
						for (int d=0; d<10; d++) {
							n[line.get(0)-97] = a;
							n[line.get(1)-97] = b;
							n[line.get(2)-97] = c;
							n[line.get(3)-97] = d;
							A = this.getCifra(startCifra + 0, n);
							B = this.getCifra(startCifra + 1, n);
							C = this.getCifra(startCifra + 2, n);
							if (this.isAllDifferent(n) && this.getIfOpIsCorrect(A,this.operator[operazione],B,C)) {
								Integer[] nums = {A,B,C};
								result.add(nums);
							}
						}
					}
				}
			}
		}

		if (size == 5) {
			for (int a=0; a<10; a++) {
				for (int b=0; b<10; b++) {
					for (int c=0; c<10; c++) {
						for (int d=0; d<10; d++) {
							for (int e=0; e<10; e++) {
								n[line.get(0)-97] = a;
								n[line.get(1)-97] = b;
								n[line.get(2)-97] = c;
								n[line.get(3)-97] = d;
								n[line.get(4)-97] = e;
								A = this.getCifra(startCifra + 0, n);
								B = this.getCifra(startCifra + 1, n);
								C = this.getCifra(startCifra + 2, n);
								if (this.isAllDifferent(n) && this.getIfOpIsCorrect(A,this.operator[operazione],B,C)) {
									Integer[] nums = {A,B,C};
									result.add(nums);
								}
							}
						}
					}
				}
			}
		}

		if (size == 6) {
			for (int a=0; a<10; a++) {
				for (int b=0; b<10; b++) {
					for (int c=0; c<10; c++) {
						for (int d=0; d<10; d++) {
							for (int e=0; e<10; e++) {
								for (int f=0; f<10; f++) {
									n[line.get(0)-97] = a;
									n[line.get(1)-97] = b;
									n[line.get(2)-97] = c;
									n[line.get(3)-97] = d;
									n[line.get(4)-97] = e;
									n[line.get(5)-97] = f;
									A = this.getCifra(startCifra + 0, n);
									B = this.getCifra(startCifra + 1, n);
									C = this.getCifra(startCifra + 2, n);
									if (this.isAllDifferent(n) && this.getIfOpIsCorrect(A,this.operator[operazione],B,C)) {
										Integer[] nums = {A,B,C};
										result.add(nums);
									}
								}
							}
						}
					}
				}
			}
		}
		
		if (size == 7) {
			for (int a=0; a<10; a++) {
				for (int b=0; b<10; b++) {
					for (int c=0; c<10; c++) {
						for (int d=0; d<10; d++) {
							for (int e=0; e<10; e++) {
								for (int f=0; f<10; f++) {
									for (int g=0; g<10; g++) {
										n[line.get(0)-97] = a;
										n[line.get(1)-97] = b;
										n[line.get(2)-97] = c;
										n[line.get(3)-97] = d;
										n[line.get(4)-97] = e;
										n[line.get(5)-97] = f;
										n[line.get(6)-97] = g;
										A = this.getCifra(startCifra + 0, n);
										B = this.getCifra(startCifra + 1, n);
										C = this.getCifra(startCifra + 2, n);
										if (this.isAllDifferent(n) && this.getIfOpIsCorrect(A,this.operator[operazione],B,C)) {
											Integer[] nums = {A,B,C};
											result.add(nums);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		if (size == 8) {
			for (int a=0; a<10; a++) {
				for (int b=0; b<10; b++) {
					for (int c=0; c<10; c++) {
						for (int d=0; d<10; d++) {
							for (int e=0; e<10; e++) {
								for (int f=0; f<10; f++) {
									for (int g=0; g<10; g++) {
										for (int h=0; h<10; h++) {
											n[line.get(0)-97] = a;
											n[line.get(1)-97] = b;
											n[line.get(2)-97] = c;
											n[line.get(3)-97] = d;
											n[line.get(4)-97] = e;
											n[line.get(5)-97] = f;
											n[line.get(6)-97] = g;
											n[line.get(7)-97] = h;
											A = this.getCifra(startCifra + 0, n);
											B = this.getCifra(startCifra + 1, n);
											C = this.getCifra(startCifra + 2, n);
											if (this.isAllDifferent(n) && this.getIfOpIsCorrect(A,this.operator[operazione],B,C)) {
												Integer[] nums = {A,B,C};
												result.add(nums);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (size == 9) {
			for (int a=0; a<10; a++) {
				for (int b=0; b<10; b++) {
					for (int c=0; c<10; c++) {
						for (int d=0; d<10; d++) {
							for (int e=0; e<10; e++) {
								for (int f=0; f<10; f++) {
									for (int g=0; g<10; g++) {
										for (int h=0; h<10; h++) {
											for (int i=0; i<10; i++) {
												n[line.get(0)-97] = a;
												n[line.get(1)-97] = b;
												n[line.get(2)-97] = c;
												n[line.get(3)-97] = d;
												n[line.get(4)-97] = e;
												n[line.get(5)-97] = f;
												n[line.get(6)-97] = g;
												n[line.get(7)-97] = h;
												n[line.get(8)-97] = i;
												A = this.getCifra(startCifra + 0, n);
												B = this.getCifra(startCifra + 1, n);
												C = this.getCifra(startCifra + 2, n);
												if (this.isAllDifferent(n) && this.getIfOpIsCorrect(A,this.operator[operazione],B,C)) {
													Integer[] nums = {A,B,C};
													result.add(nums);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}		
		return result;
	}

	public boolean risolvi() {
		if(!this.isAllNumberFill()) return false;
		this.ABC = getLine(1);
		this.DEF = getLine(2);
		this.GHI = getLine(3);
		for (Integer[] x : ABC) {
			for (Integer[] y : DEF) {
				for (Integer[] z : GHI) {
					if (this.getIfOpIsCorrect(x[0], this.operator[1], y[0], z[0]) 
					 && this.getIfOpIsCorrect(x[1], this.operator[2], y[1], z[1])
					 && this.getIfOpIsCorrect(x[2], this.operator[3], y[2], z[2])
					 ) {
						if (this.checkIfSymbolsCorrect(x,y,z)) {
							return true;							
						}
					}
				}
			}
		}
		return false;
	}
	
	private boolean checkIfSymbolsCorrect(Integer[] x, Integer[] y, Integer[] z) {
		boolean result = true;
		char[] s = new char[10];
		for (int i=0; i<s.length; i++) s[i] = ' ';
		this.fromIntToSingleDigit(x, y, z);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.number[i][j] != ' ') {
					if (s[this.solution[i][j]] == ' ') {
						s[this.solution[i][j]] = this.number[i][j];
					} else {
						result &= s[this.solution[i][j]] == this.number[i][j];
					}
				}
			}
		}
		return result;
	}
	
	private boolean isAllNumberFill() {
		boolean fill = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				fill |= this.number[i][j] != ' ';
				if ((j-2)%3 == 0) {
					if (!fill) return false;
					fill = false;
				}
			}
		}
		return true;
	}

	private void fromIntToSingleDigit(Integer[] x, Integer[] y, Integer[] z) {

		this.solution[0][0] = x[0] / 100;
		this.solution[0][1] = (x[0] - ((x[0] / 100) * 100)) / 10;
		this.solution[0][2] = (x[0] - ((x[0] / 100) * 100)) - (((x[0] - ((x[0] / 100) * 100)) / 10)) * 10;

		this.solution[0][3] = x[1] / 100;
		this.solution[0][4] = (x[1] - ((x[1] / 100) * 100)) / 10;
		this.solution[0][5] = (x[1] - ((x[1] / 100) * 100)) - (((x[1] - ((x[1] / 100) * 100)) / 10)) * 10;

		this.solution[0][6] = x[2] / 100;
		this.solution[0][7] = (x[2] - ((x[2] / 100) * 100)) / 10;
		this.solution[0][8] = (x[2] - ((x[2] / 100) * 100)) - (((x[2] - ((x[2] / 100) * 100)) / 10)) * 10;

		this.solution[1][0] = y[0] / 100;
		this.solution[1][1] = (y[0] - ((y[0] / 100) * 100)) / 10;
		this.solution[1][2] = (y[0] - ((y[0] / 100) * 100)) - (((y[0] - ((y[0] / 100) * 100)) / 10)) * 10;

		this.solution[1][3] = y[1] / 100;
		this.solution[1][4] = (y[1] - ((y[1] / 100) * 100)) / 10;
		this.solution[1][5] = (y[1] - ((y[1] / 100) * 100)) - (((y[1] - ((y[1] / 100) * 100)) / 10)) * 10;

		this.solution[1][6] = y[2] / 100;
		this.solution[1][7] = (y[2] - ((y[2] / 100) * 100)) / 10;
		this.solution[1][8] = (y[2] - ((y[2] / 100) * 100)) - (((y[2] - ((y[2] / 100) * 100)) / 10)) * 10;

		this.solution[2][0] = z[0] / 100;
		this.solution[2][1] = (z[0] - ((z[0] / 100) * 100)) / 10;
		this.solution[2][2] = (z[0] - ((z[0] / 100) * 100)) - (((z[0] - ((z[0] / 100) * 100)) / 10)) * 10;

		this.solution[2][3] = z[1] / 100;
		this.solution[2][4] = (z[1] - ((z[1] / 100) * 100)) / 10;
		this.solution[2][5] = (z[1] - ((z[1] / 100) * 100)) - (((z[1] - ((z[1] / 100) * 100)) / 10)) * 10;

		this.solution[2][6] = z[2] / 100;
		this.solution[2][7] = (z[2] - ((z[2] / 100) * 100)) / 10;
		this.solution[2][8] = (z[2] - ((z[2] / 100) * 100)) - (((z[2] - ((z[2] / 100) * 100)) / 10)) * 10;

	}
}