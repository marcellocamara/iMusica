package dev.marcello.imusica.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.OnClick;
import dev.marcello.imusica.R;
import dev.marcello.imusica.ui.main.MainActivity;
import dev.marcello.imusica.ui.register.RegisterActivity;

/**
 * Marcello Câmara
 * 2019
 */

public class LoginActivity extends AppCompatActivity implements ILoginContract.View {

    @BindView(R.id.layoutEmail) protected TextInputLayout layoutEmail;
    @BindView(R.id.layoutPassword) protected TextInputLayout layoutPassword;

    @BindView(R.id.editTextEmail) protected TextInputEditText editTextEmail;
    @BindView(R.id.editTextPassword) protected TextInputEditText editTextPassword;

    @BindString(R.string.login) protected String login;
    @BindString(R.string.close) protected String close;
    @BindString(R.string.blank_email) protected String blank_email;
    @BindString(R.string.invalid_email) protected String invalid_email;
    @BindString(R.string.blank_password) protected String blank_password;

    private ILoginContract.Presenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        presenter = new LoginPresenter(this, this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
    }

    @OnClick(R.id.btnLogin)
    public void OnButtonLoginClick(){
        layoutEmail.setErrorEnabled(false);
        layoutPassword.setErrorEnabled(false);
        presenter.OnLoginRequest(
                editTextEmail.getText().toString().trim(),
                editTextPassword.getText().toString().trim()
        );
    }

    @OnClick(R.id.btnRegister)
    public void OnButtonRegisterClick(){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void OnEmailEmpty() {
        layoutEmail.setError(blank_email);
        layoutEmail.setErrorEnabled(true);
    }

    @Override
    public void OnPasswordEmpty() {
        layoutPassword.setError(blank_password);
        layoutPassword.setErrorEnabled(true);
    }

    @Override
    public void OnEmailInvalid() {
        layoutEmail.setError(invalid_email);
        layoutEmail.setErrorEnabled(true);
    }

    @Override
    public void OnLoginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void OnLoginFailure(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(login);
        builder.setCancelable(false);
        builder.setMessage(message);
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
    protected void onStart() {
        super.onStart();
        presenter.OnCheckLoggedIn();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.OnDestroy();
    }

}