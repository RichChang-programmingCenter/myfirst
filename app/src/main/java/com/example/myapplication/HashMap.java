package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HashMap<T, T1> implements Map<String, Object> {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(@Nullable Object key) {
        return false;
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        return false;
    }

    @Nullable
    @Override
    public Object get(@Nullable Object key) {
        return null;
    }

    @Nullable
    @Override
    public Object put(String key, Object value) {
        return null;
    }

    @Nullable
    @Override
    public Object remove(@Nullable Object key) {
        return null;
    }

    @Override
    public void putAll(@NonNull Map<? extends String, ?> m) {

    }

    @Override
    public void clear() {

    }

    @NonNull
    @Override
    public Set<String> keySet() {
        return null;
    }

    @NonNull
    @Override
    public Collection<Object> values() {
        return null;
    }

    @NonNull
    @Override
    public Set<Entry<String, Object>> entrySet() {
        return null;
    }
}
