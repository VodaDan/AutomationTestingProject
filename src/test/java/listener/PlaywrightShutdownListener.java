package listener;

import pages.Navigation;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;


public class PlaywrightShutdownListener implements TestExecutionListener {
    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        System.out.println("All tests finished — closing Navigation...");
        Navigation instance = Navigation.getInstanceIfInitialized();
        if (instance != null) {
            instance.navigateClose();
        }
    }
}
