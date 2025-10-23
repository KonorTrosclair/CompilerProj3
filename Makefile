JFLAGS=-g

# Compile all Java files in Parse/
Parse/*.class: Parse/*.java Parse/Grm.java Parse/Yylex.java
	javac ${JFLAGS} Parse/*.java

Absyn/*.class: Absyn/*.java
	javac ${JFLAGS} Absyn/*.java

# Generate the parser from Grm.cup
Parse/Grm.java: Parse/Grm.cup
	cd Parse; java java_cup.Main -parser Grm -expect 3 -nonterms -dump_grammar -dump_states -compact_red <Grm.cup >Grm.out 2>Grm.err

# Generate the lexer from tiger.lex (JFlex)
Parse/Yylex.java: Parse/Tiger.lex
	cd Parse; java JLex.Main Tiger.lex; mv Tiger.lex.java Yylex.java

# Clean up generated files
clean:
	rm -f Parse/*.class Parse/Grm.java Parse/Grm.err Parse/Grm.out Parse/Yylex.java Absyn/*.class
