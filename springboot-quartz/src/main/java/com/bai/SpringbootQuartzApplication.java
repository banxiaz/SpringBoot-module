package com.bai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootQuartzApplication.class, args);
    }

}

/**
 D:\Java\JDK\jdk1.8\bin\java.exe
 -XX:TieredStopAtLevel=1
 -noverify
 -Dspring.output.ansi.enabled=always
 "-javaagent:D:\JetBrains\IntelliJ IDEA 2021.2\lib\idea_rt.jar=54471:" + "D:\JetBrains\IntelliJ IDEA 2021.2\bin"
 -Dcom.sun.management.jmxremote
 -Dspring.jmx.enabled=true
 -Dspring.liveBeansView.mbeanDomain
 -Dspring.application.admin.enabled=true
 -Dfile.encoding=UTF-8
 -classpath D:\Java\JDK\jdk1.8\jre\lib\charsets.jar;
 D:\Java\JDK\jdk1.8\jre\lib\deploy.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\access-bridge-64.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\cldrdata.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\dnsns.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\jaccess.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\jfxrt.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\localedata.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\nashorn.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\sunec.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\sunjce_provider.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\sunmscapi.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\sunpkcs11.jar;
 D:\Java\JDK\jdk1.8\jre\lib\ext\zipfs.jar;
 D:\Java\JDK\jdk1.8\jre\lib\javaws.jar;
 D:\Java\JDK\jdk1.8\jre\lib\jce.jar;
 D:\Java\JDK\jdk1.8\jre\lib\jfr.jar;
 D:\Java\JDK\jdk1.8\jre\lib\jfxswt.jar;
 D:\Java\JDK\jdk1.8\jre\lib\jsse.jar;
 D:\Java\JDK\jdk1.8\jre\lib\management-agent.jar;
 D:\Java\JDK\jdk1.8\jre\lib\plugin.jar;
 D:\Java\JDK\jdk1.8\jre\lib\resources.jar;
 D:\Java\JDK\jdk1.8\jre\lib\rt.jar;
 D:\Java_Files\Demo_pdai\springboot-module\springboot-quartz\target\classes;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\boot\spring-boot-starter-web\2.6.0\spring-boot-starter-web-2.6.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\boot\spring-boot-starter-json\2.6.0\spring-boot-starter-json-2.6.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\com\fasterxml\jackson\core\jackson-databind\2.13.0\jackson-databind-2.13.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\com\fasterxml\jackson\core\jackson-annotations\2.13.0\jackson-annotations-2.13.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\com\fasterxml\jackson\core\jackson-core\2.13.0\jackson-core-2.13.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.13.0\jackson-datatype-jdk8-2.13.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.13.0\jackson-datatype-jsr310-2.13.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\com\fasterxml\jackson\module\jackson-module-parameter-names\2.13.0\jackson-module-parameter-names-2.13.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\boot\spring-boot-starter-tomcat\2.6.0\spring-boot-starter-tomcat-2.6.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\apache\tomcat\embed\tomcat-embed-core\9.0.55\tomcat-embed-core-9.0.55.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\apache\tomcat\embed\tomcat-embed-el\9.0.55\tomcat-embed-el-9.0.55.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\apache\tomcat\embed\tomcat-embed-websocket\9.0.55\tomcat-embed-websocket-9.0.55.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\spring-web\5.3.13\spring-web-5.3.13.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\spring-beans\5.3.13\spring-beans-5.3.13.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\spring-webmvc\5.3.13\spring-webmvc-5.3.13.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\spring-aop\5.3.13\spring-aop-5.3.13.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\spring-context\5.3.13\spring-context-5.3.13.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\spring-expression\5.3.13\spring-expression-5.3.13.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\slf4j\slf4j-api\1.7.32\slf4j-api-1.7.32.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\spring-core\5.3.13\spring-core-5.3.13.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\spring-jcl\5.3.13\spring-jcl-5.3.13.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\boot\spring-boot-starter-quartz\2.6.0\spring-boot-starter-quartz-2.6.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\spring-context-support\5.3.13\spring-context-support-5.3.13.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\spring-tx\5.3.13\spring-tx-5.3.13.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\quartz-scheduler\quartz\2.3.2\quartz-2.3.2.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\com\mchange\mchange-commons-java\0.2.15\mchange-commons-java-0.2.15.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\projectlombok\lombok\1.18.20\lombok-1.18.20.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\boot\spring-boot-starter\2.6.0\spring-boot-starter-2.6.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\boot\spring-boot\2.6.0\spring-boot-2.6.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\boot\spring-boot-autoconfigure\2.6.0\spring-boot-autoconfigure-2.6.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\springframework\boot\spring-boot-starter-logging\2.6.0\spring-boot-starter-logging-2.6.0.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\ch\qos\logback\logback-classic\1.2.7\logback-classic-1.2.7.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\ch\qos\logback\logback-core\1.2.7\logback-core-1.2.7.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\apache\logging\log4j\log4j-to-slf4j\2.14.1\log4j-to-slf4j-2.14.1.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\apache\logging\log4j\log4j-api\2.14.1\log4j-api-2.14.1.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\slf4j\jul-to-slf4j\1.7.32\jul-to-slf4j-1.7.32.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;
 D:\Java\apache-maven-3.8.2\maven-repo\org\yaml\snakeyaml\1.29\snakeyaml-1.29.jar
 com.bai.SpringbootQuartzApplication
 */