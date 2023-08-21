import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

//공식 api doc 참고
//https://junit.org/junit5/docs/current/api/org.junit.platform.suite.api/org/junit/platform/suite/api/package-summary.html
@Suite
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectPackages("util.stream")
public class SuiteDemo {
}
