package rules;

import java_cup.runtime.*;
import ficExcel.CT;

%%

%class Lexer
%line
%column
%cup

%{
	/* import du tableau des données dans l'analyseur */
	CT tb = new CT("YIS CT Build R02D02_P.xls", "xml/CT.xml");

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
/* dec_int_lit = 0 | [1-9][0-9]* */
/* dec_int_id = [A-Za-z_][A-Za-z_0-9]* */

name_sheet = #[A-Za-z][A-Za-z_]*
name = [A-Za-z][A-Za-z_]*

   
%%

<YYINITIAL> {
   
    ";"                { return symbol(sym.SEMI); }
   
/*    "+"                { System.out.print(" + "); return symbol(sym.PLUS); } */
/*     "-"                { System.out.print(" - "); return symbol(sym.MINUS); } */
/*    "*"                { System.out.print(" * "); return symbol(sym.TIMES); } */
/*    "/"                { System.out.print(" / "); return symbol(sym.DIVIDE); } */

    "("                { System.out.print(" ( "); return symbol(sym.LPAREN); }
    ")"                { System.out.print(" ) "); return symbol(sym.RPAREN); }

	"=="				{ System.out.print(" == "); return symbol(sym.EQUALS); }
/*	{LineTerminator}	{ return symbol(sym.LL); }*/

	"input1"			{ System.out.print(" input1 "); return symbol(sym.INPUT1); }
	"input2"			{ System.out.print(" input2 "); return symbol(sym.INPUT2); }
	"input3"			{ System.out.print(" input3 "); return symbol(sym.INPUT3); }
	"input4"			{ System.out.print(" input4 "); return symbol(sym.INPUT4); }
	 
	 
	 
	{name_sheet}		{ 	if(tb.chercherOnglet(yytext().substring(1))==null){
								System.out.println("ERREUR !!!");
								return symbol(sym.SHEET, "onglet inexistant");
							} else {
								return symbol(sym.SHEET, new String(yytext().substring(1)));
							}
						}
							
	{name}				{ return symbol(sym.NAME, new String(yytext())); }
   
/*    {dec_int_lit}      { System.out.print(yytext());
                         return symbol(sym.NUMBER, new Integer(yytext())); } */
   
/*    {dec_int_id}       { System.out.print(yytext());
                         return symbol(sym.ID, new Integer(1));} */
   
    {WhiteSpace}       { /* just skip what was found, do nothing */ }   
}

/* [^]                    { throw new Error("Illegal character <"+yytext()+">"); }
*/