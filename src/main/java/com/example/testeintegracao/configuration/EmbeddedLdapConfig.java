package com.example.testeintegracao.configuration;

import com.unboundid.ldap.listener.InMemoryDirectoryServer;
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig;
import com.unboundid.ldap.listener.InMemoryListenerConfig;
import com.unboundid.ldif.LDIFReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLSocketFactory;
import java.io.InputStream;
import java.net.InetAddress;

@Configuration
public class EmbeddedLdapConfig {

    @Bean(destroyMethod = "shutDown")
    public InMemoryDirectoryServer ldapServer() throws Exception {
        InMemoryDirectoryServerConfig config =
                new InMemoryDirectoryServerConfig("dc=springframework,dc=org");
        config.setListenerConfigs(
                InMemoryListenerConfig.createLDAPConfig(
                        "ldap",
                        InetAddress.getByName("0.0.0.0"),
                        8389,
                        (SSLSocketFactory) null
                )
        );
        InMemoryDirectoryServer server = new InMemoryDirectoryServer(config);
        try (InputStream ldifStream =
                     getClass().getClassLoader().getResourceAsStream("ldap-data.ldif")) {
            if (ldifStream == null) {
                throw new IllegalStateException("ldap-data.ldif not found in classpath");
            }
            LDIFReader reader = new LDIFReader(ldifStream);
            server.importFromLDIF(true, reader);
        }
        server.startListening();
        System.out.println("Embedded LDAP started on port 8389");
        return server;
    }

}
