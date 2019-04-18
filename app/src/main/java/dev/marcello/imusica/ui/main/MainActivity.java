package dev.marcello.imusica.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import butterknife.BindString;
import butterknife.BindView;

import butterknife.ButterKnife;
import dev.marcello.imusica.R;
import dev.marcello.imusica.ui.home.HomeFragment;
import dev.marcello.imusica.ui.login.LoginActivity;

/**
 * Marcello Câmara
 * 2019
 */

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IMainContract.View {

    @BindView(R.id.navigationView)
    protected NavigationView navigationView;

    @BindView(R.id.drawerLayout)
    protected DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindString(R.string.logout)
    protected String logout;
    @BindString(R.string.close)
    protected String close;

    private IMainContract.Presenter presenter;
    private AlertDialog.Builder builder;
    private TextView textViewUserName, textViewUserEmail;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(null);

        presenter = new MainPresenter(this, this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        textViewUserName = headerView.findViewById(R.id.textViewUserName);
        textViewUserEmail = headerView.findViewById(R.id.textViewUserEmail);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
        navigationView.setCheckedItem(R.id.option1);

        builder = new AlertDialog.Builder(this);
        builder.setTitle(logout);
        builder.setCancelable(false);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout: {
                presenter.OnLogoutRequest();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option1: {
                Toast.makeText(this, "Option1 pressed.", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.option2: {
                Toast.makeText(this, "Option2 pressed.", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.option3: {
                Toast.makeText(this, "Option3 pressed.", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void OnUserDataRequestSuccess(String name, String email) {
        textViewUserName.setText(name);
        textViewUserEmail.setText(email);
    }

    @Override
    public void OnLogoutSuccess() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void OnLogoutFailure(String message) {
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
        presenter.OnUserDataRequest();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.OnDestroy();
    }

}