package com.lance.demo.springboot.prometheus;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GaugeService {
    private MeterRegistry meterRegistry;
    public void gaugeInc(String label,String... tags) {
        Gauge gauge = Gauge.builder(label, null, null)
                .tags(tags)
                .register(meterRegistry);

    }
}
