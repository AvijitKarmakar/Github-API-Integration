package avijit.karmakar.github.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import avijit.karmakar.github.dependencyinjection.ApplicationModule;
import avijit.karmakar.github.dependencyinjection.DaggerPresenterComponent;
import avijit.karmakar.github.dependencyinjection.PresenterComponent;
import avijit.karmakar.github.presenter.Presenter;

/**
 * Created by USER on 22-12-2016.
 */

public abstract class PresentedActivity<T extends Presenter> extends AppCompatActivity {

    private T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.create();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    private void inject() {
        presenter = onCreatePresenter();
        PresenterComponent presenterComponent = DaggerPresenterComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        injectPresenter(presenterComponent, presenter);
    }

    protected abstract T onCreatePresenter();

    protected abstract void injectPresenter(PresenterComponent component, T presenter);

}
