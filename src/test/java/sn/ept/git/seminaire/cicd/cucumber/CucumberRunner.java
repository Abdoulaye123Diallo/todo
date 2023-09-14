package sn.ept.git.seminaire.cicd.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import sn.ept.git.seminaire.cicd.TodoApplication;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"sn.ept.git.seminaire.cicd.cucumber"},
        plugin = {
                "pretty",
                "html:target/cucumber/cucumber-html.html",
                "json:target/cucumber/cucumber-json.json"
        }
)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TodoApplication.class
)
@AutoConfigureMockMvc
@CucumberContextConfiguration
@TestPropertySource(locations = {"classpath:application.properties", "classpath:cucumber.properties"})
public class CucumberRunner {
}


/*
* 1 => scenarii
* 2 => classes de tests
* 3 => la glue (colle) qui va faire la liaision entre les scenarii et les classes de test
* */
