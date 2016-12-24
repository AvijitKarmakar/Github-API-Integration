package avijit.karmakar.github.presenter;

import android.graphics.Bitmap;

import javax.inject.Inject;

import avijit.karmakar.github.datastore.OrgImgStore;
import avijit.karmakar.github.model.RepoDetail;

/**
 * Created by USER on 23-12-2016.
 */

public class RepoDetailPresenter extends PresenterStub {

    @Inject
    OrgImgStore orgImgStore;

    private IRepoDetailView iRepoDetailView;
    private RepoDetail repoDetail;
    private String ownerUrl;

    public RepoDetailPresenter(IRepoDetailView iRepoDetailView, RepoDetail repoDetail, String ownerUrl) {
        this.iRepoDetailView = iRepoDetailView;
        this.repoDetail = repoDetail;
        this.ownerUrl = ownerUrl;
    }

    @Override
    public void create() {
        iRepoDetailView.setRepoDetailData(orgImgStore.getOrgImg(repoDetail.getOwner().getLogin()),
                repoDetail, ownerUrl);
    }

    public interface IRepoDetailView {
        /**
         * This method is used to set repository detail data in RepositoryDetail activity
         * @param orgImg This is the organization avatar
         * @param repoDetail This is the repository data
         * @param ownerUrl This is the owner's avatar url
         */
        void setRepoDetailData(Bitmap orgImg, RepoDetail repoDetail, String ownerUrl);
    }

}
