package br.com.bearsoft.watsonservicetest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragmentToneAnalyser extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tone_analyser, container, false);
        //Initialize UI components
        final EditText textAnalyse = (EditText) rootView.findViewById(R.id.editTextAnalyse);
        Button btnAnalyse = (Button) rootView.findViewById(R.id.btnAnalyse);

        btnAnalyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToneAnalyserTask toneAnalyserTask = new ToneAnalyserTask(getContext());
                toneAnalyserTask.execute(textAnalyse.getText().toString().trim());
            }
        });

        return rootView;
    }
}
