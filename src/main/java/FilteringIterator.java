import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by u0168167 on 1/23/2017.
 */
public class FilteringIterator<T> implements Iterator<T> {

    Iterator<T> baseIterator;
    IObjectTest<T> objectTester;
    T currentMatch;
    boolean hasNext;

    public FilteringIterator(Iterator<T> iterator,IObjectTest<T> objectTester)
    {
        this.baseIterator = iterator;
        this.objectTester = objectTester;
        // Initialize hasNext and get the first valid object
        currentMatch = getCurrentMatch();
    }

    public boolean hasNext() {
        return hasNext;
    }

    // Returns the object that's iterated already and return it.
    // Also find out the next match proactively and also set hasNext value
    public T next() {
        if(!hasNext)
        {
            throw new NoSuchElementException();
        }
        T previousMatch = currentMatch;
        currentMatch = getCurrentMatch();
        return previousMatch;
    }

    // Helper method that uses the base iterator and gets the
    private T getCurrentMatch()
    {
        T nextElement = null;
        hasNext = false;
        while(baseIterator.hasNext())
        {
            T nextBaseElement = baseIterator.next();
            if(objectTester.test(nextBaseElement))
            {
                nextElement = nextBaseElement;
                hasNext = true;
                break;
            }
        }
        return nextElement;
    }
}
