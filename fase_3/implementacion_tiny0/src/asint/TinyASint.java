package asint;

public class TinyASint {

	public static abstract class Exp {
		public Exp() {
		}

		public abstract int prioridad();

		public abstract void procesa(Procesamiento procesamiento);
	}

	public static class StringLocalizado {
		private String s;
		private int fila;
		private int col;

		public StringLocalizado(String s, int fila, int col) {
			this.s = s;
			this.fila = fila;
			this.col = col;
		}

		public int fila() {
			return fila;
		}

		public int col() {
			return col;
		}

		public String toString() {
			return s;
		}

		public boolean equals(Object o) {
			return (o == this) || ((o instanceof StringLocalizado) && (((StringLocalizado) o).s.equals(s)));
		}

		public int hashCode() {
			return s.hashCode();
		}
	}

	private static abstract class ExpBin extends Exp {
		private Exp arg0;
		private Exp arg1;

		public Exp arg0() {
			return arg0;
		}

		public Exp arg1() {
			return arg1;
		}

		public ExpBin(Exp arg0, Exp arg1) {
			super();
			this.arg0 = arg0;
			this.arg1 = arg1;
		}
	}

	private static abstract class ExpAditiva extends ExpBin {
		public ExpAditiva(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public final int prioridad() {
			return 0;
		}
	}

