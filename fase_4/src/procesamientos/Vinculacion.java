package procesamientos;

import java.util.ListIterator;
import java.util.Map;

import tables.Vinculation_data;
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
import asint.TinyASint.LCampos;
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
import asint.TinyASint.Tipo;
import asint.TinyASint.Tipo_Array;
import asint.TinyASint.Tipo_Id;
import asint.TinyASint.Tipo_Puntero;
import asint.TinyASint.Tipo_Reg;
import asint.TinyASint.Tipo_String;
import asint.TinyASint.Tipo_bool;
import asint.TinyASint.Tipo_int;
import asint.TinyASint.Tipo_real;

public class Vinculacion extends ProcesamientoPorDefecto {
	public Vinculation_data v_data;
	public Vinculacion_fase1 vinculacion_fase1;
	public Vinculacion_fase2 vinculacion_fase2;

	public Vinculacion(Vinculation_data v_data) {
		this.v_data = v_data;
		this.vinculacion_fase1 = new Vinculacion_fase1(this);
		this.vinculacion_fase2 = new Vinculacion_fase2(this);
	}

	public void procesa(Prog_con_decs prog) {
		v_data.abreBloque();
		prog.ldecs().procesa(this.vinculacion_fase1);
		prog.ldecs().procesa(this.vinculacion_fase2);
		prog.linst().procesa(this);
		v_data.cierraBloque();

	}

	public void procesa(Prog_sin_decs prog) {
		v_data.abreBloque();
		prog.linst().procesa(this);
		v_data.cierraBloque();
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

	public void procesa(Inst_compuesta inst) {
		v_data.abreBloque();
		inst.bloque().procesa(this);
		v_data.cierraBloque();
	}

	public void procesa(Bloque_lleno bloque) {
		bloque.prog().procesa(this);

	}

	public void procesa(Bloque_vacio bloque) {

	}

	public void procesa(Inst_call_con_params inst) {
		ListIterator<Map<String, Object>> it = v_data.tablaDeSimbolos.listIterator(v_data.tablaDeSimbolos.size());
		while (it.hasPrevious()) {
			Object e = it.previous().get(inst.id().toString());
			if (e != null) {
				v_data.insertaVinculo(inst, e);
				inst.real_params().procesa(this);
				return;
			}
		}
		v_data.insertaError("Error al buscar el id: " + inst.id().toString() +
				" en la ts de la llamada call (con params) / vincula", inst.id().fila(), inst.id().col());
	}

	public void procesa(Inst_call_sin_params inst) {
		ListIterator<Map<String, Object>> it = v_data.tablaDeSimbolos.listIterator(v_data.tablaDeSimbolos.size());
		while (it.hasPrevious()) {
			Object e = it.previous().get(inst.id().toString());
			if (e != null) {
				v_data.insertaVinculo(inst, e);
				return;
			}
		}
		v_data.insertaError("Error al buscar el id: " + inst.id().toString() +
				" en la ts de la llamada call (sin params) / vincula", inst.id().fila(), inst.id().col());
	

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

	public void procesa(Inst_if_then inst) {
		inst.exp().procesa(this);
		inst.linst_aux().procesa(this);
	}

	public void procesa(Inst_if_then_else inst) {
		inst.exp().procesa(this);
		inst.linst_aux1().procesa(this);
		inst.linst_aux2().procesa(this);
	}

	public void procesa(Inst_nl inst) {
	}

	public void procesa(Inst_new inst) {

		inst.exp().procesa(this);
	}

	public void procesa(Inst_delete inst) {

		inst.exp().procesa(this);
	}

	public void procesa(LReal_Params_uno params) {
		params.exp().procesa(this);
	}

	public void procesa(LReal_Params_muchos params) {
		params.params().procesa(this);
		params.exp().procesa(this);
	}

	public void procesa(Id id) {
		ListIterator<Map<String, Object>> it = v_data.tablaDeSimbolos.listIterator(v_data.tablaDeSimbolos.size());
		while (it.hasPrevious()) {
			Object e = it.previous().get(id.id().toString());
			if (e != null) {
				v_data.insertaVinculo(id, e);
				return;
			}
		}
		v_data.insertaError("Error al buscar el id: " + id.id().toString() +
				" en la ts de la llamada call (sin params) / vincula", id.id().fila(), id.id().col());	}

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

	public void procesa(NumEnt exp) {
		
	}
	public void procesa(NumReal exp) {
		
	}

	public void procesa(Menos_unario menos_unario) {
		menos_unario.arg0().procesa(this);
	}

	public void procesa(Not not) {
		not.arg0().procesa(this);

	}

	public void procesa(Beq exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);

	}

	public void procesa(Bne exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);

	}

