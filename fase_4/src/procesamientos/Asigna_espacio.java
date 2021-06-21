package procesamientos;

import asint.ProcesamientoPorDefecto;
import asint.TinyASint.And;
import asint.TinyASint.Beq;
import asint.TinyASint.Bge;
import asint.TinyASint.Bgt;
import asint.TinyASint.Ble;
import asint.TinyASint.Bloque_lleno;
import asint.TinyASint.Bloque_vacio;
import asint.TinyASint.Blt;
import asint.TinyASint.Bne;
import asint.TinyASint.Campo;

import asint.TinyASint.Dec_Proc_con_params;
import asint.TinyASint.Dec_Proc_sin_params;
import asint.TinyASint.Dec_Type;
import asint.TinyASint.Dec_Var;
import asint.TinyASint.Div;

import asint.TinyASint.Flecha;
import asint.TinyASint.Id;
import asint.TinyASint.Index;
import asint.TinyASint.Indireccion;
import asint.TinyASint.Inst_asig;
import asint.TinyASint.Inst_call_con_params;
import asint.TinyASint.Inst_call_sin_params;
import asint.TinyASint.Inst_compuesta;
import asint.TinyASint.Inst_delete;
import asint.TinyASint.Inst_if_then;
import asint.TinyASint.Inst_if_then_else;
import asint.TinyASint.Inst_new;
import asint.TinyASint.Inst_nl;
import asint.TinyASint.Inst_read;
import asint.TinyASint.Inst_while;
import asint.TinyASint.Inst_write;
import asint.TinyASint.LCampos_muchos;
import asint.TinyASint.LCampos_uno;
import asint.TinyASint.LDecs_muchas;
import asint.TinyASint.LDecs_una;
import asint.TinyASint.LInst_aux_llena;
import asint.TinyASint.LInst_aux_vacia;
import asint.TinyASint.LParams_muchos;
import asint.TinyASint.LParams_uno;
import asint.TinyASint.LReal_Params_muchos;
import asint.TinyASint.LReal_Params_uno;
import asint.TinyASint.Lista_inst_muchas;
import asint.TinyASint.Lista_inst_una;
import asint.TinyASint.Menos_unario;
import asint.TinyASint.Mod;
import asint.TinyASint.Mul;
import asint.TinyASint.Not;
import asint.TinyASint.NumEnt;
import asint.TinyASint.NumReal;
import asint.TinyASint.Or;
import asint.TinyASint.Param_amp;
import asint.TinyASint.Param_sin_amp;
import asint.TinyASint.Prog_con_decs;
import asint.TinyASint.Prog_sin_decs;
import asint.TinyASint.Punto;
import asint.TinyASint.R_false;
import asint.TinyASint.R_null;
import asint.TinyASint.R_str;
import asint.TinyASint.R_true;
import asint.TinyASint.Resta;
import asint.TinyASint.Suma;
import asint.TinyASint.Tipo_Array;
import asint.TinyASint.Tipo_Id;
import asint.TinyASint.Tipo_Puntero;
import asint.TinyASint.Tipo_Reg;
import asint.TinyASint.Tipo_String;
import asint.TinyASint.Tipo_bool;
import asint.TinyASint.Tipo_int;
import asint.TinyASint.Tipo_real;
import tables.Asig_esp_data;
import tables.Vinculation_data;

public class Asigna_espacio extends ProcesamientoPorDefecto{
	
	private int dir; //direccion
	private int desp;//desplazamaiento
	private Asig_esp_data a_data;
	private Vinculation_data v_data;
	
	public Asigna_espacio(Vinculation_data v_data, Asig_esp_data a_data) {
		this.dir =0;
		this.desp=0;
		this.v_data = v_data;
		this.a_data = a_data;
	
	}
	public void procesa(Prog_con_decs prog) {
		prog.ldecs().procesa(this);
		prog.linst().procesa(this);
	}

	public void procesa(Prog_sin_decs prog) {
		prog.linst().procesa(this);
	}

	public void procesa(LDecs_una lDecs_una) {
		lDecs_una.dec().procesa(this);
	}

