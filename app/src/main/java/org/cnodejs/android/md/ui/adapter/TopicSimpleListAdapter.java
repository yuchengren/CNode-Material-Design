package org.cnodejs.android.md.ui.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.cnodejs.android.md.R;
import org.cnodejs.android.md.model.entity.TopicSimple;
import org.cnodejs.android.md.model.glide.GlideApp;
import org.cnodejs.android.md.ui.activity.UserDetailActivity;
import org.cnodejs.android.md.ui.util.Navigator;
import org.cnodejs.android.md.util.FormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopicSimpleListAdapter extends RecyclerView.Adapter<TopicSimpleListAdapter.ViewHolder> {

    private final Activity activity;
    private final LayoutInflater inflater;
    private final List<TopicSimple> topicSimpleList = new ArrayList<>();

    public TopicSimpleListAdapter(@NonNull Activity activity) {
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    public void setTopicSimpleListAndNotify(@NonNull List<TopicSimple> topicSimpleList) {
        this.topicSimpleList.clear();
        this.topicSimpleList.addAll(topicSimpleList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return topicSimpleList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_topic_simple, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(topicSimpleList.get(position), position == topicSimpleList.size() - 1);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_avatar)
        ImageView imgAvatar;

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_last_reply_time)
        TextView tvLastReplyTime;

        @BindView(R.id.icon_deep_line)
        View iconDeepLine;

        @BindView(R.id.icon_shadow_gap)
        View iconShadowGap;

        private TopicSimple topicSimple;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(@NonNull TopicSimple topicSimple, boolean isTheLast) {
            this.topicSimple = topicSimple;

            tvTitle.setText(topicSimple.getTitle());
            GlideApp.with(activity).load(topicSimple.getAuthor().getAvatarUrl()).placeholder(R.drawable.image_placeholder).into(imgAvatar);
            tvName.setText(topicSimple.getAuthor().getName());
            tvLastReplyTime.setText(FormatUtils.getRelativeTimeSpanString(topicSimple.getLastReplyAt()));
            iconDeepLine.setVisibility(isTheLast ? View.GONE : View.VISIBLE);
            iconShadowGap.setVisibility(isTheLast ? View.VISIBLE : View.GONE);
        }

        @OnClick(R.id.img_avatar)
        void onBtnAvatarClick() {
            UserDetailActivity.startWithTransitionAnimation(activity, topicSimple.getAuthor().getLoginName(), imgAvatar, topicSimple.getAuthor().getAvatarUrl());
        }

        @OnClick(R.id.btn_item)
        void onBtnItemClick() {
            Navigator.TopicWithAutoCompat.start(activity, topicSimple.getId());
        }

    }

}
