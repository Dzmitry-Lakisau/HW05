package by.dzmitry_lakisau.hw05;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ThreadRunnerTest {

    // Class under test
    private Caller caller;

    @Mock
    private ThreadRunner mockThreadRunner;

    @Captor
    private ArgumentCaptor<Callback> callbackArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        caller = new Caller(mockThreadRunner);
    }

    @Test
    public void testDoSomethingAsynchronouslyUsingArgumentCaptor() {

        caller.doSomethingAsynchronously();

        final List<String> results = Arrays.asList("One", "Two", "Three");

        verify(mockThreadRunner, times(1)).doSomethingAsynchronously(callbackArgumentCaptor.capture());

        assertThat(caller.getResult().isEmpty(), is(true));

        callbackArgumentCaptor.getValue().onSuccess(results);

        assertThat(caller.getResult(), is(equalTo(results)));
    }
}