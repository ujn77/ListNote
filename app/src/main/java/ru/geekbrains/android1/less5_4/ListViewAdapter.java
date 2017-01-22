package ru.geekbrains.android1.less5_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{

    /* Private field for store type of items */
    private ItemType FItemType = ItemType.getDef();

    /* Private field for store List Data */
    private StringArray FListData = null;

    /* Private field for store inflater */
    private LayoutInflater ItemInflater = null;

    /** Constructor */
    public ListViewAdapter(Context context, StringArray data, ItemType type) {
        super();
        FListData = data;
        ItemInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Get current type of items
     * */
    public ItemType getItemType() { return FItemType; }

    /**
     * Set new value for type of items
     * */
    public void setItemType(ItemType value) {
        if (!FItemType.equals(value)) {
            FItemType = value;
            notifyDataSetInvalidated();
        }
    }

    /**
     * Change type of items
     * */
    public void changeItemType() {
        setItemType(ItemType.getNext(FItemType));
    }

    /**
     * Clear all items
     * */
    public void clear() {
        FListData.clear();
        this.notifyDataSetChanged();
    }

    /**
     * Removes the data item associated with the specified position in the data
     * set.
     * */
    public void removeItem(int position) {
        FListData.remove(position);
        this.notifyDataSetChanged();
    }

    public void add(String value) {
        FListData.add(value);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() { return FListData.size(); }

    @Override
    public Object getItem(int position) { return FListData.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

		/* Create a View */
        View ItemView = null;

		/* Select type of item */
        if (getItemType() == ItemType.itFull) {
            ItemView = ItemInflater.inflate(R.layout.list_item, parent,
                    false);
        } else {
            ItemView = ItemInflater.inflate(R.layout.list_item_short, parent,
                    false);
        }

		/* Load UI components */
        TextView iNumber = (TextView) ItemView.findViewById(R.id.iNumber);
        TextView iTitle = (TextView) ItemView.findViewById(R.id.iTitle);

		/* Fill UI components */
        iNumber.setText(String.valueOf(position + 1));
        iTitle.setText(FListData.get(position));
        if (getItemType() == ItemType.itFull) {
            TextView iShort = (TextView) ItemView.findViewById(R.id.iShort);
            //iShort.setText("This is element " + String.valueOf(position + 1));
            iShort.setText("");

        }

		/* Return View (as Item) */
        return ItemView;
    }
}
