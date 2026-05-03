package store;

/**
 * Main Store Front application.
 */
public class StoreFront {

    private InventoryManager inventoryManager;
    private ShoppingCart shoppingCart;

    /**
     * Creates StoreFront object.
     */
    public StoreFront() {
        inventoryManager = new InventoryManager();
        shoppingCart = new ShoppingCart();
    }

    /**
     * Starts the store.
     */
    public void startStore() {
        inventoryManager.initializeInventory();
        shoppingCart.initializeCart();
    }

    /**
     * Buys a product.
     */
    public void buyProduct(String id) {
        SalableProduct product = inventoryManager.findProduct(id);

        if (product != null) {
            shoppingCart.addToCart(product);
            inventoryManager.removeProduct(product);
            System.out.println(product.getName() + " added to cart.");
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    /**
     * Cancels a purchase.
     */
    public void cancelProduct(String id) {
        SalableProduct product = shoppingCart.findProduct(id);

        if (product != null) {
            shoppingCart.removeFromCart(product);
            inventoryManager.addProduct(product);
            System.out.println(product.getName() + " returned to inventory.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    /**
     * Displays inventory.
     */
    public void showInventory() {
        System.out.println("Inventory:");

        if (inventoryManager.getInventory().isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (SalableProduct product : inventoryManager.getInventory()) {
                System.out.println(product);
            }
        }

        System.out.println();
    }

    /**
     * Displays cart.
     */
    public void showCart() {
        System.out.println("Shopping Cart:");

        if (shoppingCart.getCart().isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (SalableProduct product : shoppingCart.getCart()) {
                System.out.println(product);
            }
        }

        System.out.println();
    }

    // =========================
    // SORTING METHODS
    // =========================

    public void sortInventoryByNameAscending() {
        inventoryManager.sortByNameAscending();
        System.out.println("Sorted by Name (A-Z):");
        showInventory();
    }

    public void sortInventoryByNameDescending() {
        inventoryManager.sortByNameDescending();
        System.out.println("Sorted by Name (Z-A):");
        showInventory();
    }

    public void sortInventoryByPriceAscending() {
        inventoryManager.sortByPriceAscending();
        System.out.println("Sorted by Price (Low to High):");
        showInventory();
    }

    public void sortInventoryByPriceDescending() {
        inventoryManager.sortByPriceDescending();
        System.out.println("Sorted by Price (High to Low):");
        showInventory();
    }

    /**
     * Main method to run program.
     */
    public static void main(String[] args) {

        StoreFront store = new StoreFront();

        store.startStore();

        System.out.println("Store Started\n");

        store.showInventory();
        store.showCart();

        // SORTING DEMO
        store.sortInventoryByNameAscending();
        store.sortInventoryByNameDescending();
        store.sortInventoryByPriceAscending();
        store.sortInventoryByPriceDescending();

        // BUY PRODUCT
        System.out.println("Buying product W1...");
        store.buyProduct("W1");

        store.showInventory();
        store.showCart();

        // CANCEL PURCHASE
        System.out.println("Canceling product W1...");
        store.cancelProduct("W1");

        store.showInventory();
        store.showCart();
    }
}