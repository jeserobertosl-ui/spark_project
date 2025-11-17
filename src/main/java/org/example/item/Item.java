package org.example.item;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private int id;
    private String name;
    private String description;
    private float price;

    public Item(int id, String name, String description, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public static List<Item> default_values() {
        List<Item> items = new ArrayList<>();

        items.add(new Item(1, "Gorra autografiada por Peso Pluma", "Una gorra autografiada por el famoso Peso Pluma.", 621.34f));
        items.add(new Item(2, "Casco autografiado por Rosalía", "Un casco autografiado por la famosa cantante Rosalía, una verdadera MOTOMAMI!", 734.57f));
        items.add(new Item(3, "Chamarra de Bad Bunny", "Una chamarra de la marca favorita de Bad Bunny, autografiada por el propio artista.", 521.89f));
        items.add(new Item(4, "Guitarra de Fernando Delgadillo", "Una guitarra acústica de alta calidad utilizada por el famoso cantautor Fernando Delgadillo.", 823.12f));
        items.add(new Item(5, "Jersey firmado por Snoop Dogg", "Un jersey autografiado por el legendario rapero Snoop Dogg.", 355.67f));
        items.add(new Item(6, "Prenda de Cardi B autografiada", "Un crop-top usado y autografiado por la famosa rapera Cardi B. en su última visita a México.", 674.23f));
        items.add(new Item(7, "Guitarra autografiada por Coldplay", "Una guitarra eléctrica autografiada por la popular banda británica Coldplay, un día antes de su concierto en Monterrey en 2022.", 458.91f));

        return items;
    }

    public static int get_index_from_item_id(List<Item> _items, int _id) {
        for (int i = 0; i < _items.size(); i++) {
            if (_items.get(i).getId() == _id)
            {
                return i;
            }
        }
        return -1;
    }

    public static boolean item_id_exists(List<Item> _items, int _id){
        for (Item item : _items) {
            if (item.getId() == _id) {
                return true;
            }
        }
        return false;
    }
}
