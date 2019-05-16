package com.example.jstore_android_achmadkriptonnugraha;

import com.example.jstore_android_achmadkriptonnugraha.model.Item;
import com.example.jstore_android_achmadkriptonnugraha.model.Supplier;

import java.util.ArrayList;
import java.util.HashMap;

public class CollectionFunction {

    public static boolean testSupplier(Supplier supplier, ArrayList<Supplier> listSupplier){
        for (Supplier rr:listSupplier
        ) {
            if (compareSupp(rr, supplier)){
                return false;
            }
        }
        return true;
    }

    public static boolean compareSupp(Supplier supplier, Supplier rr){
        if (supplier.getId()==rr.getId()){
            return true;
        }
        return false;
    }

    public static void createChild(ArrayList<Supplier> listSupplier, ArrayList<Item> listItem, HashMap<Supplier, ArrayList<Item>> childMapping){
//        HashMap<Supplier, ArrayList<Item>> childMapping = new HashMap<>();
        for (Supplier rr:listSupplier
        ) {
            ArrayList<Item> temp = new ArrayList<>();
            for (Item ii:listItem
            ) {
                if (compareSupp(ii.getSupplier(), rr))
                    temp.add(ii);
            }
            childMapping.put(rr, temp);
        }

    }

}
