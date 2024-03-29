package ru.avogp.messenger;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Consumer;

public class SubscriberImpl<T> implements Subscriber<T> {
  private Subscription subscription;
  public List<T> consumedElements = new LinkedList<>();

  private final Consumer<T> onNextCallback;

  public SubscriberImpl(Consumer<T> onNextCallback) {
    this.onNextCallback = onNextCallback;
  }

  public SubscriberImpl() {
    this(null);
  }

  @Override
  public void onSubscribe(Subscription subscription) {
    this.subscription = subscription;
    subscription.request(1);
  }

  @Override
  public void onNext(T item) {
    System.out.println("Got : " + item);
    consumedElements.add(item);
    if (onNextCallback != null) {
      onNextCallback.accept(item);
    }
    subscription.request(1);
  }

  @Override
  public void onError(Throwable t) {
    t.printStackTrace();
  }

  @Override
  public void onComplete() {
    System.out.println("Done");
  }
}
