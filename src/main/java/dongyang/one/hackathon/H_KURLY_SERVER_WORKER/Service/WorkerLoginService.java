package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Service;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Worker;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class WorkerLoginService implements UserDetailsService {
    private final WorkerRepository workerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userId) {
        return workerRepository.findOneWithAuthoritiesByUserId(userId)
                .map(user -> createUser(user))
                .orElseThrow(() -> new UsernameNotFoundException(userId + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private User createUser(Worker worker) {

        List<GrantedAuthority> grantedAuthorities = worker.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        return new User(worker.getUserId(),
                worker.getPw(),
                grantedAuthorities
        );
    }
}
