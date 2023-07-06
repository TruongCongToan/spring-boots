package com.example.security.service.Impt;


import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import com.example.security.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    //final chỉ định rằng giá trị của trường không thể thay đổi sau khi được gán một lần.
    // Trường này sẽ được gán giá trị khi khởi tạo đối tượng và không thể thay đổi sau đó.

    @Override
    public User saveUser(User user) {
        user.setPassWord(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email).get();
        Role role = roleRepository.findByName(roleName);

        user.getRoles().add(role);

    }
}
