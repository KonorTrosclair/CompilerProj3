package Absyn;

// List of bracketed array types (EmptyArrayTypeList or ExpArrList)
public class BrackList extends Dec{
    public Object head;     // could be EmptyArrayTypeList or ExpArrList
    public BrackList tail;

    public BrackList(Object h, BrackList t) {
        this.head = h;
        this.tail = t;
    }

    // convenience constructor for single element
    public BrackList(Object h) {
        this(h, null);
    }
}
