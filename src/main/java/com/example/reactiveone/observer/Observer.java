package com.example.reactiveone.observer;

public interface Observer<T> {
   void observe(T event);
}