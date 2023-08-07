package com.example.shopfood.fragment.loginFragment;

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

import com.example.shopfood.interfacee.AccountInterface;
import com.example.shopfood.modal.StatusUser;
import com.example.shopfood.modal.User;
import com.example.shopfood.R;
import com.example.shopfood.api.ApiService;
import com.example.shopfood.api.Constant;
import com.example.shopfood.presenter.AccountPresenter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateFragment extends Fragment implements AccountInterface {
    TextInputEditText tvFullName, tvEmail, tvPass;
    CardView btnCreateAccount;
    AccountPresenter mAccountPresenter;

    public CreateFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_acount_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
        onClick();
    }
    public void initUI(View view){
        mAccountPresenter = new AccountPresenter(this);
        tvFullName = view.findViewById(R.id.tv_cFullName);
        tvEmail = view.findViewById(R.id.tv_cEmail);
        tvPass = view.findViewById(R.id.tv_cPass);
        btnCreateAccount = view.findViewById(R.id.btnCreateAccount);
    }
    public void onClick(){
        btnCreateAccount.setOnClickListener(view1 ->mAccountPresenter.createUser(
                tvEmail.getText().toString(), tvPass.getText().toString(), tvFullName.getText().toString(), getContext()));
    }

    @Override
    public void onMessage(boolean check) {
        if (check){
            Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
            tvEmail.setError("Tồn tại");
        }
    }

    @Override
    public void onMessageValidate() {
        Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMessageValidateEmail() {
        Toast.makeText(getActivity(), "Email cần đúng định dạng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void starActivity() {
    }
    @Override
    public void clearText() {
        tvEmail.setText("");
        tvPass.setText("");
        tvFullName.setText("");
    }
}