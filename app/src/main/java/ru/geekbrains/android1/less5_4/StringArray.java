package ru.geekbrains.android1.less5_4;

import java.util.ArrayList;
import android.content.Context;
import android.content.res.Resources;

public class StringArray extends ArrayList<String> {

    /** Private Constant from superclass */
    private static final long serialVersionUID = 1L;

    /** Constructor without any parameters */
    public StringArray() { super(); }

    /**
     * Constructor with parameter (read from Resources)
     * */
    public StringArray(Resources resources, int resId) {
        super(); LoadFromResource(resources, resId, false);
    }

    /**
     * Load Data from Resources
     * */
    public int LoadFromResource(Resources resources, int resId, boolean clear) {

		/* Clear array before reading, if needed */
        if (clear) this.clear();

		/* Get array of string from application resource */
        String[] values = resources.getStringArray(resId);

		/* Load data from array */
        for (int i = 0; i< values.length; i++) this.add(values[i]);

		/* Return number of element that added  */
        return values.length;

    }

    /** Load Data from Resources */
    public int LoadFromResource(Resources resources, int resId) {
        return this.LoadFromResource(resources, resId, true);
    }

    /** Load Data from Resources */
    public int LoadFromResource(Context context, int resId, boolean clear) {
        return this.LoadFromResource(context.getResources(), resId, clear);
    }

    /** Load Data from Resources */
    public int LoadFromResource(Context context, int clear) {
        return this.LoadFromResource(context.getResources(), clear, true);
    }

    /** Convert data to string */
    public String toString() {

		/* Create a StringBuilder Object */
        StringBuilder SBuilder = new StringBuilder();

		/* Fill a StringBuilder Object */
        for (int i = 0; i < this.size(); i++) SBuilder.append(this.get(i));

		/* Return a Value */
        return SBuilder.toString();

    }
}
