package Absyn;

import Symbol.Symbol;

public class bflist extends Dec {
    public Symbol name;    // parameter name
    public bfval typ;        // parameter type (TypeDec or Type)
    public boolean escape = true; // escape analysis flag

    public bflist tail;    // next parameter in list

    public bflist(bfval t, bflist tail) {
        this.typ = t;
        this.tail = tail;
    }
}
