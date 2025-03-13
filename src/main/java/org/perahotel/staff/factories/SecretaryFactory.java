package org.perahotel.staff.factories;

import org.perahotel.shared.Factory;
import org.perahotel.staff.Secretary;

public class SecretaryFactory extends Factory<Secretary> {
    @Override
    protected Secretary concreteEmployee(String name) {
        var secretary = new Secretary();
        secretary.setName(name);
        secretary.setSalary(2000.0);
        secretary.setBonus(40.0);

        return secretary;
    }
}
