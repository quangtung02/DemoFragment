package dmfragment.org.com.demofragment.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import dmfragment.org.com.demofragment.BaseActivity;

/**
 * Created by nguyen.quang.tung on 7/6/2016.
 */
public class BaseFragment extends Fragment {

    protected View mView;
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = (BaseActivity) getActivity();
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.gc();
    }
}
