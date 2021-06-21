package procesamientos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import asint.TinyASint.Param;
import asint.TinyASint.Param_amp;
import asint.TinyASint.Param_sin_amp;
import asint.TinyASint.Prog_con_decs;
import asint.TinyASint.Prog_sin_decs;
import asint.TinyASint.Punto;
import asint.TinyASint.R_false;
import asint.TinyASint.R_null;
import asint.TinyASint.Tipo_null;
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
import tables.Vinculation_data;

public class Comprueba_tipos extends ProcesamientoPorDefecto {

	private Vinculation_data v_data;
	private Comp_tipos_data c_data;
	private Map<Object, Object> tabla_contexto;

	public Comprueba_tipos(Vinculation_data v_data, Comp_tipos_data c_data) {
		this.v_data = v_data;
		this.c_data = c_data;
		this.tabla_contexto = new HashMap<Object, Object>();
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
		c_data.tipos.put(dec, dec.tipo());//TODO

	}

	public void procesa(Dec_Type dec) {
		dec.tipo().procesa(this);
		c_data.tipos.put(dec,dec.tipo());//TODO

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



	//TODO
	public void procesa(Tipo_int tipo_int) {
		c_data.tipos.put(tipo_int, c_data.t_int);//////////////////////////////////tipo_int o t_int como primer parametro??
		
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
	public void procesa(Tipo_Reg tipo_r) {
		tipo_r.lcampos().procesa(this);
		if(camposDuplicados(tipo_r.lcampos(),new ArrayList<String>())) {
			c_data.tipos.put(tipo_r, c_data.error);
		}
		else if(c_data.tipos.get(tipo_r.lcampos())!= c_data.error) {
			c_data.tipos.put(tipo_r, tipo_r);
		}
		else
			c_data.tipos.put(tipo_r, c_data.error);
	}


	public void procesa(Tipo_Id tipo_id) {// --------------TODO--------------------------------------------------------
		Tipo tipo = v_data.ref(tipo_id);
		if(tipo != c_data.error)
			c_data.tipos.put(tipo_id,tipo);
		else
			c_data.tipos.put(tipo_id, c_data.error);
		
	}
	public void procesa(Tipo_null n) {
		c_data.tipos.put(n, n);
	}

	public void procesa(Tipo_Array tipo_a) {
		tipo_a.tipo().procesa(this);
		int tam_array = Integer.parseInt(tipo_a.id().toString());
		if(c_data.tipos.get(tipo_a.tipo()) != c_data.error && tam_array > 0)
			c_data.tipos.put(tipo_a, tipo_a);
		else
			c_data.tipos.put(tipo_a, c_data.error);
		
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
	
	/////////////////////TODO
	public void procesa(Campo campo) {
		campo.tipo().procesa(this);
		c_data.tipos.put(campo,c_data.tipos.get(campo.tipo()));
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

	public void procesa(Inst_compuesta inst) {
		inst.bloque().procesa(this);
		c_data.tipos.put(inst, c_data.tipos.get(inst.bloque()));

	}
	
	public void procesa(Inst_asig inst) {
		inst.exp1().procesa(this);
		inst.exp2().procesa(this);
		if (asignacionCompatible(inst.exp1(), inst.exp2())) {
			if (c_data.esDesignador(inst.exp1()))
				c_data.tipos.put(inst, c_data.ok);
			else {
				System.out.println(inst.exp1() + " no es un designador");
				c_data.tipos.put(inst, c_data.error);
			}
				
		}	
		else {
			c_data.tipos.put(inst, c_data.error);
			System.out.println("Los tipos de las expresiones" + inst.exp1() +" y " + inst.exp2() + " no son compatibles");
		}
			
	}

	public void procesa(Inst_if_then inst) {

		inst.exp().procesa(this);
		inst.linst_aux().procesa(this);
		Tipo type = (Tipo)c_data.tipos.get(inst.exp());
		if (v_data.ref(type) instanceof Tipo_bool && c_data.tipos.get(inst.linst_aux()) == c_data.ok) {
			c_data.tipos.put(inst, c_data.ok);
		} else
			c_data.tipos.put(inst, c_data.error);

	}

	public void procesa(Inst_if_then_else inst) {

		inst.exp().procesa(this);
		inst.linst_aux1().procesa(this);
		inst.linst_aux2().procesa(this);
		Tipo type = (Tipo) c_data.tipos.get(inst.exp());
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
		Tipo type = (Tipo) c_data.tipos.get(inst.exp());
		if (v_data.ref(type) instanceof Tipo_bool && c_data.tipos.get(inst.linst_aux()) == c_data.ok) {
			c_data.tipos.put(inst, c_data.ok);
		} else
			c_data.tipos.put(inst, c_data.error);

	}

	public void procesa(Inst_read inst) {
		inst.exp().procesa(this);
		Tipo type = v_data.ref((Tipo)c_data.tipos.get(inst.exp()));
		if ((type instanceof Tipo_int || type instanceof Tipo_String || type instanceof Tipo_real)
				&& c_data.esDesignador(inst.exp())) {
			c_data.tipos.put(inst, c_data.ok);
		} else
			c_data.tipos.put(inst, c_data.error);
	}

	public void procesa(Inst_write inst) {
		inst.exp().procesa(this);
		Tipo type = v_data.ref((Tipo)c_data.tipos.get(inst.exp()));
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
		Tipo type =(Tipo) c_data.tipos.get(inst.exp());
		if (v_data.ref(type) instanceof Tipo_Puntero)
			c_data.tipos.put(inst, c_data.ok);
		else
			c_data.tipos.put(inst, c_data.error);
	}

	public void procesa(Inst_delete inst) {
		inst.exp().procesa(this);
		Tipo type = (Tipo)c_data.tipos.get(inst.exp());
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
		compruebaTiposAritmeticos(exp,
				(Tipo)c_data.tipos.get(exp.arg0()),(Tipo)c_data.tipos.get(exp.arg1()));

	}

	public void procesa(Resta exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		compruebaTiposAritmeticos(exp,
				(Tipo)c_data.tipos.get(exp.arg0()),(Tipo)c_data.tipos.get(exp.arg1()));
	}

	public void procesa(Mul exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		compruebaTiposAritmeticos(exp,
				(Tipo)c_data.tipos.get(exp.arg0()),(Tipo)c_data.tipos.get(exp.arg1()));

	}

	public void procesa(Div exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		compruebaTiposAritmeticos(exp,
				(Tipo)c_data.tipos.get(exp.arg0()),(Tipo)c_data.tipos.get(exp.arg1()));

	}

	public void procesa(Mod exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
		Tipo tipo0 = (Tipo)c_data.tipos.get(exp.arg0());
		Tipo tipo1 = (Tipo)c_data.tipos.get(exp.arg1());
		if (v_data.ref(tipo0) instanceof Tipo_int && v_data.ref(tipo1) instanceof Tipo_int) {
			c_data.tipos.put(exp, c_data.t_int);
		} else
			c_data.tipos.put(exp, c_data.error);

	}

	

	public void procesa(Menos_unario menos_unario) {
		menos_unario.arg0().procesa(this);
		Tipo tipo0 = (Tipo)c_data.tipos.get(menos_unario.arg0());
		if (v_data.ref(tipo0) instanceof Tipo_int)
			c_data.tipos.put(menos_unario, c_data.t_int);
		else if (v_data.ref(tipo0) instanceof Tipo_real)
			c_data.tipos.put(menos_unario, c_data.t_real);
		else
			c_data.tipos.put(menos_unario, c_data.error);

	}

	public void procesa(Not not) {
		not.arg0().procesa(this);
		Tipo tipo0 =(Tipo) c_data.tipos.get(not.arg0());
		if (v_data.ref(tipo0) instanceof Tipo_bool)
			c_data.tipos.put(not, c_data.t_bool);
		else
			c_data.tipos.put(not, c_data.error);

	}

	public void procesa(Beq beq) {
		beq.arg0().procesa(this);
		beq.arg1().procesa(this);
		compruebaTiposRelacionales(beq,
				(Tipo)c_data.tipos.get(beq.arg0()),(Tipo)c_data.tipos.get(beq.arg1()));

	}

	public void procesa(Bne bne) {
		bne.arg0().procesa(this);
		bne.arg1().procesa(this);
		compruebaTiposRelacionales(bne,
				(Tipo)c_data.tipos.get(bne.arg0()),(Tipo)c_data.tipos.get(bne.arg1()));

	}

	public void procesa(Bge bge) {
		bge.arg0().procesa(this);
		bge.arg1().procesa(this);
		compruebaTiposRelacionales(bge,
				(Tipo)c_data.tipos.get(bge.arg0()),(Tipo)c_data.tipos.get(bge.arg1()));

	}

	public void procesa(Ble ble) {
		ble.arg0().procesa(this);
		ble.arg1().procesa(this);
		compruebaTiposRelacionales(ble,
				(Tipo)c_data.tipos.get(ble.arg0()),(Tipo)c_data.tipos.get(ble.arg1()));

	}

	public void procesa(Blt blt) {
		blt.arg0().procesa(this);
		blt.arg1().procesa(this);
		compruebaTiposRelacionales(blt,
				(Tipo)c_data.tipos.get(blt.arg0()),(Tipo)c_data.tipos.get(blt.arg1()));

	}

	public void procesa(Bgt bgt) {
		bgt.arg0().procesa(this);
		bgt.arg1().procesa(this);
		compruebaTiposRelacionales(bgt,
				(Tipo)c_data.tipos.get(bgt.arg0()),(Tipo)c_data.tipos.get(bgt.arg1()));

	}

	public void procesa(And and) {
		and.arg0().procesa(this);
		and.arg1().procesa(this);
		compruebaTiposLogicos(and,
				(Tipo)c_data.tipos.get(and.arg0()),(Tipo)c_data.tipos.get(and.arg1()));

	}

	public void procesa(Or or) {
		or.arg0().procesa(this);
		or.arg1().procesa(this);
		compruebaTiposLogicos(or,
				(Tipo)c_data.tipos.get(or.arg0()),(Tipo)c_data.tipos.get(or.arg1()));

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
	
	public void procesa(Id exp) {
		Object vinculo = v_data.vinculos.get(exp);
		if(vinculo instanceof Dec_Var) {
			Dec_Var dec = (Dec_Var)vinculo;
			c_data.tipos.put(exp,dec.tipo()); //TODO
		}
		else if (vinculo instanceof Tipo_Id) {
			Tipo_Id tipo_id = (Tipo_Id)vinculo;
			c_data.tipos.put(exp,tipo_id); //TODO
		}
		else
			c_data.tipos.put(exp, c_data.error);
	}


	public void procesa(Index ind) {
		ind.exp1().procesa(this);
		ind.exp2().procesa(this);
		Tipo tipo1 = (Tipo)c_data.tipos.get(ind.exp1());
		Tipo tipo2 = (Tipo)c_data.tipos.get(ind.exp2());
		if(v_data.ref(tipo2) instanceof Tipo_int && v_data.ref(tipo1) instanceof Tipo_Array) {
			Tipo_Array tipo_array = (Tipo_Array)v_data.ref(tipo1);
			c_data.tipos.put(ind, v_data.ref(tipo_array.tipo()));/////////////////TODO/////////////////////////////////////////////////////////////

		}
		else
			c_data.tipos.put(ind, c_data.error);
			

	}

	public void procesa(Flecha flecha) {
		flecha.exp().procesa(this);
		Tipo tipo_exp = (Tipo)c_data.tipos.get(flecha.exp());
		
		Tipo tipo = v_data.ref(tipo_exp);
		if(tipo instanceof Tipo_Puntero) {
			Tipo_Puntero t_puntero = (Tipo_Puntero)tipo;
			Tipo type = v_data.ref(t_puntero.tipo());
			if(type instanceof Tipo_Reg) {
				Tipo_Reg tipo_reg = (Tipo_Reg)type;
				Campo c = buscaCampo(flecha.id().toString(), tipo_reg.lcampos());
				if(c!=null) {
					c_data.tipos.put(flecha, c.tipo());
					System.out.println((Tipo)c_data.tipos.get(flecha));
				}
				else {
					c_data.tipos.put(flecha, c_data.error);
					System.out.println("Campo no encontrado en reg");
				}
			}
			else
				c_data.tipos.put(flecha, c_data.error);
		}
		else
			c_data.tipos.put(flecha, c_data.error);
			
	}
	

	
	public void procesa(Punto punto) {
		punto.exp().procesa(this);
		Tipo tipo_v = (Tipo)c_data.tipos.get(punto.exp());
		Tipo tipo = v_data.ref(tipo_v);
		if(tipo instanceof Tipo_Reg) {
			Tipo_Reg tipo_reg = (Tipo_Reg)tipo;
			Campo c = buscaCampo(punto.id().toString(), tipo_reg.lcampos());
			if(c!=null) {
				c_data.tipos.put(punto,c.tipo());
			}
			else
				c_data.tipos.put(punto, c_data.error);
		}
		else
			c_data.tipos.put(punto, c_data.error);
		
	}

	public void procesa(Indireccion ind) {
		ind.exp().procesa(this);
		Tipo tipo_exp = (Tipo)c_data.tipos.get(ind.exp());
		Tipo tipo = v_data.ref(tipo_exp);
		if (tipo  instanceof Tipo_Puntero) {
			Tipo_Puntero tipo_punt = (Tipo_Puntero)tipo;
			c_data.tipos.put(ind, tipo_punt.tipo());
		}
		else
			c_data.tipos.put(ind, c_data.error);

	}

	
	public void procesa(Inst_call_con_params inst) {
		inst.real_params().procesa(this);
		Object vinculo = v_data.vinculos.get(inst);
		if(vinculo instanceof Dec_Proc_con_params) {
			Dec_Proc_con_params dec_proc = (Dec_Proc_con_params)vinculo;
			if(cuentaRealParams(inst.real_params())== cuentaParamsFormales(dec_proc.lparams())) {
				if(parametrosCompatibles(inst.real_params(),dec_proc.lparams())) {
					c_data.tipos.put(inst, c_data.ok);
				}
				else {
					c_data.tipos.put(inst, c_data.error);
					System.out.println("Paramtros no compatibles con params de "+ vinculo);
				}
					
			}
			else {
				System.out.println("No tiene el mismo numero de parametros reales y formales");
				c_data.tipos.put(inst, c_data.error);
			}
			

		}
		else {
			c_data.tipos.put(inst, c_data.error);
			System.out.println(vinculo + " no es de tipo dec_proc_con_params");
		}
			

	}
	



	public void procesa(Inst_call_sin_params inst) {
		Object vinculo = v_data.vinculos.get(inst);
		if(vinculo instanceof Dec_Proc_sin_params) {
			c_data.tipos.put(inst, c_data.ok);			
		}
		else
			c_data.tipos.put(inst, c_data.error);
	}
	

	

	
	private boolean asignacionCompatible(Object exp1, Object exp2) {
		this.tabla_contexto = new HashMap<Object, Object>();
		return asignacionCompatible_aux(exp1, exp2);
	}

	private boolean asignacionCompatible_aux(Object exp1, Object exp2) {
		
		if(this.tabla_contexto.containsKey(exp1) && tabla_contexto.get(exp1) == exp2) {
			return true;
		}
		tabla_contexto.put(exp1, exp2);
		
		
		Tipo tipo1 = v_data.ref((Tipo)c_data.tipos.get(exp1));

		Tipo tipo2 = v_data.ref((Tipo)c_data.tipos.get(exp2));
		if((tipo1 instanceof Tipo_int && tipo2 instanceof Tipo_int)
				|| (tipo1 instanceof Tipo_real && (tipo2 instanceof Tipo_int || tipo2 instanceof Tipo_real))
				|| (tipo1 instanceof Tipo_bool && tipo2 instanceof Tipo_bool)
				|| (tipo1 instanceof Tipo_String && tipo2 instanceof Tipo_String)
				|| (tipo1 instanceof Tipo_Puntero && tipo2 instanceof Tipo_null)) {
			return true;
		}
		
		Tipo tipo_base_1 = null;
		Tipo tipo_base_2 = null;
		if(tipo1 instanceof Tipo_Array && tipo2 instanceof Tipo_Array) {
			Tipo_Array tipo_array1 = (Tipo_Array)tipo1;
			Tipo_Array tipo_array2 = (Tipo_Array)tipo2;
			tipo_base_1 = tipo_array1.tipo();
			tipo_base_2 = tipo_array2.tipo();
			if(mismaLongitud(tipo_array1,tipo_array2)){
				return asignacionCompatible_aux(tipo_base_1, tipo_base_2);
			}
			System.out.println("Arrays no compatibles,no tienen el mismo tamaño los arrays");
			return false;
		}
		
		else if(tipo1 instanceof Tipo_Reg && tipo2 instanceof Tipo_Reg) {
			Tipo_Reg tipo_reg1 = (Tipo_Reg)tipo1;
			Tipo_Reg tipo_reg2 = (Tipo_Reg)tipo2;
			if(mismoNumeroCampos(tipo_reg1,tipo_reg2)) {
				return camposCompatibles(tipo_reg1.lcampos(),tipo_reg2.lcampos());
			}
			System.out.println("Los tipos " + exp1 + " y " + exp2
					+ "no son compatibles,no tienen el mismo numero de campos los registros");
			return false;
		}
		else if(tipo1 instanceof Tipo_Puntero && tipo2 instanceof Tipo_null) {
			return true;
		}
		else if(tipo1 instanceof Tipo_Puntero && tipo2 instanceof Tipo_Puntero) {
			Tipo_Puntero puntero1 = (Tipo_Puntero)tipo1;
			Tipo_Puntero puntero2 = (Tipo_Puntero)tipo2;
			return asignacionCompatible_aux(puntero1.tipo(),puntero2.tipo());
		}
		else
			return false;
	}


	//---------------------------------------FUNCIONES AUXILIARES-----------------------------------------------
	

	private void compruebaTiposAritmeticos(Exp exp,Tipo t_arg0, Tipo t_arg1) {
		Tipo type0 = v_data.ref(t_arg0);
		Tipo type1 = v_data.ref(t_arg1);
		if (type0 instanceof Tipo_int && type1 instanceof Tipo_int) {
			c_data.tipos.put(exp, c_data.t_int);
		} else if ((type0 instanceof Tipo_real && (type1 instanceof Tipo_real || type1 instanceof Tipo_int))
				|| (type1 instanceof Tipo_real && (type0 instanceof Tipo_real || type0 instanceof Tipo_int))) {
			c_data.tipos.put(exp, c_data.t_real);
		} else
			c_data.tipos.put(exp, c_data.error);

	}

	private void compruebaTiposLogicos(Exp exp,Tipo t_arg0, Tipo t_arg1) {
		if (v_data.ref(t_arg0) instanceof Tipo_bool && v_data.ref(t_arg1) instanceof Tipo_bool) {
			c_data.tipos.put(exp, c_data.t_bool);
		} else
			c_data.tipos.put(exp, c_data.error);
	}
	
	private void compruebaTiposRelacionales(Exp exp,Tipo t_arg0, Tipo t_arg1) {
		Object type0 = v_data.ref(t_arg0);
		Object type1 = v_data.ref(t_arg1);
		Boolean ok = false;
		if ((type0 instanceof Tipo_int || type0 instanceof Tipo_real)
				&& (type1 instanceof Tipo_int || type1 instanceof Tipo_real)) {
			c_data.tipos.put(exp, c_data.t_bool);
			ok=true;
		}
		else if (type0 instanceof Tipo_bool && type1 instanceof Tipo_bool) {
			c_data.tipos.put(exp, c_data.t_bool);
			ok=true;

		}
		else if (type0 instanceof Tipo_String && type1 instanceof Tipo_String) {
			c_data.tipos.put(exp, c_data.t_bool);
			ok=true;

		}
		if (exp instanceof Beq || exp instanceof Bne) {
			if(type0 instanceof Tipo_Puntero && type1 instanceof Tipo_Puntero) {
				c_data.tipos.put(exp, c_data.t_bool);
				ok=true;
				
			}
			else if (t_arg0  instanceof Tipo_Puntero && t_arg1  instanceof Tipo_null ) {
				c_data.tipos.put(exp, c_data.t_bool);
				ok=true;

			}
			else if (t_arg1  instanceof Tipo_Puntero && t_arg0  instanceof Tipo_null ) {
				c_data.tipos.put(exp, c_data.t_bool);
				ok=true;
			}
				
			else if (t_arg0  instanceof Tipo_null && t_arg1  instanceof Tipo_null ) {
				c_data.tipos.put(exp, c_data.t_bool);
				ok=true;
			}
		} 
			
		if(ok == false)
			c_data.tipos.put(exp, c_data.error);

	}
	
	private boolean tiposCorrectos(Object o1, Object o2) {
		if (c_data.tipos.get(o1) != c_data.error && c_data.tipos.get(o2) != c_data.error)
			return true;
		return false;
	}

	private boolean mismoNumeroCampos(Tipo_Reg reg1, Tipo_Reg reg2) {
		LCampos lcampos1 = reg1.lcampos();
		LCampos lcampos2 = reg2.lcampos();
		if(cuentaCampos(lcampos1) == cuentaCampos(lcampos2))
			return true;
		return false;
	}

	private int cuentaCampos(LCampos lcampos) {
		if(lcampos instanceof LCampos_uno)
			return 1;
		else if(lcampos instanceof LCampos_muchos){
			LCampos_muchos campos = (LCampos_muchos)lcampos;
			return cuentaCampos(campos.campos()) + 1;
		}
		return 0;
	}
	private boolean mismaLongitud(Tipo_Array array1, Tipo_Array array2) {
		if(Integer.parseInt(array1.id().toString()) == Integer.parseInt(array2.id().toString()))
			return true;
		return false;
	}

	
	private boolean camposDuplicados(LCampos lcampos,ArrayList<String> campos_pasados) {
		if(lcampos instanceof LCampos_uno) {
			LCampos_uno campos = (LCampos_uno)lcampos;
			for(int i= 0; i < campos_pasados.size();i++) {
				if(campos.campo().id().toString().equals(campos_pasados.get(i)))
					return false;
			}
			return true;
		}
		else if(lcampos instanceof LCampos_muchos) {
			LCampos_muchos campos = (LCampos_muchos)lcampos;
			for(int i= 0; i < campos_pasados.size();i++) {
				if(campos.campo().id().toString().equals(campos_pasados.get(i)))
					return false;
			}
			campos_pasados.add(campos.campo().id().toString());
			return camposDuplicados(campos.campos(),campos_pasados);
			
		}
		return false;
	}
	private boolean camposCompatibles(LCampos lcampos1, LCampos lcampos2) {
		if(lcampos1 instanceof LCampos_uno) {
			LCampos_uno campos1 = (LCampos_uno)lcampos1;
			LCampos_uno campos2= (LCampos_uno)lcampos2;
			return asignacionCompatible_aux(campos1.campo().tipo(),campos2.campo().tipo());
		}
		else if(lcampos1 instanceof LCampos_muchos) {
			LCampos_muchos campos = (LCampos_muchos)lcampos1;
			LCampos_muchos campos2= (LCampos_muchos)lcampos2;
			return (asignacionCompatible_aux(campos.campo().tipo(),campos2.campo().tipo()) 
					 && camposCompatibles(campos.campos(),campos2.campos()));
		}
		else
			return false;
	}

	private int cuentaRealParams(LReal_params lrparams) {
		if(lrparams instanceof LReal_Params_uno)
			return 1;
		else if( lrparams instanceof LReal_Params_muchos) {
			LReal_Params_muchos lparams = (LReal_Params_muchos)lrparams;
			return cuentaRealParams(lparams.params()) + 1;
		}
		return 0;
	}
	private int cuentaParamsFormales(LParams lparams) {
		if(lparams instanceof LParams_uno)
			return 1;
		else if( lparams instanceof LParams_muchos) {
			LParams_muchos params = (LParams_muchos)lparams;
			return cuentaParamsFormales(params.lparams()) + 1;
		}
		return 0;
	}
	
	private Boolean paramCompatible(Param param_formal, Exp param_real) {
		if(param_formal instanceof Param_sin_amp)
			return asignacionCompatible(param_formal.tipo(), (Tipo)c_data.tipos.get(param_real));
		else if(param_formal instanceof Param_amp) {
			if(c_data.esDesignador(param_real))
				return asignacionCompatible(param_formal.tipo(), (Tipo)c_data.tipos.get(param_real));
			System.out.println("La expresion "+param_real + " no es un designador");
			return false;
		}
		else 
			System.out.println("Error, no es parametro valido");
		return false;
	}
	
	private boolean parametrosCompatibles(LReal_params real_params, LParams lparams) {
		if(real_params instanceof LReal_Params_uno) {
			LReal_Params_uno lrparams_uno = (LReal_Params_uno) real_params;
			LParams_uno  lparams_uno = (LParams_uno)lparams;
			return paramCompatible(lparams_uno.param(),lrparams_uno.exp());
		}
		else {
			LReal_Params_muchos lrparams_muchos = (LReal_Params_muchos) real_params;
			LParams_muchos  lparams_muchos = (LParams_muchos)lparams;
			return parametrosCompatibles(lrparams_muchos.params(), lparams_muchos.lparams());
		}
		
	}
	
	private Campo buscaCampo(String string, LCampos campos) {
		if (campos instanceof LCampos_muchos) {
			LCampos_muchos lcampos = (LCampos_muchos) campos;
			Campo c = buscaCampo(string, lcampos.campo());
			if(c !=null) {
				return c; 
			}
			return buscaCampo(string, lcampos.campos());
		}
		else if (campos instanceof LCampos_uno) {
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

}
