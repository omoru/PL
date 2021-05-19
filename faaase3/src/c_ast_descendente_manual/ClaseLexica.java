package c_ast_descendente_manual;

public enum ClaseLexica {
	
	//UNIVALUADAS
		SEP_DI,  // Separador de la secci�n de declaraciones de la de instrucciones
		MAS,  // operator +
		MENOS,// operator -
		POR,  // operator *
		DIV,  // operator / 
		IGUAL,	  // Operador asignaci�n (=)
		BLT,  // Operador comparaci�n menor estricto (<)
		BGT,  // Operador comparacion mayor estricto (>)
		BLE,  // Operador comparaci�n menor o igual (<=)
		BGE,  // Operador comparacion mayor o igual (>=)
		BEQ,  // Operador comparacion igualdad (==)
		BNE,  // Operador comparaci�n desigualdad (!=)
		PAP,  	// Parentesis de apertura (
		PCIERRE,// Parentesis de cierre )
		EOF,	// Fin de fichero
		SEMICOLON, // ;
			
		//PALABRAS RESERVADAS
		R_INT, //Tipo basico 'int'
		R_REAL,//Tipo basico 'real'
		R_BOOL,//Tipo basico 'bool'
		R_TRUE,//Expresion basica 'true' de tipo bool
		R_FALSE,// Expresion basica 'false' de tipo bool
		R_AND, //Operador logico conjuncion
		R_OR,  //Operador logico disyuncion
		R_NOT, //Operador logico negacion
	
	//MULTIVALUADAS
		IDEN, // Identificadores :comienzan por una letra, seguida de una secuencia de cero o m�s letras, d�gitos o �_�. 
		ENT, //Literales enteros: un literal entero, comienza por + o � opcionalmente (signo del n�mero) y va seguido del n�mero entero (secuencia de uno o m�s d�gitos sin 0 no significativos a la izquierda) 		
		REAL //Literales reales: un literal real comienza con una parte entera, seguida de una parte decimal o una parte exponencial, o bien una parte decimal seguida de una parte exponencial. 
	
	
	
	

}

