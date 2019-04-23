package dev.marcello.imusica.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import dev.marcello.imusica.R;
import dev.marcello.imusica.model.Post;
import dev.marcello.imusica.util.TimestampUtil;

/**
 * Marcello
 * 2019
 */

public class PostDialog extends DialogFragment {

    @BindView(R.id.editTextTitle) protected EditText editTextTitle;
    @BindView(R.id.textViewAuthor) protected TextView textViewAuthor;
    @BindView(R.id.textViewCreated) protected TextView textViewCreated;
    @BindView(R.id.textViewUps) protected TextView textViewUps;
    @BindView(R.id.textViewComments) protected TextView textViewComments;

    private IDialog.Post dialogListener;
    private Unbinder unbinder;
    private String title = "", author = "";
    private long created = 0;
    private int ups = 0, comments = 0;
    private boolean create = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            create = bundle.getBoolean("new");
            if (create) {
                created = System.currentTimeMillis() / 1000L;
                author = bundle.getString("name");
            } else {
                Post post = bundle.getParcelable("post");
                title = post.getTitle();
                author = post.getAuthor();
                created = post.getCreated();
                ups = post.getUps();
                comments = post.getComments();
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = setBuilder(create);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_post, null);

        unbinder = ButterKnife.bind(this, view);

        editTextTitle.setText(title);
        editTextTitle.setSelection(editTextTitle.getText().length());
        textViewAuthor.setText(author);
        textViewCreated.setText(TimestampUtil.convertTime(created));
        textViewUps.setText(String.valueOf(ups));
        textViewComments.setText(String.valueOf(comments));

        builder.setView(view);

        return builder.create();
    }

    private AlertDialog.Builder setBuilder(boolean value) {
        if (value) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.new_post)
                    .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UIUtil.hideKeyboard(getActivity());
                            dialogListener.OnCreate(editTextTitle.getText().toString(), created);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton(R.string.cancel, null);
        } else {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.post)
                    .setPositiveButton(getContext().getString(R.string.close), null)
                    .setNeutralButton(getContext().getString(R.string.delete), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UIUtil.hideKeyboard(getActivity());
                            dialogListener.OnDelete(created);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton(getContext().getString(R.string.edit), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UIUtil.hideKeyboard(getActivity());
                            dialogListener.OnUpdate(
                                    editTextTitle.getText().toString(),
                                    textViewAuthor.getText().toString(),
                                    created,
                                    ups,
                                    comments
                            );
                            dialog.dismiss();
                        }
                    });
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dialogListener = (IDialog.Post) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement IDialog.Post");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}