package com.irdhina.myapplication3;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.net.URI;

public class ContactReader {
    private StringBuilder value;
    private MainActivity mainActivity;

    public ContactReader(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.value = new StringBuilder();
    }

    public void readContacts (ContentResolver contentResolver) {

        //URI provider
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        //Column yg nak access
        String[]projection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };

        Cursor cursor = contentResolver.query(uri, projection, null, null, null);

        if (cursor !=null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                String displayName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

                value.append("Contact ID : ").append(contactId).append("\n").append("Display Name : ").append(displayName).append("\n");
            }
            cursor.close();
        }
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivity.tvOutput.setText(value.toString());
            }
        });
    }
}