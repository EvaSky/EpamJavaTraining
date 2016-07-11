package by.epam.port.start;

import by.epam.port.port.Port;
import by.epam.port.ship.Ship;
import by.epam.port.warehouse.Container;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Shahray on 11.07.2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        int warehousePortSize = 15;//вместимость порта
        List<Container> containerList = new ArrayList<>(warehousePortSize);
        for (int i=0; i<warehousePortSize; i++){
            containerList.add(new Container(i));
        }

        Port port = new Port(2, 900);//Порт - 2 причала, вместимость хранилища - 900
        port.setContainersToWarehouse(containerList);


        containerList = new ArrayList<>(warehousePortSize);
        for (int i=0; i<warehousePortSize; i++){  //15 шт
            containerList.add(new Container(i+30));
        }
        Ship ship1 = new Ship("Ship1", port, 90);
        ship1.setContainersToWarehouse(containerList);//разгружаем контейнеры с корабля на склад порта

        containerList = new ArrayList<>(warehousePortSize);
        for (int i=0; i<warehousePortSize; i++){  //15 шт
            containerList.add(new Container(i+60));
        }
        Ship ship2 = new Ship("Ship2", port, 90);
        ship2.setContainersToWarehouse(containerList);

        containerList = new ArrayList<>(warehousePortSize);
        for (int i=0; i<warehousePortSize; i++){  //15 шт
            containerList.add(new Container(i+60));
        }
        Ship ship3 = new Ship("Ship3", port, 90);
        ship3.setContainersToWarehouse(containerList);


        new Thread(ship1).start();
        new Thread(ship2).start();
        new Thread(ship3).start();


        Thread.sleep(3000);

        ship1.stopShip();
        ship2.stopShip();
        ship3.stopShip();

    }
}
