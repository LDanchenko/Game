package com.ldv.courseofexchange.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ldv.courseofexchange.R;
import com.ldv.courseofexchange.rest.RestService;
import com.ldv.courseofexchange.rest.models.PrivatBankCourse;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EFragment
public class FirstFragment extends Fragment {

    public View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.first_fragment, container, false);
        Button button = ((Button) rootView.findViewById(R.id.button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPrivatCurrentCourse();
            }
        });
        return rootView;
        //
    }

    @Background
    public void getPrivatCurrentCourse() {
        RestService restService = new RestService();
        Call<List<PrivatBankCourse>> storiesModel = restService.privatBankCurrentCourse();
        storiesModel.enqueue(new Callback<List<PrivatBankCourse>>() {
            @Override
            public void onResponse(Call<List<PrivatBankCourse>> call, Response<List<PrivatBankCourse>> response) {
                final List<PrivatBankCourse> storiesEntities = response.body();

                for (int i = 0; i < storiesEntities.size() - 1; i++) {
                    //перенести это в метод
                    // for (PrivatBankCourse course : storiesEntities) {
                    PrivatBankCourse course = storiesEntities.get(i);
                    PrivatBankCourse privat = new PrivatBankCourse();

                    privat.setBaseCcy(course.getBaseCcy());
                    String courseSale = course.getSale();
                    String Ccy = course.getCcy();
                    result(courseSale, Ccy);
                    Log.d("RESULT", "" + i);
                }
                // task.loadQuotes(storiesEntities);
            }

            @Override
            public void onFailure(Call<List<PrivatBankCourse>> call, Throwable t) {

            }


        });
    }

    @UiThread
    void result(String courseSale, String Ccy) {
        EditText editText = ((EditText) rootView.findViewById(R.id.edit1));
        EditText edit2Text = ((EditText) rootView.findViewById(R.id.edit2));
        edit2Text.setText(courseSale);
        editText.setText(Ccy);
    }
}