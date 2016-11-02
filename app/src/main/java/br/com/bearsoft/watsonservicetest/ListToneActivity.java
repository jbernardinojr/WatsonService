package br.com.bearsoft.watsonservicetest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListToneActivity extends AppCompatActivity{

    private ArrayAdapter<Tone> mToneAdapter;
    private ListView lv;
    private Tone mTone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Map<String, Integer> iconMap = new HashMap<>();

        iconMap.put("anger", R.drawable.anger);
        iconMap.put("disgust", R.drawable.disgust);
        iconMap.put("fear", R.drawable.fear);
        iconMap.put("joy", R.drawable.joy);
        iconMap.put("sadness", R.drawable.sadness);

        Intent intent = getIntent();
        String tonesAsString = intent.getStringExtra("list_as_string");
        Gson gson = new Gson();
        Type type = new TypeToken<List<Tone>>(){}.getType();
        final List<Tone> tones = gson.fromJson(tonesAsString, type);
        Collections.sort(tones);

        mToneAdapter = new ArrayAdapter<Tone>(
                getApplicationContext(),
                R.layout.list_item_tone,
                R.id.textTone,
                tones);

        MyCustomAdapter customAdapter = new MyCustomAdapter(getApplicationContext(), R.layout.list_item_tone);
        String sName;
        String sToneId;
        DecimalFormat format = new DecimalFormat("#.##");
        for (int i=0; i<tones.size(); i++) {
            sName = tones.get(i).getToneName();
            sToneId = tones.get(i).getToneId();
            mTone = new Tone(String.format("%.2f", tones.get(i).getScore()*100) + "% " + sName, sToneId, iconMap.get(sToneId));
            customAdapter.add(mTone);
        }

        mToneAdapter.notifyDataSetChanged();
        lv = (ListView) findViewById(R.id.listViewTone);
        lv.setAdapter(customAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), tones.get(position).getToneName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
