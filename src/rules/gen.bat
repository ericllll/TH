%echo
rem Lexer.java
rem parser.java
..\..\jflexcup\jflex-1.6.1\lib\jflex-1.6.1.jar lrules.flex
 java -jar ..\..\jflexcup\java-cup-11b.jar < yrules.cup
