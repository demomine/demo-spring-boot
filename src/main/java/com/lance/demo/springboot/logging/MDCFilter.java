package com.lance.demo.springboot.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

public class MDCFilter extends AbstractMatcherFilter<ILoggingEvent> {
    private static final String MONITOR_FLAG = "_monitor";
    @Override
    public FilterReply decide(ILoggingEvent event) {
        Object o = event.getMDCPropertyMap().get(MONITOR_FLAG);
        if (o != null && String.valueOf(o).equalsIgnoreCase(Boolean.TRUE.toString())) {
            return this.onMatch;
        }
        return this.onMismatch;
    }
}
