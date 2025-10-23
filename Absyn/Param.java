package Absyn;

public class Param extends Dec {
    public Object lparenPos;
    public ParamList paramList;
    public Object commaPos;       // optional
    public Object ellipsesPos;    // optional
    public Object rparenPos;

    // ( parameter_list )
    public Param(ParamList p) {
        this.paramList = p;
    }

    // ( parameter_list , ... )
    public Param(ParamList p, Object e) {
        this.paramList = p;
        this.ellipsesPos = e;
    }

    // ( )
    public Param() {
        this.paramList = null;
    }
}
