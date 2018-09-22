package example.aop.clazz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FlowerDontTouch {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void touch() {
        logger.info("touch");
    }
}
