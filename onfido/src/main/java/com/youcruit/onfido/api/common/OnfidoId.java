package com.youcruit.onfido.api.common;

public class OnfidoId {
    private final String id;

    public OnfidoId(String id) {
	this.id = id;
    }

    public String getId() {
	return id;
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o.getClass() == getClass())) return false;

	OnfidoId onfidoId = (OnfidoId) o;

	return id.equals(onfidoId.id);
    }

    @Override
    public int hashCode() {
	return id.hashCode();
    }

    @Override
    public String toString() {
	return getClass().getSimpleName() + "{" +
		"id='" + id + '\'' +
		'}';
    }
}
