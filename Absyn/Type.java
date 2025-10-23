package Absyn;

public class Type extends TypeName {
    public TypeName typeName;
    public Dec typeArgs; // optional, can be null

    public Type(TypeName n, Dec a) {
        super(n != null ? n.name : null);
        this.typeName = n;
        this.typeArgs = a;
    }

    public Type(TypeName n) {
        this(n, null);
    }

    @Override
    public String toString() {
        return name != null ? name.toString() : "Type";
    }
}
