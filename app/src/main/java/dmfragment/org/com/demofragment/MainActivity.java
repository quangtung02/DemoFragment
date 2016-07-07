package dmfragment.org.com.demofragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dmfragment.org.com.demofragment.fragment.A_Fragment;
import dmfragment.org.com.demofragment.fragment.B_Fragment;
import dmfragment.org.com.demofragment.fragment.C_Fragment;

public class MainActivity extends BaseActivity {

    private Button mBtnAddA;
    private Button mBtnAddB;
    private Button mBtnAddC;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAddA = (Button) findViewById(R.id.btn_add_frm_a);
        mBtnAddB = (Button) findViewById(R.id.btn_add_frm_b);
        mBtnAddC = (Button) findViewById(R.id.btn_add_frm_c);

        mBtnAddA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedInstanceState == null) {
                    pushFragment(A_Fragment.class);
                }
            }
        });

        mBtnAddB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(B_Fragment.class);
            }
        });

        mBtnAddC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(C_Fragment.class);
            }
        });
    }
}
