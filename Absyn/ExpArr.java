package Absyn;

public class ExpArr extends Dec{
    public int lbrackPos;
    public Exp constExpr;
    public int rbrackPos;

    public ExpArr(Exp c) {
        this.constExpr = c;
    }
}