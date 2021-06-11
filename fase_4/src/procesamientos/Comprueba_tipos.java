package procesamientos;

import java.util.ArrayList;

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
import asint.TinyASint.Exp;
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
import asint.TinyASint.LCampos;
import asint.TinyASint.LCampos_muchos;
import asint.TinyASint.LCampos_uno;
import asint.TinyASint.LDecs_muchas;
import asint.TinyASint.LDecs_una;
import asint.TinyASint.LInst_aux_llena;
import asint.TinyASint.LInst_aux_vacia;
import asint.TinyASint.LParams;
import asint.TinyASint.LParams_muchos;
import asint.TinyASint.LParams_uno;
import asint.TinyASint.LReal_Params_muchos;
import asint.TinyASint.LReal_Params_uno;
import asint.TinyASint.LReal_params;
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
import asint.TinyASint.Tipo;
import asint.TinyASint.Tipo_Array;
import asint.TinyASint.Tipo_Id;
import asint.TinyASint.Tipo_Puntero;
import asint.TinyASint.Tipo_Reg;
import asint.TinyASint.Tipo_String;
import asint.TinyASint.Tipo_bool;
import asint.TinyASint.Tipo_int;
import asint.TinyASint.Tipo_real;
import tables.Comp_tipos_data;
import tables.U;
import tables.Vinculation_data;

public class Comprueba_tipos extends ProcesamientoPorDefecto {

	private Vinculation_data v_data;
	private Comp_tipos_data c_data;

	public Comprueba_tipos(Vinculation_data v_data, Comp_tipos_data c_data) {
		this.v_data = v_data;
		this.c_data = c_data;
	}


	public void procesa(Prog_con_decs prog) {
		prog.ldecs().procesa(this);
		prog.linst().procesa(this);
		if (tiposCorrectos(prog.ldecs(), prog.linst())) {
			c_data.tipos.put(prog, c_data.ok);
		} else
			c_data.tipos.put(prog, c_data.error);
	}

	public void procesa(Prog_sin_decs prog) {
		prog.linst().procesa(this);
		c_data.tipos.put(prog, c_data.tipos.get(prog.linst()));
	}

	public void procesa(LDecs_una lDecs_una) {
		lDecs_una.dec().procesa(this);
		c_data.tipos.put(lDecs_una, c_data.tipos.get(lDecs_una.dec()));
	}

	public void procesa(LDecs_muchas lDecs_muchas) {
		lDecs_muchas.ldecs().procesa(this);
		lDecs_muchas.dec().procesa(this);
		if (tiposCorrectos(lDecs_muchas.ldecs(), lDecs_muchas.dec())) {
			c_data.tipos.put(lDecs_muchas, c_data.ok);
		} else
			c_data.tipos.put(lDecs_muchas, c_data.error);

	}

	public void procesa(Dec_Var dec) {
		dec.tipo().procesa(this);
		c_data.tipos.put(dec, c_data.tipos.get(dec.tipo()));

	}

	public void procesa(Dec_Type dec) {
		dec.tipo().procesa(this);
		c_data.tipos.put(dec, c_data.tipos.get(dec.tipo()));

	}

	public void procesa(Dec_Proc_con_params dec) {
		dec.lparams().procesa(this);
		dec.bloque().procesa(this);
		if (tiposCorrectos(dec.lparams(), dec.bloque())) {
			c_data.tipos.put(dec, c_data.ok);
		} else
			c_data.tipos.put(dec, c_data.error);
	}

	public void procesa(Dec_Proc_sin_params dec) {
		dec.bloque().procesa(this);
		c_data.tipos.put(dec, c_data.tipos.get(dec.bloque()));
	}

	public void procesa(LParams_uno l_params_uno) {
		l_params_uno.param().procesa(this);
		c_data.tipos.put(l_params_uno, c_data.tipos.get(l_params_uno.param()));
	}

	public void procesa(LParams_muchos l_params_muchos) {
		l_params_muchos.lparams().procesa(this);
		l_params_muchos.param().procesa(this);
		if (tiposCorrectos(l_params_muchos.lparams(), l_params_muchos.param())) {
			c_data.tipos.put(l_params_muchos, c_data.ok);
		} else
			c_data.tipos.put(l_params_muchos, c_data.error);
	}

	public void procesa(Param_amp param) {
		param.tipo().procesa(this);
		c_data.tipos.put(param, c_data.tipos.get(param.tipo()));

	}

