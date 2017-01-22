package ru.geekbrains.android1.less5_4;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * ListView UI object
     */
    private ListView lvMain = null;

    /**
     * Private field for store List Data
     */
    private StringArray ListData = null;

    ListViewAdapter lvAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

		/* Invoke parent method */
        super.onCreate(savedInstanceState);

		/* Setting up layout */
        setContentView(R.layout.activity_main);

		/* Create List Data */
        ListData = new StringArray(getResources(), R.array.list_items);

		/* Load UI components */
        lvMain = (ListView) findViewById(R.id.lvMain);

		/* Create custom Adapter */
        lvAdapter = new ListViewAdapter(this, ListData, ItemType.getDef());

	    /* Setting up adapter for ListView (lvMain) */
        lvMain.setAdapter(lvAdapter);

        lvMain.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position,
                                    long id) {
                final EditText edittext = new EditText(MainActivity.this);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Отредактируйте текст заметки:");
                builder.setView(edittext);
                builder.setPositiveButton(R.string.dlg_btn_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //((ListViewAdapter) lvMain.getAdapter()).removeItem(position);
                        String newTextValue = edittext.getText().toString();
                        // и вот дальше надо кудато воткнуть newTextValue...

                    }
                });

                builder.setNegativeButton(R.string.dlg_btn_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //return;
                    }
                });

                AlertDialog Dialog = builder.create();
                Dialog.show();
                return;
            }
        });

        lvMain.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.dlg_tl);
                builder.setMessage(R.string.dlg_msg);
                builder.setPositiveButton(R.string.dlg_btn_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((ListViewAdapter) lvMain.getAdapter()).removeItem(position);
                    }
                });

                builder.setNegativeButton(R.string.dlg_btn_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                });

                AlertDialog Dialog = builder.create();
                Dialog.show();

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

		 /* Handle item selection */
        switch (item.getItemId()) {

		 	/* Menu "Add" */
            case R.id.menu_add_item: {
                ListData.add("Новая заметка");
                ((BaseAdapter) lvMain.getAdapter()).notifyDataSetChanged();
                return true;
            }

			 /* Menu "Clear all" */
            case R.id.menu_clear_items: {
                ((ListViewAdapter) lvMain.getAdapter()).clear();
                return true;
            }

			/* Menu "Change type of View" */
            case R.id.menu_ch_type: {
                ((ListViewAdapter) lvMain.getAdapter()).changeItemType();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void ShowMessage(String Msg) {
        (Toast.makeText(this, Msg, Toast.LENGTH_SHORT)).show();
    }
}
