package com.sato310.contentprovidersample_11_25;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 携帯の中に入っている音楽・写真等の共有のデータを使う事ができる
		ContentResolver resolver = getContentResolver();
		
		// MediaStoreのTITLEとDATAを取得
		String[] projection = new String[]{
		/* 音楽データを取得
		MediaStore.Audio.AudioColumns.TITLE,
		MediaStore.Audio.AudioColumns.DATA,
		*/
				
		/* SDカードの画像を取得
		MediaStore.Images.ImageColumns.TITLE,
		MediaStore.Images.ImageColumns.DATA,
		MediaStore.Images.ImageColumns.SIZE
		*/
				
		/* 電話帳にアクセス　*/
		ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
		ContactsContract.CommonDataKinds.Phone.NUMBER
		};
		
		Cursor cursor = resolver.query(
				/* 音楽データを取得　
				MediaStore.Audio.Media.INTERNAL_CONTENT_URI,	//音、画像等の共有データ(INTERNAL_CONTENT_URI:内部用、EXTERNAL_CONTENT_URI:SDカード等)
				projection, 	//取得するデータのどの項目を取得するか指定
				null,			//検索の絞込み
				null,			//検索の絞込み
				null);			//並び順
				*/
				
				/* SDカードの画像を取得
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				projection,
				null,
				null,
				null);
				*/
				
				/* 電話帳にアクセス　*/
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				projection,
				null,
				null,
				null);
				
				
		
		// NullPointerExceptionが起きる可能性がある
		// cursor.getCount() データが１件以上あれば実行される
		if (cursor != null && 0 < cursor.getCount()) {
			// カーソルを１番目に移動させる
			cursor.moveToFirst();
			// MediaStore.Audio.AudioColumns.TITLEがデータベースの何列目に入ってるか教えてくれる
			int nameIndex = cursor.getColumnIndex(projection[0]);
			// MediaStore.Audio.AudioColumns.DATAがデータベースの何列目に入ってるか教えてくれる
			int numberIndex = cursor.getColumnIndex(projection[1]);

			/* while文での書き方　(ダメな書き方(１行目の値を取得しない))
			// cursor.moveToNext()カーソルを次の行に移動させる(なくなったら終了)
			while (cursor.moveToNext()) {
				//上で取得した値から文字列を取得
				String title = cursor.getString(titleIndex);
				String data = cursor.getString(dataIndex);
				
				Log.i("ContentResolver", "title:" + title); 
				Log.i("ContentResolver", "data:" + data); 
			}
			*/
			
			int i = 1;
			// do内を一度は必ず通過する、その後while文を実行する
			do {
				//上で取得した値から文字列を取得
				String name = cursor.getString(nameIndex);
				String number = cursor.getString(numberIndex);
				
				Log.i(i + "contentprovider", "name:" + name); 
				Log.i(i + "contentprovider", "number:" + number); 
				
				i++;
			} while (cursor.moveToNext());
			
			
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
