package ua.nure.nlebed.sheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.nure.nlebed.domain.BlockedClient;
import ua.nure.nlebed.domain.RuckusWirelessClient;
import ua.nure.nlebed.model.UserDetails;
import ua.nure.nlebed.service.UserService;
import ua.nure.nlebed.web.RuckusRestTemplate;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class WifiAccessControlScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WifiAccessControlScheduler.class);

    private final UserService userService;
    private final RuckusRestTemplate ruckusRestTemplate;

    @Autowired
    public WifiAccessControlScheduler(UserService userService, RuckusRestTemplate ruckusRestTemplate) {
        this.userService = userService;
        this.ruckusRestTemplate = ruckusRestTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {
        List<String> macAddressesOnServer = userService
                .findAllClients()
                .stream()
                .flatMap(user -> user.getUserDetails().stream())
                .map(UserDetails::getMacAddress)
                .collect(toList());

        List<RuckusWirelessClient> macsForBlock = new ArrayList<>();
        List<String> idsForUnblock = new ArrayList<>();

        List<RuckusWirelessClient> ruckusWirelessClients = ruckusRestTemplate.queryForCurrentClients();
        List<BlockedClient> blockedClients = ruckusRestTemplate.queryForBlockedClients();


        List<RuckusWirelessClient> collect = new ArrayList<>();
        for (RuckusWirelessClient ruckusWirelessClient : ruckusWirelessClients) {
            if (blockedClients.stream().noneMatch(u -> u.getMac().equals(ruckusWirelessClient.getClientMac()))) {
                collect.add(ruckusWirelessClient);
            }
        }

        for (RuckusWirelessClient ruckusWirelessClient : collect) {
            if (!macAddressesOnServer.contains(ruckusWirelessClient.getClientMac())) {
                macsForBlock.add(ruckusWirelessClient);
            }
        }

        for (BlockedClient ruckusWirelessClient : blockedClients) {
            String mac = ruckusWirelessClient.getMac();
            if (macAddressesOnServer.contains(mac)) {
                idsForUnblock.add(ruckusWirelessClient.getId());
            }
        }

        sendBlockRequests(macsForBlock);
        sendUnblockRequests(idsForUnblock);


    }

    private void sendBlockRequests(List<RuckusWirelessClient> macsForBlock) {
        ruckusRestTemplate.sendBlockUsersRequest(macsForBlock);
    }

    private void sendUnblockRequests(List<String> ids) {
        ruckusRestTemplate.sendUnblock(ids);
    }

}
