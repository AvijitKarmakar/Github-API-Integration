package avijit.karmakar.github.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by USER on 23-12-2016.
 */

public abstract class GenericBaseAdapter<D, B extends ViewDataBinding> extends BaseAdapter {

    private List<D> data;
    private Context context;
    private int layoutId;

    GenericBaseAdapter(Context context, List<D> data, int layoutId) {
        this.context = context;
        this.data = data;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        B binding;
        if (convertView == null) {
            binding = (B) DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (B) convertView.getTag();
        }
        D data = (D) getItem(position);
        setLayout(binding, data);
        return convertView;
    }

    abstract void setLayout(B binding, D data);

}
