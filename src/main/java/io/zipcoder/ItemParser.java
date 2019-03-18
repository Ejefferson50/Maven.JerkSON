package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemParser {
    private String name;
    private Double price;
    private String type;
    private String expiration;


    public List<Item> parseItemList(String valueToParse) {
        List<Item> itemList = new ArrayList<>();
        String[] split = valueToParse.split("##");
        for (int i = 0; i < split.length; i++) {
            try {
                itemList.add(parseSingleItem(split[i]));
            } catch (ItemParseException e) {
                e.printStackTrace();
            }

        }
        //System.out.println();
        return itemList;


    }


    public Item parseSingleItem(String singleItem) throws ItemParseException {
        ArrayList<String> itemList = new ArrayList<>();
        String[] itemArr = singleItem.toLowerCase().split("[;]");

        try {
            for (int i = 0; i < itemArr.length; i++) {
                itemList.add(itemArr[i].split("[#:@^*%]")[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ItemParseException();
        }

        Item item = new Item(itemList.get(0), Double.valueOf(itemList.get(1)), itemList.get(2), itemList.get(3));
        return item;
    }


}
