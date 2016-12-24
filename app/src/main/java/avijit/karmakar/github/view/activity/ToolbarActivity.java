package avijit.karmakar.github.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import avijit.karmakar.github.databinding.ActivityToolbarBinding;
import avijit.karmakar.github.presenter.Presenter;

import avijit.karmakar.github.R;

/**
 * Created by USER on 23-12-2016.
 */

public abstract class ToolbarActivity<T extends Presenter> extends PresentedActivity<T> {

    private ActivityToolbarBinding binding;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_toolbar);
        setSupportActionBar(binding.toolbar);
        getLayoutBinding(binding);
    }

    @Override
    public void setContentView(View view) {
        binding.activityContainer.addView(view);
    }

    public abstract void getLayoutBinding(ActivityToolbarBinding binding);

}
