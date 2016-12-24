package avijit.karmakar.github.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.view.View;

import avijit.karmakar.github.Constants;
import avijit.karmakar.github.R;
import avijit.karmakar.github.databinding.ActivityUsernameBinding;

public class UsernameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setSharedElementExitTransition(
                    TransitionInflater.from(this).inflateTransition(R.transition.transition));
        }
        final ActivityUsernameBinding activityUsernameBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_username);

        activityUsernameBinding.userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsernameActivity.this, TypeUsernameActivity.class);
                intent.putExtra(Constants.ROUND_BTN_HEIGHT, activityUsernameBinding.userBtn.getHeight());
                String transitionName = getString(R.string.user_transition);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        UsernameActivity.this, activityUsernameBinding.userBtn, transitionName);
                ActivityCompat.startActivity(UsernameActivity.this, intent, options.toBundle());
            }
        });
    }

}
