package com.tdd.salestax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class Order {
    private final Collection<Item> items = new ArrayList<>();

    private Order() {

    }
    public static Order getInstance() {
        return new Order();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Iterable<? extends Item> getItems() {
        return items;
    }

}