package Absyn;

import Symbol.Symbol;

public class StructOrUnionDec extends Dec {
    public int pos;
    public Symbol name;              // struct name
    public bflist bitfields;     
    public Symbol strun;    // optional
    public StructDecList structOrUnion; // holds the fields

    public StructOrUnionDec(int p,  bflist b, Symbol d, Symbol n, StructDecList su) {
        this.pos = p;
        this.name = n;
        this.strun = d;
        this.bitfields = b;
        this.structOrUnion = su;
    }


}