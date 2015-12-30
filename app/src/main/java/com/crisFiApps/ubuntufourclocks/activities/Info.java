package com.crisFiApps.ubuntufourclocks.activities;

/**
 * Created by Jorgefc82 on 23/12/2015.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.crisFiApps.ubuntufourclocks.R;
import com.crisFiApps.ubuntufourclocks.adapters.AdaptadorLista;
import com.crisFiApps.ubuntufourclocks.items.ItemsMenus;

import java.io.IOException;
import java.util.ArrayList;

public class Info extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		ListView lista = (ListView) findViewById(R.id.listadewidgets);
		//Nueva lista de items
		ArrayList<ItemsMenus> items = new ArrayList<>();
		items.add(new ItemsMenus(getString(R.string.widget_name), R.drawable.dialubuntu_small));
		items.add(new ItemsMenus(getString(R.string.widgetred_name), R.drawable.dialred_small));
		items.add(new ItemsMenus(getString(R.string.widgetyellow_name), R.drawable.dialyellow_small));
		items.add(new ItemsMenus(getString(R.string.widgetblue_name), R.drawable.dialblue_small));
		// Relaciona el adaptador y la escucha de la lista y quita dividers
		lista.setAdapter(new AdaptadorLista(this, items));
		lista.setDivider(null);
		//En versiones superiores a Honeycomb se colorea actionbar con color Ubuntu
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
			ActionBar actionBar = getActionBar();
			if (actionBar != null) {
				actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(135, 54, 79)));
			}
		}
	}
	//Se infla menú de opciones
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_info, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		//	comparptir
		if (id == R.id.action_share) {
			Intent sharingIntent = new Intent(Intent.ACTION_SEND);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			}
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.text_share)
					+"https://play.google.com/store/apps/details?id="+ getPackageName());
			startActivity(Intent.createChooser(sharingIntent, getString(R.string.text_dialog_share)));
		}

		//	valorar
		if (id == R.id.action_rating) {
			Intent rating = new Intent(Intent.ACTION_VIEW);
			rating.setData(Uri.parse("market://details?id=" + getPackageName()));
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				rating.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			}
			try {
				startActivity(rating);
			}catch(ActivityNotFoundException e) {
				Toast.makeText(this, getString(R.string.unable_app_rating), Toast.LENGTH_LONG).show();
			}
			return true;
		}

		//	ver más apps
		if (id == R.id.action_moreapps) {
			Intent moreapps = new Intent(Intent.ACTION_VIEW);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				moreapps.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			}
			moreapps.setData(Uri.parse("https://play.google.com/store/apps/developer?id=ChisApps&hl=es"));
			try {
				startActivity(moreapps);
			}catch(ActivityNotFoundException e) {
				Toast.makeText(this, getString(R.string.unable_chisapps), Toast.LENGTH_LONG).show();
			}
			return true;
		}

		//	github
		if (id == R.id.action_github) {
			Intent github = new Intent(Intent.ACTION_VIEW);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				github.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			}
			github.setData(Uri.parse("https://github.com/ChisApps/Ubuntu-Widgets-Clocks-Collection-for-Android"));
			startActivity(github);
			return true;
		}

		//	donaciones
		if (id == R.id.action_donation) {
			Intent donation = new Intent(this, Donations.class);
			donation.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(donation);
			return true;
		}

		//	establecer wallpaper
		if (id == R.id.action_wallpapper) {
			WallpaperManager myWallpaperManager
					= WallpaperManager.getInstance(getApplicationContext());
			try {
				//Se prueba a aplicar fondo y se muestra toast con resultado
				myWallpaperManager.setResource(R.raw.wallpaper_ubuntu);
				Toast wallpaper_ok =
						Toast.makeText(getApplicationContext(),
								getString(R.string.set_wallpaper_ok), Toast.LENGTH_LONG);

				wallpaper_ok.show();
			} catch (IOException e) {
				Toast wallpaper_no =
					Toast.makeText(getApplicationContext(),
							getString(R.string.no_set_wallpaper), Toast.LENGTH_LONG);
				wallpaper_no.show();
				e.printStackTrace();
			}
		}
		return super.onOptionsItemSelected(item);
	}
}