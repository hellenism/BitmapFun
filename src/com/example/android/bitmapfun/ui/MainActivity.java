package com.example.android.bitmapfun.ui;

import com.example.android.bitmapfun.R;
import com.example.android.bitmapfun.provider.Images;
import com.example.android.bitmapfun.util.ImageCache;
import com.example.android.bitmapfun.util.ImageFetcher;
import com.example.android.bitmapfun.util.ImageCache.ImageCacheParams;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	private ListView			mListView;
	private int					mImageThumbSize;
	private int					mImageThumbSpacing;
	private ImageFetcher		mImageFetcher;
	private static final String	IMAGE_CACHE_DIR	= "test";
	public static final String	EXTRA_IMAGE		= "extra_image";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_activity);

		mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
		mImageThumbSpacing = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_spacing);
		ImageCacheParams cacheParams = new ImageCacheParams(this, IMAGE_CACHE_DIR);
		cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of
													// app memory

		// The ImageFetcher takes care of loading images into our ImageView
		// children asynchronously
		mImageFetcher = new ImageFetcher(this, mImageThumbSize);
		mImageFetcher.setLoadingImage(R.drawable.empty_photo);
		mImageFetcher.addImageCache(cacheParams);

		mListView = (ListView) this.findViewById(R.id.listView);
		mListView.setAdapter(new ListViewAdapter());
	}

	public class ListViewAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Images.imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.listview_item, null);
			ImageView iamgeView = (ImageView) view.findViewById(R.id.imageView);
			// iamgeView.setImageResource(R.drawable.pic);
			mImageFetcher.loadImage(Images.imageUrls[position], iamgeView);
			return view;
		}
	}
}
