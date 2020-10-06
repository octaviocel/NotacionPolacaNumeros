package NotacionPolaca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class mainNumeros {

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
				int resul = resuelve(cola);
				System.out.println("El resultado de tu notacion polaca es: ");
				System.out.println(resul);
				
				reader.close();
				/*while(cola.inicio!=null) {
					System.out.println(cola.inicio.dato);
					cola.inicio=cola.inicio.sig;
				}*/
			}
	}
	
	public static boolean ver(String y) { //funcion que verifica si es una operacion
		char v= y.charAt(0);
		if(v=='+'||v=='-'||v=='*'||v=='/'||v=='^') {
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
	public static boolean EsOperacion(String x) { //retorna si es ina operacion o no
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
	public static int convierteNumero(String x) { //convierte el numero si es negativo o positivo
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
