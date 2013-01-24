/**
 * The MIT License
 * 
 * Copyright (c) 2013 Alexandro Blanco <ti3r.bubblenet@gmail.com>
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
package org.blanco.examples.screenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;

/**
 * Main Activity for the application
 *
 */
public class MainActivity extends Activity {

	public static final String APP_TAG = "SCREENSHOT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void button1onClick(View source){
		Log.d(APP_TAG,"Button 1 clicked and received to method");
		takeScreenshot();
	}
	
	public void button2onClick(View source){
		Log.d(APP_TAG,"Button 2 clicked and received to method");
		Intent i = new Intent(Intent.ACTION_PICK);
		i.setType("image/jpeg");
		startActivityForResult(i, 0);
	}

	public void takeScreenshot(){
		View v = findViewById(R.id.rootLayout);
		v.setDrawingCacheEnabled(true);
		Bitmap bitmap  = v.getDrawingCache();
		String dest = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
				+File.separator+"Camera"+File.separator+"IMG_"+
				new DateFormat().format("yyyyMMdd_hhmmss", new Date())
				+".jpg";
		Log.d(APP_TAG, "Writing to: "+dest);
		File file = new File(dest);
		try {
			FileOutputStream stream = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 100, stream);
			stream.flush();
			stream.close();
		} catch (IOException e) {
			Log.e(APP_TAG, "Error taking screenshot.",e);
		}finally{
			v.setDrawingCacheEnabled(false);
		}
		
	}
	
}
