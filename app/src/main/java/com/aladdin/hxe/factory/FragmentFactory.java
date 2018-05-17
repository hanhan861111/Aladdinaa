package com.aladdin.hxe.factory;

import android.support.v4.app.Fragment;

import com.aladdin.hxe.fragment.AllOrderFragment;
import com.aladdin.hxe.fragment.CallNumFragment;
import com.aladdin.hxe.fragment.CancelFragment;
import com.aladdin.hxe.fragment.FinishFragment;
import com.aladdin.hxe.fragment.InquireAllOrderFragment;
import com.aladdin.hxe.fragment.InquireCancelOrderFragment;
import com.aladdin.hxe.fragment.InquireFinishOrderFragment;
import com.aladdin.hxe.fragment.InquireFragment;
import com.aladdin.hxe.fragment.InquireIngOrderFragment;
import com.aladdin.hxe.fragment.ManageFragment;
import com.aladdin.hxe.fragment.SetFragment;
import com.aladdin.hxe.fragment.NewOrderFragment;
import com.aladdin.hxe.fragment.PendingFragment;
import com.aladdin.hxe.fragment.RefundFragment;

import java.util.HashMap;

/**
 * Created by ${韩永光} on on 2017/11/17 0017 10:51..
 */

public class FragmentFactory {
    public static HashMap<Integer, Fragment> fragmentHashMap = new HashMap<>();

    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentHashMap.get(position);
        if (fragment != null) {
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new PendingFragment();
                break;
            case 1:
                fragment = new InquireFragment();
                break;
            case 2:
                fragment = new ManageFragment();
                break;
            case 3:
                fragment = new SetFragment();
                break;
            case 4:
                fragment = new NewOrderFragment();
                break;
            case 5:
                fragment = new CallNumFragment();
                break;
            case 6:
                fragment = new CancelFragment();
                break;
            case 7:
                fragment = new RefundFragment();
                break;
            case 8:
                fragment = new FinishFragment();
                break;
            case 9:
                fragment = new AllOrderFragment();
                break;
            case 10:
                fragment = new InquireAllOrderFragment();
                break;
            case 11:
                fragment = new InquireIngOrderFragment();
                break;
            case 12:
                fragment = new InquireFinishOrderFragment();
                break;
            case 13:
                fragment = new InquireCancelOrderFragment();
                break;


        }
        fragmentHashMap.put(position, fragment);
        return fragment;
    }
}
