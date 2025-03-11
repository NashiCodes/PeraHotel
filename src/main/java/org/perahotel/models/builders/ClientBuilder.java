package org.perahotel.models.builders;

import org.perahotel.models.Client;
import org.perahotel.shared.Builder;

public class ClientBuilder implements Builder<Client> {
    private final Client Client;

    public ClientBuilder() {
        Client = new Client();
    }

    public ClientBuilder(String name, String email, String phone) {
        Client = new Client(name, email, phone);
    }

    public Client build() {
        validateClient();
        return Client;
    }

    public ClientBuilder setName(String name) {
        Client.setName(name);
        return this;
    }

    public ClientBuilder setEmail(String email) {
        Client.setEmail(email);
        return this;
    }

    public ClientBuilder setPhone(String phone) {
        Client.setPhone(phone);
        return this;
    }

    private void validateClient() {
        if (invalidString(Client.getName())) throw new IllegalArgumentException("Name is required");
        if (invalidString(Client.getEmail())) throw new IllegalArgumentException("Email is required");
        if (invalidString(Client.getPhone())) throw new IllegalArgumentException("Phone is required");
    }

    private boolean invalidString(String str) {
        return str == null || str.isBlank();
    }
}
