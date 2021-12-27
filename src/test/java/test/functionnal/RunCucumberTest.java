package test.functionnal;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:bank_account.feature"},
        glue = {"test.functionnal"})
public class RunCucumberTest {
}