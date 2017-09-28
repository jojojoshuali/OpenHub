package com.thirtydegreesray.openhub.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thirtydegreesray.openhub.R;
import com.thirtydegreesray.openhub.common.GlideApp;
import com.thirtydegreesray.openhub.inject.component.AppComponent;
import com.thirtydegreesray.openhub.inject.component.DaggerActivityComponent;
import com.thirtydegreesray.openhub.inject.module.ActivityModule;
import com.thirtydegreesray.openhub.mvp.contract.IIssueDetailContract;
import com.thirtydegreesray.openhub.mvp.model.Issue;
import com.thirtydegreesray.openhub.mvp.presenter.IssueDetailPresenter;
import com.thirtydegreesray.openhub.ui.activity.base.BaseActivity;
import com.thirtydegreesray.openhub.ui.fragment.IssueTimelineFragment;
import com.thirtydegreesray.openhub.util.AppHelper;
import com.thirtydegreesray.openhub.util.BundleBuilder;

import butterknife.BindView;

/**
 * Created by ThirtyDegreesRay on 2017/9/26 19:27:11
 */

public class IssueDetailActivity extends BaseActivity<IssueDetailPresenter>
        implements IIssueDetailContract.View{

    public static void show(@NonNull Activity activity, @NonNull View avatarView,
                            @NonNull View titleView, @NonNull Issue issue){
        Intent intent = new Intent(activity, IssueDetailActivity.class);
        Pair<View, String> avatarPair = Pair.create(avatarView, "userAvatar");
        Pair<View, String> titlePair = Pair.create(titleView, "issueTitle");
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, avatarPair, titlePair);
        intent.putExtras(BundleBuilder.builder().put("issue", issue).build());
        activity.startActivity(intent, optionsCompat.toBundle());
    }

    @BindView(R.id.user_avatar) ImageView userImageView;
    @BindView(R.id.issue_title) TextView issueTitle;
    @BindView(R.id.issue_state_img) ImageView issueStateImg;
    @BindView(R.id.issue_state_text) TextView issueStateText;

    private IssueTimelineFragment issueTimelineFragment;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    protected int getContentView() {
        return R.layout.activity_issue_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setToolbarBackEnable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_issue_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
            case R.id.action_open_in_browser:
                AppHelper.openInBrowser(getActivity(), mPresenter.getIssue().getHtmlUrl());
                return true;
            case R.id.action_share:
                AppHelper.shareText(getActivity(), mPresenter.getIssue().getHtmlUrl());
                return true;
            case R.id.action_copy_url:
                AppHelper.copyToClipboard(getActivity(), mPresenter.getIssue().getHtmlUrl());
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showIssue(final Issue issue) {
        setToolbarTitle(getString(R.string.issue).concat(" #").concat(String.valueOf(issue.getNumber())),
                issue.getRepoFullName());
        GlideApp.with(getActivity())
                .load(issue.getUser().getAvatarUrl())
                .placeholder(R.mipmap.logo)
                .into(userImageView);
        issueTitle.setText(issue.getTitle());

        String commentStr = String.valueOf(issue.getCommentNum()).concat(" ")
                .concat(getString(R.string.comments).toLowerCase());
        if(Issue.IssueState.open.equals(issue.getState())){
            issueStateImg.setImageResource(R.drawable.ic_issues);
            issueStateText.setText(getString(R.string.open).concat("    ").concat(commentStr));
        } else {
            issueStateImg.setImageResource(R.drawable.ic_issues_closed);
            issueStateText.setText(getString(R.string.closed).concat("    ").concat(commentStr));
        }

        issueTimelineFragment = IssueTimelineFragment.create(issue);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, issueTimelineFragment)
                        .commit();
            }
        }, 500);

    }

}