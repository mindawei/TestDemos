package mockito;

//Let's import Mockito statically so that the code looks clearer

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;


public class _111VerifyBehavior {


    @Test
    public void run() {


        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

}
