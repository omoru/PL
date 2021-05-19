package asint;

import asint.TinyASint.Suma;
import asint.TinyASint.Tipo_bool;
import asint.TinyASint.Tipo_int;
import asint.TinyASint.Tipo_real;
import asint.TinyASint.Resta;
import asint.TinyASint.Mul;
import asint.TinyASint.Not;
import asint.TinyASint.Div;
import asint.TinyASint.Id;
import asint.TinyASint.Inst;
import asint.TinyASint.LDecs_muchas;
import asint.TinyASint.LDecs_una;
import asint.TinyASint.Lista_inst_muchas;
import asint.TinyASint.Lista_inst_una;
import asint.TinyASint.Menos_unario;
import asint.TinyASint.Num;
import asint.TinyASint.Or;
import asint.TinyASint.Prog;
import asint.TinyASint.And;
import asint.TinyASint.Beq;
import asint.TinyASint.Bge;
import asint.TinyASint.Bgt;
import asint.TinyASint.Ble;
import asint.TinyASint.Blt;
import asint.TinyASint.Bne;
import asint.TinyASint.Dec;
import asint.TinyASint.R_false;
import asint.TinyASint.R_true;


public interface Procesamiento {
    void procesa(Suma exp);
    void procesa(Resta exp);
    void procesa(Mul exp);
    void procesa(Div exp);
    void procesa(Id exp);
    void procesa(Num exp);
    void procesa(Dec dec);
	void procesa(Menos_unario menos_unario);
	void procesa(Not not);
	void procesa(Beq beq);
	void procesa(Bne bne);
	void procesa(Bge bge);
	void procesa(Ble ble);
	void procesa(Blt blt);
	void procesa(Bgt bgt);
	void procesa(And and);
	void procesa(Or or);
	void procesa(R_true r_true);
	void procesa(R_false r_false);
	void procesa(LDecs_una lDecs_una);
	void procesa(LDecs_muchas lDecs_muchas);
	void procesa(Lista_inst_una lista_inst_una);
	void procesa(Lista_inst_muchas lista_inst_muchas);
	void procesa(Prog prog);
	void procesa(Inst inst);
	void procesa(Tipo_int tipo_int);
	void procesa(Tipo_real tipo_real);
	void procesa(Tipo_bool tipo_bool);    
}