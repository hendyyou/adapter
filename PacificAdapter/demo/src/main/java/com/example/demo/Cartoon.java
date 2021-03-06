package com.example.demo;

import com.google.auto.value.AutoValue;
import com.pacific.adapter.DefaultBinding;
import com.pacific.adapter.PagerAdapter2;
import com.pacific.adapter.RecyclerAdapter;
import com.pacific.adapter.SimpleItem;
import com.pacific.adapter.ViewHolder;

@AutoValue
public abstract class Cartoon extends SimpleItem {

    public abstract String name();

    public abstract String description();

    public abstract String imageUrl();

    @Override
    public int getLayout() {
        return R.layout.item_cartoon;
    }

    @Override
    public void bind(ViewHolder holder) {
        DefaultBinding binding = holder.binding();
        binding.setText(R.id.text_name, name());
        binding.setText(R.id.text_desc, description());
        holder.attachImageLoader(R.id.img_header);
        holder.attachOnClickListener(R.id.layout_root);
        holder.attachOnLongClickListener(R.id.layout_root);
        holder.attachOnTouchListener(R.id.layout_root);
        holder.attachOnCheckedChangeListener(R.id.layout_root);
    }

    public static Builder builder() {
        return new AutoValue_Cartoon.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {
        public abstract Builder name(String value);

        public abstract Builder description(String value);

        public abstract Builder imageUrl(String value);

        abstract Cartoon build();
    }
}
