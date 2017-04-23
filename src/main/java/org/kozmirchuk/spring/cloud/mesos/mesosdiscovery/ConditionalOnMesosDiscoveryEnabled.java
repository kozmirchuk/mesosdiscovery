package org.kozmirchuk.spring.cloud.mesos.mesosdiscovery;

import org.kozmirchuk.spring.cloud.mesos.client.MesosClient;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.ConfigurationCondition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(ConditionalOnMesosDiscoveryEnabled.OnMesosDiscoveryEnabledCondition.class)
public @interface ConditionalOnMesosDiscoveryEnabled {

    class OnMesosDiscoveryEnabledCondition extends AllNestedConditions {

        public OnMesosDiscoveryEnabledCondition() {
            super(ConfigurationCondition.ConfigurationPhase.REGISTER_BEAN);
        }

        @ConditionalOnProperty(value = "spring.cloud.mesos.mesosdiscovery.enabled", matchIfMissing = true)
        static class FoundProperty {}

        @ConditionalOnClass(MesosClient.class)
        static class FoundClass {}
    }
}
