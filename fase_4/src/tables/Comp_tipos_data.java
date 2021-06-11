package tables;

import java.util.HashMap;
import java.util.Map;

import asint.TinyASint.Tipo;
import asint.TinyASint.Tipo_Reg;
import asint.TinyASint.Tipo_String;
import asint.TinyASint.Tipo_bool;
import asint.TinyASint.Tipo_int;
import asint.TinyASint.Tipo_real;

public class Comp_tipos_data {
	public Map<Object, Tipo> tipos;
	public Tipo ok;
	public Tipo error;
	public Tipo t_null;
	public Tipo_int t_int;
	public Tipo_real t_real;
	public Tipo_bool t_bool;
	public Tipo_String t_string;


	public Comp_tipos_data() {
		tipos = new HashMap<Object, Tipo>();
		this.ok = (Tipo) new Object();
		this.error = (Tipo) new Object();
		this.t_null =(Tipo) new Object();
		this.t_bool = new Tipo_bool();
		this.t_real = new Tipo_real();
		this.t_int = new Tipo_int();
		this.t_string = new Tipo_String();
		
	}

	
}
