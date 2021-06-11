package tiny;


import asint.TinyASint.Prog;
import c_ast_ascendente.AnalizadorLexicoTiny;
import procesamientos.Asigna_espacio;
import procesamientos.Impresion;
import procesamientos.Vinculacion;
import tables.Asig_esp_data;
import tables.Vinculation_data;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class Main {
   public static void main(String[] args) throws Exception {
	   Prog prog = null;
       if (args[0].equals("-asc")) {
    	   System.out.println("-------------------Ejecutando c_ast ascendente:----------------");
    	   System.out.println();
           prog = ejecuta_ascendente(args[1]);
       }
       else if (args[0].equals("-desc")) {
           prog = ejecuta_descendente(args[1]);
    	   System.out.println("---------------------Ejecutando c_ast descendente:----------------");
    	   System.out.println();
       }
       else 
          System.out.println("Args tienen que ser -asc o -desc y el nombre del fichero");
       
       //System.out.println("Procesamiento de impresion: ");
       //prog.procesa(new Impresion());
       //System.out.println("Procesamiento de impresion terminado con exito ");

       System.out.println("Procesamiento de vinculacion: ");
       Vinculation_data v_data = new Vinculation_data();
       prog.procesa(new Vinculacion(v_data));
       System.out.println();  

       System.out.println("Procesamiento de asignacion de espacio: ");
       Asig_esp_data a_data = new Asig_esp_data();
       prog.procesa(new Asigna_espacio(v_data, a_data));
       System.out.println();  

       
       System.out.println();  
   }
   
   
   
   private static Prog ejecuta_ascendente(String in) throws Exception {       
	     Reader input = new InputStreamReader(new FileInputStream(in));
	     AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	     c_ast_ascendente.ConstructorAST constructorast =  new c_ast_ascendente.ConstructorAST(alex);
	     return (Prog)constructorast.parse().value;
	  }
	   private static Prog ejecuta_descendente(String in) throws Exception {
	     Reader input = new InputStreamReader(new FileInputStream(in));
	     c_ast_descendente.ConstructorAST constructorast = new c_ast_descendente.ConstructorAST(input);
	     return constructorast.Init();
	   }
}   
   
