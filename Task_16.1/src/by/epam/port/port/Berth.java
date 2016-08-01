package by.epam.port.port;

import by.epam.port.warehouse.Container;
import by.epam.port.warehouse.Warehouse;

import java.util.List;

/**
 * Created by Olga Shahray on 11.07.2016.
 *
 * Класс Причал
 */
public class Berth {

    private int id;
    private Warehouse portWarehouse; //склад порта. Одновременно к складу должен иметь доступ только 1 корабль у причала

    public Berth(int id, Warehouse warehouse) {
        this.id = id;
        portWarehouse = warehouse;
    }

    public int getId() {
        return id;
    }

    //выгружаем контейнеры с корабля на склад порта в заданной количестве
    public boolean add(Warehouse shipWarehouse, int numberOfContainers) throws InterruptedException {
        synchronized (portWarehouse) {
            if (portWarehouse.getFreeSize() >= numberOfContainers) {
                List<Container> listContainer = shipWarehouse.getContainer(numberOfContainers);
                portWarehouse.addContainer(listContainer);
                return  true;
            }
        }
        return false;
    }

    //загружаем контейнеры на корабль со склада порта в заданной количестве
    public boolean get(Warehouse shipWarehouse, int numberOfContainers) throws InterruptedException {
        synchronized (portWarehouse) {
            if (portWarehouse.getRealSize() >= numberOfContainers) {
                List<Container> listContainers = portWarehouse.getContainer(numberOfContainers);
                shipWarehouse.addContainer(listContainers);
                return true;
            }
        }
        return false;
    }

}
