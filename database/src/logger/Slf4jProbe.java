package logger;

import org.slf4j.LoggerFactory;

public class Slf4jProbe {
    public static void main(String[] args) {
        System.out.println("LoggerFactory loaded from: " +
                LoggerFactory.class.getProtectionDomain().getCodeSource().getLocation());
        System.out.println("SLF4J API package version: " +
                LoggerFactory.class.getPackage().getImplementationVersion());
    }
}
