package tables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;


import asint.TinyASint.Dec_Type;

import asint.TinyASint.Tipo;
import asint.TinyASint.Tipo_Id;


public class Vinculation_data {
	public Stack<Map<String, Object>> tablaDeSimbolos;
	public Map<Object, Object> vinculos;
	public ArrayList<String> errores;
	
	public Vinculation_data() {
		tablaDeSimbolos = new Stack<Map<String, Object>>();	
		vinculos = new HashMap<Object, Object>();	
		this.errores= new ArrayList<String>();
	}
	public void abreBloque() {
		tablaDeSimbolos.push(new HashMap<String, Object>());		
	}

	public void cierraBloque() {
		tablaDeSimbolos.pop();
	}
	
	
	public void insertaError(String error,int fila, int columna) {
		this.errores.add(error + "   Fila: " + fila + ",  Columna: "+ columna);
	}
	public boolean inserta_en_ts(String string, Object v_declaracion){
		Map<String, Object> ts = tablaDeSimbolos.peek();		
		if (ts.get(string) != null){ 
			return false; 
		}
		ts.put(string, v_declaracion);
		return true;
	}
	
	public boolean insertaVinculo(Object nodo, Object vinculo){
		if (vinculos.get(nodo) != null){ return false; }
		vinculos.put(nodo, vinculo);
		return true;
	}
	
	
	public Tipo ref(Tipo tipo) {
		
		if(!(tipo instanceof Tipo_Id)) {
			return tipo;		
		}
		
		Tipo_Id type = (Tipo_Id)tipo;
		ListIterator<Map<String, Object>> it = tablaDeSimbolos.listIterator(tablaDeSimbolos.size());
		while (it.hasPrevious()){
			Object e = it.previous().get(type.id().toString());
			Dec_Type sig = (Dec_Type) e;
			if (e != null){
				return ref(sig.tipo());
			}
		}
		
		return null;
	}
	public Tipo refNodo(Tipo tipo) {
				
	}	
	
}
