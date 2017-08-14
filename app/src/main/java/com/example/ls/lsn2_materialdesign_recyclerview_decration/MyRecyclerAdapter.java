package com.example.ls.lsn2_materialdesign_recyclerview_decration;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 路很长~ on 2017/7/27.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHoler> {
    private List<String> mList;
    private List<Integer> mHeight;
    private ItemOnclickListener onclickListener;

    public MyRecyclerAdapter(ArrayList<String> mList) {
        this.mList = mList;
        mHeight = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            mHeight.add((int) (200 + Math.random() * 50));
        }
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //创建ViewHorlder
        // View view = View.inflate(viewGroup.getContext(), R.layout.item, null);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, null);
        MyViewHoler myViewHoler = new MyViewHoler(view);
        return myViewHoler;
    }

    @Override
    public void onBindViewHolder(MyViewHoler holder, final int position) {

        //绑定数据：
   /*     ViewGroup.LayoutParams params =holder.mTv.getLayoutParams();
        params.height = mHeight.get(position);*/
//        holder.mTv.setBackgroundColor(Color.rgb((int) Math.random() * 260, (int) Math.random() * 200, (int) Math.random() * 230));
        // holder.mTv.setLayoutParams(params);
        holder.mTv.setText(mList.get(position));
        holder.itemView.setOnClickListener(
                //为了解决item位置有可能错位的可能。
                new MyOnclickListener(position));

        /*new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickListener.itemOnclick(v, position);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHoler extends RecyclerView.ViewHolder {
        private TextView mTv;

        public MyViewHoler(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

    interface ItemOnclickListener {
        void itemOnclick(View view, int position);
    }

    public void setOnclickListener(ItemOnclickListener onclickListener) {
        this.onclickListener = onclickListener;
    }

    public void addItem(int position, String str) {
        mList.add(1, str);
        // notifyDataSetChanged();//这种从新刷新不太好，效率不高。
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mList.remove(1);
        notifyDataSetChanged();
        notifyItemRemoved(position);
    }

    class MyOnclickListener implements View.OnClickListener {
        private int port;

        public MyOnclickListener(int position) {
            port = position;
        }

        @Override
        public void onClick(View v) {
            onclickListener.itemOnclick(v, port);

        }
    }
}
