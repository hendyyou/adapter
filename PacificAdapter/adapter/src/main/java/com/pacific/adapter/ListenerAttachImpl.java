/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pacific.adapter;

import android.support.annotation.IdRes;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

final class ListenerAttachImpl implements ListenerAttach {
    /**
     * listeners provider
     */
    private final ListenerProvider provider;

    /**
     * holder
     */
    private final ViewHolder holder;

    public ListenerAttachImpl(ListenerProvider provider, ViewHolder holder) {
        this.provider = provider;
        this.holder = holder;
    }

    /**
     * attach OnClickListener for view
     *
     * @param viewId view id
     */
    @Override
    public void attachOnClickListener(@IdRes int viewId) {
        final View.OnClickListener l = provider.getOnClickListener();
        if (l == null) return;
        View view = AdapterUtil.findView(holder.itemView, viewId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTag(R.integer.adapter_holder, holder);
                l.onClick(v);
            }
        });
    }

    /**
     * attach OnTouchListener for view
     *
     * @param viewId view id
     */
    @Override
    public void attachOnTouchListener(@IdRes int viewId) {
        final View.OnTouchListener l = provider.getOnTouchListener();
        if (l == null) return;
        View view = AdapterUtil.findView(holder.itemView, viewId);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.setTag(R.integer.adapter_holder, holder);
                return l.onTouch(v, event);
            }
        });
    }

    /**
     * attach OnLongClickListener for view
     *
     * @param viewId view id
     */
    @Override
    public void attachOnLongClickListener(@IdRes int viewId) {
        final View.OnLongClickListener l = provider.getOnLongClickListener();
        if (l == null) return;
        View view = AdapterUtil.findView(holder.itemView, viewId);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.setTag(R.integer.adapter_holder, holder);
                return l.onLongClick(v);
            }
        });
    }

    /**
     * attach CompoundButton.OnCheckedChangeListener for CompoundButton
     *
     * @param viewId CompoundButton view id
     */
    @Override
    public void attachOnCheckedChangeListener(@IdRes int viewId) {
        final CompoundButton.OnCheckedChangeListener l = provider.getOnCheckedChangeListener();
        if (l == null) return;
        CompoundButton view = AdapterUtil.findView(holder.itemView, viewId);
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.setTag(R.integer.adapter_holder, holder);
                l.onCheckedChanged(buttonView, isChecked);
            }
        });
    }

    /**
     * load image
     *
     * @param viewId
     */
    @Override
    public void attachImageLoader(@IdRes int viewId) {
        ImageLoader loader = provider.getImageLoader();
        if (loader == null) return;
        ImageView view = AdapterUtil.findView(holder.itemView, viewId);
        loader.load(view, holder);
    }
}
