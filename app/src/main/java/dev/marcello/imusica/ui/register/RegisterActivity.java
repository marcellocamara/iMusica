package dev.marcello.imusica.ui.register;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Objects;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import dev.marcello.imusica.R;

/**
 * Marcello
 * 2019
 */

public class RegisterActivity extends AppCompatActivity implements IRegisterContract.View {

    @BindView(R.id.toolbar) protected Toolbar toolbar;

    @BindView(R.id.layoutEmail) protected TextInputLayout layoutEmail;
    @BindView(R.id.layoutName) protected TextInputLayout layoutName;
    @BindView(R.id.layoutPassword1) protected TextInputLayout layoutPassword1;
    @BindView(R.id.layoutPassword2) protected TextInputLayout layoutPassword2;

    @BindView(R.id.editTextEmail) protected TextInputEditText editTextEmail;
    @BindView(R.id.editTextName) protected TextInputEditText editTextName;
    @BindView(R.id.editTextPassword1) protected TextInputEditText editTextPassword1;
    @BindView(R.id.editTextPassword2) protected TextInputEditText editTextPassword2;

    @BindString(R.string.register) protected String register;
    @BindString(R.string.close) protected String close;
    @BindString(R.string.invalid_email) protected String invalid_email;
    @BindString(R.string.blank_email) protected String blank_email;
    @BindString(R.string.blank_name) protected String blank_name;
    @BindString(R.string.blank_password) protected String blank_password;
    @BindString(R.string.password_dont_match) protected String password_dont_match;
    @BindString(R.string.register_success) protected String register_success;

    private IRegisterContract.Presenter presenter;
    private AlertDialog.Builder builder;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        presenter = new RegisterPresenter(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        builder = new AlertDialog.Builder(this);
        builder.setTitle(register);
        builder.setCancelable(false);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
    }

    @OnClick(R.id.btnRegister)
    public void OnButtonRegisterClick(){
        layoutEmail.setErrorEnabled(false);
        layoutName.setErrorEnabled(false);
        layoutPassword1.setErrorEnabled(false);
        layoutPassword2.setErrorEnabled(false);
        presenter.OnRegisterRequest(
                editTextEmail.getText().toString().trim(),
                editTextName.getText().toString().trim(),
                editTextPassword1.getText().toString().trim(),
                editTextPassword2.getText().toString().trim()
        );
    }

    @Override
    public void OnEmailInvalid() {
        layoutEmail.setErrorEnabled(true);
        layoutEmail.setError(invalid_email);
    }

    @Override
    public void OnEmailEmpty() {
        layoutEmail.setErrorEnabled(true);
        layoutEmail.setError(blank_email);
    }

    @Override
    public void OnNameEmpty() {
        layoutName.setErrorEnabled(true);
        layoutName.setError(blank_name);
    }

    @Override
    public void OnPassword1Empty() {
        layoutPassword1.setErrorEnabled(true);
        layoutPassword1.setError(blank_password);
    }

    @Override
    public void OnPassword2Empty() {
        layoutPassword2.setErrorEnabled(true);
        layoutPassword2.setError(blank_password);
    }

    @Override
    public void OnPasswordNotMatch() {
        layoutPassword2.setErrorEnabled(true);
        layoutPassword2.setError(password_dont_match);
    }

    @Override
    public void OnRegisterSuccess() {
        builder.setMessage(register_success);
        builder.setPositiveButton(close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RegisterActivity.this.finish();
            }
        });
        builder.show();
    }

    @Override
    public void OnRegisterFailure() {
        builder.setMessage(R.string.failure);
        builder.setPositiveButton(close, null);
        builder.show();
    }

    @Override
    public void ShowProgress() {
        progressDialog.show();
    }

    @Override
    public void HideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}