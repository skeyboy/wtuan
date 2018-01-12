package xsk.com.wtuan.fragment.manager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import xsk.com.wtuan.R;
import xsk.com.wtuan.adapter.manager.ManagerManagerItemAdapter;
import xsk.com.wtuan.adapter.manager.ManagerMemberItemAdapter;


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

        managerItemAdapter = new ManagerManagerItemAdapter(getContext());
        memberItemAdapter = new ManagerMemberItemAdapter(getContext());

        managerView.setAdapter(managerItemAdapter);
        memberView.setAdapter(memberItemAdapter);

        return contentView;
    }
}
