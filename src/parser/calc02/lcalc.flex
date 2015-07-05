/*
Commenté Par: Christopher Lopes
Nom du fichier: lcalc.flex
Pour l'utiliser: jflex lcalc.flex

et, une fois le parseur créé:
javac Lexer.java
*/

/* --------------------------Section de Code Utilisateur---------------------*/

/* importer ces classes:
 java_cup.runtime.*
 sym  --classe créée avec CUP
*/
import java_cup.runtime.*;
import sym;


%%
/* -----------------Section des Déclarations et Options----------------------*/

/* Le nom de la classe que JFlex va créer sera Lexer.
Le code en sera écrit dans le fichier Lexer.java.
*/
%class Lexer

/* Le numéro de la ligne courante peut être obtenu par le biais de la variable
yyline et le numéro de la colonne courante dans la variable yycolumn.
*/
%line
%column

/* Permet le passage en mode de compatibilité CUP pour s'interfacer avec un
parseur généré par CUP.
*/
%cup

/* Déclarations

Le code entre %{ et %}, chacun de ces délimiteurs devant se trouver au début
d'une ligne, sera copié lettre par lettre dans le code source du Lexer. C'est
ici que vous déclarez les attributs et les fonctions qui sont utilisées à
l'intérieur des actions du scanneur.
*/
%{

/* Pour créer un nouvel objet java_cup.runtime.Symbol contenant des
informations sur le jeton courant, le jeton n'aura pas de valeur dans ce
cas. */
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }

/* Créé également un nouvel objet java_cup.runtime.Symbol avec des
informations sur le jeton courant mais cet objet dispose d'une valeur.
*/
 private Symbol symbol(int type, Object value) {
   return new Symbol(type, yyline, yycolumn, value);
 }
%}

/*
Déclarations de Macro

Ces déclarations sont des expressions régulières qui seront utilisées
plus loin  dans la Section des Règles Lexicales.
*/
/* Un terminateur de ligne est un \r (retour chariot), un \n (saut de
ligne) ou \r\n.
*/
LineTerminator = \r|\n|\r\n

/* Un espace est un terminateur de ligne, un espace, une tabulation ou
un saut de ligne.
*/
WhiteSpace = {LineTerminator} | [ \t\f]

/* Un entier littéral est un nombre commençant par un chiffre entre un et neuf,
suivi de zéro ou plusieurs chiffres entres zéro et neuf, ou un simple zéro.
*/
dec_int_lit = 0 | [1-9][0-9]*

/* Un identifiant d'entier est un mot commençant par une lettre entre
A et Z, a et z ou un soulignement suivi de zéro ou plusieurs lettres
entre A et Z, a et z, zéro et neuf ou un underscore. */
dec_int_id = [A-Za-z_][A-Za-z_0-9]*

%%
/* ------------------------Section des Règles Lexicales----------------------*/

/* Cette section contient des expressions régulières et des actions,
i.e. du code Java qui sera exécuté quand le scanneur reconnaîtra
l'expression régulière associée.
*/

/* YYINITIAL est l'état dans lequel le lexer commence son analyse. Les
expressions régulières suivantes ne seront donc reconnues que si le
scanneur est dans l'état de départ YYINITIAL. */
<YYINITIAL> {
/* Renvoie le jeton SEMI déclaré dans la classe sym. */
";"
{ return symbol(sym.SEMI); }
/* Renvoie le jeton trouvé déclaré dans la classe sym.*/
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

/* Si un entier est trouvé, on retourne le jeton NUMBER qui représente un
entier, la valeur de cet entier se trouve dans la chaîne de caractères yytext
qui va être transformée en entier avant d'être renvoyée.
*/
{dec_int_lit}
{ System.out.print(yytext());
  return symbol(sym.NUMBER, new Integer(yytext())); }

/* Si un identifiant est trouvé, l'afficher, renvoyer le jeton ID qui
représente un identifiant et la valeur par défaut de 1 qui est donnée à tous
les identifiants. */
{dec_int_id}
{ System.out.print(yytext());
  return symbol(sym.ID, new Integer(1));}

/* Ne rien faire si l'on rencontre un espace */
{WhiteSpace}
{ /* il suffit d'ignorer ce que l'on a trouvé */ }

}

/* Aucun jeton n'a été trouvé ce qui provoque une erreur. Affiche un message
'Illegal character'  qui contient le caractère illégal trouvé.*/
.
{ throw new Error("Illegal character <"+yytext()+">");}