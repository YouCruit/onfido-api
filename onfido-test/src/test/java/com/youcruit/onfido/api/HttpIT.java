package com.youcruit.onfido.api;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.youcruit.onfido.api.http.FakeClientRule;
import com.youcruit.onfido.api.http.FakeHttpClient;
import com.youcruit.onfido.api.http.OkHttpOnfidoClient;
import com.youcruit.onfido.api.http.OnfidoHttpClient;

@SuppressWarnings("deprecation")
@RunWith(Parameterized.class)
public abstract class HttpIT {

    private static final Logger LOGGER = LogManager.getLogger(HttpIT.class);

    private final Class<? extends OnfidoHttpClient> httpClientClass;

    @Rule
    public final FakeClientRule fakeClientRule = new FakeClientRule();

    @Parameters
    public static Collection<Object[]> data() {
	return asList(new Object[][] { { OkHttpOnfidoClient.class } });
    }

    public HttpIT(Class<OnfidoHttpClient> httpClientClass) {
	this.httpClientClass = httpClientClass;
    }

    public OkHttpOnfidoClient createClient(String authToken) {
	if (httpClientClass == OkHttpOnfidoClient.class) {
	    return new OkHttpOnfidoClient(authToken);
	}
	throw new RuntimeException("Unknown client" + httpClientClass.getName());
    }

    public OnfidoHttpClient createClient() {
	String authToken = getPropEnv("ONFIDO_AUTH_TOKEN");
	if (authToken == null) {
	    LOGGER.info("Use environment or property 'ONFIDO_AUTH_TOKEN' to set an authToken. Going with fake client.");
	    FakeHttpClient fakeHttpClient = new FakeHttpClient();
	    fakeClientRule.setClient(fakeHttpClient);
	    return fakeHttpClient;
	}
	return createClient(authToken);
    }

    public static String getPropEnv(String key) {
	String value = System.getProperties().getProperty(key);
	if (value == null) {
	    value = System.getenv(key);
	}
	return value;
    }


    protected void assertSameDate(Calendar expected, Calendar actual) {
	Calendar e = Calendar.getInstance();
	e.setTime(expected.getTime());
	Calendar a = Calendar.getInstance();
	a.setTime(actual.getTime());
	assertEquals("Differs: " + e.getTime().toGMTString() + " and " + a.getTime().toGMTString(), e.get(Calendar.YEAR), a.get(Calendar.YEAR));
	assertEquals("Differs: " + e.getTime().toGMTString() + " and " + a.getTime().toGMTString(), e.get(Calendar.DAY_OF_YEAR), a.get(Calendar.DAY_OF_YEAR));
    }

}
