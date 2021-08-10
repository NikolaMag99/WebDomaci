package rs.raf.demo;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.demo.repositories.post.MySqlPostRepository;
import rs.raf.demo.repositories.post.PostRepository;
import rs.raf.demo.services.PostService;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/index")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlPostRepository.class).to(PostRepository.class).in(Singleton.class);

                this.bindAsContract(PostService.class);
            }
        };
        register(binder);

        packages("rs.raf.demo.resources");
    }
}
