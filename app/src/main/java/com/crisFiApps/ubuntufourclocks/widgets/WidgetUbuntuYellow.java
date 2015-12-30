package com.crisFiApps.ubuntufourclocks.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.crisFiApps.ubuntufourclocks.R;

public class WidgetUbuntuYellow extends WidgetUbuntu {
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)) {

			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.widgetyellow);

			// Open alarm clock on click
			Intent alarmClockIntent = getAlarmClockIntent(context);
			if (alarmClockIntent != null) {
				PendingIntent pendingIntent = PendingIntent.getActivity(
						context, 0, alarmClockIntent, 0);
				views.setOnClickPendingIntent(R.id.Widgetyellow, pendingIntent);
			}

			AppWidgetManager
					.getInstance(context)
					.updateAppWidget(
							intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS),
							views);
		}
	}
}
