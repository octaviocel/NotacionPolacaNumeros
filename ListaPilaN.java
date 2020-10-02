package NotacionPolaca;

public class ListaPilaN {
	PilaNum inicio=null;
    
    public void push(int dato){
        PilaNum nuevo= nuevaPila(dato);
        if(!isEmpty()){
            nuevo.sig = inicio;
        }
        inicio = nuevo;
    }
    
    public int pop(){
        int resultado=inicio.dato;
        PilaNum aux = inicio;
        inicio=inicio.sig;
        aux.sig = null;
        return resultado;
    }
    
    public int pick(){
        return inicio.dato;
    }
    
    public boolean isEmpty(){
        return inicio==null;
    }
    
    public PilaNum nuevaPila(int dato){
        PilaNum nuevo =  new PilaNum();
        nuevo.dato=dato;
        nuevo.sig=null;
        return nuevo;
    }
}
