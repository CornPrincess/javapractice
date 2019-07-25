package love.minmin.Junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by IntelliJ IDEA.
 * User: zhoutianbin
 * Date: 2019-07-25
 * Time: 23:26
 */

// method2 to init mock param
@RunWith(MockitoJUnitRunner.class)
public class MockitoRunnerTest {
    @Mock
    private List mockList;


    // method1 to init mock param
//    public MockitoRunnerTest() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void test_init() {
        mockList.add(1);
        verify(mockList).add(1);
    }
}