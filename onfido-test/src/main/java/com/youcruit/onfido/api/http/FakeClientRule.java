package com.youcruit.onfido.api.http;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class FakeClientRule implements TestRule {

    private FakeHttpClient fakeHttpClient;

    public void setClient(FakeHttpClient fakeHttpClient) {
	this.fakeHttpClient = fakeHttpClient;
    }

    @Override
    public Statement apply(Statement base, Description description) {
	return new FakeClientEmpty(base);
    }

    private class FakeClientEmpty extends Statement {
	private final Statement next;

	public FakeClientEmpty(Statement base) {
	    next = base;
	}

	@Override
	public void evaluate() throws Throwable {
	    next.evaluate();
	    if (fakeHttpClient != null) {
		fakeHttpClient.assertAtEnd();
	    }
	}
    }
}
