package asinMain;

//Añadir cup.jar a librerias
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;
import asintAsc.AnalizadorSintacticoAscTiny;
import asintDesc.AnalizadorSintacticoDescTiny;

public class Main {
   public static void main(String[] args) throws Exception {
	 
	 if(args.length != 2) {
		 System.err.println("Introduzca nombre de fichero y el tipo de analizador sintactico (desc o asc)");
		 return;
	 }
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 //asint.setScanner(alex);
	 if(args[1].toString().equalsIgnoreCase("desc")) {
		 AnalizadorSintacticoDescTiny asintDesc = new AnalizadorSintacticoDescTiny(new FileReader(args[0]));
		 System.out.println("Analizador sintactico descendiente en proceso...");
		 asintDesc.SP();
		 System.out.println("OK");
	 }
	 else if(args[1].toString().equalsIgnoreCase("asc")) {
		 AnalizadorSintacticoAscTiny asintAsc = new AnalizadorSintacticoAscTiny(alex);
		 System.out.println("Analizador sintactico ascendiente en proceso...");
		 asintAsc.parse();
		 System.out.println("OK");
	 }
	 else {
		 System.err.println("Debe de ser desc o asc");
		 
	 }

	
 }
}   
   
