package store;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages the store inventory.
 */
public class InventoryManager {

    private ArrayList<SalableProduct> inventory;
    private FileService fileService;

    /**
     * Creates an InventoryManager object.
     */
    public InventoryManager() {
        inventory = new ArrayList<SalableProduct>();
        fileService = new FileService();
    }

    /**
     * Initializes inventory from JSON file.
     */
    public void initializeInventory() {
        inventory.clear();
        inventory = fileService.loadInventory("inventory.json");
    }

    /**
     * Adds a product to inventory.
     */
    public void addProduct(SalableProduct product) {
        inventory.add(product);
    }

    /**
     * Removes a product from inventory.
     */
    public void removeProduct(SalableProduct product) {
        inventory.remove(product);
    }

    /**
     * Returns the inventory list.
     */
    public ArrayList<SalableProduct> getInventory() {
        return inventory;
    }

    /**
     * Finds a product by ID.
     */
    public SalableProduct findProduct(String id) {
        for (SalableProduct product : inventory) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }

    // =========================
    // SORTING METHODS
    // =========================

    /**
     * Sort inventory by name A-Z.
     */
    public void sortByNameAscending() {
        Collections.sort(inventory, new NameAscendingComparator());
    }

    /**
     * Sort inventory by name Z-A.
     */
    public void sortByNameDescending() {
        Collections.sort(inventory, new NameDescendingComparator());
    }

    /**
     * Sort inventory by price low-high.
     */
    public void sortByPriceAscending() {
        Collections.sort(inventory, new PriceAscendingComparator());
    }

    /**
     * Sort inventory by price high-low.
     */
    public void sortByPriceDescending() {
        Collections.sort(inventory, new PriceDescendingComparator());
    }
}