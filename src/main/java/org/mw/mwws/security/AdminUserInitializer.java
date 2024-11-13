package org.mw.mwws.security;

import org.mw.mwws.entity.Permission;
import org.mw.mwws.entity.Role;
import org.mw.mwws.entity.User;
import org.mw.mwws.repo.PermissionRepo;
import org.mw.mwws.repo.RoleRepo;
import org.mw.mwws.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
public class AdminUserInitializer {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PermissionRepo permissionRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			if (userRepo.count() == 0) {
				createRolesAndPermissions();
				//createAdminUser();
			}
		};
	}

	@Transactional
	protected void createRolesAndPermissions() {
		// Create permissions
		Permission assignTaxiPermission = new Permission("ASSIGN_TAXI");
		Permission acceptRidePermission = new Permission("ACCEPT_RIDE");
		Permission viewDashboardPermission = new Permission("VIEW_DASHBOARD");
		permissionRepo.saveAll(List.of(assignTaxiPermission, acceptRidePermission, viewDashboardPermission));

		// Create roles
		Role adminRole = new Role("Admin");
		Role driverRole = new Role("Driver");

		// Assign permissions to roles
		adminRole.addPermission(assignTaxiPermission);
		adminRole.addPermission(viewDashboardPermission);

		driverRole.addPermission(acceptRidePermission);

		roleRepo.saveAll(List.of(adminRole, driverRole));

		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("admin"));
		admin.setPhone("09761122464");
		admin.addRole(adminRole);
		userRepo.save(admin);

		User driver = new User();
		driver.setUsername("driver");
		driver.setPassword(passwordEncoder.encode("driver"));
		driver.setPhone("09761122464");
		driver.addRole(driverRole);
		userRepo.save(driver);
	}
}
