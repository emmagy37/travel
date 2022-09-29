package travel.service.impl;

import travel.dao.UserDao;
import travel.dao.impl.UserDaoImpl;
import travel.domain.User;
import travel.service.UserService;
import travel.util.MailUtils;
import travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean register(User user) {
        User u = userDao.findByUsername(user.getUsername());
        if(u!=null){
            return false;
        }

        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.save(user);

        String content = "<a href='http://localhost/activeUserServlet?code="+user.getCode()+"'>active</a>";

        MailUtils.sendMail(user.getEmail(),content,"active");

        return true;
    }

    @Override
    public boolean active(String code) {
        User user = userDao.findByCode(code);
        if(user != null){
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        User u = userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        return u;
    }
}
