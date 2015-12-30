package com.crisFiApps.ubuntufourclocks.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.RemoteViews;
import com.crisFiApps.ubuntufourclocks.R;

public class WidgetUbuntu extends AppWidgetProvider {

	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)) {

			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.widget);

			// Abre gestor de alarmas on click
			Intent alarmClockIntent = getAlarmClockIntent(context);
			if (alarmClockIntent != null) {
				PendingIntent pendingIntent = PendingIntent.getActivity(
						context, 0, alarmClockIntent, 0);
				views.setOnClickPendingIntent(R.id.Widget, pendingIntent);
			}

			AppWidgetManager
					.getInstance(context)
					.updateAppWidget(
							intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS),
							views);
		}
	}

	// Método para acceder a los  gestores de alarmas de distintos determinales
	protected Intent getAlarmClockIntent(Context context) {
		PackageManager packageManager = context.getPackageManager();

		String clockImpls[][] = {
				{ "HTC Alarm Clock", "com.htc.android.worldclock",
						"com.htc.android.worldclock.WorldClockTabControl" },
				{ "Standar Alarm Clock", "com.android.deskclock",
						"com.android.deskclock.AlarmClock" },
			    { "Cyanogen Alarm Clock", "com.cyanogen.lockclock",
				        "com.android.deskclock.NEXT_ALARM_TIME_SET" },
				{ "Froyo Nexus Alarm Clock", "com.google.android.deskclock",
						"com.android.deskclock.DeskClock" },
				{ "Moto Blur Alarm Clock", "com.motorola.blur.alarmclock",
						"com.motorola.blur.alarmclock.AlarmClock" },
				{ "Samsung Galaxy Clock", "com.sec.android.app.clockpackage",
						"com.sec.android.app.clockpackage.ClockPackage" },
				{"LG Alarm Clock", "com.lge.clock",
						"com.lge.clock.AlarmClockActivity"}, // newer devices e.g. LG G3
				{"LG Alarm Clock", "com.lge.clock",
						"com.lge.clock.DefaultAlarmClockActivity"}, // older devices e.g. LG-E610
				{"Sony Ericsson Xperia Z", "com.sonyericsson.organizer",
						"com.sonyericsson.organizer.Organizer_WorldClock" }};

		for (String[] clockImpl : clockImpls) {
			String vendor = clockImpl[0];
			String packageName = clockImpl[1];
			String className = clockImpl[2];
			try {
				ComponentName cn = new ComponentName(packageName, className);
				ActivityInfo aInfo = packageManager.getActivityInfo(cn,
						PackageManager.GET_META_DATA);
				Intent alarmClockIntent = new Intent(Intent.ACTION_MAIN)
						.addCategory(Intent.CATEGORY_LAUNCHER);
				alarmClockIntent.setComponent(cn);
				return alarmClockIntent;
			} catch (NameNotFoundException e) {
			}
		}
		return null;
	}
}