	public void procesa(Param_sin_amp param) {
		param.tipo().procesa(this);
		c_data.tipos.put(param, c_data.tipos.get(param.tipo()));

	}

	public void procesa(Tipo_int tipo_int) {
		c_data.tipos.put(tipo_int, c_data.t_int);
	}

	public void procesa(Tipo_real tipo_real) {
		c_data.tipos.put(tipo_real, c_data.t_real);
	}

	public void procesa(Tipo_bool tipo_bool) {
		c_data.tipos.put(tipo_bool, c_data.t_bool);
	}

	public void procesa(Tipo_String tipo_s) {
		c_data.tipos.put(tipo_s, c_data.t_string);
	}

	public void procesa(Tipo_Puntero tipo_p) {
		tipo_p.tipo().procesa(this);
		c_data.tipos.put(tipo_p, c_data.tipos.get(tipo_p.tipo()));

	}

	public void procesa(LCampos_muchos campos) {
		campos.campos().procesa(this);
		campos.campo().procesa(this);
		if (tiposCorrectos(campos.campos(), campos.campo())) {
			c_data.tipos.put(campos, c_data.ok);
		} else
			c_data.tipos.put(campos, c_data.error);
	}

	public void procesa(LCampos_uno campos) {
		campos.campo().procesa(this);
		c_data.tipos.put(campos, c_data.tipos.get(campos.campo()));

	}
	
	public void procesa(Campo campo) {
		campo.tipo().procesa(this);
		Tipo tipo0 = c_data.tipos.get(campo.tipo());
		if(!v_data.ref(tipo0).equals(c_data.error))
			c_data.tipos.put(campo,c_data.ok);
		else
			c_data.tipos.put(campo, c_data.error);
	}


	public void procesa(Inst_compuesta inst) {
		inst.bloque().procesa(this);
		c_data.tipos.put(inst, c_data.tipos.get(inst.bloque()));

	}

	public void procesa(Lista_inst_una lista_inst_una) {
		lista_inst_una.inst().procesa(this);
		c_data.tipos.put(lista_inst_una, c_data.tipos.get(lista_inst_una.inst()));

	}

	public void procesa(Lista_inst_muchas lista_inst_muchas) {
		lista_inst_muchas.linst().procesa(this);
		lista_inst_muchas.inst().procesa(this);
		if (tiposCorrectos(lista_inst_muchas.linst(), lista_inst_muchas.inst())) {
			c_data.tipos.put(lista_inst_muchas, c_data.ok);
		} else
			c_data.tipos.put(lista_inst_muchas, c_data.error);
	}

	public void procesa(Inst_asig inst) {
		inst.exp1().procesa(this);
		inst.exp2().procesa(this);
		if (asignacionCompatible(inst.exp1(), inst.exp2()))
			if (esDesignador(inst.exp1()))
				c_data.tipos.put(inst, c_data.ok);
			else
				c_data.tipos.put(inst, c_data.error);
		else
			c_data.tipos.put(inst, c_data.error);
	}

	public void procesa(Inst_if_then inst) {

		inst.exp().procesa(this);
		inst.linst_aux().procesa(this);
		Tipo type = c_data.tipos.get(inst.exp());
		if (v_data.ref(type) instanceof Tipo_bool && c_data.tipos.get(inst.linst_aux()) == c_data.ok) {
			c_data.tipos.put(inst, c_data.ok);
		} else
			c_data.tipos.put(inst, c_data.error);

	}

	public void procesa(Inst_if_then_else inst) {

		inst.exp().procesa(this);

		inst.linst_aux1().procesa(this);

		inst.linst_aux2().procesa(this);
		Tipo type = c_data.tipos.get(inst.exp());
		if (v_data.ref(type) instanceof Tipo_bool && tiposCorrectos(inst.linst_aux1(), inst.linst_aux2())) {
			c_data.tipos.put(inst, c_data.ok);
		} else
			c_data.tipos.put(inst, c_data.error);

	}

	
	public void procesa(LInst_aux_llena linst) {
		linst.linst().procesa(this);
		c_data.tipos.put(linst, c_data.tipos.get(linst.linst()));

	}

	public void procesa(Inst_while inst) {

		inst.exp().procesa(this);

		inst.linst_aux().procesa(this);
		Tipo type = c_data.tipos.get(inst.exp());
		if (v_data.ref(type) instanceof Tipo_bool && c_data.tipos.get(inst.linst_aux()) == c_data.ok) {
			c_data.tipos.put(inst, c_data.ok);
		} else
			c_data.tipos.put(inst, c_data.error);

	}

