
Welcome to Ping Man <img src="https://raw.githubusercontent.com/MartinHeinz/MartinHeinz/master/wave.gif" width="30px">
---
#### Application designed to ping specified devices on devices.txt file to identify device is UP(Reachable) or DOWN

### Functionality

- Parse and load to internal memory devices from given file.

- Ping specified devices in given interval with specified ping timeout.

- In case of if device is not reachable (DOWN) -> inform to responsible person (EMAIL, CONSOLE)

- In case of if device issue fixed and now device is reachable (UP) -> also inform to responsible person to live good life

- Running with custom or default parameters.

---
### For running application you can use custom params or application will run with default params

#### Running Application with default params.
```
// go to application path and type
java -jar PingMan-1.0-SNAPSHOT.jar
```
####Default parameters is
```
initialDelay = 2;// second - after what second application will start
interval = 10;// second - interval of pinging action
pingTimeout = 2;// second
verbose = false // false -> show only success(UP) and warning(DOWN) console messages, 
//true -> additionally show every pinging result to defined devices.
```

#### Running application with custom parameters.
```
// go to application path and type (you can customise how many params you like -> in this case app will use defaut value to not specified params.)
// example:
java -jar PingMan-1.0-SNAPSHOT.jar --initialDelay=5 --interval=5 --pingTimeout=2 --verbose=true
// or
java -jar PingMan-1.0-SNAPSHOT.jar --interval=5 --pingTimeout=2
```


Devices file format
---
Name, IP
```
Kurdermir ATS1, 192.168.1.1
Google ATS, 8.8.8.8
Yevlax ATS3, 78.109.49.175
Tekila JOB, 10.13.44.69
```