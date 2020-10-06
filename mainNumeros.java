package NotacionPolaca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class mainNumeros {
	public static int jerarquia(String car) {// obtenemos la jerarquia de la operacion
		char caracter=car.charAt(0);
		
		if(caracter == '+'||caracter == '-') {
			return 2;
		}else if(caracter == '*'||caracter == '/') {
			return 3;
		}else if(caracter == '^') {
			return 4;
		}else if(caracter == '(') {
			return 1;
		}
		return 0;
	}
	public static boolean verificajera(int x, int y) {//verifica cual jerarquia es mayor
		return x>y;
	}
	public static ListaColNu resuelveNotacion(ListaColNu x) {
		ListaColNu aux = x;
		PilaString pila= new PilaString();
		ListaColNu regreso=new ListaColNu();
		while(aux.fin!=null) {
			String temp=aux.fin.dato;
			boolean es= EsOperacion(temp);
			if(es==true) {
				int jerar = jerarquia(temp);
				if(pila.isEmpty()==true) {
					pila.push(temp);
				}else {
					if(jerar==0) {
						String prov=pila.pop();
						int pro=jerarquia(prov);
						while(pro!=1){
							regreso.put(prov);
							if(pila.isEmpty()!=true) {
							prov=pila.pop();
							pro=jerarquia(prov);
							}else {
								pro=1;
							}
							}
						
						
					}else if(jerar==1) {
						pila.push(temp);
					}else {
					String tienepila = pila.pick();
					int cotr = jerarquia(tienepila);
					boolean comparajerarquia=verificajera(jerar,cotr);
					if(comparajerarquia==true|| cotr==1) {
						pila.push(temp);
					}else {
						
							String b=pila.pop();
							char trans= b.charAt(0);
							System.out.println(trans);
							if(trans=='('|| trans==')') {
								
							}else {
							regreso.put(Character.toString(trans));
							}
							
						pila.push(temp);
						}
					}
				}
			}else {
				regreso.put(temp);
			}
			aux.fin=aux.fin.ant;
		}
		if(pila.isEmpty()!=true) {
			while(pila.isEmpty()!=true) {
			String a=pila.pop();
			char ultimo=a.charAt(0);
			if(ultimo=='('|| ultimo==')') {
				
			}else {
			
			regreso.put(Character.toString(ultimo));
			}
			}
		}
		return regreso;
		
	}

	public static void main(String[] args) throws IOException {
		try (// TODO Auto-generated method stub
			Scanner pen = new Scanner(System.in)) {
				System.out.println("BIENVENIDO A LA NOTACION POLACA DE NUMEROS");
				System.out.println("NOTA: TU ARCHIVO DEBE CONTENER LA NOCACION POLACA SEPARADA POR ESPACIONS");
				System.out.println("RESOLVAMOS TU OPERACION POLACA");

				System.out.println("Ingresa la ruta del archivo ejemplo(C:\\Users\\DELL\\Desktop\\)");
				String leer = pen.next();
				System.out.println("Ingresa el nombre del archivo, ejemplo(input.txt)");
				String nombre = pen.next();
				File file =new File(leer+nombre);
				FileReader reader = new FileReader (file);  
				BufferedReader li = new BufferedReader(reader);

				ListaColNu cola=new ListaColNu();
				String fre= li.readLine();
				StringTokenizer tokens = new StringTokenizer(fre," ");
				while(tokens.hasMoreTokens()){
					cola.put(tokens.nextToken());
				}
				reader.close();
				System.out.println("Tu ecuacion es: ");
				cola.recorre();
				ListaColNu ecuacion= resuelveNotacion(cola);
				System.out.println();
				System.out.println("Tu ecuacion polaca es: ");
				ecuacion.recorre();
				System.out.println();
				int resul = resuelve(ecuacion);
				System.out.println("El resultado de tu ecuacion es: ");
				System.out.println(resul);
				
			}
	}
	
	public static boolean ver(String y) { //funcion que verifica si es una operacion
		char v= y.charAt(0);
		if(v=='+'||v=='-'||v=='*'||v=='/'||v=='^'||v=='('||v==')') {
			return true;
		}else {
			return false;
		}
	}
	public static boolean verv(char v) { //funcion que verifica si es una operacion
		
		if(v=='+'||v=='-'||v=='*'||v=='/'||v=='^') {
			return true;
		}else {
			return false;
		}
	}
	public static int operacion(String y, int s, int d) {// hace la operacion que manda el string y los numeros
		char v= y.charAt(0);
		if(v=='+') {
			return s+d;
		}else if(v=='-') {
			return s-d;
		}else if(v=='*') {
			return s*d;
		}else if(v=='/') {
			if(d!=0) {
				return s/d;
			}
		} 
			return (int) Math.pow(s, d);
	}
	public static int resuelve(ListaColNu cola) {
		ListaPilaN pila= new ListaPilaN();
		while(cola.fin!=null) {
			if(cola.fin!=null) {
			String v= cola.sacacola();
			boolean vera= EsOperacion(v);

			if(vera==true) {
				if(!pila.isEmpty()) {
					int g=pila.pop();
					if(!pila.isEmpty()) {
					int f=pila.pop();
					int re=operacion(v,f,g);
					pila.push(re);
					}
				}
			}else {
				pila.push(convierteNumero(v));
			}
			}
		}
		if(!pila.isEmpty()) {
			return pila.pick();
		}
		return 0;
	}
	public static boolean EsOperacion(String x) {
		int j = x.length();
		if(j>1) {
			return false;
		}else {
			boolean segundo=ver(x);
			if(segundo==false) {
				return false;
			}
			return true;
		}
	}
	public static int convierteNumero(String x) {
		boolean negativo= verv(x.charAt(0));
		if(negativo==true) {
			String resul= x.substring(1);
			int re= Integer.parseInt(resul)*(-1);
			return re;
		}else {
			return Integer.parseInt(x);
		}
		
	}
}
