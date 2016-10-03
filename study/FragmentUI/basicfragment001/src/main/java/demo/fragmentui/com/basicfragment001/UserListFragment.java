package demo.fragmentui.com.basicfragment001;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;

import demo.fragmentui.com.basicfragment001.db.DB;

/**
 * Created by anybus on 16/10/2.
 */
public class UserListFragment extends ListFragment {
    private SimpleCursorAdapter adapter;
    DB db;
    SQLiteDatabase dbWriter,dbReader;
    private AdapterView.OnItemLongClickListener listViewLongClicked = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            new AlertDialog.Builder(getActivity()).setTitle("提醒").setMessage("您确定要删除该项吗?").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Cursor cusor = adapter.getCursor();
                    cusor.moveToPosition(position);
                    int _id = cusor.getInt(cusor.getColumnIndex("_id"));
                    dbWriter.delete("user","_id=?",new String[]{_id+""});
                    getFragmentManager().beginTransaction()
                            .replace(R.id.listLayout,new UserListFragment()).commit();
                }
            }).setNegativeButton("取消",null).show();

            return true;
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = new DB(getContext());
        dbReader = db.getReadableDatabase();
        dbWriter = db.getWritableDatabase();
        Cursor cursor = dbReader.query("user",null,null,null,null,null,null,null);
        adapter = new SimpleCursorAdapter(getContext(),R.layout.row,cursor,new String[]{"_id","name","sex"},
                new int[]{R.id.tvId,R.id.tvName,R.id.tvSex});
        setListAdapter(adapter);
        getListView().setOnItemLongClickListener(listViewLongClicked);
    }
}