	public void procesa(Inst_read inst) {

		inst.exp().procesa(this);
		Tipo type = v_data.ref(c_data.tipos.get(inst.exp()));
		if ((type instanceof Tipo_int || type instanceof Tipo_String || type instanceof Tipo_real)
				&& esDesignador(inst.exp())) {
			c_data.tipos.put(inst, c_data.ok);
		} else
			c_data.tipos.put(inst, c_data.error);
	}

	public void procesa(Inst_write inst) {
		inst.exp().procesa(this);
		Tipo type = v_data.ref(c_data.tipos.get(inst.exp()));
		if (type instanceof Tipo_int || type instanceof Tipo_String || type instanceof Tipo_real
				|| type instanceof Tipo_bool) {
			c_data.tipos.put(inst, c_data.ok);
		} else
			c_data.tipos.put(inst, c_data.error);
	}

	public void procesa(Inst_nl inst) {
		c_data.tipos.put(inst, c_data.ok);
	}

	public void procesa(Inst_new inst) {
		inst.exp().procesa(this);
		Tipo type = c_data.tipos.get(inst.exp());
		if (v_data.ref(type) instanceof Tipo_Puntero)
			c_data.tipos.put(inst, c_data.ok);
		else
			c_data.tipos.put(inst, c_data.error);
	}

	public void procesa(Inst_delete inst) {
		inst.exp().procesa(this);
		Tipo type = c_data.tipos.get(inst.exp());
		if (v_data.ref(type) instanceof Tipo_Puntero)
			c_data.tipos.put(inst, c_data.ok);
		else
			c_data.tipos.put(inst, c_data.error);
	}

	public void procesa(LReal_Params_uno params) {
		params.exp().procesa(this);
		c_data.tipos.put(params, c_data.tipos.get(params.exp()));
	}

	public void procesa(LReal_Params_muchos params) {
		params.params().procesa(this);
		params.exp().procesa(this);
		if (tiposCorrectos(params.params(), params.exp()))
			c_data.tipos.put(params, c_data.ok);
		else
			c_data.tipos.put(params, c_data.error);
	}

	public void procesa(Bloque_lleno bloque) {

		bloque.prog().procesa(this);
		c_data.tipos.put(bloque, c_data.tipos.get(bloque.prog()));

	}

	public void procesa(Suma exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		Tipo tipo0 = c_data.tipos.get(exp.arg0());
		Tipo tipo1 = c_data.tipos.get(exp.arg1());
		compruebaTiposAritmeticos(exp, tipo0, tipo1);

	}

	public void procesa(Resta exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		Tipo tipo0 = c_data.tipos.get(exp.arg0());
		Tipo tipo1 = c_data.tipos.get(exp.arg1());
		compruebaTiposAritmeticos(exp, tipo0, tipo1);
	}

	public void procesa(Mul exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		Tipo tipo0 = c_data.tipos.get(exp.arg0());
		Tipo tipo1 = c_data.tipos.get(exp.arg1());
		compruebaTiposAritmeticos(exp, tipo0, tipo1);

	}

	public void procesa(Div exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		Tipo tipo0 = c_data.tipos.get(exp.arg0());
		Tipo tipo1 = c_data.tipos.get(exp.arg1());
		compruebaTiposAritmeticos(exp, tipo0, tipo1);

	}

	public void procesa(Mod exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		Tipo tipo0 = c_data.tipos.get(exp.arg0());
		Tipo tipo1 = c_data.tipos.get(exp.arg1());
		if (v_data.ref(tipo0) instanceof Tipo_int && v_data.ref(tipo1) instanceof Tipo_int) {
			c_data.tipos.put(exp, c_data.t_int);
		} else
			c_data.tipos.put(exp, c_data.error);

	}

	

	public void procesa(Menos_unario menos_unario) {
		menos_unario.arg0().procesa(this);
		Tipo tipo0 = c_data.tipos.get(menos_unario.arg0());
		if (v_data.ref(tipo0) instanceof Tipo_int)
			c_data.tipos.put(menos_unario, c_data.t_int);
		else if (v_data.ref(tipo0) instanceof Tipo_real)
			c_data.tipos.put(menos_unario, c_data.t_real);
		else
			c_data.tipos.put(menos_unario, c_data.error);

	}

