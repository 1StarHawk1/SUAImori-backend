package com.suaimori.backend.services;

import com.suaimori.backend.model.entities.Role;
import com.suaimori.backend.model.entities.RoleType;
import com.suaimori.backend.model.entities.Title;
import com.suaimori.backend.model.entities.User;
import com.suaimori.backend.repository.RoleRepository;
import com.suaimori.backend.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public UserDetails loadUserById(Long userId) throws UsernameNotFoundException{
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return optionalUser.get();
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return optionalUser.get();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User create(User user){
        if(userRepository.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }
        if(userRepository.existsByUsername(user.getUsername())){
            throw new IllegalArgumentException("User with username " + user.getUsername() + " already exists");
        }
        return saveUser(user);
    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::loadUserByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return loadUserByUsername(username);
    }

    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */
    @Deprecated
    public void getAdmin() {
//        var user = getCurrentUser();
//        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN);
//        if (adminRole == null) {
//            throw new IllegalArgumentException("Role not found");
//        }
//        user.getRoles().add(adminRole);
//        userRepository.save(user);
    }

    public Role findRoleByName(RoleType roleType) {
        return roleRepository.findByName(roleType).orElseThrow(()-> new RuntimeException("Error: Role is not found."));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Error: User is not found."));
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("Error: User is not found."));
    }

    public List<Tuple> getUser(String username, List<String> fields) {
        fields.add("username");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
        Root<User> root = cq.from(User.class);

        List<Selection<?>> selections = new ArrayList<>();
        for (String field : fields) {
            selections.add(root.get(field).alias(field));
        }
        cq.multiselect(selections);
        cq.where(cb.equal(root.get("username"), username));

        TypedQuery<Tuple> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}
