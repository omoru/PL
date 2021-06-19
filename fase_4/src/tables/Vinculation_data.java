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
		Object vinculo = vinculos.get(tipo);
		if(vinculo instanceof Dec_Type) {
			Dec_Type dec_type = (Dec_Type) vinculo;
			return ref(dec_type.tipo());
		}
		return null;
	}
	public Object refNodo(Object o) {
		if(!(o instanceof Tipo_Id)) {
			return o;		
		}
		Object vinculo = vinculos.get(o);
		if(vinculo instanceof Dec_Type) {
			Dec_Type dec_type = (Dec_Type) vinculo;
			return refNodo(dec_type.tipo());
		}
		return null;
	}	
	
}
