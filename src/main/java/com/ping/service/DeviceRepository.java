package com.ping.service;

import com.ping.domain.Device;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author musaal
 * @date 2/3/2021 : 11:30 AM
 */
public class DeviceRepository {

    public static List<Device> loadAllFromFile(){

        try (BufferedReader br = new BufferedReader(new FileReader("devices.txt"))){

            List<String> str = br.lines().flatMap(s -> Arrays.stream(s.split("\\n+")).map(String::toString)).collect(Collectors.toList());

            return convert(str);

        }catch(Exception ex){
            System.out.println("Exception on loading data from file."+ex);
        }
        return Collections.emptyList();
    }

    public static List<Device> convert(List<String> devices){

        List<Device> deviceList = null;

        try {
            deviceList = new ArrayList<>();
            for (String deviceStr : devices) {
                String[] d = deviceStr.split("\\,");
                Device device = new Device(d[0].trim(), d[1].trim());
                device.setReachable(true); // let's consider that device now is reachable.
                deviceList.add(device);
            }
        }catch (Exception ex){
            System.out.println("File converting exception "+ex);
        }
        return deviceList;
    }

}
