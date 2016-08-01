package by.epam.port.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Olga Shahray on 11.07.2016.
 */
public class Warehouse {

    private List<Container> containerList;
    private int capacity; //общая вместимость склада

    public Warehouse(int capacity) {
        containerList = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public boolean addContainer(Container container) {
        return containerList.add(container);
    }

    public boolean addContainer(List<Container> containers) {
        return (containers.size() <= getFreeSize()) ? containerList.addAll(containers) : false;
    }

    public Container getContainer() {
        if (containerList.size() > 0) {
            return containerList.remove(0);
        }
        return null;
    }

    public List<Container> getContainer(int amount) {
        if (containerList.size() >= amount) {
            List<Container> cargo = new ArrayList<>(containerList.subList(0, amount));
            containerList.removeAll(cargo);
            return cargo;
        }
        return null;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getRealSize(){
        return containerList.size();
    } //заполненность склада контейнерами

    public int getFreeSize(){
        return capacity - containerList.size();
    }//свободные места на складе

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return capacity == warehouse.capacity &&
                Objects.equals(containerList, warehouse.containerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(containerList, capacity);
    }
}
