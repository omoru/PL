package procesamientos;

import asint.ProcesamientoPorDefecto;
import asint.TinyASint.And;
import asint.TinyASint.Beq;
import asint.TinyASint.Bge;
import asint.TinyASint.Bgt;
import asint.TinyASint.Ble;
import asint.TinyASint.Bloque_lleno;

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
import maquinaP.MaquinaP;
import tables.Asig_esp_data;
import tables.Comp_tipos_data;
import tables.Etiq_data;
import tables.Vinculation_data;

public class Generacion_codigo extends ProcesamientoPorDefecto {
	private MaquinaP m;
	private Vinculation_data v_data;
	private Asig_esp_data a_data;
	private Comp_tipos_data c_data;
	private Etiq_data e_data;

	public Generacion_codigo(Etiq_data e_data, Comp_tipos_data c_data, Vinculation_data v_data, Asig_esp_data a_data) {
		this.v_data = v_data;
		this.a_data = a_data;
		this.c_data = c_data;
		this.e_data = e_data;
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

		dec.tipo().procesa(this);

	}

	public void procesa(Dec_Type dec) {

		dec.tipo().procesa(this);

	}

	public void procesa(Dec_Proc_con_params dec) {

		dec.lparams().procesa(this);

		dec.bloque().procesa(this);
		m.irInd();

	}

	public void procesa(Dec_Proc_sin_params dec) {

		dec.bloque().procesa(this);
		m.irInd();

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
		m.alloc(a_data.tamaños.get(param.tipo()));
		m.apilaPuntero(a_data.direcciones.get(param));
	}

	public void procesa(Param_sin_amp param) {
		param.tipo().procesa(this);
		m.alloc(a_data.tamaños.get(param.tipo()));
		m.apilaPuntero(a_data.direcciones.get(param));
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
		a_data.tamaños.put(tipo_p, 1);

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
		if (c_data.tipos.get(inst.exp1()) instanceof Tipo_real && c_data.tipos.get(inst.exp2()) instanceof Tipo_int) {
			if (c_data.esDesignador(inst.exp2()))
				m.fetch();
			m.intToReal();
			m.store();
		} else {
			if (c_data.esDesignador(inst.exp2())) {
				Object vinculo = v_data.vinculos.get(inst.exp1());
				Tipo tipo = c_data.tipos.get(vinculo);
				Integer tam = a_data.tamaños.get(tipo);
				m.copy(tam);
			} else
				m.store();

		}
	}

	public void procesa(Inst_if_then inst) {

		inst.exp().procesa(this);
		if (c_data.esDesignador(inst.exp()))
			m.fetch();
		m.irF(e_data.etqf.get(inst));
		inst.linst_aux().procesa(this);

	}

	public void procesa(Inst_if_then_else inst) {
		inst.exp().procesa(this);
		if (c_data.esDesignador(inst.exp()))
			m.fetch();
		m.irF(e_data.etqi.get(inst.linst_aux2()));
		inst.linst_aux1().procesa(this);
		m.irA(e_data.etqf.get(inst) + 1);
		inst.linst_aux2().procesa(this);

	}

	public void procesa(LInst_aux_llena linst) {
		linst.linst().procesa(this);
	}

	public void procesa(Inst_while inst) {

		inst.exp().procesa(this);
		if (c_data.esDesignador(inst.exp()))
			m.fetch();
		m.irF(e_data.etqf.get(inst) + 1);
		inst.linst_aux().procesa(this);

	}

	public void procesa(Inst_read inst) {
		inst.exp().procesa(this);
		m.read();
		m.store();
	}

	public void procesa(Inst_write inst) {

		inst.exp().procesa(this);
		if (c_data.esDesignador(inst.exp()))
			m.fetch();
		m.write();
	}

	public void procesa(Inst_nl inst) {
		m.writeNL();
	}

	public void procesa(Inst_new inst) {

		inst.exp().procesa(this);
		Object vinculo = v_data.vinculos.get(inst.exp());
		Tipo tipo = c_data.tipos.get(vinculo);
		m.alloc(a_data.tamaños.get(tipo));
	}

