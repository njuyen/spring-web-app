package com.supplier.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.supplier.domain.User;

@Service
public class UserRepository {
    private static final Map<Long, User> USER_DATA = new ConcurrentHashMap<>();
    private static AtomicLong userIndex = new AtomicLong(1000);
    static {
        USER_DATA.put(1001L, new User(1001L, "Admin", "123456789", User.Role.ADMIN));
        USER_DATA.put(1002L, new User(1002L, "User", "123456789", User.Role.USER));
        userIndex = new AtomicLong(1002);
    }

    public Optional<User> findOneById(long id) {
        Optional.empty();
        return Optional.of(USER_DATA.get(id));
    }

    public Optional<User> findOneByName(String name) {
        return USER_DATA.values().stream().sorted((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .filter(u -> name.equalsIgnoreCase(u.getName())).findFirst();
    }

    public Collection<User> findAll() {
        return USER_DATA.values();
    }

    public synchronized Long create(String name, String password) {
        Long id = userIndex.incrementAndGet();
        USER_DATA.put(id, new User(id, name, password, User.Role.USER));
        return id;
    }

}
