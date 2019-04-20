package dev.marcello.imusica.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dev.marcello.imusica.R;
import dev.marcello.imusica.adapter.IAdapter;
import dev.marcello.imusica.adapter.PostsAdapter;
import dev.marcello.imusica.model.PostsModel;

/**
 * Marcello
 * 2019
 */

public class HomeFragment extends Fragment implements IHome.View, IAdapter {

    @BindView(R.id.recyclerView) protected RecyclerView recyclerView;

    @BindString(R.string.okhttp) protected String okhttp;
    @BindString(R.string.close) protected String close;
    @BindString(R.string.loading) protected String loading;
    @BindString(R.string.okhttp_failure) protected String okhttp_failure;

    private IHome.Presenter presenter;
    private ProgressDialog progressDialog;
    private List<PostsModel> posts;
    private AlertDialog.Builder builder;
    private Unbinder unbinder;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, view);

        presenter = new HomePresenter(this, getContext());

        builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Posts");
        builder.setCancelable(false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(loading);
        progressDialog.setCancelable(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void OnCheckUrlRequestSuccess() {
        presenter.DoGetPostsRequest();
    }

    @Override
    public void OnCheckUrlRequestFailure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(okhttp);
        builder.setMessage(okhttp_failure);
        builder.setCancelable(false);
        builder.setPositiveButton(close, null);
        builder.show();
    }

    @Override
    public void OnGetPostsRequestSuccess(List<PostsModel> list) {
        //Allow recyclerView data
        this.posts = list;
        PostsAdapter adapter = new PostsAdapter(this.posts, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnItemClick(int position) {
        Toast.makeText(getContext(), "Item clicked: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnGetPostsRequestFailure(String message) {
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
    public void onStart() {
        super.onStart();
        presenter.DoCheckUrlRequest();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.OnDestroy();
        unbinder.unbind();
    }

}