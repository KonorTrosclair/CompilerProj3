CSC 4351 – Project 2: ANSI C Parser Team Information Team Members: 
Amy Granados: implement second half of expression with corresponding Absyn files 
Konor Trosclair: implement first half of expression with corresponding Absyn files 
Stella Levy: implement declaration with corresponding Absyn files 
Huarong Teng: implement statement with corresponding Absyn files

What Works: Grammar Parsing:

Declarations: The parser correctly recognizes and processes variable and function declarations using the modified grammar with var and fun keywords. Pass all the grammar rules.
Declaration grammars should also recognize all conditions of the tweaked grammars.
Statements: Pass all the grammar rules, such as label statement, compound statement, Jump statement etc.
Expressions: Arithmetic, relational, and logical expressions are parsed accurately, including operator precedence and associativity. 
The parser correctly handles binary and unary operations, parenthesized expressions, and variable references. Pass and should pass all the grammar rules.
Abysn files: Each grammar rule has an appropriate Absyn node:
Use of Resources and AI Tools Online C89 Specification: Provided on Moodle AI Tools: ChatGPT for code structure and general information

Test file: There is a test file called Test.c which contains all of the possible phrases the parser could encounter that we could think of.

Important notes
Konor:
    In order to get the grammars printing I ended up making my own print.java file in the Absyn directory.
    From reviewing outputs it looks as though it prints all information just not in the original print.java format (ie VarDec("some info") ) instead mine prints (ie varDec: "some info")
    Again this was the only way I could get the AST to print and when I tried to reuse the original print.java it looked like I might have to redo a lot of the Absyn files.
    I was not completly Sure if I was suppose to mess with this file but I did. If you would like me to redo the file and make it print the Tree in your format I would be willing too but it may take some time.
    The print file was also heavily assisted with AI with about 20% human intervention wich resulted in the file 
    printing info that is not needed for the tree I am my pretty sure I got rid of that though.

    In addition to the print.java file I also changed Main.java and Parse.java because it was causing a cast to type error when switching from classes 
        Example: Exp to Var to Dec ect...
    
    We also used our Lexical Analyzer for the project because the TA given one was giving issues I am aware that it was fixed in a later email but currently the one being used is our submission.
    I am aware that our lexical analyzer might have an issue with strings but when I reran the tests I did not see anything wrong perhaps I was not testing the right thing.
    If strings do give issues you can try the lexical analyzer in the skeleton code but I am not entirely sure it will work with our parser.

    There is also an aboundance of Absyn files that are not being used this ended up happening when adding other peoples work to my machine and I lost track of which ones were important.

    I also made changes to the Makefile in order to compile the absyn files and to Compile our lexical analyzer.
    
    If there is any confusion about the work done you can email me at ktrosc8@lsu.edu. I apologies if this causes any issues.



