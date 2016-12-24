package avijit.karmakar.github.presenter;

import java.util.List;

import javax.inject.Inject;

import avijit.karmakar.github.model.Event;
import avijit.karmakar.github.model.RepoDetail;
import avijit.karmakar.github.restservice.GithubRestService;
import avijit.karmakar.github.utils.TextHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USER on 22-12-2016.
 */

public class EventsPresenter extends PresenterStub {

    @Inject
    GithubRestService githubRestService;

    private IEventsView iEventsView;
    private List<Event> events;
    private Event event;

    public EventsPresenter(IEventsView iEventsView, List<Event> events) {
        this.iEventsView = iEventsView;
        this.events = events;
    }

    @Override
    public void create() {
        iEventsView.setFeedsAdapter(events);
    }

    /**
     * This method is called on item click of event's listview
     * @param item This is the selected event item
     * @param githubUsername This is the username whose repository we have to fetch
     */
    public void onEventItemClick(final Object item, String githubUsername) {
        if (item instanceof Event) {
            event = (Event) item;
        }
        iEventsView.showRepoDetailLoadProgressDialog();
        githubRestService.getRepoDetails(githubUsername,
                TextHelper.splitOnSplash(event.getRepo().getName()), new Callback<RepoDetail>() {

                    @Override
                    public void onResponse(Call<RepoDetail> call, Response<RepoDetail> response) {
                        iEventsView.hideProgressDialog();
                        if (response.isSuccessful()) {
                            iEventsView.goToRepoDetails(response.body(), event.getOrg().getAvatar_url());
                        } else {
                            iEventsView.showServerErrorMsg();
                        }
                    }

                    @Override
                    public void onFailure(Call<RepoDetail> call, Throwable t) {
                        iEventsView.hideProgressDialog();
                        iEventsView.showInternalErrorMsg();
                    }
                });
    }

    public interface IEventsView {
        /**
         * This method is used to setEventsAdapter
         * @param events This is the list of events which will be set in adapter
         */
        void setFeedsAdapter(List<Event> events);

        /**
         * This method is used to go to repository detail activity
         * @param repoDetail This is the repository detail
         * @param avatar_url This is the url of avatar
         */
        void goToRepoDetails(RepoDetail repoDetail, String avatar_url);

        /**
         * This method is used to show repo detail progress dialog
         */
        void showRepoDetailLoadProgressDialog();

        /**
         * This method is used to hide progress dialog
         */
        void hideProgressDialog();

        /**
         * This method is used to show server error msg
         */
        void showServerErrorMsg();

        /**
         * This method is used to show internal error msg
         */
        void showInternalErrorMsg();
    }

}
