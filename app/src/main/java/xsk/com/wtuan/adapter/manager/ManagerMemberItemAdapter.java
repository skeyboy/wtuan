package xsk.com.wtuan.adapter.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import xsk.com.wtuan.R;
import xsk.com.wtuan.bean.member.Member;
import xsk.com.wtuan.utils.Utils;

public class ManagerMemberItemAdapter extends ManagerItemAdapter {
    protected List<Member> members = new ArrayList<>();

    public ManagerMemberItemAdapter(Context context) {
        super(context);
    }

    public void add(List<Member> members) {
        this.members.addAll(members);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return members.size();
    }

    @Override
    public Object getItem(int i) {
        return members.get(i);
    }

    @Override
    public long getItemId(int i) {
        return members.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.adapter_manager_item, null, false);
        Member member = members.get(i);
        ImageView avator = contentView.findViewById(R.id.member_avator);
        TextView name = contentView.findViewById(R.id.member_name);

        Utils.picasso(context, member.avatar, avator);
        name.setText(member.userName);

        return contentView;
    }
}
