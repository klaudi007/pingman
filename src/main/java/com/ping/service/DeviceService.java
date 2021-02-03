package com.ping.service;

import com.ping.domain.Device;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author musaal
 * @date 2/2/2021 : 6:15 PM
 */
public class DeviceService {


    public static final String ANSI_RESET = "\u001B[0m"; // \033[36m
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

//    private static final Logger log = LoggerFactory.getLogger(DeviceService.class);

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public static Device sendPingRequest(Device device, int timeoutInSecond, boolean verbose) throws IOException {

//        log.debug("Sending ping request to device :{}, ip :{}", device.getName(), device.getIp());

        InetAddress inetAddress = InetAddress.getByName(device.getIp());

        String now = LocalDateTime.now().format(formatter);

        StringBuilder consoleMessage = new StringBuilder();

        if(inetAddress.isReachable(timeoutInSecond*1000)){
//            log.debug("Device :{}, ip :{} is reachable.", device.getName(), device.getIp());
            consoleMessage.append(ANSI_GREEN).append("UP: ");
            if(!device.isReachable()) // checking previous status.
            {
                device.setReachable(true);
                device.setIssueFixed(true);
                device.setResponsiblePersonNotified(false); // to notify responsible person again in case of issue in future.
                successMsg(device, now);
            }

        }else{
//            log.debug("Device :{}, ip :{} is NOT reachable.", device.getName(), device.getIp());
            consoleMessage.append(ANSI_RED).append("DOWN: ");
            device.setReachable(false);
            if(!device.isResponsiblePersonNotified()){
                failMsg(device, now);
                device.setIssueFixed(false);
                device.setResponsiblePersonNotified(true);
            }
        }

        if(verbose){
            consoleMessage.append(device.getName())
                    .append(", ip: ").append(device.getIp())
                    .append(", time: ").append(now).append(ANSI_RESET);
            System.out.println(consoleMessage);
        }

        return device;
    }

    public static void failMsg(Device device, String time){
        System.out.printf(ANSI_RED+"%n Warning:  %s, ip: %s is DOWN, time: %s %n"+ANSI_RESET, device.getName(), device.getIp(), time);
    }

    public static void successMsg(Device device, String time){
        System.out.printf(ANSI_GREEN+"%n Success:  %s, ip: %s UP, time: %s %n"+ANSI_RESET, device.getName(), device.getIp(), time);
    }

    public static Device issueFixed(Device device){
        device.setIssueFixed(true);
        return device;
    }

}
