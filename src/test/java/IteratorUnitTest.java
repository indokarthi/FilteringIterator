import org.junit.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by u0168167 on 1/23/2017.
 */
public class IteratorUnitTest {

    @Test
    public void FilteringIterator_EmptyIterator_hasNextReturnsFalse()
    {
        List emptyList = new ArrayList();
        FilteringIterator t = new FilteringIterator(emptyList.iterator(), Mockito.mock(IObjectTest.class));
        assertEquals(t.hasNext(),false);
    }

    @Test(expected= NoSuchElementException.class)
    public void FilteringIterator_EmptyIterator_nextThrowsException()
    {
        List emptyList = new ArrayList();
        FilteringIterator t = new FilteringIterator(emptyList.iterator(), Mockito.mock(IObjectTest.class));
        t.next();
    }

    @Test
    public void FilteringIterator_IteratorWithValidObject_ShouldReturnItem()
    {
        List emptyList = new ArrayList();
        Integer toCompare = new Integer(1);
        emptyList.add(toCompare);

        FilteringIterator t = new FilteringIterator(emptyList.iterator(),createMockIObjectTest(true));
        assertEquals(t.hasNext(),true);
        assertEquals(toCompare,t.next());
    }

    @Test
    public void FilteringIterator_IteratorWithInValidObject_ShouldNotReturnItem()
    {
        List emptyList = new ArrayList();
        Object toCompare = new Object();
        emptyList.add(toCompare);
        FilteringIterator t = new FilteringIterator(emptyList.iterator(),createMockIObjectTest(false));
        assertEquals(t.hasNext(),false);
    }


    /**
     * Creates Mock IObjectTest and returns output based on the input to this method
     * @param valueToReturn True: Will return true for any object , False : false for any object
     * @return mock object for IObjectTest
     */
    private IObjectTest createMockIObjectTest(boolean valueToReturn) {
        IObjectTest testMock = Mockito.mock(IObjectTest.class);
        when(testMock.test(anyObject())).thenReturn(valueToReturn);
        return testMock;
    }

}
