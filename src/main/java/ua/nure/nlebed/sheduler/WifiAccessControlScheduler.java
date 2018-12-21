package ua.nure.nlebed.sheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ua.nure.nlebed.model.User;
import ua.nure.nlebed.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WifiAccessControlScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WifiAccessControlScheduler.class);

    private final UserService userService;

    @Autowired
    public WifiAccessControlScheduler(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        LocalDateTime nowMinus15Minutes = LocalDateTime.now().minusSeconds(20L);
        List<User> clientsToDisconnect = userService.findAllClients().stream()
                .filter(u -> u.getLastConnectionTime().isBefore(nowMinus15Minutes))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(clientsToDisconnect)) {
            // TODO disconnect them in RUCKUS
            clientsToDisconnect.forEach(u -> u.setIsConnected(false));
            userService.updateClientsStatuses(clientsToDisconnect);
        }
    }

}
