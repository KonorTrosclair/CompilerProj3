package Absyn;
import Symbol.Symbol;

public class VarDec extends Dec {
    public Symbol name;        // variable name
    public boolean escape = true;
    public Type typ;           // the declared type (has its own .name)
    public Dec init;           // initializer expression
    public bflist params;

    public VarDec(int p, bflist par, Symbol n, Type t, Dec i) {
        pos = p;
        params = par;
        name = n;
        typ = t;
        init = i;
    }
}
