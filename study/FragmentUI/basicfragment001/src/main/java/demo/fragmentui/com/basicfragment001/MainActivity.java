package demo.fragmentui.com.basicfragment001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.addLayout,new AddFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.listLayout,new UserListFragment()).commit();

    }
}
