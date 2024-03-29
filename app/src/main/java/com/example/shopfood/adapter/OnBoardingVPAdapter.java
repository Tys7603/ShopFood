package com.example.shopfood.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.shopfood.fragment.loginFragment.AccountFragment;
import com.example.shopfood.fragment.onboardingFragment.OnboardingFragment;
import com.example.shopfood.fragment.onboardingFragment.OnboardingFragment1;

public class OnBoardingVPAdapter extends FragmentStateAdapter {
    public OnBoardingVPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1 : return new OnboardingFragment1();
            case 2 : return new AccountFragment();
            default : return new OnboardingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
