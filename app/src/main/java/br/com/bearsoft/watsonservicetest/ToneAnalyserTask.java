package br.com.bearsoft.watsonservicetest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbern_000 on 29/10/2016.
 */

public class ToneAnalyserTask extends AsyncTask <String, Void, List<Tone>> {

    private Context mContext;
    private ProgressDialog progressDialog;
    private String sToneAnalyser;
    private final String API_USER = "ab666063-34e1-4ce0-910e-51074c2a67de";
    private final String API_PWD  = "IlFusckbYdgh";
    private final String LOG_TAG = "ToneAnalyserTask";

    public ToneAnalyserTask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(mContext, "Download",
                "Waiting download data", true);
    }

    @Override
    protected List<Tone> doInBackground(String... strings) {

        if(strings.length == 0) {
            return null;
        }
        String sTextAnalyse = strings[0];
        ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
        service.setUsernameAndPassword(API_USER, API_PWD);
        ToneAnalysis tone = service.getTone(sTextAnalyse, null).execute();
        Log.d(LOG_TAG, "Service Response: " + tone.toString());

        return getToneScore(tone.toString());
    }

    private List<Tone> getToneScore (String toneJson) {

        List<Tone> tones = null;
        try {
            JSONObject json = new JSONObject(toneJson).getJSONObject("document_tone");
            JSONArray jsonArray = json.getJSONArray("tone_categories");

            JSONArray jsonTones = jsonArray.getJSONObject(0).getJSONArray("tones");

            Log.d(LOG_TAG, "OBJ=" + jsonTones.toString());


            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            tones = new ArrayList<>();

            for (int i = 0; i < jsonTones.length(); i++) {
                Tone tone = gson.fromJson(jsonTones.get(i).toString(), Tone.class);
                tones.add(tone);
                Log.d(LOG_TAG, i + "");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tones;

    }

    @Override
    protected void onPostExecute(List<Tone> tones) {
        super.onPostExecute(tones);
        progressDialog.dismiss();

        Gson gson = new Gson();
        String jsonTone = gson.toJson(tones);

        Intent intent = new Intent(mContext, ListToneActivity.class);
        intent.putExtra("list_as_string", jsonTone);
        mContext.startActivity(intent);
    }
}
