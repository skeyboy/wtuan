package xsk.com.wtuan.fragment.manager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xsk.com.wtuan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MsgManageFragment extends Fragment {


    public MsgManageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_msg_manage, container, false);
    }

}