	public void procesa(LDecs_muchas lDecs_muchas) {
		lDecs_muchas.ldecs().procesa(this);
		lDecs_muchas.dec().procesa(this);

	}

	public void procesa(Dec_Var dec) {
		this.a_data.direcciones.put(dec,dir);
		dec.tipo().procesa(this);
		dir += this.a_data.tamaños.get(dec.tipo());

	}

	public void procesa(Dec_Type dec) {
		this.a_data.direcciones.put(dec,dir);
		dec.tipo().procesa(this);
		

	}

	public void procesa(Dec_Proc_con_params dec) {
		this.a_data.direcciones.put(dec,dir);
		dec.lparams().procesa(this);
		dec.bloque().procesa(this);

	}

	public void procesa(Dec_Proc_sin_params dec) {
		this.a_data.direcciones.put(dec,dir);

		dec.bloque().procesa(this);

	}

	public void procesa(LParams_uno l_params_uno) {
		l_params_uno.param().procesa(this);
	}

	public void procesa(LParams_muchos l_params_muchos) {
		l_params_muchos.lparams().procesa(this);
		
		l_params_muchos.param().procesa(this);
	}

	public void procesa(Param_amp param) {
		this.a_data.direcciones.put(param,dir);

		param.tipo().procesa(this);
		dir += this.a_data.tamaños.get(param.tipo());

	}

	public void procesa(Param_sin_amp param) {
		this.a_data.direcciones.put(param,dir);

		param.tipo().procesa(this);
		dir += this.a_data.tamaños.get(param.tipo());

		
	}

	public void procesa(Tipo_int tipo_int) {
		this.a_data.tamaños.put(tipo_int, 1);
	}

	public void procesa(Tipo_real tipo_real) {
		this.a_data.tamaños.put(tipo_real, 1);
	}

	public void procesa(Tipo_bool tipo_bool) {
		this.a_data.tamaños.put(tipo_bool, 1);
	}

	public void procesa(Tipo_String tipo_s) {
		this.a_data.tamaños.put(tipo_s, 1);
	}



	public void procesa(Tipo_Puntero tipo_p) {
		this.a_data.tamaños.put(tipo_p, 1);

	}


	public void procesa(LCampos_muchos campos) {
		campos.campos().procesa(this);
		campos.campo().procesa(this);
	}

	public void procesa(LCampos_uno campos) {
		campos.campo().procesa(this);
	}

	public void procesa(Campo campo) {
		a_data.desplazamientos.put(campo,this.desp);
		campo.tipo().procesa(this);
		this.desp += a_data.tamaños.get(campo.tipo());
		
	}

	public void procesa(Lista_inst_una lista_inst_una) {
		lista_inst_una.inst().procesa(this);
	}

	public void procesa(Lista_inst_muchas lista_inst_muchas) {
		lista_inst_muchas.linst().procesa(this);
		lista_inst_muchas.inst().procesa(this);
	}

	public void procesa(Inst_asig inst) {
		inst.exp1().procesa(this);
		inst.exp2().procesa(this);
	}

	public void procesa(Inst_if_then inst) {
		
		inst.exp().procesa(this);
		
		inst.linst_aux().procesa(this);
		
	}

	public void procesa(Inst_if_then_else inst) {
		
		inst.exp().procesa(this);
		
		inst.linst_aux1().procesa(this);
		
		inst.linst_aux2().procesa(this);
		
	}

	public void procesa(LInst_aux_vacia linst) {
		
	}

	public void procesa(LInst_aux_llena linst) {
		linst.linst().procesa(this);
	}

	public void procesa(Inst_while inst) {
		
		inst.exp().procesa(this);
		
		inst.linst_aux().procesa(this);
		
	}

	public void procesa(Inst_read inst) {
		
		inst.exp().procesa(this);
	}

	public void procesa(Inst_write inst) {
	
		inst.exp().procesa(this);
	}

	public void procesa(Inst_nl inst) {
		
	}

	public void procesa(Inst_new inst) {
		
		inst.exp().procesa(this);
	}

	public void procesa(Inst_delete inst) {
		
		inst.exp().procesa(this);
	}

	public void procesa(Inst_call_con_params inst) {
		inst.real_params().procesa(this);
		
	}

