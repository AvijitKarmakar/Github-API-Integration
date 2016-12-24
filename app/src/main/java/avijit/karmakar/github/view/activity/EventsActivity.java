package avijit.karmakar.github.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import avijit.karmakar.github.Constants;
import avijit.karmakar.github.R;
import avijit.karmakar.github.databinding.ActivityEventsBinding;
import avijit.karmakar.github.databinding.ActivityToolbarBinding;
import avijit.karmakar.github.dependencyinjection.PresenterComponent;
import avijit.karmakar.github.model.Event;
import avijit.karmakar.github.model.RepoDetail;
import avijit.karmakar.github.presenter.EventsPresenter;
import avijit.karmakar.github.view.adapter.EventsAdapter;

public class EventsActivity extends ToolbarActivity<EventsPresenter>
        implements EventsPresenter.IEventsView, AdapterView.OnItemClickListener {

    private EventsPresenter presenter;
    private ActivityEventsBinding binding;
    private String githubUsername;
    private ProgressDialog progressDialog;
    private View itemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setSharedElementEnterTransition(
                    TransitionInflater.from(this).inflateTransition(R.transition.transition));
            getWindow().setSharedElementExitTransition(
                    TransitionInflater.from(this).inflateTransition(R.transition.transition));
        }
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        binding.eventsListView.setOnItemClickListener(this);
    }

    @Override
    public void getLayoutBinding(ActivityToolbarBinding binding) {
        this.binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_events,
                binding.activityContainer, false);
        setTitle(githubUsername);
    }

    @Override
    protected EventsPresenter onCreatePresenter() {
        Intent intent = getIntent();
        List<Event> events = (List<Event>) intent.getSerializableExtra(Constants.FEEDS);
        githubUsername = intent.getStringExtra(Constants.GITHUB_USERNAME);
        presenter = new EventsPresenter(this, events);
        return presenter;
    }

    @Override
    protected void injectPresenter(PresenterComponent component, EventsPresenter presenter) {
        component.inject(presenter);
    }

    @Override
    public void setFeedsAdapter(List<Event> events) {
        EventsAdapter eventsAdapter = new EventsAdapter(this, events, R.layout.event_item);
        binding.eventsListView.setAdapter(eventsAdapter);
    }

    @Override
    public void goToRepoDetails(RepoDetail repoDetail, String avatar_url) {
        Intent intent = new Intent(this, RepoDetailActivity.class)
                .putExtra(Constants.REPO_DETAIL, repoDetail)
                .putExtra(Constants.OWNER_URL, avatar_url);
        String transitionName = getString(R.string.owner_img_transition);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                itemView.findViewById(R.id.avatar_img_view), transitionName);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

    @Override
    public void showRepoDetailLoadProgressDialog() {
        progressDialog.setMessage(getString(R.string.fetch_repo_detail_msg));
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void showServerErrorMsg() {
        Toast.makeText(this, R.string.server_error_msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInternalErrorMsg() {
        Toast.makeText(this, R.string.internal_error_msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.itemView = view;
        presenter.onEventItemClick(binding.eventsListView.getAdapter().getItem(position), githubUsername);
    }

}
