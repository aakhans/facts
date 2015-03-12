package au.amir.personal.facts.tests;

import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;

import au.amir.personal.facts.model.FactsSheet;
import au.amir.personal.facts.service.MyService;

/**
 * Created by amir on 3/12/2015.
 */
public class DataTests extends TestCase {

    FactsSheet factsSheet;
    boolean    dataFetched;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dataFetched = MyService.getInstance().prepareData("https://dl.dropboxusercontent.com/u/746330/facts.json");
        if (!dataFetched) return;

        factsSheet = MyService.getInstance().getFactsSheet();
    }

    @SmallTest
    public void testIsDataFetchedSuccessfully()
    {
        assertTrue(dataFetched); // if preparedata in MyService returns true then test passed
    }

    @SmallTest
    public void testFactsTitle()
    {
        assertEquals("About Canada",factsSheet.getTitle()); // if we found title as "About Canada" then this test passed
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
