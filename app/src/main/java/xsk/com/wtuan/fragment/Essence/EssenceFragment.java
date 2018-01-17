package xsk.com.wtuan.fragment.Essence;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import xsk.com.wtuan.R;
import xsk.com.wtuan.adapter.EssenceFragmentAdapter;
import xsk.com.wtuan.bean.essence.Essence;

/**
 * A simple {@link Fragment} subclass.
 */
public class EssenceFragment extends Fragment {

    ListView recyclerView;
    EssenceFragmentAdapter adapter;
    List<Essence> essences = new ArrayList<>();


    public EssenceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_essence, container, false);
        recyclerView = contentView.findViewById(R.id.essence_recycler);
        for (int i = 0; i < 24; i++) {
            Essence essence = new Essence();
            essence.title = i + "title";
            essence.coverURL = "https://upload.jianshu.io/users/upload_avatars/354846/baddbd6a4bb3.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/96/h/96";
            essence.dateTime = 1234567;
            essences.add(essence);
        }

        adapter = new EssenceFragmentAdapter(essences, contentView.getContext());
        recyclerView.setAdapter(adapter);
        return contentView;
    }

}
