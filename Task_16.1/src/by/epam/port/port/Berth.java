package by.epam.port.port;

import by.epam.port.warehouse.Warehouse;

/**
 * Created by Olga Shahray on 11.07.2016.
 */
public class Berth {

    private int id;
    private Warehouse portWarehouse;

    public Berth(int id, Warehouse warehouse) {
        this.id = id;
        portWarehouse = warehouse;
    }

    public int getId() {
        return id;
    }

    public boolean add(Warehouse shipWarehouse, int numberOfConteiners) throws InterruptedException {
        boolean result = false;
        boolean portLock = false;

        synchronized (portWarehouse) {
            synchronized (shipWarehouse) {

            }
        }
        return result;

    }


    public boolean get(Warehouse shipWarehouse, int numberOfConteiners) throws InterruptedException {
        boolean result = false;
        return result;
    }

}
