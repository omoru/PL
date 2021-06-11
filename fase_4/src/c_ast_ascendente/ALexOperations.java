
package c_ast_ascendente;

public class ALexOperations {
  private AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;   
  }
  public UnidadLexica token(int clase) {
     UnidadLexica t = new UnidadLexica(alex.fila(), alex.columna(), clase, alex.lexema());
     //alex.incCol();
     return t;     
  }
}

