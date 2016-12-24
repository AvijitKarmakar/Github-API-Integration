package avijit.karmakar.github.dependencyinjection;

import javax.inject.Singleton;

import avijit.karmakar.github.presenter.EventsPresenter;
import avijit.karmakar.github.presenter.RepoDetailPresenter;
import avijit.karmakar.github.presenter.TypeUsernamePresenter;
import dagger.Component;

/**
 * Created by USER on 22-12-2016.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface PresenterComponent {
    void inject(TypeUsernamePresenter presenter);

    void inject(EventsPresenter presenter);

    void inject(RepoDetailPresenter presenter);
}
