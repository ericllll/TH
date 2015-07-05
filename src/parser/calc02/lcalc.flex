/*
Comment� Par: Christopher Lopes
Nom du fichier: lcalc.flex
Pour l'utiliser: jflex lcalc.flex

et, une fois le parseur cr��:
javac Lexer.java
*/

/* --------------------------Section de Code Utilisateur---------------------*/

/* importer ces classes:
 java_cup.runtime.*
 sym  --classe cr��e avec CUP
*/
import java_cup.runtime.*;
import sym;


%%
/* -----------------Section des D�clarations et Options----------------------*/

/* Le nom de la classe que JFlex va cr�er sera Lexer.
Le code en sera �crit dans le fichier Lexer.java.
*/
%class Lexer

/* Le num�ro de la ligne courante peut �tre obtenu par le biais de la variable
yyline et le num�ro de la colonne courante dans la variable yycolumn.
*/
%line
%column

/* Permet le passage en mode de compatibilit� CUP pour s'interfacer avec un
parseur g�n�r� par CUP.
*/
%cup

/* D�clarations

Le code entre %{ et %}, chacun de ces d�limiteurs devant se trouver au d�but
d'une ligne, sera copi� lettre par lettre dans le code source du Lexer. C'est
ici que vous d�clarez les attributs et les fonctions qui sont utilis�es �
l'int�rieur des actions du scanneur.
*/
%{

/* Pour cr�er un nouvel objet java_cup.runtime.Symbol contenant des
informations sur le jeton courant, le jeton n'aura pas de valeur dans ce
cas. */
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }

/* Cr�� �galement un nouvel objet java_cup.runtime.Symbol avec des
informations sur le jeton courant mais cet objet dispose d'une valeur.
*/
 private Symbol symbol(int type, Object value) {
   return new Symbol(type, yyline, yycolumn, value);
 }
%}

/*
D�clarations de Macro

Ces d�clarations sont des expressions r�guli�res qui seront utilis�es
plus loin  dans la Section des R�gles Lexicales.
*/
/* Un terminateur de ligne est un \r (retour chariot), un \n (saut de
ligne) ou \r\n.
*/
LineTerminator = \r|\n|\r\n

/* Un espace est un terminateur de ligne, un espace, une tabulation ou
un saut de ligne.
*/
WhiteSpace = {LineTerminator} | [ \t\f]

/* Un entier litt�ral est un nombre commen�ant par un chiffre entre un et neuf,
suivi de z�ro ou plusieurs chiffres entres z�ro et neuf, ou un simple z�ro.
*/
dec_int_lit = 0 | [1-9][0-9]*

/* Un identifiant d'entier est un mot commen�ant par une lettre entre
A et Z, a et z ou un soulignement suivi de z�ro ou plusieurs lettres
entre A et Z, a et z, z�ro et neuf ou un underscore. */
dec_int_id = [A-Za-z_][A-Za-z_0-9]*

%%
/* ------------------------Section des R�gles Lexicales----------------------*/

/* Cette section contient des expressions r�guli�res et des actions,
i.e. du code Java qui sera ex�cut� quand le scanneur reconna�tra
l'expression r�guli�re associ�e.
*/

/* YYINITIAL est l'�tat dans lequel le lexer commence son analyse. Les
expressions r�guli�res suivantes ne seront donc reconnues que si le
scanneur est dans l'�tat de d�part YYINITIAL. */
<YYINITIAL> {
/* Renvoie le jeton SEMI d�clar� dans la classe sym. */
";"
{ return symbol(sym.SEMI); }
/* Renvoie le jeton trouv� d�clar� dans la classe sym.*/
"+"
{ System.out.print(" + "); return symbol(sym.PLUS); }
"-"
{ System.out.print(" - "); return symbol(sym.MINUS); }
"*"
{ System.out.print(" * "); return symbol(sym.TIMES); }
"/"
{ System.out.print(" / "); return symbol(sym.DIVIDE); }
"("
{ System.out.print(" ( "); return symbol(sym.LPAREN); }
")"
{ System.out.print(" ) "); return symbol(sym.RPAREN); }

/* Si un entier est trouv�, on retourne le jeton NUMBER qui repr�sente un
entier, la valeur de cet entier se trouve dans la cha�ne de caract�res yytext
qui va �tre transform�e en entier avant d'�tre renvoy�e.
*/
{dec_int_lit}
{ System.out.print(yytext());
  return symbol(sym.NUMBER, new Integer(yytext())); }

/* Si un identifiant est trouv�, l'afficher, renvoyer le jeton ID qui
repr�sente un identifiant et la valeur par d�faut de 1 qui est donn�e � tous
les identifiants. */
{dec_int_id}
{ System.out.print(yytext());
  return symbol(sym.ID, new Integer(1));}

/* Ne rien faire si l'on rencontre un espace */
{WhiteSpace}
{ /* il suffit d'ignorer ce que l'on a trouv� */ }

}

/* Aucun jeton n'a �t� trouv� ce qui provoque une erreur. Affiche un message
'Illegal character'  qui contient le caract�re ill�gal trouv�.*/
.
{ throw new Error("Illegal character <"+yytext()+">");}