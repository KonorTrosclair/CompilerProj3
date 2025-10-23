package Absyn;

public class ExpArrList extends Dec{
    public Dec head;
    public ExpArrList tail;

    // Constructor for a list element with tail
    public ExpArrList(Dec h, ExpArrList t) {
        this.head = h;
        this.tail = t;
    }

    // Convenience constructor for a single element
    public ExpArrList(Dec h) {
        this(h, null);
    }
}

