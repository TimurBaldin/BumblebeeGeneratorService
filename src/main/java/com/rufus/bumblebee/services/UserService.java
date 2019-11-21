package com.rufus.bumblebee.services;

import com.rufus.bumblebee.configurer.enums.ClientStatus;
import com.rufus.bumblebee.configurer.enums.UserRole;
import com.rufus.bumblebee.controllers.requests.user.AddUserRequest;
import com.rufus.bumblebee.controllers.requests.user.BaseUserRequest;
import com.rufus.bumblebee.controllers.responses.dto.UserDto;
import com.rufus.bumblebee.repository.UserRepository;
import com.rufus.bumblebee.tables.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDto createUser(AddUserRequest request) {
        Client client = new Client();
        client.setLogin(request.getLogin());
        client.setFirstName(request.getFirstName());
        client.setSecondName(request.getSecondName());
        client.setLastName(request.getLastName());
        client.setEmail(request.getEmail());
        client.setToken(request.getToken());
        client.setStatus(ClientStatus.NEW);
        client.setRole(UserRole.valueOf(request.getRole()));
        return convertToUserDto(repository.createClient(client));
    }

    public UserDto disableUser(BaseUserRequest request) {
        return convertToUserDto(repository.disableClient(request.getLogin()));
    }

    public boolean logIn(){
        return true;
    }

    private UserDto convertToUserDto(Client client) {
        UserDto userDto = new UserDto();
        userDto.setUserId(client.getUserId());
        userDto.setSessionId(client.getSessionId());
        userDto.setStatus(client.getStatus().name());
        return userDto;
    }

}
