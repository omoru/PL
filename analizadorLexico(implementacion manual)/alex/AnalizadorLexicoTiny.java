package alex;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexicoTiny {

   private Reader input;
   private StringBuffer lex;
   private int sigCar;
   private int filaInicio;
   private int columnaInicio;
   private int filaActual;
   private int columnaActual;
   private static String NL = System.getProperty("line.separator");
   
   private static enum Estado {
	
    INICIO,
    REC_POR,
    REC_DIV,
    REC_PAP,
    REC_PCIERR,
    REC_IGUAL,
    REC_MAS,
    REC_MENOS,
    REC_ID,
    REC_ENT,
    REC_0,
    REC_IDEC,
    REC_DEC,
    REC_EOF,
    REC_SCOLON,
    REC_BLT,
    REC_BLE,
    REC_BGT,
    REC_BGE,
    REC_IBNE,
    REC_BNE,
    REC_BEQ,
    REC_ISEP_DI,
    REC_SEP_DI
    
   
    /*REC_IEXP
     * REC_IEXP2
     * REC_EXP
     * REC_EXP0
     * 
     * */
    
   }

   private Estado estado;

   public AnalizadorLexicoTiny(Reader input) throws IOException {
    this.input = input;
    lex = new StringBuffer();
    sigCar = input.read();
    filaActual=1;
    columnaActual=1;
   }
   
   public UnidadLexica sigToken() throws IOException {
     estado = Estado.INICIO;
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     lex.delete(0,lex.length());
     while(true) {
         switch(estado) {
           case INICIO: 
              if(hayLetra())  transita(Estado.REC_ID);
              else if (hayDigitoPos()) transita(Estado.REC_ENT);
              else if (hayCero()) transita(Estado.REC_0);
              else if (haySuma()) transita(Estado.REC_MAS);
              else if (hayResta()) transita(Estado.REC_MENOS);
              else if (hayMul()) transita(Estado.REC_POR);
              else if (hayDiv()) transita(Estado.REC_DIV);
              else if (hayPAp()) transita(Estado.REC_PAP);
              else if (haySemicolon()) transita(Estado.REC_SCOLON);
              else if (hayPCierre()) transita(Estado.REC_PCIERR);
              else if (hayIgual()) transita(Estado.REC_IGUAL);
              else if (haySep()) transitaIgnorando(Estado.INICIO); //ESPACIO EN VLANCO, TABULADOR O SALTO DE LINEA
              else if(hayMenor()) transita(Estado.REC_BLT);
              else if(hayMayor()) transita(Estado.REC_BGT);
              else if(hayExclamacion()) transita(Estado.REC_IBNE);   
              else if(hayAmpersand()) transita(Estado.REC_ISEP_DI);
              else if (hayEOF()) transita(Estado.REC_EOF);
              else error();
              break;
           case REC_ID: 
              if (hayLetra() || hayDigito() || hayUnderscore()) transita(Estado.REC_ID);
              else return unidadId();               
              break;
           case REC_ENT:
               if (hayDigito()) transita(Estado.REC_ENT);
               else if (hayPunto()) transita(Estado.REC_IDEC);
               else return unidadEnt();
               break;
           case REC_0:
               if (hayPunto()) transita(Estado.REC_IDEC);
               else return unidadEnt();
               break;
           case REC_MAS:
               if (hayDigitoPos()) transita(Estado.REC_ENT);
               else if(hayCero()) transita(Estado.REC_0);
               else return unidadMas();
               break;
           case REC_MENOS: 
               if (hayDigitoPos()) transita(Estado.REC_ENT);
               else if(hayCero()) transita(Estado.REC_0);
               else return unidadMenos();
               break;
           case REC_POR: return unidadPor();
           case REC_DIV: return unidadDiv();              
           case REC_PAP: return unidadPAp();
           case REC_PCIERR: return unidadPCierre();
           case REC_ISEP_DI:
        	   if(hayAmpersand()) transita(Estado.REC_SEP_DI);
        	   else error();
        	   break;
           case REC_SEP_DI: return unidadSeparadorDI();
           case REC_IGUAL:
        	   if(hayIgual()) transita(Estado.REC_BEQ); //////////////////////////////////
        	   else return unidadIgual();
        	   break;
           
           case REC_BLT:
        	   if(hayIgual()) transita(Estado.REC_BLE);
        	   else return unidadBLT();
        	   break;
           case REC_BLE: return unidadBLE();
           case REC_BNE: return unidadBNE();
           case REC_BEQ: return unidadBEQ();
           case REC_BGT:
        	   if(hayIgual()) transita(Estado.REC_BGE);
        	   else return unidadBGT();
        	   break;
           case REC_BGE: return unidadBGE();
           case REC_SCOLON: return unidadSemicolon();
           case REC_EOF: return unidadEof();
           case REC_IDEC:
               if (hayDigitoPos()) transita(Estado.REC_DEC);
               else if (hayCero()) transita(Estado.REC_IDEC);
               else error();
               break;
           case REC_IBNE:
        	   if(hayIgual()) transita(Estado.REC_BNE);
        	   else error();
        	   break;
           case REC_DEC: 
               if (hayDigitoPos()) transita(Estado.REC_DEC);
               else if (hayCero()) transita(Estado.REC_IDEC);
               else return unidadReal();
               break;
		default:
			error();
			break;
         }
     }    
   }



private void transita(Estado sig) throws IOException {
     lex.append((char)sigCar);
     sigCar();         
     estado = sig;
   }
   private void transitaIgnorando(Estado sig) throws IOException {
     sigCar();         
     filaInicio = filaActual;
     columnaInicio = columnaActual;
     estado = sig;
   }
   private void sigCar() throws IOException {
     sigCar = input.read();
     if (sigCar == NL.charAt(0)) saltaFinDeLinea();
     if (sigCar == '\n') {
        filaActual++;
        columnaActual=0;
     }
     else {
       columnaActual++;  
     }
   }
   private void saltaFinDeLinea() throws IOException {
      for (int i=1; i < NL.length(); i++) {
          sigCar = input.read();
          if (sigCar != NL.charAt(i)) error();
      }
      sigCar = '\n';
   }
   
   //Letra = [a-z,A-Z]
   private boolean hayLetra() {return sigCar >= 'a' && sigCar <= 'z' ||
                                      sigCar >= 'A' && sigCar <= 'z';}
   //DigitoPositivo = [1-9]
   private boolean hayDigitoPos() {return sigCar >= '1' && sigCar <= '9';}
   
   //Digito = DigitoPositivo | 0  
   private boolean hayDigito() {return hayDigitoPos() || hayCero();}
   
   
   private boolean hayUnderscore() {return sigCar == '_';}
   private boolean hayExclamacion() {return sigCar == '!';}
   private boolean hayAmpersand() {return sigCar == '&';}
   private boolean hayCero() {return sigCar == '0';}
   private boolean haySuma() {return sigCar == '+';}
   private boolean hayResta() {return sigCar == '-';}
   private boolean hayMul() {return sigCar == '*';}
   private boolean hayDiv() {return sigCar == '/';}
   private boolean hayPAp() {return sigCar == '(';}
   private boolean hayPCierre() {return sigCar == ')';}
   private boolean hayIgual() {return sigCar == '=';}
   private boolean haySemicolon() {return sigCar == ';';}
   private boolean hayPunto() {return sigCar == '.';}
   //Cadenas ignorables
   private boolean haySep() {return sigCar == ' ' || sigCar == '\t' || sigCar=='\n';}
   
   private boolean hayMenor() {return sigCar == '<';}
   private boolean hayMayor() {return sigCar == '>';}
   private boolean hayEOF() {return sigCar == -1;}
   
   private UnidadLexica unidadId() {
     switch(lex.toString()) {
         case "int":  
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.R_INT);
         case "real":    
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.R_REAL);
         case "bool":  
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.R_BOOL);
         case "true":    
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.R_TRUE);
         case "false":  
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.R_FALSE);
         case "and":    
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.R_AND);
         case "or":  
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.R_OR);
         case "not":    
            return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.R_NOT);
         default:    
            return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.IDEN,lex.toString());     
      }
   }
   
   
   
   
   private UnidadLexica unidadEnt() {
     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.ENT,lex.toString());     
   }    
   private UnidadLexica unidadReal() {
     return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.REAL,lex.toString());     
   }    
   private UnidadLexica unidadMas() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAS);     
   }    
   private UnidadLexica unidadMenos() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MENOS);     
   }    
   private UnidadLexica unidadPor() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.POR);     
   }    
   private UnidadLexica unidadDiv() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DIV);     
   }    
   private UnidadLexica unidadPAp() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PAP);     
   }    
   private UnidadLexica unidadPCierre() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PCIERRE);     
   }    
   private UnidadLexica unidadIgual() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.IGUAL);     
   }     
   private UnidadLexica unidadEof() {
     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EOF);     
   }
   private UnidadLexica unidadSemicolon() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.SEMICOLON);     
   } 
   private UnidadLexica unidadSeparadorDI() {
	   return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.SEP_DI);
   }
	   
   
   private UnidadLexica unidadBEQ() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BEQ);     
   } 
   private UnidadLexica unidadBLT() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BLT);     
   } 
   private UnidadLexica unidadBLE() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BLE);     
   }
   private UnidadLexica unidadBGT() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BGT);     
   }
   private UnidadLexica unidadBGE() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BGE);     
   }
   private UnidadLexica unidadBNE() {
	     return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BNE);     
   }
   
   private void error() {
     System.err.println("("+filaActual+','+columnaActual+"):Caracter inexperado :"+ this.lex);  
     System.exit(1);
   }

   public static void main(String arg[]) throws IOException {
     Reader input = new InputStreamReader(new FileInputStream("input.txt"));
     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
     UnidadLexica unidad;
     do {
       unidad = al.sigToken();
       System.out.println(unidad);
     }
     while (unidad.clase() != ClaseLexica.EOF);
    } 
}