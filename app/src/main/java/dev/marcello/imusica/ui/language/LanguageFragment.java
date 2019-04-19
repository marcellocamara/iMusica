package dev.marcello.imusica.ui.language;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static dev.marcello.imusica.util.LanguageUtil.setLanguage;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dev.marcello.imusica.R;
import dev.marcello.imusica.ui.login.LoginActivity;

/**
 * Marcello
 * 2019
 */

public class LanguageFragment extends Fragment {

    private Unbinder unbinder;

    public LanguageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_language, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btnEnglish)
    public void OnButtonEnglishClick() {
        changeLanguage("en");
    }

    @OnClick(R.id.btnPortuguese)
    public void OnButtonPortugueseClick() {
        changeLanguage("pt");
    }

    private void changeLanguage(String lang) {
        setLanguage(lang, getContext());
        getActivity().finish();
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}