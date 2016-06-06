/*
 * Copyright (c) 2015 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.alltherages;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements RageComicListFragment.OnRageComicSelected {
    static final String tag_rageComicList = "rageComicList"; // TODO: what does "rageComicList" refer to?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tablet_layout);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.listFragment, RageComicListFragment.newInstance(), tag_rageComicList)
                    .commit();
        }

        View detailsFrame = this.findViewById(R.id.detailFragment);
        //boolean mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        boolean mDualPane = true;
        if (mDualPane)
        {
            //if it is a dual pane, show the first item by default
            // Get rage face names and descriptions.
            final Resources resources = this.getResources();
            String mName = resources.getStringArray(R.array.names) [0];
            String mDescription = resources.getStringArray(R.array.descriptions) [0];
            String mUrl = resources.getStringArray(R.array.urls) [0];

            // Get rage face images.
            final TypedArray typedArray =resources.obtainTypedArray(R.array.images);
            int mImageResId = typedArray.getResourceId(0, 0);
            typedArray.recycle();
            final RageComicDetailsFragment detailsFragment =
                    RageComicDetailsFragment.newInstance(mImageResId, mName, mDescription, mUrl);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detailFragment, detailsFragment, "rageComicDetails")
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onRageComicSelected(int imageResId, String name, String description, String url) {
        final RageComicDetailsFragment detailsFragment =
                RageComicDetailsFragment.newInstance(imageResId, name, description, url);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.detailFragment, detailsFragment, "rageComicDetails")
                .addToBackStack(null)
                .commit();
    }


}
