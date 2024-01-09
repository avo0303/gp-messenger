package ru.avogp.messenger.ui.auth;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.avogp.messenger.Service;
import ru.avogp.messenger.ServiceSubscriber;

public class AuthServiceImpl implements AuthService {
  private final Logger logger;

  private SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
  // private ServiceSubscriber<String> subscriber;

  public AuthServiceImpl() {
    // subscriber = new ServiceSubscriber<>();
    logger = LogManager.getLogger(AuthServiceImpl.class);
  }

  @Override
  public void onAuth(Map<String, String> user) {
    logger.info("ONAUTH");
    logger.info(publisher.getSubscribers().size());
    publisher.submit(user.toString());
  }

  @Override
  public void onAuthSuccess() {
    // TODO Auto-generated method stub
    logger.error("Not implemented");
  }

  @Override
  public void onAuthFailed() {
    // TODO Auto-generated method stub
    logger.error("Not implemented");
  }

  @Override
  public void subscribe(ServiceSubscriber<Serializable> subscriber) {
    publisher.subscribe(subscriber);
  }

  @Override
  public void register(Consumer<Service> register) {
    register.accept(this);
  }
}
