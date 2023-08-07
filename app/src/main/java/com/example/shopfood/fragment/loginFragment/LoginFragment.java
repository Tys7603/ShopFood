package com.example.shopfood.fragment.loginFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shopfood.activity.main.MainActivity;
import com.example.shopfood.interfacee.AccountInterface;
import com.example.shopfood.modal.User;
import com.example.shopfood.R;
import com.example.shopfood.api.ApiService;
import com.example.shopfood.presenter.AccountPresenter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment implements AccountInterface {
    CardView btnLogin;
    TextInputEditText  tvEmail, tvPass;
    AccountPresenter mAccountPresenter;
    public LoginFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        onClick();
    }
    private void initUI(View view) {
        mAccountPresenter = new AccountPresenter(this);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvEmail = view.findViewById(R.id.tv_email);
        tvPass = view.findViewById(R.id.tv_pass);
    }

    private void onClick() {
        btnLogin.setOnClickListener(view1 -> mAccountPresenter.login(tvEmail.getText().toString(), tvPass.getText().toString(), getContext()));
    }

    @Override
    public void onMessage(boolean check) {
        if (check){
            Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMessageValidate() {
        Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMessageValidateEmail() {

    }

    @Override
    public void starActivity() {
        startActivity(new Intent(getActivity(), MainActivity.class));
    }

    @Override
    public void clearText() {
        tvEmail.setText("");
        tvPass.setText("");
    }
}