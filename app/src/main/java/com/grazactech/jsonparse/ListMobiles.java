package com.grazactech.jsonparse;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ListMobiles extends ListFragment {
    private final String TAG = "ListMobiles";
    private ArrayList<Mobile> mMobileList;
    String url = "https://androidtutorialpoint.com/api/MobileJSONArray.json";
    private final String EXTRA_JSON_OBJECT = "mobileObject";
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG,response.toString());
                        Log.d(TAG,"Len "+response.length());
                        mMobileList = JSONParser.parseArrayFeed(response);
                        pDialog.hide();
                        MobileAdapter adapter = new MobileAdapter(mMobileList);
                        setListAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
// hide the progress dialog
                pDialog.hide();
            }
        });
// Adding request to request queue
        Volley.newRequestQueue(getActivity()).add(jsonArrayReq);
    }


private class MobileAdapter extends ArrayAdapter<Mobile> {
    public MobileAdapter(ArrayList<Mobile> mobiles)
    {
        super(getActivity(), 0, mobiles);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
// If we weren't given a view, inflate one
        Log.d(TAG,"pos "+position);
        if (convertView == null) {
            convertView = getActivity().getLayoutInflater()
                    .inflate(R.layout.category_list_item_1, null);
        }
        Mobile mobile = mMobileList.get(position);
        TextView nameTextView = convertView.findViewById(R.id.textview_name);
        nameTextView.setText(mobile.getmName());
        nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),ParseJSONArrayObject.class);
                Bundle args = new Bundle();
//args.putSerializable(EXTRA_JSON_MOBILE_OBJECT, mMobileList.get(position));
                i.putExtra(EXTRA_JSON_OBJECT, mMobileList.get(position));
                startActivity(i);
            }
        });
        return convertView;
    }
}

}