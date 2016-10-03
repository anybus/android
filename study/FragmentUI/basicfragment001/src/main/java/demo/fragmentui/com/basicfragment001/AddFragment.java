package demo.fragmentui.com.basicfragment001;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import demo.fragmentui.com.basicfragment001.db.DB;

/**
 * Created by anybus on 16/10/2.
 */
public class AddFragment extends Fragment {
    private EditText editName;
    private RadioGroup sex;
    private RadioButton man,female;
    private Button btnAdd;
    private DB db;
    private SQLiteDatabase dbWriter;
    private String selectedSex;
    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!checkEmpty()){
                return;
            }
            ContentValues c = new ContentValues();
            c.put("name",editName.getText().toString());
            c.put("sex",selectedSex);
            dbWriter.insert("user",null,c);
            refreshListView();

        }
    };
    private RadioGroup.OnCheckedChangeListener mChangeRadio = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == man.getId()){
                selectedSex = man.getText().toString();
            }
            if (checkedId == female.getId()){
                selectedSex = female.getText().toString();
            }
        }
    };



    private boolean checkEmpty(){
        if (TextUtils.isEmpty(editName.getText())){
            Toast.makeText(getContext(),"姓名不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!man.isChecked() && !female.isChecked()){
            Toast.makeText(getContext(),"性别不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_layout,container,false);
        editName = (EditText) view.findViewById(R.id.edName);
        sex = (RadioGroup) view.findViewById(R.id.group_sex);
        man = (RadioButton) view.findViewById(R.id.radio_man);
        female = (RadioButton) view.findViewById(R.id.radio_female);
        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        db = new DB(getContext());
        dbWriter = db.getWritableDatabase();
        sex.setOnCheckedChangeListener(mChangeRadio);
        btnAdd.setOnClickListener(btnListener);

        return view;
    }

    private void refreshListView(){
        getFragmentManager().beginTransaction()
                .replace(R.id.listLayout,new UserListFragment()).commit();
        editName.setText("");
        editName.clearFocus();
        sex.clearCheck();
    }

}
