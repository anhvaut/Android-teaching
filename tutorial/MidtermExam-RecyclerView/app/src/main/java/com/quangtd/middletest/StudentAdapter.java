package com.quangtd.middletest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * QuangTD on 10/6/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.InfoViewHolder> {
    private Context mContext;
    private List<Student> mStudents;

    public StudentAdapter(Context context, List<Student> students) {
        this.mContext = context;
        this.mStudents = students;
    }

    @Override public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_student, parent, false);
        return new InfoViewHolder(view);
    }

    @Override public void onBindViewHolder(InfoViewHolder holder, int position) {
        holder.setData(mStudents.get(position));
    }

    @Override public int getItemCount() {
        return (mStudents == null) ? 0 : mStudents.size();
    }

    class InfoViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvInfo;

        public InfoViewHolder(View itemView) {
            super(itemView);
            mTvInfo = itemView.findViewById(R.id.tvInfo);
        }

        void setData(Student student) {
            if (student != null) {
                mTvInfo.setText(student.toString());
            }
        }
    }
}
