package asint;

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

public class ProcesamientoPorDefecto implements Procesamiento {

	@Override
	public void procesa(Suma exp) {

		
	}

	@Override
	public void procesa(Resta exp) {

		
	}

	@Override
	public void procesa(Mul exp) {
		
		
	}

	@Override
	public void procesa(Div exp) {
		
		
	}

	@Override
	public void procesa(Id exp) {
		
		
	}



	@Override
	public void procesa(Menos_unario menos_unario) {
		
		
	}

	@Override
	public void procesa(Not not) {
		
		
	}

	@Override
	public void procesa(Beq beq) {
		
		
	}

	@Override
	public void procesa(Bne bne) {
		
		
	}

	@Override
	public void procesa(Bge bge) {
		
		
	}

	@Override
	public void procesa(Ble ble) {
		
		
	}

	@Override
	public void procesa(Blt blt) {
		
		
	}

	@Override
	public void procesa(Bgt bgt) {
		
		
	}

	@Override
	public void procesa(And and) {
		
		
	}

	@Override
	public void procesa(Or or) {
		
		
	}

	@Override
	public void procesa(R_true r_true) {
		
		
	}

	@Override
	public void procesa(R_false r_false) {
		
		
	}

	@Override
	public void procesa(LDecs_una lDecs_una) {
		
		
	}

	@Override
	public void procesa(LDecs_muchas lDecs_muchas) {
		
		
	}

	@Override
	public void procesa(Lista_inst_una lista_inst_una) {
		
		
	}

	@Override
	public void procesa(Lista_inst_muchas lista_inst_muchas) {
		
		
	}

	@Override
	public void procesa(Prog_con_decs prog) {
		
		
	}

	@Override
	public void procesa(Prog_sin_decs prog) {
		
		
	}


	@Override
	public void procesa(Tipo_int tipo_int) {
		
		
	}

	@Override
	public void procesa(Tipo_real tipo_real) {
		
		
	}

	@Override
	public void procesa(Tipo_bool tipo_bool) {
		
		
	}

	@Override
	public void procesa(Tipo_String tipo_String) {
		
		
	}

	@Override
	public void procesa(Tipo_Id tipo_Id) {
		
		
	}

	@Override
	public void procesa(Tipo_Puntero tipo_Puntero) {
		
		
	}

	@Override
	public void procesa(Tipo_Array tipo_Array) {
		
		
	}

	@Override
	public void procesa(Tipo_Reg tipo_Reg) {
		
		
	}

	@Override
	public void procesa(LCampos_uno lCampos_uno) {
		
		
	}

	@Override
	public void procesa(LCampos_muchos lCampos_muchos) {
		
		
	}

	@Override
	public void procesa(Campo campo) {
		
		
	}

	@Override
	public void procesa(LInst_aux_vacia lInst_aux_vacia) {
		
		
	}

	@Override
	public void procesa(LInst_aux_llena lInst_aux_llena) {
		
		
	}

	@Override
	public void procesa(LReal_Params_uno lReal_Params_uno) {
		
		
	}

	@Override
	public void procesa(LReal_Params_muchos lReal_Params_muchos) {
		
		
	}

	@Override
	public void procesa(Param_sin_amp param_sin_amp) {
		
		
	}

	@Override
	public void procesa(Param_amp param_amp) {
		
		
	}

	@Override
	public void procesa(Bloque_lleno bloque_lleno) {
		
		
	}

	@Override
	public void procesa(Bloque_vacio bloque_vacio) {
		
		
	}

	@Override
	public void procesa(LParams_muchos lParams_muchos) {
		
		
	}

	@Override
	public void procesa(LParams_uno lParams_uno) {
		
		
	}

	@Override
	public void procesa(Mod mod) {
		
		
	}

	@Override
	public void procesa(R_null r_null) {
		
		
	}

	@Override
	public void procesa(R_str r_str) {
		
		
	}

	@Override
	public void procesa(Index index) {
		
		
	}

	@Override
	public void procesa(Indireccion indireccion) {
		
		
	}

	@Override
	public void procesa(Punto punto) {
		
		
	}

	@Override
	public void procesa(Flecha flecha) {
		
		
	}

	@Override
	public void procesa(Dec_Var dec_var) {
		
		
	}

	@Override
	public void procesa(Dec_Proc_sin_params dec_Proc_sin_params) {
		
		
	}

	@Override
	public void procesa(Dec_Proc_con_params dec_Proc_con_params) {
		
		
	}

	@Override
	public void procesa(Dec_Type dec_Type) {
		
		
	}

	@Override
	public void procesa(Inst_asig inst) {
		
		
	}

	@Override
	public void procesa(Inst_if_then inst_if_then) {
		
		
	}

	@Override
	public void procesa(Inst_if_then_else inst_if_then_else) {
		
		
	}

	@Override
	public void procesa(Inst_while inst_while) {
		
		
	}

	@Override
	public void procesa(Inst_read inst_read) {
		
		
	}

	@Override
	public void procesa(Inst_write inst_write) {
		
		
	}

	@Override
	public void procesa(Inst_nl inst_nl) {
		
		
	}

	@Override
	public void procesa(Inst_new inst_new) {
		
		
	}

	@Override
	public void procesa(Inst_delete inst_delete) {
		
		
	}

	@Override
	public void procesa(Inst_call_con_params inst_call_con_params) {
		
		
	}

	@Override
	public void procesa(Inst_call_sin_params inst_call_sin_params) {
		
		
	}

	@Override
	public void procesa(Inst_compuesta inst_compuesta) {
		
		
	}

	@Override
	public void procesa(NumEnt exp) {
		
		
	}

	@Override
	public void procesa(NumReal exp) {
		
		
	}

	
}
