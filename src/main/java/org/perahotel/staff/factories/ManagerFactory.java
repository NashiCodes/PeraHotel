package org.perahotel.staff.factories;

import org.perahotel.shared.Factory;
import org.perahotel.staff.Manager;

public class ManagerFactory extends Factory<Manager> {

    @Override
    protected Manager concreteEmployee(String name) {
        var manager = new Manager();
        manager.setName(name);
        manager.setSalary(3000.0);
        manager.setBonus(60.0);

        return manager;
    }
}
