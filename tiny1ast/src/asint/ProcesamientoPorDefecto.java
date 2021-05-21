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
import asint.TinyASint.Num;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Resta exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Mul exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Div exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Id exp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Num exp) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void procesa(Menos_unario menos_unario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Not not) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Beq beq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Bne bne) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Bge bge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Ble ble) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Blt blt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Bgt bgt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(And and) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Or or) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(R_true r_true) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(R_false r_false) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LDecs_una lDecs_una) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LDecs_muchas lDecs_muchas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Lista_inst_una lista_inst_una) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Lista_inst_muchas lista_inst_muchas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Prog_con_decs prog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Prog_sin_decs prog) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void procesa(Tipo_int tipo_int) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Tipo_real tipo_real) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Tipo_bool tipo_bool) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Tipo_String tipo_String) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Tipo_Id tipo_Id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Tipo_Puntero tipo_Puntero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Tipo_Array tipo_Array) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Tipo_Reg tipo_Reg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LCampos_uno lCampos_uno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LCampos_muchos lCampos_muchos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Campo campo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LInst_aux_vacia lInst_aux_vacia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LInst_aux_llena lInst_aux_llena) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LReal_Params_uno lReal_Params_uno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LReal_Params_muchos lReal_Params_muchos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Param_sin_amp param_sin_amp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Param_amp param_amp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Bloque_lleno bloque_lleno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Bloque_vacio bloque_vacio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LParams_muchos lParams_muchos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(LParams_uno lParams_uno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Mod mod) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(R_null r_null) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(R_str r_str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Index index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Indireccion indireccion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Punto punto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Flecha flecha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Dec_Var dec_var) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Dec_Proc_sin_params dec_Proc_sin_params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Dec_Proc_con_params dec_Proc_con_params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Dec_Type dec_Type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_asig inst) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_if_then inst_if_then) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_if_then_else inst_if_then_else) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_while inst_while) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_read inst_read) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_write inst_write) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_nl inst_nl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_new inst_new) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_delete inst_delete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_call_con_params inst_call_con_params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_call_sin_params inst_call_sin_params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Inst_compuesta inst_compuesta) {
		// TODO Auto-generated method stub
		
	}

	
}
