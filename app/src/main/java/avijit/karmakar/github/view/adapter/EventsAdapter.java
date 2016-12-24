package avijit.karmakar.github.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Html;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.signature.StringSignature;

import java.util.List;

import avijit.karmakar.github.R;
import avijit.karmakar.github.databinding.EventItemBinding;
import avijit.karmakar.github.datastore.OrgImgStore;
import avijit.karmakar.github.model.Event;
import avijit.karmakar.github.utils.DateHelper;
import avijit.karmakar.github.utils.TextHelper;

/**
 * Created by USER on 23-12-2016.
 */

public class EventsAdapter extends GenericBaseAdapter<Event, EventItemBinding> {

    private Context context;
    private OrgImgStore orgImgStore;

    public EventsAdapter(Context context, List<Event> data, int layoutId) {
        super(context, data, layoutId);
        this.context = context;
        orgImgStore = new OrgImgStore(context);
    }

    @Override
    void setLayout(final EventItemBinding binding, final Event event) {
        Glide.with(context)
                .load(event.getOrg().getAvatar_url())
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(binding.avatarImgView) {

                    @Override
                    protected void setResource(Bitmap resource) {
                        orgImgStore.saveOrgImg(resource, event.getOrg().getLogin());
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        binding.avatarImgView.setImageDrawable(circularBitmapDrawable);
                    }
                });
        binding.reponameTxt.setText(Html.fromHtml(String.format(context.getString(R.string.repo_name_txt),
                TextHelper.splitOnSplash(event.getRepo().getName()))));
        binding.eventTypeTxt.setText(Html.fromHtml(String.format(context.getString(R.string.event_type_txt),
                event.getType())));
        binding.publicEventTxt.setText(Html.fromHtml(String.format(context.getString(R.string.public_event_txt),
                event.isPublic() ? context.getString(R.string.yes) : context.getString(R.string.no))));
        binding.eventCreateDateTxt.setText(Html.fromHtml(String.format(context.getString(R.string.repo_create_date_txt),
                DateHelper.formatDateIsoToddMMyyyyHHmm(event.getCreated_at()))));
    }

}