	public void procesa(Not not) {
		not.arg0().procesa(this);
		Tipo tipo0 = c_data.tipos.get(not.arg0());
		if (v_data.ref(tipo0) instanceof Tipo_bool)
			c_data.tipos.put(not, c_data.t_bool);
		else
			c_data.tipos.put(not, c_data.error);

	}

	public void procesa(Beq beq) {
		beq.arg0().procesa(this);
		beq.arg1().procesa(this);
		Tipo tipo0 = c_data.tipos.get(beq.arg0());
		Tipo tipo1 = c_data.tipos.get(beq.arg1());
		compruebaTiposRelacionales(beq, tipo0, tipo1);

	}

	public void procesa(Bne bne) {
		bne.arg0().procesa(this);
		bne.arg1().procesa(this);
		Tipo tipo0 = c_data.tipos.get(bne.arg0());
		Tipo tipo1 = c_data.tipos.get(bne.arg1());
		compruebaTiposRelacionales(bne, tipo0, tipo1);

	}

	public void procesa(Bge bge) {
		bge.arg0().procesa(this);
		bge.arg1().procesa(this);
		Tipo tipo0 = c_data.tipos.get(bge.arg0());
		Tipo tipo1 = c_data.tipos.get(bge.arg1());
		compruebaTiposRelacionales(bge, tipo0, tipo1);

	}

	public void procesa(Ble ble) {
		ble.arg0().procesa(this);
		ble.arg1().procesa(this);
		Tipo tipo0 = c_data.tipos.get(ble.arg0());
		Tipo tipo1 = c_data.tipos.get(ble.arg1());
		compruebaTiposRelacionales(ble, tipo0, tipo1);

	}

	public void procesa(Blt blt) {
		blt.arg0().procesa(this);
		blt.arg1().procesa(this);
		Tipo tipo0 = c_data.tipos.get(blt.arg0());
		Tipo tipo1 = c_data.tipos.get(blt.arg1());
		compruebaTiposRelacionales(blt, tipo0, tipo1);

	}

	public void procesa(Bgt bgt) {
		bgt.arg0().procesa(this);
		bgt.arg1().procesa(this);
		Tipo tipo0 = c_data.tipos.get(bgt.arg0());
		Tipo tipo1 = c_data.tipos.get(bgt.arg1());
		compruebaTiposRelacionales(bgt, tipo0, tipo1);

	}

	public void procesa(And and) {
		and.arg0().procesa(this);
		and.arg1().procesa(this);
		Tipo exp0 = c_data.tipos.get(and.arg0());
		Tipo exp1 = c_data.tipos.get(and.arg1());
		compruebaTiposLogicos(and, exp0, exp1);

	}

	public void procesa(Or or) {
		or.arg0().procesa(this);
		or.arg1().procesa(this);
		Tipo exp0 = c_data.tipos.get(or.arg0());
		Tipo exp1 = c_data.tipos.get(or.arg1());
		compruebaTiposLogicos(or, exp0, exp1);

	}

	public void procesa(R_true r_true) {
		c_data.tipos.put(r_true, c_data.t_bool);

	}

	public void procesa(R_false r_false) {
		c_data.tipos.put(r_false, c_data.t_bool);
	}

	public void procesa(R_null n) {
		c_data.tipos.put(n, c_data.t_null);
	}

	public void procesa(R_str s) {
		c_data.tipos.put(s, c_data.t_string);
	}
	public void procesa(NumEnt num) {
		c_data.tipos.put(num, c_data.t_int);
	}
	public void procesa(NumReal num) {
		c_data.tipos.put(num, c_data.t_real);

	}

	private void compruebaTiposAritmeticos(Exp exp, Tipo tipo0, Tipo tipo1) {
		Tipo type0 = v_data.ref(tipo0);
		Tipo type1 = v_data.ref(tipo1);
		if (type0 instanceof Tipo_int && type1 instanceof Tipo_int) {
			c_data.tipos.put(exp, c_data.t_int);
		} else if ((type0 instanceof Tipo_real && (type1 instanceof Tipo_real || type1 instanceof Tipo_int))
				|| (type1 instanceof Tipo_real && (type0 instanceof Tipo_real || type0 instanceof Tipo_int))) {
			c_data.tipos.put(exp, c_data.t_real);
		} else
			c_data.tipos.put(exp, c_data.error);

	}

	private void compruebaTiposLogicos(Exp exp, Tipo tipo0, Tipo tipo1) {
		if (v_data.ref(tipo0) instanceof Tipo_bool && v_data.ref(tipo1) instanceof Tipo_bool) {
			c_data.tipos.put(exp, c_data.t_bool);
		} else
			c_data.tipos.put(exp, c_data.error);
	}
	
