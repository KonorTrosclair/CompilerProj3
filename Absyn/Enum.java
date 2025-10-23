package Absyn;
import Symbol.Symbol;;

public class Enum extends Dec{
    public String name;
    public Exp value;

    public Enum(String n) { this.name = n; value=null; }
    public Enum(String n,  Exp c) { this.name = n; value=c; }
}

