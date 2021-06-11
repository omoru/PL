package c_ast_ascendente;
%%
%cup
%line
%column
%class AnalizadorLexicoTiny
%unicode
%public

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresTiny errores){
    this.errores=errores;
  }
%}

%eofval{
   return ops.token(ClaseLexica.EOF);
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = 0 | ({digitoPositivo}{digito}*)
parteDecimal = \.(0 | ({digito}* {digitoPositivo}))
parteExponencial = (E|e)[\+\-]?{parteEntera}
comentario = #[^\n]*
allowedCharacters = [^\"\r\b\n]
ignoredStrings = [ \t\r\b\n]
SEP_DI= &&           
MAS= \+         
MENOS= \-           
POR= \*           
DIV= \/           
BLT = <
BGT = >         
BLE = <\=
BGE = >\=
BEQ = \=\=
BNE = \!=
IGUAL = \=
PAP = \(
PCIERRE = \)
SEMICOLON = ;
MOD = %
CAP= \[
CCIERRE = ]
LLAP = \{
LLCIERRE = \}
PUNTO = \.
FLECHA = \->
COMA = ,
AMP = &
R_INT= int
R_REAL= real
R_BOOL= bool
R_STRING= string
R_TRUE= true
R_FALSE= false
R_AND= and
R_OR= or
R_NOT= not
R_NULL= null
R_PROC= proc
R_IF= if
R_THEN= then
R_ELSE= else
R_ENDIF= endif
R_WHILE=  while
R_DO= do
R_ENDWHILE= endwhile
R_CALL= call
R_RECORD= record
R_ARRAY= array
R_OF= of
R_POINTER= pointer
R_NEW= new
R_DELETE= delete
R_READ= read
R_WRITE= write
R_NL= nl
R_VAR= var
R_TYPE= type
cadena = \"{allowedCharacters}*\"
identificador = {letra}({letra}|{digito}|_)*
numeroEntero = [\+\-]?{parteEntera}
numeroReal = {numeroEntero}({parteDecimal}{parteExponencial} | {parteDecimal} |{parteExponencial})
%%

{ignoredStrings}        {}
{comentario}            {}

{R_INT}                 {return ops.token(ClaseLexica.r_int);}
{R_REAL}                {return ops.token(ClaseLexica.r_real);}
{R_BOOL}                {return ops.token(ClaseLexica.r_bool);}
{R_STRING}              {return ops.token(ClaseLexica.r_string);}
{R_TRUE}                {return ops.token(ClaseLexica.r_true);}
{R_FALSE}               {return ops.token(ClaseLexica.r_false);}
{R_AND}                 {return ops.token(ClaseLexica.and);}
{R_OR}                  {return ops.token(ClaseLexica.or);}
{R_NOT}                 {return ops.token(ClaseLexica.not);}
{R_NULL}                {return ops.token(ClaseLexica.r_null);}
{R_PROC}                {return ops.token(ClaseLexica.proc);}
{R_IF}                  {return ops.token(ClaseLexica.r_if);}
{R_THEN}                {return ops.token(ClaseLexica.then);}
{R_ELSE}                {return ops.token(ClaseLexica.r_else);}
{R_ENDIF}               {return ops.token(ClaseLexica.endif);}
{R_WHILE}               {return ops.token(ClaseLexica.r_while);}
{R_DO}                  {return ops.token(ClaseLexica.r_do);}
{R_ENDWHILE}            {return ops.token(ClaseLexica.endwhile);}
{R_CALL}                {return ops.token(ClaseLexica.call);}
{R_RECORD}              {return ops.token(ClaseLexica.record);}
{R_ARRAY}               {return ops.token(ClaseLexica.array);}
{R_OF}                  {return ops.token(ClaseLexica.of);}
{R_POINTER}             {return ops.token(ClaseLexica.pointer);}
{R_NEW}                 {return ops.token(ClaseLexica.r_new);}
{R_DELETE}              {return ops.token(ClaseLexica.delete);}
{R_READ}                {return ops.token(ClaseLexica.read);}
{R_WRITE}               {return ops.token(ClaseLexica.write);}
{R_NL}                  {return ops.token(ClaseLexica.nl);}
{R_VAR}                 {return ops.token(ClaseLexica.var);}
{R_TYPE}                {return ops.token(ClaseLexica.type);}
{AMP}                   {return ops.token(ClaseLexica.amp);}
{SEP_DI}                {return ops.token(ClaseLexica.sep_di);}
{MAS}                   {return ops.token(ClaseLexica.mas);}
{MENOS}                 {return ops.token(ClaseLexica.menos);}
{POR}                   {return ops.token(ClaseLexica.por);}
{DIV}                   {return ops.token(ClaseLexica.div);}
{IGUAL}                 {return ops.token(ClaseLexica.igual);} 
{BLT}                   {return ops.token(ClaseLexica.blt);}
{BGT}                   {return ops.token(ClaseLexica.bgt);}
{BLE}                   {return ops.token(ClaseLexica.ble);}
{BGE}                   {return ops.token(ClaseLexica.bge);}
{BEQ}                   {return ops.token(ClaseLexica.beq);}
{BNE}                   {return ops.token(ClaseLexica.bne);}
{PAP}                   {return ops.token(ClaseLexica.pap);}
{PCIERRE}               {return ops.token(ClaseLexica.pcierre);} 
{SEMICOLON}             {return ops.token(ClaseLexica.scol);}
{MOD}                   {return ops.token(ClaseLexica.mod);}
{CAP}                   {return ops.token(ClaseLexica.cap);}
{CCIERRE}               {return ops.token(ClaseLexica.ccierre);}
{LLAP}                  {return ops.token(ClaseLexica.llap);}
{LLCIERRE}              {return ops.token(ClaseLexica.llcierre);}
{PUNTO}                 {return ops.token(ClaseLexica.punto);}
{FLECHA}                {return ops.token(ClaseLexica.flecha);}
{COMA}                  {return ops.token(ClaseLexica.coma);}

{numeroReal}            {return ops.token(ClaseLexica.real);}
{numeroEntero}          {return ops.token(ClaseLexica.ent);}
{cadena}                {return ops.token(ClaseLexica.cadena);}
{identificador}         {return ops.token(ClaseLexica.id);}


[^]                     {errores.errorLexico(fila(),lexema());}   