	private boolean tiposCorrectos(Object o1, Object o2) {
		if (c_data.tipos.get(o1) != c_data.error && c_data.tipos.get(o2) != c_data.error)
			return true;
		return false;
	}


	// -----------------------------------------------------------------------------------

	
	public void procesa(Id exp) {
		Object vinculo = v_data.vinculos.get(exp);
		if(vinculo instanceof Dec_Var) {
			Dec_Var dec = (Dec_Var)vinculo;
			c_data.tipos.put(exp, v_data.ref(dec.tipo()));
		}
		else
			c_data.tipos.put(exp, c_data.error);
	}


	public void procesa(Bloque_vacio bloque) {

	}

	public void procesa(Index ind) {
		ind.exp1().procesa(this);
		ind.exp2().procesa(this);
		Tipo tipo1 = c_data.tipos.get(ind.exp1());
		Tipo tipo2 = c_data.tipos.get(ind.exp2());
		if(v_data.ref(tipo2) instanceof Tipo_int && v_data.ref(tipo1) instanceof Tipo_Array) {
			Tipo_Array t =(Tipo_Array)v_data.ref(tipo1);
			c_data.tipos.put(ind, t.tipo());//////////////////////////////////////////////////////////////////////////////
		}
		else
			c_data.tipos.put(ind, c_data.error);
			

	}

	public void procesa(Flecha flecha) {
		flecha.exp().procesa(this);
		Tipo tipo_exp = c_data.tipos.get(flecha.exp());
		Tipo tipo = v_data.ref(tipo_exp);
		if(tipo instanceof Tipo_Puntero) {
			Tipo tipo_puntero = v_data.refNodo(tipo);
			if(tipo_puntero instanceof Tipo_Reg) {
				Tipo_Reg tipo_reg = (Tipo_Reg)tipo_puntero;
				Campo c = buscaCampo(flecha.id().toString(), tipo_reg.lcampos());
				if(c!=null) {
					Tipo tipo_campo = v_data.ref(c.tipo());
					c_data.tipos.put(flecha, tipo_campo);
				}
				else
					c_data.tipos.put(flecha, c_data.error);
			}
		}
		else
			c_data.tipos.put(flecha, c_data.error);
			
	}
	private Campo buscaCampo(String string, LCampos campos) {
		if (campos instanceof LCampos_muchos) {
			LCampos_muchos lcampos = (LCampos_muchos) campos;
			
			Campo c = buscaCampo(string, lcampos.campo());
			if(c !=null) {
				return c; 
			}
			return buscaCampo(string, lcampos.campos());
		} else if (campos instanceof LCampos_uno) {
			LCampos_uno lcampos = (LCampos_uno) campos;
			return buscaCampo(string, lcampos);
		}

		return null;

	}

	private Campo buscaCampo(String string, LCampos_uno campos) {
		return buscaCampo(string, campos.campo());
	}

	private Campo buscaCampo(String string, Campo campo) {

		if (campo.id().toString().equals(string))
			return campo;
		return null;
		
	}
	
	public void procesa(Punto punto) {
		punto.exp().procesa(this);
		Tipo tipo_exp = c_data.tipos.get(punto.exp());
		Tipo tipo = v_data.ref(tipo_exp);
		if(tipo instanceof Tipo_Reg) {
			Tipo_Reg tipo_reg = (Tipo_Reg)tipo;
			Campo c = buscaCampo(punto.id().toString(), tipo_reg.lcampos());
			if(c!=null) {
				Tipo tipo_campo = v_data.ref(c.tipo());
				c_data.tipos.put(punto, tipo_campo);
			}
			else
				c_data.tipos.put(punto, c_data.error);
		}
		
	}

	public void procesa(Indireccion ind) {
		ind.exp().procesa(this);
		Tipo tipo_exp = c_data.tipos.get(ind.exp());
		Tipo tipo = v_data.ref(tipo_exp);
		if (tipo  instanceof Tipo_Puntero) {
			Tipo_Puntero tipo_puntero = (Tipo_Puntero)tipo;
			Tipo tipo_base = v_data.refNodo(tipo_puntero.tipo());
			c_data.tipos.put(ind, tipo_base);
		}
		else
			c_data.tipos.put(ind, c_data.error);

	}

	
	public void procesa(Tipo_Reg tipo_r) {
		tipo_r.lcampos().procesa(this);
		if(camposDuplicados(tipo_r.lcampos())) {
			c_data.tipos.put(tipo_r, c_data.error);
		}
		if(c_data.tipos.get(tipo_r.lcampos())!= c_data.error) {
			c_data.tipos.put(tipo_r, tipo_r);////////////////////////////////////////////////////////////////////////////
		}
		else
			c_data.tipos.put(tipo_r, c_data.error);
	}




