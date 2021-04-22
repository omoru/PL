/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asint;

import alex.UnidadLexica;
import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import errors.GestionErroresTiny;
import java.io.IOException;
import java.io.Reader;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class AnalizadorSintacticoTiny {
	private UnidadLexica anticipo;
	private AnalizadorLexicoTiny alex;
	private GestionErroresTiny errores;
	public AnalizadorSintacticoTiny(Reader input) throws IOException {
		errores = new GestionErroresTiny();
		alex = new AnalizadorLexicoTiny(input);
		alex.fijaGestionErrores(errores);
		sigToken();
	}
	public void Sp() {
		PROGRAMA();
		empareja(ClaseLexica.EOF);
	}

	private void PROGRAMA() {
		switch(anticipo.clase()) {
		case R_BOOL: case R_INT: case R_REAL:          
			LDECs();
			empareja(ClaseLexica.SEP_DI);
			LINST();
			break;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),ClaseLexica.R_INT,
				ClaseLexica.R_REAL,ClaseLexica.R_BOOL);                                            
		}
	}

	private void LDECs() {
		switch(anticipo.clase()) {
		case R_BOOL: case R_INT: case R_REAL:
			DEC();
			RLDECs();
			break;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),ClaseLexica.R_INT,
				ClaseLexica.R_REAL,ClaseLexica.R_BOOL);     
		} 
	}

	private void RLDECs() {
		switch(anticipo.clase()) {
		case SEMICOLON:    
			empareja(ClaseLexica.SEMICOLON);
			DEC();
			RLDECs();
			break;
		case SEP_DI:break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.SEMICOLON,ClaseLexica.SEP_DI);                                       
		}          
	}   

	private void DEC() {
		switch(anticipo.clase()) {
		case R_BOOL: case R_INT: case R_REAL:  
			NOMBRE_TIPO();
			empareja(ClaseLexica.IDEN);
			break;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),ClaseLexica.R_INT,
				ClaseLexica.R_REAL,ClaseLexica.R_BOOL);                           
		}          
	}   

	private void NOMBRE_TIPO() {
		switch(anticipo.clase()) {
		case R_BOOL:  empareja(ClaseLexica.R_BOOL);break;
		case R_INT:   empareja(ClaseLexica.R_INT);break;
		case R_REAL:  empareja(ClaseLexica.R_REAL);break;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),ClaseLexica.R_INT,
				ClaseLexica.R_REAL,ClaseLexica.R_BOOL);                                  
		}          
	}   

	private void LINST() {
		switch(anticipo.clase()) {
		case IDEN:    
			INST();
			RLINST();
			break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN);                                       
		}

	}    

	private void RLINST() {
		switch(anticipo.clase()) {       
		case SEMICOLON:   
			empareja(ClaseLexica.SEMICOLON);
			INST();
			RLINST();
			break;
		case EOF:break;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.SEMICOLON,ClaseLexica.EOF);                                       
		}
	}

	private void INST() {
		switch(anticipo.clase()) {       
		case IDEN:   
			empareja(ClaseLexica.IDEN);
			empareja(ClaseLexica.IGUAL);
			E0();
			break;
		default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN);                                       
		}
	}
	private void E0() {
		switch(anticipo.clase()) {
		case ENT: case R_FALSE: case IDEN: case MENOS: case R_NOT: case PAP: case REAL: case R_TRUE:
			E1();
			RES0();
			break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN,ClaseLexica.ENT,
				ClaseLexica.REAL, ClaseLexica.PAP,ClaseLexica.R_NOT,ClaseLexica.MENOS,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE);                                    
		}  
	}
	private void RES0() {
		switch(anticipo.clase()) {
		case MAS: 
			empareja(ClaseLexica.MAS);
			E0();
			break;
		case MENOS: 
			empareja(ClaseLexica.MENOS);
			E1();
			break;
		case PCIERRE: case SEMICOLON: case EOF: break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.MAS,ClaseLexica.MENOS);                                              
		} 
	}
	private void E1() {
		switch(anticipo.clase()) {
		case ENT: case R_FALSE: case IDEN: case MENOS: case R_NOT: case PAP: case REAL: case R_TRUE:
			E2();
			RES1();
			break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN,ClaseLexica.ENT,
				ClaseLexica.REAL, ClaseLexica.PAP,ClaseLexica.R_NOT,ClaseLexica.MENOS,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE);                                                                      
		}  
	}
	private void RES1() {
		switch(anticipo.clase()) {
		case R_AND: case R_OR: 
			Op1AI();
			E2();
			RES1();
			break;
		case MAS: case MENOS: case PCIERRE: case SEMICOLON: case EOF:break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.R_AND,ClaseLexica.R_OR,
					ClaseLexica.MAS, ClaseLexica.MENOS);                                              
		} 
	}
	private void E2() {
		switch(anticipo.clase()) {
		case ENT: case R_FALSE : case IDEN: case MENOS: case R_NOT: case PAP: case REAL: case R_TRUE:
			E3();
			RES2();
			break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN,ClaseLexica.ENT,
				ClaseLexica.REAL, ClaseLexica.PAP,ClaseLexica.R_NOT,ClaseLexica.MENOS,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE); 
		}   
	}

	private void RES2() {
		switch(anticipo.clase()) {
		case BEQ:  case BNE:    case BLT:    case BLE: case BGE:    case BGT:
			Op2AI();
			E3();
			RES2();
			break;
		case R_AND: case R_OR:    case MAS:    case MENOS:    case PCIERRE:    case SEMICOLON:    case EOF:
			break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.BEQ,ClaseLexica.BNE,ClaseLexica.BLT,ClaseLexica.BLE,ClaseLexica.BGE,ClaseLexica.BGT,ClaseLexica.R_AND,
					ClaseLexica.R_OR,ClaseLexica.MAS,ClaseLexica.MENOS);
		}  
	}
	private void E3() {
		switch(anticipo.clase()) {
		case ENT: case R_FALSE : case IDEN: case MENOS: case R_NOT: case PAP: case REAL: case R_TRUE:
			E4();
			RES3();
			break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN,ClaseLexica.ENT,
				ClaseLexica.REAL, ClaseLexica.PAP,ClaseLexica.R_NOT,ClaseLexica.MENOS,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE); 
		}  
	}
	private void RES3() {
		switch(anticipo.clase()) {
		case DIV: case POR:
			Op3NA();
			E4();
			break;
		case R_AND: case BEQ : case BGE: case BGT: case BLE: case BNE: case BLT: case MAS:
		case MENOS: case R_OR : case PCIERRE: case SEMICOLON: case EOF:
			break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.DIV,ClaseLexica.POR,ClaseLexica.R_AND,ClaseLexica.BEQ,ClaseLexica.BGE,ClaseLexica.BGT,ClaseLexica.BLE,
					ClaseLexica.BNE,ClaseLexica.BLT,ClaseLexica.MAS,ClaseLexica.MENOS,ClaseLexica.R_OR);
		}  
	}
	private void E4() {
		switch(anticipo.clase()) {
		case MENOS:
			empareja(ClaseLexica.MENOS);
			E5();
			break;
		case R_NOT:
			empareja(ClaseLexica.R_NOT);
			E4();
			break;
		case ENT: case R_FALSE : case IDEN: case PAP: case REAL: case R_TRUE:
			E5();
			break;
		default:  errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
				ClaseLexica.IDEN,ClaseLexica.ENT,
				ClaseLexica.REAL, ClaseLexica.PAP,ClaseLexica.R_NOT,ClaseLexica.MENOS,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE); 
		}  
	}
	private void E5() {
		switch(anticipo.clase()) {
		case ENT:  empareja(ClaseLexica.ENT);break;
		case REAL:   empareja(ClaseLexica.REAL);break;
		case IDEN:  empareja(ClaseLexica.IDEN);break;
		case R_FALSE:  empareja(ClaseLexica.R_FALSE);break;
		case R_TRUE:   empareja(ClaseLexica.R_TRUE);break;
		case PAP:  
			empareja(ClaseLexica.PAP);
			E0();
			empareja(ClaseLexica.PCIERRE);
			break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.ENT,ClaseLexica.REAL,ClaseLexica.IDEN,ClaseLexica.PAP,ClaseLexica.R_FALSE,ClaseLexica.R_TRUE);
		}  
	}

	private void Op3NA() {
		switch(anticipo.clase()) {
		case POR:  empareja(ClaseLexica.POR);break;
		case DIV:   empareja(ClaseLexica.DIV);break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.POR,ClaseLexica.DIV);
		}  
	}

	private void Op2AI() {
		switch(anticipo.clase()) {
		case BNE:  empareja(ClaseLexica.BNE);break;
		case BEQ:   empareja(ClaseLexica.BEQ);break;
		case BLE:  empareja(ClaseLexica.BLE);break;
		case BGE:   empareja(ClaseLexica.BGE);break;
		case BLT:  empareja(ClaseLexica.BLT);break;
		case BGT:   empareja(ClaseLexica.BGT);break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.BEQ,ClaseLexica.BNE,ClaseLexica.BLT,ClaseLexica.BLE,ClaseLexica.BGE,ClaseLexica.BGT);
		}  
	}

	private void Op1AI() {
		switch(anticipo.clase()) {
		case R_AND:  empareja(ClaseLexica.R_AND);break;
		case R_OR:   empareja(ClaseLexica.R_OR);break;
		default:    
			errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
					ClaseLexica.R_AND,ClaseLexica.R_OR);
		}  
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

}
