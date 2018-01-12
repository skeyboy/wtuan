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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xsk.com.wtuan.R;
import xsk.com.wtuan.activity.TuanActivity;
import xsk.com.wtuan.adapter.PMBMyAdapter;
import xsk.com.wtuan.bean.tuan.RequestTuanResultBean;
import xsk.com.wtuan.bean.tuan.Tuan;


/**
 * A simple {@link Fragment} subclass.
 */
public class PMBMyFragment extends Fragment {
    ListView listView;
    GridView gridView;
    SimpleAdapter grideViewAdapter;

    private PMBMyAdapter adapter;




    protected void pullWTuan() {
        String json = "{\n" +
                "    \"code\": 0,\n" +
                "    \"msg\": \"success\",\n" +
                "    \"data\": {\n" +
                "        \"currentPage\": 1,\n" +
                "        \"data\": [\n" +
                "            {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"1\",\n" +
                "                \"schoolId\": 1,\n" +
                "                \"userId\": 1,\n" +
                "                \"logo\": \"1\",\n" +
                "                \"addTime\": 1515381289,\n" +
                "                \"categoryId\": 1,\n" +
                "                \"description\": \"111111\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"firstPageUrl\": \"http://127.0.0.1:1004/api/v1/tuan/list?page=1\",\n" +
                "        \"from\": 1,\n" +
                "        \"lastPage\": 1,\n" +
                "        \"lastPageUrl\": \"http://127.0.0.1:1004/api/v1/tuan/list?page=1\",\n" +
                "        \"nextPageUrl\": \"\",\n" +
                "        \"path\": \"http://127.0.0.1:1004/api/v1/tuan/list\",\n" +
                "        \"perPage\": 15,\n" +
                "        \"prevPageUrl\": \"\",\n" +
                "        \"to\": 1,\n" +
                "        \"total\": 1\n" +
                "    }\n" +
                "}";
        Gson gson = new GsonBuilder().create();
        RequestTuanResultBean requestResultBean = (RequestTuanResultBean) gson.fromJson(json, RequestTuanResultBean.class);

        adapter.addTuans(requestResultBean.data.data);
        adapter.addTuans(requestResultBean.data.data);
        adapter.addTuans(requestResultBean.data.data);
        if (grideViewAdapter == null) {
            List<Map<String, Object>> gList = new ArrayList<>();

            for (Tuan tuan : requestResultBean.data.data) {
                Map<String, Object> obj = new HashMap<>();
                obj.put("name", tuan.name);
                obj.put("image",R.drawable.ic_empty_zhihu);
                gList.add(obj);
                gList.add(obj);
                gList.add(obj);
            }
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

//        TuanListRequest request = new TuanListRequest();
//        request.get(RequestTuanResultBean.class, new JsonResultRequest.OnBeanResult() {
//            @Override
//            public void onSuccess(RequestResultBean bean) {
//                RequestTuanResultBean tuans = (RequestTuanResultBean) bean;
//                adapter.addTuans(tuans.data.data);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//
//            }
//        });
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
