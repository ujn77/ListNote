package ru.geekbrains.android1.less5_4;


public enum ItemType {

    /** An expanded view of the item. */
    itFull,

    /** A simplified view of the item. */
    itShort;

    /**
     * Returns default item type value.
     * */
    public static ItemType getDef() { return ItemType.itFull; }

    /**
     * Returns a next item type value.
     * */
    public static ItemType getNext(ItemType iType) {
        int index = (iType.ordinal() + 1) % ItemType.values().length;
        return ItemType.values()[index];
    }
}
