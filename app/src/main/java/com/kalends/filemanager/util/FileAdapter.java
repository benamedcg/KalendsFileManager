package com.kalends.filemanager.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kalends.filemanager.R;
import com.kalends.filemanager.pojo.FileInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class FileAdapter extends ArrayAdapter<FileInfo> {

    private  int resourceId;
    public FileAdapter(Context context, int textViewResourceId, List<FileInfo> objects){

        super(context,textViewResourceId,objects);
        this.resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        FileInfo filePojo = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView fileImage = view.findViewById(R.id.file_image);
        TextView fileName = view.findViewById(R.id.file_name);
        fileImage.setImageResource(filePojo.getImageId());
        fileName.setText(filePojo.getName());

        return view;
    }

}
