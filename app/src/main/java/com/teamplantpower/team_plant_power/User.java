package com.teamplantpower.team_plant_power;
import android.content.Context;
import android.provider.Settings.Secure;
import android.view.View;

/**
 * User Model for firebase: id uniquely identifies phone and is reference to name in dbs.
 * Ex:
 * User -
 *       id : name
 */

public class User {
    private String name;
    public static final String DBUserListKey = "Users";

    /**
     * User Constructor, View required for getting context to create unique device Id
     * @param name
     */
    public User(String name) {
        this.name = name;
    }
    /**
     * retrieves names
     * @return String
     */

    public String getName() {
        return name;
    }

    /**
     * retrives unique id to retrieve user from DB
     * @return String
     */
    public static String getId(Context c) {
        return Secure.getString(c.getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
    }

}
