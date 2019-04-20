package dev.marcello.imusica.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.marcello.imusica.R;
import dev.marcello.imusica.model.PostsModel;
import dev.marcello.imusica.util.TimestampUtil;

/**
 * Marcello
 * 2019
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private IAdapter onRecyclerViewClick;
    private List<PostsModel> list;

    public PostsAdapter(List<PostsModel> posts, IAdapter onRecyclerViewClick){
        this.onRecyclerViewClick = onRecyclerViewClick;
        this.list = posts;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_post_item, viewGroup, false);
        return new PostsViewHolder(viewItem, onRecyclerViewClick);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder postsViewHolder, int i) {
        PostsModel post = list.get(i);
        postsViewHolder.textViewTitle.setText(post.getPost().getTitle());
        postsViewHolder.textViewAuthor.setText(post.getPost().getAuthor());
        postsViewHolder.textViewCreated.setText(TimestampUtil.convertTime(post.getPost().getCreated()));
        postsViewHolder.textViewComments.setText(String.valueOf(post.getPost().getComments()));
        postsViewHolder.textViewUps.setText(String.valueOf(post.getPost().getUps()));
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textViewTitle) protected TextView textViewTitle;
        @BindView(R.id.textViewAuthor) protected TextView textViewAuthor;
        @BindView(R.id.textViewCreated) protected TextView textViewCreated;
        @BindView(R.id.textViewComments) protected TextView textViewComments;
        @BindView(R.id.textViewUps) protected TextView textViewUps;

        private IAdapter onRecyclerViewClick;

        public PostsViewHolder(@NonNull View itemView, IAdapter onRecyclerViewClick) {
            super(itemView);
            this.onRecyclerViewClick = onRecyclerViewClick;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRecyclerViewClick.OnItemClick(getAdapterPosition());
        }
    }

}