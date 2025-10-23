package Absyn;

public class InitList extends Dec {
    public Dec head;          // first initializer
    public InitList tail;      // rest of the list
    public int commaPos;       // position of comma (optional, only for second form)

    // single initializer
    public InitList(Dec h) {
        this.head = h;
        this.tail = null;
    }

    // initializer_list , initializer
    public InitList(InitList l, Dec i) {
        this.head = i;
        this.tail = l;
    }
}
