package NotacionPolaca;

public class ListaColNu {
	ColaNum inicio=null;
    ColaNum fin=null;
    
    public  void put (String dato) {
	ColaNum nuevo = nuevaCola(dato);
	if(isEmpty()) {
		inicio=nuevo;
		fin=nuevo;
            }else {
		nuevo.sig=inicio;
		inicio.ant=nuevo;
		inicio=nuevo;
		}
	}
    public String sacacola(){
        
    	if(fin!=null) {
            ColaNum aux=fin;
            String resultado = aux.dato;
            if(fin.ant!=null) {
            fin =fin.ant;
            fin.sig=null;
            aux.ant=null;
            }else {
            	fin=null;
            }
            return resultado;
            }
            return "0";
        
    }
    public void sacainicio() {
    	ColaNum aux=inicio;
    	inicio=inicio.sig;
    	inicio.ant=null;
    	aux.sig=null;
    }
    public boolean isEmpty(){
        return inicio==null&&fin==null;
    }
    public ColaNum nuevaCola(String dato){
        ColaNum nuevo =  new ColaNum();
        nuevo.dato=dato;
        nuevo.sig=null;
        nuevo.ant=null;
        return nuevo;
    }
    public void recorre(){
        ColaNum aux = fin;
        while(aux!=null){
            System.out.print(aux.dato+" ");
            aux=aux.ant;
        }
    }
    public int largo() {
    int r=0;
	ColaNum aux=inicio;
	while(aux!=null) {
		r++;
		aux=aux.sig;
	}
	return r;
    }
}
