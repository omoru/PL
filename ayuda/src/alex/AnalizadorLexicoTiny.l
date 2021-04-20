package alex;
import errors.GestionErroresTiny;
%%
%cup
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
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
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = 0 | ({digitoPositivo}{digito}*)
parteDecimal = \.(0 | {digito})* {digitoPositivo}
parteExponencial = (E|e)[\+,\-]?{parteEntera}
comentario = #[^\n]*
allowedCharacters = [^\"\r\b\n]
ignoredStrings = [ \t\r\b\n]
cadena = \"{allowedCharacters}*\"
identificador = {letra}({letra}|{digito}|_)*
numeroEntero = [\+,\-]?{parteEntera}
numeroReal = {numeroEntero}({parteDecimal}|{parteExponencial}|{parteDecimal}{parteExponencial}})
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
%%

{ignoredStrings}        {}
{comentario}            {}

{R_INT}                 {return ops.unidadR_int();}
{R_REAL}                {return ops.unidadR_real();}
{R_BOOL}                {return ops.unidadR_bool();}
{R_STRING}              {return ops.unidadR_string();}
{R_TRUE}                {return ops.unidadR_true();}
{R_FALSE}               {return ops.unidadR_false();}
{R_AND}                 {return ops.unidadR_and();}
{R_OR}                  {return ops.unidadR_or();}
{R_NOT}                 {return ops.unidadR_not();}
{R_NULL}                {return ops.unidadR_null();}
{R_PROC}                {return ops.unidadR_proc();}
{R_IF}                  {return ops.unidadR_if();}
{R_THEN}                {return ops.unidadR_then();}
{R_ELSE}                {return ops.unidadR_else();}
{R_ENDIF}               {return ops.unidadR_endif();}
{R_WHILE}               {return ops.unidadR_while();}
{R_DO}                  {return ops.unidadR_do();}
{R_ENDWHILE}            {return ops.unidadR_endwhile();}
{R_CALL}                {return ops.unidadR_call();}
{R_RECORD}              {return ops.unidadR_record();}
{R_ARRAY}               {return ops.unidadR_array();}
{R_OF}                  {return ops.unidadR_of();}
{R_POINTER}             {return ops.unidadR_pointer();}
{R_NEW}                 {return ops.unidadR_new();}
{R_DELETE}              {return ops.unidadR_delete();}
{R_READ}                {return ops.unidadR_read();}
{R_WRITE}               {return ops.unidadR_write();}
{R_NL}                  {return ops.unidadR_nl();}
{R_VAR}                 {return ops.unidadR_var();}
{R_TYPE}                {return ops.unidadR_type();}
{AMP}                   {return ops.unidadAmp();}
{SEP_DI}                {return ops.unidadSeparadorDI();}
{MAS}                   {return ops.unidadMas();}
{MENOS}                 {return ops.unidadMenos();}
{POR}                   {return ops.unidadPor();}
{DIV}                   {return ops.unidadDiv();}
{IGUAL}                 {return ops.unidadIgual();} 
{BLT}                   {return ops.unidadBLT();}
{BGT}                   {return ops.unidadBGT();}
{BLE}                   {return ops.unidadBLE();}
{BGE}                   {return ops.unidadBGE();}
{BEQ}                   {return ops.unidadBEQ();}
{BNE}                   {return ops.unidadBNE();}
{PAP}                   {return ops.unidadPAp();}
{PCIERRE}               {return ops.unidadPCierre();} 
{SEMICOLON}             {return ops.unidadSemicolon();}
{MOD}                   {return ops.unidadMod();}
{CAP}                   {return ops.unidadCorchete_ap();}
{CCIERRE}               {return ops.unidadCorchete_ci();}
{LLAP}                  {return ops.unidadLlave_ap();}
{LLCIERRE}              {return ops.unidadLlave_ci();}
{PUNTO}                 {return ops.unidadPunto();}
{FLECHA}                {return ops.unidadFlecha();}
{COMA}                  {return ops.unidadComa();}

{numeroEntero}          {return ops.unidadEnt();}
{numeroReal}            {return ops.unidadReal();}
{cadena}                {return ops.unidadCadena();}
{identificador}         {return ops.unidadId();}


[^]                     {errores.errorLexico(fila(),lexema());}   