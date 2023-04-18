SDK for work with indoor navigation.

Подключение библиотеки к проекту:
Сначала необходимо __подключить необходимые зависимости__ в файл gradle:
```gradle
dependencies {
    ...

    implementation files('libs/app-debug.aar')
    implementation files('libs/data-debug.aar')
    implementation files('libs/domain-debug.aar')

    implementation 'org.altbeacon:android-beacon-library:2.19.5' // https://altbeacon.github.io/android-beacon-library
    implementation 'com.lemmingapex.trilateration:trilateration:1.0.2'

    ...
}
```
Первые три файла генерируются при компиляции библиотеки в X/build/outputs/aar/X-debug.aar
, где X - модуль библиотеки, то есть app, domain и data.
Сейчас пока что эти файлы можно взять здесь -> https://github.com/CodeR-na-r1/IndoorServiceApp/tree/main/app/libs

Остальные зависимости покдлючают библиотеку для работы с маячками и модули для рассчета трилатерации.

Далее необходимо __добавить разрешение__ в манифест:
Разрешения для обнаружения Wi-Fi маячков:
```xml
<!-- Permissions for Wi-Fi beacons -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
```

Разрешения для обнаружения Bluetooth маячков:
```xml
<!-- Permissions for Bluetooth beacons -->
<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.BLUETOOTH_SCAN"/>
    <!-- Below is only needed if you want to read the device name or establish a bluetooth connection
    -->
    <uses-permission android:name="android.permission.BLUETOOTH_CONNECT"/>
```

Использование:

Взаимодействие с функционалом SDK осуществляется через API синглтон класс типа _IndoorService_

Внутри IndoorService на данный момент находятся пять вспомогательных классов, каждый из которых отвечает за отведенный ему функционал:
+ AzimuthManager
    > Отвечает за получение азимута при помощи встроенных датчиков (IMU) телефона
+ BluetoothBeaconsEnvironment
    > Отвечает за получение количества BLE маяков вокруг, а также расстояние до каждого из них в метрах
+ WiFiBeaconsEnvironment
    > Отвечает за получение количества Wi-Fi маяков вокруг, а также их уровень сигнала (RSSI)
+ Position
    > Позволяет методом трилатерации определить положение пользователя (2D-координату) при помощи передаваемой ему дистанции до каждого маячка, а также заданных в начале константных координат самих маячков.
+ Mapper
    > Позволяет удобно преобразовать данные из одного типа в другой при передаче обьектов между модулями библиотеки.

Подробный функционал каждого из вспомогательных классов выше:
1. _AzimuthManager_
    + Начать мониторинг сенсоров: __startListen()__ и __stopListen()__ для остановки мониторинга, вызываются обычно в методах onResume и onPause() соответсвенно.
    + Получить последнее полученное значение азимута: __getAzimuth()__
    + Получить viewmodel для подписки на данные: __getAzimuthViewModel()__
2. _BluetoothBeaconsEnvironment_
    + Начать поиск и анализ маячков: __startRanging()__ и __stopRanging()__ для остановки поиска маячков, вызываются обычно в методах onResume и onPause() соответсвенно.
    + Получить последнюю полученную информацию о маячках: __getRanging()__
    + Получить viewmodel для подписки на данные о маячках: __getRangingViewModel()__
3. _WiFiBeaconsEnvironment_
    + Функционал будет описан позже.
4. _Position_
    + __setEnvironment()__ вызывается единожды для установки начальных позиций маячков. В качестве аргумента принимает коллекцию из элементов типа StateEnvironment, в которой храниться mac-адрес маячка (тип String) и  стуктура Point с координатами x, y (тип Double). (*__Без вызова этого метода, остальные методы данного класса работать не будут!__*)
    + __getPosition()__ вызывается сколь угодно для получения позиции пользователя, рассчитывающегося при помощи установленных ранее в методе setEnvironment() начальных позиций маячков, а также дистанций до каждого маячка, передаваемых в качестве входного параметра, который является коллекцией с элементами типа EnvironmentInfo. EnvironmentInfo представляет собой структуру с полями id (тип String) и distance (тип Double)
5. _Mapper_
    + Функционал будет описан позже.

Примерная схема SDK:
<img src="./schemeSDK.svg">

*Возможные проблемы:*

Если программа не может определить конфигарацию для сборки (требует ее добавить), то необоходимо зайти в пункт меню 'File' -> 'Invalidate Caches...'