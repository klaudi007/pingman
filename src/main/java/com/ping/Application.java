package com.ping;

import com.ping.domain.Device;
import com.ping.service.DeviceRepository;
import com.ping.service.DeviceService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author musaal
 * @date 2/2/2021 : 6:15 PM
 */
public class Application {

//    private static int count = 0;

    private static int initialDelay = 1;// second

    private static int interval = 10;// second

    private static int pingTimeout = 2;// second

    private static List<Device> devices = new ArrayList<>();

    static{
        initDevices();
    }

    public static void main(String args[]){

//        String initialDelayStr = System.getProperty("initialDelay");
//        String intervalStr = System.getProperty("interval");
//        String pingTimeoutStr = System.getProperty("pingTimeout");
        Map<String, String> applicationParams = convertToKeyValuePair(args);

        try{
            if(applicationParams.get("initialDelay")!=null)
                initialDelay = Integer.parseInt(applicationParams.get("initialDelay"));

            if(applicationParams.get("interval")!=null)
                interval = Integer.parseInt(applicationParams.get("interval"));

            if(applicationParams.get("pingTimeout")!=null)
                pingTimeout = Integer.parseInt(applicationParams.get("pingTimeout"));

            System.out.printf("Application params is => initialDelay : %d, interval : %d, pingTimout : %d %n%n",
                    initialDelay, interval, pingTimeout);

        }catch (Exception ex){
            System.out.println("Exception on parsing custom initial params "+ex);
        }


        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        Runnable task1 = () -> {
//            count++;
            run();
        };

        // init Delay = 5, repeat the task every 1 second
        ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task1, initialDelay, interval, TimeUnit.SECONDS);



//        while (true) {
//            System.out.println("count :" + count);
////            Thread.sleep(1000);
//            if (count == 5) {
//                System.out.println("Count is 5, cancel the scheduledFuture!");
//                scheduledFuture.cancel(true);
//                ses.shutdown();
//                break;
//            }
//        }

    }


    public static void run(){
//        System.out.println("=============== Application Running  ==================");
        try {
            for(Device device : devices){
                DeviceService.sendPingRequest(device, pingTimeout);
//                if(!device.isReachable()){
//
//                }else{
//
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.printf("%n");
    }

    public static void initDevices(){

        devices = DeviceRepository.loadAllFromFile();

        System.out.println("=============== Ping Man: Successfully initialised "+devices.size()+" Devices ==================");
    }

    private static HashMap<String, String> convertToKeyValuePair(String[] args) {

        HashMap<String, String> params = new HashMap<>();

        for (String arg: args) {

            String[] splitFromEqual = arg.split("=");

            String key = splitFromEqual[0].substring(2);
            String value = splitFromEqual[1];

            params.put(key, value);

        }

        return params;
    }

}


//        Device kurdemirAts = new Device("Kurdemir ATS", "192.168.1.1");
//        Device GoogleAts = new Device("Google ATS", "8.8.8.8");
//        Device yevlaxAts = new Device("Yevlax ATS", "78.109.49.175");
//        Device yevlaxAts2 = new Device("Yevlax ATS", "78.109.49.176");
//        Device yevlaxAts3 = new Device("Yevlax ATS", "78.109.49.177");
//
//        devices.add(kurdemirAts);
//        devices.add(GoogleAts);
//        devices.add(yevlaxAts);
//        devices.add(yevlaxAts2);
//        devices.add(yevlaxAts3);
