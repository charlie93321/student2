package com.spring;

import org.springframework.context.ApplicationEvent;

import java.util.Objects;

public class TestEvent extends ApplicationEvent {

    private TestParam source;

    public TestEvent(TestParam source) {
        super(source);
        this.source = source;
    }

    @Override
    public TestParam getSource() {
        return source;
    }

    public void setSource(TestParam source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestEvent testEvent = (TestEvent) o;
        return Objects.equals(source, testEvent.source);
    }

    @Override
    public int hashCode() {

        return Objects.hash(source);
    }
}
