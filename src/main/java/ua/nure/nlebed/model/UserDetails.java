package ua.nure.nlebed.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
@NoArgsConstructor
@Data
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "mac_address")
    private String macAddress;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "device")
    private String device;

    @Column(name = "blocked")
    private boolean blocked;

    public UserDetails(String macAddress, String ipAddress, String device, boolean blocked) {
        this.macAddress = macAddress;
        this.ipAddress = ipAddress;
        this.device = device;
        this.blocked = blocked;
    }
}
