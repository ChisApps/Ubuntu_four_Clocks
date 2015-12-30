package com.crisFiApps.ubuntufourclocks.activities;
/**
 * Created by Jorgefc82 on 29/12/2015.
 */

/*
 * Copyright (C) 2011-2015 Dominik Schürmann <dominik@dominikschuermann.de>
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

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.crisFiApps.ubuntufourclocks.BuildConfig;
import com.crisFiApps.ubuntufourclocks.R;
import org.sufficientlysecure.donations.DonationsFragment;

public class Donations extends FragmentActivity {

    /**
     * Google
     */
    private static final String GOOGLE_PUBKEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlQpNwUvHxCXhQs0agqnz6hJ+2fejs3IX4l0Mb1MXOiRaU/8g0TXJzEr/u7EPEQTa1TuMaFE2Ytq9axyRlFeW+zciSlRJxebtyNZDGM4AHuqjT3XxrRHw7J0M0RmvZpwXncUN3PUxCv160NbItYablOqF5rkLvLmHo2i+PAjBEnMnplr2KYeLIM0wZ7uFu509bZGpaLw2NrIiVFjhwQDNrqiOst6rY4TR7QhDG5ukXbtrAbSb+PBaa7l9R4nehxhB0zUkhyNcPirj8TKKxmDyEEPSWOTEMApEzgzlRE8FwMpd2wwtk4LRU31uBV3yoajYCg9NUej91eyF+x2Tnb4pCwIDAQAB";
    private static final String[] GOOGLE_CATALOG = new String[]{"ubuntuclocks.donation.1",
            "ubuntuclocks.donation.2", "ubuntuclocks.donation.3", "ubuntuclocks.donation.5", "ubuntuclocks.donation.10"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.donations);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        DonationsFragment donationsFragment;
        if (BuildConfig.DONATIONS_GOOGLE) {
            donationsFragment = DonationsFragment.newInstance(BuildConfig.DEBUG, true, GOOGLE_PUBKEY, GOOGLE_CATALOG,
                    getResources().getStringArray(R.array.donation_google_catalog_values), false, null, null,
                    null, false, null, null, false, null);
        }
        ft.replace(R.id.donations_activity_container, donationsFragment, "donationsFragment");
        ft.commit();
        //En versiones superiores a Honeycomb se colorea actionbar con color Ubuntu
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(135, 54, 79)));
            }
        }
    }

    /**
     * Needed for Google Play In-app Billing. It uses startIntentSenderForResult(). The result is not propagated to
     * the Fragment like in startActivityForResult(). Thus we need to propagate manually to our Fragment.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag("donationsFragment");
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
