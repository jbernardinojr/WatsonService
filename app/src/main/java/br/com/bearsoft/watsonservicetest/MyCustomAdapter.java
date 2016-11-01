package br.com.bearsoft.watsonservicetest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by JBSJunior on 31/10/2016.
 */

public class MyCustomAdapter extends ArrayAdapter {

    private LayoutInflater mInflater;

    public MyCustomAdapter(Context context, int resource) {
        super(context, resource);
        mInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mRow = convertView;

        DataHandler mHandler;

        Log.d("MeuAdaptadorCustom", "getView " + position + " " + convertView);

        if (convertView==null) {
            mRow = mInflater.inflate(R.layout.list_item_tone, parent, false);
            mHandler = new DataHandler();
            mHandler.imagemIcone = (ImageView) mRow.findViewById(R.id.imageTone);
            mHandler.nomePersonagem = (TextView) mRow.findViewById(R.id.textTone);
            mRow.setTag(mHandler);
        } else {
            mHandler = (DataHandler) mRow.getTag();
        }

        Tone dataProvider = (Tone) this.getItem(position);
        mHandler.imagemIcone.setImageResource(dataProvider.getIcone());
        mHandler.nomePersonagem.setText(dataProvider.getToneName());
        return mRow;
    }

    private class DataHandler {
        ImageView imagemIcone;
        TextView nomePersonagem;
    }
}
