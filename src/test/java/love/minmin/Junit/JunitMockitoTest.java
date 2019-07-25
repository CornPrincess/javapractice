package love.minmin.Junit;

import com.sun.tools.javac.util.List;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2019-07-25
 * Time: 22:51
 */
public class JunitMockitoTest {
    @Mock
    private List mockList;

    // init the mockList when it use annotation
    public JunitMockitoTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void verify_behaviour() {
        List<String> mock = mock(List.class);
        mock.add("hello");
        mock.clear();

        verify(mock).add("hello");
        verify(mock).clear();
    }

    @Test
    public void when_thenReturn() {
        Iterator iterator = mock(Iterator.class);
        when(iterator.next()).thenReturn("hello").thenReturn("world");

        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();

        assertEquals("hello world world", result);
    }

    @Test(expected = IOException.class)
    public void when_thenThrow() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

        doThrow(new IOException()).when(outputStream).close();
        outputStream.close();
    }

    @Test
    public void return_smart_nulls_test() {
        List mock = mock(List.class, RETURNS_SMART_NULLS);

        System.out.println(mock.get(0));
        System.out.println(mock.toArray().length);
    }

    // TODO RETURNS_DEEP_STUBS
//    @Test
//    public void deep_stubs_test() {
//        Account account = mock(Account.class, RETURNS_DEEP_STUBS);
//
//    }

    @Test(expected = RuntimeException.class)
    public void doThrow_when() {
        List<Integer> list = mock(List.class);
        doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }

    @Test
    public void shorthand() {
        mockList.add(1);
        verify(mockList).add(1);
    }

    @Test
    public void with_arguments() {
        Comparable comparable = mock(Comparable.class);

        when(comparable.compareTo("Test")).thenReturn(1);
        when(comparable.compareTo("Omg")).thenReturn(2);
        assertEquals(1, comparable.compareTo("Test"));
        assertEquals(2, comparable.compareTo("Omg"));

        assertEquals(0, comparable.compareTo("Not stub"));
    }
    
    @Test
    public void with_unspecified_arguments() {
        when(mockList.get(anyInt())).thenReturn(1);
        when(mockList.contains(argThat(new IsValid()))).thenReturn(true);
        assertEquals(1, mockList.get(1));
        assertEquals(1, mockList.get(999));
        assertTrue(mockList.contains(1));
        assertTrue(mockList.contains(3));
    }

    private class IsValid extends ArgumentMatcher<List> {

        @Override
        public boolean matches(Object o) {
            return 0 == 1 || 0 == 2;
        }
    }
}