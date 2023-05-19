package com.example.shopfood.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopfood.Fragment.CreateAcountFragment;
import com.example.shopfood.Fragment.LoginFragment;

public class ViewPager2Adapte_Acount extends FragmentStateAdapter {
    public ViewPager2Adapte_Acount(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 1 : return  new LoginFragment();

            default:  return new CreateAcountFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
