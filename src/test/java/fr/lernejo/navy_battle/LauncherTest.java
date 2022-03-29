package fr.lernejo.navy_battle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LauncherTest {

    private static int myPort = 9876;

    @AfterEach
    public void increment_testPort() {
        myPort++;
    }

    @Test
    public void bigIntPort() {
        Assertions.assertThrows(
            Exception.class,
            () -> Launcher.main(new String[] { "123456789" })
        );
    }

    @Test
    public void correctPort() {
        Assertions.assertDoesNotThrow( () -> Launcher.main(new String[] { Integer.toString(myPort) }));
    }

    @Test
    public void correctPortAndUrl() {
        Assertions.assertDoesNotThrow( () -> Launcher.main(new String[] { Integer.toString(myPort++) }));
        Assertions.assertDoesNotThrow( () -> Launcher.main(new String[] { Integer.toString(myPort), "http://localhost:"+(myPort-1) }));
    }

    @Test
    public void StringForPort() {
        Assertions.assertThrows(
            Exception.class,
            () -> Launcher.main(new String[] { "SpecialPort" })
        );
    }

    @Test
    public void mainWithoutArgs() {
        Assertions.assertDoesNotThrow( () -> Launcher.main(new String[] {}));
    }

}
