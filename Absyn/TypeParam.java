package Absyn;

public class TypeParam extends Dec {

    public ParamTypeList paramList; 


    // Constructor for ( parameter_type_list )
    public TypeParam(ParamTypeList x) {
        this.paramList = x;
    }

    // Constructor for ( )
    public TypeParam() {
        this.paramList = null;
    }
}