package org.example;

public class MySingleton {
    private static volatile MySingleton _instance;

    private MySingleton() {

    }

    public static synchronized MySingleton getInstance() {
        if (_instance == null) {
            _instance = new MySingleton();
        }
        return _instance;
    }

    public static MySingleton getInstance_v2() {
        if (_instance != null) {
            return _instance;
        }

        synchronized (MySingleton.class) {
            if (_instance == null) {
                _instance = new MySingleton();
            }
        }
        return _instance;
    }

    public static MySingleton getInstance_v3() {
        if (_instance == null) {
            synchronized (MySingleton.class) {
                if (_instance == null) {
                    _instance = new MySingleton();
                }
            }
        }
        return _instance;
    }
}
