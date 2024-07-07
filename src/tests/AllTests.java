package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    CalculaTotalTest.class,
    CalculaFreteTest.class,
    CalculaTaxaTest.class,
    CalculaCashbackTest.class,
    ClienteTest.class,
    EnderecoTest.class
})
public class AllTests {
}
