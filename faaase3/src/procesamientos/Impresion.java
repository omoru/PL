package procesamientos;

import asint.ProcesamientoPorDefecto;
import asint.TinyASint.And;
import asint.TinyASint.Beq;
import asint.TinyASint.Bge;
import asint.TinyASint.Bgt;
import asint.TinyASint.Ble;
import asint.TinyASint.Blt;
import asint.TinyASint.Bne;
import asint.TinyASint.Dec;
import asint.TinyASint.Div;
import asint.TinyASint.Exp;
import asint.TinyASint.Id;
import asint.TinyASint.Inst;
import asint.TinyASint.LDecs_muchas;
import asint.TinyASint.LDecs_una;
import asint.TinyASint.Lista_inst_muchas;
import asint.TinyASint.Lista_inst_una;
import asint.TinyASint.Menos_unario;
import asint.TinyASint.Mul;
import asint.TinyASint.Not;
import asint.TinyASint.Num;
import asint.TinyASint.Or;
import asint.TinyASint.Prog;
import asint.TinyASint.R_false;
import asint.TinyASint.R_true;
import asint.TinyASint.Resta;
import asint.TinyASint.Suma;
import asint.TinyASint.Tipo_bool;
import asint.TinyASint.Tipo_int;
import asint.TinyASint.Tipo_real;

public class Impresion extends ProcesamientoPorDefecto{
	public Impresion() {
	}
	public void procesa(Prog prog) {
		prog.ldecs().procesa(this);
		System.out.print(" ");
		System.out.println("&&");
		prog.linst().procesa(this);
		System.out.println();
	}
	
	public void procesa(LDecs_una lDecs_una) {
		lDecs_una.dec().procesa(this);
		
	}

	public void procesa(LDecs_muchas lDecs_muchas) {
		lDecs_muchas.ldecs().procesa(this);
	    System.out.println(",");
	    lDecs_muchas.dec().procesa(this);
		
	}
	
	public void procesa(Dec dec) {
		dec.tipo().procesa(this);
		System.out.print(" ");
		System.out.print(dec.id());
		
	}
	
	public void procesa(Tipo_int tipo_int) {
		System.out.print("int");
	}

	
	public void procesa(Tipo_real tipo_real) {
		System.out.print("real");
	}
	
	public void procesa(Tipo_bool tipo_bool) {
		System.out.print("bool");
	}
	
	public void procesa(Lista_inst_una lista_inst_una) {
		lista_inst_una.inst().procesa(this);
	}

	
	public void procesa(Lista_inst_muchas lista_inst_muchas) {
		lista_inst_muchas.linst().procesa(this);
		System.out.println(";");
		lista_inst_muchas.inst().procesa(this);	
	}
	
	public void procesa(Inst inst) {
		System.out.print(inst.id().toString() + " = ");
		inst.exp().procesa(this);
	}

   private void imprime_arg(Exp arg, int p) {
       if (arg.prioridad() < p) {
           System.out.print("(");
           arg.procesa(this);
           System.out.print(")");
       }
       else {
           arg.procesa(this);
       }
   }
	public void procesa(Suma exp) {
		  imprime_arg(exp.arg0(),1); 
	      System.out.print(" + ");
	      imprime_arg(exp.arg1(),0);  
	
	}

	public void procesa(Resta exp) {
		  imprime_arg(exp.arg0(),1); 
	      System.out.print(" - ");
	      imprime_arg(exp.arg1(),1);
		
	}

	
	public void procesa(Mul exp) {
		 imprime_arg(exp.arg0(),4); 
	     System.out.print(" * ");
	     imprime_arg(exp.arg1(),4);
		
	}

	public void procesa(Div exp) {
		 imprime_arg(exp.arg0(),4); 
	     System.out.print(" /" );
	     imprime_arg(exp.arg1(),4);
		
	}

	public void procesa(Id exp) {
		System.out.print(exp.id().toString());		
	}

	@Override
	public void procesa(Num exp) {
		System.out.print(exp.num().toString());	
	}

	
	public void procesa(Menos_unario menos_unario) {
		System.out.print("-");
		imprime_arg(menos_unario.arg0(),5);
	}

	public void procesa(Not not) {
		System.out.print(" not ");
		imprime_arg(not.arg0(),4);
	}

	public void procesa(Beq beq) {
		 imprime_arg(beq.arg0(),2); 
	     System.out.print(" == ");
	     imprime_arg(beq.arg1(),3);
		
	}

	public void procesa(Bne bne) {
		 imprime_arg(bne.arg0(),2); 
	     System.out.print(" != ");
	     imprime_arg(bne.arg1(),3);
		
	}

	public void procesa(Bge bge) {
		 imprime_arg(bge.arg0(),2); 
	     System.out.print(" >= ");
	     imprime_arg(bge.arg1(),3);
		
	}

	public void procesa(Ble ble) {
		 imprime_arg(ble.arg0(),2); 
	     System.out.print(" <= ");
	     imprime_arg(ble.arg1(),3);
		
	}


	public void procesa(Blt blt) {
		 imprime_arg(blt.arg0(),2); 
	     System.out.print(" < ");
	     imprime_arg(blt.arg1(),3);
		
	}


	public void procesa(Bgt bgt) {
		 imprime_arg(bgt.arg0(),2); 
	     System.out.print(" > ");
	     imprime_arg(bgt.arg1(),3);
		
	}


	public void procesa(And and) {
		 imprime_arg(and.arg0(),1); 
	     System.out.print(" and ");
	     imprime_arg(and.arg1(),2);
		
	}

	
	public void procesa(Or or) {
		imprime_arg(or.arg0(),1); 
	    System.out.print(" or ");
	    imprime_arg(or.arg1(),2);
	}

	
	public void procesa(R_true r_true) {
		System.out.print(" true ");
		
	}


	public void procesa(R_false r_false) {
		System.out.print(" true ");
	}



		
}
