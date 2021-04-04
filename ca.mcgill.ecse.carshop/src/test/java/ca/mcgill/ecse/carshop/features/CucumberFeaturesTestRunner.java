package ca.mcgill.ecse.carshop.features;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "pretty", features = "src/test/resources/MakeAppointment.feature")
public class CucumberFeaturesTestRunner {
	
}