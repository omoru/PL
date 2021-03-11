package alex;

public class ALexOperations {
  private AnalizadorLexicoTiny alex;
  
  public ALexOperations(AnalizadorLexicoTiny alex) {
	  this.alex = alex;   
  }
  
  //PALABRAS RESERVADAS
  
  public UnidadLexica unidadR_int() {return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_INT);}
  public UnidadLexica unidadR_real () {return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_REAL);}
  public UnidadLexica unidadR_bool() {return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_BOOL);}
  public UnidadLexica unidadR_string() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_STRING);}
  public UnidadLexica unidadR_true() {return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_TRUE);}
  public UnidadLexica unidadR_false() {return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_FALSE);}
  public UnidadLexica unidadR_and() {return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_AND);}
  public UnidadLexica unidadR_or() {return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_OR);}
  public UnidadLexica unidadR_not() {return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_NOT);}
  public UnidadLexica unidadR_null() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_NULL);}
  public UnidadLexica unidadR_proc() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_PROC);}
  public UnidadLexica unidadR_if() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_IF);}
  public UnidadLexica unidadR_then() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_THEN);}
  public UnidadLexica unidadR_else() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_ELSE);}
  public UnidadLexica unidadR_endif() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_ENDIF);}
  public UnidadLexica unidadR_while() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_WHILE);}
  public UnidadLexica unidadR_do() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_DO);}
  public UnidadLexica unidadR_endwhile() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_ENDWHILE);}
  public UnidadLexica unidadR_call() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_CALL);}
  public UnidadLexica unidadR_record() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_RECORD);}
  public UnidadLexica unidadR_array() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_ARRAY);}
  public UnidadLexica unidadR_of() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_OF);}
  public UnidadLexica unidadR_pointer() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_POINTER);}
  public UnidadLexica unidadR_new() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_NEW);}
  public UnidadLexica unidadR_delete() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_DELETE);}
  public UnidadLexica unidadR_read() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_READ);}
  public UnidadLexica unidadR_write() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_WRITE);}
  public UnidadLexica unidadR_nl() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_NL);}
  public UnidadLexica unidadR_var() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_VAR);}
  public UnidadLexica unidadR_type() { return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.R_TYPE);}



  //MULTIVALUADAS
   public UnidadLexica unidadEnt() {
     return new UnidadLexicaMultivaluada(alex.fila(),alex.columna(),ClaseLexica.ENT,alex.lexema());     
   }  
   public UnidadLexica unidadId() {
	   return new UnidadLexicaMultivaluada(alex.fila(),alex.columna(),ClaseLexica.IDEN,alex.lexema());     
   }  
   public UnidadLexica unidadReal() {
     return new UnidadLexicaMultivaluada(alex.fila(),alex.columna(),ClaseLexica.REAL,alex.lexema());     
   }
   
   public UnidadLexica unidadCadena() {
	   return new UnidadLexicaMultivaluada(alex.fila(),alex.columna(),ClaseLexica.CAD,alex.lexema()); 
   }
   
   //UNIVALUADAS
   public UnidadLexica unidadSeparadorDI() {
	   return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.SEP_DI);
   }
   public UnidadLexica unidadMas() {
     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.MAS);     
   }    
   public UnidadLexica unidadMenos() {
     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.MENOS);     
   }    
   public UnidadLexica unidadPor() {
     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.POR);     
   }    
   public UnidadLexica unidadDiv() {
     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.DIV);     
   }
   public UnidadLexica unidadBEQ() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.BEQ);     
   } 
   public UnidadLexica unidadBLT() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.BLT);     
   } 
   public UnidadLexica unidadBLE() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.BLE);     
   }
   public UnidadLexica unidadBGT() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.BGT);     
   }
   public UnidadLexica unidadBGE() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.BGE);     
   }
   public UnidadLexica unidadBNE() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.BNE);     
   }
   public UnidadLexica unidadIgual() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.IGUAL);     
   }
   public UnidadLexica unidadPAp() {
     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.PAP);     
   }    
   public UnidadLexica unidadPCierre() {
     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.PCIERRE);     
   }    
   public UnidadLexica unidadSemicolon() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.SEMICOLON);     
   }
   public UnidadLexica unidadMod() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.MOD);     
   }
   public UnidadLexica unidadCorchete_ap() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.CAP);     
   }
   public UnidadLexica unidadCorcheteCierre() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.CCIERRE);     
   }
   public UnidadLexica unidadLlave_ap() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.LLAP);     
   }
   public UnidadLexica unidadLlave_ci() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.LLCIERRE);     
   }
   public UnidadLexica unidadPunto() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.PUNTO);     
   }
   public UnidadLexica unidadFlecha() {
	     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.FLECHA);     
   }
   public UnidadLexica unidadComa() {
     return new UnidadLexicaUnivaluada(alex.fila(),alex.columna(),ClaseLexica.COMA);     
   }
   public UnidadLexica unidadAmp() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AMP); 
   }
   public UnidadLexica unidadEof() {
	     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EOF); 
   }
      
   public void error() {
     System.err.println("("+filaActual+','+columnaActual+"):Caracter inexperado :"+ this.lex);  
     System.exit(1);
   }
}
