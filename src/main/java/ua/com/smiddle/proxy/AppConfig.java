package ua.com.smiddle.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * @author ksa on 14.12.16.
 * @project soap_rest_proxy
 */
@Configuration
@EnableWs
@ComponentScan("ua.com.smiddle.proxy")
@PropertySource("classpath:application.properties")
public class AppConfig extends WsConfigurerAdapter {

    @Bean(name = "service")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema serviceSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ServicePort");
        wsdl11Definition.setLocationUri("/soapws");
        wsdl11Definition.setTargetNamespace("http://proxy.smiddle.com.ua/soap");
        wsdl11Definition.setSchema(serviceSchema);
        return wsdl11Definition;
    }
    @Bean
    public XsdSchema serviceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("service.xsd"));
    }
}
