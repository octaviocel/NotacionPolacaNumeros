package NotacionPolaca;

public class PilaString {
	PilaS inicio=null;
    
    public void push(String dato){
        PilaS nuevo= nuevaPila(dato);
        if(!isEmpty()){
            nuevo.sig = inicio;
        }
        inicio = nuevo;
    }
    
    public String pop(){
        String resultado=inicio.dato;
        PilaS aux = inicio;
        inicio=inicio.sig;
        aux.sig = null;
        return resultado;
    }
    
    public String pick(){
        return inicio.dato;
    }
    
    public boolean isEmpty(){
        return inicio==null;
    }
    
    public PilaS nuevaPila(String dato){
        PilaS nuevo =  new PilaS();
        nuevo.dato=dato;
        nuevo.sig=null;
        return nuevo;
    }
}
