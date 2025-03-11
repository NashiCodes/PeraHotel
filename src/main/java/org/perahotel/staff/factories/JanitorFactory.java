package org.perahotel.staff.factories;

import org.perahotel.shared.Factory;
import org.perahotel.staff.Janitor;

public class JanitorFactory extends Factory<Janitor> {

    @Override
    protected Janitor concreteEmployee(String name) {
        var janitor = new Janitor();
        janitor.setName(name);
        janitor.setSalary(1500.0);
        janitor.setBonus(2.0);

        return janitor;
    }
}
