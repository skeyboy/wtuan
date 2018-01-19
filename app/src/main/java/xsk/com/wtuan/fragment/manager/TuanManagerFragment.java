package xsk.com.wtuan.fragment.manager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.HashMap;

import xsk.com.wtuan.R;
import xsk.com.wtuan.adapter.manager.ManagerManagerItemAdapter;
import xsk.com.wtuan.adapter.manager.ManagerMemberItemAdapter;
import xsk.com.wtuan.bean.RequestResultBean;
import xsk.com.wtuan.bean.member.TuanMemberResultBean;
import xsk.com.wtuan.net.JsonResultRequest;
import xsk.com.wtuan.net.request.tuan.TuanMemberRequest;
import xsk.com.wtuan.utils.Utils;


public class TuanManagerFragment extends Fragment {
    GridView managerView;
    GridView memberView;
    ManagerManagerItemAdapter managerItemAdapter;
    ManagerMemberItemAdapter memberItemAdapter;

    public TuanManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_tuan_manager, container, false);
        managerView = contentView.findViewById(R.id.manager_manager_gridview);
        memberView = contentView.findViewById(R.id.manager_member_gridview);
        final TextView memberCountView = contentView.findViewById(R.id.member_count);

        managerItemAdapter = new ManagerManagerItemAdapter(getContext());
        memberItemAdapter = new ManagerMemberItemAdapter(getContext());

        managerView.setAdapter(managerItemAdapter);
        memberView.setAdapter(memberItemAdapter);

        TuanMemberRequest request = new TuanMemberRequest();
        HashMap<String, String> p = new HashMap<>();
        p.put("tuanId", "1");

        request.get(p, TuanMemberResultBean.class, new JsonResultRequest.OnBeanResult() {
            @Override
            public void onSuccess(RequestResultBean bean) {
                final TuanMemberResultBean resultBean = (TuanMemberResultBean) bean;

                Utils.runOnUiThread(getContext(), new Runnable() {
                    @Override
                    public void run() {
                        memberItemAdapter.add(resultBean.data.data);
                        memberCountView.setText("全部（"+resultBean.data.data.size()+"）");
                    }
                });
            }

            @Override
            public void onFailure(Exception e) {

            }
        });


        return contentView;
    }
}
