package semops;

import asint.TinyASint;

public class SemOps extends TinyASint {
   public Exp exp(String op, Exp arg0, Exp arg1) {
       switch(op) {
	       case "+":     return suma(arg0,arg1);
	       case "-":     return resta(arg0,arg1);
	       case "*":     return mul(arg0,arg1);
	       case "/":     return div(arg0,arg1) ;
	       case "==":     return beq(arg0,arg1);
	       case "<=":     return ble(arg0,arg1);
	       case ">=":     return bge(arg0,arg1);
	       case "!=":     return bne(arg0,arg1) ;
	       case "<":     return blt(arg0,arg1);
	       case ">":     return bgt(arg0,arg1);
	       case "and":     return and(arg0,arg1);
	       case "or":     return or(arg0,arg1);
       }
       throw new UnsupportedOperationException("exp "+op);
   }

public Dec switch_tipo(Tipo tipo) {
	// TODO Auto-generated method stub
	return null;
}  

   
}
