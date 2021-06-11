package semops;

import asint.TinyASint;

public class SemOps extends TinyASint {
	public Exp exp(String op, Exp arg0, Exp arg1) {
		switch (op) {
		case "+":
			return suma(arg0, arg1);
		case "-":
			return resta(arg0, arg1);
		case "*":
			return mul(arg0, arg1);
		case "/":
			return div(arg0, arg1);
		case "%":
			return mod(arg0, arg1);
		case "==":
			return beq(arg0, arg1);
		case "<=":
			return ble(arg0, arg1);
		case ">=":
			return bge(arg0, arg1);
		case "!=":
			return bne(arg0, arg1);
		case "<":
			return blt(arg0, arg1);
		case ">":
			return bgt(arg0, arg1);
		case "and":
			return c_and(arg0, arg1);
		case "or":
			return c_or(arg0, arg1);
		}
		throw new UnsupportedOperationException("exp " + op);
	}

	public Prog programa(LDecs ldecs, LInst linst) {
		if (ldecs == null)
			return prog_sin_decs(linst);
		else
			return prog_con_decs(ldecs, linst);
	}

}
