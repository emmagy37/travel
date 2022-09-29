package travel.service;

import travel.domain.User;

public interface UserService {
    /**
     * register
     * @param user
     * @return
     */
    boolean register(User user);

    boolean active(String code);

    User login(User user);
}
