package alex;
import asintAsc.ClaseLexica;
public class ALexOperations {
  private AnalizadorLexicoTiny alex;
  
  public ALexOperations(AnalizadorLexicoTiny alex) {
	  this.alex = alex;   
  }
  
  //PALABRAS RESERVADAS
  
  public UnidadLexica unidadR_int() {return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_int,"int");}
  public UnidadLexica unidadR_real () {return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_real,"real");}
  public UnidadLexica unidadR_bool() {return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_bool,"bool");}
  public UnidadLexica unidadR_string() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_string,"string");}
  public UnidadLexica unidadR_true() {return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_true,"true");}
  public UnidadLexica unidadR_false() {return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_false,"false");}
  public UnidadLexica unidadR_and() {return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.and,"and");}
  public UnidadLexica unidadR_or() {return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.or,"or");}
  public UnidadLexica unidadR_not() {return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.not,"not");}
  public UnidadLexica unidadR_null() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_null,"null");}
  public UnidadLexica unidadR_proc() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.proc,"proc");}
  public UnidadLexica unidadR_if() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_if,"if");}
  public UnidadLexica unidadR_then() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.then,"then");}
  public UnidadLexica unidadR_else() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_else,"else");}
  public UnidadLexica unidadR_endif() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.endif,"endif");}
  public UnidadLexica unidadR_while() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_while,"while");}
  public UnidadLexica unidadR_do() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_do,"do");}
  public UnidadLexica unidadR_endwhile() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.endwhile,"endwhile");}
  public UnidadLexica unidadR_call() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.call,"call");}
  public UnidadLexica unidadR_record() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.record,"record");}
  public UnidadLexica unidadR_array() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.array,"array");}
  public UnidadLexica unidadR_of() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.of,"of");}
  public UnidadLexica unidadR_pointer() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.pointer,"pointer");}
  public UnidadLexica unidadR_new() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.r_new,"new");}
  public UnidadLexica unidadR_delete() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.delete,"delete");}
  public UnidadLexica unidadR_read() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.read,"read");}
  public UnidadLexica unidadR_write() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.write,"write");}
  public UnidadLexica unidadR_nl() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.nl,"nl");}
  public UnidadLexica unidadR_var() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.var,"var");}
  public UnidadLexica unidadR_type() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.type,"type");}


  //MULTIVALUADAS
   public UnidadLexica unidadEnt() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ent,alex.lexema());     
   }  
   public UnidadLexica unidadId() {
	   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.id,alex.lexema());     
   }  
   public UnidadLexica unidadReal() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.real,alex.lexema());     
   }
   
   public UnidadLexica unidadCadena() {
	   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.cadena,alex.lexema()); 
   }
   
   //UNIVALUADAS
   public UnidadLexica unidadSeparadorDI() {
	   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.sep_di,"&&");
   }
   public UnidadLexica unidadMas() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.mas,"+");     
   }    
   public UnidadLexica unidadMenos() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.menos,"-");     
   }    
   public UnidadLexica unidadPor() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.por,"*");     
   }    
   public UnidadLexica unidadDiv() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.div,"/");     
   }
   public UnidadLexica unidadBEQ() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.beq,"==");     
   } 
   public UnidadLexica unidadBLT() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.blt,"<");     
   } 
   public UnidadLexica unidadBLE() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ble,"<=");     
   }
   public UnidadLexica unidadBGT() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.bgt,">");     
   }
   public UnidadLexica unidadBGE() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.bge,">=");     
   }
   public UnidadLexica unidadBNE() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.bne,"!=");     
   }
   public UnidadLexica unidadIgual() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.igual,"=");     
   }
   public UnidadLexica unidadPAp() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.pap,"(");     
   }    
   public UnidadLexica unidadPCierre() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.pcierre,")");     
   }    
   public UnidadLexica unidadSemicolon() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.scol,";");     
   }
   public UnidadLexica unidadMod() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.mod,"%");     
   }
   public UnidadLexica unidadCorchete_ap() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.cap,"[");     
   }
   public UnidadLexica unidadCorchete_ci() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ccierre,"]");     
   }
   public UnidadLexica unidadLlave_ap() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.llap,"{");     
   }
   public UnidadLexica unidadLlave_ci() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.llcierre,"}");     
   }
   public UnidadLexica unidadPunto() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.punto,".");     
   }
   public UnidadLexica unidadFlecha() {
	     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.flecha,"->");     
   }
   public UnidadLexica unidadComa() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.coma,",");     
   }
   public UnidadLexica unidadAmp() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.amp,"&"); 
   }
   public UnidadLexica unidadEof() {
	     return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.EOF,"<EOF>"); 
   }
      
   public void error() {
     System.err.println("("+alex.fila()+','+alex.columna()+"):Caracter inexperado :"+ alex.lexema());  
     System.exit(1);
   }
}
