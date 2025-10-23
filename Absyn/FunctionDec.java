package Absyn;
import Symbol.Symbol;

public class FunctionDec extends Dec {
    public Symbol name;
    public bflist params;
    public Type result;   // optional return type
    public Dec body;      // function body (expression or compound statement)
    public Stm next;      // next declaration in the list

    public boolean leaf = false;

    public FunctionDec(int p, bflist a, Type r, Symbol n, Dec b, Stm x) {
        pos = p;
        params = a;
        result = r;
        name = n;
        body = b;
        next = x;
    }
}
