package alex;

public enum ClaseLexica {
	
	//UNIVALUADAS
		SEP_DI,  // Separador de la secci�n de declaraciones de la de instrucciones
		SEMICOLON, // ;
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
		MOD, // operador modulo %
		CAP, //Corchete apertura
		CCIERRE, //corchete cierre
		LLAP, //llave apertura
		LLCIERRE, //lave cierre
		PUNTO, //Operador de acceso a registro '.'
		FLECHA, //Operador de acceso a registro '->'
		COMA,// separador declaraciones
		AMP,//	parametro por variable (&)
		EOF,	// Fin de fichero
		
			
		//PALABRAS RESERVADAS
		R_INT, //Tipo basico 'int'
		R_REAL,//Tipo basico 'real'
		R_BOOL,//Tipo basico 'bool'
		R_TRUE,//Expresion basica 'true' de tipo bool
		R_FALSE,// Expresion basica 'false' de tipo bool
		R_AND, //Operador logico conjuncion
		R_OR,  //Operador logico disyuncion
		R_NOT, //Operador logico negacion
		R_STRING, //Tipo basico 'string'
		R_NULL,
		R_PROC,
		R_IF,
		R_THEN,
		R_ELSE,
		R_ENDIF,
		R_WHILE,
		R_DO,
		R_ENDWHILE,
		R_CALL,
		R_RECORD,
		R_ARRAY,
		R_OF,
		R_POINTER,
		R_NEW,
		R_DELETE,
		R_READ,
		R_WRITE,
		R_NL,
		R_VAR,
		R_TYPE,
	
	//MULTIVALUADAS
		IDEN, // Identificadores :comienzan por una letra, seguida de una secuencia de cero o m�s letras, d�gitos o �_�. 
		ENT, //Literales enteros: un literal entero, comienza por + o � opcionalmente (signo del n�mero) y va seguido del n�mero entero (secuencia de uno o m�s d�gitos sin 0 no significativos a la izquierda) 		
		REAL, //Literales reales: un literal real comienza con una parte entera, seguida de una parte decimal o una parte exponencial, o bien una parte decimal seguida de una parte exponencial. 
	    CAD   //Literales cadena : Comienzan con comilla doble ("), seguida de una secuencia de 0 o m�s caracteres distintos de comilla doble ("), retroceso (\b), retorno de carro (\r), y salto de l�nea (\n), seguida de comilla doble("). 
	
	
	

}

