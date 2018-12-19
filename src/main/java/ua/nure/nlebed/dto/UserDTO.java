package ua.nure.nlebed.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String email;

    private String name;

    private String lastName;

    private String macAddress;

    private String ipAddress;

    private String device;

    private String photoUrl;
}
