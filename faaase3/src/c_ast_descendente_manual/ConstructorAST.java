package c_ast_descendente_manual;

import java.io.IOException;
import java.io.Reader;

import asint.TinyASint.Dec;
import asint.TinyASint.Exp;
import asint.TinyASint.Inst;
import asint.TinyASint.LDecs;
import asint.TinyASint.LInst;
import asint.TinyASint.Prog;
import asint.TinyASint.StringLocalizado;
import asint.TinyASint.Tipo;
import semops.SemOps;

public class ConstructorAST {
	
	private UnidadLexica anticipo;
	private AnalizadorLexicoTiny alex;
	private GestionErroresTiny errores;
	private SemOps sem;
	
	public ConstructorAST(Reader input) throws IOException {
		 errores = new GestionErroresTiny();
	     alex = new AnalizadorLexicoTiny(input,errores);
	     sigToken();
	     sem = new SemOps();
	}

	public Prog Init() {
		Prog prog = PROGRAMA();
		empareja(ClaseLexica.EOF);
		return prog;
	}
		
	private Prog PROGRAMA() {
		switch(anticipo.clase()) {
			case R_BOOL: case R_INT: case R_REAL:          
				LDecs ldecs = LDECs();
				empareja(ClaseLexica.SEP_DI);
				LInst linst = LINST();
				return sem.programa(ldecs,linst);
			default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),ClaseLexica.R_INT,
					ClaseLexica.R_REAL,ClaseLexica.R_BOOL);                                            
			}
		return null;
	}

	private LDecs LDECs() {
		switch(anticipo.clase()) {
		case R_BOOL: case R_INT: case R_REAL:
			Dec dec = DEC();
			return RLDECs(sem.lista_dec_una(dec));
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),ClaseLexica.R_INT,
				ClaseLexica.R_REAL,ClaseLexica.R_BOOL);     
		}
		return null; 
	}

	private LDecs RLDECs(LDecs lDecs_h) {
		switch(anticipo.clase()) {
		case SEMICOLON:    
			empareja(ClaseLexica.SEMICOLON);
			Dec dec = DEC();
			return RLDECs(sem.lista_dec_muchas(lDecs_h, dec));
		case SEP_DI:return lDecs_h;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.SEMICOLON,ClaseLexica.SEP_DI);                                       
		}
		return null;          
	}   

	private Dec DEC() {
		switch(anticipo.clase()) {
		case R_BOOL: case R_INT: case R_REAL:  
			Tipo tipo = NOMBRE_TIPO();
			StringLocalizado aux = str(this.anticipo.toString(),this.anticipo.fila(),
					this.anticipo.columna()); 
			empareja(ClaseLexica.IDEN);
			return sem.dec(tipo,aux);
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),ClaseLexica.R_INT,
				ClaseLexica.R_REAL,ClaseLexica.R_BOOL);                           
		}
		return null;          
	}   
	
	
	private Tipo NOMBRE_TIPO() {
		switch(anticipo.clase()) {
		case R_BOOL:  empareja(ClaseLexica.R_BOOL);
					  return sem.tipo_bool();
		case R_INT:   empareja(ClaseLexica.R_INT);
					  return sem.tipo_Entero();
		case R_REAL:  empareja(ClaseLexica.R_REAL);
					  return sem.tipo_real();
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),ClaseLexica.R_INT,
				ClaseLexica.R_REAL,ClaseLexica.R_BOOL);                                  
		}
		return null;          
	}   

	private LInst LINST() {
		switch(anticipo.clase()) {
		case IDEN:    
			Inst inst = INST();
			return RLINST(sem.lista_inst_una(inst));
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN);                                       
		}
		return null;

	}   
	
	private LInst RLINST(LInst lInst_h) {
		switch(anticipo.clase()) {       
		case SEMICOLON:   
			empareja(ClaseLexica.SEMICOLON);
			Inst inst = INST();
			return RLINST(sem.lista_inst_muchas(lInst_h, inst));
		case EOF:return lInst_h;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.SEMICOLON,ClaseLexica.EOF);                                       
		}
		return lInst_h;
	}

	private Inst INST() {
		switch(anticipo.clase()) {       
		case IDEN:   
			StringLocalizado aux = str(this.anticipo.toString(),
					this.anticipo.fila(),this.anticipo.columna());
			empareja(ClaseLexica.IDEN);
			empareja(ClaseLexica.IGUAL);
			Exp exp = E0();
			return sem.inst(aux,exp);
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN);                                       
		}
		return null;
	}
	
	private Exp E0() {
		switch(anticipo.clase()) {
		case ENT: case R_FALSE: case IDEN: case MENOS: case R_NOT: case PAP: case REAL: case R_TRUE:
			Exp e1 = E1();
			return RES0(e1);
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN,ClaseLexica.ENT,
				ClaseLexica.REAL, ClaseLexica.PAP,ClaseLexica.R_NOT,ClaseLexica.MENOS,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE);                                    
		}
		return null;  
	}
	private Exp RES0(Exp exp_h) {
		switch(anticipo.clase()) {
		case MAS: 
			empareja(ClaseLexica.MAS);
			Exp exp_0 = E0();
			return RES0(sem.exp("+", exp_h, exp_0));
		case MENOS: 
			empareja(ClaseLexica.MENOS);
			Exp exp_1 = E1();
			return RES0(sem.exp("+", exp_h, exp_1));
		case PCIERRE: case SEMICOLON: case EOF: return exp_h;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.MAS,ClaseLexica.MENOS);                                              
		}
		return null; 
	}
	private Exp E1() {
		switch(anticipo.clase()) {
			case ENT: case R_FALSE: case IDEN: case MENOS: case R_NOT: case PAP: case REAL: case R_TRUE:
				Exp exp_2 = E2();
				return RES1(exp_2);
			default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.IDEN,ClaseLexica.ENT,
					ClaseLexica.REAL, ClaseLexica.PAP,ClaseLexica.R_NOT,ClaseLexica.MENOS,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE);                                                                      
			}
		return null;  
	}
	private Exp RES1(Exp exp_h) {
		switch(anticipo.clase()) {
			case R_AND: case R_OR: 
				String op = Op1AI();
				Exp exp_2 = E2();
				return RES1(sem.exp(op, exp_h, exp_2));
			case MAS: case MENOS: case PCIERRE: case SEMICOLON: case EOF:return exp_h;
			default:    
				errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
						ClaseLexica.R_AND,ClaseLexica.R_OR,
						ClaseLexica.MAS, ClaseLexica.MENOS);                                              
			}
		return null; 
	}
	private Exp E2() {
		switch(anticipo.clase()) {
		case ENT: case R_FALSE : case IDEN: case MENOS: case R_NOT: case PAP: case REAL: case R_TRUE:
			Exp exp_3 = E3();
			return RES2(exp_3);
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN,ClaseLexica.ENT,
				ClaseLexica.REAL, ClaseLexica.PAP,ClaseLexica.R_NOT,ClaseLexica.MENOS,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE); 
		}
		return null;   
	}

	private Exp RES2(Exp exp_h) {
		switch(anticipo.clase()) {
		case BEQ:  case BNE:    case BLT:    case BLE: case BGE:    case BGT:
			String op = Op2AI();
			Exp exp_3= E3();
			return RES2(sem.exp(op, exp_h, exp_3));
		case R_AND: case R_OR:    case MAS:    case MENOS:    case PCIERRE:    case SEMICOLON:    case EOF:
			return exp_h;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.BEQ,ClaseLexica.BNE,ClaseLexica.BLT,ClaseLexica.BLE,ClaseLexica.BGE,ClaseLexica.BGT,ClaseLexica.R_AND,
					ClaseLexica.R_OR,ClaseLexica.MAS,ClaseLexica.MENOS);
		}
		return null;  
	}
	private Exp E3() {
		switch(anticipo.clase()) {
		case ENT: case R_FALSE : case IDEN: case MENOS: case R_NOT: case PAP: case REAL: case R_TRUE:
			Exp exp_4 = E4();
			return RES3(exp_4);
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN,ClaseLexica.ENT,
				ClaseLexica.REAL, ClaseLexica.PAP,ClaseLexica.R_NOT,ClaseLexica.MENOS,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE); 
		}
		return null;  
	}
	private Exp RES3(Exp exp_h) {
		switch(anticipo.clase()) {
		case DIV: case POR:
			String op = Op3NA();
			Exp exp_4 = E4();
			return sem.exp(op, exp_h, exp_4);
		case R_AND: case BEQ : case BGE: case BGT: case BLE: case BNE: case BLT: case MAS:
		case MENOS: case R_OR : case PCIERRE: case SEMICOLON: case EOF:
			return exp_h;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.DIV,ClaseLexica.POR,ClaseLexica.R_AND,ClaseLexica.BEQ,ClaseLexica.BGE,ClaseLexica.BGT,ClaseLexica.BLE,
					ClaseLexica.BNE,ClaseLexica.BLT,ClaseLexica.MAS,ClaseLexica.MENOS,ClaseLexica.R_OR);
		}
		return null;  
	}
	private Exp E4() {
		switch(anticipo.clase()) {
		case MENOS:
			empareja(ClaseLexica.MENOS);
			Exp exp_5 = E5();
			return sem.menos_unario(exp_5);
		case R_NOT:
			empareja(ClaseLexica.R_NOT);
			Exp exp_4 = E4();
			return sem.not(exp_4);
		case ENT: case R_FALSE : case IDEN: case PAP: case REAL: case R_TRUE:
			return E5();
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN,ClaseLexica.ENT,
				ClaseLexica.REAL, ClaseLexica.PAP,ClaseLexica.R_NOT,ClaseLexica.MENOS,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE); 
		}
		return null;  
	}
	private Exp E5() {
		StringLocalizado str = str(this.anticipo.toString(), this.anticipo.fila(),
				this.anticipo.columna());
		switch(anticipo.clase()) {
		case ENT:  empareja(ClaseLexica.ENT);
				   return sem.num_ent(str);
		case REAL:   empareja(ClaseLexica.REAL);
					 return sem.num_real(str);
		case IDEN:  empareja(ClaseLexica.IDEN);
					return sem.identificador(str);
		case R_FALSE:  empareja(ClaseLexica.R_FALSE);
						return sem.r_false();
		case R_TRUE:   empareja(ClaseLexica.R_TRUE);
						return sem.r_true();
		case PAP:  
			empareja(ClaseLexica.PAP);
			Exp exp_0= E0();
			empareja(ClaseLexica.PCIERRE);
			return exp_0;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.ENT,ClaseLexica.REAL,ClaseLexica.IDEN,ClaseLexica.PAP,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE);
		}
		return null;  
	}


	
	
	private String Op3NA() {
		switch(anticipo.clase()) {
		case POR:  empareja(ClaseLexica.POR);
					return "*";
		case DIV:   empareja(ClaseLexica.DIV);
					return "/";
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.POR,ClaseLexica.DIV);
		}
		return null;  
	}

	private String Op2AI() {
		switch(anticipo.clase()) {
		case BNE:  empareja(ClaseLexica.BNE);return "!=";
		case BEQ:   empareja(ClaseLexica.BEQ);return "==";
		case BLE:  empareja(ClaseLexica.BLE);return "<=";
		case BGE:   empareja(ClaseLexica.BGE);return ">=";
		case BLT:  empareja(ClaseLexica.BLT);return "<";
		case BGT:   empareja(ClaseLexica.BGT);return ">";
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.BEQ,ClaseLexica.BNE,ClaseLexica.BLT,ClaseLexica.BLE,ClaseLexica.BGE,ClaseLexica.BGT);
		}
		return null;  
	}

	private String Op1AI() {
		switch(anticipo.clase()) {
		case R_AND:  empareja(ClaseLexica.R_AND);return "and";
		case R_OR:   empareja(ClaseLexica.R_OR);return "or";
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.R_AND,ClaseLexica.R_OR);
		}
		return null;  
	}
	  
	private void empareja(ClaseLexica claseEsperada) {
	      if (anticipo.clase() == claseEsperada)
	          sigToken();
	      else errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),claseEsperada);
	}
	
	private void sigToken() {
	      try {
	        anticipo = alex.sigToken(); 
	      }
	      catch(IOException e) {
	        errores.errorFatal(e);
	      }
	   }
	  public StringLocalizado str(String s, int fila, int col) {
	        return new StringLocalizado(s,fila,col);
	    }
}