	public void procesa(Inst_delete inst) {
		inst.exp().procesa(this);
		Object vinculo = v_data.vinculos.get(inst.exp());
		Tipo tipo = c_data.tipos.get(vinculo);
		m.dealloc(a_data.tamaños.get(tipo));
	}

	public void procesa(Inst_call_con_params inst) {
		m.apilaInt(e_data.etqf.get(inst));
		inst.real_params().procesa(this);
		Object vinculo = v_data.vinculos.get(inst);
		m.irA(a_data.direcciones.get(vinculo));
	}

	public void procesa(Inst_call_sin_params inst) {
		m.apilaInt(e_data.etqf.get(inst));
		Object vinculo = v_data.vinculos.get(inst);
		m.irA(a_data.direcciones.get(vinculo));
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

	public void procesa(Suma exp) {
		exp.arg0().procesa(this);
		if (c_data.esDesignador(exp.arg0()))
			m.fetch();
		if (c_data.tipos.get(exp) instanceof Tipo_real && c_data.tipos.get(exp.arg0()) instanceof Tipo_int)
			m.intToReal();
		exp.arg1().procesa(this);
		if (c_data.esDesignador(exp.arg1()))
			m.fetch();
		if (c_data.tipos.get(exp) instanceof Tipo_real && c_data.tipos.get(exp.arg1()) instanceof Tipo_int)
			m.intToReal();
		m.suma();

	}

	public void procesa(Resta exp) {
		exp.arg0().procesa(this);
		if (c_data.esDesignador(exp.arg0()))
			m.fetch();
		if (c_data.tipos.get(exp) instanceof Tipo_real && c_data.tipos.get(exp.arg0()) instanceof Tipo_int)
			m.intToReal();
		exp.arg1().procesa(this);
		if (c_data.esDesignador(exp.arg1()))
			m.fetch();
		if (c_data.tipos.get(exp) instanceof Tipo_real && c_data.tipos.get(exp.arg1()) instanceof Tipo_int)
			m.intToReal();
		m.resta();

	}

	public void procesa(Mul exp) {
		exp.arg0().procesa(this);
		if (c_data.esDesignador(exp.arg0()))
			m.fetch();
		if (c_data.tipos.get(exp) instanceof Tipo_real && c_data.tipos.get(exp.arg0()) instanceof Tipo_int)
			m.intToReal();
		exp.arg1().procesa(this);
		if (c_data.esDesignador(exp.arg1()))
			m.fetch();
		if (c_data.tipos.get(exp) instanceof Tipo_real && c_data.tipos.get(exp.arg1()) instanceof Tipo_int)
			m.intToReal();
		m.mul();
	}

	public void procesa(Div exp) {
		exp.arg0().procesa(this);
		if (c_data.esDesignador(exp.arg0()))
			m.fetch();
		if (c_data.tipos.get(exp) instanceof Tipo_real && c_data.tipos.get(exp.arg0()) instanceof Tipo_int)
			m.intToReal();
		exp.arg1().procesa(this);
		if (c_data.esDesignador(exp.arg1()))
			m.fetch();
		if (c_data.tipos.get(exp) instanceof Tipo_real && c_data.tipos.get(exp.arg1()) instanceof Tipo_int)
			m.intToReal();
		m.div();
	}

	public void procesa(Mod exp) {
		exp.arg0().procesa(this);
		if (c_data.esDesignador(exp.arg0()))
			m.fetch();
		exp.arg1().procesa(this);
		if (c_data.esDesignador(exp.arg1()))
			m.fetch();
		m.mod();

	}

	public void procesa(NumEnt exp) {
		m.apilaEntero(exp.num());
	}

	public void procesa(NumReal exp) {
		m.apilaReal(exp.num());
	}

	public void procesa(Menos_unario menos_unario) {
		menos_unario.arg0().procesa(this);
		if (c_data.esDesignador(menos_unario.arg0()))
			m.fetch();

		m.menos();
	}

	public void procesa(Not not) {
		not.arg0().procesa(this);
		if (c_data.esDesignador(not.arg0()))
			m.fetch();

		m.not();
	}

	public void procesa(Beq beq) {
		beq.arg0().procesa(this);
		if (c_data.esDesignador(beq.arg0()))
			m.fetch();
		beq.arg1().procesa(this);
		if (c_data.esDesignador(beq.arg1()))
			m.fetch();
		m.beq();
	}

	public void procesa(Bne bne) {
		bne.arg0().procesa(this);
		if (c_data.esDesignador(bne.arg0()))
			m.fetch();
		bne.arg1().procesa(this);
		if (c_data.esDesignador(bne.arg1()))
			m.fetch();
		m.bne();
	}

	public void procesa(Bge bge) {
		bge.arg0().procesa(this);
		if (c_data.esDesignador(bge.arg0()))
			m.fetch();
		bge.arg1().procesa(this);
		if (c_data.esDesignador(bge.arg1()))
			m.fetch();
		m.bge();

	}

	public void procesa(Ble ble) {
		ble.arg0().procesa(this);
		if (c_data.esDesignador(ble.arg0()))
			m.fetch();
		ble.arg1().procesa(this);
		if (c_data.esDesignador(ble.arg1()))
			m.fetch();
		m.ble();

	}

	public void procesa(Blt blt) {
		blt.arg0().procesa(this);
		if (c_data.esDesignador(blt.arg0()))
			m.fetch();
		blt.arg1().procesa(this);
		if (c_data.esDesignador(blt.arg1()))
			m.fetch();
		m.blt();

	}

	public void procesa(Bgt bgt) {
		bgt.arg0().procesa(this);
		if (c_data.esDesignador(bgt.arg0()))
			m.fetch();
		bgt.arg1().procesa(this);
		if (c_data.esDesignador(bgt.arg1()))
			m.fetch();
		m.bgt();

	}

	public void procesa(And and) {
		and.arg0().procesa(this);
		if (c_data.esDesignador(and.arg0()))
			m.fetch();
		and.arg1().procesa(this);
		if (c_data.esDesignador(and.arg1()))
			m.fetch();
		m.and();

	}

	public void procesa(Or or) {
		or.arg0().procesa(this);
		if (c_data.esDesignador(or.arg0()))
			m.fetch();
		or.arg1().procesa(this);
		if (c_data.esDesignador(or.arg1()))
			m.fetch();
		m.or();
	}

	public void procesa(R_true r_true) {
		m.apilaBool(true);

	}

	public void procesa(R_false r_false) {
		m.apilaBool(false);
	}

	public void procesa(Punto punto) {
		punto.exp().procesa(this);
		Object vinculo = v_data.vinculos.get(punto.exp());
		Tipo tipo = c_data.tipos.get(vinculo);
		m.acc(punto.id(), a_data.tamaños.get(tipo));
	}

	public void procesa(Flecha flecha) {
		flecha.exp().procesa(this);
		m.fetch();
		Object vinculo = v_data.vinculos.get(flecha.exp());
		Tipo tipo = c_data.tipos.get(vinculo);
		m.acc(flecha.id(), a_data.tamaños.get(tipo));
	}

	public void procesa(Indireccion ind) {
		ind.exp().procesa(this);
		m.fetch();

	}

	public void procesa(R_null n) {
		m.apilaPuntero(-1);
	}

	public void procesa(R_str s) {
		m.apilaString(s.id().toString());
	}

	public void procesa(Index ind) {
		ind.exp1().procesa(this);

		ind.exp2().procesa(this);
		if (c_data.esDesignador(ind.exp2()))
			m.fetch();
		Object vinculo = v_data.vinculos.get(ind);
		Tipo tipo = c_data.tipos.get(vinculo);
		m.indx(a_data.tamaños.get(tipo));
	}

	public void procesa(Id id) {
		Object vinculo = v_data.vinculos.get(id);
		m.apilaPuntero(a_data.direcciones.get(vinculo));
	}

}
