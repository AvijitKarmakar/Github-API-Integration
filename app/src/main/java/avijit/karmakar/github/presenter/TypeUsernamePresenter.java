package avijit.karmakar.github.presenter;

import java.util.List;

import javax.inject.Inject;

import avijit.karmakar.github.model.Event;
import avijit.karmakar.github.restservice.GithubRestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USER on 22-12-2016.
 */

public class TypeUsernamePresenter extends PresenterStub {

    @Inject
    GithubRestService githubRestService;

    private ITypeUsernameView iTypeUsernameView;

    public TypeUsernamePresenter(ITypeUsernameView iTypeUsernameView) {
        this.iTypeUsernameView = iTypeUsernameView;
    }

    /**
     * This method is used to get events of the that username
     * @param username This is the username whose events we have to fetch
     */
    public void onViewBtnClick(String username) {
        username = username.trim();
        if (username.length() > 0) {
            iTypeUsernameView.showEventLoadProgressDialog();
            githubRestService.getUserEvents(username, new Callback<List<Event>>() {

                @Override
                public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                    iTypeUsernameView.hideProgressDialog();
                    if (response.isSuccessful()) {
                        iTypeUsernameView.goToEvents(response.body());
                    } else {
                        iTypeUsernameView.showServerErrorMsg();
                    }
                }

                @Override
                public void onFailure(Call<List<Event>> call, Throwable t) {
                    iTypeUsernameView.hideProgressDialog();
                    iTypeUsernameView.showInternalErrorMsg();
                }
            });
        } else {
            iTypeUsernameView.showUsernameEmptyMsg();
        }
    }

    public interface ITypeUsernameView {
        /**
         * This method is used to show username empty message
         */
        void showUsernameEmptyMsg();

        /**
         *  This method is used to send list of events to events list activity
         * @param events This is the list of events which we have fetched
         */
        void goToEvents(List<Event> events);

        /**
         * This method is used to show event loading progress dialog
         */
        void showEventLoadProgressDialog();

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
