package com.study.chazo.naverbandanalysis.base.view;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.study.chazo.naverbandanalysis.BR;
import com.study.chazo.naverbandanalysis.base.viewmodel.BaseItemViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chazo on 2018-07-20
 */
public class BindingRecyclerViewAdapter<ViewModel extends BaseItemViewModel> extends RecyclerView.Adapter<BindingRecyclerViewAdapter.BindViewHolder> {
    private ObservableArrayList<ViewModel> items;
    private ObservableList.OnListChangedCallback listChangedCallback;

    public BindingRecyclerViewAdapter(){
        listChangedCallback = new ObservableList.OnListChangedCallback<ObservableList<ViewModel>>() {
            @Override
            public void onChanged(ObservableList<ViewModel> sender) {}

            @Override
            public void onItemRangeChanged(ObservableList<ViewModel> sender, int positionStart, int itemCount) {
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList<ViewModel> sender, int positionStart, int itemCount) {
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<ViewModel> sender, int fromPosition, int toPosition, int itemCount) {
                notifyItemRangeChanged(fromPosition, toPosition+itemCount);
            }

            @Override
            public void onItemRangeRemoved(ObservableList<ViewModel> sender, int positionStart, int itemCount) {
                notifyItemRangeRemoved(positionStart, itemCount);
            }
        };
    }

    public void setItems(@NonNull ObservableArrayList<ViewModel> items) {
        if(this.items != null) {
            this.items.removeOnListChangedCallback(listChangedCallback);
        }
        this.items = items;
        this.items.addOnListChangedCallback(listChangedCallback);
        notifyDataSetChanged();
    }

//    public void addItems(List<ViewModel> items) {
//        this.items.addAll(items);
//    }
//
//    public void addItem(ViewModel item) {
//        items.add(item);
//    }
//
//    public void removeItem(int position) {
//        items.remove(position);
//    }
//
//    public void removeItem(ViewModel item) {
//        items.remove(item);
//    }

    @NonNull
    @Override
    public BindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new BindViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindViewHolder holder, int position) {
        holder.bind(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        if(items == null) return 0;
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    static class BindViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        BindViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(BaseItemViewModel viewModel, int position) {
//            binding.setVariable(BR., position);
            binding.setVariable(BR.viewModel, viewModel);
            binding.executePendingBindings();
        }
    }
}
