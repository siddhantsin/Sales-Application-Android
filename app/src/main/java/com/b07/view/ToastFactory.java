package com.b07.view;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ToastFactory {
    public static void showErrorToast(Context context){
        CharSequence toastText = "There was a problem! Please try again.";
        Toast toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showAccountMadeToast(Context context, int roleId, long userId){
        CharSequence toastText = "";
        if (roleId == 1){
            toastText = "The ID for the admin is " + userId;
        } else if (roleId == 2){
            toastText = "The ID for the employee is " + userId;
        } else {
            toastText = "The ID for the customer is " + userId;
        }
        Toast toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showNotAuthenticatedToast(Context context){
        CharSequence toastText = "The id or password that you entered was incorrect!";
        Toast toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showAddedItemToast(Context context, String item){
        CharSequence toastText = "One unit of the item " + item + " has been added to the cart!";
        Toast toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showItemNotFound(Context context){
        CharSequence toastText = "There are already 0 instances of this item in your cart!";
        Toast toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showRemovedItemToast(Context context, String item) {
        CharSequence toastText = "One unit of the item " + item + " has been removed from the cart!";
        Toast toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showEmployeePromotedToast(Context context, String id) {
        CharSequence toastText = "The employee with id " + id + " has been promoted to an admin!";
        Toast toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
        toast.show();
    }

}
