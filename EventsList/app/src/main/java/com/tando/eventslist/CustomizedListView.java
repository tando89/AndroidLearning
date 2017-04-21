package com.tando.eventslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by tando on 4/21/17.
 */

public class CustomizedListView extends BaseAdapter {
    Context contextLst;

    LayoutInflater layoutInflater;

    String[] eventNum = {"01/17/2017", "02/17/2017", "03/17/2017", "04/17/2017", "05/17/2017"
                         ,"06/17/2017", "07/17/2017", "08/17/2017", "09/17/2017"};

    String[] eventNames = {"Event0", "Event1", "Event2", "Event3", "Event4", "Event5",
                           "Event6", "Event7", "Event8"};
    String[] eventHosts = {"Host0", "Host1", "Host2", "Host3", "Host4", "Host5",
            "Host6", "Host7", "Host8"};
    String[] eventDescs = {"Desc0", "Desc1", "Desc2", "Desc3", "Desc4", "Desc5",
            "Desc6", "Desc7", "Desc8"};

    //constructor
    //values of context
    public CustomizedListView(Context contextLst) {
        this.contextLst = contextLst;
        layoutInflater = (LayoutInflater) contextLst.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return eventNum.length;
    }

    @Override
    public Object getItem(int position) {
        return eventNum[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.customized_list_view, null);

        final TextView eventNo = (TextView) view.findViewById(R.id.eventsNo);
        final TextView txtEvents = (TextView) view.findViewById(R.id.txtEvents);
        final TextView txtHosts = (TextView) view.findViewById(R.id.txtHosts);
        final TextView txtDescs = (TextView) view.findViewById(R.id.txtDesc);

        String eventNoValue = eventNo.getText().toString();
        String txtEventsValue = txtEvents.getText().toString();
        String txtHostsValue = txtHosts.getText().toString();
        String txtDescsValue = txtDescs.getText().toString();

        eventNo.setText(eventNoValue + eventNum[position]);
        txtEvents.setText(txtEventsValue + eventNames[position]);
        txtHosts.setText(txtHostsValue + eventHosts[position]);
        txtDescs.setText(txtDescsValue + eventDescs[position]);


        return view;

    }
}