	public static class Suma extends ExpAditiva {
		public Suma(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Resta extends ExpAditiva {
		public Resta(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	private static abstract class ExpLogica extends ExpBin {
		public ExpLogica(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public final int prioridad() {
			return 1;
		}
	}

	public static class And extends ExpLogica {
		public And(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Or extends ExpLogica {
		public Or(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	private static abstract class ExpComparativa extends ExpBin {
		public ExpComparativa(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public final int prioridad() {
			return 2;
		}
	}

	public static class Beq extends ExpComparativa {
		public Beq(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Bne extends ExpComparativa {
		public Bne(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Bge extends ExpComparativa {
		public Bge(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Ble extends ExpComparativa {
		public Ble(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Blt extends ExpComparativa {
		public Blt(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Bgt extends ExpComparativa {
		public Bgt(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	private static abstract class ExpMultiplicativa extends ExpBin {
		public ExpMultiplicativa(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public final int prioridad() {
			return 3;
		}
	}

	public static class Mul extends ExpMultiplicativa {
		public Mul(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Div extends ExpMultiplicativa {
		public Div(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	private static abstract class ExpUnaria extends Exp {
		private Exp arg0;

		public Exp arg0() {
			return arg0;
		}

		public ExpUnaria(Exp arg0) {
			super();
			this.arg0 = arg0;
		}

		public final int prioridad() {
			return 4;
		}
	}

	public static class Menos_unario extends ExpUnaria {
		public Menos_unario(Exp arg0) {
			super(arg0);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Not extends ExpUnaria {
		public Not(Exp arg0) {
			super(arg0);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class Num extends Exp {
		private StringLocalizado num;

		public Num(StringLocalizado num) {
			super();
			this.num = num;
		}

		public final int prioridad() {
			return 5;
		}

		public StringLocalizado num() {
			return num;
		}
	}

	public static class NumEnt extends Num {
		public NumEnt(StringLocalizado num) {
			super(num);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class NumReal extends Num {
		public NumReal(StringLocalizado num) {
			super(num);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Id extends Exp {
		private StringLocalizado id;

		public Id(StringLocalizado id) {
			super();
			this.id = id;
		}

		public StringLocalizado id() {
			return id;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		public final int prioridad() {
			return 5;
		}
	}

	public static class R_true extends Exp {
		public R_true() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		public final int prioridad() {
			return 5;
		}
	}

	public static class R_false extends Exp {
		public R_false() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		public final int prioridad() {
			return 5;
		}
	}

	public static abstract class LDecs {
		public LDecs() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class LDecs_una extends LDecs {
		private Dec dec;

		public LDecs_una(Dec dec) {
			super();
			this.dec = dec;
		}

		public Dec dec() {
			return dec;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class LDecs_muchas extends LDecs {
		private Dec dec;
		private LDecs ldecs;

		public LDecs_muchas(LDecs ldecs, Dec dec) {
			super();
			this.dec = dec;
			this.ldecs = ldecs;
		}

		public Dec dec() {
			return dec;
		}

		public LDecs ldecs() {
			return ldecs;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class LInst {
		public LInst() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Lista_inst_una extends LInst {
		private Inst inst;

		public Lista_inst_una(Inst inst) {
			super();
			this.inst = inst;
		}

		public Inst inst() {
			return inst;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Lista_inst_muchas extends LInst {
		private Inst inst;
		private LInst linst;

		public Lista_inst_muchas(LInst linst, Inst inst) {
			super();
			this.inst = inst;
			this.linst = linst;
		}

		public Inst inst() {
			return inst;
		}

		public LInst linst() {
			return linst;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Prog {
		private LInst linst;
		private LDecs ldecs;

		public Prog(LDecs ldecs, LInst linst) {
			this.linst = linst;
			this.ldecs = ldecs;
		}

		public LInst linst() {
			return linst;
		}

		public LDecs ldecs() {
			return ldecs;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Inst {
		private StringLocalizado id;
		private Exp exp;

		public Inst(StringLocalizado id, Exp exp) {
			this.id = id;
			this.exp = exp;
		}

		public StringLocalizado id() {
			return id;
		}

		public Exp exp() {
			return exp;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class Tipo {
		public Tipo() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Tipo_int extends Tipo {
		public Tipo_int() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Tipo_real extends Tipo {
		public Tipo_real() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Tipo_bool extends Tipo {
		public Tipo_bool() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Dec {
		private StringLocalizado id;
		private Tipo tipo;

		public Dec(Tipo tipo, StringLocalizado id) {
			this.id = id;
			this.tipo = tipo;
		}

		public StringLocalizado id() {
			return id;
		}

		public Tipo tipo() {
			return tipo;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	// Constructoras
	public Prog programa(LDecs Ldecs, LInst Linst) {
		return new Prog(Ldecs, Linst);
	}

	public LDecs lista_dec_una(Dec dec) {
		return new LDecs_una(dec);
	}

	public LDecs lista_dec_muchas(LDecs ldecs, Dec dec) {
		return new LDecs_muchas(ldecs, dec);
	}

	public Tipo tipo_Entero() {
		return new Tipo_int();
	}

	public Tipo tipo_real() {
		return new Tipo_real();
	}

	public Tipo tipo_bool() {
		return new Tipo_bool();
	}

	public Dec dec(Tipo tipo, StringLocalizado id) {
		return new Dec(tipo, id);
	}

	public LInst lista_inst_una(Inst inst) {
		return new Lista_inst_una(inst);
	}

	public LInst lista_inst_muchas(LInst linst, Inst inst) {
		return new Lista_inst_muchas(linst, inst);
	}

	public Inst inst(StringLocalizado id, Exp exp) {
		return new Inst(id, exp);
	}

	public Exp suma(Exp arg0, Exp arg1) {
		return new Suma(arg0, arg1);
	}

	public Exp resta(Exp arg0, Exp arg1) {
		return new Resta(arg0, arg1);
	}

	public Exp mul(Exp arg0, Exp arg1) {
		return new Mul(arg0, arg1);
	}

	public Exp div(Exp arg0, Exp arg1) {
		return new Div(arg0, arg1);
	}

	public Exp beq(Exp arg0, Exp arg1) {
		return new Beq(arg0, arg1);
	}

	public Exp bne(Exp arg0, Exp arg1) {
		return new Bne(arg0, arg1);
	}

	public Exp ble(Exp arg0, Exp arg1) {
		return new Ble(arg0, arg1);
	}

	public Exp bge(Exp arg0, Exp arg1) {
		return new Bge(arg0, arg1);
	}

	public Exp bgt(Exp arg0, Exp arg1) {
		return new Bgt(arg0, arg1);
	}

	public Exp blt(Exp arg0, Exp arg1) {
		return new Blt(arg0, arg1);
	}

	public Exp and(Exp arg0, Exp arg1) {
		return new And(arg0, arg1);
	}

	public Exp or(Exp arg0, Exp arg1) {
		return new Or(arg0, arg1);
	}

	public Exp not(Exp exp) {
		return new Not(exp);
	}

	public Exp menos_unario(Exp exp) {
		return new Menos_unario(exp);
	}

	public Exp num_real(StringLocalizado num) {
		return new NumReal(num);
	}

	public Exp num_ent(StringLocalizado num) {
		return new NumEnt(num);
	}

	public Exp identificador(StringLocalizado id) {
		return new Id(id);
	}

	public Exp r_false() {
		return new R_false();
	}

	public Exp r_true() {
		return new R_true();
	}

}
