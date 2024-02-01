package com.vladimirpandurov.invoice_manager01.service;

import com.vladimirpandurov.invoice_manager01.domain.Role;

public interface RoleService {

    Role getRoleByUserId(Long userId);

}
