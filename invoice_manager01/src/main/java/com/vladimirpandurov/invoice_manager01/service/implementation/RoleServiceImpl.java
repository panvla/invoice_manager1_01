package com.vladimirpandurov.invoice_manager01.service.implementation;

import com.vladimirpandurov.invoice_manager01.domain.Role;
import com.vladimirpandurov.invoice_manager01.repository.RoleRepository;
import com.vladimirpandurov.invoice_manager01.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByUserId(Long userId) {
        return this.roleRepository.getRoleByUserId(userId);
    }
}
