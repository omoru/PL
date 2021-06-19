package tables;

import java.util.HashMap;
import java.util.Map;

import asint.Procesamiento;
import asint.TinyASint.Exp;
import asint.TinyASint.Flecha;
import asint.TinyASint.Id;
import asint.TinyASint.Index;
import asint.TinyASint.Indireccion;
import asint.TinyASint.Punto;
import asint.TinyASint.R_null;
import asint.TinyASint.Tipo;
import asint.TinyASint.Tipo_Array;
import asint.TinyASint.Tipo_Id;
import asint.TinyASint.Tipo_Reg;
import asint.TinyASint.Tipo_String;
import asint.TinyASint.Tipo_bool;
import asint.TinyASint.Tipo_int;
import asint.TinyASint.Tipo_null;
import asint.TinyASint.Tipo_real;

public class Comp_tipos_data {
	public Map<Object, Object> tipos;
	public Object ok;
	public Object error;
	public Tipo_null t_null;
	public Tipo_int t_int;
	public Tipo_real t_real;
	public Tipo_bool t_bool;
	public Tipo_String t_string;


	public Comp_tipos_data() {
		tipos = new HashMap<Object, Object>();
		this.ok = new Object();
		this.error = new Object();
		this.t_null =new Tipo_null();
		this.t_bool = new Tipo_bool();
		this.t_real = new Tipo_real();
		this.t_int = new Tipo_int();
		this.t_string = new Tipo_String();
		
		
	}
	
	public boolean esDesignador(Exp exp) {
		if(exp instanceof Id || exp instanceof Indireccion ||  exp instanceof Index
				||  exp instanceof Punto ||  exp instanceof Flecha)
			return true;	
		return false;
	}

	
}