	public void procesa(Inst_call_sin_params inst) {
		
	}

	public void procesa(LReal_Params_uno params) {
		params.exp().procesa(this);
	}

	public void procesa(LReal_Params_muchos params) {
		params.params().procesa(this);
		params.exp().procesa(this);
	}

	public void procesa(Inst_compuesta inst) {
		inst.bloque().procesa(this);
	}

	public void procesa(Bloque_lleno bloque) {
		
		bloque.prog().procesa(this);
		

	}

	public void procesa(Bloque_vacio bloque) {
		
	}


	public void procesa(Suma exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);


	}

	public void procesa(Resta exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);

	}

	public void procesa(Mul exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);

	}

	public void procesa(Div exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);

	}

	public void procesa(Mod exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);

	}

	public void procesa(Id exp) {
		
	}

	public void procesa(NumEnt exp) {
		a_data.tamaños.put(exp, 1);
	}
	public void procesa(NumReal exp) {
		a_data.tamaños.put(exp, 1);
	}
	public void procesa(Menos_unario menos_unario) {
		menos_unario.arg0().procesa(this);
	}

	public void procesa(Not not) {
		not.arg0().procesa(this);

	}

	public void procesa(Beq beq) {
		beq.arg0().procesa(this);
		beq.arg1().procesa(this);


	}

	public void procesa(Bne bne) {
		bne.arg0().procesa(this);
		bne.arg1().procesa(this);


	}

	public void procesa(Bge bge) {
		bge.arg0().procesa(this);
		bge.arg1().procesa(this);


	}

	public void procesa(Ble ble) {
		ble.arg0().procesa(this);
		ble.arg1().procesa(this);


	}

	public void procesa(Blt blt) {
		blt.arg0().procesa(this);
		blt.arg1().procesa(this);


	}

	public void procesa(Bgt bgt) {
		bgt.arg0().procesa(this);
		bgt.arg1().procesa(this);


	}

	public void procesa(And and) {
		and.arg0().procesa(this);
		and.arg1().procesa(this);


	}

	public void procesa(Or or) {
		or.arg0().procesa(this);
		or.arg1().procesa(this);

	}

	public void procesa(R_true r_true) {
		

	}

	public void procesa(R_false r_false) {
	}

	public void procesa(Punto punto) {
		punto.exp().procesa(this);
	}

	public void procesa(Flecha flecha) {
		flecha.exp().procesa(this);
	}

	public void procesa(Indireccion ind) {
		ind.exp().procesa(this);

	}

	public void procesa(R_null n) {
	}

	public void procesa(R_str s) {
		
	}

	public void procesa(Index ind) {
		ind.exp1().procesa(this);
		ind.exp2().procesa(this);
		
	}
	
	
	
	
	
	
	
	
	public void procesa(Tipo_Reg tipo_r) {
		int aux = this.desp;
		this.desp = 0;
		tipo_r.lcampos().procesa(this);
		a_data.tamaños.put(tipo_r, this.desp);
		this.desp = aux;
	}

	
	public void procesa(Tipo_Id tipo_id) {//----------------------------------------------------------------------
		
		Object vinculo = v_data.vinculos.get(tipo_id);
		if(vinculo instanceof Dec_Var) {
			Dec_Var dec = (Dec_Var)vinculo;
			dec.tipo().procesa(this);
			int tam = a_data.tamaños.get(dec.tipo());
			this.a_data.tamaños.put(tipo_id, tam);
		}
		
		if(vinculo instanceof Dec_Type) {
			Dec_Type dec = (Dec_Type)vinculo;
			dec.tipo().procesa(this);
			int tam = a_data.tamaños.get(dec.tipo());
			this.a_data.tamaños.put(tipo_id, tam);
		}
		
	}
	public void procesa(Tipo_Array tipo_a) {
		tipo_a.tipo().procesa(this);
		int n_elems = Integer.parseInt(tipo_a.id().toString());
		int tam_tb = a_data.tamaños.get(tipo_a.tipo());
		this.a_data.tamaños.put(tipo_a,tam_tb * n_elems);

	}
}
