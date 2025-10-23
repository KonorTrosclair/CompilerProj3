package Absyn;

public class Init extends Dec{
    public Dec dec;      
    public Exp exp;      
    public InitList list;      

    public Init(Dec e) { this.dec = e; this.list = null; }
    public Init(Exp e) { this.exp = e; this.list = null;}
    public Init(InitList i) { this.dec = null; this.list = i; }
}
