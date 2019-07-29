package ttatli.com.movielistdemo.util;

public interface NotifyAction<T> {
    void onNotified(T key,T object);
}
