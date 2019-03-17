import com.tenbent.wework.Wework;
import com.tenbent.wework.WeworkConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestGetToken {
    @Test
    void testToken(){
        Wework wework=new Wework();
        String token=wework.getWeworkToken(WeworkConfig.getInstance().secret);
        assertThat(token, not(equalTo(null)));
    }
}
