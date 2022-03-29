package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LauncherTest {
    @Test
    public void bigIntPort() {
        Assertions.assertThrows(
            Exception.class,
            () -> Launcher.main(new String[] { "123456789" })
        );
    }
/*
    @Test
    public void StringForPort() {
        Assertions.assertThrows(
            Exception.class,
            () -> Launcher.main(new String[] { "SpecialPort" })
        );
    }

    @Test
    public void correctPort() {
        Assertions.assertDoesNotThrow(
            () -> Launcher.main(new String[] { Integer.toString(9876) })
        );
    }

    @Test
    public void mainWithoutArgs() {
        Assertions.assertDoesNotThrow( () -> Launcher.main(new String[] {}));
    }
    */
}
