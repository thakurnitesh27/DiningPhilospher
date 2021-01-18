package com.thakurnitesh27.diningphilosopher.enhance;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureCollection {

    //private List<T> list;

    private CompletableFutureCollection() {

    }

    public static  <T> List<CompletableFuture<T>> transform(Collection<T> collection) {
        ImmutableList<T> list = ImmutableList.copyOf(collection);
         return list.parallelStream().map(obj-> CompletableFuture.completedFuture(obj)).collect(Collectors.toList()) ;
    }

    /*public static  <T> List<CompletableFuture<T>> transformToArray(Collection<T> collection) {
        ImmutableList<T> list = ImmutableList.copyOf(collection);
        List<CompletableFuture<T>> result= list.parallelStream().map(obj-> CompletableFuture.completedFuture(obj)).collect(Collectors.toList()) ;
        return result.toArray();
    }*/


}
