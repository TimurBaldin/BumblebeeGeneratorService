package com.rufus.bumblebee.services;

import com.rufus.bumblebee.controllers.requests.user.UserRequest;
import com.rufus.bumblebee.controllers.responses.dto.UserDto;
import com.rufus.bumblebee.repository.ClientRepository;
import com.rufus.bumblebee.tables.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public UserDto createClient(UserRequest request) {
        Client client = new Client();
        client.setLogin(request.getLogin());
        client.setFirstName(request.getFirstName());
        client.setSecondName(request.getSecondName());
        client.setLastName(request.getLastName());
        client.setEmail(request.getEmail());
        client.setToken(request.getToken());
        return convertToUserDto(repository.createClient(client));
    }

    public UserDto deleteClietn()

    private UserDto convertToUserDto(Client client) {
        UserDto userDto = new UserDto();
        userDto.setUserId(client.getUserId());
        userDto.setSessionId(client.getSessionId());
        return userDto;
    }

}
