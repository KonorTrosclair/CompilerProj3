package Absyn;
import Symbol.Symbol;

public class StructDecList extends Dec{
    public StructDecList prev;  // previous declarations (can be null)
    public Type type;             // type of the field
    public Symbol name;         // name of the field
    public Object semicolon;    // optional; you can make this int or null if unused

    public StructDecList(Type t, Symbol n) {
        this(null, t, n);
    }

    public StructDecList(StructDecList p, Type t, Symbol n) {
        this.prev = p;
        this.type = t;
        this.name = n;
    }
}