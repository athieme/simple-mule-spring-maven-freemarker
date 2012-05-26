package com.athieme;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Transformer {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("com/athieme/applicationContext.xml");
        Transformer transformer = (Transformer) context.getBean("transformer");
        transformer.transform();
    }

    public static class Message {
        public static class Password {
            public String getEmail() {
                return "athieme@athieme.com";
            }

            public String getUrl() {
                return "http://localhost";
            }

            public String getHash() {
                return "hash";
            }
        }

        public Message() {
            super();
        }

        public Password getPassword() {
            return new Password();
        }
    }

    public Transformer() {
        super();
    }

    public void transform() throws Exception {
        final StringWriter result = new StringWriter();
        final Template template = configuration.getTemplate("password.ftl");
        final Message message = new Message();
        final Map<String, Message> map = new HashMap<String, Message>();
        map.put("message", message);
        template.process(map, result);
        String str = result.toString();
        System.out.println(str);
    }

    public void setConfiguration(final Configuration configuration) {
        this.configuration = configuration;
    }

    private Configuration configuration;
}
