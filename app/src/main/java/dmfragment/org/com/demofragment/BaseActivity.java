package dmfragment.org.com.demofragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.lang.reflect.Constructor;

import dmfragment.org.com.demofragment.fragment.BaseFragment;

/**
 * Created by nguyen.quang.tung on 7/6/2016.
 */
public class BaseActivity extends FragmentActivity implements FragmentManager.OnBackStackChangedListener {

    private int mBackStackEntryCount;

    @Override
    public void onBackStackChanged() {
        Log.d("AAA", "onBackStackChanged");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        popFragment();
    }

    /**
     * Create an instance for fragment
     *
     * @param className
     * @return
     */
    protected BaseFragment createInstanceFragment(Class<? extends BaseFragment> className) {
        try {
            Constructor<? extends BaseFragment> constructor = className.getConstructor(new Class[]{});
            BaseFragment fragment = constructor.newInstance(new Object[]{});
            return fragment;
        } catch (Exception e) {
            throw new IllegalStateException("Error initial class fragment " + e);
        }
    }

    /**
     * Push a fragment to backStack and show it
     *
     * @param fragmentClass
     * @return
     */
    public BaseFragment pushFragment(Class fragmentClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BaseFragment currentFragment = (BaseFragment) fragmentManager.findFragmentByTag("" + fragmentManager.getBackStackEntryCount());

        BaseFragment fragment = createInstanceFragment(fragmentClass);

        if (currentFragment != null) fragmentTransaction.hide(currentFragment);

        fragmentTransaction.add(R.id.container, fragment, "" + (fragmentManager.getBackStackEntryCount() + 1));

        // add new fragment to back Stack
        fragmentTransaction.addToBackStack(fragmentClass.getName());
        fragmentTransaction.commit();
        Log.d("AAA", "push " + fragmentManager.getBackStackEntryCount());
        return fragment;
    }

    /**
     * Pop a fragment from backStack
     */
    protected void popFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mBackStackEntryCount = fragmentManager.getBackStackEntryCount();
        if (mBackStackEntryCount >= 1) {
            fragmentManager.executePendingTransactions();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            BaseFragment currentFragment = (BaseFragment) fragmentManager.findFragmentByTag("" + mBackStackEntryCount);
            BaseFragment nextFragment = (BaseFragment) fragmentManager.findFragmentByTag("" + (mBackStackEntryCount - 1));

            Log.d("AAA", "currentFragment " + currentFragment + mBackStackEntryCount);
            Log.d("AAA", "nextFragment " + nextFragment + (mBackStackEntryCount - 1));
            if (nextFragment == null) {
                finish();
                return;
            }

            fragmentTransaction.remove(currentFragment);
            fragmentTransaction.show(nextFragment);
            fragmentManager.popBackStack();
            fragmentTransaction.commit();
        } else {
            super.onBackPressed();
        }
    }
}
