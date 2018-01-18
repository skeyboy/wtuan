package xsk.com.wtuan.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xsk.com.wtuan.R;
import xsk.com.wtuan.activity.TuanActivity;
import xsk.com.wtuan.adapter.PMBMyAdapter;
import xsk.com.wtuan.bean.RequestResultBean;
import xsk.com.wtuan.bean.tuan.RequestTuanResultBean;
import xsk.com.wtuan.net.JsonResultRequest;
import xsk.com.wtuan.net.request.TuanListRequest;
import xsk.com.wtuan.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class PMBMyFragment extends Fragment {
    ListView listView;
    GridView gridView;
    SimpleAdapter grideViewAdapter;

    private PMBMyAdapter adapter;




    protected void pullWTuan() {

        if (grideViewAdapter == null) {
            List<Map<String, Object>> gList = new ArrayList<>();
            String [] from ={"image","name"};
            int [] to = {R.id.image,R.id.name};

            grideViewAdapter = new SimpleAdapter(getContext(), gList, R.layout.adapter_pmb_gride, from, to);
            this.gridView.setAdapter(grideViewAdapter);
            grideViewAdapter.notifyDataSetChanged();
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    HashMap<String, Object> item = (HashMap<String, Object>) adapterView.getItemAtPosition(i);
                    Intent intent = new Intent(getContext(), TuanActivity.class);
                    getActivity().startActivity(intent);
                }
            });
        }

        TuanListRequest request = new TuanListRequest();
        request.get(RequestTuanResultBean.class, new JsonResultRequest.OnBeanResult() {
            @Override
            public void onSuccess(final RequestResultBean bean) {

                Utils.runOnUiThread(getContext(), new Runnable() {
                    @Override
                    public void run() {
                        RequestTuanResultBean tuans = (RequestTuanResultBean) bean;
                        adapter.addTuans(tuans.data.data);
                    }
                });

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        adapter = new PMBMyAdapter(getContext());


        View view = inflater.inflate(R.layout.fragment_pmbmy, container, false);
        gridView = view.findViewById(R.id.pmb_gride);

        listView = view.findViewById(R.id.pmb_my);

        listView.setAdapter(adapter);

        pullWTuan();

        return view;
    }

}