	public void procesa(Tipo_Id tipo_id) {// ----------------------------------------------------------------------
		Tipo tipo = v_data.ref(tipo_id);
		if(tipo != c_data.error)
			c_data.tipos.put(tipo_id, tipo_id);//////////////////////////////
		else
			c_data.tipos.put(tipo_id, c_data.error);
		
	}

	public void procesa(Tipo_Array tipo_a) {
		tipo_a.tipo().procesa(this);
		int tam_array = Integer.parseInt(tipo_a.id().toString());
		if(c_data.tipos.get(tipo_a.tipo()) != c_data.error && tam_array > 0)
			c_data.tipos.put(tipo_a, tipo_a);
		else
			c_data.tipos.put(tipo_a, c_data.error);
		
	}

	public void procesa(Inst_call_con_params inst) {
		inst.real_params().procesa(this);
		Object vinculo = v_data.vinculos.get(inst);
		if(vinculo instanceof Dec_Proc_con_params) {
			Dec_Proc_con_params dec_proc = (Dec_Proc_con_params)vinculo;
			if(parametrosCompatibles(inst.real_params(),dec_proc.lparams()))
				c_data.tipos.put(inst, c_data.ok);
			else
				c_data.tipos.put(inst, c_data.error);
		}
		else
			c_data.tipos.put(inst, c_data.error);

	}
	


	public void procesa(LInst_aux_vacia linst) {

	}


	public void procesa(Inst_call_sin_params inst) {
		Object vinculo = v_data.vinculos.get(inst);
		if(vinculo instanceof Dec_Proc_sin_params) {
			c_data.tipos.put(inst, c_data.ok);			
		}
		else
			c_data.tipos.put(inst, c_data.error);
	}
	

	private void compruebaTiposRelacionales(Exp exp, Tipo tipo0, Tipo tipo1) {
		Tipo type0 = v_data.ref(tipo0);
		Tipo type1 = v_data.ref(tipo1);

		if ((type0 instanceof Tipo_int || type0 instanceof Tipo_real)
				&& (type1 instanceof Tipo_int || type1 instanceof Tipo_real)) {
			c_data.tipos.put(exp, c_data.t_bool);
		}
		if (type0 instanceof Tipo_bool && type1 instanceof Tipo_bool) {
			c_data.tipos.put(exp, c_data.t_bool);
		}
		if (type0 instanceof Tipo_String && type1 instanceof Tipo_String) {
			c_data.tipos.put(exp, c_data.t_bool);
		}
		if (exp instanceof Beq || exp instanceof Bne) {// ??????????????????????????????????????????????????????????
			if ((type0 instanceof Tipo_Puntero || type0.equals(c_data.t_null))
					&& (type1 instanceof Tipo_Puntero || type1.equals(c_data.t_null))) {
				c_data.tipos.put(exp, c_data.t_bool);
			} else
				c_data.tipos.put(exp, c_data.error);

		} else
			c_data.tipos.put(exp, c_data.error);

	}

	private boolean parametrosCompatibles(LReal_params real_params, LParams lparams) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean asignacionCompatible(Exp exp1, Exp exp2) {
		
	}

	private boolean asignacionCompatible_aux(Exp exp1, Exp exp2,ArrayList<U> u) {
	
		Tipo tipo_exp1 = c_data.tipos.get(exp1);
		Tipo tipo_exp2 = c_data.tipos.get(exp1);
		Tipo ref_exp1 = v_data.ref(tipo_exp1);
		Tipo ref_exp2 = v_data.ref(tipo_exp2);
		if(ref_exp1 instanceof Tipo_int && ref_exp2 instanceof Tipo_int)
			return true;
		return true;
	}
	

	private boolean esDesignador(Exp exp) {
		if(exp instanceof Id || exp instanceof Indireccion ||  exp instanceof Index
				||  exp instanceof Punto ||  exp instanceof Flecha)
			return true;	
		return false;
	}
	private boolean camposDuplicados(LCampos lcampos) {
		// TODO Auto-generated method stub
		return false;
	}
}