	public void procesa(Bge exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);

	}

	public void procesa(Ble exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);

	}

	public void procesa(Blt exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);

	}

	public void procesa(Bgt exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);

	}

	public void procesa(And exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);

	}

	public void procesa(Or exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}

	public void procesa(LInst_aux_vacia linst) {

	}

	public void procesa(LInst_aux_llena linst) {
		linst.linst().procesa(this);
	}

	public void procesa(R_true r_true) {

	}

	public void procesa(R_false r_false) {

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

	public void procesa(Punto punto) {
		punto.exp().procesa(this);
		Object vinculo_exp = v_data.vinculos.get(punto.exp());
		
		/*if (vinculo_exp instanceof Dec_Var) {

			Dec_Var type_vinculo_exp = (Dec_Var) vinculo_exp;
			if (type_vinculo_exp.tipo() instanceof Tipo_Reg) {

				Tipo_Reg reg = (Tipo_Reg) type_vinculo_exp.tipo();
				if (buscaCampo(punto.id().toString(), reg.lcampos())) {

				} else
					v_data.insertaError("No se encontro el campo " + punto.id().toString(), punto.id().fila(),
							punto.id().col());

			} else if (type_vinculo_exp.tipo() instanceof Tipo_Id) {
				Tipo type = v_data.ref(type_vinculo_exp.tipo());
				if (type instanceof Tipo_Reg) {

					Tipo_Reg reg = (Tipo_Reg) type;
					if (buscaCampo(punto.id().toString(), reg.lcampos())) {

					} else
						v_data.insertaError("No se encontro el campo " + punto.id().toString(), punto.id().fila(),
								punto.id().col());

				} else
					v_data.insertaError("El tipo base de " + punto.id().toString() + " no es de tipo registro.",
							punto.id().fila(), punto.id().col());

			} else
				v_data.insertaError("El tipo base de " + punto.id().toString() + " no es de tipo registro.",
						punto.id().fila(), punto.id().col());
		} else*/ if (vinculo_exp instanceof Dec_Type) {

			Dec_Type type_vinculo_exp = (Dec_Type) vinculo_exp;
			if (type_vinculo_exp.tipo() instanceof Tipo_Reg) {

				Tipo_Reg reg = (Tipo_Reg) type_vinculo_exp.tipo();
				if (buscaCampo(punto.id().toString(), reg.lcampos())) {

				} else
					v_data.insertaError("No se encontro el campo " + punto.id().toString(), punto.id().fila(),
							punto.id().col());

			} else if (type_vinculo_exp.tipo() instanceof Tipo_Id) {
				Tipo type = v_data.ref(type_vinculo_exp.tipo());
				if (type instanceof Tipo_Reg) {

					Tipo_Reg reg = (Tipo_Reg) type;
					if (buscaCampo(punto.id().toString(), reg.lcampos())) {
					} else
						v_data.insertaError("No se encontro el campo " + punto.id().toString(), punto.id().fila(),
								punto.id().col());

				} else
					v_data.insertaError("El tipo base de " + punto.id().toString() + " no es de tipo registro.",
							punto.id().fila(), punto.id().col());

			} else
				v_data.insertaError("El tipo base de " + punto.id().toString() + " no es de tipo registro.",
						punto.id().fila(), punto.id().col());
		}

	}

	private boolean buscaCampo(String string, LCampos campos) {
		if (campos instanceof LCampos_muchos) {
			LCampos_muchos lcampos = (LCampos_muchos) campos;
			return buscaCampo(string, lcampos.campo()) || buscaCampo(string, lcampos.campos());
		} else if (campos instanceof LCampos_uno) {
			LCampos_uno lcampos = (LCampos_uno) campos;
			return buscaCampo(string, lcampos);
		}

		return false;

	}

	private boolean buscaCampo(String string, LCampos_uno campos) {
		return buscaCampo(string, campos.campo());
	}

	private boolean buscaCampo(String string, Campo campo) {

		if (campo.id().toString().equals(string))
			return true;
		else
			return false;
	}

	public class Vinculacion_fase1 extends ProcesamientoPorDefecto {
		public Vinculacion_fase1(Vinculacion vinculacion) {
		}

		public void procesa(LDecs_una lDecs_una) {
			lDecs_una.dec().procesa(this);
		}

		public void procesa(LDecs_muchas lDecs_muchas) {
			lDecs_muchas.ldecs().procesa(this);
			lDecs_muchas.dec().procesa(this);
		}

		public void procesa(Dec_Var dec) {
			dec.tipo().procesa(this);
			v_data.inserta_en_ts(dec.id().toString(), dec);
		}

		public void procesa(Dec_Type dec) {
			dec.tipo().procesa(this);
			v_data.inserta_en_ts(dec.id().toString(), dec);

		}

		public void procesa(Dec_Proc_con_params dec) {
			v_data.inserta_en_ts(dec.id().toString(), dec);

		}

		public void procesa(Dec_Proc_sin_params dec) {
			v_data.inserta_en_ts(dec.id().toString(), dec);

		}

		public void procesa(LParams_uno l_params_uno) {
			l_params_uno.param().procesa(this);
		}

		public void procesa(LParams_muchos l_params_muchos) {
			l_params_muchos.lparams().procesa(this);
			l_params_muchos.param().procesa(this);
		}

		public void procesa(Param_amp param) {
			param.tipo().procesa(this);
		}

		public void procesa(Param_sin_amp param) {
			param.tipo().procesa(this);
		}

		public void procesa(Tipo_int tipo_int) {

		}

		public void procesa(Tipo_real tipo_real) {
		}

		public void procesa(Tipo_bool tipo_bool) {
		}

		public void procesa(Tipo_String tipo_s) {
		}

		public void procesa(Tipo_Id tipo_id) {// ---------------------------------????????????????????????????------------

			ListIterator<Map<String, Object>> it = v_data.tablaDeSimbolos.listIterator(v_data.tablaDeSimbolos.size());
			while (it.hasPrevious()) {
				Object e = it.previous().get(tipo_id.id().toString());
				if (e != null) {
					v_data.insertaVinculo(tipo_id, e);
					return;
				}
			}
			v_data.insertaError("Error al buscar el id: " + tipo_id.id().toString() +
					" en la ts / vinculacion_fase1", tipo_id.id().fila(), tipo_id.id().col());	

		}

		public void procesa(Tipo_Puntero tipo_p) {
			if (!(tipo_p.tipo() instanceof Tipo_Id))// --------------------------??????????????
				tipo_p.tipo().procesa(this);
		}

		public void procesa(Tipo_Array tipo_a) {
			tipo_a.tipo().procesa(this);

		}

		public void procesa(Tipo_Reg tipo_r) {
			tipo_r.lcampos().procesa(this);

		}

		public void procesa(LCampos_muchos campos) {
			campos.campos().procesa(this);
			campos.campo().procesa(this);
		}

		public void procesa(LCampos_uno campos) {
			campos.campo().procesa(this);
		}
	}

	public class Vinculacion_fase2 extends ProcesamientoPorDefecto {
		private Vinculacion vinculacion;

		public Vinculacion_fase2(Vinculacion vinculacion) {
			this.vinculacion = vinculacion;
		}

		public void procesa(LDecs_una lDecs_una) {
			lDecs_una.dec().procesa(this);
		}

		public void procesa(LDecs_muchas lDecs_muchas) {
			lDecs_muchas.ldecs().procesa(this);
			lDecs_muchas.dec().procesa(this);
		}

		public void procesa(Dec_Var dec) {
			dec.tipo().procesa(this);
		}

		public void procesa(Dec_Type dec) {
			dec.tipo().procesa(this);

		}

		public void procesa(Dec_Proc_con_params dec) {
			this.vinculacion.v_data.abreBloque();
			dec.lparams().procesa(this.vinculacion.vinculacion_fase1);
			dec.lparams().procesa(this);
			dec.bloque().procesa(this.vinculacion);
			this.vinculacion.v_data.cierraBloque();

		}

		public void procesa(Dec_Proc_sin_params dec) {
			this.vinculacion.v_data.abreBloque();
			dec.bloque().procesa(new Vinculacion(v_data));
			this.vinculacion.v_data.cierraBloque();
		}

		public void procesa(LParams_uno l_params_uno) {
			l_params_uno.param().procesa(this);
		}

		public void procesa(LParams_muchos l_params_muchos) {
			l_params_muchos.lparams().procesa(this);
			l_params_muchos.param().procesa(this);
		}

		public void procesa(Param_amp param) {
			param.tipo().procesa(this);
			this.vinculacion.v_data.inserta_en_ts(param.id().toString(), param.tipo());
		}

		public void procesa(Param_sin_amp param) {
			param.tipo().procesa(this.vinculacion.vinculacion_fase1);
			this.vinculacion.v_data.inserta_en_ts(param.id().toString(), param.tipo());
		}

		public void procesa(Tipo_int tipo_int) {

		}

		public void procesa(Tipo_real tipo_real) {
		}

		public void procesa(Tipo_bool tipo_bool) {
		}

		public void procesa(Tipo_String tipo_s) {
		}

		public void procesa(Tipo_Id tipo_id) {

		}

		public void procesa(Tipo_Puntero tipo_p) {
			ListIterator<Map<String, Object>> it = v_data.tablaDeSimbolos.listIterator(v_data.tablaDeSimbolos.size());

			if (tipo_p.tipo() instanceof Tipo_Id) {
				Tipo_Id t = (Tipo_Id) tipo_p.tipo();
				while (it.hasPrevious()) {
					Object e = it.previous().get(t.id().toString());
					if (e != null) {
						v_data.insertaVinculo(tipo_p, e);
						return;
					}
				}
			}

		}

		public void procesa(Tipo_Array tipo_a) {
			tipo_a.tipo().procesa(this);

		}

		public void procesa(Tipo_Reg tipo_r) {
			tipo_r.lcampos().procesa(this);

		}

		public void procesa(LCampos_muchos campos) {
			campos.campos().procesa(this);
			campos.campo().procesa(this);
		}

		public void procesa(LCampos_uno campos) {
			campos.campo().procesa(this);
		}

		public void procesa(Campo campo) {
			campo.tipo().procesa(this);
		}

	}

}
