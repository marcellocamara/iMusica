package dev.marcello.imusica.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dev.marcello.imusica.R;
import dev.marcello.imusica.adapter.IAdapter;
import dev.marcello.imusica.adapter.PostsAdapter;
import dev.marcello.imusica.model.Post;
import dev.marcello.imusica.model.PostsModel;
import dev.marcello.imusica.ui.dialog.IDialog;
import dev.marcello.imusica.ui.dialog.PostDialog;
import dev.marcello.imusica.ui.main.IMainContract;

/**
 * Marcello
 * 2019
 */

public class HomeFragment extends Fragment implements IHome.View, IAdapter, IDialog.Post {

    @BindView(R.id.recyclerView) protected RecyclerView recyclerView;

    @BindString(R.string.posts) protected String posts;
    @BindString(R.string.okhttp) protected String okhttp;
    @BindString(R.string.close) protected String close;
    @BindString(R.string.loading) protected String loading;
    @BindString(R.string.no_changes) protected String no_changes;
    @BindString(R.string.okhttp_failure) protected String okhttp_failure;
    @BindString(R.string.create_failure) protected String create_failure;
    @BindString(R.string.edit_failure) protected String edit_failure;
    @BindString(R.string.create_success) protected String create_success;
    @BindString(R.string.edit_post_success) protected String edit_post_success;
    @BindString(R.string.delete_post_success) protected String delete_post_success;

    private IMainContract.ScreenTitle screenTitle;
    private IHome.Presenter presenter;
    private ProgressDialog progressDialog;
    private PostsAdapter adapter;
    private List<PostsModel> list;
    private int lastPosition;
    private String userName = "", titleUpdated = "";
    private AlertDialog.Builder builder;
    private Unbinder unbinder;

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenTitle.SetTitle(getString(R.string.home));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        unbinder = ButterKnife.bind(this, view);

        presenter = new HomePresenter(this, getContext());

        builder = new AlertDialog.Builder(getContext());
        builder.setTitle(posts);
        builder.setCancelable(false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(loading);
        progressDialog.setCancelable(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        presenter.DoCheckUrlRequest();

        return view;
    }

    @OnClick(R.id.faButton)
    public void OnFloatingActionButtonClick() {
        PostDialog dialog = new PostDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean("new", true);
        bundle.putString("name", userName);
        dialog.setArguments(bundle);
        dialog.setTargetFragment(HomeFragment.this, 1);
        dialog.show(getActivity().getSupportFragmentManager(), "PostDialog");
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
    public void DoGetUserNameRequestSuccess(String name) {
        this.userName = name;
    }

    @Override
    public void OnGetPostsRequestSuccess(List<PostsModel> list) {
        //Allows show data on recyclerView
        this.list = list;
        adapter = new PostsAdapter(this.list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnItemClick(int position) {
        lastPosition = position;
        PostDialog postDialog = new PostDialog();
        Bundle bundle = new Bundle();
        bundle.putParcelable("post", list.get(position).getPost());
        postDialog.setArguments(bundle);
        postDialog.setTargetFragment(HomeFragment.this, 1);
        postDialog.show(getActivity().getSupportFragmentManager(), "PostDialog");
    }

    @Override
    public void OnCreate(String title, long created) {
        presenter.DoCreatePostRequest(title, userName, created);
    }

    @Override
    public void OnUpdate(String title, String author, long created, int ups, int comments) {
        titleUpdated = title;
        String postTitle = this.list.get(lastPosition).getPost().getTitle();
        presenter.DoUpdatePostRequest(postTitle, title, author, created, ups, comments);
    }

    @Override
    public void OnDelete(long created) {
        presenter.DoDeletePostRequest(created);
    }

    @Override
    public void OnGetPostsRequestFailure(String message) {
        builder.setMessage(message);
        builder.setPositiveButton(close, null);
        builder.show();
    }

    @Override
    public void OnCreatePostRequestFailure() {
        builder.setMessage(create_failure);
        builder.setPositiveButton(close, null);
        builder.show();
    }

    @Override
    public void OnCreatePostRequestSuccess(Post post) {
        this.list.add(new PostsModel(post));
        adapter.notifyDataSetChanged();
        builder.setMessage(
                create_success
        );
        builder.setPositiveButton(close, null);
        builder.show();
    }

    @Override
    public void OnUpdatePostRequestSuccess(Post post) {
        this.list.get(lastPosition).getPost().setTitle(titleUpdated);
        adapter.notifyDataSetChanged();
        builder.setMessage(
                edit_post_success
        );
        builder.setPositiveButton(close, null);
        builder.show();
    }

    @Override
    public void OnUpdatePostRequestNoChanges() {
        builder.setMessage(no_changes);
        builder.setPositiveButton(close, null);
        builder.show();
    }

    @Override
    public void OnUpdatePostRequestFailure() {
        builder.setMessage(edit_failure);
        builder.setPositiveButton(close, null);
        builder.show();
    }

    @Override
    public void OnDeletePostRequestSuccess() {
        this.list.remove(lastPosition);
        adapter.notifyDataSetChanged();
        builder.setMessage(delete_post_success);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        screenTitle = (IMainContract.ScreenTitle) getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.DoGetUserNameRequest();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.OnDestroy();
        unbinder.unbind();
    }

}