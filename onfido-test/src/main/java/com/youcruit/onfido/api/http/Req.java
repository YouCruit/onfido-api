package com.youcruit.onfido.api.http;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Req {
    final String encodedPathSegment;
    final Map<String, String> queryParameters;
    final List<String> pathSegments;

    public Req(String encodedPathSegment, Map<String, String> queryParameters, List<String> pathSegments) {
	this.encodedPathSegment = encodedPathSegment;
	this.queryParameters = queryParameters;
	this.pathSegments = pathSegments;
    }

    public Req(Map<String, String> queryParameters, List<String> pathSegments) {
	this("", queryParameters, pathSegments);
    }

    public Req(List<String> pathSegments) {
	this("", Collections.emptyMap(), pathSegments);
    }

    @Override
    public boolean equals(final Object o) {
	if (this == o) {
	    return true;
	}
	if (!(o instanceof Req)) {
	    return false;
	}

	Req req = (Req) o;

	if (!encodedPathSegment.equals(req.encodedPathSegment)) {
	    return false;
	}
	if (!queryParameters.equals(req.queryParameters)) {
	    return false;
	}
	return pathSegments.equals(req.pathSegments);

    }

    @Override
    public int hashCode() {
	int result = encodedPathSegment.hashCode();
	result = 31 * result + queryParameters.hashCode();
	result = 31 * result + pathSegments.hashCode();
	return result;
    }

    @Override
    public String toString() {
	return "Req{" +
		"encodedPathSegment='" + encodedPathSegment + '\'' +
		", queryParameters=" + queryParameters +
		", pathSegments=" + pathSegments +
		'}';
    }
}
