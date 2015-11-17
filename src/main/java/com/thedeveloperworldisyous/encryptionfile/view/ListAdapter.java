package com.thedeveloperworldisyous.encryptionfile.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thedeveloperworldisyous.encryptionfile.R;

import java.util.List;

/**
 * Created by javiergonzalezcabezas on 16/11/15.
 */
public class ListAdapter extends ArrayAdapter<String> {

    private final List<String> mList;
    private final Activity mContext;

    public ListAdapter(Activity context, List<String> list) {
        super(context, -1, list);
        this.mContext = context;
        this.mList = list;
    }

    static class ViewHolder {
        protected TextView text;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        int sizeList = mList.size()-1;

        if (convertView == null) {
            LayoutInflater inflater = mContext.getLayoutInflater();
            view = inflater.inflate(R.layout.row_list, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) view.findViewById(R.id.row_list_text_view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();
        holder.text.setText(mList.get(sizeList-position).toString());

        return view;
    }
}
