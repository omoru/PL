package c_ast_ascendente;

import asint.TinyASint.StringLocalizado;
import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {

   public UnidadLexica(int fila, int columna, int clase,String lexema) {
     super(clase,null);
     value = new StringLocalizado(lexema,fila,columna);
     
   }
   public int clase () {return sym;}
   public StringLocalizado lexema(){return (StringLocalizado)value;}
}

