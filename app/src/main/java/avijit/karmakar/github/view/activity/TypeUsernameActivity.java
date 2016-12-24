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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import avijit.karmakar.github.Constants;
import avijit.karmakar.github.R;
import avijit.karmakar.github.databinding.ActivityTypeUsernameBinding;
import avijit.karmakar.github.dependencyinjection.PresenterComponent;
import avijit.karmakar.github.model.Event;
import avijit.karmakar.github.presenter.TypeUsernamePresenter;

public class TypeUsernameActivity extends PresentedActivity<TypeUsernamePresenter>
        implements TypeUsernamePresenter.ITypeUsernameView {

    private ActivityTypeUsernameBinding binding;
    private TypeUsernamePresenter presenter;
    private ProgressDialog progressDialog;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setSharedElementEnterTransition(
                    TransitionInflater.from(this).inflateTransition(R.transition.transition));
            getWindow().setSharedElementExitTransition(
                    TransitionInflater.from(this).inflateTransition(R.transition.transition));
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_type_username);

        if (height > 0) {
            int density = (int) getResources().getDisplayMetrics().density;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) binding.usernameContainer.getLayoutParams();
            params.setMargins(20 * density, 0, 20 * density, (height / 2)  + (100 * density));
            binding.usernameContainer.setLayoutParams(params);

            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) binding.viewBtn.getLayoutParams();
            params1.setMargins(0, 0, 0, 100 * density);
            binding.viewBtn.setLayoutParams(params1);
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        binding.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onViewBtnClick(binding.usernameTxt.getText().toString());
            }
        });

    }

    @Override
    protected TypeUsernamePresenter onCreatePresenter() {
        height = getIntent().getIntExtra(Constants.ROUND_BTN_HEIGHT, 0);
        presenter = new TypeUsernamePresenter(this);
        return presenter;
    }

    @Override
    protected void injectPresenter(PresenterComponent component, TypeUsernamePresenter presenter) {
        component.inject(presenter);
    }

    @Override
    public void showUsernameEmptyMsg() {
        binding.usernameTxtInputLayout.setError(getString(R.string.username_empty_msg));
    }

    @Override
    public void goToEvents(List<Event> events) {
        Intent intent = new Intent(this, EventsActivity.class)
                .putExtra(Constants.FEEDS, (Serializable) events)
                .putExtra(Constants.GITHUB_USERNAME, binding.usernameTxt.getText().toString());
        String transitionName = getString(R.string.view_btn_transition);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                binding.viewBtn, transitionName);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

    @Override
    public void showEventLoadProgressDialog() {
        progressDialog.setMessage(getString(R.string.fetch_events_msg));
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

}
