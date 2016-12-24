package avijit.karmakar.github.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.transition.TransitionInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import avijit.karmakar.github.Constants;
import avijit.karmakar.github.R;
import avijit.karmakar.github.databinding.ActivityRepoDetailBinding;
import avijit.karmakar.github.databinding.ActivityToolbarBinding;
import avijit.karmakar.github.dependencyinjection.PresenterComponent;
import avijit.karmakar.github.model.RepoDetail;
import avijit.karmakar.github.presenter.RepoDetailPresenter;
import avijit.karmakar.github.utils.DateHelper;

public class RepoDetailActivity extends ToolbarActivity<RepoDetailPresenter>
        implements RepoDetailPresenter.IRepoDetailView {

    private RepoDetailPresenter presenter;
    private ActivityRepoDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setSharedElementEnterTransition(
                    TransitionInflater.from(this).inflateTransition(R.transition.transition));
        }
        setContentView(binding.getRoot());
    }

    @Override
    protected RepoDetailPresenter onCreatePresenter() {
        Intent intent = getIntent();
        RepoDetail repoDetail = (RepoDetail) intent.getSerializableExtra(Constants.REPO_DETAIL);
        String ownerUrl = intent.getStringExtra(Constants.OWNER_URL);
        presenter = new RepoDetailPresenter(this, repoDetail, ownerUrl);
        return presenter;
    }

    @Override
    protected void injectPresenter(PresenterComponent component, RepoDetailPresenter presenter) {
        component.inject(presenter);
    }

    @Override
    public void getLayoutBinding(ActivityToolbarBinding binding) {
        this.binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_repo_detail,
                binding.activityContainer, false);
    }

    @Override
    public void setRepoDetailData(Bitmap orgImg, RepoDetail repoDetail, String ownerUrl) {
        setTitle(repoDetail.getFull_name());

        Glide.with(this)
                .load(ownerUrl)
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(binding.avatarImgView) {

                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        binding.avatarImgView.setImageDrawable(circularBitmapDrawable);
                    }
                });

        String repoDesc = repoDetail.getDescription();
        if (!TextUtils.isEmpty(repoDesc)) {
            binding.descTxt.setText(repoDesc);
            binding.descTxt.setVisibility(View.VISIBLE);
        } else {
            binding.descTxt.setVisibility(View.GONE);
        }

        binding.cloneUrlTxt.setText(repoDetail.getClone_url());

        binding.repoCreateDateTxt.setText(Html.fromHtml(String.format(getString(R.string.repo_create_date_txt),
                DateHelper.formatDateIsoToddMMyyyyHHmm(repoDetail.getCreated_at()))));
        binding.repoUpdatedDateTxt.setText(Html.fromHtml(String.format(getString(R.string.repo_update_date_txt),
                DateHelper.formatDateIsoToddMMyyyyHHmm(repoDetail.getUpdated_at()))));

        binding.watchCountTxt.setText(String.valueOf(repoDetail.getWatchers_count()));
        binding.starCountTxt.setText(String.valueOf(repoDetail.getStargazers_count()));

        Glide.with(this)
                .load(repoDetail.getOwner().getAvatar_url())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(binding.ownerImgView) {

            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                binding.ownerImgView.setImageDrawable(circularBitmapDrawable);
            }
        });

    }

}
