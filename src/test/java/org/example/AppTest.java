package org.example;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MyTestWatcher.class)
public class AppTest {

    @Test
    public void shouldAnswerWithTrue()
    {
        Assertions.assertTrue( false );
    }

    @Test
    public void shouldAnswerWithFalse()
    {
        Assertions.assertFalse( false );
    }

    @Test
    public void shouldBeFalse()
    {
        Assertions.assertFalse( 2 > 3 );
    }
}
