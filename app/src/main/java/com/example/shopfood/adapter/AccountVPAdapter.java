package com.example.shopfood.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopfood.fragment.loginFragment.CreateFragment;
import com.example.shopfood.fragment.loginFragment.LoginFragment;

public class AccountVPAdapter extends FragmentStateAdapter {
    public AccountVPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 1 : return  new LoginFragment();

            default:  return new CreateFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
