package com.crisFiApps.ubuntufourclocks.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.crisFiApps.ubuntufourclocks.R;
import com.crisFiApps.ubuntufourclocks.items.ItemsMenus;
import java.util.List;

/**
 * Created by Jorgefc82 on 23/12/2015.
 */
public class AdaptadorLista extends ArrayAdapter {

    public AdaptadorLista(Context context, List objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.itemmenu, null);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        TextView name = (TextView) convertView.findViewById(R.id.name);

        ItemsMenus item = (ItemsMenus) getItem(position);
        icon.setImageResource(item.getIconId());
        name.setText(item.getName());

        return convertView;
    }
    //Se sobreescribe método para que los items del listview no sean seleccionables
    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
