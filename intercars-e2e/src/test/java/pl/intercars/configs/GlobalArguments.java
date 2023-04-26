package pl.intercars.configs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GlobalArguments {
    public static final int PROXY_PORT = 1234;
    public static final List<String> DRIVER_ARGUMENTS = Arrays.asList("--remote-allow-origins=*", "ignore-certificate-errors");
    public static final int DRIVER_FLUENT_WAIT_POOLING_IN_MILLIS = 500;
    public static final int DRIVER_FLUENT_WAIT_TIMEOUT_IN_SECONDS = 10;
    public static final int DRIVER_IMPLICIT_WAIT_IN_SECONDS = 5;
    public static final String URL = "https://intercars.pl/";
    public static final String CUCUMBER_GLUE = "pl.intercars";
    public static final String FEATURES = "src/test/resources/features";


}
